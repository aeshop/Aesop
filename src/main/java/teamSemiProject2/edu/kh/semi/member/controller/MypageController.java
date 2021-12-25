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
import teamSemiProject2.edu.kh.semi.member.model.vo.AddrList;
import teamSemiProject2.edu.kh.semi.member.model.vo.Grade;
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
		
		
		Member loginMember = (Member)session.getAttribute("loginMember");
		
		int memberNo = loginMember.getMemberNo();
			
		MypageService service = new MypageService();
			
			// 마이페이지
			if(command.equals("")) {
				if(method.equals("GET")) {
					
					// 최근 주문내역 조회
					try {

						List<OrderList> orderList = service.selectOrderList(memberNo);
						
						session.setAttribute("orderList", orderList);
						
						
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					// 로그인한 회원의 누적 금액에 따라 업그레이드되는 등급 조회
					try {
						
						Grade grade = service.selectGrade(memberNo);
						
						session.setAttribute("grade", grade);
					
						
					}catch(Exception e) {
						e.printStackTrace();
					}
					
					
					path = "/WEB-INF/views/member/myPage.jsp";
					dispatcher = req.getRequestDispatcher(path);
					dispatcher.forward(req, resp);
				}
			
		
			// 주문/배송 내역 조회
			}else if(command.equals("orderHistory")) {
				if(method.equals("GET")) {
					
					
					
					try {

						List<OrderList> orderList = service.selectOrderList(memberNo);
						
						session.setAttribute("orderList", orderList);
						
						List<OrderList> orderStatusList = service.selectOrderStatus(memberNo);
						
						session.setAttribute("orderStatusList", orderStatusList);
						
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
					
					
					try {
						
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
					
					try {
						
						List<AddrList> addrList = service.selectAddrList(memberNo);
						session.setAttribute("addrList", addrList);
						
						// 기본 배송지 조회
						AddrList defaultAddr = service.selectDefaultAddr(memberNo);
						session.setAttribute("defaultAddr", defaultAddr);
						
						
					}catch(Exception e) {
						e.printStackTrace();
					}
					
					path = "/WEB-INF/views/member/addrModify.jsp";
					dispatcher = req.getRequestDispatcher(path);
					dispatcher.forward(req, resp);
				}
				  
				
				
			// 배송지목록조회 -> 배송지 수정
			}else if(command.equals("addr/edit")){
				if(method.equals("GET")) {
					
					System.out.println(4);
					
					path = "/WEB-INF/views/member/addrModifyEdit.jsp";
					dispatcher = req.getRequestDispatcher(path);
					dispatcher.forward(req, resp);
				
				}else {//post
					
					System.out.println(3);
					
					// 파라미터 얻어오기
					String addrName = req.getParameter("addrName");
					String addrReceiverName = req.getParameter("addrReceiverName");
					String zipCode = req.getParameter("zipCode");
					String address1 = req.getParameter("address1");
					String address2 = req.getParameter("address2");
					
					String[] aPhone = req.getParameterValues("aPhone");
					String addrPhone = String.join("-", aPhone);
					
					String defaultCheck = req.getParameter("defaultCheck");
					
					
					//AddrList 객체를 생성하여 파라미터를 하나의 객체에 저장
					AddrList updateAddr = new AddrList();
					
					updateAddr.setAddrName(addrName);
					updateAddr.setAddrReceiverName(addrReceiverName);
					updateAddr.setZipCode(zipCode);
					updateAddr.setAddress1(address1);
					updateAddr.setAddress2(address2);
					updateAddr.setAddrPhone(addrPhone);
					updateAddr.setDefaultAddress(defaultCheck);
					
					
					// 어떤 주소의 번호를 수정할지 구분하기 위한
					// "주소번호"를 session에 있는 addrList에서 얻어오기 (addrNo)
					AddrList addrList = (AddrList)session.getAttribute("addrList");
					
					updateAddr.setAddrNo( addrList.getAddrNo() );
					
					try {
						int result = service.updateDeliveryAddr(updateAddr);
						
						
						if(result > 0) { //수정 성공시
							session.setAttribute("message", "해당 배송지가 수정되었습니다.");
							
							addrList.setAddrName(addrName);
							addrList.setAddrReceiverName(addrReceiverName);
							addrList.setZipCode(zipCode);
							addrList.setAddress1(address1);
							addrList.setAddress2(address2);
							addrList.setAddrPhone(addrPhone);
							addrList.setDefaultAddress(defaultCheck);
						
						}else { // 수정 실패 시
							session.setAttribute("message", "해당 배송지 수정 실패");
						}
						
						// 배송지 목록 페이지로 재요청
						resp.sendRedirect("addr");
						
					}catch(Exception e) {
						e.printStackTrace();
					}
					
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
					
					
					// 로그인한 회원의 누적 금액에 따라 업그레이드되는 등급의 할인율 조회에 필요한 데이터..
					try {
						
						// 등급객체 세션에 저장하기 
						Grade grade = service.selectGrade(memberNo);
						session.setAttribute("grade", grade);
						
						// 배송지목록 세션에 저장하기
						List<AddrList> addrList = service.selectAddrList(memberNo);
						session.setAttribute("addrList", addrList);
					
						
						// 기본 배송지 조회
						AddrList defaultAddr = service.selectDefaultAddr(memberNo);
						session.setAttribute("defaultAddr", defaultAddr);
						
						
						
					}catch(Exception e) {
						e.printStackTrace();
					}
					
					
					
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
