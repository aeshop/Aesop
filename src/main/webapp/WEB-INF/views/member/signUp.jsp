<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="${contextPath}/resources/css/member/member.css">
<jsp:include page="../common/r_header.jsp" />

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
      <div style="border: 1px solid black; height: 48px;">
        인증 가능한걸로 해볼게요
      </div>
      <div class="titleStep">
        <span style="border-bottom: 1px solid #000;">Step 2</span>
      </div>
      <div class="input_wrap">
        <div class="signup_input">
          <input id="id" name="id" placeholder="아이디" type="text" autocomplete="off" required>
          <input type="hidden" name="idDup" id="idDup" value="false">
          <span id="checkId" class="validity-msg">.</span>
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
          <span id="checkEmail" class="validity-msg"></span>
        </div>
      </div>

      <div class="input_wrap2">
        <div class="signup_input">
          <input id="name" name="name" type="text" style="background-color: rgb(238 238 238);" required>
          <span id="checkName" class="validity-msg"></span>
        </div>
        <div class="signup_input">
          <input id="birthday" name="birthday" type="date" required>
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
            <input id="phone2" name="phone" maxlength="4" type="text" style=" width: 35%; border: none;" required>
            -
            <input id="phone3" name="phone" maxlength="4" type="text" style="width: 35%; border: none;" required>
        </div>
            <span id="checkPhone" class="validity-msg" style="border: none;"></span>
      </div>
      <button type="submit" id="signup_submit">가입하기</button>
    </form>
  </div>

<!-- footer -->
<jsp:include page="../common/r_footer.jsp" />
<script src="${contextPath}/resources/js/member/member.js"></script>


</body>
</html>