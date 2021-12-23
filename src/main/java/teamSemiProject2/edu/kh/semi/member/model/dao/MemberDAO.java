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

			String filePath = MemberDAO.class.getResource("/teamSemiProject2/edu/kh/semi/sql/member-query.xml")
					.getPath();

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

	/**
	 * 아이디 중복 확인
	 * 
	 * @param inputId
	 * @param conn
	 * @return result (1 중복, 0 사용 가능)
	 * @throws Exception
	 */
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

	/**
	 * 이메일 중복 체크
	 * 
	 * @param inputEmail
	 * @param conn
	 * @return result(1 중복, 0 사용 가능)
	 * @throws Exception
	 */
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

	/**
	 * 아이디로 회원 정보 검색
	 * 
	 * @param inputId
	 * @param conn
	 * @return member
	 * @throws Exception
	 */
	public Member idSearch(String inputId, Connection conn) throws Exception {

		Member member = null;

		try {
			String sql = prop.getProperty("idSearch");

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, inputId);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				member = new Member();

				member.setMemberId(inputId);

				member.setMemberName(rs.getString(1));
				member.setMemberPhone(rs.getString(2));
				member.setMemberEmail(rs.getString(3));
				member.setMemberBirthday(rs.getString(4));

			}

		} finally {
			close(rs);
			close(pstmt);
		}

		return member;
	}

}
