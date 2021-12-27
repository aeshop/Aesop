<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>





<jsp:include page="../common/r_header.jsp" />

<jsp:include page="../common/sidebar_n.jsp"></jsp:include>




			<head>
			
			<link rel="stylesheet" href="${contextPath}/resources/css/n_style.css">
			
			</head>

<fmt:parseNumber value="${100*(1-sessionScope.loginMember.memberGradeDiscount)}" var="discountRate" integerOnly="true"/>

<div id="n-order-container">

	<div id="n-membership-benefit-wrapper">
		<div class="n-infomation">
			<div class="n-membership">혜택정보</div>
			<div class="n-membership">
				<span id="n-membership-id"><strong>${sessionScope.loginMember.memberName}</strong></span>님은, <strong> [<span
					id="n-membership-rank">${sessionScope.loginMember.memberGradeName}</span>] </strong> 회원이십니다. <br>구매금액의
				<span id="n-membership-rate"><strong>${discountRate}</strong></span>%을 할인 받으실 수 있습니다.
			</div>

		</div>
	</div>
	<!-- 주문항목 테이블 -->
	<div id="n-shipping-wrapper">

		<ul id="n-shipping">
			<li>국내배송상품 ( <span>${orderCount}</span> )
			</li>
		</ul>
	</div>

	<div id="n-cart-info">
		<table class="n-cart-table">
			<thead>
				<tr>
					<th style="padding: 10px 0;"><input type="checkbox" id="allChk" onchange="checkAll(this)"></th>
					<th>이미지</th>
					<th>상품정보</th>
					<th>판매가</th>
					<th>수량</th>
					<th>배송비</th>
					<th>합계</th>
					<th>선택</th>

				</tr>
			</thead>
			<tbody>
				<c:choose>
				<c:when test="${orderCount eq 0}">
				<tr>
				<td colspan="8">
				<h3 style="color:red;font-size:1.2em;font-weight:bold;">장바구니에 담은 상품이 없습니다.</h3>
				</td>
				</tr>
				</c:when>
				
				<c:otherwise>
				
				<c:forEach items="${orderList}" var="index" varStatus="vs">
				<%--DB에서 정보 받아와서 화면에 뿌리기 hidden으로 주문번호 checkbox 앞에 숨겨둠 parentNode firstChild로 접근가능  --%>
					<tr>
					<td><input type="hidden" value="${index.orderNo}"><input type="checkbox" class="n-order-chk" value="${index.orderNo}"></td>
					<td><img src="${contextPath}${index.thumnailImgPath}${index.thumnailImgName}" style="margin: 6px 0;" alt="썸네일이미지"></td>
					<td>
					<a href="${contextPath}/product/productDetail?productNo=${index.productNo}">${index.productName}</a></td>
					<c:set var="price" value="${(1-index.productDiscount) *index.productPrice}"/>
					
 					
 					<td class="proPrice" width="100px;"><fmt:formatNumber value="${price}" maxFractionDigits="3"/>원</td>

					<td><span> <span class="n-qty-change"> 

					<input type="text" class="n-qty-no proAmount" value="${index.orderAmount}">
					<input type="hidden" value ="${index.productStock}"> 
					<a onclick="amountUp(this)"><img src="${contextPath}/resources/images/order/btn_quantity_up.gif"alt="수량Up버튼"></a>
				    <a onclick="amountDown(this)"><img src="${contextPath}/resources/images/order/btn_quantity_down.gif"alt="수량Down버튼"></a>

						</span></span></td>
					
					<c:if test="${vs.count eq 1}">
						<td width="100px;" rowspan="${orderCount}"><span class="n-shipCal"></span>원</td>					

					</c:if>	
						<td ><span class="orderPrice"></span>원</td>
					<td class="n-cart-btns"><a onclick="doOrder()"><img src="${contextPath}/resources/images/order/btn_order_p.png" style="margin: 0px 0px 10px 0;  height: 23.13px;" alt="주문하기버튼img"></a> 
					<a onclick="deleteOrder()"><img src="${contextPath}/resources/images/order/btn_delete2.gif"alt="삭제버튼img"></a></td>
				</tr>
				
				
				
				
				</c:forEach>
				
				
				
				</c:otherwise>
				
				</c:choose>
				
				
			</tbody>


			<tfoot>
			<tr><td colspan="5" style="text-align:left; padding-left: 20px;" >[기본배송]</td>
				<td colspan="3" style="padding: 10px 0;">상품구매금액 <span class="n-proCal"></span>+ 배송비 <span class="n-shipCal"></span> = 합계 : <span class="n-sumCal"></span>원</td></tr>
				
			</tfoot>
		</table>
		<div class="n-cart-ctl-btn">
			<span>선택상품을</span> <a onclick="deleteSelectedOrder()"><img src="${contextPath}/resources/images/order/btn_delete2.gif" alt="삭제하기버튼img"></a>
			<p>
				<a onclick="deleteAll()"><img src="${contextPath}/resources/images/order/btn_clear.gif" alt="장바구니 비우기버튼img"></a>
			</p>
		</div>
	</div>
	<!-- 총 주문금액 : 국내배송상품 -->
	<div class="n-summery">
		<table class="n-summery-table">
			<thead>
				<tr>
					<td>총 상품금액</td>
					<td>총 배송비</td>
					<td>총 결제예정금액</td>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td><span class="n-proCal"></span>원</td>
					<td>+ <span class="n-shipCal"></span>원</td>
					<td>=<span class="n-sumCal"></span>원</td>
				</tr>
			</tbody>

		</table>

	</div>

	<!-- 주문버튼 -->
	<div class="n-order-btn-wrapper">
		<div>
			<button class="btn btn-primary" onclick="orderAll(this)" style="background: black; border-color: black;">전체상품주문</button>
			<button class="btn btn-secondary" onclick="orderSelectedProduct()" style="border-color: black;  background: gray;">선택상품주문</button>
		</div>

		<p>
			<button class="btn btn-outline-secondary" style="width: 108px; font-size: 14px;"  onclick="location.href='${contextPath}/category/view';">쇼핑계속하기</button>
		</p>
	</div>
</div>


<jsp:include page="../common/r_footer.jsp" />
<script type="text/javascript" src="${contextPath}/resources/js/order/myCart.js"></script>

