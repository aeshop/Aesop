package teamSemiProject2.edu.kh.semi.order.model.service;

import static teamSemiProject2.edu.kh.semi.common.JDBCTemplate.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.rowset.JoinRowSet;

import teamSemiProject2.edu.kh.semi.delivery.model.vo.Delivery;
import teamSemiProject2.edu.kh.semi.member.model.dao.MemberDAO;
import teamSemiProject2.edu.kh.semi.member.model.vo.Address;
import teamSemiProject2.edu.kh.semi.order.model.dao.OrderDAO;
import teamSemiProject2.edu.kh.semi.order.model.vo.Order;

public class OrderService {

	Connection conn = null;
	OrderDAO dao = new OrderDAO();

	/**
	 * 회원번호로 미결제상태인 주문 row 받아오기
	 * 
	 * @param loginMemberNo
	 * @return
	 * @throws Exception
	 */
	public List<Order> getOrder(int loginMemberNo) throws Exception {

		conn = getConnection();
		List<Order> oList = null;

		oList = dao.getOrder(loginMemberNo, conn);

		close(conn);
		return oList;
	}

	/**
	 * ajax 수량변경
	 * 
	 * @param orderAmount
	 * @param orderNo
	 * @param loginMemberNo
	 * @return
	 * @throws Exception
	 */
	public int amountChange(int orderAmount, int orderNo, int loginMemberNo) throws Exception {

		conn = getConnection();

		int result = dao.amountChange(orderAmount, orderNo, loginMemberNo, conn);

		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}

