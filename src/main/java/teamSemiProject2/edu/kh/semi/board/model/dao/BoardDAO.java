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
import teamSemiProject2.edu.kh.semi.board.model.vo.Pagination;
import teamSemiProject2.edu.kh.semi.category.model.dao.CategoryDAO;
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

	
	
	//  전체 게시글 수 조회
	public int getListCount(Connection conn,int code) throws Exception{
		
		int listCount = 0;
		
		try {

			String sql = prop.getProperty("getListCount");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, code);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				listCount = rs.getInt(1);
			}
			
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return listCount;
	}



	public List<Board> selectBoardList(Pagination pagination, Connection conn, int code) throws Exception{
		List<Board> boardList = new ArrayList<Board>(); 
		
		try {
			String sql = prop.getProperty("selectBoardList");
			int startRow = (pagination.getCurrentPage() - 1) * pagination.getLimit() + 1  ;
			int endRow = startRow + pagination.getLimit() - 1;
			
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, code);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			
			rs = pstmt.executeQuery();
			int count = pagination.getListCount() -(pagination.getCurrentPage()-1) * pagination.getLimit();  
			while(rs.next()) {
				
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
			
			if(rs.next()) {
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
				
			}
			
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return board;
	}



	public int increaseReadCount(int boardNo, Connection conn) throws Exception{
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




	
	
	
	
	
	
	
	
}
