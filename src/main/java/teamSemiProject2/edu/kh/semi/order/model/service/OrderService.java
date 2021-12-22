package teamSemiProject2.edu.kh.semi.order.model.service;

import static teamSemiProject2.edu.kh.semi.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

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
	public List<Order> orderAll(String[] orderNoArr, int loginMemberNo) throws Exception {

		conn = getConnection();
		int [] orderNoArrInt = new int [orderNoArr.length];
		List<Order> resultList = null;

		//int 배열로 만들어서 전환
		for (int i = 0; i < orderNoArr.length; i++) {
			orderNoArrInt[i] = Integer.parseInt(orderNoArr[i]);

		}
		
		List<Order> oList =  dao.getOrder(loginMemberNo, conn);

		//java.util.ArrayList cannot be cast to teamSemiProject2.edu.kh.semi.order.model.vo.OrderList ClassCastException발생함
//발생 원인: 자바의 기본적인 상속원리의 위배: 분명히 상위 타입으로 받은 것을 멋대로 downCasting을 시도하니까 안됬음,
			//아예 건내줄때부터 상위 타입으로 지정되있어야 한다. 그러나, getOrder의 경우에는 무조건 ArrayList를 반환한다
			
//			즉 getOrder를 이용할꺼면 클래스를 재창조하는 내 방법은 사용하지 못하고, 아래에서 foreach문을 돌리면서 ResultList에만 그것을 추가

			resultList = new ArrayList<Order>();

			//for - foreach : 이중 for 문 돌리면서 있으면 반환해서 추가하고 없으면 null;
			
			
			for(int i =0; i<orderNoArrInt.length;i++){
				
				for (Order order : oList) {
					if(order.getOrderNo()==orderNoArrInt[i]) {
						resultList.add(order);
						break;
					}
				}
				
				
				
			}
			
			
			
		
		close(conn);

		return resultList;
	}

}
