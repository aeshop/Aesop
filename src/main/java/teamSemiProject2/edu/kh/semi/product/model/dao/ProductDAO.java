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

}
