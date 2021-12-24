package teamSemiProject2.edu.kh.semi.member.model.vo;

public class Grade {
	
	private int memberGradeNo;
	private String memberGradeName;
	private double memberGradeDiscount;
	private int memberPurchaseAmount;
	
	private String leftMoney;
	
	private int totalPrice;
	
	

	public int getMemberGradeNo() {
		return memberGradeNo;
	}

	public void setMemberGradeNo(int memberGradeNo) {
		this.memberGradeNo = memberGradeNo;
	}

	public String getMemberGradeName() {
		return memberGradeName;
	}

	public void setMemberGradeName(String memberGradeName) {
		this.memberGradeName = memberGradeName;
	}

	public double getMemberGradeDiscount() {
		return memberGradeDiscount;
	}

	public void setMemberGradeDiscount(double memberGradeDiscount) {
		this.memberGradeDiscount = memberGradeDiscount;
	}

	public int getMemberPurchaseAmount() {
		return memberPurchaseAmount;
	}

	public void setMemberPurchaseAmount(int memberPurchaseAmount) {
		this.memberPurchaseAmount = memberPurchaseAmount;
	}

	public String getLeftMoney() {
		return leftMoney;
	}

	public void setLeftMoney(String leftMoney) {
		this.leftMoney = leftMoney;
	}

	
	
	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Override
	public String toString() {
		return "Grade [memberGradeNo=" + memberGradeNo + ", memberGradeName=" + memberGradeName
				+ ", memberGradeDiscount=" + memberGradeDiscount + ", memberPurchaseAmount=" + memberPurchaseAmount
				+ ", leftMoney=" + leftMoney + ", totalPrice=" + totalPrice + "]";
	}

	
	
	
	
	
	

}
