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

@WebServlet("/member/*")
public class MemberController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String method = req.getMethod();
		String uri = req.getRequestURI();
		String contextPath = req.getContextPath();
		
		String command = uri.substring(  (contextPath + "/member/").length()  );
				
		String path = null;
		RequestDispatcher dispatcher = null;
		
			if(command.equals("login")) {
				if(method.equals("GET")) {
					path = "/WEB-INF/views/member/login.jsp";
					dispatcher = req.getRequestDispatcher(path);
					dispatcher.forward(req, resp);
				}else {
					// POST
					req.setCharacterEncoding("UTF-8");
					
					// 전달받은 파라미터를 변수에 저장
					String memberId = req.getParameter("memberId");
					String memberPw = req.getParameter("memberPw");
					


					System.out.println("memberId : " + memberId);
					System.out.println("memberPw : " + memberPw);

					
					try {
						MemberService service = new MemberService();
						
						Member loginMember = service.login(memberId, memberPw);
						
						HttpSession session = req.getSession();
						
						if (loginMember != null) {



							if (loginMember.getStatusCode() == 101) {


							System.out.println("여기까지");

							if (loginMember.getStatusCode() == 101) {
								System.out.println(loginMember.getMemberEmail());

								session.setAttribute("loginMember", loginMember);
								session.setMaxInactiveInterval(3000);
								
							}
							
						}else { // 로그인 실패
							session.setAttribute("message", "아이디 또는 비밀번호를 확인해주세요.");
							
						}
						resp.sendRedirect(req.getContextPath() );
						
						}}catch (Exception e) {
						e.printStackTrace();
					}
						
				}
				
			}
			
			
			if(command.equals("myPage")) {
				if(method.equals("GET")) {
					
					
					HttpSession session = req.getSession();
					
					
					
					path = "/WEB-INF/views/member/myPage.jsp";
					dispatcher = req.getRequestDispatcher(path);
					dispatcher.forward(req, resp);
				}
			}
			
			
			if(command.equals("myPage/orderHistory")) {
				if(method.equals("GET")) {
					path = "/WEB-INF/views/order/orderHistory.jsp";
					dispatcher = req.getRequestDispatcher(path);
					dispatcher.forward(req, resp);
				}
			}
			
			if(command.equals("myPage/orderStatus")) {
				if(method.equals("GET")) {
					path = "/WEB-INF/views/order/orderStatus.jsp";
					dispatcher = req.getRequestDispatcher(path);
					dispatcher.forward(req, resp);
				}
			}
			
			if(command.equals("myPage/addr")) {
				if(method.equals("GET")) {
					path = "/WEB-INF/views/member/addrModify.jsp";
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
