package teamSemiProject2.edu.kh.semi.board.model.service;


import static teamSemiProject2.edu.kh.semi.common.JDBCTemplate.*;
import java.sql.Connection;
import java.util.List;

import teamSemiProject2.edu.kh.semi.board.model.dao.BoardDAO;
import teamSemiProject2.edu.kh.semi.board.model.vo.Board;
import teamSemiProject2.edu.kh.semi.board.model.vo.BoardImage;
import teamSemiProject2.edu.kh.semi.board.model.vo.Category;
import teamSemiProject2.edu.kh.semi.board.model.vo.Pagination;
import teamSemiProject2.edu.kh.semi.common.XSS;
import teamSemiProject2.edu.kh.semi.product.model.vo.Product;

public class BoardService {
	private BoardDAO dao = new BoardDAO();

	// 페이지 네이션
	public Pagination getPagination(int cp, int code) throws Exception {

		Connection conn = getConnection();

		// 전체 게시글 수 조회 DAO 호출
		int listCount = dao.getListCount(conn,code);

		close(conn);

		// 전체 게시글 수 + 현패 페이지를 이용하여
		// 페이징 처리 관련 값 생성
		return new Pagination(listCount, cp);
	}
	
	// 검색 페이지네이션
	public Pagination getPagination(int cp, int code, String searchKey, String search) throws Exception{
		Connection conn = getConnection();
		
		String condition = null;
		
		switch(searchKey) {
		case "board_title":
			condition = " AND BOARD_TITLE LIKE '%' || ? || '%'"; break;
		case "board_content":
			condition = " AND BOARD_CONTENT LIKE '%' || ? || '%'"; break;
		case "member_name":
			condition = " AND MEMBER_NAME LIKE '%' || ? || '%'"; break;
		}

		// 검색 조건을 만족하는 전체 게시글 수 조회 DAO 호출
		int listCount = dao.getListCount(conn,code, condition, search );

		close(conn);

		// 검색 조건을 만족하는 전체 게시글 수 + 현패 페이지를 이용하여
		// 페이징 처리 관련 값 생성
		return new Pagination(listCount, cp);
	}
	

	// 게시글 목록 조회
	public List<Board> selectBoardList(Pagination pagination, int code) throws Exception {

		Connection conn = getConnection();
		List<Board> boardList = dao.selectBoardList(pagination, conn,code);

		close(conn);
		return boardList;
	}
	
	
	// 검색 게시글 목록 조회
	public List<Board> selectSearchList(Pagination pagination, int code, String searchKey, String search) throws Exception {

		Connection conn = getConnection();
		String condition = null;
		
		switch(searchKey) {
		case "board_title":
			condition = " AND BOARD_TITLE LIKE '%' || ? || '%'"; break;
		case "board_content":
			condition = " AND BOARD_CONTENT LIKE '%' || ? || '%'"; break;
		case "member_name":
			condition = " AND MEMBER_NAME LIKE '%' || ? || '%'"; break;
		}
		
		List<Board> boardList = dao.selectSearchList(pagination, conn,code, condition, search);

		close(conn);
		return boardList;
	}

	
	

	public Board selectBoard(int boardNo, int memberNo) throws Exception {

		Connection conn = getConnection();

		Board board = dao.selectBoardList(boardNo, conn);
		// 조회된 게시글이 있고, 해당 게시글의 작성자와 로그인된 회원이 같지 않으면
		// 조회수 증가
		if (board != null && board.getMemberNo() != memberNo) {

			int result = dao.increaseReadCount(boardNo, conn);

			if (result > 0) {
				commit(conn);

				// 먼저 조회되었던 게시글 정보에서 조회수를 1증가
				board.setReadCount(board.getReadCount() + 1);
			}

			else
				rollback(conn);

		}

		close(conn);

		return board;
	}

	public List<Category> selectCategory() throws Exception{
		Connection conn = getConnection();
		
		List<Category> category = dao.selectCategory(conn);
		close(conn);
		return category;
	}

	public int insertBoard(Board board) throws Exception{
		Connection conn = getConnection();
		int boardNo = dao.nextBoardNo(conn);
		
		board.setBoardNo(boardNo);
		
		
		// 게시글 삽입 
		// xss 방지
		board.setBoardTitle(XSS.replaceParameter(board.getBoardTitle()));
		board.setBoardContent(XSS.replaceParameter(board.getBoardContent()));
		
		// 개행문자 <br>변경
		
		String content = board.getBoardContent().replaceAll("(\r\n|\r|\n|\n\r)", "<br>");
		board.setBoardContent(content);
		
		
		int result = dao.insertBoard(board,conn);
		if(result >0) {
			commit(conn);
			result = boardNo;
		}
		else rollback(conn);
		return result;
	}

	public List<Product> selectProduct() throws Exception{
		
		Connection conn = getConnection();
		List<Product> product = dao.selectProduct(conn);
		close(conn);
		return product;
	}

//	public List<Board> searchBoardList(String searchKey, String search) throws Exception{
//		
//		Connection conn = getConnection();
//		List<Board> board = dao.searchBoardList(searchKey,search,conn);
//		close(conn);
//		
//		return board;
//	}







}
