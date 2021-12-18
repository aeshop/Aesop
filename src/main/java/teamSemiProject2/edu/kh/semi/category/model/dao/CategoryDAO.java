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

import org.eclipse.jdt.internal.compiler.IErrorHandlingPolicy;

import teamSemiProject2.edu.kh.semi.category.model.vo.Category;
import teamSemiProject2.edu.kh.semi.order.controller.orderConTmp;
import teamSemiProject2.edu.kh.semi.product.model.vo.Product;
import teamSemiProject2.edu.kh.semi.product.model.vo.ProductImage;

public class CategoryDAO {

	PreparedStatement pstmt = null;
	ResultSet rs = null;
	Properties prop = null;

	public CategoryDAO() {
		try {
			prop = new Properties();
			String filePath = CategoryDAO.class.getResource("/teamSemiProject2/edu/kh/semi/sql/category-query.xml")
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

	public List<Product> getProduct(Connection conn) throws Exception {

		List<Product> pList = new ArrayList<Product>();

		try {
			String sql = prop.getProperty("getProduct");

			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				Product tmp = new Product();
				tmp.setProductNo(rs.getInt("PRODUCT_NO"));
				tmp.setProductName(rs.getString("PRODUCT_NM"));
				tmp.setPrice(rs.getInt(3));
				tmp.setDiscount(rs.getDouble(4));
				tmp.setStock(rs.getInt(5));
				tmp.setCategoryNo(rs.getInt(6));
				tmp.setStatusNo(rs.getInt(7));

				pList.add(tmp);
			}
		} finally {
			close(rs);
			close(pstmt);
		}

		return pList;
	}

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

}
