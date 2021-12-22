package teamSemiProject2.edu.kh.semi.board.model.service;


import static teamSemiProject2.edu.kh.semi.common.JDBCTemplate.*;
import java.sql.Connection;
import java.util.List;

import teamSemiProject2.edu.kh.semi.board.model.dao.BoardDAO;
import teamSemiProject2.edu.kh.semi.board.model.vo.Board;
import teamSemiProject2.edu.kh.semi.board.model.vo.BoardImage;
import teamSemiProject2.edu.kh.semi.board.model.vo.Category;
import teamSemiProject2.edu.kh.semi.board.model.vo.Pagination;

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

	// 게시글 목록 조회
	public List<Board> selectBoardList(Pagination pagination, int code) throws Exception {

		Connection conn = getConnection();
		List<Board> boardList = dao.selectBoardList(pagination, conn,code);

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

}
