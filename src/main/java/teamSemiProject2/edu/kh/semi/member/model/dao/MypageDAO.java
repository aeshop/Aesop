package teamSemiProject2.edu.kh.semi.member.model.dao;

import static teamSemiProject2.edu.kh.semi.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import teamSemiProject2.edu.kh.semi.member.model.vo.AddrList;
import teamSemiProject2.edu.kh.semi.member.model.vo.Address;
import teamSemiProject2.edu.kh.semi.member.model.vo.Grade;
import teamSemiProject2.edu.kh.semi.member.model.vo.Member;
import teamSemiProject2.edu.kh.semi.member.model.vo.OrderList;
import teamSemiProject2.edu.kh.semi.order.model.vo.Order;

public class MypageDAO {

	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private Properties prop;

	public MypageDAO() {
		try {
			prop = new Properties();

			String filePath = MypageDAO.class.getResource("/teamSemiProject2/edu/kh/semi/sql/mypage-query.xml")
					.getPath();

			prop.loadFromXML(new FileInputStream(filePath));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 최근 주문내역 조회 DAO
	 * 
	 * @param memberNo
	 * @param conn
	 * @return orderList
	 * @throws Exception
	 */
	public List<OrderList> selectOrderList(int memberNo, Connection conn) throws Exception {

		List<OrderList> orderList = new ArrayList<OrderList>();

		try {
			String sql = prop.getProperty("selectOrderList");

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, memberNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				OrderList order = new OrderList();

				order.setProductNo(rs.getInt("PRODUCT_NO"));
				order.setDeliveryDt(rs.getDate("DELIVERY_DT"));
				order.setProductImgPath(rs.getString("PRODUCT_IMG_PATH"));
				order.setProductImgNm(rs.getString("PRODUCT_IMG_NM"));
				order.setProductName(rs.getString("PRODUCT_NM"));
				order.setOrderAmount(rs.getInt("ORDER_AMOUNT"));
				order.setDeliveryNo(rs.getString("DELIVERY_NO"));
				order.setProductPrice(rs.getInt("PRODUCT_PRICE"));
				order.setOrderStatusName(rs.getString("ORDER_STATUS_NM"));

				orderList.add(order);

			}

		} finally {
			close(rs);
			close(pstmt);

		}

		return orderList;
	}

	/**
	 * 취소/교환/환불 내역 조회 DAO
	 * 
	 * @param memberNo
	 * @param conn
	 * @return orderStatusList
	 * @throws Exception
	 */
	public List<OrderList> selectOrderStatus(int memberNo, Connection conn) throws Exception {

		List<OrderList> orderStatusList = new ArrayList<OrderList>();

		try {
			String sql = prop.getProperty("selectOrderStatus");

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, memberNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				OrderList order = new OrderList();

				order.setDeliveryDt(rs.getDate("DELIVERY_DT"));
				order.setProductImgPath(rs.getString("PRODUCT_IMG_PATH"));
				order.setProductImgNm(rs.getString("PRODUCT_IMG_NM"));
				order.setProductName(rs.getString("PRODUCT_NM"));
				order.setOrderAmount(rs.getInt("ORDER_AMOUNT"));
				order.setDeliveryNo(rs.getString("DELIVERY_NO"));
				order.setProductPrice(rs.getInt("PRODUCT_PRICE"));
				order.setOrderStatusName(rs.getString("ORDER_STATUS_NM"));

				orderStatusList.add(order);

			}

		} finally {
			close(rs);
			close(pstmt);

		}

		return orderStatusList;
	}

