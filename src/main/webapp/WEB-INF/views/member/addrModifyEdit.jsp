<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<jsp:include page="/WEB-INF/views/common/r_header.jsp"/>
<jsp:include page="/WEB-INF/views/common/sidebar_n.jsp"/>

    <!-- main 1페이지 -->
    <!-- content -->
    
    
    
    <c:set var="addrList" value="${sessionScope.addrList[param.idx]}"/>
    <div class="content">
      <form method="POST" action="edit" onsubmit="return addrUpdateValidate();" id="registerForm">
      	<input type="hidden" name="idx" value="${param.idx}">
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
                          <input type="text" id="addr-name" value="${addrList.addrName }" name="addrName">
                      </td>
                  </tr>
                  <tr>
                      <th>
                          성명
                          <img src="${contextPath}/resources/images/cnh/images/icon_required.gif">
                      </th>
                      <td>
                          <input type="text" id="recipient-name" value="${addrList.addrReceiverName }" name="addrReceiverName">
                      </td>
                  </tr>
                  <tr>
                      <th>
                          주소
                          <img src="${contextPath}/resources/images/cnh/images/icon_required.gif">
                      </th>
                      <td>
                          <input type="text" id="sample6_postcode" placeholder="우편번호" value="${addrList.zipCode }" name="zipCode">
                          <input type="button" class="postcode-btn" onclick="sample6_execDaumPostcode()" value="우편번호" ><br>
                          <input type="text" id="sample6_address" placeholder="주소" value="${addrList.address1}" name="address1"><br>
                          <input type="text" id="sample6_detailAddress" placeholder="상세주소" value="${addrList.address2}" name="address2">
                          <!-- <input type="text" id="sample6_extraAddress" placeholder="참고항목"> -->
                      </td>
                  </tr>
                  <tr>
                      <th>
                      <c:set var="ph" value="${fn:split(addrList.addrPhone, '-') }"/>
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
                          <input type="text" id="phone2" maxlength="4" value="${ph[1] }" name="aPhone">
                          -
                          <input type="text" id="phone3" maxlength="4" value="${ph[2] }" name="aPhone">
                      </td>
                  </tr>
                  <tr class="default-addr">
                      <td colspan="2">
<%--                       <c:choose>
	                       	<c:when test="${sessionScope.addrList[param.idx].defaultAddress == 'Y' }">
		                          <input type="checkbox" id="default-check" name="defaultCheck" checked>
	                       	</c:when>
                       	<c:otherwise>
                       			  <input type="checkbox" id="default-check" name="defaultCheck">
                       	</c:otherwise>
                      </c:choose>
		                          <label for="default-check">기본 배송지로 저장</label>
                       --%></td>
                  </tr>
              </table>
          </div>
          <div class="addr-register-btn">
              <span>
                   <!-- 등록 버튼 -->
                  <a style="cursor:pointer;">
                      <img src="${contextPath}/resources/images/cnh/images/btn_address_register2.gif" id="register">
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
document.getElementById("register").addEventListener("click", function(){
	//document.getElementById("registerForm").submit();
	
	$("#registerForm").attr("method", "POST");
	document.getElementById("registerForm").submit();
	
});



</script>

<%-- session에 message 속성이 존재하는 경우 alert 창으로 해당 내용을 출력 --%>
<c:if test="${ !empty sessionScope.message }">
	<script>
	$(function(){
		alert("${message}");
	})
		// EL 작성 시 scope를 지정하지 않으면
		// page -> request -> session -> applicaiton 순서로 검색하여
		// 일치하는 속성이 있으면 출력
	</script>
	
	<%-- message 1회 출력 후 session에서 제거 --%>
	<c:remove var="message" scope="session"/>

</c:if>


<!-- 기본배송지 체크 js -->
<script> 
$(document).ready(function() { 
	$("input:checkbox").on('click', function() { 
		if ( $(this).prop('checked') ) { 
//			$(this).parent().addClass("selected"); 
			
			
		}else { 
//			$(this).parent().removeClass("selected"); 
		
		} 
			
	}); 
}); 
</script>


<script src="${contextPath}/resources/js/member/updateMember.js"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
</body>
</html>