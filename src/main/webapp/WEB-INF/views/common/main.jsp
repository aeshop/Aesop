<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- header -->
<jsp:include page="r_header.jsp" />

<!-- 메인 화면 이미지 -->
<div class="main_container" style="border: 1px solid black;">
	<jsp:include page="sidebar_n.jsp" />

	<!-- main 1페이지 -->
	<div class="main_container_1">
		<!-- Bootstrap Carousel-->
					<div class="carousel-inner">
						<div class="carousel-item active main_container_slide" data-interval="true">test11111</div>
						<div class="carousel-item main_container_slide" data-interval="true">
							test22222</div>
						<div class="carousel-item main_container_slide" data-interval="true">
							test33333</div>
						<button class="carousel-control-prev" type="button"
							data-bs-target="#carouselExampleDark" data-bs-slide="prev">
							<span class="carousel-control-prev-icon" aria-hidden="true"></span>
							<span class="visually-hidden">Previous</span>
						</button>
						<button class="carousel-control-next" type="button"
							data-bs-target="#carouselExampleDark" data-bs-slide="next">
							<span class="carousel-control-next-icon" aria-hidden="true"></span>
							<span class="visually-hidden">Next</span>
						</button>
					</div>
	</div>
	<div style="padding: 40px 0; border: none;"></div>

	<!-- main 2페이지 -->
	<div class="main_container_2">
		<div>
			<ul>
				<li>#TAG</li>
				<li>#태연</li>
				<li>#플리스</li>
				<li>#벨벳</li>
				<li>#트랙</li>
			</ul>
		</div>


		<div>
			<div class="main_container_2_slide_left">
				<div id="main_container_2_slide_left_carousel"
					class="carousel carousel-dark slide" data-bs-ride="carousel">

					<!-- Bootstrap Carousel-->
					<div class="carousel-inner">
						<div class="carousel-item active main_slide_2"
							data-interval="false">test11111</div>
						<div class="carousel-item main_slide_2" data-interval="false">
							test22222</div>
						<div class="carousel-item main_slide_2" data-interval="false">
							test33333</div>
						<button class="carousel-control-prev" type="button"
							data-bs-target="#carouselExampleDark" data-bs-slide="prev">
							<span class="carousel-control-prev-icon" aria-hidden="true"></span>
							<span class="visually-hidden">Previous</span>
						</button>
						<button class="carousel-control-next" type="button"
							data-bs-target="#carouselExampleDark" data-bs-slide="next">
							<span class="carousel-control-next-icon" aria-hidden="true"></span>
							<span class="visually-hidden">Next</span>
						</button>
					</div>
				</div>
				
				<div class="main_container_2_slide_left_desc">
					<h4>test X test11111</h4>
					<p>test test test1111</p>
				</div>
			</div>

			<div class="main_container_2_slide_center">
				<div class="main_container_2_slide_center_image">slide EVENT</div>
				<div class="main_container_2_slide_center_desc">
					<h4>test X test</h4>
					<p>test test test</p>
				</div>

			</div>
			<div class="main_container_2_slide_center_hover">
				<div>1</div>
				<div>2</div>
				<div>3</div>
				<div>4</div>
			</div>

			<div class="main_container_2_slide_right">
				<div>slide EVENT</div>
				<div>
					<h4>test X test</h4>
					<p>test test test</p>
				</div>
			</div>

		</div>
	</div>


	<!-- main 3페이지 -->
	<div class="main_container_3">
		<div>
			<h3 class="Black_Border_Heading Common_Margin"
				style="margin-left: 165px;">
				<span>LOOKBOOK</span>
			</h3>
		</div>

		<div>
			<div class="main_container_3_slide_left">
				<div class="main_container_3_slide_left_img"></div>
				<div class="main_container_3_slide_left_desc">
					<h4>test X test11111</h4>
					<p>test test test1111</p>
				</div>
			</div>

			<div class="main_container_3_slide_center">
				<div class="main_container_3_slide_center_img"></div>
				<div class="main_container_3_slide_center_desc">
					<h4>test X test11111</h4>
					<p>test test test1111</p>
				</div>
			</div>

			<div class="main_container_3_slide_right">
				<div class="main_container_3_slide_right_img"></div>
				<div class="main_container_3_slide_right_desc"></div>
			</div>


		</div>
	</div>


	<!-- main 4페이지 -->
	<div class="main_container_4">
		<div
			style="height: 742px; margin-left: 110px; margin-top: 30px; display: flex; flex-direction: row;">
			<div style="width: 45%; height: 710px;">
				<h3 class="Black_Border_Heading Common_Margin">
					<span style="text-decoration: 2.5px underline black;">AESOP
						VIDEO</span>
				</h3>
				<div style="height: 400px;">
					<iframe width="100%" height="100%" src="https://www.youtube.com/embed/o7L-3ZOhVsY" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen>
					</iframe>
				</div>
				<div
					style="display: flex; flex-direction: row; justify-content: center;">
					<div style="width: 25%; height: 140px;"><img src="${contextPath}/resources/images/common/youtube_img1.png" style="width:100%; height:100%;"></div>
					<div style="width: 25%; height: 140px;"><img src="${contextPath}/resources/images/common/youtube_img2.png" style="width:100%; height:100%;"></div>
					<div style="width: 25%; height: 140px;"><img src="${contextPath}/resources/images/common/youtube_img3.png" style="width:100%; height:100%;"></div>
				</div>
			</div>


			<div style="width: 45%; height: 710px;">
				<h3 class="Black_Border_Heading Common_Margin">
					<span style="text-decoration: 2.5px underline black;"> WHO
						IS AESOP? </span>
				</h3>
				<div
					style="height: 600px; display: flex; flex-direction: row; flex-wrap: wrap;">
					<div style="width: 43%;"></div>
					<div style="width: 43%;"></div>
					<div style="width: 43%;"></div>
					<div style="width: 43%;"></div>
				</div>
			</div>

		</div>
	</div>










</div>

<!-- footer -->
<jsp:include page="r_footer.jsp" />
</body>
</html>