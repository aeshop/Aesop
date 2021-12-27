<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
        <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

      


          
<jsp:include page="../common/r_header.jsp"></jsp:include>

<jsp:include page="../common/sidebar_n.jsp"></jsp:include>


            <head>
                
                <link rel="stylesheet" href="${contextPath}/resources/css/n_style.css">

            </head>

                <div id="n-list-container">
                    <div id="n-category-hero-banner">
                        <!-- 캐러셀: 안함 이미지 딴거박음-->
	
                        <a href="#"> <img src="${contextPath}/resources/images/category/aesopSearch.webp" alt="">

                        </a>
                        <div class="n-wrap">
                            <div class="n-title">
                                <strong>검색 페이지</strong>
                            </div>
                        </div>


                    </div>



                    <div id="n-navbar-wrapper">
                        <!-- 카테고리 네비게이션 바: 완성 -->
                        <div id="n-category-navbar">
                            <ul id="n-category-ul">

                                <c:forEach items="${category}" var="cate"> <%--카테고리 안들고왔는데 --%>
                                    <fmt:parseNumber var="cCn" value="${cate.currentCategoryNo/10}" integerOnly="true"/>
                                    	<fmt:parseNumber var="cN" value="${cate.categoryNo2/10}" integerOnly="true"/>
                                    <c:if test="${cate.categoryNo2%10==0}">
                                    	<c:choose>
                                    	
                                    	<c:when test="${cCn==cN}">
                                    <li><a href="search?keyword=${keyword}&cp=${pagination.currentPage}&cate=${cate.categoryNo2}" style="font-weight:bold;">${cate.categoryName}</a></li>
                                    	
                                    	</c:when>
                                    	
                                    	<c:otherwise>
                                    	<li><a href="search?keyword=${keyword}&cp=${pagination.currentPage}&cate=${cate.categoryNo2}">${cate.categoryName}</a></li>
                                    	
                                    	</c:otherwise>
                                    	</c:choose>

                                    </c:if>


                                </c:forEach>

                            </ul>

                        </div>

                        <div id="n-sort-navbar">
                        <div id="n-sort-wrapper">
                           <div onclick="showMethod()">${method_name} &#9660;</div>
                            <ul id="n-sort-ul">
                                <li><a href="search?keyword=${keyword}&cate=${category[0].currentCategoryNo}&sort_method=1">신상품</a></li>
                                <li><a href="search?keyword=${keyword}&cate=${category[0].currentCategoryNo}&sort_method=2">상품명</a></li>
                                <li><a href="search?keyword=${keyword}&cate=${category[0].currentCategoryNo}&sort_method=3">낮은가격</a></li>
                                <li><a href="search?keyword=${keyword}&cate=${category[0].currentCategoryNo}&sort_method=4">높은가격</a></li>
                            </ul>
						</div>
                        </div>
                    </div>
                  <!-- 소분류 네비게이션 바, 및 페이지네이션: 테스트 필요-->
                    
                    <c:if test="${category[0].currentCategoryNo!=300}">
                    <div id="n-sCategory-navbar-wrapper">
                    
                       <div id="n-sCategory-navbar">
                            <ul id="n-sCategory-ul">
                            
                            <c:forEach items="${category}" var="index">
                            <c:set var="lc1" value="${category[0].currentCategoryNo/10}"/>
                            <fmt:parseNumber type="number" var="largeCate" integerOnly="true" value="${lc1}"/>
                            <c:set var="lc2" value="${index.categoryNo2/10}"/>
                            <fmt:parseNumber type="number" var="indexCate" integerOnly="true" value="${lc2}"/>
                            
								<c:if test="${largeCate eq indexCate && largeCate*10!=index.categoryNo2}">
								                               <%--소수점으로 나오는 문제 해결, fmt: parseNumber를 사용한다
								                               그럼 이제 대분류가 섞여 나오는 문제 해결해야한다
								                               
								                               소분류 컬럼을 하나 추가하는 편이 훨씬 편하다, 어쨋든 지금 상황에서는 안되므로	
								                               이전에 parseNumber로 만들었던 정수:31 에 10을 곱한 값 : 310 320 ... 등
								                               이 대분류 310 320 이랑 같지 않을 경우에만 출력하도록 만들었다.							                               
								                                --%>
								
							<c:choose>
							<c:when test="${index.categoryNo2==index.currentCategoryNo}">
							<li><a href="search?keyword=${keyword}&cp=${pagination.currentPage}&cate=${index.categoryNo2}" style="font-weight:bold;">${index.categoryName}</a></li>
							
							</c:when>
							
							<c:otherwise>
								<li><a href="search?keyword=${keyword}&cp=${pagination.currentPage}&cate=${index.categoryNo2}">${index.categoryName}</a></li>							
							</c:otherwise>
							</c:choose>
							
							
								
								
								</c:if>

							</c:forEach>
                            
                            
                            </ul>

                        </div>
                   </div>
                        
                    </c:if>
                    
                 
                    
                    
                    
                    

                    <div id="n-product-list-wrapper">
                        <ul id="n-product-list-ul">
                            <!-- 상품 진열 리스트 아이템 시작: 반복문 -->
							<c:if test="${empty productList}">
							<h3 style="text-align:center;">상품이 존재하지 않습니다.</h3>
							</c:if>

                            <c:forEach items="${productList}" var="product">
					
					
                                <li class="n-anchorBox">
                                <c:if test="${product.stock eq 0}">
                                <c:set var="isSoldout" value="soldout"/>
                                </c:if>
                                
                                    <div class="n-thumbnail">
                                        <a href="${contextPath}/product/productDetail?productNo=${product.productNo}"><img src="${contextPath}${product.imgList[0].imgPath}${product.imgList[0].imgName}" alt="이미지들어가는곳" class="${isSoldout}"> </a>
                                    </div>
                                    <div class="n-discription">
                                        <div class="n-tag">
                                        
                                        <c:choose>
                                        <c:when test="${!empty isSoldout}">
                                         <span style="background-color: #fff; color: red; border:solid 1px red;">SOLD OUT</span>
                                        </c:when>
                                        <c:otherwise>
                                         <span style="background-color: red; color: #fff">-<fmt:formatNumber
										type="number" value="${product.discount*100}" /> %</span>
                                        </c:otherwise>
                                        </c:choose>
                                        
                                        </div>
                                        <strong class="n-name"> <a href="#"> <span>${product.productName}</span>
							</a>
							</strong>
                                        <div class="n-priceGroup">
                                            <div class="n-dc-rate">${product.discount*100}%</div>
                                            <fmt:formatNumber type="number" maxFractionDigits="3" value="${(1-product.discount)*product.price}" var="discountPrice" />
                                            <div class="n-price">${discountPrice}원</div>
                                            <fmt:formatNumber type="number" maxFractionDigits="3" value="${product.price}" var="originalPrice" />

                                            <div class="n-origin-price">${originalPrice}원</div>
                                        </div>
                                        <div class="n-ico-basket">
                                            <img src="${contextPath}/resources/images/category/cart.png" alt="장바구니 담기" onclick="addCart()">
                                        </div>
                                        <div class="n-review">
                                            <span class="n-star"> <svg xmlns="http://www.w3.org/2000/svg"
										xmlns:xlink="http://www.w3.org/1999/xlink" width="20"
										height="20" viewBox="0 0 20 20" class="star">
                                  <defs>
                                      <path id="star-full"
											d="M7.157 6.698l2.165-4.59a.743.743 0 0 1 1.358 0l2.165 4.59 4.84.74c.622.096.87.895.42 1.353l-3.503 3.57.827 5.044c.106.647-.544 1.141-1.1.835l-4.328-2.382-4.329 2.382c-.556.306-1.205-.188-1.099-.835l.826-5.044-3.502-3.57c-.45-.458-.202-1.257.42-1.352l4.84-.74z"></path>
                                  </defs>
                                  <use xlink:href="#star-full"></use>
                              </svg>

								</span> 
								<c:choose>
								<c:when test="${!empty product.scoreCount }">
								<span class="n-star-score">${product.scoreAvg}</span> <span class="n-reviewCount">(리뷰 ${product.scoreCount})</span>								
								</c:when>
								<c:otherwise>
								<span class="n-star-score">-</span> <span class="n-reviewCount">(리뷰 0)</span>
								
								</c:otherwise>
								
								</c:choose>
							
                                
                                        </div>
                                        <div class="n-soldout-icon"></div>
                                    </div>
                                </li>



                            </c:forEach>


                            <!-- 상품 진열 리스트 반복문 끝 -->

                        </ul>



                    </div>

 
					<!-- 페이지네이션 부분, 완성 -->
