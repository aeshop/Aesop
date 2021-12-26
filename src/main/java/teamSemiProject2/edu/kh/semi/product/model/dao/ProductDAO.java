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
import teamSemiProject2.edu.kh.semi.order.model.vo.Order;
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

	public Product getProduct(int productNo, Connection conn) throws Exception {
		Product result = null;
		try {

			String sql = prop.getProperty("getProduct");

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, productNo);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				Product tmp = new Product();

				tmp.setProductNo(rs.getInt("PRODUCT_NO"));
				tmp.setProductName(rs.getString("PRODUCT_NM"));
				tmp.setPrice(rs.getInt("PRODUCT_PRICE"));
				tmp.setDiscount(rs.getDouble("DISCOUNT"));
				tmp.setStock(rs.getInt("STOCK"));
				tmp.setCategoryNo(rs.getInt("PRODUCT_CATEGORY"));
				tmp.setStatusNo(rs.getInt("PRO_STATUS_NO"));

				result = tmp;
			}

		} finally {
			close(rs);
			close(pstmt);
		}

		return result;
	}

	public List<ProductImage> getProductImg(int productNo, Connection conn) throws Exception {

		List<ProductImage> imgList = new ArrayList<ProductImage>();

		try {

			String sql = prop.getProperty("getProductImg");

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, productNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				ProductImage img = new ProductImage();

				img.setImgNo(rs.getInt(1));
				img.setImgPath(rs.getString(2));
				img.setImgName(rs.getString(3));
				img.setImgLevel(rs.getInt(4));
				img.setProductNo(rs.getInt(5));

				imgList.add(img);

			}

		} finally {

			close(rs);
			close(pstmt);
		}

		return imgList;
	}

	public int addCart(int productNo, int amount, int loginMemberNo, Connection conn) throws Exception {
		int result = 0;

		try {

			String sql = prop.getProperty("addCart");
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, loginMemberNo);
			pstmt.setInt(2, productNo);
			pstmt.setInt(3, amount);
			result = pstmt.executeUpdate();

		} finally {

			close(pstmt);
		}

		return result;
	}

	/**
	 * 새로운 주문 번호를 얻어오는 메소드
	 * 
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	public int getNewOrderNo(Connection conn) throws Exception {

		int result = 0;

		try {

			String sql = prop.getProperty("getNewOrderNo");
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				result = rs.getInt(1) + 1;
			}

		} finally {
			close(rs);
			close(pstmt);
		}

		return result;
	}

	public int insertOrder(int newOrderNo, int productNo, int amount, int loginMemberNo, Connection conn)
			throws Exception {
		int result = 0;

		try {

			String sql = prop.getProperty("insertOrder");
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, loginMemberNo);
			pstmt.setInt(2, productNo);
			pstmt.setInt(3, amount);
			result = pstmt.executeUpdate();

		} finally {

			close(pstmt);
		}

		return result;
	}

	public List<Order> getFirstOrder(int loginMemberNo,Connection conn) throws Exception {
		List<Order> oList = new ArrayList<Order>();
		try {

			String sql = prop.getProperty("getFirstOrder");

			pstmt = conn.prepareStatement(sql);
			pstmt .setInt(1, loginMemberNo);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				Order tmp = new Order();

				tmp.setOrderNo(rs.getInt("ORDER_NO"));
				tmp.setMemberNo(rs.getInt("MEMBER_NO"));
				tmp.setProductNo(rs.getInt("PRODUCT_NO"));
				tmp.setOrderAmount(rs.getInt("ORDER_AMOUNT"));
				tmp.setOrderStatusCode(rs.getInt("ORDER_STATUS_CD"));

				// 상품정보
				tmp.setProductName(rs.getString("PRODUCT_NM"));
				tmp.setProductPrice(rs.getInt("PRODUCT_PRICE"));
				tmp.setProductDiscount(rs.getDouble("DISCOUNT"));
				tmp.setThumnailImgPath(rs.getString("PRODUCT_IMG_PATH"));
				tmp.setThumnailImgName(rs.getString("PRODUCT_IMG_NM"));

				oList.add(tmp);

			}

		} finally {
			close(rs);

			close(pstmt);
		}

		return oList;
	}

}
