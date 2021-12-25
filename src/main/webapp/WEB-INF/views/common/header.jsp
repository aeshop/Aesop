<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- JSTL c태그 사용을 위한 taglib 추가 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- 프로젝트의 시작 주소를 간단히 얻어올 수 있도록 application scope로 contextPath라는 변수를 생성함--%>
<c:set var="contextPath" value="${pageContext.servletContext.contextPath}" scope="application"/>


<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>aesop</title>
		  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
	     crossorigin="anonymous">
	  <link href='https://fonts.googleapis.com/css?family=Playfair+Display' rel='stylesheet' type='text/css'>
	
	  <!-- swiper CSS -->
	  <!-- 널디에서 사용하길래 사용해봅니다. (오토슬라이드 기능) -->
	  <link rel="stylesheet" href="https://unpkg.com/swiper/swiper-bundle.min.css">
	  
	  <link rel="stylesheet" href="${contextPath}/resources/css/header.css">
	  <link rel="stylesheet" href="${contextPath}/resources/css/footer.css">
	  <link rel="stylesheet" href="${contextPath}/resources/css/productDetail.css">
	  <link rel="stylesheet" href="${contextPath}/resources/css/myPage.css">
	
</head>
<body>
  <header id="carousel">
    <!-- Bootstrap Carousel-->
    <div id="carouselExampleDark" class="carousel carousel-dark slide" data-bs-ride="carousel">
      <div class="carousel-inner">
        <div class="carousel-item active" data-bs-interval="5500">
          11111111111111111111111111
        </div>
        <div class="carousel-item" data-bs-interval="5500" >
          222222222222222222222222222
        </div>
        <div class="carousel-item" data-bs-interval="5500">
          33333333333333333333333333333
        </div>
      </div>
    </div>
  </header>

  <nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
      <div class="collapse navbar-collapse" id="navbarColor03">
        <div class="nav-logo">
          <a href="#" id="logo_href">
            <img src="${contextPath}/resources/images/logo.png" style="width: 100px;">
          </a>
        </div>
        <ul class="navbar-nav me-auto">
          <li class="nav-item">
            <a class="nav-link" href="#">Category1
              <!-- <span class="visually-hidden">(current)</span> -->
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">Category2</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">Category3</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">Category4</a>
          </li>

        </ul>
        <form class="d-flex">
          <input class="form-control me-sm-2" type="text" placeholder="Search" style=" border-bottom: 2px solid black;">
          <button type="button" class="r-form-control-button">
            <i class="fas fa-search" style="font-size: 44px;"></i>
          </button>

          <button type="button">
            LOGIN
          </button>
          <button type="button">
            JOIN
          </button>
          <button type="button">
            <i class="far fa-user"></i>
          </button>
          <button type="button">
            <i class="fas fa-shopping-cart"></i>
          </button>

        </form>
      </div>
    </div>
  </nav>


	