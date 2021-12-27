<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="${contextPath}/resources/css/member/member.css">
<jsp:include page="../common/r_header.jsp" />
<jsp:include page="/WEB-INF/views/common/sidebar_n.jsp"/>

<div class="login_wrap">

	<form id="login" method="post" action="${contextPath}/member/login" onsubmit="return loginValidate()" >
		<div class="login_container">
			<div class="login_container_form">
				<div class="login_title">LOGIN</div>
				<fieldset>
					<div class="login_form">
						<div class="login_form_input">
							<input type="text" id="memberId" name="memberId" placeholder="ID" required>
							<input type="password" id="memberPw" name="memberPw" placeholder="PW" required>
						</div>
						<button type="submit" class="login_btn">로그인</button>
						<div class="typeLogin">
							<a href="findId">아이디찾기</a> 
							<a href="findPw">비밀번호찾기</a>
						</div>
					</div>
				</fieldset>
			</div>


			<div class="login_container_img">
			
				<img src="https://storage.oneslist.com/assets/2021/10/19155932/aesop-pe.jpg"
					style="width:800px; padding: 100px 0;"
				> 
			</div>
		</div>
	</form>

</div>



<!-- footer -->
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
<script src="${contextPath}/resources/js/member/member.js"></script>


</body>
</html>