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


import java.util.Date;
import java.util.Properties;
import java.util.Random;

import javax.activation.CommandMap;
import javax.activation.MailcapCommandMap;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.mail.internet.MimeMultipart;

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

				req.getRequestDispatcher(path).forward(req, resp);
			} else {
				HttpSession session = req.getSession();
				// POST
				String memberId = req.getParameter("memberId");
				String memberPw = req.getParameter("memberPw");


				memberId = replaceParameter(memberId);
				try {
					MemberService service = new MemberService();

					Member loginMember = service.login(memberId, memberPw);


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
				HttpSession session = req.getSession();
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
				HttpSession session = req.getSession();
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
			if (method.equals("POST")) {
				// post
				String inputId = req.getParameter("id");

				try {
					MemberService service = new MemberService();

					int result = service.idDupCheck(inputId);

					PrintWriter out = resp.getWriter();

					out.print(result);
				}

				catch (Exception e) {
					e.printStackTrace();
				}

			}

		}
		// ################이메일 유효성 검사
		else if (command.equals("emailDupCheck")) {
			if (method.equals("POST")) {
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
		
		// ########### 이메일 인증 검사
		else if(command.equals("emailConfirm")) {
			if(method.equals("GET")) {
				String sendMail = req.getParameter("inputEmailConfirm");
		         System.out.println("보낼 이메일 주소입니다 :" +sendMail);
		         
		     //    String host = "smtp.gmail.com";
		         String user = "fbrhksgus2@gmail.com"; // 자신의  계정
		         String password = "rhksgus0^^";// 자신의 패스워드

		         // 메일 받을 주소
		         /* String to_email = m.getEmail(); */
		     //    String to_email = sendMail;

		         // SMTP 서버 정보를 설정한다.
		         Properties props = new Properties();
		              
		          props.put("mail.transport.protocol", "smtp");
		          props.put("mail.smtp.host", "smtp.gmail.com");
		          props.put("mail.smtp.port", "587");
		          props.put("mail.smtp.auth", "true");
		       
		          props.put("mail.smtp.quitwait", "false");
		          props.put("mail.smtp.socketFactory.port", "587");
		          props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		          props.put("mail.smtp.socketFactory.fallback", "true");
		          props.put("mail.smtp.starttls.enable","true");


		         // 인증 번호 생성기
		         StringBuffer temp = new StringBuffer();
		         Random rnd = new Random();
		         for (int i = 0; i < 10; i++) {
		            int rIndex = rnd.nextInt(3);
		            switch (rIndex) {
		            case 0:
		               // a-z
		               temp.append((char) ((int) (rnd.nextInt(26)) + 97));
		               break;
		            case 1:
		               // A-Z
		               temp.append((char) ((int) (rnd.nextInt(26)) + 65));
		               break;
		            case 2:
		               // 0-9
		               temp.append((rnd.nextInt(10)));
		               break;
		            }
		         }
		         String AuthenticationKey = temp.toString();
		         System.out.println(AuthenticationKey);

		         Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
		            protected PasswordAuthentication getPasswordAuthentication() {
		               return new PasswordAuthentication(user, password);
		            }
		         });

		         // email 전송
		         try {
		            MimeMessage msg = new MimeMessage(session);
		            msg.setFrom(new InternetAddress(user, "상호명"));
		            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(sendMail));

		            // 메일 제목
		            msg.setSubject("안녕하십니까?  AESOP입니다.");
		            // 메일 내용
		            msg.setText("인증 번호는 : " + temp + "입니다.");

		            Transport.send(msg);
		          

		            resp.getWriter().print(temp);
		            
		         } catch (Exception e) {
		            e.printStackTrace();// TODO: handle exception
		         }
		         HttpSession saveKey = req.getSession();
		         saveKey.setAttribute("AuthenticationKey", AuthenticationKey);
		         
		         
		         
			}
			
		}
		
		if(command.equals("findId")) {
			
			if (method.equals("GET")) {
				path = "/WEB-INF/views/member/findId.jsp";
				req.getRequestDispatcher(path).forward(req, resp);

			}else if(method.equals("POST")) {
				
				String memberEmail = req.getParameter("email");
				String memberName = req.getParameter("name");
				
				memberEmail = replaceParameter(memberEmail);

				try {
					MemberService service = new MemberService();
					Member member = new Member(memberEmail, memberName);

					String memberId = service.findId(member);

					if (memberId != null) {
						req.setAttribute("id", memberId);
						
						HttpSession session = req.getSession();
						session.setAttribute("memberId", memberId);
						
					resp.sendRedirect(req.getContextPath());
					}else {
						System.out.println("실패");
					}

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
