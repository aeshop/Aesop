package teamSemiProject2.edu.kh.semi.order.model.dao;

import static teamSemiProject2.edu.kh.semi.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.net.ssl.CertPathTrustManagerParameters;

import teamSemiProject2.edu.kh.semi.delivery.model.vo.Delivery;
import teamSemiProject2.edu.kh.semi.member.model.vo.Address;
import teamSemiProject2.edu.kh.semi.member.model.vo.Member;
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
				tmp.setProductStock(rs.getInt("STOCK"));
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

	/**
	 * 수량 변경 데이터베이스에 반영
	 * 
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

	/**
	 * 선택된 장바구니 레코드 지우기
	 * 
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

	/**
	 * 기본 주소 받아오기
	 * 
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
				addr.setAddress1(rs.getString("ADDRESS1"));
				addr.setAddress2(rs.getString("ADDRESS2"));
				addr.setMemberNo(rs.getInt("MEMBER_NO"));
				addr.setIsDefault(rs.getString("DEFAULT_ADDRESS"));
				addr.setAddrPhone(rs.getString("ADDRESS_PHONE"));
				addr.setReceiverName(rs.getString("ADDR_RECEIVER_NM"));
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

	/**
	 * 배송번호가 있는지 조회위해 체크
	 * 
	 * @param deliNum
	 * @param conn
	 * @return 중복됬을때 트루반환 없으면 펄스반환
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

	/**
	 * 중복되지 않을 때 새로운 배송 레코드를 삽입하는 메소드
	 * 
	 * @param deliNum
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	public int insertDeliNo(String deliveryNo, long totalPrice, int loginMemberNo, Connection conn) throws Exception {

		int result = 0;

		try {

			String sql = prop.getProperty("insertDeliNo");
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, deliveryNo);

			pstmt.setInt(2, loginMemberNo);

			pstmt.setLong(3, totalPrice);

			result = pstmt.executeUpdate();

		} finally {

			close(pstmt);

		}

		return result;
	}

	public int downStock(int orderNo, int loginMemberNo, Connection conn) throws Exception {
		int result = 0;

		try {

			String sql = prop.getProperty("downStock");
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, orderNo);
			pstmt.setInt(2, loginMemberNo);
			pstmt.setInt(3, orderNo);
			pstmt.setInt(4, loginMemberNo);

			result = pstmt.executeUpdate();

		} finally {
			close(pstmt);
		}

		return 1;
	}
	
	public void checkStock(int orderNo, Connection conn) throws Exception{

		try {
			String sql = prop.getProperty("checkStock");
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, orderNo);
			pstmt.setInt(2, orderNo);

			pstmt.executeUpdate();
			
			
		} finally {

close(pstmt);
		}
		
	}
	

	public long getTotalPrice(int orderNo, int loginMemberNo, Connection conn) throws SQLException {
		long result = 0;

		try {

			String sql = prop.getProperty("getTotalPrice");
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, orderNo);
			pstmt.setInt(2, loginMemberNo);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				result = rs.getLong(1);
			}

		} finally {
			close(rs);
			close(pstmt);
		}

		return result;
	}

	public Delivery getDelivery(String merchantUid, int loginMemberNo, Connection conn) throws Exception {

		Delivery result = null;
		try {

			String sql = prop.getProperty("getDelivery");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, merchantUid);
			pstmt.setInt(2, loginMemberNo);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				Delivery tmp = new Delivery();
				tmp.setDeliveryNo(rs.getString("DELIVERY_NO"));
				tmp.setMemberNo(rs.getInt("MEMBER_NO"));
				tmp.setZipCode(rs.getString("ZIP_CODE"));
				tmp.setAddress1(rs.getString("ADDRESS1"));
				tmp.setAddress2(rs.getString("ADDRESS2"));
				tmp.setReceiverName(rs.getString("RECEIVER_NAME"));
				tmp.setReceiverPhone(rs.getString("RECEIVER_PHONE"));
				tmp.setDeliveryDate(rs.getString("DELIVERY_DT"));
				tmp.setDeliveryStatusCode(rs.getInt("DELIVERY_STATUS_CD"));
				tmp.setDeliveryPrice(rs.getLong("TOTAL_PRICE"));
				tmp.setDeliveryMessage(rs.getString("DELIVERY_MESSAGE"));

				result = tmp;
			}

		} finally {

			close(rs);
			close(pstmt);

		}

		return result;
	}

	public Member getMember(int loginMemberNo, Connection conn) throws Exception {
		Member result = null;
		try {

			String sql = prop.getProperty("getMember");

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, loginMemberNo);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = new Member();
				result.setMemberGradeDiscount(rs.getDouble("MEMBER_GRADE_DISCOUNT"));
			}

		} finally {

			close(rs);
			close(pstmt);
		}

		return result;
	}

	/**
	 * 배송정보 update로 완성시키는 메소드
	 * 
	 * @param del
	 * @param merchantUid
	 * @param conn
	 * @return
	 */
	public int completeDelivery(Delivery del, Connection conn) throws Exception {

		int result = 0;
		try {

			String sql = prop.getProperty("completeDelivery");

			/*
			 * 
			 * update delivery set ZIP_CODE = ?, ADDRESS1 = ?, ADDRESS2 = ?,
			 * RECEIVER_NAME=?, DELIVERY_STATUS_CD = ?, DELIVERY_MESSAGE=? where delivery_no
			 * = ?
			 * 
			 * 
			 * 
			 * 
			 */

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, del.getZipCode());
			pstmt.setString(2, del.getAddress1());
			pstmt.setString(3, del.getAddress2());
			pstmt.setString(4, del.getReceiverName());
			pstmt.setString(5, del.getReceiverPhone());

			pstmt.setInt(6, del.getDeliveryStatusCode());
			pstmt.setString(7, del.getDeliveryMessage());
			pstmt.setString(8, del.getDeliveryNo());

			result = pstmt.executeUpdate();

		} finally {
			close(pstmt);

		}

		return result;
	}

	/**
	 * 주문정보 update로 완성시키는 메소드
	 * 
	 * @param merchantUid
	 * @param orderNoIntArr
	 * @param conn
	 * @return
	 */
	public int completeOrder(String merchantUid, int orderNo, Connection conn) throws Exception {

		int result = 0;

		try {

			String sql = prop.getProperty("completeOrder");

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, merchantUid);
			pstmt.setInt(2, orderNo);

			result = pstmt.executeUpdate();

		} finally {
			close(pstmt);

		}

		return result;
	}

	public List<Address> getAddress(int loginMemberNo, Connection conn) throws Exception {

		List<Address> resultList = new ArrayList<Address>();

		try {

			String sql = prop.getProperty("getAddress");

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, loginMemberNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				Address tmp = new Address();
				tmp.setAddressNo(rs.getInt("ADDRESS_NO"));
				tmp.setAddressName(rs.getString("ADDRESS_NM"));
				tmp.setZipCode(rs.getString("ZIP_CODE"));
				tmp.setAddress1(rs.getString("ADDRESS1"));
				tmp.setAddress2(rs.getString("ADDRESS2"));
				tmp.setIsDefault(rs.getString("DEFAULT_ADDRESS"));
				tmp.setAddrPhone(rs.getString("ADDRESS_PHONE"));
				tmp.setReceiverName(rs.getString("ADDR_RECEIVER_NM"));
				resultList.add(tmp);
			}

		} finally {

			close(rs);
			close(pstmt);
		}

		return resultList;
	}

	/** 제품재고복구
	 * @param orderNo
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	public int restoreStock(int orderNo, Connection conn) throws Exception {

	


		int result = 0;

		try {

			String sql = prop.getProperty("restoreStock");

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, orderNo);
			pstmt.setInt(2, orderNo);

			result = pstmt.executeUpdate();

		} finally {
			close(pstmt);

		}

		return result;
	
		
	
	
	}

	/** 선택한 order의 상태 코드 변경(결제취소 = 403)
	 * @param orderNo
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	public int cancelOrder(int orderNo, Connection conn) throws Exception {

	


		int result = 0;

		try {

			String sql = prop.getProperty("cancelOrder");

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, orderNo);

			result = pstmt.executeUpdate();

		} finally {
			close(pstmt);

		}

		return result;
	
		
	
	
	}

	/**- update 배송취소 = 505
	 * @param deliveryNo
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	public int cancelDelivery(String deliveryNo, Connection conn) throws Exception  {

		int result = 0;

		try {

			String sql = prop.getProperty("cancelDelivery");

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, deliveryNo);

			result = pstmt.executeUpdate();

		} finally {
			close(pstmt);

		}

		return result;
	
		
	}



}
