package teamSemiProject2.edu.kh.semi.product.model.vo;

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

	private String categoryName;
	private String statusName;
	
	
	
	public Product() {
		super();
	}



	public Product(int productNo, String productName, int price, double discount, int stock, int categoryNo,
			int statusNo, String categoryName, String statusName) {
		super();
		this.productNo = productNo;
		this.productName = productName;
		this.price = price;
		this.discount = discount;
		this.stock = stock;
		this.categoryNo = categoryNo;
		this.statusNo = statusNo;
		this.categoryName = categoryName;
		this.statusName = statusName;
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

	
	
	
	
}
