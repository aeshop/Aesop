package teamSemiProject2.edu.kh.semi.member.model.service;

import static teamSemiProject2.edu.kh.semi.common.JDBCTemplate.*;

import java.sql.Connection;

import teamSemiProject2.edu.kh.semi.member.model.dao.MemberDAO;
import teamSemiProject2.edu.kh.semi.member.model.vo.Member;

public class MemberService {

	private MemberDAO dao = new MemberDAO();
	
	
	public Member login(String memberId, String memberPw) throws Exception{
		Connection conn = getConnection();
		
		Member loginMember = dao.login(memberId, memberPw, conn);
		
		close(conn);
		
		return loginMember;
	}

}
