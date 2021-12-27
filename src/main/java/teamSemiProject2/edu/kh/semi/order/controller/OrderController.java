package teamSemiProject2.edu.kh.semi.order.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.json.simple.JSONObject;

import com.google.gson.Gson;
import com.google.gson.JsonParser;

import teamSemiProject2.edu.kh.semi.common.ImportAccessToken;
import teamSemiProject2.edu.kh.semi.common.ImportRecord;
import teamSemiProject2.edu.kh.semi.delivery.model.vo.Delivery;
import teamSemiProject2.edu.kh.semi.member.model.vo.Address;
import teamSemiProject2.edu.kh.semi.member.model.vo.Member;
import teamSemiProject2.edu.kh.semi.order.model.service.OrderService;
import teamSemiProject2.edu.kh.semi.order.model.vo.Order;

@WebServlet("/order/*")
public class OrderController extends HttpServlet {

	@Override // 전부 get방식에서 처리함
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 메소드를 알아야되고, 요청 보내야되고, 경로 알아야되고, 에러 뜨면 메세지 다뤄야됨.

		String method = req.getMethod();
		String uri = req.getRequestURI();
		String contextPath = req.getContextPath();
		// System.out.println(contextPath);

		String command = uri.substring((contextPath + "/order/").length());

		// System.out.println(command);
		RequestDispatcher dispatcher = null;
		String path = null;
		String message = null;
		HttpSession session = req.getSession();

		OrderService service = new OrderService();

		Member loginMember = (Member) session.getAttribute("loginMember");

		int loginMemberNo = loginMember.getMemberNo();

