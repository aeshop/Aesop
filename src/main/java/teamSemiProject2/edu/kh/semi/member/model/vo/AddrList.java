package teamSemiProject2.edu.kh.semi.member.model.vo;

public class AddrList {
	
	private int addrNo;
	private String addrName;
	private String zipCode;
	private String address1;
	private String address2;
	private String defaultAddress;
	private int memberNo;
	private String addrPhone;
	private String addrReceiverName;
	
	public AddrList() {
	}

	public int getAddrNo() {
		return addrNo;
	}

	public void setAddrNo(int addrNo) {
		this.addrNo = addrNo;
	}

	public String getAddrName() {
		return addrName;
	}

	public void setAddrName(String addrName) {
		this.addrName = addrName;
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

	public String getDefaultAddress() {
		return defaultAddress;
	}

	public void setDefaultAddress(String defaultAddress) {
		this.defaultAddress = defaultAddress;
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

	public String getAddrReceiverName() {
		return addrReceiverName;
	}

	public void setAddrReceiverName(String addrReceiverName) {
		this.addrReceiverName = addrReceiverName;
	}

	@Override
	public String toString() {
		return "AddrList [addrNo=" + addrNo + ", addrName=" + addrName + ", zipCode=" + zipCode + ", address1="
				+ address1 + ", address2=" + address2 + ", defaultAddress=" + defaultAddress + ", memberNo=" + memberNo
				+ ", addrPhone=" + addrPhone + ", addrReceiverName=" + addrReceiverName + "]";
	}
	
	
	
	
}
