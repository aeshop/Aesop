package teamSemiProject2.edu.kh.semi.product.model.service;

import static teamSemiProject2.edu.kh.semi.common.JDBCTemplate.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import teamSemiProject2.edu.kh.semi.category.model.vo.Category;
import teamSemiProject2.edu.kh.semi.member.model.vo.Address;
import teamSemiProject2.edu.kh.semi.order.model.dao.OrderDAO;
import teamSemiProject2.edu.kh.semi.order.model.vo.Order;
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

	public Product getProductDetail(int productNo) throws Exception{

		
		Product result = null;
		conn = getConnection();
		
		result = dao.getProduct(productNo,conn);
		
		result.setImgList(dao.getProductImg(productNo,conn)); 
		
		close(conn);
		

		return result;
	}

	public int addCart(int productNo, int amount, int loginMemberNo) throws Exception {

		int result = 0;
		
		conn = getConnection();
		
		
		result = dao.addCart(productNo,amount,loginMemberNo,conn);
		
		if(result>0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		
		
		close(conn);
		
		
		return result;
		
		
		
		
	}

	/** 가장 최근의 내 주문(목록형식)  + 기본주소 들고오기  
	 * @param productNo
	 * @param amount
	 * @param loginMemberNo
	 * @return order정보 1개 담긴 리스트 : 결제 페이지로 반환
	 * @throws Exception
	 */
	public Map<String,Object> buyNow(int loginMemberNo) throws Exception{
		Map<String,Object> resultMap = new HashMap<String,Object>();
		
		try {
			
	
		
		conn = getConnection();
		

		List<Order> oList = getFirstOrder(loginMemberNo,conn);
		
		Address defaultAddress = new OrderDAO().getPrimaryAddress(loginMemberNo,conn);
		resultMap.put("orderList", oList);
		resultMap.put("defaultAddress", defaultAddress);
		
		
		} finally {
			close(conn);
		}
		
		
		
		return resultMap;
	}

	private List<Order> getFirstOrder(int loginMemberNo,Connection conn) throws Exception {
		List<Order> oList = null;
		

		oList=dao.getFirstOrder(loginMemberNo,conn);
		
		
		
		return oList;
	}
	
	

}
