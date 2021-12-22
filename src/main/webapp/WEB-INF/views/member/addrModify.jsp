<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<jsp:include page="/WEB-INF/views/common/r_header.jsp"/>

    <!-------------------------------------------------- 낙희 --------------------------------->
    <!-- main 1페이지 -->
    <!-- content -->
    <div class="content">
      <form>
          <table class="addr-table">
              <colgroup>
                  <col style="width: 30px;">
                  <col style="width: 100px;">
                  <col style="width: 120px;">
                  <col style="width: 250px;">
                  <col style="width: 620px;">
                  <col style="width: 80px;">
              </colgroup>
              <thead>
                  <tr>
                      <th>
                          <span>
                              <input type="checkbox">
                          </span>
                      </th>
                      <th>배송지명</th>
                      <th>수령인</th>
                      <th>휴대전화</th>
                      <th>주소</th>
                      <th>수정</th>
                  </tr>
              </thead>
              <tbody> <!-- 등록된 주소 없을경우 : class="displaynone" 추가하여 안보이게 할 예정-->
                  <tr>
                      <td colspan="6" class="addr-message" >등록된 주소가 없습니다.</td>
                  </tr>
              </tbody> 
              <tbody class="addr-list"> <!-- 등록된 주소 있을 경우 -->
                  <tr>
                      <td>
                          <input type="checkbox">
                      </td>
                      <td>
                          집
                      </td>
                      <td>
                          <span>이솝</span>
                      </td>
                      <td>
                          <span>010-1111-1111</span>
                      </td>
                      <td class="addr">
                          (
                          <span> 06183 </span>
                          )
                          <span>서울특별시 강남구 도곡로57길 12 (역삼동)</span> 
                          <span>역삼2차아이파크</span>   
                      </td>
                      <td>
                          <a href="addrModifyEdit.html">
                              <img src="${contextPath}/resources/images/cnh/images/btn_address_modify.gif">
                          </a>
                      </td>
                  </tr>
              </tbody>
          </table>
          <div class="addr-btn">
              <span class="addr-btn1">
                  <a href="#none"> <!-- 등록된 주소록이 없을시 .displaynone-->
                      <img src="${contextPath}/resources/images/cnh/images/btn_address_delete.gif">
                  </a>
              </span>
              <span class="addr-btn2">
                  <a href="/aesop_MyPage/addrRegister_cnh.html">
                      <img src="${contextPath}/resources/images/cnh/images/btn_address_register.gif">
                  </a>
              </span>
          </div>
      </form>
      <div class="addr-notice">
          <h3>배송주소록 유의사항</h3>
          <div>
              <ol>
                  <li class="num1">배송 주소록은 최대 10개까지 등록할 수 있으며, 별도로 등록하지 않을 경우 최근 배송 주소록 기준으로 자동 업데이트 됩니다.</li>
                  <li class="num2">자동 업데이트를 원하지 않을 경우 주소록 고정 선택을 선택하시면 선택된 주소록은 업데이트 대상에서 제외됩니다.</li>
                  <li class="num3">기본 배송지는 1개만 저장됩니다. 다른 배송지를 기본 배송지로 설정하시면 기본 배송지가 변경됩니다.</li>
              </ol>
          </div>
      </div>
  </div> <!--content end-->    


  <!-- footer include -->
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
	
</body>
</html>