package teamSemiProject2.edu.kh.semi.member.model.service;

import static teamSemiProject2.edu.kh.semi.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import teamSemiProject2.edu.kh.semi.member.model.dao.MypageDAO;
import teamSemiProject2.edu.kh.semi.member.model.vo.OrderList;

public class MypageService {
	
	private MypageDAO dao = new MypageDAO();

	/** 최근 주문내역 조회 Service
	 * @param memberNo
	 * @return orderList
	 * @throws Exception
	 */
	public List<OrderList> selectOrderList(int memberNo) throws Exception {
		
		Connection conn = getConnection();
		
		List<OrderList> orderList = dao.selectOrderList(memberNo, conn);
		
		close(conn);
		
		return orderList;
	}

}
