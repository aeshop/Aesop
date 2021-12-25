<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="contextPath"
	value="${pageContext.servletContext.contextPath}" scope="application" />
<jsp:include page="../../common/r_header.jsp" />
<!DOCTYPE html>
<html lang="ko">

<head>
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<title>게시글 등록</title>
<link rel="stylesheet" href="${contextPath}/resources/css/boardInsert.css">
</head>

<body>
	<div class="container my-5">

		<h1>게시글 등록</h1>
		<hr>


		<form action="insert" method="post" role="form"
			onsubmit="return boardValidate();">

			<%-- 카테고리 --%>
			<div class="mb-2">
				<label class="input-group-addon mr-5 insert-label" style="padding-right: 30px;">카테고리:</label> 
				<select
					class="custom-select tSBKF" id="categoryCode" name="categoryCode"
					style="width: 150px;">

					<c:forEach items="${category}" var="c">
						<option id="categoryOption" value="${c.categoryCode}">${c.categoryName }</option>
					</c:forEach>

				</select>
				<select id="reviewImg" class="tSBKF">
					<c:forEach items="${product}" var="p">
						<option value="${p.productNo}" title="${contextPath}${p.categoryName}">${p.productName}</option>
						
					</c:forEach>
				</select>
			</div>

			<div class="form-inline mb-2" >
				<label class="input-group-addon mr-3 insert-label" style="padding-bottom: 10px;">제목</label> <input
					placeholder=" 제목을 입력해주세요." type="text" class="form-control"
					id="boardTitle" name="boardTitle" size="70">
			</div>

			<div class="form-inline mb-2" >
				<label class="input-group-addon mr-3 insert-label" style="padding-bottom: 10px;">작성자</label>
				<h5 class="my-0" id="writer">${loginMember.memberName }</h5>
			</div>


			<div class="form-inline mb-2">
				<label class="input-group-addon mr-3 insert-label" style="padding-bottom: 10px;">작성일</label>
				<h5 class="my-0" id="today"></h5>
			</div>

			<hr>


			<div class="form-group">

				<textarea rows="20" cols="106" placeholder=" 내용을 입력 해주세요 "
					name="boardContent"></textarea>
			</div>
			<input type="hidden" name="c" value="${param.c}">
			

			<div class="text-center">
				<button type="submit" class="tSBKF">등록</button>
				<button type="button" class="tSBKF" onclick="location.href='list?c=${param.c}'">목록으로</button>
			</div>


		</form>
		
	</div>
</body>
<script src="${contextPath}/resources/js/board/boardInsert.js"></script>

</html>

<jsp:include page="../../common/r_footer.jsp" />