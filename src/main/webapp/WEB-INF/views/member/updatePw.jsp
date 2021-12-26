<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link rel="stylesheet"
	href="${contextPath}/resources/css/member/member.css">
<jsp:include page="../common/r_header.jsp" />


<div class="xans-element- xans-member xans-member-findPw">
	<jsp:include page="../common/sidebar_n.jsp" />
	<form id="updatePw" action="updatePw" method="post" >

		<div class="xans-element- xans-member xans-member-passwordreset ">
			<div class="inner">
				<h3>비밀번호 재설정</h3>
				<fieldset>
					<ul class="ec-base-desc typeBullet gSmall">
						<li>
						<strong class="term">아이디</strong> 
						<span><strong>${sessionScope.memberId}</strong></span>
						</li>
						<li>
							<div class="desc">
								<strong class="term">새 비밀번호</strong> <input id="newPwd1"
									name="newPwd1"  maxlength="12"
									placeholder="(영문 / 숫자 / 특수문자 , 4자~12자)" type="password"
									style="width: 230px;"> <span id="checkPwd1">영문 /
									숫자 / 특수문자 , 4자~12자로 작성해주세요.</span>
							</div>
						</li>
						<li>
							<div class="desc">
								<strong class="term">새 비밀번호 확인</strong> <input id="newPwd2"
									name="newPwd2"  maxlength="12" type="password"
									style="width: 230px;"> <span id="checkPwd2"></span>
							</div>
						</li>
					</ul>
					<p class="ec-base-button gBlank20">
						<button type="submit" class="btn btn-dark" id="updatePw_btn">확인</button>
						<!-- <button type="button" class="btn btn-dark" onclick="location.href='/'">취소</button> -->
						<!-- <a href="/"> <img
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
</body>
</html>