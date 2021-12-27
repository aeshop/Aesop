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

	

	<!-- 주문항목 테이블 -->
	<div id="n-shipping-wrapper" style="margin:50px 0 0 0">

		<ul id="n-shipping" >
			<li> 주문 상세 내역
			</li>
			<li> 주문 번호 : <span> ${delivery.deliveryNo}</span> 
			</li>
		</ul>
	</div>
		          <div class="addr-register-box">
              <table class="addr-register-table" style="margin-top:0;">
                  <colgroup>
                      <col style="width: 120px;">
                      <col style="width: 1080px;">
                  </colgroup>
               
                  <tr>
                      <th>
                          성명
                      </th>
                      <td>
                         <!-- 해당 주문내역에 등록된 수령인 이름 불러오기 -->
                           ${delivery.receiverName}
                      </td>
                  </tr>
                  <tr>
                      <th>
                          주소
                      </th>
                      <td>
                          <!-- 해당 주문내역에 등록된 배송지 주소 불러오기 -->
                          ${delivery.zipCode}<br>
                          ${delivery.address1}<br>
                          ${delivery.address2}
                      </td>
                  </tr>
                  <tr>
                      <th>
                          휴대전화
                      </th>
                      <td>
                          <!--  해당 주문내역에 등록된 배송 폰번호 불러오기 (멤버 폰번호가 아님)-->
                          ${delivery.receiverPhone}
                      </td>
                  </tr>
              </table>
          </div>
	

	<div id="n-cart-info" style="margin-top: 50px;">
		<table class="n-cart-table">
			<thead>
				<tr>
					<th style="padding: 10px 0;">이미지</th>
					<th>상품정보</th>
					<th>판매가</th>
					<th>수량</th>
					<th>배송비</th>
					<th>할인금액</th>
					<th>합계</th>

				</tr>
			</thead>
			<tbody>
				
				<%--DB에서 정보 받아와서 화면에 뿌리기 hidden으로 주문번호 checkbox 앞에 숨겨둠 parentNode firstChild로 접근가능  --%>
					
					<c:forEach items="${orderList}" var="i" varStatus="vs">
					
					
					
						<tr>
						<td><img src="${contextPath}${i.productImgPath}${i.productImgNm}" style="margin: 6px 0;" alt="썸네일이미지"></td> <!-- src태그 안에다가 이미지 경로 넣기 -->
						<td><a href="${contextPath}/product/productDetail?productNo=${i.productNo}"></a>${i.productName}</td>
					<c:set var="price" value=""/>
					
 					
 					<td class="proPrice" width="100px;"><fmt:formatNumber value="${(1-i.productDiscount)*i.productPrice}" maxFractionDigits="3"/> <!-- 상품 정가 넣기 -->  원</td>

					<td> <!-- 수량 -->
						<span>
						 	<span class="n-qty-change"> 
						 		<!--  수량 입력 --> ${i.orderAmount}개
								<input type="hidden" value =""> <!-- 이건 왜있는지 모르겠어요 (정보얻어오려고 있는 것 같아서 둡니다) -->
							</span>
						</span>
					</td>
					<c:if test="${vs.index==0}">
					<td width="100px;" rowspan="${orderCount}"><span class="n-shipCal"><fmt:formatNumber value="${ship}" maxFractionDigits="3"/></span>원</td>		 <!-- 배송비 -->			
					</c:if>

					<c:if test="${vs.index==0}">
					<td width="100px;" rowspan="${orderCount}"><span><fmt:formatNumber value="${sum+ship- delivery.deliveryPrice}" maxFractionDigits="3"/></span>원</td>		 <!-- 할인 -->			
					</c:if>


						<td ><span><fmt:formatNumber value="${(1-i.productDiscount)*i.productPrice*i.orderAmount}" maxFractionDigits="3"/></span>원</td> <!--  합계  -->
				</tr>
					
					
					
					
					
					
					
					
					
					
					</c:forEach>
					
				
				
				
			</tbody>

		<fmt:formatNumber var="sum2" value="${sum}" maxFractionDigits="3"/>
		<fmt:formatNumber var="ship2" value="${ship}" maxFractionDigits="3"/>
			<tfoot>
			<tr><td colspan="5" style="text-align:left; padding-left: 20px;" >[기본배송]</td>
				<td colspan="3" style="padding: 10px 0;">상품구매금액 <span class="n-proCal"><fmt:formatNumber value="${sum}" maxFractionDigits="3"/></span>+ 배송비 <span class="n-shipCal"><fmt:formatNumber value="${ship}" maxFractionDigits="3"/></span> = 합계 : <span class="n-sumCal"><fmt:formatNumber value="${sum+ship}" maxFractionDigits="3"/></span>원</td></tr>
				
			</tfoot>
		</table>
		<div class="n-cart-ctl-btn">
		</div>
	</div>
	<!-- 총 주문금액 : 국내배송상품 -->
	<div class="n-summery">
		<table class="n-summery-table">
			<thead>
				<tr>
					<td>총 상품금액</td>
					<td>총 배송비</td>
					<td>총 할인금액</td>
					<td>총 결제금액</td>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td><span class="n-proCal"></span>${sum2}원</td> <!-- 총상품금액(원가) * 수량 -->
					<td>+ <span class="n-shipCal"></span>${ship2}원</td>  <!-- 총배송비-->
					<td>- <span class="n-shipCal"></span><fmt:formatNumber value="${sum+ship- delivery.deliveryPrice}" maxFractionDigits="3"/>원</td> <!-- 총할인금액 (멤버등급별)-->
					<td>=<span class="n-sumCal"></span><fmt:formatNumber value="${delivery.deliveryPrice}" maxFractionDigits="3"/>원</td> <!-- 총 결제금액 -->
				</tr>
			</tbody>

		</table>

	</div>

	<!-- 주문버튼 -->
	<div class="n-order-btn-wrapper">
		<div>
			<button type="button" class="btn btn-primary" style="background: black; border-color: black;">
				<a href="${contextPath}/myPage/orderHistory" style="color:white;">주문목록보기 </a>
			</button>
		</div>

	</div>
</div>


<jsp:include page="../common/r_footer.jsp" />
