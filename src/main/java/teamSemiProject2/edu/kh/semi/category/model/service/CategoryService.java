package teamSemiProject2.edu.kh.semi.category.model.service;

import static teamSemiProject2.edu.kh.semi.common.JDBCTemplate.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import teamSemiProject2.edu.kh.semi.category.model.dao.CategoryDAO;
import teamSemiProject2.edu.kh.semi.category.model.vo.Category;
import teamSemiProject2.edu.kh.semi.category.model.vo.Pagination;
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



	public Pagination getPagination(int cp) throws Exception {
		conn = getConnection();

		int productCount = dao.getPageCount(conn);
		
		Pagination pagination = new Pagination(productCount, cp);
		
		
		
		close(conn);
		return pagination;
	}
	
	public Pagination getPagination(int cp, int categoryNo) throws Exception {
		conn = getConnection();

		int productCount = dao.getPageCount(categoryNo,conn);
		
		Pagination pagination = new Pagination(productCount, cp);
		
		
		
		close(conn);
		return pagination;
	}
	
	

	public Pagination getPagination(int cp, int categoryNo, String keyword) throws Exception{
		conn = getConnection();

		int productCount = dao.getPageCount(categoryNo,conn,keyword);
		
		Pagination pagination = new Pagination(productCount, cp);
		
		
		
		close(conn);
		return pagination;
	}
	
	
	

	public List<Product> getProduct(Pagination pagination,int categoryNo) throws Exception {
		conn = getConnection();

		List<Product> pList = null;

		pList = dao.getProduct(pagination,categoryNo,conn);

		//제품이 가진 이미지 경로정보를 제품 클래스에 추가
		for (Product product : pList) {
			List<ProductImage> imgList = dao.getProductImageList(product.getProductNo(),conn);
			
			product.setImgList(imgList);
		}
		
		
		close(conn);

		return pList;
	}



	public List<Product> getProduct(Pagination pagination, int categoryNo, int sortMethod) throws Exception {
		conn = getConnection();

		List<Product> pList = null;

		pList = dao.getProduct(pagination,categoryNo,sortMethod,conn);

		//제품이 가진 이미지 경로정보를 제품 클래스에 추가
		for (Product product : pList) {
			List<ProductImage> imgList = dao.getProductImageList(product.getProductNo(),conn);
			
			product.setImgList(imgList);
		}
		
		
		close(conn);

		return pList;
	}



	public List<Product> searchKeyword(Pagination pagination,int categoryNo,int sortMethod,String keyword) throws Exception {
		conn = getConnection();
		
		List<Product> pList = dao.searchKeyword(pagination,categoryNo,sortMethod,keyword,conn);
		
		
		
		//제품이 가진 이미지 경로정보를 제품 클래스에 추가
		for (Product product : pList) {
			List<ProductImage> imgList = dao.getProductImageList(product.getProductNo(),conn);
			
			product.setImgList(imgList);
		}
		
		
		
		close(conn);
		
		
		return pList;
	}








}
