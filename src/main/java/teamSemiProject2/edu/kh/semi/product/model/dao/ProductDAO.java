package teamSemiProject2.edu.kh.semi.product.model.dao;

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
import teamSemiProject2.edu.kh.semi.product.model.vo.Product;
import teamSemiProject2.edu.kh.semi.product.model.vo.ProductImage;

public class ProductDAO {

	PreparedStatement pstmt = null;
	ResultSet rs = null;
	Properties prop = null;

	public ProductDAO() {
		try {
			prop = new Properties();
			String filePath = ProductDAO.class.getResource("/teamSemiProject2/edu/kh/semi/sql/product-query.xml")
					.getPath();

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
				tmp.setCategoryNo(rs.getInt("CATEGORY_NO"));
				tmp.setCategoryName(rs.getString("CATEGORY_NM"));
				category.add(tmp);

			}
		} finally {

			close(rs);
			close(pstmt);
		}

		return category;
	}

	public int nextProductNum(Connection conn) throws SQLException {
		String sql = prop.getProperty("nextProductNum");
		int result = 0;
		try {

			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				result = rs.getInt(1);
			}
		} finally {

			close(rs);
			close(pstmt);
		}

		return result;
	}

	public int insertProduct(Product prod, Connection conn) throws Exception {
		String sql = prop.getProperty("insertProduct");
		int result = 0;
		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, prod.getProductNo());
			pstmt.setString(2, prod.getProductName());
			pstmt.setInt(3, prod.getPrice());
			pstmt.setDouble(4, prod.getDiscount());
			pstmt.setInt(5, prod.getStock());
			pstmt.setInt(6, prod.getCategoryNo());

			
			
			result = pstmt.executeUpdate();

			
		} finally {

			close(pstmt);
		}

		return result;
	}

	public int insertImg(ProductImage img, Connection conn) throws SQLException {
		String sql = prop.getProperty("insertImg");
		int result = 0;
		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, img.getImgPath());
			pstmt.setString(2, img.getImgName());
			pstmt.setInt(3, img.getImgLevel());
			pstmt.setInt(4, img.getProductNo());

			
			
			result = pstmt.executeUpdate();

			
		} finally {

			close(pstmt);
		}

		return result;
	}

}
