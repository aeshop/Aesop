package teamSemiProject2.edu.kh.semi.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import teamSemiProject2.edu.kh.semi.board.model.service.ReplyService;
import teamSemiProject2.edu.kh.semi.board.model.vo.Reply;

@WebServlet("/reply/*")
public class ReplyController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String method = req.getMethod();
		String uri = req.getRequestURI();
		String contextPath = req.getContextPath();
		String command = uri.substring(  (contextPath + "/reply/").length()  );
		System.out.println(contextPath);
		System.out.println(command);
		
		ReplyService service = new ReplyService();
		try {
			if(command.equals("select")) {
				int boardNo = Integer.parseInt(req.getParameter("boardNo"));
				List<Reply> rList = service.selectReplyList(boardNo);
				
				System.out.println(rList);
				new Gson().toJson(rList,resp.getWriter());
			}
			else if(command.equals("insert")) {
				int memberNo = Integer.parseInt(req.getParameter("memberNo"));
				int boardNo = Integer.parseInt(req.getParameter("boardNo"));
				
				String replyContent = req.getParameter("replyContent");
				
				Reply reply = new Reply();
				reply.setMemberNo(memberNo);
				reply.setBoardNo(boardNo);
				reply.setReplyContent(replyContent);
				
				int result = service.insertReply(reply);
				
				resp.getWriter().print(result);
			}
			
			else if (command.equals("update")) {
				int replyNo = Integer.parseInt(req.getParameter("replyNo"));
				String replyContent = req.getParameter("replyContent");
				
				int result = service.updateReply(replyNo,replyContent);
				resp.getWriter().print(result);
			}
			else if (command.equals("delete")) {
				int replyNo = Integer.parseInt(req.getParameter("replyNo"));
				
				int result = service.deleteReply(replyNo);
				resp.getWriter().print(result);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			
			// ajax error 속성 활용을 위한 500에러를 응답으로 전달
			resp.setStatus(500);
			resp.getWriter().print(e.getMessage());
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req,resp);
	}
}
