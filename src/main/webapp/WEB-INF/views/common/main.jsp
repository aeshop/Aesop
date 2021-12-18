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
      <c:set var="contextPath" value="${pageContext.servletContext.contextPath}" scope="application"/>

   <h1>소제목</h1>
   
   <pre>내용</pre>
   
   
   
   <a href="${contextPath}/product/add">제품 입력 페이지로 이동</a>
</body>
</html>