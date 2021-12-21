package teamSemiProject2.edu.kh.semi.order.model.service;

import static teamSemiProject2.edu.kh.semi.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import teamSemiProject2.edu.kh.semi.order.model.dao.OrderDAO;
import teamSemiProject2.edu.kh.semi.order.model.vo.Order;

public class OrderService {

	Connection conn = null;
	OrderDAO dao = new OrderDAO();
	
	public List<Order> getOrder(int loginMemberNo) throws Exception {

		conn = getConnection();
		List<Order> oList = null;
		
		
		oList = dao.getOrder(loginMemberNo,conn);
		
		close(conn);
		return oList;
	}

	public int amountChange(int orderAmount,int orderNo, int loginMemberNo  ) throws Exception {

		
		conn = getConnection();

		int result = dao.amountChange(orderAmount,orderNo,loginMemberNo,conn);
		
		
		if(result>0) {
			commit(conn);
		}else {
			rollback(conn);
		}

		return result;
	}

	public int deleteOrder(int orderNo, int loginMemberNo) throws Exception{

		
		conn = getConnection();

		int result = dao.deleteOrder(orderNo,loginMemberNo,conn);
		
		
		if(result>0) {
			commit(conn);
		}else {
			rollback(conn);
		}

		return result;
	}

}