		try {

			if (method.equals("GET")) {// get방식 처리
				if (command.equals("view")) {// 장바구니 페이지로 이동

					/*
					 * 원래는 로그인한 상태에서 들어가게 되서 로그인이 되어있지 않으면, 로그인이 필요한 서비스입니다. 메세지가 confirm으로 나오고 로그인
					 * 할것인지 아닌지 여부를 알아내야 한다. - 이거를 어떻게 구현하지? 로그인 체크 자체는 세션으로 가능함, 그러나 바로 url 로 접속하려고
					 * 하면? - 비회원 주문기능이 있어서 그냥 들어가지나, 그렇게 안만들고 싶으면 어떻게 할지 질문해야 할 것으로 보인다.
					 * 
					 * 
					 * 
					 * 지금은 테스트용 회원을 만들고 테스트용 회원으로 장바구니에 담고 하는 기능을 만들기 a href : get으로 접근하더라도 DB에 접근해서
					 * 내 회원번호와 맞는 주문 테이블 row를 가지고 와야 한다.
					 */

					//로그인 번호로 장바구니 테이블 조회 후 진행한다
					List<Order> oList = service.getOrder(loginMemberNo);

				
					
					
					req.setAttribute("orderList", oList);
					// 사실 회원정보는 세션에 다 있는데 받아올 필요가 있었을까? - order query를 다시 짜야 한다.(가격, 할인율 가져와야함) +
					// 지금은 Session안에 정보를 강제로 집어넣기
					req.setAttribute("orderCount", oList.size());

					path = "/WEB-INF/views/order/myCart.jsp";
					dispatcher = req.getRequestDispatcher(path);
					dispatcher.forward(req, resp);

				} else if (command.equals("delete")) {

					int orderNo = Integer.parseInt(req.getParameter("orderNo"));

					int result = service.deleteOrder(orderNo, loginMemberNo);

					if (result == 1) {
						message = "정상적으로 삭제되었습니다.";

					} else {
						message = "삭제 과정에서 오류 발생";
					}

					session.setAttribute("message", message);
					path = "view";// redirect는 주소 경로 사용하므로, WEB-INF 등으로 직접접근 할 수 없다
					resp.sendRedirect(path);

				} else if (command.equals("deleteAll")) {

					String[] orderNoArr = req.getParameterValues("orderNo");

					//[4, 3] : 정상적으로 보내지는 것을 확인했다 이제 지우면 된다
					/*
					 * String 배열을 int배열로 변환: parseInt말고 다른 수식이 필요함, 그냥 service에서 DAO 여러번 보내면서 변경하는걸로...
					 * 
					 * */

					int result = service.deleteAll(orderNoArr,loginMemberNo);
					
					if (result == 1) {
						message = "정상적으로 체크된 물품이 삭제되었습니다.";

					} else {
						message = "삭제 과정에서 오류 발생";
					}
					session.setAttribute("message", message);
					path = "view";// redirect는 주소 경로 사용하므로, WEB-INF 등으로 직접접근 할 수 없다
					resp.sendRedirect(path);
					
					
				}else if (command.equals("orderAll")) {
					//결제 페이지로 데이터 전달: 선택된 상품(주문), 사용자정보, 주소록의 기본 주소, 배송번호(20121101-1234) 
					
					
					String[] orderNoArr = req.getParameterValues("orderNo");
				
 
					
					Map<String,Object> resultMap = service.orderAll(orderNoArr,loginMemberNo);
					//oList에는 없는 체크되지 않은 주문를 제외한 주문row들이 옴
					
					//회원번호 + 선택된 상품번호들을 전달해서 선택된 상품목록, 주소록의 기본주소, 배송번호 를 맵의 형태로 받아옴
				
					
					ArrayList oList = (ArrayList) resultMap.get("orderList");
					Address defaultAddress = (Address) resultMap.get("defaultAddress");
					//배송번호는 ajax로 요청하고 받아올 것이다.
					
					//어트리뷰트에 넣고 진행함
					req.setAttribute("orderList", oList);
					req.setAttribute("orderCount", oList.size());

					req.setAttribute("defaultAddress", defaultAddress);
					
					//결제 페이지에 정보를 보냄
					path="/WEB-INF/views/order/payment.jsp";
					dispatcher = req.getRequestDispatcher(path);
					dispatcher.forward(req, resp);
				} else if (command.equals("addrPop")) {
					
					List<Address> addrList = service.getAddress(loginMemberNo);
					
					req.setAttribute("addrList", addrList);
					
					path="/WEB-INF/views/order/addrPopUp.jsp";
					
					dispatcher = req.getRequestDispatcher(path);
					dispatcher.forward(req, resp);
					
					
				}

			} else {// post방식 처리

				if (command.equals("amountChange")) {
					// ajax는 req dispatcher forward를 사용하지 않는다.
					int orderNo = Integer.parseInt(req.getParameter("orderNo"));
					int orderAmount = Integer.parseInt(req.getParameter("orderAmount"));
					
					int result = service.amountChange(orderAmount, orderNo, loginMemberNo);
					resp.getWriter().print(result);

				} else if(command.equals("beforeImport")) {
					
					try {
						//payment페이지에 있는 주문번호들 배열로 받아옴
						String [] orderNoArr = req.getParameterValues("orderNoList[]");
				
						
						Map<String, String> resultMap = service.beforeImport(orderNoArr,loginMemberNo);
						//AJAX로 맵 반환함

						resp.getWriter().print(new Gson().toJson(resultMap));
						
						
					} catch (Exception e) {//수량 부족으로 체크 제약조건 위배할 경우

						e.printStackTrace();
						
						if(e instanceof SQLException) {
							SQLException se = (SQLException) e;
							if(se.getErrorCode()==2290) {//캐치 조건에 어긋날시에
								
							System.out.println("수량이 부족합니다.");
							Map<String, String> resultMap = new HashMap<String, String>();
							resultMap.put("stCode", "199");
							resp.getWriter().print(new Gson().toJson(resultMap));

							}
						}
					
					
					}
			


				}
				
				else if(command.equals("validation")) {
					/* 아임포트 실행직전 내 서버에 저장된 값과 아임포트 서버에 넘어간 값 비교검증
					* 1. 내 서버에 저장된 가격은 delivery table에서 merchant_uid로 받아옴
					* 2. 아임포트 서버에 저장된 가격은 엑세스 토큰을 받고 결제번호 imp_uid로 조회해서 파싱
					*
					 * */
					//1. 결제번호 주문번호를 아임포트 실행 성공 후 ajax에서 추출하기
										
					String impUid = req.getParameter("imp_uid");
					//결제 번호
					String merchantUid = req.getParameter("merchant_uid");
					//배송 번호
					
					
					//1. 내 서버에 저장된 가격 가져오기
					long myServerAmount = service.getDelivery(merchantUid, loginMemberNo).getDeliveryPrice();
					
					//2. 아임포트에 저장된 가격 가져오기
					//2-1)액세스 토큰 받아옴
					
					String accessToken = new ImportAccessToken().getAccessToken();
					//2-2) 저장된 가격 가져옴					
					long importServerAmount =new ImportRecord().getImportAmount(impUid, accessToken);
//					System.out.println("아임포트에서 받아온 결제값:"+importServerAmount);
					
					//2-3) 저장된 가격이 서로 같으면 검증 완료 의미: 배송 레코드에 ajax로 전달받은 상세정보(주소 등등) 집어넣고
					//order 테이블 해당 row들 결제완료로 상태코드수정, 배송번호 널에서 업데이트
					if(myServerAmount==importServerAmount) {
						
						Delivery del = new Delivery();
						
						
						del.setMemberNo(loginMemberNo);
						del.setDeliveryNo(merchantUid);
						del.setZipCode(req.getParameter("dZipCode"));
						del.setAddress1(req.getParameter("dAddress1"));
						del.setAddress2(req.getParameter("dAddress2"));
						del.setReceiverName(req.getParameter("dReceiverName"));
						del.setReceiverPhone(req.getParameter("dReceiverPhone"));
						//상태코드를 결제완료코드 = 배송준비중 로 바꾸기
						del.setDeliveryStatusCode(502);
						del.setDeliveryMessage(req.getParameter("dMessage"));		
						
						System.out.println(del);
						
						//해당 배송번호로 업데이트할 주문들
						String [] orderNoArr = req.getParameterValues("orderNoList[]");

						
						int [] orderNoIntArr = new int [orderNoArr.length];
						
						for(int i =0; i<orderNoArr.length;i++){
							orderNoIntArr[i]= Integer.parseInt(orderNoArr[i]);
														
						}
						
//						int result=service.completeDelOrder(del,merchantUid,orderNoIntArr);
						int result=service.completeDelOrder(del,orderNoIntArr);
						
					}else {
						//검증 불일치 시 자동환불처리 등을 진행할 수 있다는데 거기까지는 못했고
						//그냥 수량조절, 거래파기 까지만 
						System.out.println("불일치");

						String [] orderNoArr = req.getParameterValues("orderNoList[]");
						String deliveryNo = req.getParameter("merchant_uid");
						
						
						
						int result = service.payCancel(orderNoArr,deliveryNo);
						
						
						
					
					
					
					}
					
					
				}
				
				//아임포트 결제 취소시 서버 DB 조작
				/*
				 * 
				 *  1. 선택한 order들에 의한 product 재고 삭감 복구
            		2. 선택한 order들의 상태코드 변경//안해도 됨
            		3. 해당 배송의 상태코드 변경
				 * 
				 * 
				 * 
				 * */
				
				else if(command.equals("payCancel")) {
					String [] orderNoArr = req.getParameterValues("orderNoList[]");
					String deliveryNo = req.getParameter("merchant_uid");
					
					
					
					int result = service.payCancel(orderNoArr,deliveryNo);
					
					String ajaxMsg;
					if(result>0) {
						ajaxMsg = "결제 취소";

						System.out.println(ajaxMsg);

					}else {
						ajaxMsg = "결제 취소 후 취소작업에서 문제 발생";

						System.out.println(ajaxMsg);

					}
					
				resp.getWriter().print(ajaxMsg);
					
					
				}

				

			}

		} catch (Exception e) {

			e.printStackTrace();
			
		
			

		}

	}

}
