package teamSemiProject2.edu.kh.semi.category.model.vo;

public class Category {

	private int categoryNo;
	private String categoryName;
	public Category() {
		super();

	}
	public Category(int categoryNumber, String categoryName) {
		super();
		this.categoryNo = categoryNumber;
		this.categoryName = categoryName;
	}
	public int getCategoryNo() {
		return categoryNo;
	}
	public void setCategoryNo(int categoryNumber) {
		this.categoryNo = categoryNumber;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	
}
