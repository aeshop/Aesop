package teamSemiProject2.edu.kh.semi.delivery.model.vo;

public class Delivery {

	
	
	private String deliveryNo;
	private int memberNo;
	private String zipCode;
	private String address1;
	private String address2;
	private String receiverName;
	private String receiverPhone;
	private String deliveryDate;
	private int deliveryStatusCode;
	private long deliveryPrice;
	private String deliveryMessage;

	
	public Delivery() {
		super();

	}
	public String getDeliveryNo() {
		return deliveryNo;
	}
	public void setDeliveryNo(String deliveryNo) {
		this.deliveryNo = deliveryNo;
	}
	public int getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getReceiverName() {
		return receiverName;
	}
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
	public String getReceiverPhone() {
		return receiverPhone;
	}
	public void setReceiverPhone(String receiverPhone) {
		this.receiverPhone = receiverPhone;
	}
	public String getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	public int getDeliveryStatusCode() {
		return deliveryStatusCode;
	}
	public void setDeliveryStatusCode(int deliveryStatusCode) {
		this.deliveryStatusCode = deliveryStatusCode;
	}
	public long getDeliveryPrice() {
		return deliveryPrice;
	}
	public void setDeliveryPrice(long deliveryPrice) {
		this.deliveryPrice = deliveryPrice;
	}
	public String getDeliveryMessage() {
		return deliveryMessage;
	}
	public void setDeliveryMessage(String deliveryMessage) {
		this.deliveryMessage = deliveryMessage;
	}
	@Override
	public String toString() {
		return "Delivery [deliveryNo=" + deliveryNo + ", memberNo=" + memberNo + ", zipCode=" + zipCode + ", address1="
				+ address1 + ", address2=" + address2 + ", receiverName=" + receiverName + ", receiverPhone="
				+ receiverPhone + ", deliveryDate=" + deliveryDate + ", deliveryStatusCode=" + deliveryStatusCode
				+ ", deliveryPrice=" + deliveryPrice + ", deliveryMessage=" + deliveryMessage + "]";
	}
	
	
	
	
	
	
	
}
