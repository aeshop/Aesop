package teamSemiProject2.edu.kh.semi.member.model.dao;

import static teamSemiProject2.edu.kh.semi.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import teamSemiProject2.edu.kh.semi.member.model.vo.Member;

public class MemberDAO {
	
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private Properties prop;
	
	
	public MemberDAO() {
		try {
			prop = new Properties();
			
			String filePath 
			= MemberDAO.class.getResource("/teamSemiProject2/edu/kh/semi/sql/member-query.xml").getPath();
			
			prop.loadFromXML( new FileInputStream( filePath ) );
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public Member login(String memberId, String memberPw, Connection conn) throws Exception{
		Member loginMember = null;
		
		try {
			String sql = prop.getProperty("login");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, memberId);
			pstmt.setString(2, memberPw);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				loginMember = new Member();
				
				loginMember.setMemberNo(rs.getInt("MEMBER_NO"));
				loginMember.setMemberId(memberId);
				loginMember.setMemberEmail(rs.getString("MEMBER_EMAIL"));
				loginMember.setMemberName(rs.getString("MEMBER_NAME"));
				loginMember.setMemberBirthday(rs.getInt("MEMBER_BIRTHDAY"));
				loginMember.setMemberPhone(rs.getString("MEMBER_PHONE"));
				loginMember.setEnrollDate(rs.getDate("ENROLL_DT"));
				loginMember.setStatusCode(rs.getInt("MEMBER_STATUS_NO"));
				loginMember.setGradeCode(rs.getInt("MEMBER_GRADE_NO"));
				loginMember.setMemberGradeName(rs.getString("MEMBER_GRADE_NAME"));
				loginMember.setMemberGradeDiscount(rs.getInt("MEMBER_GRADE_DISCOUNT"));
				
			}
		} finally {
			close(rs);
			close(pstmt);
		}
		return loginMember;
	}

}
