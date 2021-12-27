<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

        <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

            <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

                <!DOCTYPE html>
                <html lang="ko">

                <head>
                    <meta charset="UTF-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1.0">
                    <title>배송 주소록 관리</title>

                    <link rel="stylesheet" href="${contextPath}/resources/css/n_style.css">



                </head>

                <body>


                    <div id="addr-popup-container">


                        <div id="addr-popup-title">배송주소록 관리</div>

                        <div id="addr-caution-container">
                            <div id="caution-title">배송주소록 유의사항</div>

                            <div id="caution-content">
                                <p>-배송 주소록은 최대 10개까지 등록할 수 있으며, 별도로 등록하지 않을 경우 최근 배송 주소록 기준으로 자동 업데이트 됩니다.</p>
                                <p>-자동 업데이트를 원하지 않을 경우 주소록 고정 선택을 선택하시면 선택된 주소록은 업데이트 대상에서 제외됩니다.</p>
                                <p>-기본 배송지는 1개만 저장됩니다. 다른 배송지를 기본 배송지로 설정하시면 기본 배송지가 변경됩니다.</p>
                            </div>

                        </div>



                        <div id="addr-list">
                            <table>
                                <tr id="addr-thead">
                                    <th>배송지명</th>
                                    <th>우편번호</th>
                                    <th>수령인</th>
                                    <th>휴대전화</th>
                                    <th>주소</th>
                                    <th>배송지관리</th>
                                </tr>
                                <!-- 여기부턴 forEach -->
                                
                                <c:forEach items="${addrList}" var="addr" varStatus="vs">
                                
                                <tr class="addr-list-content-${vs.index}">
                                    <td>
                                   <span>${addr.addressName}</span>
                                     <c:if test="${addr.isDefault=='Y'}">
                                    <span style="color:white;background-color:skyblue;padding:5px;">기본</span>                                    
                                    </c:if>                                   
                                    </td>                                    
                                    <td><span>${addr.zipCode}</span></td>
                                    <td><span>${addr.receiverName}</span></td>
                                    <td><span>${addr.addrPhone}</span></td>
                                    <td class="addressName"><span>${addr.address1}</span> <span>${addr.address2}</span></td>
                                    <td>
                                        <img src="${contextPath}/resources/images/order/btn_address_apply.gif" alt="적용이미지" onclick="setParentAddress(event)">
                                    </td>
                                </tr>
                                
                                </c:forEach>
                                
                           
                            </table>

                        </div>

                        


                    </div>

<script type="text/javascript" src="${contextPath}/resources/js/order/applyAddr.js"></script>


                </body>

                </html>