<br>
                        <div id="n-pagination-wrapper">
                            <ul class="n-pagination">
                                <!-- 첫째, 이전 리모콘 버튼 -->
                                <li><a class="page-link" href="search?keyword=${keyword}&cp=1&cate=${category[0].currentCategoryNo}&sort_method=${sort_method}">&lt;&lt;</a></li>
                                <li><a class="page-link" href="search?keyword=${keyword}&cp=${pagination.prevPage}&cate=${category[0].currentCategoryNo}&sort_method=${sort_method}">&lt;</a></li>



                                <c:forEach begin="${pagination.startPage}" end="${pagination.endPage}" step="1" var="index">

                                    <c:choose>
                                        <c:when test="${index==pagination.currentPage}">
                                            <li><a class="page-link" style="color:black;font-weight:bold;">${index}</a></li>

                                        </c:when>
                                        <c:otherwise>
                                            <li><a class="page-link" href="search?keyword=${keyword}&cp=${index}&cate=${category[0].currentCategoryNo}&sort_method=${sort_method}">${index}</a></li>

                                        </c:otherwise>
                                    </c:choose>



                                </c:forEach>


                                <!-- 다음, 마지막 리모콘 버튼 -->
                                <li><a class="page-link" href="search?keyword=${keyword}&cp=${pagination.nextPage}&cate=${category[0].currentCategoryNo}&sort_method=${sort_method}">&gt;</a></li>
                                <li><a class="page-link" href="search?keyword=${keyword}&cp=${pagination.maxPage}&cate=${category[0].currentCategoryNo}&sort_method=${sort_method}">&gt;&gt;</a></li>

                            </ul>

                        </div>
                            <!-- 페이지네이션 끝 -->


                </div>



