<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="header_n.jsp"></jsp:include>


   <h1>페이지 기능확인위한 임시 페이지</h1>
   
   
   
   
   <a href="${contextPath}/product/add">제품 입력 페이지로 이동</a>
   <br>
   <hr>
      <a href="${contextPath}/category/view">카테고리 페이지로 이동</a>
         <br>
         <hr>
      
      <a href="${contextPath}/order/view">장바구니 페이지로 이동</a>
   
   
     <c:if test="${!empty sessionScope.message}">
<script>
 	alert("${message}");

//EL 작성시 scope작성 안하면 page request session application 순으로 검색
</script>

<c:remove var="message" scope="session"/>
<%--유지되고 있던 session의 message attribute를 제거해줘야 한다. --%>
</c:if>
   
   
   
   
   
<jsp:include page="footer_n.jsp"></jsp:include>
