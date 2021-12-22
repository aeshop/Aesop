<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.servletContext.contextPath}" scope="application" />
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title></title>
	
	<link rel="stylesheet" href="${contextPath}/resources/css/boardV.css">
</head>
<body>
	<div id="contents">

        <!-- 글 내용-->
        <form id="BoardDelForm" name="" action="" method="post" target="_self" >
        <div class="ec-base-table typeWrite">
            <table border="1" summary="">

                <colgroup>
                    <col style="width:130px;">
                    <col style="width:auto;">
                </colgroup>
                <tbody>
                    <tr>
                        <th scope="row">제목</th>
                        <td>${board.boardTitle}</td>
                    </tr>
                    <tr>
                        <th scope="row">작성자</th>
                        <td> ${board.memberName}</td>
                    </tr>
                    <tr>

                        <td colspan="2"> 조회수 ${board.readCount} </td>
                    </tr>
                    <tr>
                        <td colspan="2">

                            <div class="detail">
                                <div class="fr-view fr-view-article">
									<img style ="width:20%;" alt="관리자" src="${contextPath}/resources/images/board/관리자.jpg">
                                    <p>${board.boardContent}</p>
                                </div>
                            </div>
                        </td>
                    </tr>
                    <tr class="attach displaynone">
                        <th scope="row">첨부파일</th>
                        <td></td>
                    </tr>
                    <tr class="displaynone ">
                        <th scope="row">비밀번호</th>
                        <td><input id="password" name="password" fw-filter="" fw-label="비밀번호" fw-msg=""
                                onkeydown="if (event.keyCode == 13 || event.which == 13) { return false; }" value=""
                                type="password"> <span class="ec-base-help txtInfo">수정 및 삭제하려면 비밀번호를
                                입력하세요.</span>
                        </td>
                    </tr>
                </tbody>
            </table>
            <div
                class="xans-element- xans-board xans-board-commentpackage-1002 xans-board-commentpackage xans-board-1002 ">


                <div class="xans-element- xans-board xans-board-movement-1002 xans-board-movement xans-board-1002 ">
                    <ul>
                        <li class="prev displaynone">
                        	<c:if test="${param.cp >= 2 }">
							<strong>이전글</strong><a href="${contextPath}/"></a>
							</c:if>
                            
                        </li>
                        <li class="next ">
                            <strong>다음글</strong><a href=""> 공지공지공지 </a>
                        </li>
                    </ul>
                </div>
            </div>
            <a href="list?cp=${param.cp }" class="btn btn-primary float-right mr-2">목록으로</a>
</form>
    </div>
</body>
</html>