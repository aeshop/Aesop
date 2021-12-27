<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<!DOCTYPE html>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<c:set var="contextPath" value="${pageContext.servletContext.contextPath}" scope="application" />
		<html lang="ko">

		<head>
			<meta charset="UTF-8">
			<jsp:include page="../../common/r_header.jsp" />
			<jsp:include page="../../common/sidebar_n.jsp" />
			<meta name="viewport" content="width=device-width, initial-scale=1.0">
			<title></title>

			<link rel="stylesheet" href="${contextPath}/resources/css/boardV.css">
		</head>

		<body>
			<div id="contents">
			
				<!-- 글 내용-->
				
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

								<c:if test="${board.categoryCode eq 801}">
									<tr id="mcBorder">
										<th scope="row" id="mcBorder">별점</th>
										
										<td id="mcBorder" style="color:#ff5151">
											<c:if test="${board.productScore eq 1}">★☆☆☆☆</c:if>
											<c:if test="${board.productScore eq 2}">★★☆☆☆</c:if>
											<c:if test="${board.productScore eq 3}">★★★☆☆</c:if>
											<c:if test="${board.productScore eq 4}">★★★★☆</c:if>
											<c:if test="${board.productScore eq 5}">★★★★★</c:if>
										</td>

									</tr>

								</c:if>


								<tr id="mcBorder">
									<th scope="row" id="mcBorder">작성자</th>
									<td id="mcBorder">${board.memberName}</td>
								</tr>
								<tr id="mcBorder">

									<td colspan="2" id="mcBorder">조회수 ${board.readCount}</td>
								</tr>
								<tr>
									<td colspan="2" id="mcBorder">
										<c:if test="${not empty img}">
											<img style="width: 20%;" src="${contextPath}${img}"/>
										
										</c:if>
												<c:if test="${param.c eq '803'}">
													<img style="width: 20%;" alt="관리자"
													src="${contextPath}/resources/images/board/관리자.jpg">
												</c:if>
												<p>${board.boardContent}</p>
											
									</td>
								</tr>
								<tr class="attach" id="mcBorder">
									<th scope="row" id="mcBorder">첨부파일</th>
									<td id="mcBorder"></td>
								</tr>
								<tr id="mcBorder">
									<c:if test="${loginMember.memberNo eq board.memberNo}">
									
									<td id="mcBorder" colspan="2" >
										<button class="tSBKF nodragon" onclick="deleteBoard(no1);" >게시글 삭제</button>
										<button class="tSBKF nodragon" onclick="updateBoard();" >게시글 수정</button>
									</td>
									</c:if>


								</tr>
								

								<jsp:include page="../reply.jsp"></jsp:include>

							</tbody>
						</table>
					</div>

					<div class="xans-element- xans-board xans-board-commentpackage-1002 xans-board-commentpackage xans-board-1002 ">
						<div
							class="xans-element- xans-board xans-board-movement-1002 xans-board-movement xans-board-1002 ">
							<ul>
								<li class="prev displaynone"></li>
							</ul>
						</div>
					</div>

				

				<form action="#" method="POST" name="requestForm">
					<input type="hidden" name="cp" value="${param.cp }"> 
					<input type="hidden" name="no"
						value="${param.no }">
						<input type="hidden" name="c"
						value="${param.c }">
				</form>
					<script>
						const boardNo = ${board.boardNo};
						const no1 = '${param.no}';
						</script>
						

				<script src="${contextPath}/resources/js/board/boardView.js"></script>


	
			</div>
		</body>

		</html>
		<jsp:include page="../../common/r_footer.jsp" />