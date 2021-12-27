<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link rel="stylesheet" href="${contextPath}/resources/css/member/member.css">
<jsp:include page="../common/r_header.jsp" />
	<jsp:include page="../common/sidebar_n.jsp" />

<div class="signup_wrap">

    <form class="signup_form" method="post" name="signUpForm" action="${contextPath}/member/signup" onsubmit="return Validate();">
      <h2>회원가입</h2>
      <div class="join_step">
        <span class="join_active"></span>
        <span></span>
      </div>
      
      <div class="titleStep">
        <span style="border-bottom: 1px solid #000;">Step 1</span>
      </div>
      <div class="input_wrap">
      
        <div class="signup_input">
          <input id="id" name="id" placeholder="아이디" type="text" autocomplete="off" required>
          <span id="checkId" class="validity-msg"></span>
        </div>
        
        <div class="signup_input">
          <input id="pwd1" name="pwd1" placeholder="비밀번호" type="password" required>
          <span id="checkPwd1" class="validity-msg"></span>
        </div>
        
        <div class="signup_input">
          <input id="pwd2" name="pwd2" placeholder="비밀번호 확인" type="password" required>
          <span id="checkPwd2" class="validity-msg"></span>
        </div>
        
        <div class="signup_input">
          <input id="email" name="email" placeholder="이메일" type="email" autocomplete="off" required>
          <button type="button" id="emailConfirm_btn" onclick="emailConfirm_check();" style= "width:110px; height: 40px; display:none; 
          background-color: rgb(146 26 255); color:#fff; font-weight:bold;">이메일 인증</button>
          <input id="emailConfirm" name="emailConfirm" placeholder="이메일 인증번호를 입력해주세요." type="password" autocomplete="off" style= "width:110px; height: 40px; display:none;" required>
          <span id="checkEmail" class="validity-msg"></span>
          <span id="checkEmail2" class="validity-msg"></span>
        </div>
      </div>

      <div class="input_wrap2">
        <div class="signup_input">
          <input id="name" name="name" type="text" placeholder="이름" required>
          <span id="checkName" class="validity-msg"></span>
        </div>
        <div class="signup_input">
          <input id="birthday" name="birthday" type="date" min="1921-01-01" max="2020-12-31" required>
          <span id="checkBirthday" class="validity-msg"></span>
        </div>
        <div class="signup_input signup_input_arr" style="border: 1px solid rgb(201 201 201);">
          <select id="phone1" name="phone" style="width: 20%; font-size: 19px; border: none;">
              <option value="010">010</option>
              <option value="011">011</option>
              <option value="016">016</option>
              <option value="017">017</option>
            </select>
            -
            <input id="phone2" name="phone" class="phone" maxlength="4" type="number" style=" width: 35%; border: none;" required>
            -
            <input id="phone3" name="phone" class="phone" maxlength="4" type="number" style="width: 35%; border: none;" required>
            <span id="checkPhone" class="validity-msg" style="border: none;"></span>
        </div>
        <div style="border:none; margin-top: 15px;">
          <div class="agr_item chk_neo" style="text-align: start;">
            <label class="label_chk" style="color: #921aff;">
              <input id="age14" name="age14" value="1" type="checkbox" required> 만14세 이상입니다(필수) </label>
          </div>
          <div class="agr_item chk_neo " style="text-align: start;">
            <input id="agree_service_check0" name="agree_service_check[] " class="ec-base-chk" value="1" type="checkbox"
              required>
            <label for="agree_service_check0" class="label_chk">서비스 이용약관(필수)</label>
            <!-- <div class="more" id="more_agr1">전체보기</div> -->
          </div>
          <div class="agr_item chk_neo " style="text-align: start;">
            <input id="agree_privacy_check0" name="agree_privacy_check[]" class="ec-base-chk" value="1" type="checkbox"
              required>
            <label for="agree_privacy_check0" class="label_chk">개인정보 수집 및 이용 동의(필수)</label>
          </div>
        </div>
      </div>
      <button type="submit" id="signup_submit">가입하기</button>
    </form>
  </div>

<!-- footer -->
<jsp:include page="../common/r_footer.jsp" />
<script src="${contextPath}/resources/js/member/member.js"></script>

<c:if test="${ !empty sessionScope.message }">
	<script>
	$(function(){
		alert("${message}");
	})
	</script>

	<%-- message 1회 출력 후 session에서 제거 --%>
	<c:remove var="message" scope="session" />

</c:if>

</body>
</html>