<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

	<!-- header include -->
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	
	<!-- 메인 화면 이미지 -->
	<!-------------------------------------------------- 낙희 --------------------------------->
    <!-- 제품상세 페이지 -->
    <div class="content">
      <div class="productDetailHeader">
        <div>
          <img src="${contextPath}/resources/images/handcream1.webp">
          <div id="productInfo">
            <div>
            <input type="hidden" id="n-proNumber" value="${productNo}">
              <h2 style="margin-bottom: 20px;">레저렉션 아로마틱 핸드 밤</h2>
            </div>
            <div id="priceSection">
              <div id="productPrice">판매가</div>
              <span id="salePrice">27,900원</span> <!-- 상품금액 * 할인율 -->
              <span id="originPrice">31,000원</span> <!-- 상품금액, originPrice display: none 추가 -->
              <div id="discountRate">10%</div> <!-- 할인율 0일경우 #salePrice에 수식 X, discountRate display: none 추가 -->
            </div>
            <div id="deliveryFee">
              <span>배송비</span>
              <span>2,500원</span>
            </div>
            <div>
              <div id="productSize">
                사이즈
              </div>
              <select id="sizeSelect">
                <option value="msg" selected> - [필수] 옵션을 선택해 주세요 - </option>
                <option value="none" disabled> -------------------------------------- </option>
                <!-- DB에서 해당 상품 사이즈 목록 불러오기 -->
                <option value="75ml" > 75ml </option>
                <option value="120ml" > 120ml </option>
                <option value="500ml" > 500ml </option>
              </select>
              <div id="finalOrder">
                <table>
                  <tbody>
                    <tr>
                      <td>
                        <p>
                          레저렉션 아로마틱 핸드 밤
                          -
                          <span>75ml</span> <!-- 사이즈 75ml-->
                        </p>
                      </td>
                      <td>
                        <input type="number" value="1">
                      </td>
                      <td>
                        <span>31,000원</span> <!-- 상품금액(정가)-->
                      </td>
                      <td>
                        <a href="#none"> <!-- 삭제 -->
                          <img id="selectCancel" src="https://whoisnerdy.com/web/upload/images/detail_cancel_button.svg">
                        </a>
                      </td>
                    </tr>
                  </tbody>
                  <tfoot>
                    <tr>
                      <td>
                        <strong>총 상품금액(수량)</strong>
                        <strong>
                          <span>31,000원</span>
                          <span> (1개) </span>
                        </strong>
                      </td>
                    </tr>
                  </tfoot>
                </table>
              </div>
              <div id="orderBtn">
                <a href="#none" id="cartBtn">
                    <p>장바구니 담기</p>
                </a>
                <a href="#none" id="purchaseBtn">
                    <p>바로 구매</p>
                </a>
                <span class="btn_action soldout displaynone">SOLD OUT</span>
              </div>
            </div>
          </div>
        </div>
      </div> <!--productHeader end-->
      
    </div> <!-- content end-->
    <div>
      <p id="productTxt">무료 선물 포장 서비스<br>
        주문하신 모든 제품에 대해 선물 포장 서비스를 제공해 드립니다.</p>
    </div>
    <div id="productDescript"> <!-- 제품 설명-->
      <img src="${contextPath}/resources/images/handcream1-detail1.JPG">
    </div>
    
    <!-- 카카오 공유링크 아이콘-->
    <!-- <a id="create-kakao-link-btn" href="javascript:;">
      <img
      src="https://developers.kakao.com/assets/img/about/logos/kakaolink/kakaolink_btn_medium.png"
      />
    </a> -->
    
    <!-- 트위터, 페북 공유링크-->
    <!-- <a id="btnTwitter" class="btn px-1"><img src="images/twiter-icon.png"></a>     
    <a id="btnFacebook" class="btn px-1"><img src="images/facebook-icon.png"></a>  -->
		
		
		
	<!-- footer include -->
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>

		<!--  js 파일에서 사용을위한 script-->
		
		<script>
		const loginMemberNo = ${loginMember.memberNo};
		</script>
		
		
	
	<script type="text/javascript" src="${contextPath}/resources/js/product/productDetail.js"></script>
	
	
</body>
</html>