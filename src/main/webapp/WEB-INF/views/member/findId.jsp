<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link rel="stylesheet"
	href="${contextPath}/resources/css/member/member.css">
<jsp:include page="../common/r_header.jsp" />
	<jsp:include page="../common/sidebar_n.jsp" />


<div class="xans-element- xans-member xans-member-findid" style="text-align: center;">

	<form id="findId" method="post" action="findId" >
		<div class="findId_container">
			<fieldset>
				<legend>아이디 찾기</legend>

				<p id="name_view" class="name" style="">
					<strong id="name_lable">이름</strong> 
					<input id="name" name="name" class="lostInput" type="text">
				</p>

				<p id="email_view" class="email" style="">
					<strong>이메일</strong> 
					<input id="email" name="email" class="lostInput" type="email">
				</p>

				<button type="submit" class="btn btn-dark" id="findId_btn">확인</button>
			</fieldset>
		</div>
	</form>
</div>



<jsp:include page="../common/r_footer.jsp" />
</body>
</html>