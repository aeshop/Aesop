package teamSemiProject2.edu.kh.semi.category.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import teamSemiProject2.edu.kh.semi.category.model.service.CategoryService;
import teamSemiProject2.edu.kh.semi.category.model.vo.Category;
import teamSemiProject2.edu.kh.semi.category.model.vo.Pagination;
import teamSemiProject2.edu.kh.semi.member.model.vo.Member;
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

		HttpSession session = req.getSession();
		Member loginMember = (Member) session.getAttribute("loginMember");
		
		
		
		String path = null;
		RequestDispatcher dispatcher = null;
		String message = null;
		
		CategoryService service = new CategoryService();
		
		try {

			if (method.equals("GET")) {
				//a href로 카테고리: 제품 진열 페이지로 이동, 이동 시 parameter는 카테고리 번호, 페이지네이션을 위해서 currentPage정보가 필요함: 기본 1
				if (command.equals("view")) {

			
				
				//위임할 제품진열 페이지에 카테고리 번호, 현재 페이지를 가지고 와서 DB에서 데이터 조회 후 뿌려주어야 함
				
				//카테고리 번호, 현재 페이지 가지고 오기, 기본값 설정
				
				String c = req.getParameter("cate");//카테고리
				
				int categoryNo = c!=null? Integer.parseInt(c): 300;
				
				String p = req.getParameter("cp");				
				int cp = p!=null? Integer.parseInt(p) : 1;
				
				//카테고리 번호가 300 = 기본 카테고리일 경우 모든 상품을 가져와야한다
				//기본 카테고리가 아니라 310 320 이런식일 경우 그 부류의 상품만을 가져와야한다
				//번호에 따라 SQL문을 다르게 쓰면 쉽게 가능하지만, 한번에 쓰고 싶으면? 
				
				//300 : 이상 이하로 안됨, 310 320: 310 이상 319 이하, 320 이상 329 이하 이런식으로 가능
				//그냥 번호에 따라 하기로 결론냄: 300일때는 300 ~ 399 이도록 만들어놓음
				
				//전체 카테고리 정보는 항상 보여지고 있는 상태, 클릭한것만 검정불 들어오고  가져온 상태에서, 세부 카테고리는 안보임
				//널디와 이솝의 제품페이지 차이점이 널디는 큰 카테고리 안 세부 카테고리가 없고 이솝은 있다는 것
				
				//소 분류
				//소분류의 경우에는 자기것만 보여야 하므로, between 물음표가 자기에서 자기로 끝나야 한다
				List<Category> cList = service.getCategory();
				
				//sort_method= 1(최신),2(상품명),3(낮,가),4(높,가),5
				String sm = req.getParameter("sort_method");
				
				int sortMethod = sm!=null?Integer.parseInt(sm):1;
				
				for(Category cate : cList){
					cate.setCurrentCategoryNo(categoryNo);					
				}
				
				req.setAttribute("category", cList);
				
				//DB의 얻어올 정보는 제품 정보 List<Product>와,  pagination 정보:페이지네이션 객체이고, pagination객체를 먼저 얻어와야한다
				Pagination pagination = service.getPagination(cp,categoryNo);				
				//페이지네이션 계산이 다 끝난 객체와 입력된 파라미터들을 전달
				List<Product> pList = service.getProduct(pagination,categoryNo,sortMethod);
				
				
				req.setAttribute("pagination", pagination);
				req.setAttribute("productList", pList);
				req.setAttribute("sort_method", sortMethod);
				String [] methodArr = {"신상품","상품명","낮은가격","높은가격"};
				req.setAttribute("method_name", methodArr[sortMethod-1]);
				//카테고리 페이지로 요청위임 경로설정
				path = "/WEB-INF/views/category/category.jsp";
				dispatcher = req.getRequestDispatcher(path);
				dispatcher.forward(req, resp);
				
				
				
				} else if (command.equals("search")) {
					
					
					String c = req.getParameter("cate");//카테고리
					String keyword = req.getParameter("keyword");
					String p = req.getParameter("cp");				

					
					int categoryNo = c!=null? Integer.parseInt(c): 300;
					
					int cp = p!=null? Integer.parseInt(p) : 1;
					Pagination pagination = service.getPagination(cp,categoryNo,keyword);				

					//sort_method= 1(최신),2(상품명),3(낮,가),4(높,가),5
					String sm = req.getParameter("sort_method");
					
					int sortMethod = sm!=null?Integer.parseInt(sm):1;
					
					
					List<Product> pList = null;
					pList=service.searchKeyword(pagination, categoryNo,sortMethod,keyword );
					
//					System.out.println(pagination);
					
					req.setAttribute("pagination", pagination);

					
					req.setAttribute("keyword", keyword);
					List<Category> cList = service.getCategory();
					for(Category cate : cList){
						cate.setCurrentCategoryNo(categoryNo);					
					}

					req.setAttribute("category", cList);
					req.setAttribute("sort_method", sortMethod);

					req.setAttribute("productList", pList);
					
					//카테고리 페이지로 요청위임 경로설정
					path = "/WEB-INF/views/category/search.jsp";
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
