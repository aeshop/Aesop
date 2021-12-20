package teamSemiProject2.edu.kh.semi.product.model.service;

import static teamSemiProject2.edu.kh.semi.common.JDBCTemplate.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import teamSemiProject2.edu.kh.semi.category.model.vo.Category;
import teamSemiProject2.edu.kh.semi.product.model.dao.ProductDAO;
import teamSemiProject2.edu.kh.semi.product.model.vo.Product;
import teamSemiProject2.edu.kh.semi.product.model.vo.ProductImage;

public class ProductService {
	Connection conn;

	ProductDAO dao = new ProductDAO();
	
	public List<Category> getCategory() throws SQLException {
		
		conn= getConnection();
		
		List< Category> category =dao.getCategory(conn);

		close(conn);
		return category;
	}

	public int addProduct(Product prod, List<ProductImage> imgList) throws Exception {
		int result = 0;
		
		conn=getConnection();
		
		//1)번호표: 새로운 product와 이미지가 들어갈 번호를 얻어옴
		int productNum = dao.nextProductNum(conn);

		//2) 얻어온 productNo를 객체에 집어넣음
		prod.setProductNo(productNum);

		
//		 2.2)product테이블에 insert
		result = dao.insertProduct(prod,conn);

		//3) 정상적으로 insert 되면 다음으로 product_img 테이블에 이미지 경로정보 삽입
		if(result>0) {
			

			for (ProductImage img : imgList) {
				result =0;
				img.setProductNo(productNum);				
				result = dao.insertImg(img,conn);
				
				if(result==0) {
					rollback(conn);
					break;
				}
				
			}
			
			if(result>0) {
				commit(conn);
				
			} else {
				rollback(conn);
			}
			
			
		} else {
			rollback(conn);
		}
		
	
		
		
		
		
		
		return result;
	}
	
	

}
