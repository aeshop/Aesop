<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="contextPath"
	value="${pageContext.servletContext.contextPath}" scope="application" />
	
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title></title>
	
	<style>
	#txt{
	
		position: relative;
	    border: 1px solid;
	    border-color: rgba(51, 51, 51, 0.2);
	    width: 240px;
	    height: 65px;
	    float: left;
	    right: -24px;
	    bottom: -63px;
	    font-size: 14px;
	    text-align: left;
	    line-height: 4.5;
	    padding-left: 20px;
	}
	
	#txt:hover{
		cursor: pointer;
		transform:scale(1.1); 
		-webkit-transform:scale(1.05); 
	}
	
	
	img{
		left: 0;
	    float: left;
	    position: absolute;
	}
	
	span{
	z-index: 6000;
    position: relative;
	}
		
	#m_title{
	font-size: 30px;
    font-weight: bold;
    bottom: -37px;
    right: -27px;
    font-weight:bold;
    }
		
	#m_content{
	bottom: -44px;
    right: -33px;
    font-size: 13px;
	}	
	
	#closeWin{
		    position: relative;
    right: -180px;
    bottom: 55px;
    color: gray;
    background: none;
    border: 1px solid gray;


	}
	
	
	</style>
</head>
<body>
	<img src="${contextPath}/resources/images/cnh/images/popimg.png" >
		<span id="m_title" >
		스킨케어 키트 <br>
		</span>
		<span id="m_content">
		스킨, 바디, 홈 케어 제품의 엄선된 셀렉션으로 <br>
		선물을 받는 다양한 분들에게 즐거움을 선사하세요.
		</span>
	<div id="txt">
		<a href="${contextPath}/product/productDetail?productNo=82" style="text-decoration:none; color:black;">제품 상세 보기 　　　　　　　　>
		</a>
	</div>
	<button id="closeWin"  onclick="closeWin()">X</button>

  <script>
  	function closeWin() {
    	window.close();        
	}
  </script>
</body>
</html>