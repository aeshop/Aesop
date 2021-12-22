package teamSemiProject2.edu.kh.semi.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

import edu.kh.semi.common.MyRenamePolicy;
import teamSemiProject2.edu.kh.semi.board.model.service.BoardService;
import teamSemiProject2.edu.kh.semi.board.model.vo.Board;
import teamSemiProject2.edu.kh.semi.board.model.vo.Category;
import teamSemiProject2.edu.kh.semi.board.model.vo.Pagination;
import teamSemiProject2.edu.kh.semi.member.model.vo.Member;


@WebServlet("/board/notice/*")
public class BoardController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String method = req.getMethod();

		String uri = req.getRequestURI();
		String contextPath = req.getContextPath();

		String command = uri.substring((contextPath + "/board/notice/").length());

		String path = null;
		RequestDispatcher dispatcher = null;
		String message = null;
		int code = 803;
		try {

			BoardService service = new BoardService();

			int cp = req.getParameter("cp") == null ? 1 : Integer.parseInt(req.getParameter("cp"));
			
			if (command.equals("list")) {

				Pagination pagination = service.getPagination(cp,code);

				List<Board> boardList = service.selectBoardList(pagination,code);

				req.setAttribute("pagination", pagination);
				req.setAttribute("boardList", boardList);

				path = "/WEB-INF/views/board/notice/noticeList.jsp";
				dispatcher = req.getRequestDispatcher(path);
				dispatcher.forward(req, resp);

			} // list 끝
			
			
			else if(command.equals("view")){
				int boardNo = Integer.parseInt(req.getParameter("no"));
				Member loginMember = (Member)req.getSession().getAttribute("loginMember");
				int memberNo = 0;
				if(loginMember != null)  memberNo = loginMember.getMemberNo();
				
				Board board = service.selectBoard(boardNo,memberNo);
				if(board != null) {
					System.out.print("댓글");
					
					req.setAttribute("board", board);
					path = "/WEB-INF/views/board/notice/noticeView.jsp";
					dispatcher = req.getRequestDispatcher(path);
					dispatcher.forward(req, resp);
				}
				// if 끝
				else {
					// 조회실패
					System.out.println("조회실패");
				}
			}
			
			else if (command.equals("insert")) {
				if(method.equals("GET")) {
					List<Category> category = service.selectCategory();
					
					req.setAttribute("category", category);
					
					path = "/WEB-INF/view/board/boardInsert.jsp";
					dispatcher = req.getRequestDispatcher(path);
					dispatcher.forward(req, resp);
				}
				else {
					String boardTitle = req.getParameter("boardTitle");
					String boardContent = req.getParameter("boardContent");
					int categoryCode = Integer.parseInt(req.getParameter("categoryCode"));
					
					int memberNo = ((Member))
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		doGet(req, resp);
	}
}
