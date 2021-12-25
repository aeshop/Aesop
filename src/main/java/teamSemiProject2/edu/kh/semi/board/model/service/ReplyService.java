package teamSemiProject2.edu.kh.semi.board.model.service;


import static teamSemiProject2.edu.kh.semi.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import teamSemiProject2.edu.kh.semi.board.model.dao.ReplyDAO;
import teamSemiProject2.edu.kh.semi.board.model.vo.Reply;
import teamSemiProject2.edu.kh.semi.common.XSS;

public class ReplyService {
	ReplyDAO dao = new ReplyDAO();

	public List<Reply> selectReplyList(int boardNo) throws Exception {
		Connection conn = getConnection();

		List<Reply> rList = dao.selectReplyList(boardNo, conn);

		close(conn);

		return rList;
	}

	public int insertReply(Reply reply) throws Exception {
		Connection conn = getConnection();

		reply.setReplyContent(XSS.replaceParameter(reply.getReplyContent()));

		// 개행문자 처리코드
		reply.setReplyContent(reply.getReplyContent().replaceAll("(\r\n|\r|\n|\n\r)", "<br>"));
		
		int result = dao.insertReply(conn,reply);
		
		if(result>0) commit(conn);
		else rollback(conn);
		return result;
	}

	public int updateReply(int replyNo, String replyContent) throws Exception{
		Connection conn = getConnection();
		
		replyContent = XSS.replaceParameter(replyContent);
		replyContent = replyContent.replaceAll("(\r\n|\r|\n|\n\r)", "<br>");
		int result = dao.updateReply(replyNo,replyContent,conn);
		
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}

	public int deleteReply(int replyNo) throws Exception{
		Connection conn = getConnection();
		
		int result  = dao.deleteReply(replyNo,conn);
		
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
		
	}

}
