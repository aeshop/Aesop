<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<jsp:include page="/WEB-INF/views/common/r_header.jsp"/>
<jsp:include page="/WEB-INF/views/common/sidebar_n.jsp"/>

    <!-- main 1페이지 -->
    <!-- content -->
    <div class="content">
      <form method="POST" action="Register" onsubmit="return addrRegisterValidate();" id="rForm">
      <input type="hidden" name="addrNo">
          <div class="addr-register-box">
              <table class="addr-register-table" >
                  <colgroup>
                      <col style="width: 120px;">
                      <col style="width: 1080px;">
                  </colgroup>
                  <tr>
                      <th>
                          배송지명
                          <img src="${contextPath}/resources/images/cnh/images/icon_required.gif">
                      </th>
                      <td>
                          <input type="text" id="addr-name" name="addrName">
                      </td>
                  </tr>
                  <tr>
                      <th>
                          성명
                          <img src="${contextPath}/resources/images/cnh/images/icon_required.gif">
                      </th>
                      <td>
                          <input type="text" id="recipient-name" name="addrReceiverName">
                      </td>
                  </tr>
                  <tr>
                      <th>
                          주소
                          <img src="${contextPath}/resources/images/cnh/images/icon_required.gif">
                      </th>
                      <td>
                          <input type="text" id="sample6_postcode" placeholder="우편번호" name="zipCode">
                          <input type="button" class="postcode-btn" onclick="sample6_execDaumPostcode()" value="우편번호"><br>
                          <input type="text" id="sample6_address" placeholder="주소" name="address1"><br>
                          <input type="text" id="sample6_detailAddress" placeholder="상세주소" name="address2">
                          <!-- <input type="text" id="sample6_extraAddress" placeholder="참고항목"> -->
                      </td>
                  </tr>
                  <tr>
                      <th>
                          휴대전화
                          <img src="${contextPath}/resources/images/cnh/images/icon_required.gif">
                      </th>
                      <td>
                          <select id="phone1" name="aPhone">
                              <option value="010">010</option>
                              <option value="011">011</option>
                              <option value="016">016</option>
                              <option value="017">017</option>
                              <option value="018">018</option>
                              <option value="019">019</option>
                          </select>
                          -
                          <input type="text" id="phone2" maxlength="4" name="aPhone">
                          -
                          <input type="text" id="phone3" maxlength="4"  name="aPhone">
                      </td>
                  </tr>
                  <tr class="default-addr">
                      <td colspan="2">
                          <!-- <input type="checkbox" id="default-check">
                          <label for="default-check">기본 배송지로 저장</label> -->
                      </td>
                  </tr>
              </table>
          </div>
          <div class="addr-register-btn">
              <span>
                  <!-- 등록 버튼 -->
                  <a style="cursor:pointer;">
                      <img src="${contextPath}/resources/images/cnh/images/btn_address_register2.gif" id="reg">
                  </a>
                  <a href="${contextPath}/myPage/addr"> <!-- 취소 버튼 -->
                      <img src="${contextPath}/resources/images/cnh/images/btn_address_cancel.gif">
                  </a>
              </span>
          </div>
      </form>
      <div class="addr-notice">
          <h3>배송주소록 유의사항</h3>
          <div>
              <ol>
                  <li class="num1">배송 주소록은 최대 10개까지 등록할 수 있으며, 별도로 등록하지 않을 경우 최근 배송 주소록 기준으로 자동 업데이트 됩니다.</li>
                  <li class="num2">자동 업데이트를 원하지 않을 경우 주소록 고정 선택을 선택하시면 선택된 주소록은 업데이트 대상에서 제외됩니다.</li>
                  <li class="num3">기본 배송지는 1개만 저장됩니다. 다른 배송지를 기본 배송지로 설정하시면 기본 배송지가 변경됩니다.</li>
              </ol>
          </div>
      </div>
    </div> <!--content end-->
    
    
      <!-- footer include -->
<jsp:include page="/WEB-INF/views/common/r_footer.jsp"/>

<!-- 폰 번호 저장 -->
<script>
$(function(){
	const ph0 ="${ph[0]}";
	
	$("#phone1 > option").each(function(index, item){
		
		if(ph0 == item.innerText){
			item.setAttribute("selected", true);
		}
	});
});

// 등록 버튼 눌렀을 시.. 제출
document.getElementById("reg").addEventListener("click", function(){
	//document.getElementById("registerForm").submit();
	
	$("#rForm").attr("method", "POST");
	document.getElementById("rForm").submit();
	
});



</script>


<script src="${contextPath}/resources/js/member/updateMember.js"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
</body>
</html>