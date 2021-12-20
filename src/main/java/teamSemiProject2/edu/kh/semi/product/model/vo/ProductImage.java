package teamSemiProject2.edu.kh.semi.product.model.vo;

public class ProductImage {
private int imgNo;
private String imgPath;
private String imgName;

private int imgLevel;
private int productNo;

public ProductImage() {
	super();

}

public ProductImage(int imgNo, String imgPath, String imgName, int imgLevel, int productNo) {
	super();
	this.imgNo = imgNo;
	this.imgPath = imgPath;
	this.imgName = imgName;

	this.imgLevel = imgLevel;
	this.productNo = productNo;
}

public int getImgNo() {
	return imgNo;
}

public void setImgNo(int imgNo) {
	this.imgNo = imgNo;
}

public String getImgPath() {
	return imgPath;
}

public void setImgPath(String imgPath) {
	this.imgPath = imgPath;
}

public String getImgName() {
	return imgName;
}

public void setImgName(String imgName) {
	this.imgName = imgName;
}



public int getImgLevel() {
	return imgLevel;
}

public void setImgLevel(int imgLevel) {
	this.imgLevel = imgLevel;
}

public int getProductNo() {
	return productNo;
}

public void setProductNo(int productNo) {
	this.productNo = productNo;
}

@Override
public String toString() {
	return "ProductImage [imgNo=" + imgNo + ", imgPath=" + imgPath + ", imgName=" + imgName + ", imgLevel=" + imgLevel
			+ ", productNo=" + productNo + "]";
}




}
