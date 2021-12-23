<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath"
	value="${pageContext.servletContext.contextPath}" scope="application" />
<!DOCTYPE html>
<html lang="ko">

<head>

<title>게시글 등록</title>
<style>
@import url(//fonts.googleapis.com/earlyaccess/notosanskr.css);

* {
	font-family: 'Noto Sans KR', sans-serif;
}

.container {
	width: 1000px;
	margin: 0 auto;
}

.form-group {
	width: 1000px;
	margin: 0 auto;
}

.text-center {
	width: 1000px;
	margin: 0 auto;
}

div {
	padding: 10px;
}

input {
	border: 1px solid #353535;
	border-radius: 8px;
	height: 40px;
}

textarea {
	padding: 10px;
	box-sizing: border-box;
	border-radius: 10px;
}

hr {
	border: 1px solid #353535;
	width: 840px;
	margin: unset;
}
</style>
</head>

<body>

	<div class="container my-5">

		<h1>게시글 등록</h1>
		<hr>

		
		<form action="insert" method="post"  role="form" onsubmit="return boardValidate();">

			<%-- 카테고리 --%>
			<div class="mb-2">
				<label class="input-group-addon mr-3 insert-label">카테고리</label> <select
					class="custom-select" id="categoryCode" name="categoryCode"
					style="width: 150px;">

					<c:forEach items="${category}" var="c">
						<option value="${c.categoryCode}">${c.categoryName }</option>
					</c:forEach>

				</select>
			</div>


			<div class="form-inline mb-2">
				<label class="input-group-addon mr-3 insert-label">제목</label> <input
					placeholder=" 제목을 입력해주세요." type="text" class="form-control"
					id="boardTitle" name="boardTitle" size="70">
			</div>

			<div class="form-inline mb-2">
				<label class="input-group-addon mr-3 insert-label">작성자</label>
				<h5 class="my-0" id="writer">${loginMember.memberName }</h5>
			</div>


			<div class="form-inline mb-2">
				<label class="input-group-addon mr-3 insert-label">작성일</label>
				<h5 class="my-0" id="today"></h5>
			</div>

			<hr>
	

	<div class="form-group">

		<textarea rows="30" cols="120" placeholder=" 내용을 입력 해주세요 " name="boardContent"></textarea>
	</div>



	<div class="text-center">
		<button type="submit" class="btn btn-primary">등록</button>
		<button type="button" class="btn btn-primary">목록으로</button>
	</div>


	</form>
	</div>
</body>
<script>
function boardValidate() {
	if ($("#boardTitle").val().trim().length == 0) {
		alert("제목을 입력해 주세요.");
		$("#title").focus();
		return false;
	}

	if ($("#boardContent").val().trim().length == 0) {
		alert("내용을 입력해 주세요.");
		$("#content").focus();
		return false;
	}
}</script>
</html>

<jsp:include page="../../common/r_footer.jsp" />
