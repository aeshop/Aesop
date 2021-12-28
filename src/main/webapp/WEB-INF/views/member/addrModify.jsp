<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<jsp:include page="/WEB-INF/views/common/r_header.jsp"/>
<jsp:include page="/WEB-INF/views/common/sidebar_n.jsp"/>

    <!-------------------------------------------------- 낙희 --------------------------------->
    <!-- main 1페이지 -->
    <!-- content -->
    <div class="content">
      <form>
          <table class="addr-table">
              <colgroup>
                  <col style="width: 30px;">
                  <col style="width: 150px;">
                  <col style="width: 120px;">
                  <col style="width: 250px;">
                  <col style="width: 570px;">
                  <col style="width: 80px;">
              </colgroup>
              <thead>
               <tr>
                      <th>
                          <span>
                              <input type="checkbox" id="addr-chk" onchange="checkSub(this)">
                          </span>
                      </th>
                      <th>배송지명</th>
                      <th>수령인</th>
                      <th>휴대전화</th>
                      <th>주소</th>
                      <th>수정</th>
                  </tr>
              </thead>
                    <c:choose>
                       <c:when test="${empty sessionScope.addrList}">
                           <tbody> 
                              <tr>
                                  <td colspan="6" class="addr-message">등록된 주소가 없습니다.</td>
                              </tr>
                          </tbody> 
                       </c:when>
                       <c:otherwise>
                       <tbody class="addr-list">
                      
                          <c:forEach items="${sessionScope.addrList}" var="addr" varStatus="vs">
                            <tr>
                               <td>
                                   <input type="checkbox" class="addr-chk-sub">
                                   <input type="hidden" class="addrNo" value = "${addr.addrNo}">                                   
                               </td>
                               <td>
                                <c:choose>
                                   <c:when test="${addr.defaultAddress == 'N' }">
                                      ${addr.addrName }
                                   </c:when>
                                   <c:otherwise>
                                        <img src="${contextPath}/resources/images/cnh/images/ico_addr_default.gif">
                                        ${addr.addrName }
                                   </c:otherwise>
                                </c:choose>
                               </td>
                               <td>
                                   <span>${addr.addrReceiverName }</span>
                               </td>
                               <td>
                                   <span>${addr.addrPhone }</span>
                               </td>
                               <td class="addr">
                                   (
                                   <span> ${addr.zipCode } </span>
                                   )
                                   <span>${addr.address1 }</span> 
                                   <span>${ addr.address2}</span>   
                               </td>
                               <td>
                                   <a href="${contextPath}/myPage/addr/edit?idx=${vs.index}">
                                       <img src="${contextPath}/resources/images/cnh/images/btn_address_modify.gif">
                                   </a>
                                   
                               </td>
                           </tr>
                          
                          
                          </c:forEach>
                       </tbody>
                          
                       </c:otherwise>
                    </c:choose>
                 
          </table>
          <div class="addr-btn">
              <c:if test="${!empty sessionScope.addrList}">
                    <span class="addr-btn1" >
                     <a style="cursor:pointer;"> <!-- 등록된 주소록이 없을시 .displaynone-->
                         <img src="${contextPath}/resources/images/cnh/images/btn_address_delete.gif" onclick="delChkAddr()" >
                     </a>
                    </span>
                 </c:if>
              <span class="addr-btn2">
                  <a style="cursor:pointer;">
                      <img src="${contextPath}/resources/images/cnh/images/btn_address_register.gif" onclick="addrRegister()">
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
<jsp:include page="/WEB-INF/views/common/r_footer.jsp"/>

<script type="text/javascript" src="${contextPath}/resources/js/member/myPage/addrModify.js"></script>
<!-- <script>

 function addrRegister(){
   if( ${fn:length(sessionScope.addrList)} == 10){
      alert("배송지는 최대 10개까지만 등록할 수 있습니다.");
      
      document.getElementById("addrRegisterBtn").setAttribute('href',' # ');

   }
} 
   
</script> -->
   
</body>
</html>