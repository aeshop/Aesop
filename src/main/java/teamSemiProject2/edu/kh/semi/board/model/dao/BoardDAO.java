package teamSemiProject2.edu.kh.semi.board.model.dao;

import static teamSemiProject2.edu.kh.semi.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import teamSemiProject2.edu.kh.semi.board.model.vo.Board;
import teamSemiProject2.edu.kh.semi.board.model.vo.BoardImage;
import teamSemiProject2.edu.kh.semi.board.model.vo.Category;
import teamSemiProject2.edu.kh.semi.board.model.vo.Pagination;
import teamSemiProject2.edu.kh.semi.category.model.dao.CategoryDAO;
import teamSemiProject2.edu.kh.semi.product.model.vo.Product;
import teamSemiProject2.edu.kh.semi.product.model.vo.ProductImage;

public class BoardDAO {

	PreparedStatement pstmt = null;
	ResultSet rs = null;
	Properties prop = null;

	public BoardDAO() {
		String filePath = CategoryDAO.class.getResource("/teamSemiProject2/edu/kh/semi/sql/board-query.xml").getPath();
		try {

			prop = new Properties();
			prop.loadFromXML(new FileInputStream(filePath));
		} catch (Exception e) {
			e.printStackTrace();
		}
	} // 생성자 끝

