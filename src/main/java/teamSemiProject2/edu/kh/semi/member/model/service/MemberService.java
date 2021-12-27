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
		int result = dao.emailDupCheck(inputEmail, conn);
		close(conn);
		return result;
	}


	public String findId(Member member) throws Exception{
		String findId = dao.findId(member, conn);
		close(conn);
		return findId;
	}



	public int updatePw(String memberId, String memberPw) throws Exception{
		int result = dao.updatePw(memberId, memberPw, conn);
		if(result > 0 ){
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}


	public Member memberInfo(String memberName, String memberEmail) throws Exception{
		Member member = dao.memberInfo(memberName, memberEmail, conn);
		close(conn);
		return member;
	}




}
