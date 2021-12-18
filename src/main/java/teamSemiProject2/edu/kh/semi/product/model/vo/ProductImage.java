package teamSemiProject2.edu.kh.semi.product.model.vo;

public class ProductImage {
private int imgNo;
private String imgPath;
private String imgName;

private int imgLevel;
private int boardNo;

public ProductImage() {
	super();

}

public ProductImage(int imgNo, String imgPath, String imgName, int imgLevel, int boardNo) {
	super();
	this.imgNo = imgNo;
	this.imgPath = imgPath;
	this.imgName = imgName;

	this.imgLevel = imgLevel;
	this.boardNo = boardNo;
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

public int getBoardNo() {
	return boardNo;
}

public void setBoardNo(int boardNo) {
	this.boardNo = boardNo;
}

@Override
public String toString() {
	return "BoardImage [imgNo=" + imgNo + ", imgPath=" + imgPath + ", imgName=" + imgName +   ", imgLevel=" + imgLevel + ", boardNo=" + boardNo + "]";
}



}
