package teamSemiProject2.edu.kh.semi.main.controller;

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

@WebServlet("/main")
public class MainServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 모든 페이지들은 실행되고 나서 header, footer, sideMenu에 요청을 include : 요청에 매번 category 담기?

		// 카테고리 정보를 담아서 application scope 에 집어넣고 항상 사용하도록 만들어야함

		try {
			List<Category> cList = new CategoryService().getCategory();
			req.getServletContext().setAttribute("category", cList);
			req.getServletContext().setAttribute("categorySize", cList.size());

			
			
			String path = "/WEB-INF/views/common/main.jsp";
			RequestDispatcher dispatcher = req.getRequestDispatcher(path);
			dispatcher.forward(req, resp);

		} catch (Exception e) {

			e.printStackTrace();
			System.out.println("error in main");

		}

	}
}
