package teamSemiProject2.edu.kh.semi.member.model.service;

import static teamSemiProject2.edu.kh.semi.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import teamSemiProject2.edu.kh.semi.member.model.dao.MypageDAO;
import teamSemiProject2.edu.kh.semi.member.model.vo.AddrList;
import teamSemiProject2.edu.kh.semi.member.model.vo.Grade;
import teamSemiProject2.edu.kh.semi.member.model.vo.Member;
import teamSemiProject2.edu.kh.semi.member.model.vo.OrderList;
import teamSemiProject2.edu.kh.semi.order.model.dao.OrderDAO;

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

	
	
	/** 취소/교환/환불 내역 조회 Service
	 * @param memberNo
	 * @return orderStatusList
	 * @throws Exception
	 */
	public List<OrderList> selectOrderStatus(int memberNo) throws Exception{
		Connection conn = getConnection();
		
		List<OrderList> orderStatusList = dao.selectOrderStatus(memberNo, conn);
		
		close(conn);
		
		return orderStatusList;
	}



	/** 다음 등급 조회 Service
	 * @param memberNo
	 * @return Grade
	 * @throws Exception
	 */
	public Grade selectGrade(int memberNo) throws Exception {
		
		Connection conn = getConnection();
		
		Grade grade = dao.selectGrade(memberNo, conn);
		
		close(conn);
		
		return grade;
	}



	/** 배송지 목록 조회 
	 * @param memberNo
	 * @return addrList
	 * @throws Exception
	 */
	public List<AddrList> selectAddrList(int memberNo) throws Exception{
		
		Connection conn = getConnection();
		
		List<AddrList> addrList = dao.selectAddrList(memberNo, conn);
		
		close(conn);
		
		return addrList;
	}



	/** 기본 배송지 조회 service
	 * @param memberNo
	 * @return defaultAddr
	 * @throws Exception
	 */
	public AddrList selectDefaultAddr(int memberNo) throws Exception{
		
		Connection conn = getConnection();
		
		AddrList defaultAddr = dao.selectDefaultAddr(memberNo, conn);
		
		close(conn);
		
		return defaultAddr;
	}



	/** 배송지 수정
	 * @param updateAddr
	 * @return result (1: 성공, 0:실패)
	 * @throws Exception
	 */
	public int updateDeliveryAddr(AddrList updateAddr) throws Exception{
		Connection conn = getConnection();
		
		int result = dao.updateDeliveryAddr(updateAddr, conn);
		
		if(result> 0) commit(conn);
		else 		  rollback(conn);
		
		close(conn);
		
		return result;
	}
	
	/**
	 * 1.회원정보 갱신, + 2.기본주소가 존재하고, 회원이 수정페이지에서 주소 입력했다면 기본주소 갱신 + 3.갱신된 회원의 정보 들고와서
	 * 세션에 담기
	 * 
	 * @param tmp
	 * @return int
	 */
	public int updateMemberInfo(Member tmp) throws Exception {
		int result = 0;

		Connection conn = getConnection();

		result = dao.updateMember(tmp, conn);

		// 회원정보 수정,
		if (result > 0 && tmp.getDefaultAddress() != null) {
			// 만약 사용자가 기본 주소록 있으면 주소록 수정
			if (new OrderDAO().getPrimaryAddress(tmp.getMemberNo(), conn) != null) {
				result = 0;
				result = dao.updateDefaultAddr(tmp.getDefaultAddress(), conn);
			}

			if (result > 0) {
				commit(conn);
			} else {
				rollback(conn);
			}
		} else if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}

		return result;
	}

	public int secession(int memberNo) throws Exception {

		int result = 0;

		Connection conn = getConnection();

		result = dao.secession(memberNo, conn);

		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}

		close(conn);
		return result;
	}

	public int delCheckedAddr(String[] addrNo, int loginMemberNo) throws Exception {
		int result = 0;

		Connection conn = getConnection();

		for (int i = 0; i < addrNo.length; i++) {

			int no = Integer.parseInt(addrNo[i]);

			result = dao.delCheckedAddr(no, loginMemberNo, conn);

			if (result <= 0) {
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

}
