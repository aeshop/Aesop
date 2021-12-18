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
		try {

			if (method.equals("GET")) {//a href로 제품 입력 페이지로 오게됨, DB에서 카테고리 정보 담아서 보여줌, 대분류 소분류 안나누고 보여주기
				CategoryService service = new CategoryService();
				if (command.equals("add")) {

			List<Category> category	= service.getCategory();
					req.setAttribute("category", category);
					
					dispatcher = req.getRequestDispatcher("/WEB-INF/views/category/addProduct.jsp");
					
					
					
					dispatcher.forward(req, resp);
					
				}
			} else {//method 방식이 post 
				
				if (command.equals("add")) { //post이면서 add: 제품 입력시 data가 text, image로 오게 된다
					
					
					
					
				}
				
				
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();

		}

	}

}