<%-- <jsp:include page="../common/footer_n.jsp"></jsp:include>
 --%><jsp:include page="../common/r_footer.jsp"></jsp:include>

<script type="text/javascript">

document.querySelector('#searchKeyword').value = "${keyword}";



function checkLogined(){
	if("${sessionScope.loginMember.memberNo}"){
		return true;
	} else {
		return false;
	}
}





function showMethod(e){
	
document.getElementById('n-sort-ul').style.display="block";
}






	function addCart() {

	    if (!checkLogined()) {
	        alert("로그인 이후에 진행해 주세요");
	        return;
	    }

	    const productNo = document.getElementById('n-proNumber').value;
	    //제품번호 받아오기


	    $.ajax({

	        url: contextPath + "/product/addCart",
	        method: "POST",
	        data: {
	            productNo: productNo,
	            amount: 1,
	        },

	        success: function(result) {

	            if (result == 1) {

	                const moveToCart = confirm('상품이 장바구니에 담겼습니다. 이동하시겠습니까?');

	                if (moveToCart) {
	                    location.href = contextPath + '/order/view';
	                }

	            }



	        },

	        error: function(jqXHR, textStatus, errorThrown) {
	            console.log("ajax 통신-장바구니담기 중 오류 발생");
	            console.log(jqXHR.responseText);
	        }



	    });

	}


</script>
