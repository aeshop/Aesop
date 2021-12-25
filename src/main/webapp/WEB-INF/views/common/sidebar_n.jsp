<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- main -->
<div class="sidebar" style="position: fixed;">
      <ul class="sidebar_ul">


         <%--
      application scope - category 순회하면서 데이터 표시 
      category 는 300 = ALL 부터 하위 소분류까지 나열된 LIST
      처음시작 = 
      300=ALL: 사이드바, li 태그의 시작, 안보여주고 싶으므로 var status.index = 0인경우 li 태그만
      
      중간 list-item 변경: 소분류에서 대분류로 변할때  li태그가 닫히고 열림 + 대분류명 적음
      
      끝 = varStatus.index = 전체 리스트 크기 -1 일때 소분류명을 div안에 넣고 li 태그를 닫음
   그외 = div 태그 안 소분류들
      
      --%>
         <c:forEach items="${applicationScope.category}" var="item"
            varStatus="vs">
            <c:choose>
               <c:when test="${vs.index==0}">
                  <li class="sidebar_categoty">
               </c:when>


               <c:when test="${vs.index==categorySize-1}">
                  <div>
                     <a href="${contextPath}/category/view?cate=${item.categoryNo2}">${item.categoryName}</a>
                  </div>
                  </li>
               </c:when>

               <c:when test="${item.categoryNo2%10 eq 0}">
                  </li>
                  <li class="sidebar_categoty">${item.categoryName}
               </c:when>

               <c:otherwise>
                  <div>
                     <a href="${contextPath}/category/view?cate=${item.categoryNo2}">${item.categoryName}</a>
                  </div>
               </c:otherwise>

            </c:choose>


         </c:forEach>



      </ul>
   </div>