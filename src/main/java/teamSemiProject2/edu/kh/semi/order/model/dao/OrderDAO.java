package teamSemiProject2.edu.kh.semi.order.model.dao;

import static teamSemiProject2.edu.kh.semi.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import teamSemiProject2.edu.kh.semi.member.model.vo.Address;
import teamSemiProject2.edu.kh.semi.order.model.vo.Order;

public class OrderDAO {

	PreparedStatement pstmt = null;
	ResultSet rs = null;

	Properties prop = null;

	public OrderDAO() {
		try {
			prop = new Properties();
			String filePath = OrderDAO.class.getResource("/teamSemiProject2/edu/kh/semi/sql/order-query.xml").getPath();
			prop.loadFromXML(new FileInputStream(filePath));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 회원번호로 주문정보 받아오기
	 * 
	 * @param loginMemberNo
	 * @param conn
	 * @return ArrayList
	 * @throws Exception
	 */
	public List<Order> getOrder(int loginMemberNo, Connection conn) throws Exception {

		List<Order> oList = new ArrayList<Order>();

		try {
			String sql = prop.getProperty("getOrder");

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, loginMemberNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				Order tmp = new Order();
				tmp.setOrderNo(rs.getInt("ORDER_NO"));
				tmp.setMemberNo(rs.getInt("MEMBER_NO"));
				tmp.setProductNo(rs.getInt("PRODUCT_NO"));
				tmp.setOrderAmount(rs.getInt("ORDER_AMOUNT"));
				tmp.setOrderStatusCode(rs.getInt("ORDER_STATUS_CD"));

				// 상품정보
				tmp.setProductName(rs.getString("PRODUCT_NM"));
				tmp.setProductPrice(rs.getInt("PRODUCT_PRICE"));
				tmp.setProductDiscount(rs.getDouble("DISCOUNT"));
				tmp.setThumnailImgPath(rs.getString("PRODUCT_IMG_PATH"));
				tmp.setThumnailImgName(rs.getString("PRODUCT_IMG_NM"));

				oList.add(tmp);
			}

		} finally {
			close(rs);
			close(pstmt);
		}

		return oList;
	}

	/**수량 변경 데이터베이스에 반영
	 * @param orderAmount
	 * @param orderNo
	 * @param loginMemberNo
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	public int amountChange(int orderAmount, int orderNo, int loginMemberNo, Connection conn) throws Exception {
		int result = 0;

		try {

			String sql = prop.getProperty("amountChange");
			pstmt = conn.prepareStatement(sql);
//UPDATE order_1 SET order_amount = ? WHERE order_no = ? AND member_no = ?

			pstmt.setInt(1, orderAmount);
			pstmt.setInt(2, orderNo);
			pstmt.setInt(3, loginMemberNo);

			result = pstmt.executeUpdate();

		} finally {

			close(pstmt);

		}

		return result;
	}

	/**선택된 장바구니 레코드 지우기
	 * @param orderNo
	 * @param loginMemberNo
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	public int deleteOrder(int orderNo, int loginMemberNo, Connection conn) throws Exception {
		int result = 0;

		try {

			String sql = prop.getProperty("deleteOrder");
			pstmt = conn.prepareStatement(sql);
//			UPDATE order_1 SET order_status_cd = 403 WHERE order_no = ? AND member_no = ?

			pstmt.setInt(1, orderNo);
			pstmt.setInt(2, loginMemberNo);

			result = pstmt.executeUpdate();

		} finally {

			close(pstmt);

		}

		return result;
	}

	/**기본 주소 받아오기
	 * @param loginMemberNo
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	public Address getPrimaryAddress(int loginMemberNo, Connection conn) throws Exception {

		Address addr = null;
		try {
			String sql = prop.getProperty("getPrimaryAddress");

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, loginMemberNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				addr = new Address();
				addr.setAddressNo(rs.getInt("ADDRESS_NO"));
				addr.setZipCode(rs.getString("ZIP_CODE"));
				addr.setAddressName(rs.getString("ADDRESS_NM"));
				addr.setAddress1("ADDRESS1");
				addr.setAddress2("ADDRESS2");
				addr.setMemberNo(rs.getInt("MEMBER_NO"));
				addr.setIsDefault(rs.getString("DEFAULT_ADDRESS"));
				addr.setAddrPhone(rs.getString("ADDRESS_PHONE"));
			}

		} finally {
			close(rs);
			close(pstmt);
		}

		return addr;
	}



	/**
	 * 오늘 쌓인 배송 레코드 count를 받아오는 메소드
	 * 
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	public int todayDeliCount(Connection conn) throws Exception {

		int result = 0;

		try {

			String sql = prop.getProperty("todayDeliCount");

			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				result = rs.getInt(1);
			}

		} finally {
			close(rs);
			close(pstmt);
		}

		return result;
	}

	/** 배송번호가 있는지 조회위해 체크
	 * @param deliNum
	 * @param conn
	 * @return  중복됬을때 트루반환 없으면 펄스반환
	 * @throws Exception
	 */
	public boolean deliNoDupCheck(String deliveryNo, Connection conn) throws Exception {

		try {

			String sql = prop.getProperty("deliNoDupCheck");

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, deliveryNo);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				return true;
			}

		} finally {
			close(rs);
			close(pstmt);

		}

		return false;
	}

	/** 중복되지 않을 때 새로운 배송 레코드를 삽입하는 메소드
	 * @param deliNum
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	public int insertDeliNo(String deliveryNo, int loginMemberNo,Connection conn) throws Exception {

		int result = 0;

		try {

			String sql = prop.getProperty("insertDeliNo");
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, deliveryNo);
			pstmt.setInt(2, loginMemberNo);

			result = pstmt.executeUpdate();

		} finally {

			close(pstmt);

		}

		return result;
	}


}
