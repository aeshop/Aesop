<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/views/common/r_header.jsp"/>

    <!-------------------------------------------------- 낙희 --------------------------------->
    <!-- main 1페이지 -->
    <!-- content -->
    <div class="content">
      <div id="my-grade-info">
          <div>
              <p id="my-grade-descript">
                  <img src="${contextPath}/resources/images/cnh/images/img_member_default.gif">
              </p>
              <div id="my-grade-message">
                  <p class="grade-mesage" >저희 쇼핑몰을 이용해 주셔서 감사합니다. 
                  <strong>
                      <span>${sessionScope.loginMember.memberName }</span> <!-- ajax -->
                  </strong>
                  님은
                  <strong>
                      [
                      <span>${sessionScope.loginMember.memberGradeName }</span> <!-- ajax -->
                      ]
                  </strong>
                  회원이십니다.
                  </p>
                  <p class="grade-mesage">
                      <strong>
                          <span> 10000원 이상</span> <!-- ajax -->
                      </strong>
                      구매시
                      <strong>
                          <span>${sessionScope.loginMember.memberGradeDiscount}</span> <!-- ajax -->
                          %
                      </strong>
                      을 추가적립 받으실 수 있습니다.
                  </p>
              </div>
          </div>
      </div>
      <form method="post" action="#" id="update_form" name="update_form">
          <div id="user-info">
              <p class="info-title">기본정보</p>
              <p id="required-info">
                  <img src="${contextPath}/resources/images/cnh/images/icon_required.gif">필수입력사항
              </p>
              <div id="user-info-form">
                  <table  style="border-collapse : collapse; ">
                      <colgroup>
                          <col style="width:150px;">
                          <col style="width:1050px;">
                      </colgroup>
                      <tbody>
                          <tr>
                              <th>
                                  아이디
                                  <img src="${contextPath}/resources/images/cnh/images/icon_required.gif">
                              </th>
                              <td>
                                  <input type="text" value="${sessionScope.loginMember.memberId }">
                                  (영문소문자/숫자, 4~16자)
                              </td>
                          </tr>
                          <tr>
                              <th>
                                  비밀번호
                                  <img src="${contextPath}/resources/images/cnh/images/icon_required.gif">
                              </th>
                              <td>
                                  <input type="password" value="${sessionScope.loginMember.memberPw }">
                                  (영문 대소문자/숫자/특수문자 중 2가지 이상 조합, 8자~16자)
                              </td>
                          </tr>
                          <tr>
                              <th>
                                  비밀번호 확인
                                  <img src="${contextPath}/resources/images/cnh/images/icon_required.gif">
                              </th>
                              <td>
                                  <input type="password" value="${sessionScope.loginMember.memberPw }">
                                  <span id="pwConfirmMsg"></span>
                              </td>
                          </tr>
                          <tr>
                              <th>
                                  이름
                                  <img src="${contextPath}/resources/images/cnh/images/icon_required.gif">
                              </th>
                              <td>
                                  <input type="text" value="${sessionScope.loginMember.memberName}">
                              </td>
                          </tr>
                          <tr>
                              <th>
                                  주소
                              </th>
                              <td>
                                  <input type="text" id="sample6_postcode" placeholder="우편번호">
                                  <input type="button" class="postcode-btn" onclick="sample6_execDaumPostcode()" value="우편번호"><br>
                                  <input type="text" id="sample6_address" placeholder="주소"><br>
                                  <input type="text" id="sample6_detailAddress" placeholder="상세주소">
                              </td>
                          </tr>
                          <tr>
                              <th>
                                  휴대전화
                                  <img src="${contextPath}/resources/images/cnh/images/icon_required.gif">
                              </th>
                              <td>
                                  <select id="phone1">
                                      <option value="010">010</option>
                                      <option value="011">011</option>
                                      <option value="016">016</option>
                                      <option value="017">017</option>
                                      <option value="018">018</option>
                                      <option value="019">019</option>
                                  </select>
                                  -
                                  <input type="text" id="phone2" maxlength="4">
                                  -
                                  <input type="text" id="phone3" maxlength="4">
                              </td>
                          </tr>
                          <tr>
                              <th>
                                  이메일
                                  <img src="${contextPath}/resources/images/cnh/images/icon_required.gif">
                              </th>
                              <td>
                                  <input type="email" value="${sessionScope.loginMember.memberEmail}">
                              </td>
                          </tr>
                      </tbody>
                  </table>
                  <p class="info-title">추가정보</p>
                  <div id="addInfoInput">
                      <table  style="border-collapse: collapse;">
                          <colgroup>
                              <col style="width:150px;">
                              <col style="width:1050px;">
                          </colgroup>
                          <tbody>
                              <tr>
                                  <th>
                                      생년월일
                                      <img src="${contextPath}/resources/images/cnh/images/icon_required.gif">
                                  </th>
                                  <td>
                                      <input type="text" id="birth_year" value="">년 <!-- value에 loginMember에 담긴 생년월일 불러오기-->
                                      <input type="text" id="birth_month" value="">월
                                      <input type="text" id="birth_day" value="">일
                                  </td>
                              </tr>
                          </tbody>
                      </table>
                  </div>
              </div>
          </div>
          <div class="editBtnBox">
              <a href="#none">
                  <img src="${contextPath}/resources/images/cnh/images/btn_modify_member.gif">
              </a>
              <a href="#none">
                  <img src="${contextPath}/resources/images/cnh/images/btn_modify_cancel.gif">
              </a>
              <span>
                  <a href="#none">
                      <img src="${contextPath}/resources/images/cnh/images/btn_modify_out.gif">
                  </a>
              </span>
          </div>
      </form>

  </div> <!--content end-->

  <!-- footer include -->
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>	
</body>
</html>