<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<link rel="stylesheet" href="${contextPath}/resources/css/reply-style.css">
<script>

// 전역 변수로 댓글 관련 기능에 사용될 변수를 미리 선언
// -> 이 때 JSP에서만 사용 가능한 EL 값을 전역 변수로 선언하면
// 

// 로그인한 회원의 회원 번호, 비로그인 시 "" (빈문자열)
const loginMemberNo = "${loginMember.memberNo}";


// 수정 전 댓글 요소를 저장할 변수 (댓글 수정 시 사용)
let beforeReplyRow;

</script>

<script src="${contextPath}/resources/js/board/reply.js"></script>
<div id="mcBorder">
	<!-- 댓글 작성 부분 -->
	<div class="replyWrite" id="mcBorder">
		<table align="center">
			<tr>
				<td id="mcBorder">
					<textArea style="width:850px; resize: none;"rows="5" id="replyContent"></textArea>
				</td>
				<td id="mcBorder">
					<button class="tSBKF" style="width:100px; height:70px; font-size: 1.2rem;"  onclick="addReply()">
						댓글<br>등록
					</button>
				</td>
			</tr>
		</table>
	</div>


	<!-- 댓글 출력 부분 -->
	<div class="replyList mt-5 pt-2">
		<ul id="replyListArea">
			<c:forEach items="${rList}" var="reply">
				<li class="reply-row">
					<div>
						<p class="rWriter">${reply.memberName}</p>
						<p class="rDate">　작성일 : ${reply.replyCreateDate }</p>
					</div>
	
					<p class="rContent">${reply.replyContent }</p>
					
					
					<c:if test="${reply.memberNo == loginMember.memberNo}">
						<div class="replyBtnArea">
							<button class="tSBKF" onclick="showUpdateReply(${reply.replyNo}, this)">수정</button>
							<button class="tSBKF" onclick="deleteReply(${reply.replyNo})">삭제</button>
						</div>
					</c:if>
				</li>
			</c:forEach>
		</ul>
	</div>
	<a href="list?c=${param.c}&cp=${param.cp }" class="tSBKF" style="margin:10px; text-decoration:none">목록으로</a>
</div>





