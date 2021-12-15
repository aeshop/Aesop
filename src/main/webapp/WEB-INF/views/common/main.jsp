<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- header -->
<jsp:include page="header.jsp" />

<!-- 메인 화면 이미지 -->
<div class="main-container">
	<!-- swiper슬라이더 메인컨테이너 -->
	<div class="swiper-container">
		<!-- 보여지는 영역 -->
		<div class="swiper-wrapper">
			<!-- div class="swiper-slide" 를 추가하면된다 -->
			<div class="swiper-slide">
				<img src="${contextPath}/resources/images/common/test1.jpg" alt="test" />
			</div>
			<div class="swiper-slide">
				<img src="${contextPath}/resources/images/common/test1.jpg" alt="test" />
			</div>
			<div class="swiper-slide">
				<img src="${contextPath}/resources/images/common/test1.jpg" alt="test" />
			</div>
			<div class="swiper-slide">
				<img src="${contextPath}/resources/images/common/test1.jpg" alt="test" />
			</div>
		</div>
		<!-- 페이징 버튼 처리 -->
		<div class="swiper-pagination"></div>

		<!-- 방향 버튼 상황에 따라 추가 삭제가능 -->
		<div class="swiper-button-prev"></div>
		<div class="swiper-button-next"></div>
	</div>
</div>

<!-- footer -->
<jsp:include page="footer.jsp"/>

</body>
</html>