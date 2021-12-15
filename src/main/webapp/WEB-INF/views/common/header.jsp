<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.servletContext.contextPath}" scope="application" />


<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>aesop</title>

<!-- Bootstrap4 CSS -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">

<!-- 공용 CSS -->
<link rel="stylesheet" href="${contextPath}/resources/css/common/common.css">

<!-- swiper CSS -->
<!-- 널디에서 사용하길래 사용해봅니다. (오토슬라이드 기능) -->
  <link rel="stylesheet" href="https://unpkg.com/swiper/swiper-bundle.css" />
  <link rel="stylesheet" href="https://unpkg.com/swiper/swiper-bundle.min.css" />
</head>

<body>
<div class="header">
    <div class="nav-event">
      <a href="#">이미지 오토 슬라이드 이벤트</a>
    </div>
    <div class="main_nav">
      <div class="main_nav_left">
        <div class="nav_links">
          <a href="#">category1</a>
          <a href="#">category2</a>
          <a href="#">category3</a>
          <a href="#">category4</a>
          <a href="#">category5</a>
          <a href="#">category6</a>
        </div>
      </div>

      <div class="main_nav_right">
        <form id="searchBarForm" action="#" method="get">
          <div class="main_header_search">
            <input class= "inputTypeText"  type="text" id="keyword" placeholder="SEARCH" autocomplete="off">
            <input type="submit" value="검색" class="btn-search">
          </div>
        </form>

        <div class="main_nav_sign">
          <a href="#" class="btn-login">Login</a>
          <a href="#" class="btn-join ">Join</a>
        </div>

        <div class="main_nav_member">
          <a href="#" class="btn-mypage">Mypage</a>
          <a href="#" class="btn-cart">Cart</a>
        </div>
      </div>

    </div>
  </div>

