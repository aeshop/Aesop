<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:include page="/WEB-INF/views/common/r_header.jsp"/>
<jsp:include page="/WEB-INF/views/common/sidebar_n.jsp"/>

    <!-- main 1페이지 -->
    <div class="content">
      <div class="order-menu">
          <ul class="order-title">
              <li class="selected">
                  <a href="#">
                      주문내역조회 (
                          <span>${fn:length(sessionScope.orderList)}</span>
                      )
                  </a>
              </li>
              <li>
                  <a href="${contextPath}/myPage/orderStatus">
                      취소/반품/교환 내역(
                          <span>${fn:length(sessionScope.orderStatusList)}</span>
                      )
                  </a>
              </li>
          </ul>
      </div>
      <div class="select-bar">
          <fieldset class="status-select">
              <form method="GET">
                  <select class="select-form">
                      <option value="all" selected> 전체 주문처리상태</option>
                      <option value="prepared"> 배송 준비중</option>
                      <option value="shipped"> 배송중</option>
                      <option value="shipped-complate"> 배송완료</option>
                      <option value="cancel"> 취소</option>
                      <option value="exchange"> 교환</option>
                      <option value="return"> 반품</option>
                  </select>
                  <!-- 달력 날짜선택 기능(datepicker)-->
              </form>
          </fieldset>
          <ul>
              <li>기본적으로 최근 3개월간의 자료가 조회되며, 기간 검색시 지난 주문내역을 조회하실 수 있습니다.</li>
              <li>주문번호를 클릭하시면 해당 주문에 대한 상세내역을 확인하실 수 있습니다.</li>
          </ul>
      </div>
      <div class="list-title">
          <p>주문 상품 정보</p>
      </div>
      <table class="order-list">
          <colgroup>
              <col style="width: 140px;">
              <col style="width: 110px;">
              <col style="width: 610px;">
              <col style="width: 80px;">
              <col style="width: 120px;">
              <col style="width: 120px;">
          </colgroup>
          <thead>
              <tr>
                  <th>
                  주문일자<br>
                  [주문번호]
                  </th>
                  <th>이미지</th>
                  <th>상품정보</th>
                  <th>수량</th>
                  <th>상품구매금액</th>
                  <th>주문처리상태</th>
              </tr>
          </thead>
          <tbody>
          
          	<c:choose>
          		<c:when test="${empty sessionScope.orderList }">
          			<tr>
	              		<td colspan=6> 주문내역이 없습니다.
	          		</tr>
          		</c:when>
          		<c:otherwise>
          			<c:forEach items="${sessionScope.orderList}" var="o">
          				<tr>
                  			<td class="order-num">
                    			  ${o.deliveryDt}
                    		  <p style="margin: 0;">
                       		 	 <a href="#">${o.deliveryNo}</a> 
                    	 	  </p>
                    	    <!--  주문 취소 버튼 ( 배송 준비중일 경우에만 보이게 설정 )-->
                   		     <a href="#" class="displaynone">
                         		 <img src="${contextPath}/resources/images/cnh/images/btn_order_cancel.gif">
                    	 	 </a>
                		  </td>
                  <td class="order-product-img">
                      <img src="${contextPath}${o.productImgPath}${o.productImgNm}">
                  </td>
                  <td>
                      <a href="${contextPath}/product/productDetail?productNo=${o.productNo}" style="text-decoration: none;">
                          <strong>
                              ${o.productName }
                          </strong>
                      </a>
                  </td>
                  <td>${o.orderAmount}</td>
                  <td class="order-product-price">
                      <strong> <fmt:formatNumber value="${o.productPrice * o.orderAmount}" groupingUsed="true"/> 원</strong>
                      
                  </td>
                  <td class="order-status">
                      <p style=" margin-bottom: 0px;"> ${o.orderStatusName} </p>
                  </td>
              </tr>
          			
          			</c:forEach>
          			
          		</c:otherwise>
          		
          	</c:choose>
         	  
              
          </tbody>
      </table>
      <div class="order-li-pagination">
          <a href="#" class="order-pagination"> 1 </a> <!-- 페이지네이션 자리-->
      </div>
      <div class="order-process">
          <img src="${contextPath}/resources/images/cnh/images/orderprocess.jpg">
      </div>
  </div>
  
  <!-- footer include -->
<jsp:include page="/WEB-INF/views/common/r_footer.jsp"/>


</body>
</html>