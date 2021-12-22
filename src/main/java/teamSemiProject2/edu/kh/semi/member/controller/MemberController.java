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
public class MemberController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String method = req.getMethod();
		String uri = req.getRequestURI();
		String contextPath = req.getContextPath();

		String command = uri.substring((contextPath + "/member/").length());

		String path = null;
		RequestDispatcher dispatcher = null;
		HttpSession session = req.getSession();
		req.setCharacterEncoding("UTF-8");

		// 마이페이지
		if (command.equals("myPage")) {
			if (method.equals("GET")) {
				path = "/WEB-INF/views/member/myPage.jsp";
				dispatcher = req.getRequestDispatcher(path);
				dispatcher.forward(req, resp);
			}
		}
		
		// 마이페이지 끝 ****************************************************

		// 로그인 페이지
		if (command.equals("login")) {
			if (method.equals("GET")) {
				path = "/WEB-INF/views/member/login.jsp";
				dispatcher = req.getRequestDispatcher(path);
				dispatcher.forward(req, resp);
			} else {
				
				// POST
				String memberId = req.getParameter("memberId");
				String memberPw = req.getParameter("memberPw");

				try {
					MemberService service = new MemberService();

					Member loginMember = service.login(memberId, memberPw);

//					System.out.println(loginMember);
					if (loginMember != null) {

						if (loginMember.getStatusCode() == 101) {
								session.setAttribute("loginMember", loginMember);
								session.setMaxInactiveInterval(3000);
								
								

								resp.sendRedirect(req.getContextPath());
						} else { // 로그인 실패
							session.setAttribute("message", "아이디 또는 비밀번호를 확인해주세요.");

						}

					}
				} catch (Exception e) {
					e.printStackTrace();
				}

			}

		}
		// 로그인 끝 *********************************
		// 조인(회원가입 폼 전 페이지) **************************************
		else if(command.equals("join")) {
			if(method.equals("GET")) {
				path = "/WEB-INF/views/member/join.jsp";
				dispatcher = req.getRequestDispatcher(path);
				dispatcher.forward(req, resp);
				
			}else {
				// post
			}
		}
		
		// 회원가입 *************
		else if(command.equals("signup")) {
			if(method.equals("GET")) {
				path = "/WEB-INF/views/member/signup.jsp";
				dispatcher = req.getRequestDispatcher(path);
				dispatcher.forward(req, resp);
			
			}else {
				// post
				
				
			}
		}
		
		
		
		
		
		
		
		
		

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
