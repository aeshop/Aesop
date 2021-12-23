<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>





<jsp:include page="../common/header_n.jsp"></jsp:include>

<jsp:include page="../common/sidebar_n.jsp"></jsp:include>




			<head>
			
			<link rel="stylesheet" href="${contextPath}/resources/css/n_style.css">
			
			</head>




    <div id="n-order-container">


        <form id="n-order-frm" action="">





            <div id="n-membership-benefit-wrapper">
                <div class="n-infomation">
                    <div class="n-membership">혜택정보</div>
                    <div class="n-membership"><span id="n-membership-id">${sessionScope.loginMemberName}</span>님은, [<span id="n-membership-rank">${sessionScope.loginMemberGradeName}</span>] 회원이십니다. <br>구매금액의 <span id="n-membership-rate">${discountRate}</span>%을 할인 받으실 수 있습니다.</div>

                </div>
            </div>
            <div id="n-shipping-alert">
                <div><img src="${contextPath}/resources/images/order/ico_info.gif" alt="느낌표 아이콘"> 상품의 수량 변경은 상품상세 또는 장바구니에서 가능합니다.</div>
            </div>
            <div id="n-shipping-wrapper">

                <div class="n-order-row">
                <%--결제 페이지에 들어가는 즉시 controller는 DB와 연결해서 주문번호를 생성하고 받아옴 20211223-0123 이런 형태 --%>
                    <div>국내배송상품 주문내역</div><input type="hidden" id="delivery-no" value = "${deliveryNo}">
                    <p><img src="${contextPath}/resources/images/order/btn_prev.gif" alt="이전페이지 버튼"></p>

                </div>
            </div>

            <div id="n-cart-info">
                <table class="n-cart-table">
                    <thead>
                        <tr>
                            <th><input type="checkbox" onclick=""></th>
                            <th>이미지</th>
                            <th>상품정보</th>
                            <th>판매가</th>
                            <th>수량</th>
                            <th>배송비</th>
                            <th>합계</th>

                        </tr>
                    </thead>
                    <tbody>
                    
                    <c:forEach items="${orderList}" var="index" varStatus="vs">
                     	<tr>
                            <td>
                                <input type="checkbox" onclick="">
                            </td>
                            <td>
                                 <img src="${contextPath}${index.thumnailImgPath}${index.thumnailImgName}" alt="물품이미지">
                            </td>
                            <td><a href="">${index.productName}</a>
                               
                            </td>
                            <td><fmt:formatNumber maxFractionDigits="3">${index.productPrice*index.productDiscount}</fmt:formatNumber>원</td>

                            <td>
                                ${index.orderAmount}
                            </td>
                            <c:if test="${vs.index==0}">
                            <td rowspan="${orderCount}">자바스크립트</td>
                            
                            </c:if>
                            <c:set var="calculratedPrice" value="${index.productPrice*index.productDiscount*index.orderAmount}"/>
                            <td>
                            <input type="hidden" class="rowPrice" value="${calculratedPrice}">
                            <fmt:formatNumber maxFractionDigits="3">${calculratedPrice}</fmt:formatNumber>원</td>

                        </tr>
                    </c:forEach>
                    
                       

                    </tbody>
                    <tfoot>
                        <tr id="n-p-summery">
                            <td>[기본배송]</td>
                            <td colspan="6">상품구매금액 <span class="allProPrice"></span> + 배송비 <span class="shipPrice"></span> = 합계 : <span class="calPrice"></span>원 </td>
                        </tr>
                    </tfoot>


                </table>

                <div class="n-order-row n-delete">
                    <div>선택상품을 <img src="${contextPath}/resources/images/order/btn_delete2.gif" alt="삭제하기 버튼"></div>
                    <p><img src="${contextPath}/resources/images/order/btn_prev.gif" alt="이전페이지 버튼"></p>

                </div>
            </div>


            <!-- 주문 정보 -->

            <div id="n-order-info">
                <div id="n-order-wrapper">
                    <div class="n-order-row">
                        <div>주문 정보</div>
                        <p><img src="${contextPath}/resources/images/order/ico_required.gif" alt="required 빨간 별">필수입력사항</p>

                    </div>

                    <table class="n-order-table">
                        <tr>
                            <td>주문하시는 분<img src="${contextPath}/resources/images/order/ico_required.gif" alt="required 빨간 별"></td>
                            <td>
                                <div><input type="text" name="oName" size="10" value="${loginMember.memberName}" required></div>
                            </td>
                        </tr>
                        <tr>
                            <td>휴대전화<img src="${contextPath}/resources/images/order/ico_required.gif" alt="required 빨간 별"></td>
                            <td><select name="phone1" id="oPhone1" required>
                            <option value="010" selected>010</option>
                            <option value="011">011</option>
                            <option value="016">016</option>
                            <option value="017">017</option>
                            <option value="018">018</option>
                            <option value="019">019</option>
                          </select> -
                          <c:set var="mPhone" value="${loginMember.memberPhone}"></c:set>
                          <!-- 013-4856-1382 -->
                                <input type="text" name="oPhone2" size="10" value="${fn:substring(mPhone, 4, 8)}"  required> -
                                <input type="text" name="oPhone3" size="10" value="${fn:substring(mPhone, 9, 13)}" required>
                            </td>
                        </tr>
                        <tr>
                      <c:set var="mEmail" value="${loginMember.memberEmail}"/>




                            <td rowspan="3">이메일<img src="${contextPath}/resources/images/order/ico_required.gif" alt="required 빨간 별"></td>
                            <td><input type="text" name="oEmail1" value='${fn:substringBefore(mEmail, "@")}'> @
                                <input type="text" name="oEmail2" value='${fn:substringAfter(mEmail, "@")}'>
                                <select name="oEmail3">
                                <option value="selected">이메일 선택</option>
                                <option value="naver.com">naver.com</option>
                                <option value="daum.net">daum.net</option>
                                <option value="nate.com">nate.com</option>
                                <option value="gmail.com">gmail.com</option>
                                <option value="etc">직접입력</option>
                              </select>
                            </td>

                        </tr>
                        <tr>

                            <td>-이메일을 통해 주문처리과정을 보내드립니다.</td>
                        </tr>
                        <tr>

                            <td>-이메일 주소란에는 반드시 수신가능한 이메일주소를 입력해 주세요.</td>
                        </tr>
                    </table>

                </div>

            </div>


            <!-- 배송 정보 -->
            <div id="n-delivery-info">
                <div id="n-delivery-wrapper">
                    <div class="n-order-row">
                        <div>배송 정보</div>
                        <p><img src="${contextPath}/resources/images/order/ico_required.gif" alt="required 빨간 별">필수입력사항</p>

                    </div>
                    <table class="n-order-table">
                        <tr>
                            <td>
                                <div>배송지 선택</div>
                            </td>
                            <td colspan="2">
                                <input type="radio" id="sameaddr0" name="addr" value="sameaddr0" checked>
                                <label for="sameaddr0">주문자 정보와 동일</label>
                                <input type="radio" id="sameaddr1" name="addr" value="sameaddr1">
                                <label for="sameaddr1">새로운 배송지</label>
                          

                                <span><img src="${contextPath}/resources/images/order/btn_address.gif" alt="주소록 버튼"></span>

                            </td>
                        </tr>
                        <tr>
                            <td>받으시는 분<img src="${contextPath}/resources/images/order/ico_required.gif" alt="required 빨간 별"></td>
                            <td>
                                <input type="text" name="rName" size="10" value="${loginMember.memberName}">
                            </td>
                        </tr>
                        <tr>
                            <td rowspan="3">주소<img src="${contextPath}/resources/images/order/ico_required.gif" alt="required 빨간 별"></td>
                            <td><input type="text" name="rZipcode" value="${defaultAddress.zipCode}"> <img src="${contextPath}/resources/images/order/btn_zipcode.gif" alt="우편번호 버튼이미지"></td>
                        </tr>
                        <tr>
                            <td><input type="text" name="rAddr1" size="70" maxlength="100" value="${defaultAddress.address1}"><span class="n-addr">기본주소</span></td>
                        </tr>
                        <tr>
                            <td><input type="text" name="rAddr2" size="70" maxlength="100"  value="${defaultAddress.address1}"><span class="n-addr"></span>나머지주소(선택입력가능) <span style="color:red;">상세주소(동/호수)를 꼭 기입 바랍니다</span></span>
                            </td>

                        </tr>
                        <tr>
                            <td>휴대전화<img src="${contextPath}/resources/images/order/ico_required.gif" alt="required 빨간 별"></td>
                            <td><select name="phone1" id="rPhone1" required>
                          <option value="010" selected>010</option>
                          <option value="011">011</option>
                          <option value="016">016</option>
                          <option value="017">017</option>
                          <option value="018">018</option>
                          <option value="019">019</option>
                        </select> -
                      <c:set var="aPhone" value="${defaultAddress.addrPhone}"/>
                                <input type="text" name="rPhone2" size="10" value="${fn:substring(aPhone, 4, 8)}"  required> -
                                <input type="text" name="rPhone3" size="10"  value="${fn:substring(aPhone, 9, 13)}"  required></td>

                        </tr>

                        <tr>
                            <td>배송메세지</td>
                            <td colspan="2">
                                <textArea name="rMessage" cols="160" rows="5">

                        </textArea>
                            </td>
                        </tr>
                    </table>

                </div>

            </div>

            <!-- 결제 예정 금액 -->


            <div id="n-pay-info">
                <div id="n-pay-wrapper">
                    <div class="n-order-row">
                        <div>결제 예정 금액</div>

                    </div>
                    <table id="n-cal-table">
                        <tr>
                            <td>총 주문 금액 <img src="${contextPath}/resources/images/order/btn_list.gif" alt="내역보기 이미지 버튼"></td>
                            <td>총 할인 + 부가결제 금액</td>
                            <td>총 결제예정 금액</td>
                        </tr>
                        <tr>
                            <td><span class="calPrice"></span>원</td>
                            <td>-123,456원 </td>
                            <td>=123,456원 </td>

                        </tr>
                    </table>
                    <table id="n-discount-table">
                        <tr>
                            <td>총 할인 금액</td>
                            <td>111,111원</td>
                        </tr>


                        <tr>
                            <td>추가 할인 금액</td>
                            <td><img src="${contextPath}/resources/images/order/btn_list.gif" alt="내역보기"></td>
                        </tr>
                    </table>

                </div>

            </div>

            <!-- 결제 수단 -->

            <!-- 카드결제 api 이용 예정  사용법 익혀야함 -->






    <p>아임 서포트 결제 모듈 테스트 해보기</p>
    <button id="check_module" type="button">아임 서포트 결제 모듈 테스트 해보기</button>





            <!-- 카드사 직접결제 -->

        </form>

    </div>



<jsp:include page="../common/footer_n.jsp"></jsp:include>
<script type="text/javascript" src="${contextPath}/resources/js/order/myCart.js"></script>
<!-- 아임포트 CDN -->
<script src="https://cdn.iamport.kr/js/iamport.payment-1.1.8.js" type="text/javascript"></script>
 <script type="text/javascript" src="${contextPath}/resources/js/order/payment.js"></script>
 
<%--  <script type="text/javascript" src="${contextPath}/resources/js/order/payment_2_내작업본.js"></script>
 --%>