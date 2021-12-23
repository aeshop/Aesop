package teamSemiProject2.edu.kh.semi.member.controller;

import java.io.IOException;
import java.io.PrintWriter;

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
				req.getRequestDispatcher(path).forward(req, resp);
			}
		}

		// 마이페이지 끝 ****************************************************

		// 로그인 페이지
		if (command.equals("login")) {
			if (method.equals("GET")) {
				path = "/WEB-INF/views/member/login.jsp";
				req.getRequestDispatcher(path).forward(req, resp);
			} else {

				// POST
				String memberId = req.getParameter("memberId");
				String memberPw = req.getParameter("memberPw");

				memberId = replaceParameter(memberId);
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

		// 로그아웃 *********************************
		else if (command.equals("logout")) {
			if (method.equals("GET")) {
				session.removeAttribute("loginMember");
				session.invalidate();

				resp.sendRedirect(req.getContextPath());
			}
		}

		// 조인(회원가입 폼 전 페이지) **************************************
		else if (command.equals("join")) {
			if (method.equals("GET")) {
				path = "/WEB-INF/views/member/join.jsp";
				req.getRequestDispatcher(path).forward(req, resp);

			} else {
				// post
			}
		}

		// 회원가입 *************
		else if (command.equals("signup")) {
			if (method.equals("GET")) {
				path = "/WEB-INF/views/member/signUp.jsp";
				req.getRequestDispatcher(path).forward(req, resp);

			} else {
				// post
				String memberId = req.getParameter("id");
				String memberPw = req.getParameter("pwd1");
				String memberEmail = req.getParameter("email");
				String memberName = req.getParameter("name");
				String memberBirthday = req.getParameter("birthday");

				String[] phone = req.getParameterValues("phone");
				String memberPhone = String.join("-", phone);

				memberId = replaceParameter(memberId);
				memberEmail = replaceParameter(memberEmail);

				Member member = new Member(memberId, memberPw, memberEmail, memberName, memberBirthday, memberPhone);

				try {
					MemberService service = new MemberService();

					int result = service.signUp(member);

					String message = null;
					if (result > 0) {
						message = "회원가입에 성공하셨습니다.";

						session.setAttribute("message", message);

						resp.sendRedirect(req.getContextPath());
					}

				} catch (Exception e) {
					e.printStackTrace();

				}

			}
		}
		// #################아이디 유효성 검사
		else if (command.equals("idDupCheck")) {
			if (method.equals("GET")) {

			} else {
				// post
				String inputId = req.getParameter("id");


				try {
					MemberService service = new MemberService();

					int result = service.idDupCheck(inputId);

					PrintWriter out = resp.getWriter();

					out.print(result);
				} catch (Exception e) {
					e.printStackTrace();
				}

			}

		} 
		// ################이메일 유효성 검사
		else if (command.equals("emailDupCheck")) {
			if (method.equals("GET")) {
				
			} else {
				// post
				String inputEmail = req.getParameter("email");

				try {
					MemberService service = new MemberService();

					int result = service.emailDupCheck(inputEmail);

					PrintWriter out = resp.getWriter();

					out.print(result);
				} catch (Exception e) {
					e.printStackTrace();

				}
			}
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

	private String replaceParameter(String param) {

		String result = param;

		if (result != null) {

			result = result.replaceAll("&", "&amp;");

			result = result.replaceAll("<", "&lt;");

			result = result.replaceAll(">", "&gt;");

			result = result.replaceAll("\"", "&quot");
		}

		return result;
	}

}
