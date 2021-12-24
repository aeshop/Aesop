<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath"
	value="${pageContext.servletContext.contextPath}" scope="application" />
<html lang="ko">
<head>
<meta charset="UTF-8">
<jsp:include page="../../common/r_header.jsp" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title></title>

<link rel="stylesheet" href="${contextPath}/resources/css/boardV.css">
</head>
<body>
	<div id="contents">

		<!-- 글 내용-->
		<form id="BoardDelForm" name="" action="" method="post" target="_self">
			<div class="ec-base-table typeWrite">
				<table id="mcBorder" summary="">

					<colgroup>
						<col style="width: 130px;">
						<col style="width: auto;">
					</colgroup>
					<tbody>
						<tr id="mcBorder">
							<th scope="row" id="mcBorder">제목</th>
							<td id="mcBorder">${board.boardTitle}</td>
						</tr>
						<tr id="mcBorder">
							<th scope="row" id="mcBorder">작성자</th>
							<td id="mcBorder">${board.memberName}</td>
						</tr>
						<tr id="mcBorder">

							<td colspan="2" id="mcBorder">조회수 ${board.readCount}</td>
						</tr>
						<tr>
							<td colspan="2" id="mcBorder">

								<div class="detail">
									<div class="fr-view fr-view-article">
										<img style="width: 20%;" alt="관리자"
											src="${contextPath}/resources/images/board/관리자.jpg">
										<p>${board.boardContent}</p>
									</div>
								</div>
							</td>
						</tr>
						<tr class="attach displaynone" id="mcBorder">
							<th scope="row" id="mcBorder">첨부파일</th>
							<td id="mcBorder"></td>
						</tr>
						<tr class="displaynone " id="mcBorder">
							<th scope="row" id="mcBorder">비밀번호</th>
							<td id="mcBorder"><input id="password" name="password"
								fw-filter="" fw-label="비밀번호" fw-msg=""
								onkeydown="if (event.keyCode == 13 || event.which == 13) { return false; }"
								value="" type="password"> <span
								class="ec-base-help txtInfo">수정 및 삭제하려면 비밀번호를 입력하세요.</span></td>


						</tr>

						<jsp:include page="../reply.jsp"></jsp:include>
						
					</tbody>
				</table>
			</div>

			<div
				class="xans-element- xans-board xans-board-commentpackage-1002 xans-board-commentpackage xans-board-1002 ">


				<div
					class="xans-element- xans-board xans-board-movement-1002 xans-board-movement xans-board-1002 ">
					<ul>
						<li class="prev displaynone"></li>
					</ul>
				</div>
			</div>

		</form>
		<form action="#" method="POST" name="requestForm">
			<input type="hidden" name="cp" value="${param.cp }"> <input
				type="hidden" name="no" value="${param.no }">
		</form>
	</div>
</body>
</html>
<jsp:include page="../../common/r_footer.jsp" />