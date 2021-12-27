package teamSemiProject2.edu.kh.semi.member.model.vo;

import java.sql.Date;

/**
 * @author nyr
 *
 */
public class OrderList {

	private int orderNo;
	private int productNo;
	private Date deliveryDt;
	private String productImgPath;
	private String productImgNm;
	private String productName;
	private int orderAmount;
	private String deliveryNo;
	private int productPrice;
	private double productDiscount;
	private String orderStatusName;
	
	
	
	
	
	
	public int getOrderNo() {
		return orderNo;
	}


	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}


	public int getProductNo() {
		return productNo;
	}


	public void setProductNo(int productNo) {
		this.productNo = productNo;
	}
	
	


	public String getProductImgNm() {
		return productImgNm;
	}


	public void setProductImgNm(String productImgNm) {
		this.productImgNm = productImgNm;
	}


	public OrderList() {
	}


	public Date getDeliveryDt() {
		return deliveryDt;
	}


	public void setDeliveryDt(Date deliveryDt) {
		this.deliveryDt = deliveryDt;
	}




	public String getProductImgPath() {
		return productImgPath;
	}




	public void setProductImgPath(String productImgPath) {
		this.productImgPath = productImgPath;
	}




	public String getProductName() {
		return productName;
	}




	public void setProductName(String productName) {
		this.productName = productName;
	}




	public int getOrderAmount() {
		return orderAmount;
	}




	public void setOrderAmount(int orderAmount) {
		this.orderAmount = orderAmount;
	}




	public String getDeliveryNo() {
		return deliveryNo;
	}




	public void setDeliveryNo(String deliveryNo) {
		this.deliveryNo = deliveryNo;
	}




	public int getProductPrice() {
		return productPrice;
	}




	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}




	public String getOrderStatusName() {
		return orderStatusName;
	}




	public void setOrderStatusName(String orderStatusName) {
		this.orderStatusName = orderStatusName;
	}


	

	public double getProductDiscount() {
		return productDiscount;
	}


	public void setProductDiscount(double productDiscount) {
		this.productDiscount = productDiscount;
	}


	@Override
	public String toString() {
		return "OrderList [deliveryDt=" + deliveryDt + ", productImgPath=" + productImgPath + ", productName="
				+ productName + ", orderAmount=" + orderAmount + ", deliveryNo=" + deliveryNo + ", productPrice="
				+ productPrice + ", orderStatusName=" + orderStatusName + "]";
	}

	
	
	
}
