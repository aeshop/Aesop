package teamSemiProject2.edu.kh.semi.order.model.dao;

import static teamSemiProject2.edu.kh.semi.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

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
	 * @return
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

	public int deleteOrder(int orderNo, int loginMemberNo, Connection conn)  throws Exception{
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

}
