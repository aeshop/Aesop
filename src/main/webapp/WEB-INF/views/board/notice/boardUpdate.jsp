<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

		<c:set var="contextPath" value="${pageContext.servletContext.contextPath}" scope="application" />
		<jsp:include page="../../common/r_header.jsp" />
		<jsp:include page="../../common/sidebar_n.jsp" />
		<!DOCTYPE html>
		<html lang="ko">

		<head>
			<script src="https://code.jquery.com/jquery-3.6.0.min.js"
				integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
			<title>게시글 수정</title>
			<link rel="stylesheet" href="${contextPath}/resources/css/boardInsert.css">
		</head>

		<body>
			<div class="container my-5">

				<h3>게시글 수정</h3>
				<div style="border-bottom: 5px double #353535; width:800px; border-radius: 5px;">
				</div>
				<form action="update" method="post" onsubmit="return boardValidate();">

					<div class="mb-2">
						<label class="input-group-addon mr-5 insert-label" style="padding-right: 10px;">카테고리:</label>
						<select class="custom-select tSBKF" id="categoryCode" name="categoryCode"
							style="width: 150px; margin:0 30px 0 0;">
							<c:forEach items="${category}" var="c">

								<c:if test="${c.categoryCode eq 803 }">
										<c:if test="${loginMember.memberNo eq 30}">
											<option id="categoryOption" value="${c.categoryCode}">${c.categoryName }</option>
										</c:if>
									</c:if>
								
									<c:if test="${c.categoryCode eq 802 }">
										
										<option id="categoryOption" value="${c.categoryCode}">${c.categoryName }</option>
										
										
									</c:if>
								<c:if test="${c.categoryCode eq 801}">
									<c:if test="${not empty product}">

										<option id="categoryOption" value="${c.categoryCode}">${c.categoryName }
										</option>

									</c:if>
								</c:if>
							</c:forEach>

						</select>
						<c:if test="${empty product}">
							<span class="voidworker" style="color:#ff5151">　　구매하신 상품이 존재하지 않습니다.</span>

						</c:if>
						<c:if test="${not empty product}">
							<select id="reviewImg" name="reviewImg" class="tSBKF" style="padding-left: 30px;">
								<c:forEach items="${product}" var="p">
									<option value="${p.productNo}" title="${contextPath}${p.categoryName}">
										${p.productName}</option>

								</c:forEach>
							</select>
						</c:if>
					</div>

					<div class="form-inline mb-2">
						<label class="input-group-addon mr-3 insert-label" style="padding-bottom: 19px;">제목　</label>
						<input placeholder=" 제목을 입력해주세요." type="text" class="fgmkt" id="boardTitle" name="boardTitle"
							size="40" value="${board.boardTitle}">
						<label class="rvrv input-group-addon mr-3 insert-label" <c:if
							test="${ empty product}">style="visibility: hidden;</c:if> " >　　평점　</label>
						<select name="star" class="tSBKF rvrv" <c:if test="${ empty product}">style="visibility: hidden;
							</c:if> ">

							<option value="1">★☆☆☆☆</option>
							<option value="2">★★☆☆☆</option>
							<option value="3">★★★☆☆</option>
							<option value="4">★★★★☆</option>
							<option value="5" selected>★★★★★</option>


						</select>
					</div>

					<div class="trekjhj">
						<label class="input-group-addon mr-3 insert-label" style="padding-bottom: 10px;">　작성자</label>
						<h5 class="my-0" id="writer">${loginMember.memberName }</h5>
					</div>


					<div class="trekjhj">
						<label style="padding-bottom: 10px;">　작성일</label>
						<h5 class="my-0" id="today"></h5>
					</div>

					<div style="border-bottom: 5px double #353535; width:800px; border-radius: 5px;">
					</div>


					<div class="form-group">

						<textarea style="resize: none;" rows="20" cols="85" placeholder=" 내용을 입력 해주세요 " class="fgmkt"
							name="boardContent">${board.boardContent }</textarea>
					</div>
					<input type="hidden" name="c" value="${param.c}">


					<div class="text-center">
						<button type="submit" class="tSBKF">수정</button>
						<button type="button" class="tSBKF" onclick="location.href='list?c=${param.c}'">목록으로</button>
					</div>

					<!-- update 진행 시 사용할 게시글 번호 -->
					<input type="hidden" name="no" value="${board.boardNo}">
					<input type="hidden" name="cp" value="${param.cp}">
				</form>
			</div>


			<script>

				(function () {
					// 오늘 날짜 출력 
					var today = new Date();
					var month = (today.getMonth() + 1);
					var date = today.getDate();

					var str = today.getFullYear() + "-"
						+ (month < 10 ? "0" + month : month) + "-"
						+ (date < 10 ? "0" + date : date);
					$("#today").html(str);
				})();


				// 유효성 검사 
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
				}

				// 수정버튼 클릭 시 동작
				function updateForm() {
					document.requestForm.action = "updateForm";
					document.requestForm.method = "POST";
					document.requestForm.submit();
				}

			</script>


		</body>
		<script src="${contextPath}/resources/js/board/boardInsert.js"></script>

		</html>

		<jsp:include page="../../common/r_footer.jsp" />