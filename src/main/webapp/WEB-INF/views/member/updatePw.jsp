<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="../common/r_header.jsp" />
<jsp:include page="../common/sidebar_n.jsp" />


<div class="xans-element- xans-member xans-member-findPw">
	<form id="updatePw" action="updatePw" method="post"
		style="text-align: center;">

		<div class="xans-element- xans-member xans-member-passwordreset" style="text-align: center;">
			<div class="inner" style="text-align: center;">
				<h3>비밀번호 재설정</h3>
				<fieldset style="text-align:center;">
					<div class="signup_input">
						<input id="id" name="id" placeholder="아이디" type="text"
							autocomplete="off" required><span id="checkId"
							class="validity-msg"></span>
					</div>

					<div class="signup_input">
						<input id="pwd1" name="pwd1" placeholder="비밀번호" type="password"
							required> <span id="checkPwd1" class="validity-msg"></span>
					</div>

					<div class="signup_input">
						<input id="pwd2" name="pwd2" placeholder="비밀번호 확인" type="password"
							required> <span id="checkPwd2" class="validity-msg"></span>
					</div>
					<p class="ec-base-button gBlank20">
						<button type="submit" class="btn btn-dark" id="updatePw_btn">확인</button>
						<button type="button" class="btn btn-dark" onclick="location.href='${contextPath}/main'">취소</button>
						<!-- <a href=""> <img
							src="//img.echosting.cafe24.com/skin/base_ko_KR/member/btn_cancel.gif"
							alt="취소">
						</a> -->
					</p>
				</fieldset>
			</div>
		</div>
	</form>
</div>



<jsp:include page="../common/r_footer.jsp" />
<script>

var checkObj = {
		id: false,
		pwd1: false,
		pwd2: false
	};


/* id 유효성 검사 */
$("#id").on("input", function() {
	const regExp = /^[a-zA-Z0-9]{6,12}$/;
	const inputId = $("#id").val();

	if (inputId.length == 0) {
		$("#checkId").text("");
		checkObj.id = false;

	} else{
		if (regExp.test(inputId)) {
			$.ajax({
				url: "idDupCheck",
				data: { "id": inputId },
				type: "POST",

				success: function(result) {
					// console.log(result);

					if (result > 0) {
						$("#checkId").text("아이디가 일치합니다.").css("color", "rgb(146 26 255)");
						checkObj.id = true;

					} else {
						$("#checkId").text("아이디를 확인해주세요.").css("color", "rgb(146 26 255)");
						checkObj.id = false;
					}
				},

				error: function(e) {
					console.log(e);

				}
			});

		} else {
			$("#checkId").text("영어, 숫자 6~12글자로 작성해주세요.").css("color", "rgb(146 26 255)");
			checkObj.id = false;
		}
	}
});


/* 비밀번호 유효성 검사 */
$("#pwd1").on("input", function(){
	const inputPw = $("#pwd1").val();
	const regExp = /^[a-zA-Z\d\!\@\#\-\_]{4,12}$/;

	if (inputPw.length == 0) {
		$("#checkPwd1").text("");

		checkObj.pwd1 = false;
	} else if (regExp.test(inputPw)) {
		$("#checkPwd1").text("");

		checkObj.pwd1 = true;

		$("#pwd1,  #pwd2").on("input", function() {
			const pwd11 = $("#pwd1").val();
			const pwd22 = $("#pwd2").val();

			if (pwd11.trim() == "" && pwd22.trim() == "") {
				$("#checkPwd1, #checkPwd2").text("");

				checkObj.pwd2 = false;

			} else if (pwd11 == pwd22) {
				$("#checkPwd2").text("비밀번호가 일치합니다.").css("color", "rgb(146 26 255)");

				checkObj.pwd2 = true;

			} else {
				$("#checkPwd2").text("비밀번호 불일치").css("color", "rgb(146 26 255)");

				checkObj.pwd2 = false;
			}
		});


	} else {
		$("#checkPwd1").text("비밀번호는 영문,숫자,특수문자(!,@,#,-,_)포함 4 ~ 12글자로 작성해주세요.").css("color","rgb(146 26 255)");

		checkObj.pwd1 = false;
	}
});

</script>
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