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

<link rel="stylesheet"   href="${contextPath}/resources/css/common/r_common.css">
<link rel="stylesheet"   href="${contextPath}/resources/css/productDetail.css">

<!-- bootstrap -->
<link
   href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
   rel="stylesheet"
   crossorigin="anonymous">

<!-- 외부 font -->
<link href='https://fonts.googleapis.com/css?family=Playfair+Display'
   rel='stylesheet' type='text/css'>
   

<!-- swiper CSS -->
<!-- 널디에서 사용하길래 사용해봅니다. (오토슬라이드 기능) -->
<link rel="stylesheet"
   href="https://unpkg.com/swiper/swiper-bundle.min.css" />


<link rel="stylesheet" href="${contextPath}/resources/css/myPage.css">
<link rel="stylesheet"
	href="${contextPath}/resources/css/productDetail.css">
<link rel="stylesheet"
	href="${contextPath}/resources/css/orderHistory.css">
<link rel="stylesheet"
	href="${contextPath}/resources/css/addrModify.css">
<link rel="stylesheet"
	href="${contextPath}/resources/css/addrModifyEdit.css">
<link rel="stylesheet"
	href="${contextPath}/resources/css/addrRegister.css">
<link rel="stylesheet"
	href="${contextPath}/resources/css/updateMember.css">
<link rel="stylesheet"
	href="${contextPath}/resources/css/myPageBoard.css">
</head>

<script>
	const contextPath = "${contextPath}";
</script>
<body>
	<header id="carousel">
		<!-- Bootstrap Carousel-->
		<div id="carouselExampleDark" class="carousel carousel-dark slide"
			data-bs-ride="carousel">
			<div class="carousel-inner"
				style="text-align: center; line-height: 40px;  font-size: 13px;">
				<div class="carousel-item active" data-bs-interval="5500">전 구매
					무료 배송과 선물포장 혜택을 즐겨보세요.</div>
				<div class="carousel-item" data-bs-interval="5500">
				이솝 온라인 몰의 모든 주문 배송 건은 탄소중립을 지키기 위해 노력하고 있습니다.

				</div>
				<div class="carousel-item" data-bs-interval="5500">
					이솝 멤버십 등급별 혜택 보기</div>
			</div>
		</div>
	</header>

	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<div class="container-fluid">
			<div class="collapse navbar-collapse" id="navbarColor03">
				<div class="nav-logo">
					<a href="${contextPath}" id="logo_href"> <img
						src="${contextPath}/resources/images/cnh/images/logo.png"
						style="width: 100px;">
					</a>
				</div>


				<ul class="navbar-nav me-auto">
					<li class="nav-item"><a class="nav-link"
						href="https://www.aesop.com/kr/r/philosophy-to-design/">디자인</a></li>
					<li class="nav-item"><a class="nav-link"
						href="https://www.aesop.com/kr/r/about/">브랜드스토리</a></li>
					<li class="nav-item"><a class="nav-link" href="${contextPath}/board/notice/list?c=801">리뷰</a></li>
					<li class="nav-item"><a class="nav-link" href="${contextPath}/board/notice/list?c=802">Q&A</a></li>
					<li class="nav-item"><a class="nav-link" href="${contextPath}/board/notice/list?c=803">공지사항</a></li>

				</ul>
				<div class="d-flex">
					<input class="form-control me-sm-2" type="text"
						placeholder="Search" id="searchKeyword"
						style="border-bottom: 1px solid black; border-radius: 0; background-color: rgba(255, 255, 255, 0.2); font-size: 14px;">
					<button type="button" class="r-form-control-button">
						<i class="fas fa-search"></i>
					</button>

					<c:choose>
						<c:when test="${ empty sessionScope.loginMember }">
							<button type="button"
								onclick="location.href='${contextPath}/member/login'">
								LOGIN</button>
							<button type="button"
								onclick="location.href='${contextPath}/member/join'">JOIN</button>
						</c:when>
						<c:otherwise>
							<button type="button"
								onclick="location.href='${contextPath}/myPage'">
								<i class="far fa-user"></i>
							</button>
							<button type="button"
								onclick="location.href='${contextPath}/member/logout'">LOGOUT</button>
						</c:otherwise>
					</c:choose>
					<button type="button"
						onclick="location.href='${contextPath}/order/view'">
						<i class="fas fa-shopping-cart"></i>
					</button>


				</div>
			</div>
		</div>

	</nav>
	
	<script type="text/javascript">
	
	
		
		document.addEventListener('keyDown', function(e){
		    const searchBar = document.querySelector('#searchKeyword');
		    console.log(event);
		    
		    if(searchBar.focus&&e.keyCode==13){
		   
		    	
		    	 if ((searchBar.value.trim().length == 0)) {
		 	        alert('검색어를 입력해 주세요.');
		 	        searchBar.focus();
		 	        return;
		 	    } else {
		 	    	search();
		 	    }
		    	 
		    	
		    }
		    
		   
		});
		
	
	
	
	
	function search() {
	    const searchBar = document.querySelector('#searchKeyword');
	    //클래스로 입력창 내용을 받아옴

	    if (searchBar.value.trim().length == 0) {
	        alert('검색어를 입력해 주세요.');
	        return;
	    }

	    const keyword = searchBar.value;


	    location.href = contextPath + '/category/search?keyword=' + keyword;



	}
	
	
	
	</script>

