<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<jsp:include page="/WEB-INF/views/common/r_header.jsp"/>
<jsp:include page="/WEB-INF/views/common/sidebar_n.jsp"/>

    <!-------------------------------------------------- 낙희 --------------------------------->
    <!-- main 1페이지 -->
    <div class="content">
    
      <div class="myshopMain">
          <div class="grade">
              <div class="grade-text">
                  <strong>
                      <span class="member-name">${sessionScope.loginMember.memberName}</span>
                      회원
                  </strong>
                  님은
                  <br>
                  <span class="grade-name">${sessionScope.loginMember.memberGradeName}</span>
                  등급입니다.
              </div>
          </div>
          <div class="grade-detail">
              <div class="grade-detail-text">
                  <span>${sessionScope.loginMember.memberGradeName}</span>
                  등급 혜택
              </div>
              <div class="accrual-rate" style="letter-spacing: 0.0006px;">
                  현재 등급의 할인율이
                 <strong>${ Math.floor((1 - sessionScope.loginMember.memberGradeDiscount) *100) } <!-- 1 - memberGradeDiscount *100 -->
                  % </strong> 입니다. 아래 <strong>‘멤버십 등급별 혜택 보기’</strong>로 혜택을 확인해 보세요!
              </div>
              <div class="grade-bar">
                  <div class="bar"> 
                  	<c:choose>
                  	
                  		<c:when test="${sessionScope.grade.leftMoney  >= 300000  }">
	                    	<div class="graph" id="myGrp" style="width: 0%;"></div>
                  		</c:when>
                  		<c:when test="${sessionScope.grade.leftMoney > 200000 } ">
                  			<div class="graph" id="myGrp" style="width: 70%;"></div>
                  		</c:when>
                  		<c:when test="${sessionScope.grade.leftMoney > 100000 || sessionScope.grade.leftMoney < 0 } ">
                  			<div class="graph" id="myGrp" style="width: 30%;"></div>
                  		</c:when>
                  		<c:otherwise>
                  		</c:otherwise>
                  	</c:choose>
                  </div> 
              </div>
              <div class="grade-guide">
              
			  <c:choose>
              	<c:when test="${empty sessionScope.grade.leftMoney}">
              		<%-- ${sessionScope.grade.memberPurchaseAmount}원 --%>
              		<fmt:formatNumber value="${sessionScope.grade.memberPurchaseAmount}" groupingUsed="true"/>원
              	</c:when>
              	<c:otherwise>
                  <span><fmt:formatNumber value="${sessionScope.grade.leftMoney}" groupingUsed="true"/>원 </span>
              	</c:otherwise>
              </c:choose>
              
                  추가 구매시 다음달
                  <span>${sessionScope.grade.memberGradeName }</span>
                  달성
              </div>
              <div class="member-benefit">
                  <a id="modal" style="cursor:pointer;">멤버십 등급별 혜택 보기</a>
                  
                  <div class="n_modal">
                  	<div class="modal_content" title="클리갛면 창이 닫힙니다.">
                  	  <img src="${contextPath}/resources/images/cnh/images/mypage_pop.png">
                  	</div>
                  </div>
                  
              </div>
          </div>
      </div>
      <div class="myshopping">
          <div class="side">
              <div class="myinfo">
                  <div class="my-title">
                      나의 쇼핑 정보
                  </div>
                  <ul>
                      <li>
                          <a href="${contextPath}/myPage/orderHistory">
                              <img src="${contextPath}/resources/images/cnh/images/myicon_01.png"> 주문/배송현황
                              <c:if test="${!empty sessionScope.result }">
                              <span class="count-icon">
                                  <span>
	                                    <span class="count">
				 							${sessionScope.result}
										</span>
                                  </span>
                              </span>
                              </c:if>
                          </a>
                      </li>
                      <li>
                          <a href="${contextPath}/myPage/orderStatus">
                              <img src="${contextPath}/resources/images/cnh/images/myicon_02.png"> 취소/교환/반품 내역
                              <!-- <span class="count-icon">
                                  <span class="count">1</span>
                              </span> -->
                          </a>
                      </li>
                      <li>
                          <a href="${contextPath}/order/view">
                              <img src="${contextPath}/resources/images/cnh/images/wish_icon.png"> 장바구니
                            <!--   <span class="count-icon">
                                  <span class="count">1</span>
                              </span> -->
                          </a>
                      </li>
                      <li>
                          <a href="${contextPath}/myPage/addr">
                              <img src="${contextPath}/resources/images/cnh/images/myicon_03.png"> 배송지 수정
                          </a>
                      </li>
                  </ul>
              </div>
              <div class="myShopping_activity">

                  <div class="my-title">나의 쇼핑 활동</div>
                  <table style="width: 250px;">
                      <tr>
                          <td>
                              <a href="${contextPath}/myPage/updateMember">
                                  <p>
                                      <img src="${contextPath}/resources/images/cnh/images/myinfo.png">
                                  </p>
                                  <p>회원정보 수정</p>
                              </a>
                          </td>
                          <td>
                              <a href="${contextPath}/board/notice/list?search_key=member_name&search=${loginMember.memberName}&c=801">
                                  <p>
                                      <img src="${contextPath}/resources/images/cnh/images/board.png">
                                  </p>
                                  <p>리뷰 관리</p>
                              </a>
                          </td>
                          <td>
                              <a href="${contextPath}/board/notice/list?search_key=member_name&search=${loginMember.memberName}&c=802">
                                  <p>
                                      <img src="${contextPath}/resources/images/cnh/images/qna.png">
                                  </p>
                                  <p>문의 내역</p>
                              </a>
                          </td>
                      </tr>
                  </table>
              </div>
          </div>
          <div class="recent-order">
              <div class="my-title2">최근 주문</div>
              <table class="mypage-table" style="width: 100%;">
                  <thead>
                      <tr>
                          <th class="order-date" style="width: 135px;">주문일자</th>
                          <th class="order-img" style="width: 92px;">이미지</th>
                          <th class="order-product" style="width: auto;">상품정보</th>
                          <th class="order-quantity" style="width: 60px;">수량</th>
                          <th class="order-no" style="width: 135px;">주문번호</th>                                
                          <th class="order-price"style="width: 110px;">상품구매금액</th>
                      </tr>
                  </thead>
                  <tbody style="font-weight: 300;">
                  		<!--  로그인한 멤버의 주문내역이 없는경우, 아닌경우 -->
                  			<c:choose>
                  				<c:when test="${empty sessionScope.orderList}">
                  					<tr>
	                      			<td colspan=6> 주문내역이 없습니다.
	                    		    </tr>
                  				</c:when>
                  				<c:otherwise>
			                  		<c:forEach items="${sessionScope.orderList}" var="o" begin="0" end="2">
                  						<tr>
				                          <td class="order-date"> ${o.deliveryDt}</td>
				                          <td class="order-img"> 
				                              <a href="#">
				                                  <img src="${contextPath}${o.productImgPath}${o.productImgNm}">
				                              </a>
				                          </td>
				                          <td class="order-product">
				                              <a href="${contextPath}/product/productDetail?productNo=${o.productNo}">
				                                  <strong>${o.productName }</strong>
				                              </a>
				                          </td>
				                          <td class="order-quantity">${o.orderAmount }</td>
				                          <td class="order-no">
				                              <a href="${contextPath}/myPage/orderDetail?deNo=${o.deliveryNo }"> <!-- 주문상세 내역 페이지로 이동-->
				                                  ${o.deliveryNo }
				                              </a>
				                          </td>
				                          <td class="order-price">
				                              <strong>
				                              	<fmt:formatNumber value="${o.productPrice * o.orderAmount}" groupingUsed="true"/> 원
				                              </strong>
				                              
				                          </td>
                      					</tr>
			                  		</c:forEach>
                  				</c:otherwise>
                  			</c:choose>
                  		
	                 	 

                     
                      
                  
                  </tbody>
              </table>
              <div class="order-more"> <!-- 최근 주문 더보기 -->
                  <a href="${contextPath}/myPage/orderHistory"> <!-- 주문/배송현황 > 주문내역조회 html로 이동-->
                      더보기
                      <img src="${contextPath}/resources/images/cnh/images/more.png">
                  </a>
              </div>
          </div>
    </div>
  </div>

<!-- footer include -->
<jsp:include page="/WEB-INF/views/common/r_footer.jsp"/>
<script type="text/javascript" src="${contextPath}/resources/js/member/myPage/modal.js"></script>


</body>
</html>