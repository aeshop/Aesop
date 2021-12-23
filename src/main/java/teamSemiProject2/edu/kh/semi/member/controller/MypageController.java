package teamSemiProject2.edu.kh.semi.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import teamSemiProject2.edu.kh.semi.member.model.service.MemberService;
import teamSemiProject2.edu.kh.semi.member.model.service.MypageService;
import teamSemiProject2.edu.kh.semi.member.model.vo.Member;
import teamSemiProject2.edu.kh.semi.member.model.vo.OrderList;

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
		
		
			
			
			// 마이페이지
			if(command.equals("")) {
				if(method.equals("GET")) {
					
					// 최근 주문내역 조회
					Member loginMember = (Member)session.getAttribute("loginMember");
					
					int memberNo = loginMember.getMemberNo();
					
					
					try {
						MypageService service = new MypageService();

						List<OrderList> orderList = service.selectOrderList(memberNo);
						
						session.setAttribute("orderList", orderList);
						
						
						
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					
					
					path = "/WEB-INF/views/member/myPage.jsp";
					dispatcher = req.getRequestDispatcher(path);
					dispatcher.forward(req, resp);
				}
			
		
			// 주문/배송 내역 조회
			}else if(command.equals("orderHistory")) {
				if(method.equals("GET")) {
					
					
					Member loginMember = (Member)session.getAttribute("loginMember");
					
					int memberNo = loginMember.getMemberNo();
					
					
					try {
						MypageService service = new MypageService();

						List<OrderList> orderList = service.selectOrderList(memberNo);
						
						session.setAttribute("orderList", orderList);
						
						
						
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					
					path = "/WEB-INF/views/order/orderHistory.jsp";
					dispatcher = req.getRequestDispatcher(path);
					dispatcher.forward(req, resp);
				}
			
				
				
			// 취소/교환/반품 (주문상태) 조회
			}else if(command.equals("orderStatus")) {
				if(method.equals("GET")) {
					
					Member loginMember = (Member)session.getAttribute("loginMember");
					
					int memberNo = loginMember.getMemberNo();
					
					try {
						
						MypageService service = new MypageService();

						List<OrderList> orderStatusList = service.selectOrderStatus(memberNo);
						
						session.setAttribute("orderStatusList", orderStatusList);
						
					}catch(Exception e) {
						e.printStackTrace();
					}
					
					
					path = "/WEB-INF/views/order/orderStatus.jsp";
					dispatcher = req.getRequestDispatcher(path);
					dispatcher.forward(req, resp);
				}
				
			// 등록된 배송지 목록 조회
			}else if(command.equals("addr")) {
				if(method.equals("GET")) {
					path = "/WEB-INF/views/member/addrModify.jsp";
					dispatcher = req.getRequestDispatcher(path);
					dispatcher.forward(req, resp);
				}
				
			// 배송지목록조회 -> 배송지 수정
			}else if(command.equals("addr/edit")){
				if(method.equals("GET")) {
					path = "/WEB-INF/views/member/addrModifyEdit.jsp";
					dispatcher = req.getRequestDispatcher(path);
					dispatcher.forward(req, resp);
				}
			
			// 배송지목록조회 -> 배송지 등록
			}else if(command.equals("addr/Register")){
				if(method.equals("GET")) {
					path = "/WEB-INF/views/member/addrRegister.jsp";
					dispatcher = req.getRequestDispatcher(path);
					dispatcher.forward(req, resp);
				}
			
			// 회원정보 수정
			}else  if(command.equals("updateMember")){
				if(method.equals("GET")) {
					path = "/WEB-INF/views/member/updateMember.jsp";
					dispatcher = req.getRequestDispatcher(path);
					dispatcher.forward(req, resp);
				}
			
			// 게시글 관리/ 문의내역 관리
			}else if(command.equals("myPageBoard")) {
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