		return result;
	}

	/**
	 * 주문번호 해당하는 컬럼 주문취소로 상태변경
	 * 
	 * @param orderNo
	 * @param loginMemberNo
	 * @return
	 * @throws Exception
	 */
	public int deleteOrder(int orderNo, int loginMemberNo) throws Exception {

		conn = getConnection();

		int result = dao.deleteOrder(orderNo, loginMemberNo, conn);

		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}

		return result;
	}

	/**
	 * 해당하는 번호들 주문취소로 상태변경
	 * 
	 * @param orderNoArr
	 * @param loginMemberNo
	 * @return
	 * @throws Exception
	 */
	public int deleteAll(String[] orderNoArr, int loginMemberNo) throws Exception {

		conn = getConnection();
		int orderNo = 0;
		int result = 0;

		for (int i = 0; i < orderNoArr.length; i++) {
			orderNo = Integer.parseInt(orderNoArr[i]);

			result = dao.deleteOrder(orderNo, loginMemberNo, conn);

			if (result == 0) {
				rollback(conn);
				break;
			}

		}

		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	/**
	 * 선택한 주문번호에 해당하는 컬럼들만 배송 페이지로 가지고 옴
	 * 
	 * @param orderNoArr
	 * @param loginMemberNo
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> orderAll(String[] orderNoArr, int loginMemberNo) throws Exception {

		conn = getConnection();
		int[] orderNoArrInt = new int[orderNoArr.length];
		List<Order> resultList = null;

		// int 배열로 만들어서 전환
		for (int i = 0; i < orderNoArr.length; i++) {
			orderNoArrInt[i] = Integer.parseInt(orderNoArr[i]);

		}

		List<Order> oList = dao.getOrder(loginMemberNo, conn);
		// 자신이 장바구니에 담은 미결제상태의 모든 상품을 가지고 오기

		// java.util.ArrayList cannot be cast to
		// teamSemiProject2.edu.kh.semi.order.model.vo.OrderList ClassCastException발생함
//발생 원인: 자바의 기본적인 상속원리의 위배: 분명히 상위 타입으로 받은 것을 멋대로 downCasting을 시도하니까 안됬음,
		// 아예 건내줄때부터 상위 타입으로 지정되있어야 한다. 그러나, getOrder의 경우에는 무조건 ArrayList를 반환한다

//			즉 getOrder를 이용할꺼면 클래스를 재창조하는 내 방법은 사용하지 못하고, 아래에서 foreach문을 돌리면서 ResultList에만 그것을 추가

		resultList = new ArrayList<Order>();

		// 1.선택한 상품의 ArrayList
		// for - foreach : 이중 for 문 돌리면서 인수로 받았던 배열에 있으면 반환해서 추가하고 없으면 넘어감
		for (int i = 0; i < orderNoArrInt.length; i++) {

			for (Order order : oList) {
				if (order.getOrderNo() == orderNoArrInt[i]) {
					resultList.add(order);
					break;
				}
			}
		}

		// 2. 주소록의 기본주소 반환

		Address defaultAddr = dao.getPrimaryAddress(loginMemberNo, conn);

		
		
		
		// order ArrayList, defaultAddress를 맵에 담음

		HashMap<String, Object> resultMap = new HashMap<String, Object>();

		resultMap.put("orderList", resultList);

		resultMap.put("defaultAddress", defaultAddr);

		close(conn);

		return resultMap;
	}

	/**
	 * 1.주문 수량 조절, 2.가격 계산 , 3.배송번호 생성, 배송번호 레코드 생성 후 배송번호 반환하는
	 * 
	 * @param orderNoArr
	 * @param loginMemberNo
	 * @return
	 * @throws Exception
	 */
	public Map<String, String> beforeImport(String[] orderNoArr, int loginMemberNo) throws Exception {

		HashMap<String, String> resultMap = null;

		conn = getConnection();
		try {
			// 1. 주문 수량 조절: 제품 테이블 update - 실패시 예외 반환
			//1-2. 제품 상태코드(품절,정상) 확인 후 변경: 제품 테이블 update
			downStock(orderNoArr, loginMemberNo, conn);

			// 2. 가격 계산 : select를 여러번 수행해서 값 계산 : 제품가격*(1-할인율)*주문수량 을 주문번호 길이만큼 반복

			long totalPrice = getTotalPrice(orderNoArr, loginMemberNo, conn);

			// 3. 배송번호 생성 : 배송 번호를 만들고, 그게 중복레코드가 있는지를 체크한 다음에, 있으면 주문취소 없으면 레코드 삽입

			// 배송 번호는 날짜 - 배송카운트 +1 의 형태이다
			// 3.1 배송카운트를 얻어옴
			int todayDeliCount = dao.todayDeliCount(conn);

			SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
			Date time = new Date();
			String dateString = sf.format(time);

			// 3.2 나머지 자리는 0으로 채워진 6자리 배송카운트+1를 만듬
			String deliveryNo = String.format("%s-%06d", dateString, todayDeliCount + 1);

			// 3.3 혹시 삽입이 안되는 상황이 되는지 알기 위해 이미 같은 배송번호를 쓰는 레코드가 있는지 조회함
			boolean deliNoDupCheck = dao.deliNoDupCheck(deliveryNo, conn);
			int insertCheck = 0;

			if (!deliNoDupCheck) {
				insertCheck = dao.insertDeliNo(deliveryNo, totalPrice, loginMemberNo, conn);

				if (insertCheck > 0) {
					commit(conn);
					// 수량 조절하고(update), 가격 계산하고, 배송코드 만들고, insert해서 가격을 포함한 새로운 레코드 만드는데까지 성공하면
					// commit;
				} else {
					rollback(conn);
				}

			} else {
				// 있으면 null 반환
				return null;
			}

			resultMap = new HashMap<String, String>();
			// 모두 성공했으면 더 진행해도 된다는 status property=200와 총가격, 배송번호 반환
			resultMap.put("deliveryNo", deliveryNo);
			resultMap.put("totalPrice", totalPrice + "");
			resultMap.put("stCode", "200");

		} finally {

			close(conn);
		}

		return resultMap;
	}

	private long getTotalPrice(String[] orderNoArr, int loginMemberNo, Connection conn) throws Exception {
		long result = 0;
		// 가격 계산을 한다음 그 값을 가지고올지, 가지고 와서 가격 계산을 할지
		for (int i = 0; i < orderNoArr.length; i++) {
			int orderNo = Integer.parseInt(orderNoArr[i]);

			result += dao.getTotalPrice(orderNo, loginMemberNo, conn);

		}

		if (result < 50000)
			result += 2500;
//여기까지 하면 전체 상품의 계산은 완료되고 추가적으로 멤버의 할인율만큼 할인을 해줘야 한다
		result *= dao.getMember(loginMemberNo, conn).getMemberGradeDiscount();

		return result;
	}

	/**재고 감소후 품절 유무 확인
	 * @param orderNoArr
	 * @param loginMemberNo
	 * @param conn
	 * @throws Exception
	 */
	private void downStock(String[] orderNoArr, int loginMemberNo, Connection conn) throws Exception {
int flag = 0;
		for (int i = 0; i < orderNoArr.length; i++) {
			int orderNo = Integer.parseInt(orderNoArr[i]);

			
		flag =	dao.downStock(orderNo, loginMemberNo, conn);

		if(flag >0) {
			dao.checkStock(orderNo,conn);
			flag=0;
		} else {
			rollback(conn); return;
		}
			
		}
	}

	/**
	 * 배송번호 조회
	 * 
	 * @param merchantUid
	 * @param loginMemberNo
	 * @return
	 */
	public Delivery getDelivery(String merchantUid, int loginMemberNo) throws Exception {

		Delivery result = null;

		conn = getConnection();

		result = dao.getDelivery(merchantUid, loginMemberNo, conn);

		close(conn);

		return result;
	}

	public int completeDelOrder(Delivery del, int [] orderNoIntArr) throws Exception {
		int result = 0;
		conn = getConnection();

		//배송 테이블에 배송 정보 최종 업데이트
		result = dao.completeDelivery(del,conn);
		
		if(result <0) {
			rollback(conn); return 0;
		}
		//해당 배송과 연결될 주문 테이블들 업데이트
		
		result = completeOrder(del.getDeliveryNo(),orderNoIntArr,conn);
		
		if(result >0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		
		close(conn);
		return result;
	}

	private int completeOrder(String merchantUid, int[] orderNoIntArr,Connection conn) throws Exception {

		int result = 0;
		for(int i =0; i<orderNoIntArr.length;i++){
			
			result = dao.completeOrder(merchantUid,orderNoIntArr[i],conn);
			
			if(result >0) {
				continue;
			} else {
				rollback(conn);
				break;
			}
			
		}
		
		return result;
	}

	/** 생각해보니 이걸 먼저 진행할 껄 그랬음, 기본배송지도 Y가 N보다 후순위라는걸 고려하면 얼마든지 구할수 있었는데....
	 * @param loginMemberNo
	 * @return
	 * @throws Exception
	 */
	public List<Address> getAddress(int loginMemberNo) throws Exception {

		List<Address> result = null;
		
		conn=getConnection();
		
		
		result = dao.getAddress(loginMemberNo,conn);
		
		
		close(conn);
		return result;
	}

	/**아임포트 결제 취소시 서버 DB 조작
	 * 
	 *  *  1. 선택한 order들에 의한 product 재고 삭감 복구 - update
         2. 선택한 order들의 상태코드 변경 - update
         3. 해당 배송의 상태코드 변경 - update
	 * 
	 * 
	 * 
	 * @param orderNoArr
	 * @param deliveryNo
	 * @return
	 */
	public int payCancel(String[] orderNoArr, String deliveryNo) throws Exception{

		conn=getConnection();
		
		int result = 0;
		//1. 선택한 order들에 의한 product 재고 삭감 복구 & 품절유무 체크
		for(int i =0; i<orderNoArr.length;i++){
			
			int orderNo = Integer.parseInt(orderNoArr[i]);
			
		result =	dao.restoreStock(orderNo,conn);
		dao.checkStock(orderNo, conn);
		if(result<=0) {
			rollback(conn);
			break;
		} 

			
		
		}
	
		//3. 윗부분을 모두 성공했을 때 해당 배송의 상태코드 변경 - update 결제파기 = 506

		if(result>0) {
			result = dao.cancelDelivery(deliveryNo,conn);
			
			if(result>0) {
				commit(conn);
			} else {
				rollback(conn);
			}
		}
		
	
		
		close(conn);
		
		
		return result;
	}

}
