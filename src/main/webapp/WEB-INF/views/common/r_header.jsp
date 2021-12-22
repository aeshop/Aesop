<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="contextPath"
	value="${pageContext.servletContext.contextPath}" scope="application" />
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>aesop</title>
<!-- 공용 CSS -->

<!-- bootstrap -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">

<!-- 외부 font -->
<link href='https://fonts.googleapis.com/css?family=Playfair+Display'
	rel='stylesheet' type='text/css'>

<!-- swiper CSS -->
<!-- 널디에서 사용하길래 사용해봅니다. (오토슬라이드 기능) -->
<link rel="stylesheet"
	href="https://unpkg.com/swiper/swiper-bundle.min.css" />


<link rel="stylesheet"
	href="${contextPath}/resources/css/header.css">
<link rel="stylesheet" href="${contextPath}/resources/css/footer.css">

	  <link rel="stylesheet" href="${contextPath}/resources/css/myPage.css">
	  <link rel="stylesheet" href="${contextPath}/resources/css/productDetail.css">
	  <link rel="stylesheet" href="${contextPath}/resources/css/orderHistory.css">
	  <link rel="stylesheet" href="${contextPath}/resources/css/addrModify.css">
	  <link rel="stylesheet" href="${contextPath}/resources/css/addrModifyEdit.css">
	  <link rel="stylesheet" href="${contextPath}/resources/css/addrRegister.css">
	  <link rel="stylesheet" href="${contextPath}/resources/css/updateMember.css">
	  <link rel="stylesheet" href="${contextPath}/resources/css/myPageBoard.css">
</head>

<body>
	<header id="carousel">
		<!-- Bootstrap Carousel-->
		<div id="carouselExampleDark" class="carousel carousel-dark slide"
			data-bs-ride="carousel">
			<div class="carousel-inner">
				<div class="carousel-item active" data-bs-interval="5500">
					11111111111111111111111111</div>
				<div class="carousel-item" data-bs-interval="5500">
					222222222222222222222222222</div>
				<div class="carousel-item" data-bs-interval="5500">
					33333333333333333333333333333</div>
			</div>
		</div>
	</header>

	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<div class="container-fluid">
			<div class="collapse navbar-collapse" id="navbarColor03">
				<div class="nav-logo">
					<a href="${contextPath}" id="logo_href"> 
						<img src="${contextPath}/resources/images/cnh/images/logo.png" style="width: 100px;">
					 </a>
				</div>
				<ul class="navbar-nav me-auto">
					<li class="nav-item"><a class="nav-link" href="#">Category1
							<!-- <span class="visually-hidden">(current)</span> -->
					</a></li>
					<li class="nav-item"><a class="nav-link" href="#">Category2</a>
					</li>
					<li class="nav-item"><a class="nav-link" href="#">Category3</a>
					</li>
					<li class="nav-item"><a class="nav-link" href="#">Category4</a>
					</li>

				</ul>
				<div class="d-flex">
					<input class="form-control me-sm-2" type="text"
						placeholder="Search" style="border-bottom: 2px solid black;">
					<button type="button" class="r-form-control-button">
						<i class="fas fa-search" style="font-size: 44px;"></i>
					</button>

					<c:choose>
						<c:when test="${ empty sessionScope.loginMember }">
							<button type="button"
								onclick="location.href='${contextPath}/member/login'">
								LOGIN</button>
							<button type="button" onclick="location.href='${contextPath}/member/join'">JOIN</button>
						</c:when>
						<c:otherwise>
							<button type="button">LOGOUT</button>
							<button type="button" onclick="location.href='${contextPath}/member/myPage'">
								<i class="far fa-user"></i>
							</button>
						</c:otherwise>
					</c:choose>
					<button type="button">
						<i class="fas fa-shopping-cart"></i>
					</button>

				</div>
			</div>
		</div>
	</nav>
	
