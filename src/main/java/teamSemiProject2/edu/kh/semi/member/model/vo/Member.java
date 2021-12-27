package teamSemiProject2.edu.kh.semi.member.model.vo;

import java.sql.Date;

public class Member {
   private int memberNo;
   private String memberId;
   private String memberPw;
   private String memberEmail;
   private String memberName;
   private String memberBirthday;
   private String memberPhone;
   private Date enrollDate;
   private int statusCode;
   private int gradeCode;

   private String memberGradeName;
   private Double memberGradeDiscount;
   //회원과 주소는 밀접한 관계를 가지고 있어서, 기본 주소 담을 자리는 만듬
   private Address defaultAddress;
   public Member() {
   }
   
   
   // 회원가입 
   public Member(String memberId, String memberPw, String memberEmail, String memberName, String memberBirthday,
         String memberPhone) {
      super();
      this.memberId = memberId;
      this.memberPw = memberPw;
      this.memberEmail = memberEmail;
      this.memberName = memberName;
      this.memberBirthday = memberBirthday;
      this.memberPhone = memberPhone;
   }
   
   


   // 아이디 찾기
   public Member( String memberEmail, String memberName) {
      super();
      this.memberEmail = memberEmail;
      this.memberName = memberName;
   }
   
   

   public Member(String memberId, String memberEmail, String memberName) {
	super();
	this.memberId = memberId;
	this.memberEmail = memberEmail;
	this.memberName = memberName;
}


// 회원 정보
   public Member(int memberNo, String memberId, String memberEmail, String memberName, String memberBirthday,
         String memberPhone, Date enrollDate, int statusCode, int gradeCode, String memberGradeName,
         Double memberGradeDiscount) {
      super();
      this.memberNo = memberNo;
      this.memberId = memberId;
      this.memberEmail = memberEmail;
      this.memberName = memberName;
      this.memberBirthday = memberBirthday;
      this.memberPhone = memberPhone;
      this.enrollDate = enrollDate;
      this.statusCode = statusCode;
      this.gradeCode = gradeCode;
      this.memberGradeName = memberGradeName;
      this.memberGradeDiscount = memberGradeDiscount;
   }

   public int getMemberNo() {
      return memberNo;
   }

   public void setMemberNo(int memberNo) {
      this.memberNo = memberNo;
   }

   public String getMemberId() {
      return memberId;
   }

   public void setMemberId(String memberId) {
      this.memberId = memberId;
   }

   public String getMemberPw() {
      return memberPw;
   }

   public void setMemberPw(String memberPw) {
      this.memberPw = memberPw;
   }

   public String getMemberEmail() {
      return memberEmail;
   }

   public void setMemberEmail(String memberEmail) {
      this.memberEmail = memberEmail;
   }

   public String getMemberName() {
      return memberName;
   }

   public void setMemberName(String memberName) {
      this.memberName = memberName;
   }

   public String getMemberBirthday() {
      return memberBirthday;
   }

   public void setMemberBirthday(String memberBirthday) {
      this.memberBirthday = memberBirthday;
   }

   public String getMemberPhone() {
      return memberPhone;
   }

   public void setMemberPhone(String memberPhone) {
      this.memberPhone = memberPhone;
   }

   public Date getEnrollDate() {
      return enrollDate;
   }

   public void setEnrollDate(Date enrollDate) {
      this.enrollDate = enrollDate;
   }

   public int getStatusCode() {
      return statusCode;
   }

   public void setStatusCode(int statusCode) {
      this.statusCode = statusCode;
   }

   public int getGradeCode() {
      return gradeCode;
   }

   public void setGradeCode(int gradeCode) {
      this.gradeCode = gradeCode;
   }

   public String getMemberGradeName() {
      return memberGradeName;
   }

   public void setMemberGradeName(String memberGradeName) {
      this.memberGradeName = memberGradeName;
   }

   public Double getMemberGradeDiscount() {
      return memberGradeDiscount;
   }

   public void setMemberGradeDiscount(Double memberGradeDiscount) {
      this.memberGradeDiscount = memberGradeDiscount;
   }

   
   
   public Address getDefaultAddress() {
	return defaultAddress;
}


public void setDefaultAddress(Address defaultAddress) {
	this.defaultAddress = defaultAddress;
}


@Override
   public String toString() {
      return "Member [memberNo=" + memberNo + ", memberId=" + memberId + ", memberPw=" + memberPw + ", memberEmail="
            + memberEmail + ", memberName=" + memberName + ", memberBirthday=" + memberBirthday + ", memberPhone="
            + memberPhone + ", enrollDate=" + enrollDate + ", statusCode=" + statusCode + ", gradeCode=" + gradeCode
            + ", memberGradeName=" + memberGradeName + ", memberGradeDiscount=" + memberGradeDiscount + "]";
   }

}