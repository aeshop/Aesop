package teamSemiProject2.edu.kh.semi.category.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import teamSemiProject2.edu.kh.semi.category.model.service.CategoryService;
import teamSemiProject2.edu.kh.semi.category.model.vo.Category;
import teamSemiProject2.edu.kh.semi.product.model.vo.Product;

@WebServlet("/category/*")
public class CategoryController extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String method = req.getMethod();
		String uri = req.getRequestURI();
		String contextPath = req.getContextPath();
		String command = uri.substring((contextPath + "/category/").length());

		
		String path = null;
		RequestDispatcher dispatcher = null;
		String message = null;
		
		CategoryService service = new CategoryService();
		
		try {

			if (method.equals("GET")) {
				//a href로 카테고리: 제품 진열 페이지로 이동, 이동 시 parameter는 카테고리 번호, 페이지네이션을 위해서 currentPage정보가 필요함: 기본 1
				if (command.equals("view")) {

				//카테고리 페이지로 요청위임 경로설정
				path = "/WEB-INF/views/category/category.jsp";
				
				//위임할 제품진열 페이지에 카테고리 번호, 현재 페이지를 가지고 와서 DB에서 데이터 조회 후 뿌려주어야 함
				//DB의 얻어올 정보는 제품 정보 List<Product>와,  pagination 정보:페이지네이션 객체
				
				//우선은 기초적으로 나열 실험 해보려 <Product> List만을 들고온다 
				
				
				List<Product> pList = service.getProduct();
				
				
				req.setAttribute("productList", pList);
				
				dispatcher = req.getRequestDispatcher(path);
				dispatcher.forward(req, resp);
				
				
				
				}
			} else {//method 방식이 post 
				
				
				
				
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();

		}

	}

}