	// 전체 게시글 수 조회
	public int getListCount(Connection conn, int code) throws Exception {

		int listCount = 0;

		try {

			String sql = prop.getProperty("getListCount");

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, code);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				listCount = rs.getInt(1);
			}

		} finally {
			close(rs);
			close(pstmt);
		}

		return listCount;
	}

	// 검색 조건을 만족하는 전체 게시글 수 조회
	public int getListCount(Connection conn, int code, String condition, String search) throws Exception {

		int listCount = 0;

		try {

			String sql = prop.getProperty("getListCount") + condition;

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, code);
			pstmt.setString(2, search);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				listCount = rs.getInt(1);
			}

		} finally {
			close(rs);
			close(pstmt);
		}

		return listCount;
	}

	public List<Board> selectBoardList(Pagination pagination, Connection conn, int code) throws Exception {
		List<Board> boardList = new ArrayList<Board>();

		try {
			String sql = prop.getProperty("selectBoardList");
			int startRow = (pagination.getCurrentPage() - 1) * pagination.getLimit() + 1;
			int endRow = startRow + pagination.getLimit() - 1;

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, code);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);

			rs = pstmt.executeQuery();
			int count = pagination.getListCount() - (pagination.getCurrentPage() - 1) * pagination.getLimit();
			while (rs.next()) {

				Board board = new Board();
				board.setViewNo(count--);
				board.setBoardNo(rs.getInt("BOARD_NO"));
				board.setBoardTitle(rs.getString("BOARD_TITLE"));
				board.setMemberName(rs.getString("MEMBER_NAME"));
				board.setReadCount(rs.getInt("READ_COUNT"));
				board.setCategoryName(rs.getString("CATEGORY_NM"));
				board.setBoardStatusName(rs.getString("BD_STATUS_NM"));
				board.setCreateDate(rs.getString("CREATE_DT"));

				// 리스트에 담기
				boardList.add(board);
			}
		} finally {
			close(rs);
			close(pstmt);
		}

		return boardList;
	}

	public Board selectBoardList(int boardNo, Connection conn) throws Exception {

		Board board = null;

		try {
			String sql = prop.getProperty("selectBoard");

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				board = new Board();

				board.setBoardNo(rs.getInt("BOARD_NO"));
				board.setBoardTitle(rs.getString("BOARD_TITLE"));
				board.setBoardContent(rs.getString("BOARD_CONTENT"));
				board.setMemberNo(rs.getInt("MEMBER_NO"));
				board.setMemberName(rs.getString("MEMBER_NAME"));
				board.setCreateDate(rs.getString("CREATE_DT"));
				board.setModifyDate(rs.getString("MODIFY_DT"));
				board.setCategoryCode(rs.getInt("CATEGORY_CD"));
				board.setCategoryName(rs.getString("CATEGORY_NM"));
				board.setReadCount(rs.getInt("READ_COUNT"));
				board.setBoardStatusName(rs.getString("BD_STATUS_NM"));
				board.setProductScore(rs.getInt("PRODUCT_SCORE"));
				board.setProductNo(rs.getInt("PRODUCT_NO"));
			}

		} finally {
			close(rs);
			close(pstmt);
		}

		return board;
	}

	public int increaseReadCount(int boardNo, Connection conn) throws Exception {
		int result = 0;
		try {
			String sql = prop.getProperty("increaseReadCount");

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);

			result = pstmt.executeUpdate();
		} finally {
			close(pstmt);
		}

		return result;
	}

	public List<Category> selectCategory(Connection conn) throws Exception {

		List<Category> category = new ArrayList<Category>();

		try {
			String sql = prop.getProperty("selectCategory");

			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Category gory = new Category();

				gory.setCategoryCode(rs.getInt(1));
				gory.setCategoryName(rs.getString(2));
				category.add(gory);
			}
		} finally {
			close(rs);
			close(pstmt);
		}
		return category;
	}

	public int nextBoardNo(Connection conn) throws Exception {

		int boardNo = 0;
		try {
			String sql = prop.getProperty("nextBoardNo");

			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				boardNo = rs.getInt(1);
			}
		} finally {
			close(rs);
			close(pstmt);
		}

		return boardNo;
	}

	public int insertBoard(Board board, Connection conn) throws Exception {

		int result = 0;
		try {
			String sql = prop.getProperty("insertBoard");

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, board.getBoardNo());

			pstmt.setString(2, board.getBoardTitle());
			pstmt.setString(3, board.getBoardContent());
			pstmt.setInt(4, board.getCategoryCode());
			pstmt.setInt(5, board.getMemberNo());

			result = pstmt.executeUpdate();

		} finally {
			close(pstmt);
		}

		return result;
	}

	public List<Product> selectProduct(int memberNo, Connection conn) throws Exception {

		List<Product> product = new ArrayList<Product>();

		try {

			String sql = prop.getProperty("selectProduct");
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, memberNo);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Product pdt = new Product();
				pdt.setProductNo(rs.getInt(1));
				pdt.setProductName(rs.getString(2));
				pdt.setCategoryName(rs.getString(3) + rs.getString(4));

				product.add(pdt);
			}

		} finally {
			close(rs);
			close(pstmt);
		}

		return product;
	}

	public List<Board> selectSearchList(Pagination pagination, Connection conn, int code, String condition,
			String search) throws Exception {

		List<Board> board = new ArrayList<Board>();

		try {
			String sql = prop.getProperty("searchBoardList") + condition +" ORDER BY BOARD_NO DESC) A)" +" WHERE RNUM BETWEEN ? AND ?" ;
			
			int startRow = (pagination.getCurrentPage() - 1) * pagination.getLimit() + 1;
			int endRow = startRow + pagination.getLimit() - 1;
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, code);
			pstmt.setString(2, search);
			pstmt.setInt(3,startRow);
			pstmt.setInt(4,endRow);
			rs = pstmt.executeQuery();
			
			int count = pagination.getListCount() - (pagination.getCurrentPage() - 1) * pagination.getLimit();
			while (rs.next()) {
				Board board2 = new Board();
				board2.setViewNo(count--);
				board2.setBoardNo(rs.getInt("BOARD_NO"));
				board2.setBoardTitle(rs.getString("BOARD_TITLE"));
				board2.setMemberName(rs.getString("MEMBER_NAME"));
				board2.setReadCount(rs.getInt("READ_COUNT"));
				board2.setCategoryName(rs.getString("CATEGORY_NM"));
				board2.setBoardStatusCode(rs.getInt("BD_STATUS_CD"));
				board2.setCreateDate(rs.getString("CREATE_DT"));
				// 리스트에 담기
				board.add(board2);
			}
		} finally {
			close(rs);
			close(pstmt);
		}

		return board;
	}

	public int deleteBoard(int boardNo, Connection conn) throws Exception {

		int result = 0;

		try {
			String sql = prop.getProperty("deleteBoard");

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);

			result = pstmt.executeUpdate();
		} finally {
			close(pstmt);
		}

		return result;
	}

	public int insertBoardReview(Board board, Connection conn) throws Exception {
		int result = 0;
		try {
			String sql = prop.getProperty("insertBoardReview");

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, board.getBoardNo());

			pstmt.setString(2, board.getBoardTitle());
			pstmt.setString(3, board.getBoardContent());
			pstmt.setInt(4, board.getCategoryCode());
			pstmt.setInt(5, board.getMemberNo());
			pstmt.setInt(6, board.getProductScore());
			pstmt.setInt(7, board.getProductNo());
			result = pstmt.executeUpdate();

		} finally {
			close(pstmt);
		}

		return result;
	}

	public String imgSearch(int boardNo, Connection conn) throws Exception {

		String img = "";

		try {
			String sql = prop.getProperty("imgSearch");
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				img = rs.getString(1) + rs.getString(2);

			}

		} finally {
			close(rs);
			close(pstmt);
		}

		return img;
	}

	public int updateBoard(Board board, Connection conn) throws Exception {

		int result = 0;

		try {
			String sql = prop.getProperty("updateBoard");

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, board.getBoardTitle());
			pstmt.setString(2, board.getBoardContent());
			pstmt.setInt(3, board.getCategoryCode());
			pstmt.setInt(4, board.getBoardNo());

			result = pstmt.executeUpdate();
		} finally {
			close(pstmt);
		}

		return result;
	}

	public int updateBoardReview(Board board, Connection conn) throws Exception {
		int result = 0;

		try {
			String sql = prop.getProperty("updateBoardReview");
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, board.getBoardTitle());
			pstmt.setString(2, board.getBoardContent());
			pstmt.setInt(3, board.getCategoryCode());
			
			pstmt.setInt(4, board.getProductScore());
			pstmt.setInt(5, board.getProductNo());
			pstmt.setInt(6, board.getBoardNo());
			result = pstmt.executeUpdate();
		} finally {
			close(pstmt);
		}
		return result;
	}

}
