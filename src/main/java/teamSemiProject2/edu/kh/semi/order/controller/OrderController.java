package teamSemiProject2.edu.kh.semi.order.controller;

import java.io.IOException;
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

					int result = service.deleteOrder(orderNo, (int) session.getAttribute("loginMemberNo"));

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
					String deliveryNo = (String) resultMap.get("deliveryNo");
					
					//어트리뷰트에 넣고 진행함
					req.setAttribute("orderList", oList);
					req.setAttribute("orderCount", oList.size());
					
					req.setAttribute("defaultAddress", defaultAddress);
					req.setAttribute("deliveryNo",deliveryNo);
					
					//결제 페이지에 정보를 보냄
					path="/WEB-INF/views/order/payment.jsp";
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

				}

			}

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

}
