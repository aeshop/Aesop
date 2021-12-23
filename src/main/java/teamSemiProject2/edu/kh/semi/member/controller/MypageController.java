package teamSemiProject2.edu.kh.semi.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import teamSemiProject2.edu.kh.semi.member.model.service.MemberService;
import teamSemiProject2.edu.kh.semi.member.model.vo.Member;

@WebServlet("/myPage/*")
public class MypageController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String method = req.getMethod();
		String uri = req.getRequestURI();
		String contextPath = req.getContextPath();
		
		String command = uri.length() > (contextPath + "/myPage").length() ?
							uri.substring(  (contextPath + "/myPage/").length()  )  :
								uri.substring(  (contextPath + "/myPage").length()  );
				
		String path = null;
		RequestDispatcher dispatcher = null;
		
		HttpSession session = req.getSession();

			
			
			if(command.equals("")) {
				if(method.equals("GET")) {
					
					path = "/WEB-INF/views/member/myPage.jsp";
					dispatcher = req.getRequestDispatcher(path);
					dispatcher.forward(req, resp);
				}
			}
			
			
			if(command.equals("orderHistory")) {
				if(method.equals("GET")) {
					path = "/WEB-INF/views/order/orderHistory.jsp";
					dispatcher = req.getRequestDispatcher(path);
					dispatcher.forward(req, resp);
				}
			}
			
			if(command.equals("orderStatus")) {
				if(method.equals("GET")) {
					path = "/WEB-INF/views/order/orderStatus.jsp";
					dispatcher = req.getRequestDispatcher(path);
					dispatcher.forward(req, resp);
				}
			}
			
			if(command.equals("addr")) {
				if(method.equals("GET")) {
					path = "/WEB-INF/views/member/addrModify.jsp";
					dispatcher = req.getRequestDispatcher(path);
					dispatcher.forward(req, resp);
				}
				
			}
			
			
			if(command.equals("addr/edit")){
				if(method.equals("GET")) {
					path = "/WEB-INF/views/member/addrModifyEdit.jsp";
					dispatcher = req.getRequestDispatcher(path);
					dispatcher.forward(req, resp);
				}
			}
			
			if(command.equals("addr/Register")){
				if(method.equals("GET")) {
					path = "/WEB-INF/views/member/addrRegister.jsp";
					dispatcher = req.getRequestDispatcher(path);
					dispatcher.forward(req, resp);
				}
			}
			
			if(command.equals("updateMember")){
				if(method.equals("GET")) {
					path = "/WEB-INF/views/member/updateMember.jsp";
					dispatcher = req.getRequestDispatcher(path);
					dispatcher.forward(req, resp);
				}
			}

			
			if(command.equals("myPageBoard")) {
				if(method.equals("GET")) {
					path = "/WEB-INF/views/member/myPageBoard.jsp";
					dispatcher = req.getRequestDispatcher(path);
					dispatcher.forward(req, resp);
				}
			}
			
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
