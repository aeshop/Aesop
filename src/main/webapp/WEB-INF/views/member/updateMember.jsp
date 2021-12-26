<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="/WEB-INF/views/common/r_header.jsp"/>
<jsp:include page="/WEB-INF/views/common/sidebar_n.jsp"/>

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
                      <span>${sessionScope.loginMember.memberName }</span> 
                  </strong>
                  님은
                  <strong>
                      [
                      <span>${sessionScope.loginMember.memberGradeName }</span> 
                      ]
                  </strong>
                  회원이십니다.
                  </p>
                  <p class="grade-mesage">
                      <strong>
	                      <c:choose>
				              	<c:when test="${empty sessionScope.grade.leftMoney}">
				              		${sessionScope.grade.memberPurchaseAmount}원
				              	</c:when>
				              	<c:otherwise>
				                  <span>${sessionScope.grade.leftMoney }원 </span>
				              	</c:otherwise>
			              </c:choose>
                      </strong>
                      추가 구매시
                      <strong>
                          <span><fmt:formatNumber value="${(1 - sessionScope.grade.memberGradeDiscount) *100}" pattern="#"/></span> 
                          %
                      </strong>
                      할인받으실 수 있습니다.
                  </p>
              </div>
          </div>
      </div>
      <form method="post" action="${contextPath}/myPage/updateMember" id="update_form" name="update_form">
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
                                <p>${sessionScope.loginMember.memberId}</p>
                             <%--세션스코프에서 아이디 받으면 되니 아이디는 제출에 필요x --%>
                              </td>
                          </tr>
                          <tr>
                              <th>
                                  비밀번호
                                  <img src="${contextPath}/resources/images/cnh/images/icon_required.gif" >
                              </th>
                              <td>
                                  <input type="password" name="pwd1" id="pwd1">
                                  (영문 대소문자/숫자/특수문자 중 2가지 이상 조합, 8자~16자)
                                  <span id="pw1ConfirmMsg"></span><%--비번검증 --%>                                  
                              </td>
                          </tr>
                          <tr>
                              <th>
                                  비밀번호 확인
                                  <img src="${contextPath}/resources/images/cnh/images/icon_required.gif" >
                              </th>
                              <td>
                                  <input type="password" name="pwd2"  id="pwd2">
                                  <span id="pw2ConfirmMsg"></span><%--비번검증 --%>
                              </td>
                          </tr>
                          <tr>
                              <th>
                                  이름
                                  <img src="${contextPath}/resources/images/cnh/images/icon_required.gif">
                              </th>
                              <td>
                                  <input type="text" value="${sessionScope.loginMember.memberName}" name="userName">
                              </td>
                          </tr>
                          <tr>
                              <th>
                                  주소
                              </th>
                              <td>
                              	<c:choose>
	                               	<c:when test="${empty sessionScope.defaultAddr}">
		                                  <input type="text" id="sample6_postcode" placeholder="우편번호" name="zipCode">
		                                  <input type="button" class="postcode-btn" onclick="sample6_execDaumPostcode()" value="우편번호"><br>
		                                  <input type="text" id="sample6_address"  name="address1" placeholder="주소"> (주소)<br>
		                                  <input type="text" id="sample6_detailAddress" name="address2" placeholder="상세주소"> (상세주소)
	                               	</c:when>
	                               	<c:otherwise>
	                               		  <input type="text" id="sample6_postcode" value="${sessionScope.defaultAddr.zipCode}" name="zipCode">
		                                  <input type="button" class="postcode-btn" onclick="sample6_execDaumPostcode()" value="우편번호"><br>
		                                  <input type="text" id="sample6_address" name="address1" placeholder="주소" value="${sessionScope.defaultAddr.address1}"> (주소)<br>
		                                  <input type="text" id="sample6_detailAddress" name="address2" placeholder="상세주소" value="${sessionScope.defaultAddr.address2}"> (상세주소)
	                               	</c:otherwise>
                               	</c:choose>
                              </td>
                          </tr>
                          <tr>
                              <th>
                              <!--  memberPhone을 "-" 기준으로 쪼갠 배열을 저장하는 변수 선언 -->
							<c:set var="ph" value="${fn:split(loginMember.memberPhone, '-')}"/>
                                  휴대전화
                                  <img src="${contextPath}/resources/images/cnh/images/icon_required.gif">
                              </th>
                              <td>
                                  <select id="phone1" name="phone">
                                      <option value="010">010</option>
                                      <option value="011">011</option>
                                      <option value="016">016</option>
                                      <option value="017">017</option>
                                      <option value="018">018</option>
                                      <option value="019">019</option>
                                  </select>
                                  -
                                  <input type="text" id="phone2"  maxlength="4"  name="phone" value="${ph[1]}">
                                  -
                                  <input type="text" id="phone3" maxlength="4"  name="phone" value="${ph[2]}">
                              </td>
                          </tr>
                          <tr>
                          <c:set var="em" value="${fn:split(loginMember.memberEmail, '@')}"/>
                              <th>
                                  이메일
                                  <img src="${contextPath}/resources/images/cnh/images/icon_required.gif">
                              </th>
                              <td>
                              
                                  <input type="text" name="eMail" value="${em[0]}">@
                                  <input type="text" name="eMail" value="${em[1]}">
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
                                      <input type="date" id="birth_year" name="birth_year" value="${sessionScope.loginMember.memberBirthday }">
                                  </td>
                              </tr>
                          </tbody>
                      </table>
                  </div>
              </div>
          </div>
          <div class="editBtnBox">
              <a id="editSubmitBtn" style="cursor:pointer;" onclick="updateMemberInfo()">
                  <img src="${contextPath}/resources/images/cnh/images/btn_modify_member.gif">
              </a>
              <a href="./" style="cursor:pointer;">
                  <img src="${contextPath}/resources/images/cnh/images/btn_modify_cancel.gif">
              </a>
              <span>
                  <a  style="cursor:pointer;" onclick="secession()">
                      <img src="${contextPath}/resources/images/cnh/images/btn_modify_out.gif">
                  </a>
              </span>
          </div>
      </form>

  </div> <!--content end-->

  <!-- footer include -->
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>	
<%--제이쿼리 들어있을 것 --%>
<script>
$(function(){
	const ph0 ="${ph[0]}";
	
	$("#phone1 > option").each(function(index, item){
		
		if(ph0 == item.innerText){
			item.setAttribute("selected", true);
		}
	});
});

const memberNo = ${sessionScope.loginMember.memberNo};

</script>

<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="${contextPath}/resources/js/member/updateMember.js"></script>

</body>
</html>