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

}
