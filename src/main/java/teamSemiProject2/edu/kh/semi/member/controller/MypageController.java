package teamSemiProject2.edu.kh.semi.member.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import teamSemiProject2.edu.kh.semi.delivery.model.vo.Delivery;
import teamSemiProject2.edu.kh.semi.member.model.service.MemberService;
import teamSemiProject2.edu.kh.semi.member.model.service.MypageService;
import teamSemiProject2.edu.kh.semi.member.model.vo.AddrList;
import teamSemiProject2.edu.kh.semi.member.model.vo.Address;
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
		
		String message;
		
		
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
						
						// 카운트넘버(최근 2일이내 주문내역 조회)
						
						int result = service.selectCountNum(memberNo);
						
						session.setAttribute("result", result);
						
						
						
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
					
//					System.out.println(4);
					
					path = "/WEB-INF/views/member/addrModifyEdit.jsp";
					dispatcher = req.getRequestDispatcher(path);
					dispatcher.forward(req, resp);
				
				}else {//post
					
//					System.out.println(3);
					
					// 파라미터 얻어오기
					int idx = Integer.parseInt( req.getParameter("idx") );
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
					List<AddrList> addrList = (List<AddrList>)session.getAttribute("addrList");

					updateAddr.setAddrNo( addrList.get(idx).getAddrNo() );
					
					try {
						int result = service.updateDeliveryAddr(updateAddr);
						
						
						if(result > 0) { //수정 성공시
							session.setAttribute("message", "해당 배송지가 수정되었습니다.");
							
							addrList.get(idx).setAddrName(addrName);
							addrList.get(idx).setAddrReceiverName(addrReceiverName);
							addrList.get(idx).setZipCode(zipCode);
							addrList.get(idx).setAddress1(address1);
							addrList.get(idx).setAddress2(address2);
							addrList.get(idx).setAddrPhone(addrPhone);
							addrList.get(idx).setDefaultAddress(defaultCheck);
						
						}else { // 수정 실패 시
							session.setAttribute("message", "해당 배송지 수정 실패");
						}
						
						// 배송지 목록 페이지로 재요청
						resp.sendRedirect("../addr");
						
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
				
				}else {//post
					
					
					// 파라미터 얻어오기
					String addrName = req.getParameter("addrName");
					String addrReceiverName = req.getParameter("addrReceiverName");
					String zipCode = req.getParameter("zipCode");
					String address1 = req.getParameter("address1");
					String address2 = req.getParameter("address2");
					
					String[] aPhone = req.getParameterValues("aPhone");
					String addrPhone = String.join("-", aPhone);
					
					String defaultCheck = req.getParameter("defaultCheck");
					
					// 객체에 저장
					AddrList registerAddr = new AddrList();
					
					registerAddr.setAddrName(addrName);
					registerAddr.setAddrReceiverName(addrReceiverName);
					registerAddr.setZipCode(zipCode);
					registerAddr.setAddress1(address1);
					registerAddr.setAddress2(address2);
					registerAddr.setAddrPhone(addrPhone);
					registerAddr.setDefaultAddress(defaultCheck);
					registerAddr.setMemberNo(memberNo);
					
					try {
						int result = service.registerDeliveryAddr(registerAddr);
						
						
						if(result > 0) { //등록 성공시
							session.setAttribute("message", "해당 배송지가 등록되었습니다.");
							
						
						}else { // 등록 실패 시
							session.setAttribute("message", "해당 배송지 등록 실패");
						}
						
						// 배송지 목록 페이지로 재요청
						resp.sendRedirect("../addr");
						
					}catch(Exception e) {
						e.printStackTrace();
					}
					
				}
				
				
				
				
				// 체크된 주소록 삭제
			} else if (command.equals("addr/delete")) {
				if (method.equals("POST")) {

					try {
						String[] addrNo = req.getParameterValues("addrNoList[]");

						int loginMemberNo = loginMember.getMemberNo();

						int result = service.delCheckedAddr(addrNo, loginMemberNo);

						resp.getWriter().print(result);
					} catch (Exception e) {
						e.printStackTrace();
					}

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
				
				else {// POST 방식

					// 회원정보 수정 후에 세션을 다시 받아와야 한다? 이게 가능한가?
//					System.out.println("데이터가 post로 전달");
//						System.out.println(req.getParameter("userName"));

					// post전달 완료 req에 제대로 담겨오는지 확인, 완료
					try {

						Member tmp = new Member();
						tmp.setMemberNo(loginMember.getMemberNo());
						tmp.setMemberPw(req.getParameter("pwd1")); // 암호화 제대로 거쳤는지 확인 필요: wrapper가 작동 안되고 있음

						tmp.setMemberName(req.getParameter("userName"));
						String[] phoneNums = req.getParameterValues("phone");
						String phone = String.join("-", phoneNums);
						tmp.setMemberPhone(phone);
						tmp.setMemberBirthday(req.getParameter("birth_year"));
						String[] eMails = req.getParameterValues("eMail");
						String email = String.join("@", eMails);
						tmp.setMemberEmail(email);

						// 주소는 기본 주소록을 찾고, update 시켜주는 형식이어야 한다. 회원번호가 일치하고 Y로 된 주소록을 찾으면 된다는 의미이다.
						String zipCode = req.getParameter("zipCode");
						String addr1 = req.getParameter("address1");
						Address addr = null;
						if (zipCode != null && addr1 != null) {
							addr = new Address();
							addr.setMemberNo(loginMember.getMemberNo());
							addr.setZipCode(zipCode);
							addr.setAddress1(addr1);
							addr.setAddress2(req.getParameter("address2"));
						}
						if (addr != null) {
							tmp.setDefaultAddress(addr);
						}

						int result = service.updateMemberInfo(tmp);
						// 회원정보 수정이 성공하면 회원정보 다시한번 받아오기
						if (result > 0) {
							message = "회원정보 수정이 완료되었습니다.";
							loginMember = new MemberService().login(loginMember.getMemberId(), req.getParameter("pwd1"));
							session.setAttribute("loginMember", loginMember);
						} else {
							message = "회원정보 수정이 실패하였습니다.";
						}

						session.setAttribute("message", message);
						path = "/WEB-INF/views/member/myPage.jsp";
						dispatcher = req.getRequestDispatcher(path);
						dispatcher.forward(req, resp);
					} catch (Exception e) {

						e.printStackTrace();
					}

				}
			
				
			// 게시글 관리/ 문의내역 관리
			}else if(command.equals("myPageBoard")) {
				if(method.equals("GET")) {
					path = "/WEB-INF/views/member/myPageBoard.jsp";
					dispatcher = req.getRequestDispatcher(path);
					dispatcher.forward(req, resp);
				}
			
				
				// 회원탈퇴
			} else if (command.equals("secession")) {
				if (method.equals("GET")) {
					try {
						int result = service.secession(loginMember.getMemberNo());
		
						if (result > 0) {
							session.invalidate();
							path = req.getContextPath();
							resp.sendRedirect(path);
						}
		
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			
				
			// 주문 상세내역 조회
			} else if (command.equals("orderDetail")) {
				if(method.equals("GET")) {
					try {
						
						String deliveryNo = req.getParameter("deNo");
						
					HashMap<String, Object> resultMap =	service.getOrderDetail(loginMember.getMemberNo(),deliveryNo);
						
					List<OrderList> oList = (List<OrderList>)(resultMap.get("orderList"));
					Delivery del = (Delivery) resultMap.get("delivery");
					
					Double sum = (Double )resultMap.get("sum");
					
					int ship = (Integer)resultMap.get("ship");
					
					req.setAttribute("orderList",  oList);
					req.setAttribute("delivery", del);
					req.setAttribute("orderCount", oList.size());
					req.setAttribute("sum", sum);
					req.setAttribute("ship", ship);
					
					
					path = "/WEB-INF/views/member/orderDetail.jsp";
					dispatcher = req.getRequestDispatcher(path);
					dispatcher.forward(req, resp);
					
					}catch(Exception e) {
						e.printStackTrace();
					}
					
				}
				
			}
			
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
