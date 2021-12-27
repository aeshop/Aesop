package teamSemiProject2.edu.kh.semi.category.model.dao;

import static teamSemiProject2.edu.kh.semi.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import teamSemiProject2.edu.kh.semi.category.model.vo.Category;
import teamSemiProject2.edu.kh.semi.category.model.vo.Pagination;
import teamSemiProject2.edu.kh.semi.product.model.vo.Product;
import teamSemiProject2.edu.kh.semi.product.model.vo.ProductImage;

public class CategoryDAO {

	PreparedStatement pstmt = null;
	ResultSet rs = null;
	Properties prop = null;

	public CategoryDAO() {
		try {
			prop = new Properties();
			String filePath = CategoryDAO.class.getResource("/teamSemiProject2/edu/kh/semi/sql/category-query.xml").getPath();

			prop.loadFromXML(new FileInputStream(filePath));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public List<Category> getCategory(Connection conn) throws SQLException {
		List<Category> category = new ArrayList<Category>();

		try {
			String sql = prop.getProperty("getCategory");

			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				Category tmp = new Category();
				tmp.setCategoryNo1(rs.getInt("CATEGORY_NO1"));
				tmp.setCategoryNo2(rs.getInt("CATEGORY_NO2"));

				tmp.setCategoryName(rs.getString("CATEGORY_NM"));
				category.add(tmp);

			}
		} finally {

			close(rs);
			close(pstmt);
		}

		return category;
	}

	public List<Product> getProduct(Pagination pagination, int categoryNo, Connection conn) throws Exception {

		List<Product> pList = new ArrayList<Product>();

		try {
			String sql = prop.getProperty("getProduct");

			pstmt = conn.prepareStatement(sql);
			// pagination 객체에 존재하는 limit 를 활용해야 한다.
			// 현재 페이지가 1이면, 제품은 1~12번, 2면 13~24번 제품이 보여져야한다
			// ((cp -1)*limit +1), ((cp -1)*limit +1) + limit -1

			// 받아온 categoryNo를 경우의 수에 맞게 활용

			int cateStart = 0;
			int cateEnd = 0;
			if (categoryNo == 300) {
				cateStart = 300;
				cateEnd = cateStart + 100 - 1;
			} else {
				
				if(categoryNo%10==0) {
					cateStart = categoryNo;
					cateEnd = cateStart + 10 - 1;
				} else {
					cateStart = categoryNo;
					cateEnd = categoryNo;

				}
			
			}

			int startProduct = ((pagination.getCurrentPage() - 1) * pagination.getLimit() + 1);
			int endProduct = startProduct + pagination.getLimit() - 1;

			// 카테고리별로 검색조건 앵커
			pstmt.setInt(1, cateStart);
			pstmt.setInt(2, cateEnd);
			// 페이지네이션 ROWNUM BETWEEN 앵커
			pstmt.setInt(3, startProduct);
			pstmt.setInt(4, endProduct);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				Product tmp = new Product();
				tmp.setProductNo(rs.getInt("PRODUCT_NO"));
				tmp.setProductName(rs.getString("PRODUCT_NM"));
				tmp.setPrice(rs.getInt("PRODUCT_PRICE"));
				tmp.setDiscount(rs.getDouble("DISCOUNT"));
				tmp.setStock(rs.getInt("STOCK"));
				tmp.setCategoryNo(rs.getInt("PRODUCT_CATEGORY"));//311 312 등이 온다
				tmp.setStatusNo(rs.getInt("PRO_STATUS_NO"));
				tmp.setCategoryName(rs.getString("CATEGORY_NM"));

				pList.add(tmp);
			}
		} finally {
			close(rs);
			close(pstmt);
		}

		return pList;
	}
//제품 이미지 가져옴
	public List<ProductImage> getProductImageList(int productNo, Connection conn) throws Exception {

		List<ProductImage> imgList = new ArrayList<ProductImage>();

		try {
			String sql = prop.getProperty("getProductImageList");

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, productNo);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ProductImage tmp = new ProductImage();
				tmp.setImgNo(rs.getInt(1));
				tmp.setImgPath(rs.getString(2));
				tmp.setImgName(rs.getString(3));
				tmp.setImgLevel(rs.getInt(4));
				tmp.setProductNo(rs.getInt(5));
				imgList.add(tmp);
			}
		} finally {
			close(rs);
			close(pstmt);
		}

		return imgList;
	}

	public int getPageCount(Connection conn) throws Exception {
		int result = 0;
		try {//상품 카테고리에 따라 동적으로 검색이 변해야 한다
			String sql = prop.getProperty("getPageCount");
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();
			if (rs.next()) {

				result = rs.getInt(1);
			}

		} finally {
			close(pstmt);
			close(rs);
		}

		return result;
	}
	
	public int getPageCount(int categoryNo, Connection conn) throws Exception {
		int result = 0;
		try {//상품 카테고리에 따라 동적으로 검색이 변해야 한다
			String sql = prop.getProperty("getPageCount");
			
			int cateStart = 0;
			int cateEnd = 0;
			if (categoryNo == 300) {
				cateStart = 300;
				cateEnd = cateStart + 100 - 1;
			} else {
				
				if(categoryNo%10==0) {
					cateStart = categoryNo;
					cateEnd = cateStart + 10 - 1;
				} else {
					cateStart = categoryNo;
					cateEnd = categoryNo;

				}
			
			}
			
			
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cateStart);
			pstmt.setInt(2, cateEnd);
			rs = pstmt.executeQuery();
			if (rs.next()) {

				result = rs.getInt(1);
			}

		} finally {
			close(pstmt);
			close(rs);
		}

		return result;
	}
	
	
	public int getPageCount(int categoryNo, Connection conn, String keyword) throws Exception {
		int result = 0;
		try {//상품 카테고리에 따라 동적으로 검색이 변해야 한다
			String sql = prop.getProperty("getPageCount_search");
			
			int cateStart = 0;
			int cateEnd = 0;
			if (categoryNo == 300) {
				cateStart = 300;
				cateEnd = cateStart + 100 - 1;
			} else {
				
				if(categoryNo%10==0) {
					cateStart = categoryNo;
					cateEnd = cateStart + 10 - 1;
				} else {
					cateStart = categoryNo;
					cateEnd = categoryNo;

				}
			
			}
			
			
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cateStart);
			pstmt.setInt(2, cateEnd);
			pstmt.setString(3, keyword);
			rs = pstmt.executeQuery();
			if (rs.next()) {

				result = rs.getInt(1);
			}

		} finally {
			close(pstmt);
			close(rs);
		}

		return result;
	}
	
	
	
	

	public List<Product> getProduct(Pagination pagination, int categoryNo, int sortMethod, Connection conn) throws Exception {

		List<Product> pList = new ArrayList<Product>();

		try {
			String sql = prop.getProperty("getProduct");


			sql+=prop.getProperty("sortMethod"+sortMethod);//상황에 따른 정렬조건 달아줌
			
			pstmt = conn.prepareStatement(sql);
			// pagination 객체에 존재하는 limit 를 활용해야 한다.
			// 현재 페이지가 1이면, 제품은 1~12번, 2면 13~24번 제품이 보여져야한다
			// ((cp -1)*limit +1), ((cp -1)*limit +1) + limit -1

			// 받아온 categoryNo를 경우의 수에 맞게 활용

			int cateStart = 0;
			int cateEnd = 0;
			if (categoryNo == 300) {
				cateStart = 300;
				cateEnd = cateStart + 100 - 1;
			} else {
				
				if(categoryNo%10==0) {
					cateStart = categoryNo;
					cateEnd = cateStart + 10 - 1;
				} else {
					cateStart = categoryNo;
					cateEnd = categoryNo;

				}
			
			}

			int startProduct = ((pagination.getCurrentPage() - 1) * pagination.getLimit() + 1);
			int endProduct = startProduct + pagination.getLimit() - 1;

			// 카테고리별로 검색조건 앵커
			pstmt.setInt(1, cateStart);
			pstmt.setInt(2, cateEnd);
			// 페이지네이션 ROWNUM BETWEEN 앵커
			pstmt.setInt(3, startProduct);
			pstmt.setInt(4, endProduct);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				Product tmp = new Product();
				tmp.setProductNo(rs.getInt("PRODUCT_NO"));
				tmp.setProductName(rs.getString("PRODUCT_NM"));
				tmp.setPrice(rs.getInt("PRODUCT_PRICE"));
				tmp.setDiscount(rs.getDouble("DISCOUNT"));
				tmp.setStock(rs.getInt("STOCK"));
				tmp.setCategoryNo(rs.getInt("PRODUCT_CATEGORY"));//311 312 등이 온다
				tmp.setStatusNo(rs.getInt("PRO_STATUS_NO"));
				tmp.setCategoryName(rs.getString("CATEGORY_NM"));
				tmp.setScoreAvg(rs.getString("AVG"));
				tmp.setScoreCount(rs.getString("CNT"));
				pList.add(tmp);
			}
		} finally {
			close(rs);
			close(pstmt);
		}

		return pList;
	}
	public List<Product> searchKeyword(Pagination pagination, int categoryNo, int sortMethod, String keyword,Connection conn) throws Exception {
		
		List<Product> pList = new ArrayList<Product>();
		
		try {
			String sql = prop.getProperty("searchKeyword");
			
			
			sql+=prop.getProperty("sortMethod"+sortMethod);//정렬조건 달아줌
			
			pstmt = conn.prepareStatement(sql);
			// pagination 객체에 존재하는 limit 를 활용해야 한다.
			// 현재 페이지가 1이면, 제품은 1~12번, 2면 13~24번 제품이 보여져야한다
			// ((cp -1)*limit +1), ((cp -1)*limit +1) + limit -1
			
			// 받아온 categoryNo를 경우의 수에 맞게 활용
			
			int cateStart = 0;
			int cateEnd = 0;
			if (categoryNo == 300) {
				cateStart = 300;
				cateEnd = cateStart + 100 - 1;
			} else {
				
				if(categoryNo%10==0) {
					cateStart = categoryNo;
					cateEnd = cateStart + 10 - 1;
				} else {
					cateStart = categoryNo;
					cateEnd = categoryNo;
					
				}
				
			}
			
			int startProduct = ((pagination.getCurrentPage() - 1) * pagination.getLimit() + 1);
			int endProduct = startProduct + pagination.getLimit() - 1;
			
			// 카테고리별로 검색조건 앵커
			pstmt.setInt(1, cateStart);
			pstmt.setInt(2, cateEnd);
			//검색조건 like %?%
			pstmt.setString(3, keyword);
			// 페이지네이션 ROWNUM BETWEEN 앵커
			pstmt.setInt(4, startProduct);
			pstmt.setInt(5, endProduct);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Product tmp = new Product();
				tmp.setProductNo(rs.getInt("PRODUCT_NO"));
				tmp.setProductName(rs.getString("PRODUCT_NM"));
				tmp.setPrice(rs.getInt("PRODUCT_PRICE"));
				tmp.setDiscount(rs.getDouble("DISCOUNT"));
				tmp.setStock(rs.getInt("STOCK"));
				tmp.setCategoryNo(rs.getInt("PRODUCT_CATEGORY"));//311 312 등이 온다
				tmp.setStatusNo(rs.getInt("PRO_STATUS_NO"));
				tmp.setCategoryName(rs.getString("CATEGORY_NM"));
				tmp.setScoreAvg(rs.getString("AVG"));
				tmp.setScoreCount(rs.getString("CNT"));
				pList.add(tmp);
			}
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return pList;
	}

	


	

}
