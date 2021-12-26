package teamSemiProject2.edu.kh.semi.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/pop")
public class PopController extends HttpServlet{

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String path = null;
		RequestDispatcher dispatcher = null;
		req.setCharacterEncoding("UTF-8");
		
		path = "/WEB-INF/views/common/pop.jsp";
		dispatcher = req.getRequestDispatcher(path);
		dispatcher.forward(req, resp);
	}
}
