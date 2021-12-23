package teamSemiProject2.edu.kh.semi.member.model.service;

import static teamSemiProject2.edu.kh.semi.common.JDBCTemplate.*;

import java.sql.Connection;

import teamSemiProject2.edu.kh.semi.member.model.dao.MemberDAO;
import teamSemiProject2.edu.kh.semi.member.model.vo.Member;

public class MemberService {

	private MemberDAO dao = new MemberDAO();
	Connection conn = getConnection();
	
	
	public Member login(String memberId, String memberPw) throws Exception{
		
		Member loginMember = dao.login(memberId, memberPw, conn);
		
		close(conn);
		
		return loginMember;
	}


	public int signUp(Member member) throws Exception {
		
		int result = dao.signUp(member, conn);
		
		if(result > 0 ) commit(conn);
		else			rollback(conn);
		
		close(conn);
		
		return result;
	}



	/** 아이디 중복 확인
	 * @param inputId
	 * @return result (1 중복, 0 사용 가능)
	 * @throws Exception
	 */
	public int idDupCheck(String inputId) throws Exception{
		
		int result = dao.idDupCheck(inputId, conn);
		
		close(conn);
		
		return result;
	}



	/** 이메일 중복 확인
	 * @param inputEmail
	 * @return result(1 중복, 0 사용 가능)
	 * @throws Exception
	 */
	public int emailDupCheck(String inputEmail) throws Exception {
		Connection conn = getConnection();
		int result = dao.emailDupCheck(inputEmail, conn);
		close(conn);
		return result;
	}



	/** 아이디로 회원 정보 검색
	 * @param inputId
	 * @return member(null 조회결과 없음)
	 * @throws Exception
	 */
	public Member idSearch(String inputId) throws Exception{
		
		Connection conn = getConnection(); // DBCP에서 커넥션 얻어오기
		
		Member member = dao.idSearch(inputId, conn);
		
		close(conn); // 사용 완료된 커넥션을 DBCP에 반환
		
		return member;
	}

}
