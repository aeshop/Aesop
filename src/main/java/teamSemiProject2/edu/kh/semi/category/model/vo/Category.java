package teamSemiProject2.edu.kh.semi.category.model.vo;

public class Category {

	private int categoryNo1;
	private int categoryNo2;

	private String categoryName;
	private int currentCategoryNo;
	
	public Category() {
		super();

	}
	public int getCategoryNo1() {
		return categoryNo1;
	}
	public void setCategoryNo1(int categoryNo1) {
		this.categoryNo1 = categoryNo1;
	}
	public int getCategoryNo2() {
		return categoryNo2;
	}
	public void setCategoryNo2(int categoryNo2) {
		this.categoryNo2 = categoryNo2;
	}
	
	public Category(int categoryNo1, String categoryName) {
		super();
		this.categoryNo1 = categoryNo1;
		this.categoryName = categoryName;
	}
	
	
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public int getCurrentCategoryNo() {
		return currentCategoryNo;
	}
	public void setCurrentCategoryNo(int currentCategoryNo) {
		this.currentCategoryNo = currentCategoryNo;
	}
	
	
}
