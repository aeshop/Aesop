<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="${contextPath}/resources/css/member/member.css">
<jsp:include page="../common/r_header.jsp" />

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
							<a href="#">비밀번호찾기</a>
						</div>
					</div>
				</fieldset>
			</div>


			<div class="login_container_img"></div>
		</div>
	</form>

</div>



<!-- footer -->
<jsp:include page="../common/r_footer.jsp" />
<script src="${contextPath}/resources/js/member/member.js"></script>


</body>
</html>