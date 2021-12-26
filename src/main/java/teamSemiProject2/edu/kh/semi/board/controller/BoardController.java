package teamSemiProject2.edu.kh.semi.board.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import teamSemiProject2.edu.kh.semi.board.model.service.BoardService;
import teamSemiProject2.edu.kh.semi.board.model.service.ReplyService;
import teamSemiProject2.edu.kh.semi.board.model.vo.Board;
import teamSemiProject2.edu.kh.semi.board.model.vo.Category;
import teamSemiProject2.edu.kh.semi.board.model.vo.Pagination;
import teamSemiProject2.edu.kh.semi.board.model.vo.Reply;
import teamSemiProject2.edu.kh.semi.member.model.vo.Member;
import teamSemiProject2.edu.kh.semi.product.model.vo.Product;

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
		HttpSession session = req.getSession();

		try {

			BoardService service = new BoardService();

			int cp = req.getParameter("cp") == null ? 1 : Integer.parseInt(req.getParameter("cp"));

			if (command.equals("list")) {
				Pagination pagination = null;
				List<Board> boardList = null;

				int code = Integer.parseInt(req.getParameter("c"));
				if (req.getParameter("search") != null) {
					String searchKey = req.getParameter("search_key");
					String search = req.getParameter("search");

					pagination = service.getPagination(cp, code, searchKey, search);

					boardList = service.selectSearchList(pagination, code, searchKey, search);
					// boardList = service.searchBoardList(searchKey,search);

				} else {

					pagination = service.getPagination(cp, code);

					boardList = service.selectBoardList(pagination, code);
				}
				String gubun = null;
				switch (code) {
				case 801:
					gubun = "리뷰";
					break;
				case 802:
					gubun = "Q&A";
					break;
				case 803:
					gubun = "공지사항";
					break;
				}

				req.setAttribute("gubun", gubun);
				req.setAttribute("pagination", pagination);

				req.setAttribute("boardList", boardList);
				path = "/WEB-INF/views/board/notice/noticeList.jsp";
				dispatcher = req.getRequestDispatcher(path);
				dispatcher.forward(req, resp);

			} // list 끝

			else if (command.equals("view")) {
				int boardNo = Integer.parseInt(req.getParameter("no"));
				Member loginMember = (Member) req.getSession().getAttribute("loginMember");
				int memberNo = 0;
				if (loginMember != null)
					memberNo = loginMember.getMemberNo();
				
				String img = service.imgSearch(boardNo);
				Board board = service.selectBoard(boardNo, memberNo);
				if (board != null) {

					List<Reply> rList = new ReplyService().selectReplyList(boardNo);
					req.setAttribute("rList", rList);
					req.setAttribute("img", img);
					req.setAttribute("board", board);
					path = "/WEB-INF/views/board/notice/noticeView.jsp";
					dispatcher = req.getRequestDispatcher(path);
					dispatcher.forward(req, resp);
				}
				// if 끝
				else {
					// 조회실패
					System.out.println("조회실패");
					resp.sendRedirect("list");
				}
			} else if (command.equals("delete")) {
				int boardNo = Integer.parseInt(req.getParameter("no"));
				System.out.println(boardNo);
				int result = service.deleteBoard(boardNo);
				resp.getWriter().print(result);
				
				
			} else if (command.equals("insert")) {
				int memberNo = ((Member) session.getAttribute("loginMember")).getMemberNo();
				if (method.equals("GET")) {
					List<Category> category = service.selectCategory();

					List<Product> product = service.selectProduct(memberNo);
					
					req.setAttribute("product", product);
					req.setAttribute("category", category);

					path = "/WEB-INF/views/board/notice/noticeInsert.jsp";
					dispatcher = req.getRequestDispatcher(path);
					dispatcher.forward(req, resp);
				} else {

					
					String boardTitle = req.getParameter("boardTitle");

					String boardContent = req.getParameter("boardContent");

					int categoryCode = Integer.parseInt(req.getParameter("categoryCode"));
					
					int star = Integer.parseInt(req.getParameter("star"));
					Board board = new Board();
					
					if(req.getParameter("reviewImg")!=null) {
						int productNo = Integer.parseInt(req.getParameter("reviewImg"));
						board.setProductNo(productNo);
						
					}
					board.setBoardTitle(boardTitle);
					board.setBoardContent(boardContent);
					board.setCategoryCode(categoryCode);
					board.setMemberNo(memberNo);
					
					board.setProductScore(star);
					
					int result = service.insertBoard(board);
					if (result > 0) {
						message = "게시글이 등록 되었습니다.";
						path = "view?no=" + result + "&cp=1&c=" + req.getParameter("c");
					} else {
						message = "게시글 등록중 문제가 발생했습니다.";
						path = "insert";
					}

					session.setAttribute("message", message);
					resp.sendRedirect(path);
				}
			}
			
			else if(command.equals("updateBoard")) {
				
				int memberNo = ((Member) session.getAttribute("loginMember")).getMemberNo();
				
					

					List<Product> product = service.selectProduct(memberNo);
					
					
					
				
				
				int boardNo = Integer.parseInt(req.getParameter("no"));
				Board board = service.updateView(boardNo);
				List<Category> category = service.selectCategory();
				req.setAttribute("product", product);
				req.setAttribute("board", board);
				req.setAttribute("category", category);
				path = "/WEB-INF/views/board/notice/boardUpdate.jsp";
				dispatcher = req.getRequestDispatcher(path);
				dispatcher.forward(req, resp);
			}
			else if(command.equals("update")) {
				
				session = req.getSession();
				
				String boardTitle = req.getParameter("boardTitle");
				String boardContent = req.getParameter("boardContent");
				int categoryCode = Integer.parseInt( req.getParameter("categoryCode") );
				
				// 수정할 게시글 번호 얻어오기 (insert와의 차이점)
				int boardNo = Integer.parseInt( req.getParameter("no"));
				
				// 로그인한 회원 번호
				int memberNo = ((Member)session.getAttribute("loginMember")).getMemberNo();
				

				int star = Integer.parseInt(req.getParameter("star"));
				Board board = new Board();
				
				if(req.getParameter("reviewImg")!=null) {
					int productNo = Integer.parseInt(req.getParameter("reviewImg"));
					board.setProductNo(productNo);
					
				}
				board.setBoardTitle(boardTitle);
				board.setBoardContent(boardContent);
				board.setCategoryCode(categoryCode);
				board.setMemberNo(memberNo);
				board.setBoardNo(boardNo);
				board.setProductScore(star);
			
				
				 
				int result = service.updateBoard(board);
				
				
				if(result > 0) { // 수정 성공
					message = "게시글이 수정되었습니다.";
					path = "view?no=" + boardNo + "&cp=" + req.getParameter("cp")+"&c="+board.getCategoryCode();
							
				}else { // 수정 실패
					
					message = "게시글 수정 실패";
					path = req.getHeader("referer") + "?no=" + boardNo+"&c="+req.getParameter("c");
				
					
				}
				
				session.setAttribute("message", message);
				resp.sendRedirect(path);
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
