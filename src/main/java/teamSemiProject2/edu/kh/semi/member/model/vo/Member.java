package teamSemiProject2.edu.kh.semi.member.model.vo;

import java.sql.Date;

public class Member {
<<<<<<< HEAD
   private int memberNo;
   private String memberId;
   private String memberPw;
   private String memberEmail;
   private String memberName;
   private int memberBirthday;
   private String memberPhone;
   private Date enrollDate;
   private int statusCode;
   private int gradeCode;
   
   public Member() {}
   
   
   // 회원가입 생성자
   public Member(int memberNo, String memberId, String memberEmail, String memberName, int memberBirthday,
         String memberPhone, Date enrollDate, int statusCode, int gradeCode) {
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

   public int getMemberBirthday() {
      return memberBirthday;
   }

   public void setMemberBirthday(int memberBirthday) {
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

   @Override
   public String toString() {
      return "Member [memberNo=" + memberNo + ", memberId=" + memberId + ", memberPw=" + memberPw + ", memberEmail="
            + memberEmail + ", memberName=" + memberName + ", memberBirthday=" + memberBirthday + ", memberPhone="
            + memberPhone + ", enrollDate=" + enrollDate + ", statusCode=" + statusCode + ", gradeCode=" + gradeCode
            + "]";
   };
   
   
   
=======
	private int memberNo;
	private String memberId;
	private String memberPw;
	private String memberEmail;
	private String memberName;
	private int memberBirthday;
	private String memberPhone;
	private Date enrollDate;
	private int statusCode;
	private int gradeCode;
	
	public Member() {}
	
	
	// 회원가입 생성자
	public Member(int memberNo, String memberId, String memberEmail, String memberName, int memberBirthday,
			String memberPhone, Date enrollDate, int statusCode, int gradeCode) {
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

	public int getMemberBirthday() {
		return memberBirthday;
	}

	public void setMemberBirthday(int memberBirthday) {
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

	@Override
	public String toString() {
		return "Member [memberNo=" + memberNo + ", memberId=" + memberId + ", memberPw=" + memberPw + ", memberEmail="
				+ memberEmail + ", memberName=" + memberName + ", memberBirthday=" + memberBirthday + ", memberPhone="
				+ memberPhone + ", enrollDate=" + enrollDate + ", statusCode=" + statusCode + ", gradeCode=" + gradeCode
				+ "]";
	};
	
	
	
>>>>>>> CNH
}
