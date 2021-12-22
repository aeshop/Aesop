<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
   <meta charset="UTF-8">
   <meta name="viewport" content="width=device-width, initial-scale=1.0">
   <title></title>
</head>
<body>
   
   
   <h1>관리자 로그인 없이 상품추가 페이지</h1>


<form action="${contextPath}/product/add" enctype="multipart/form-data" method="post">

<h3>상품 카테고리</h3> 
<br>
<c:forEach items="${category}" var="item">

<c:if test="${item.categoryNo2%10!=0}">
<input type="radio" name="categoryNo" value="${item.categoryNo2}">
${item.categoryName}
<br>

</c:if>

</c:forEach>


<h3>상품명</h3> 
<input type="text" name= "productName">
<h3>상품가격</h3> 
<input type="number" name= "productPrice">
<h3>할인율: 소수점으로 입력 <br> ex: 90% 할인 = 0.9</h3> 
<input type="text" name= "productDiscount">
<h3>상품재고</h3> 
<input type="number" name= "productStock">

<h3>상품사진(최대 3장)</h3> 

<input type="file" name="img0">
<input type="file" name="img1">
<input type="file" name="img2">

<hr>
<br>
<button>제품 등록</button>

</form>   
   
  <c:if test="${!empty sessionScope.message}">
<script>
 	alert("${message}");

//EL 작성시 scope작성 안하면 page request session application 순으로 검색
</script>

<c:remove var="message" scope="session"/>
<%--유지되고 있던 session의 message attribute를 제거해줘야 한다. --%>
</c:if>
   
   
   
   
</body>
</html>