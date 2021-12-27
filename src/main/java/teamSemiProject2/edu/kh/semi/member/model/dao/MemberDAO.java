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

   private PreparedStatement pstmt;
   private ResultSet rs;
   private Properties prop;

   public MemberDAO() {
      try {
         prop = new Properties();

         String filePath = MemberDAO.class.getResource("/teamSemiProject2/edu/kh/semi/sql/member-query.xml").getPath();
         prop.loadFromXML(new FileInputStream(filePath));
      } catch (Exception e) {
         e.printStackTrace();
      }

   }

   public Member login(String memberId, String memberPw, Connection conn) throws Exception {
      Member loginMember = null;

      try {
         String sql = prop.getProperty("login");

         pstmt = conn.prepareStatement(sql);

         pstmt.setString(1, memberId);
         pstmt.setString(2, memberPw);

         rs = pstmt.executeQuery();
         if (rs.next()) {
            loginMember = new Member();

            loginMember.setMemberNo(rs.getInt("MEMBER_NO"));
            loginMember.setMemberId(memberId);
            loginMember.setMemberEmail(rs.getString("MEMBER_EMAIL"));
            loginMember.setMemberName(rs.getString("MEMBER_NAME"));
            loginMember.setMemberBirthday(rs.getString("MEMBER_BIRTHDAY"));
            loginMember.setMemberPhone(rs.getString("MEMBER_PHONE"));
            loginMember.setEnrollDate(rs.getDate("ENROLL_DT"));
            loginMember.setStatusCode(rs.getInt("MEMBER_STATUS_NO"));
            loginMember.setGradeCode(rs.getInt("MEMBER_GRADE_NO"));
            loginMember.setMemberGradeName(rs.getString("MEMBER_GRADE_NAME"));
            loginMember.setMemberGradeDiscount(rs.getDouble("MEMBER_GRADE_DISCOUNT"));

         }
      } finally {
         close(rs);
         close(pstmt);
      }
      return loginMember;
   }
   
   

   public int signUp(Member member, Connection conn) throws Exception {

      int result = 0;

      try {
         String sql = prop.getProperty("signUp");

         pstmt = conn.prepareStatement(sql);

         pstmt.setString(1, member.getMemberId());
         pstmt.setString(2, member.getMemberPw());
         pstmt.setString(3, member.getMemberEmail());
         pstmt.setString(4, member.getMemberName());
         pstmt.setString(5, member.getMemberBirthday());
         pstmt.setString(6, member.getMemberPhone());

         result = pstmt.executeUpdate();

      } finally {
         close(pstmt);
      }

      return result;
   }

   public int idDupCheck(String inputId, Connection conn) throws Exception {

      int result = 0;

      try {
         String sql = prop.getProperty("idDupCheck");

         pstmt = conn.prepareStatement(sql);

         pstmt.setString(1, inputId);

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

   public int emailDupCheck(String inputEmail, Connection conn) throws Exception {

      int result = 0;

      try {
         String sql = prop.getProperty("emailDupCheck");
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, inputEmail);

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

   
   public String findId(Member member, Connection conn) throws Exception{
      String findId = null;
      
      try {
         String sql = prop.getProperty("findId");
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, member.getMemberEmail());
         pstmt.setString(2, member.getMemberName());
         
         rs = pstmt.executeQuery();
         if (rs.next()) {

            findId = rs.getString("MEMBER_ID");

         }
      } finally {
         close(rs);
         close(pstmt);
      }
      return findId;
   }


	

	public int updatePw(String memberId, String memberPw, Connection conn) throws Exception{
		int result = 0;
		
		try {
			String sql = prop.getProperty("updatePw");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberPw);
			pstmt.setString(2, memberId);
			
			result = pstmt.executeUpdate();
			
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	public Member memberInfo(String memberName, String memberEmail, Connection conn) throws Exception{
		Member member = null;
		
		try {
			String sql = prop.getProperty("memberInfo");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(2, memberName);
			pstmt.setString(3, memberEmail);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				member = new Member();

				member.setMemberNo(rs.getInt("MEMBER_NO"));
				member.setMemberId(rs.getString("MEMBER_ID"));
				member.setMemberEmail(rs.getString("MEMBER_EMAIL"));
				member.setMemberName(rs.getString("MEMBER_NAME"));
				member.setStatusCode(rs.getInt("MEMBER_STATUS_NO"));
				
			}
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return member;
	}


}