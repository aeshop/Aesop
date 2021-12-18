package teamSemiProject2.edu.kh.semi.product.model.service;

import static teamSemiProject2.edu.kh.semi.common.JDBCTemplate.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import teamSemiProject2.edu.kh.semi.category.model.vo.Category;
import teamSemiProject2.edu.kh.semi.product.model.dao.ProductDAO;

public class ProductService {
	Connection conn;

	ProductDAO dao = new ProductDAO();
	
	public List<Category> getCategory() throws SQLException {
		
		conn= getConnection();
		
		List< Category> category =dao.getCategory(conn);

		close(conn);
		return category;
	}
	
	

}
