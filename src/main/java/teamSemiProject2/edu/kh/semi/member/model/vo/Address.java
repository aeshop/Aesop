package teamSemiProject2.edu.kh.semi.member.model.vo;

/**
 * @author nyr
 *
 */
public class Address {

	private int addressNo;
	
	private String zipCode;
	
	private String addressName;
	private String address1;
	private String address2;
	private String isDefault;
	private int memberNo;
	private String addrPhone;
	private String receiverName;
	
	
	public Address() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getAddressNo() {
		return addressNo;
	}
	public void setAddressNo(int addressNo) {
		this.addressNo = addressNo;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getAddressName() {
		return addressName;
	}
	public void setAddressName(String addressName) {
		this.addressName = addressName;
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
	public String getIsDefault() {
		return isDefault;
	}
	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}
	public int getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}
	public String getAddrPhone() {
		return addrPhone;
	}
	public void setAddrPhone(String addrPhone) {
		this.addrPhone = addrPhone;
	}
	public String getReceiverName() {
		return receiverName;
	}
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
	@Override
	public String toString() {
		return "Address [addressNo=" + addressNo + ", zipCode=" + zipCode + ", addressName=" + addressName
				+ ", address1=" + address1 + ", address2=" + address2 + ", isDefault=" + isDefault + ", memberNo="
				+ memberNo + ", addrPhone=" + addrPhone + ", receiverName=" + receiverName + "]";
	}
	
	
	
	
	
}
