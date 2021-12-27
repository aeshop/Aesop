package teamSemiProject2.edu.kh.semi.product.controller;

import java.io.IOException;
import java.security.Provider.Service;
import java.util.ArrayList;
import java.util.Enumeration;
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

import com.oreilly.servlet.MultipartRequest;

import teamSemiProject2.edu.kh.semi.category.model.service.CategoryService;
import teamSemiProject2.edu.kh.semi.category.model.vo.Category;
import teamSemiProject2.edu.kh.semi.common.MyRenamePolicy;
import teamSemiProject2.edu.kh.semi.member.model.vo.Address;
import teamSemiProject2.edu.kh.semi.member.model.vo.Member;
import teamSemiProject2.edu.kh.semi.order.model.vo.Order;
import teamSemiProject2.edu.kh.semi.product.model.service.ProductService;
import teamSemiProject2.edu.kh.semi.product.model.vo.Product;
import teamSemiProject2.edu.kh.semi.product.model.vo.ProductImage;

@WebServlet("/product/*")
public class ProductController extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String method = req.getMethod();
		String uri = req.getRequestURI();
		String contextPath = req.getContextPath();
		String command = uri.substring((contextPath + "/product/").length());

		String path = null;
		RequestDispatcher dispatcher = null;
		String message = null;
		
		ProductService service = new ProductService();
		HttpSession session = req.getSession();
		
		Member loginMember = (Member) session.getAttribute("loginMember"); 
		
		
		try {

			if (method.equals("GET")) {// a href로 제품 입력 페이지로 오게됨, DB에서 카테고리 정보 담아서 보여줌, 대분류 소분류 안나누고 보여주기
				
				if (command.equals("add")) {

					List<Category> category = service.getCategory();
					req.setAttribute("category", category);

					dispatcher = req.getRequestDispatcher("/WEB-INF/views/product/addProduct.jsp");

					dispatcher.forward(req, resp);

				} else if(command.equals("productDetail")) {//   /product/productDetail?productNo=숫자 로 던져서 얻어온 정보를 화면에 뿌려줘야한다
					int productNo = Integer.parseInt(req.getParameter("productNo"));
					
				Product product = 	service.getProductDetail(productNo);
					
					req.setAttribute("product",product);
					path="/WEB-INF/views/product/productDetail.jsp";
					
					dispatcher = req.getRequestDispatcher(path);
					dispatcher.forward(req, resp);
				
				} 	else if(command.equals("buyNow")) {
					//바로 구매 : 주문번호를 번호표처럼받아서, 그걸로 insert,
					//주문번호를 가지고와서 select, orderList반환받아서 req에 담고 
					//결제페이지로
					
					int productNo = Integer.parseInt(req.getParameter("productNo"));
					int amount = Integer.parseInt(req.getParameter("amount"));
					int	loginMemberNo = loginMember.getMemberNo();
					
					 int result = service.addCart(productNo,amount,loginMemberNo);

					 Map<String, Object> resultMap = null;

					 
					 if(result==1) {//삽입이 성공했으면, 즉시구매하고자 하는 물건 = 내가 추가한 가장 최근의 order 데이터
						 //배송으로 보내는 거라서 주문목록 + 기본주소록 목록이 필요하다 - 둘을 맵으로 묶어서 와야함

						 
						 resultMap = service.buyNow(loginMemberNo);
					 }
					
					 ArrayList oList = (ArrayList) resultMap.get("orderList");
						Address defaultAddress = (Address) resultMap.get("defaultAddress");
					
						
						
					 req.setAttribute("orderList", oList);
					req.setAttribute("orderCount", oList.size());
					req.setAttribute("defaultAddress",defaultAddress);

					
					
					
					
					//결제 페이지에 요청을 위임 보냄
					path="/WEB-INF/views/order/payment.jsp";
					
					dispatcher = req.getRequestDispatcher(path);

					dispatcher.forward(req, resp);

				}
				
				
			} else {// method 방식이 post

				if (command.equals("add")) { // post이면서 add: 제품 입력시 data가 text, image로 오게 된다

					/*
					 * MultipartRequest 을 사용하기 위한 준비 1. 업로드 되는 파일 전체 합의 최대 용량 지정
					 * 
					 * 2. 업로드 되는 파일(이미지,음악파일 등)을 서버 컴퓨터 어디에 저장할지 경로 지정
					 * 
					 * 3. 저장되는 파일의 이름을 변경 (ex카카오톡도 날짜, 번호로 이름이 바뀜: 왜 해야 하는가? 파일 중복이 나서 덮어쓰기 등이 발생하는데
					 * 이를 막기 위해서이다)
					 * 
					 * 
					 */
					// 1. 업로드 되는 파일 전체 합의 최대 용량
					int maxSize = 1024 * 1024 * 100;
					// 2. 업로드 되는 파일을 어디에 저장할지 경로 지정(실제 파일 경로)
					String root = req.getServletContext().getRealPath("/");

					String filePath = "/resources/images/product/";

					String realPath = root + filePath;
					// 3. 저장되는 파일의 이름을 namePolicy를 이용해서 변경(namePolicy interface 사용)
					// text data는 그냥 저장되서 product 테이블에,
					// image data는 FileRenamePolicy로 이름이 변경되어 resource 폴더에 저장되고 위치 정보가 product_img
					// 폴더에 저장됨

					MultipartRequest mReq = new MultipartRequest(req, realPath, maxSize, "UTF-8", new MyRenamePolicy());
					// req로 받아온 정보들은 mReq에서도 그대로 사용, 전달가능
					// 세팅 완료

					// 4. 데이터 받아오기 text, image 따로

					// 4.1 텍스트 데이터
					String productName = mReq.getParameter("productName");
					int price = Integer.parseInt(mReq.getParameter("productPrice"));
					double discount = Double.parseDouble(mReq.getParameter("productDiscount"));
					int stock = Integer.parseInt(mReq.getParameter("productStock"));
					int categoryNo = Integer.parseInt(mReq.getParameter("categoryNo"));

					Product prod = new Product();
					prod.setProductName(productName);
					prod.setPrice(price);
					prod.setDiscount(discount);
					prod.setStock(stock);
					prod.setCategoryNo(categoryNo);
					

					// 4.2 image 데이터

					Enumeration<String> files = mReq.getFileNames();// 파일 name 어트리뷰트들의 정보를 얻어옴. 3개
					List<ProductImage> imgList = new ArrayList<ProductImage>();

					while (files.hasMoreElements()) {
						String name = files.nextElement();// 다음 요소값(name) 얻어오기
						if (mReq.getFilesystemName(name) != null) {// 현재 요소에 업로드된 파일이 있을 경우
							ProductImage tmp = new ProductImage();
							tmp.setImgName(mReq.getFilesystemName(name));

							// DB에서 꺼내서 img태그의 src 부분에 사용할 것이다, img src = 폴더의 경로 + 파일이름 으로 해당 이미지를 보여주는 방식
							// 웹 문서상 경로이기 때문에 file경로만 있어도 됨
							tmp.setImgPath(filePath);

							// img 떼네고 imgLevel 필드에 int값 집어넣기
							tmp.setImgLevel(Integer.parseInt(name.replace("img", "")));

							imgList.add(tmp);
						}
					}

					// 5.데이터 다루기: 두 테이블에 정보를 동시에 저장하고 외래 키로 연결하는 방식,
					// 먼저 product 시퀀스의 nextVal로 image테이블에서 참조 키로 사용하는 번호를 받아와야한다, 그 후 그 번호를 product와
					// productImage 객체에 집어넣고 사용한다.
					// 위 과정은 서비스 단에서 진행한다.

					 
				int result = service.addProduct(prod,imgList);
					
				if(result >0) {
					message ="제품 추가에 성공하였습니다.";
				} else {
					message ="제품 추가에 실패하였습니다.";

				}
			
				path = contextPath;
				
				
				session.setAttribute("message", message);
				
				resp.sendRedirect(path);
				
				} else if(command.equals("addCart")) {
					
					int productNo = Integer.parseInt(req.getParameter("productNo"));
					int amount = Integer.parseInt(req.getParameter("amount"));
					int	loginMemberNo = loginMember.getMemberNo();
					
				 int result = service.addCart(productNo,amount,loginMemberNo);
					
					//ajax 반환
				 
				resp.getWriter().print(result);	
					
					
					
					
				}
				
			

			}

		} catch (Exception e) {
			e.printStackTrace();

		}

	}

}
