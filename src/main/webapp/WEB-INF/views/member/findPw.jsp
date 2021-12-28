<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet"
	href="${contextPath}/resources/css/member/member.css">
<jsp:include page="../common/r_header.jsp" />
	<jsp:include page="../common/sidebar_n.jsp" />


<div class="xans-element- xans-member xans-member-findid" style="text-align: center;">

	<form id="findPw" method="get" action="updatePw" >
		<div class="findPw_container">
			<fieldset>
				<legend>비밀번호 찾기</legend>
				
				<p id="name_view" class="name">
					<strong id="name_lable">이름</strong> 
					<input id="name" name="name" class="lostInput" type="text" required>
				</p>

				<p id="email_view" class="email">
					<strong>이메일</strong> 
					<input id="email" name="email" class="lostInput" type="email" required>
				</p>

				<button type="button" class="btn btn-dark" id="findPw_btn" 
				onclick="location.href='${contextPath}/member/updatePw'">확인</button>
				<button type="button" class="btn btn-dark" onclick="location.href='${contextPath}/main'">취소</button>
			</fieldset>
		</div>
	</form>
</div>



<jsp:include page="../common/r_footer.jsp" />

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