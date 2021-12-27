package teamSemiProject2.edu.kh.semi.board.model.dao;

import static teamSemiProject2.edu.kh.semi.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import teamSemiProject2.edu.kh.semi.board.model.vo.Reply;
public class ReplyDAO {
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private Properties prop;

	public ReplyDAO() {
	
	String filePath = BoardDAO.class.getResource("/teamSemiProject2/edu/kh/semi/sql/reply-query.xml").getPath();
	
	try {
		prop = new Properties();
		prop.loadFromXML(new FileInputStream(filePath));
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	}

	public List<Reply> selectReplyList(int boardNo, Connection conn) throws Exception{
		
		List<Reply> rList = new ArrayList<Reply>();
		try {
			String sql = prop.getProperty("selectReplyList");
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, boardNo);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Reply reply = new Reply();
				reply.setReplyNo(rs.getInt("REPLY_NO"));
				reply.setReplyContent(rs.getString("REPLY_CONTENT"));
				reply.setReplyCreateDate(rs.getString("REPLY_CREATE_DT"));
				reply.setBoardNo(rs.getInt("BOARD_NO"));
				reply.setMemberNo(rs.getInt("MEMBER_NO"));
				reply.setMemberName(rs.getString("MEMBER_NAME"));
				reply.setReplyStatusCode(rs.getInt("REPLY_STATUS_CD"));
				reply.setReplyStatusName(rs.getString("REPLY_STATUS_NM"));

				rList.add(reply);
			}
		} finally {
			close(rs);
			close(pstmt);
		}
		return rList;
	}

	public int insertReply(Connection conn, Reply reply) throws Exception{
		
		int result = 0;
		try {
			String sql = prop.getProperty("insertReply");
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, reply.getReplyContent());
			pstmt.setInt(2, reply.getBoardNo());
			pstmt.setInt(3, reply.getMemberNo());
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int updateReply(int replyNo, String replyContent, Connection conn) throws Exception{
		int result = 0;
		
		try {
			String sql = prop.getProperty("updateReply");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,replyContent);
			pstmt.setInt(2, replyNo);
			
			result = pstmt.executeUpdate();
		} finally {
			
			close(pstmt);
		}
		
		return result;
	}

	public int deleteReply(int replyNo, Connection conn) throws Exception{
		
		int result = 0;
		try {
			String sql = prop.getProperty("deleteReply");
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, replyNo);
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}
}
	