	/**
	 * 다음 등급 조회 DAO
	 * 
	 * @param memberNo
	 * @param conn
	 * @return Grade
	 * @throws Exception
	 */
	public Grade selectGrade(int memberNo, Connection conn) throws Exception {
		Grade grade = null;

		try {
			String sql = prop.getProperty("selectGrade");

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, memberNo);
			pstmt.setInt(2, memberNo);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				grade = new Grade();

				grade.setMemberGradeName(rs.getString("MEMBER_GRADE_NAME"));
				grade.setMemberPurchaseAmount(rs.getInt("MEMBER_PURCHASE_AMOUNT"));
				grade.setLeftMoney(rs.getString("남은금액"));
				grade.setMemberGradeDiscount(rs.getDouble("MEMBER_GRADE_DISCOUNT"));
			}

		} finally {
			close(rs);
			close(pstmt);
		}

		return grade;
	}

	/**
	 * 배송지 목록 DAO
	 * 
	 * @param memberNo
	 * @param conn
	 * @return addrList
	 * @throws Exception
	 */
	public List<AddrList> selectAddrList(int memberNo, Connection conn) throws Exception {
		List<AddrList> addrList = new ArrayList<AddrList>();

		try {
			String sql = prop.getProperty("selectAddrList");

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, memberNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				AddrList addr = new AddrList();

				addr.setAddrNo(rs.getInt("ADDRESS_NO"));
				addr.setAddrName(rs.getString("ADDRESS_NM"));
				addr.setZipCode(rs.getString("ZIP_CODE"));
				addr.setAddress1(rs.getString("ADDRESS1"));
				addr.setAddress2(rs.getString("ADDRESS2"));
				addr.setDefaultAddress(rs.getString("DEFAULT_ADDRESS"));
				addr.setMemberNo(memberNo);
				addr.setAddrPhone(rs.getString("ADDRESS_PHONE"));
				addr.setAddrReceiverName(rs.getString("ADDR_RECEIVER_NM"));

				addrList.add(addr);

			}

		} finally {
			close(rs);
			close(pstmt);
		}

		return addrList;
	}

	/**
	 * 기본배송지 DAO
	 * 
	 * @param memberNo
	 * @param conn
	 * @return defaultAddr
	 * @throws Exception
	 */
	public AddrList selectDefaultAddr(int memberNo, Connection conn) throws Exception {
		AddrList defaultAddr = null;

		try {
			String sql = prop.getProperty("selectDefaultAddr");

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, memberNo);

			rs = pstmt.executeQuery();

			if (rs.next()) {

				defaultAddr = new AddrList();

				defaultAddr.setZipCode(rs.getString("ZIP_CODE"));
				defaultAddr.setAddress1(rs.getString("ADDRESS1"));
				defaultAddr.setAddress2(rs.getString("ADDRESS2"));

			}

		} finally {
			close(rs);
			close(pstmt);
		}

		return defaultAddr;
	}

	/**
	 * 배송지 수정 DAO
	 * 
	 * @param updateAddr
	 * @param conn
	 * @return result( 1:성공, 0:실패)
	 * @throws Exception
	 */
	public int updateDeliveryAddr(AddrList updateAddr, Connection conn) throws Exception {
		int result = 0;

		try {
			String sql = prop.getProperty("updateDeliveryAddr");

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, updateAddr.getAddrName());
			pstmt.setString(2, updateAddr.getAddrReceiverName());
			pstmt.setString(3, updateAddr.getZipCode());
			pstmt.setString(4, updateAddr.getAddress1());
			pstmt.setString(5, updateAddr.getAddress2());
			pstmt.setString(6, updateAddr.getAddrPhone());
			pstmt.setInt(7, updateAddr.getAddrNo());

			result = pstmt.executeUpdate();

		} finally {
			close(pstmt);
		}

		return result;
	}

	public int updateMember(Member tmp, Connection conn) throws Exception {

		int result = 0;
		try {
			String sql = prop.getProperty("updateMember");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, tmp.getMemberPw());
			pstmt.setString(2, tmp.getMemberEmail());
			pstmt.setString(3, tmp.getMemberName());
			pstmt.setString(4, tmp.getMemberPhone());
			pstmt.setString(5, tmp.getMemberBirthday());
			pstmt.setInt(6, tmp.getMemberNo());

			result = pstmt.executeUpdate();
		} finally {

			close(pstmt);

		}

		return result;
	}

	public int updateDefaultAddr(Address deAddr, Connection conn) throws Exception {

		int result = 0;
		try {
			String sql = prop.getProperty("updateDefaultAddr");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, deAddr.getZipCode());
			pstmt.setString(2, deAddr.getAddress1());
			pstmt.setString(3, deAddr.getAddress2());
			pstmt.setInt(4, deAddr.getMemberNo());

			result = pstmt.executeUpdate();
		} finally {

			close(pstmt);

		}

		return result;
	}

	public int secession(int memberNo, Connection conn) throws Exception {
		int result = 0;

		try {

			String sql = prop.getProperty("secession");

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, memberNo);

			result = pstmt.executeUpdate();

		} finally {
			close(pstmt);

		}

		return result;
	}

	public int delCheckedAddr(int no, int loginMemberNo, Connection conn) throws Exception {

		int result = 0;

		try {

			String sql = prop.getProperty("delCheckedAddr");

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, no);
			pstmt.setInt(2, loginMemberNo);

			result = pstmt.executeUpdate();

		} finally {
			close(pstmt);

		}

		return result;
	}

	/**
	 * 배송지 등록 DAO
	 * 
	 * @param registerAddr
	 * @param conn
	 * @return result(1:성공, 0:실패)
	 * @throws Exception
	 */
	public int registerDeliveryAddr(AddrList registerAddr, Connection conn) throws Exception {

		int result = 0;

		try {
			String sql = prop.getProperty("registerDeliveryAddr");

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, registerAddr.getAddrName());
			pstmt.setString(2, registerAddr.getZipCode());
			pstmt.setString(3, registerAddr.getAddress1());
			pstmt.setString(4, registerAddr.getAddress2());
			pstmt.setInt(5, registerAddr.getMemberNo());
			pstmt.setString(6, registerAddr.getAddrPhone());
			pstmt.setString(7, registerAddr.getAddrReceiverName());

			result = pstmt.executeUpdate();

		} finally {
			close(pstmt);
		}

		return result;

	}

	/**
	 * 주문내역 카운트
	 * 
	 * @param memberNo
	 * @param conn
	 * @return result(1:성공 , 0:실패)
	 * @throws Exception
	 */
	public int selectCountNum(int memberNo, Connection conn) throws Exception {
		int result = 0;

		try {
			String sql = prop.getProperty("selectCountNum");

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, memberNo);

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

	public List<OrderList> getOrderDetail(int memberNo, String deliveryNo, Connection conn) throws Exception {

		List<OrderList> oList = new ArrayList<OrderList>();

		try {

			String sql = prop.getProperty("getOrderDetail");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, memberNo);
			pstmt.setString(2, deliveryNo);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				OrderList tmp = new OrderList();
				
				tmp.setOrderNo(rs.getInt("ORDER_NO"));
				tmp.setProductNo(rs.getInt("PRODUCT_NO"));
				tmp.setOrderAmount(rs.getInt("ORDER_AMOUNT"));
				tmp.setDeliveryNo(rs.getString("DELIVERY_NO"));
				tmp.setProductName(rs.getString("PRODUCT_NM"));
				tmp.setProductPrice(rs.getInt("PRODUCT_PRICE"));
				tmp.setProductDiscount(rs.getDouble("DISCOUNT"));
				tmp.setProductImgPath(rs.getString("PRODUCT_IMG_PATH"));
				tmp.setProductImgNm(rs.getString("PRODUCT_IMG_NM"));
				
				oList.add(tmp);
			}
			
			
			
			
		} finally {

			close(rs);
			close(pstmt);
		}

		return oList;
	}

}
