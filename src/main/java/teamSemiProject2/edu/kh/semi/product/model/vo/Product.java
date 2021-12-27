package teamSemiProject2.edu.kh.semi.product.model.vo;

import java.util.List;

/**
 * @author nyr
 *
 */
public class Product {

	private int productNo;
	private String productName;
	private int price;
	private double discount;
	private int stock;
	private int categoryNo;
	private int statusNo;

	//카테고리
	private String categoryName;
	private String statusName;
	
	//별점
	private String scoreAvg;
	private String scoreCount;
	
	//이미지리스트
	private List<ProductImage> imgList;
	
	


	public Product() {
		super();
	}


	public int getProductNo() {
		return productNo;
	}



	public void setProductNo(int productNo) {
		this.productNo = productNo;
	}



	public String getProductName() {
		return productName;
	}



	public void setProductName(String productName) {
		this.productName = productName;
	}



	public int getPrice() {
		return price;
	}



	public void setPrice(int price) {
		this.price = price;
	}



	public double getDiscount() {
		return discount;
	}



	public void setDiscount(double discount) {
		this.discount = discount;
	}



	public int getStock() {
		return stock;
	}



	public void setStock(int stock) {
		this.stock = stock;
	}



	public int getCategoryNo() {
		return categoryNo;
	}



	public void setCategoryNo(int categoryNo) {
		this.categoryNo = categoryNo;
	}



	public int getStatusNo() {
		return statusNo;
	}



	public void setStatusNo(int statusNo) {
		this.statusNo = statusNo;
	}



	public String getCategoryName() {
		return categoryName;
	}



	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}



	public String getStatusName() {
		return statusName;
	}



	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	
	public List<ProductImage> getImgList() {
		return imgList;
	}


	public void setImgList(List<ProductImage> imgList) {
		this.imgList = imgList;
	}

	

	public String getScoreAvg() {
		return scoreAvg;
	}


	public void setScoreAvg(String scoreAvg) {
		this.scoreAvg = scoreAvg;
	}


	public String getScoreCount() {
		return scoreCount;
	}


	public void setScoreCount(String scoreCount) {
		this.scoreCount = scoreCount;
	}


	@Override
	public String toString() {
		return "Product [productNo=" + productNo + ", productName=" + productName + ", price=" + price + ", discount="
				+ discount + ", stock=" + stock + ", categoryNo=" + categoryNo + ", statusNo=" + statusNo
				+ ", categoryName=" + categoryName + ", statusName=" + statusName + ", scoreAvg=" + scoreAvg
				+ ", scoreCount=" + scoreCount + ", imgList=" + imgList + "]";
	}




	
	
	
	
}
