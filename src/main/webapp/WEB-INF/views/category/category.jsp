<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
        <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

            <!DOCTYPE html>


            <html lang="ko">

            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>제품 진열페이지</title>
                <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
                <!-- bootstrap5 -->
                <link rel="stylesheet" href="${contextPath}/resources/css/n_style.css">
                <!-- custom css -->

            </head>

            <body>
                <header> 헤더입니다</header>

                <div id="n-list-container">
                    <div id="n-category-hero-banner">
                        <!-- 원래는 이미지가 막 변경되고 그런거같은데, 안함 -->

                        <a href="#"> <img src="${contextPath}/resources/images/category/categoryBanner.jpg" alt="">

                        </a>
                        <div class="n-wrap">
                            <div class="n-label">new</div>
                            <div class="n-title">
                                up to 40% sale <br>
                                <strong>아직도 뭘 팔아야되는지 모르겠어오</strong>
                            </div>
                        </div>


                    </div>



                    <div id="n-navbar-wrapper">
                        <!-- 카테고리 네비게이션 바: 반복문 -->
                        <div id="n-category-navbar">
                            <ul id="n-category-ul">

                                <c:forEach items="${category}" var="cate">
                                    <c:if test="${cate.categoryNo%10==0}">
                                        <li><a href="view?cp=${pagination.currentPage}&cate=${cate.categoryNo}">${cate.categoryName}</a></li>

                                    </c:if>


                                </c:forEach>

                            </ul>

                        </div>
                        <!-- 정렬방식 네비게이션 바 -->
                        <div id="n-sort-navbar">
                            <ul id="n-sort-ul">
                                <li><a href="#">신상품</a></li>
                                <li><a href="#">상품명</a></li>
                                <li><a href="#">낮은가격</a></li>
                                <li><a href="#">높은가격</a></li>
                            </ul>

                        </div>
                    </div>

                    <div id="n-product-list-wrapper">
                        <ul id="n-product-list-ul">
                            <!-- 상품 진열 리스트 아이템 시작: 반복문 -->
                            <c:forEach items="${productList}" var="product">

                                <li class="n-anchorBox">
                                    <div class="n-thumbnail">
                                        <a href="링크"><img src="${contextPath}${product.imgList[0].imgPath}${product.imgList[0].imgName}" alt="이미지들어가는곳"> </a>
                                    </div>
                                    <div class="n-discription">
                                        <div class="n-tag">
                                            <span style="background-color: #000; color: #fff">시즌오프</span> <span style="background-color: red; color: #fff"><fmt:formatNumber
										type="number" value="${product.discount*100}" /> %</span>
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
                                            <img src="${contextPath}/resources/images/category/cart.png" alt="장바구니 담기" onclick="">
                                        </div>
                                        <div class="n-review">
                                            <span class="n-star"> <svg
										xmlns="http://www.w3.org/2000/svg"
										xmlns:xlink="http://www.w3.org/1999/xlink" width="20"
										height="20" viewBox="0 0 20 20" class="star">
                                  <defs>
                                      <path id="star-full"
											d="M7.157 6.698l2.165-4.59a.743.743 0 0 1 1.358 0l2.165 4.59 4.84.74c.622.096.87.895.42 1.353l-3.503 3.57.827 5.044c.106.647-.544 1.141-1.1.835l-4.328-2.382-4.329 2.382c-.556.306-1.205-.188-1.099-.835l.826-5.044-3.502-3.57c-.45-.458-.202-1.257.42-1.352l4.84-.74z"></path>
                                  </defs>
                                  <use xlink:href="#star-full"></use>
                              </svg>

								</span> <span class="n-star-score"></span> <span class="n-reviewCount">(244)</span>
                                        </div>
                                        <div class="n-soldout-icon"></div>
                                    </div>
                                </li>



                            </c:forEach>









                            <!-- 상품 진열 리스트 반복문 끝 -->

                        </ul>



                       



                    </div>

 
<!-- 페이지네이션 부분 -->

                        <div id="n-pagination-wrapper">
                            <ul class="n-pagination">
                                <!-- 첫째, 이전 리모콘 버튼 -->
                                <li><a class="page-link" href="view?cp=1">&lt;&lt;</a></li>
                                <li><a class="page-link" href="view?cp=${pagination.prevPage}">&lt;</a></li>



                                <c:forEach begin="${pagination.startPage}" end="${pagination.endPage}" step="1" var="index">

                                    <c:choose>
                                        <c:when test="${index==pagination.currentPage}">
                                            <li><a class="page-link" style="color:black;font-weight:bold;">${index}</a></li>

                                        </c:when>
                                        <c:otherwise>
                                            <li><a class="page-link" href="view?cp=${index}">${index}</a></li>

                                        </c:otherwise>
                                    </c:choose>



                                </c:forEach>




                                <!-- 다음, 마지막 리모콘 버튼 -->
                                <li><a class="page-link" href="view?cp=${pagination.nextPage}">&gt;</a></li>
                                <li><a class="page-link" href="view?cp=${pagination.maxPage}">&gt;&gt;</a></li>

                            </ul>

                        </div>
                            <!-- 페이지네이션 끝 -->


                </div>



                <footer> 푸터입니다</footer>

                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
                <!-- bootstrap5 -->


                <script type="text/javascript">
                    <%--
console.log(${pagination.currentPage});
console.log(${pagination.nextPage});
console.log(${pagination.endPage});

--%>
                </script>
            </body>

            </html>