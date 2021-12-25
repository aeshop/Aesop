package teamSemiProject2.edu.kh.semi.order.model.vo;

public class Order {

	private int orderNo;
	private int memberNo;
	private int productNo;
	private int orderAmount;
	private int orderStatusCode;
	private int deliveryNo;
	
	//제품 가격
	private String productName;
	private int productPrice;
	private double productDiscount;
	//제품 수량
	private int productStock;
	//제품이미지 : 0번
	private String thumnailImgPath;
	private String thumnailImgName;
	
	private String memberName;
	private String memberGradeName;
	private double memberDiscount;

	
	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}
	public int getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}
	public int getProductNo() {
		return productNo;
	}
	public void setProductNo(int productNo) {
		this.productNo = productNo;
	}
	public int getOrderAmount() {
		return orderAmount;
	}
	public void setOrderAmount(int orderAmount) {
		this.orderAmount = orderAmount;
	}
	public int getOrderStatusCode() {
		return orderStatusCode;
	}
	public void setOrderStatusCode(int orderStatusCode) {
		this.orderStatusCode = orderStatusCode;
	}
	public int getDeliveryNo() {
		return deliveryNo;
	}
	public void setDeliveryNo(int deliveryNo) {
		this.deliveryNo = deliveryNo;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getMemberGradeName() {
		return memberGradeName;
	}
	public void setMemberGradeName(String memberGradeName) {
		this.memberGradeName = memberGradeName;
	}
	public double getMemberDiscount() {
		return memberDiscount;
	}
	public void setMemberDiscount(double memberDiscount) {
		this.memberDiscount = memberDiscount;
	}
	public String getThumnailImgPath() {
		return thumnailImgPath;
	}
	public void setThumnailImgPath(String thumnailImgPath) {
		this.thumnailImgPath = thumnailImgPath;
	}
	public String getThumnailImgName() {
		return thumnailImgName;
	}
	public void setThumnailImgName(String thumnailImgName) {
		this.thumnailImgName = thumnailImgName;
	}
	public int getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}
	public double getProductDiscount() {
		return productDiscount;
	}
	public void setProductDiscount(double productDiscount) {
		this.productDiscount = productDiscount;
	}
	
	
	public int getProductStock() {
		return productStock;
	}
	public void setProductStock(int productStock) {
		this.productStock = productStock;
	}
	@Override
	public String toString() {
		return "Order [orderNo=" + orderNo + ", memberNo=" + memberNo + ", productNo=" + productNo + ", orderAmount="
				+ orderAmount + ", orderStatusCode=" + orderStatusCode + ", deliveryNo=" + deliveryNo + ", productName="
				+ productName + ", productPrice=" + productPrice + ", productDiscount=" + productDiscount
				+ ", productStock=" + productStock + ", thumnailImgPath=" + thumnailImgPath + ", thumnailImgName="
				+ thumnailImgName + ", memberName=" + memberName + ", memberGradeName=" + memberGradeName
				+ ", memberDiscount=" + memberDiscount + "]";
	}




	
	
	
	
	
	
	
}
