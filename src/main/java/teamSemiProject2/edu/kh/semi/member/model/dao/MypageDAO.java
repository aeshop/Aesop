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

import teamSemiProject2.edu.kh.semi.member.model.vo.OrderList;

public class MypageDAO {
	
	   private Statement stmt;
	   private PreparedStatement pstmt;
	   private ResultSet rs;
	   private Properties prop;
	   
	   public MypageDAO() {
		      try {
		         prop = new Properties();
		         
		         String filePath 
		         = MypageDAO.class.getResource("/teamSemiProject2/edu/kh/semi/sql/mypage-query.xml").getPath();
		         
		         prop.loadFromXML( new FileInputStream( filePath ) );
		      }catch (Exception e) {
		         e.printStackTrace();
		      }
		      
		   }
	   
	   
	/** 최근 주문내역 조회 DAO
	 * @param memberNo
	 * @param conn
	 * @return orderList
	 * @throws Exception
	 */
	public List<OrderList> selectOrderList(int memberNo, Connection conn) throws Exception{
		
		List<OrderList> orderList = new ArrayList<OrderList>();
		
		try {
			String sql = prop.getProperty("selectOrderList");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, memberNo);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				OrderList order = new OrderList();
				
				order.setDeliveryDt(rs.getDate("DELIVERY_DT"));
				order.setProductImgPath(rs.getString("PRODUCT_IMG_PATH"));
				order.setProductName(rs.getString("PRODUCT_NM"));
				order.setOrderAmount(rs.getInt("ORDER_AMOUNT"));
				order.setDeliveryNo(rs.getString("DELIVERY_NO"));
				order.setProductPrice(rs.getInt("PRODUCT_PRICE"));
				order.setOrderStatusName(rs.getString("ORDER_STATUS_NM"));
				
				orderList.add(order);
				
			}
			
		}finally {
			close(rs);
			close(pstmt);
			
		}
		
		return orderList;
	}
	   
	   

}
