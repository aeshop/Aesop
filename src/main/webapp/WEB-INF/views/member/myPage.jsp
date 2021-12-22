<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<jsp:include page="/WEB-INF/views/common/r_header.jsp"/>

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
              <div class="accrual-rate">
                  적립율 
                  ${sessionScope.loginMember.memberGradeDiscount}
                  %
              </div>
              <div class="grade-bar">
                  <div class="bar"> 
                      <div class="graph" id="myGrp" style="width: 20%;"></div>
                  </div> 
              </div>
              <div class="grade-guide">
                  <span>2원</span>
                  추가 구매시 다음달
                  <span>브론즈</span>
                  달성
              </div>
              <div class="member-benefit">
                  <a href="#">멤버십 등급별 혜택 보기</a>
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
                          <a href="${contextPath}/member/myPage/orderHistory">
                              <img src="${contextPath}/resources/images/cnh/images/myicon_01.png"> 주문/배송현황
                              <span class="count-icon">
                                  <span>
                                      <span class="count">1</span>
                                  </span>
                              </span>
                          </a>
                      </li>
                      <li>
                          <a href="${contextPath}/member/myPage/orderStatus">
                              <img src="${contextPath}/resources/images/cnh/images/myicon_02.png"> 취소/교환/반품 내역
                              <span class="count-icon">
                                  <span class="count">1</span>
                              </span>
                          </a>
                      </li>
                      <li>
                          <a href="${contextPath}/order/view">
                              <img src="${contextPath}/resources/images/cnh/images/wish_icon.png"> 장바구니
                              <span class="count-icon">
                                  <span class="count">1</span>
                              </span>
                          </a>
                      </li>
                      <li>
                          <a href="${contextPath}/member/myPage/addr">
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
                              <a href="${contextPath}/member/myPage/updateMember">
                                  <p>
                                      <img src="${contextPath}/resources/images/cnh/images/myinfo.png">
                                  </p>
                                  <p>회원정보 수정</p>
                              </a>
                          </td>
                          <td>
                              <a href="${contextPath}/member/myPage/myPageBoard">
                                  <p>
                                      <img src="${contextPath}/resources/images/cnh/images/board.png">
                                  </p>
                                  <p>게시물 관리</p>
                              </a>
                          </td>
                          <td>
                              <a href="${contextPath}/member/myPage/myPageBoard">
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
                  <tbody>
                      <tr>
                          <td class="order-date"> 2021-12-28</td>
                          <td class="order-img"> 
                              <a href="#">
                                  <img src="${contextPath}/resources/images/cnh/images/product1.webp">
                              </a>
                          </td>
                          <td class="order-product">
                              <a href="#">
                                  <strong>레버런스 아로마틱 핸드 워시</strong>
                              </a>
                          </td>
                          <td class="order-quantity">1</td>
                          <td class="order-no">
                              <a href="#"> <!-- 주문상세 내역 페이지로 이동-->
                                  20211211-0003782
                              </a>
                          </td>
                          <td class="order-price">
                              <strong>49,000원</strong>
                          </td>
                      </tr>
                  </tbody>
              </table>
              <div class="order-more"> <!-- 최근 주문 더보기 -->
                  <a href="${contextPath}/member/myPage/orderHistory"> <!-- 주문/배송현황 > 주문내역조회 html로 이동-->
                      더보기
                      <img src="${contextPath}/resources/images/cnh/images/more.png">
                  </a>
              </div>
          </div>
    </div>
  </div>

<!-- footer include -->
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>



</body>
</html>