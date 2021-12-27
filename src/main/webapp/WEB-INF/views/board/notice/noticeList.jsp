<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<c:set var="contextPath" value="${pageContext.servletContext.contextPath}" scope="application" />
		<!DOCTYPE html>
		<html lang="ko">

		<head>
			<meta charset="UTF-8">
			<meta name="viewport" content="width=device-width, initial-scale=1.0">
			<jsp:include page="../../common/r_header.jsp" />
			<jsp:include page="../../common/sidebar_n.jsp" />
			<link rel="stylesheet" href="${contextPath}/resources/css/board.css">
			<title>하이</title>
		</head>

		<body>


			<div class="banner">
				<div class="alpa">


					<h1 style="font-size: 64px; color: #ffffff">Forums (${gubun})</h1>
				</div>

				<div>
					<p style="font-size: 24px; color: #ffffff">AE$OP's community
						board (이솝 커뮤니티 게시판)</p>
				</div>

			</div>


			<div class="centerV">

				<div class="ec-base-table typeList gBorder">
					<table border="1" summary="">

						<colgroup>
							<col style="width: 70px;">

							<col style="width: 700px;">
							<col style="width: 84px;">

							<col style="width: 105px;">

						</colgroup>
						<thead>
							<tr>
								<th scope="col">No</th>
								<th scope="col">Subject</th>
								<th scope="col">Name</th>
								<th scope="col" class="">Hit</th>

							</tr>
						</thead>
						<%-- 게시글 목록 출력 --%>
							<tbody>

								<c:choose>

									<c:when test="${empty boardList}">
										<%-- 조회된 게시글 목록이 없을 때 --%>
											<tr>
												<td colspan="4">게시글이 존재하지 않습니다.</td>
											</tr>
									</c:when>

									<c:otherwise>
										<c:forEach items="${boardList}" var="board">

											<%-- 조회된 게시글 목록이 있을 때 --%>
												<tr class="center">
													<td>${board.viewNo}</td>

													<td class="leftV"><a
															href="${contextPath}/board/notice/view?no=${board.boardNo}&c=${param.c}&cp=${pagination.currentPage}">
															<c:choose>
																<c:when test="${board.boardStatusName eq '블라인드' }">
																	<strong style="color: red; font-size: 11px"> 관리자에
																		의해 블라인드 처리된 게시글입니다. </strong>
																</c:when>
																<c:otherwise>
																	${board.boardTitle}
																</c:otherwise>
															</c:choose>
														</a></td>
													<td>${board.memberName }</td>

													<td class=""><span class="txtNum">${board.readCount}</span></td>

												</tr>
										</c:forEach>
									</c:otherwise>

								</c:choose>
							</tbody>

					</table>
					<br>
					<br>
					<form>
						<fieldset class="boardSearch">

							<select id="search_key" name="search_key" class="tSBKF">
								<option value="board_title">제목</option>
								<option value="board_content">내용</option>
								<option value="member_name">글쓴이</option>
							</select> <input id="button" name="search" class="inputTypeText tSBKF" placeholder=""
								value="${param.search}" type="text">
							<button class="tSBKF">찾기</button>

						</fieldset>
						<input type="hidden" name="c" value="${param.c}">
					</form>
					<%-- 로그인이 되어있는 경우에만 글쓰기 버튼 노출 --%> <c:if test="${param.c ne '803' || loginMember.memberNo eq 30}">
						<c:if test="${!empty loginMember }">
							<button type="button" class="tSBKF" id="insertBtn"
								onclick="location.href = '${contextPath}/board/notice/insert?c=${param.c}';">글쓰기</button>
							</c:if>
						</c:if>
				</div>
				<!-- 버튼-->
			</div>



			<%---------------------- Pagination ----------------------%>

			<!-- search_key=board_content&search=쿼리&c=801 -->

				<c:if test="${!empty param.search}">
					<c:set var="searchParam">
						&search_key=${param.search_key}&search=${param.search}
					</c:set>
				</c:if>
					

				<div class="my-5">
					<ul class="pagination">


						<c:if test="${pagination.startPage != 1 }">
							<li><a class="page-link" href="list?c=${param.c}&cp=1${searchParam}">&lt;&lt;</a></li>
							<li><a class="page-link" href="list?c=${param.c}&cp=${pagination.prevPage}${searchParam}">&lt;</a></li>
						</c:if>

						<%-- 페이지네이션 번호 목록 --%>
							<c:forEach begin="${pagination.startPage}" end="${pagination.endPage}" step="1" var="i">
								<c:choose>
									<c:when test="${i == pagination.currentPage}">
										<li><a class="page-link" style="color: black; font-weight: bold;">${i}</a>
										</li>
									</c:when>

									<c:otherwise>
										<li><a class="page-link" href="list?c=${param.c}&cp=${i}${searchParam}">${i}</a></li>
									</c:otherwise>
								</c:choose>
							</c:forEach>

							<c:if test="${pagination.endPage != pagination.maxPage }">
								<li><a class="page-link" href="list?c=${param.c}&cp=${pagination.nextPage}${searchParam}">&gt;</a></li>
								<li><a class="page-link" href="list?c=${param.c}&cp=${pagination.maxPage }${searchParam}">&gt;&gt;</a></li>
							</c:if>
					</ul>
				</div>



		</body>

		</html>

		<jsp:include page="../../common/r_footer.jsp" />