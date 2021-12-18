package teamSemiProject2.edu.kh.semi.category.model.service;

import static teamSemiProject2.edu.kh.semi.common.JDBCTemplate.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import teamSemiProject2.edu.kh.semi.category.model.dao.CategoryDAO;
import teamSemiProject2.edu.kh.semi.category.model.vo.Category;
import teamSemiProject2.edu.kh.semi.product.model.vo.Product;
import teamSemiProject2.edu.kh.semi.product.model.vo.ProductImage;

public class CategoryService {
	Connection conn;

	CategoryDAO dao = new CategoryDAO();

	public List<Category> getCategory() throws SQLException {

		conn = getConnection();

		List<Category> category = dao.getCategory(conn);

		close(conn);
		return category;
	}

	public List<Product> getProduct() throws Exception {
		conn = getConnection();

		List<Product> pList = null;

		pList = dao.getProduct(conn);

		
		for (Product product : pList) {
			List<ProductImage> imgList = dao.getProductImageList(product.getProductNo(),conn);
			
			product.setImgList(imgList);
		}
		
		
		close(conn);

		return pList;
	}

}
