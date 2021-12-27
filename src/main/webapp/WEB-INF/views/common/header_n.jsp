<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.servletContext.contextPath}" scope="application"/>
<%-- contextPath 변수선언 --%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link href='https://fonts.googleapis.com/css?family=Playfair+Display' rel='stylesheet' type='text/css'>

    <!-- swiper CSS -->
    <!-- 널디에서 사용한 것 응용 -->
    <link rel="stylesheet" href="https://unpkg.com/swiper/swiper-bundle.min.css" />
      
       <!-- local CSS -->
   <link rel="stylesheet" href="${contextPath}/resources/css/common/n_headFootSide.css">
</head>

<body>

    <!-- header -->
    <!-- Swiper -->
    <!-- <div class="swiperHead swiper_header">
    <div class="swiper-wrapper">
      <div class="swiper-slide">Slide 1</div>
      <div class="swiper-slide">Slide 2</div>
      <div class="swiper-slide">Slide 3</div>
    </div>
  </div> -->

    <section id="carousel">
        <!-- Bootstrap Carousel-->
        <div id="carouselExampleDark" class="carousel carousel-dark slide" data-bs-ride="carousel">
            <div class="carousel-inner">
                <div class="carousel-item active" data-bs-interval="5500">
                    11111111111111111111111111
                </div>
                <div class="carousel-item" data-bs-interval="5500">
                    222222222222222222222222222
                </div>
                <div class="carousel-item" data-bs-interval="5500">
                    33333333333333333333333333333
                </div>
            </div>
        </div>
    </section>

    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container-fluid">
            <div class="collapse navbar-collapse" id="navbarColor03">
                <div class="nav-logo">
                    <a href="#" id="logo_href">
            Aesop
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
                    <input class="form-control me-sm-2" type="text" placeholder="Search" id="searchInput" style=" border-bottom: 2px solid black;"> <button type="button" class="r-form-control-button" style="border: none; background-color: transparent;">
                    <i class="fas fa-search" style="font-size: 44px;"></i></button>
                </form>
            </div>
        </div>
    </nav>