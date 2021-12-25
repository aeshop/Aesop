
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- header -->
<jsp:include page="r_header.jsp" />

<!-- 메인 화면 이미지 -->
<div class="main_container" style="border: 1px solid black;">
    <div class="sidebar" style="position: fixed;">
      <ul class="sidebar_ul">
      
      
      <%--
      application scope - category 순회하면서 데이터 표시 
      category 는 300 = ALL 부터 하위 소분류까지 나열된 LIST
      처음시작 = 
      300=ALL: 사이드바, li 태그의 시작, 안보여주고 싶으므로 var status.index = 0인경우 li 태그만
      
      중간 list-item 변경: 소분류에서 대분류로 변할때  li태그가 닫히고 열림 + 대분류명 적음
      
      끝 = varStatus.index = 전체 리스트 크기 -1 일때 소분류명을 div안에 넣고 li 태그를 닫음
	그외 = div 태그 안 소분류들
      
      --%>
  <c:forEach items="${applicationScope.category}" var="item" varStatus="vs"> 
          <c:choose>
          <c:when  test="${vs.index==0}">
        <li class="sidebar_categoty">  
          </c:when>
           
        
           <c:when  test="${vs.index==categorySize-1}">
           <div><a href="#">${item.categoryName}</a></div>
        	</li>    
          </c:when>
          
            <c:when  test="${item.categoryNo2%10 eq 0}">
                           	</li>                       
                            <li class="sidebar_categoty">
                             ${item.categoryName} 
                                                        
          </c:when>
          
          <c:otherwise>
                    <div><a href="#">${item.categoryName}</a></div>
          </c:otherwise>
          
          </c:choose>
         
 
  </c:forEach>
      
     
      
      </ul>
    </div>

    <!-- main 1페이지 -->
    <div class="main_container_1">
      <a>test</a>
    </div>
    <div style="padding: 40px 0; border: none;"></div>

    <!-- main 2페이지 -->
    <div class="main_container_2">
      <div>
        <ul>
          <li>#TAG</li>
          <li>#태연</li>
          <li>#플리스</li>
          <li>#벨벳</li>
          <li>#트랙</li>
        </ul>
      </div>


      <div>
        <div class="main_container_2_slide_left">
          <div id="main_container_2_slide_left_carousel" class="carousel carousel-dark slide" data-bs-ride="carousel">

            <!-- Bootstrap Carousel-->
            <div class="carousel-inner">
              <div class="carousel-item active main_slide_2" data-interval="false">
                test11111
              </div>
              <div class="carousel-item main_slide_2" data-interval="false">
                test22222
              </div>
              <div class="carousel-item main_slide_2" data-interval="false">
                test33333
              </div>
              <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleDark"
                data-bs-slide="prev">
                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                <span class="visually-hidden">Previous</span>
              </button>
              <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleDark"
                data-bs-slide="next">
                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                <span class="visually-hidden">Next</span>
              </button>
            </div>

          </div>
          <div class="main_container_2_slide_left_desc">
            <h4>test X test11111</h4>
            <p>test test test1111</p>
          </div>
        </div>

        <div class="main_container_2_slide_center">
          <div class="main_container_2_slide_center_image">
            slide EVENT
          </div>
          <div class="main_container_2_slide_center_desc">
            <h4>test X test</h4>
            <p>test test test</p>
          </div>

        </div>
        <div class="main_container_2_slide_center_hover">
          <div>1</div>
          <div>2</div>
          <div>3</div>
          <div>4</div>
        </div>

        <div class="main_container_2_slide_right">
          <div>
            slide EVENT
          </div>
          <div>
            <h4>test X test</h4>
            <p>test test test</p>
          </div>
        </div>

      </div>
    </div>


    <!-- main 3페이지 -->
    <div class="main_container_3">
      <div>
        <h3 class="Black_Border_Heading Common_Margin" style="margin-left: 165px;">
          <span>LOOKBOOK</span>
        </h3>
      </div>

      <div>
        <div class="main_container_3_slide_left">
          <div class="main_container_3_slide_left_img">


          </div>
          <div class="main_container_3_slide_left_desc">
            <h4>test X test11111</h4>
            <p>test test test1111</p>
          </div>
        </div>

        <div class="main_container_3_slide_center">
          <div class="main_container_3_slide_center_img">


          </div>
          <div class="main_container_3_slide_center_desc">
            <h4>test X test11111</h4>
            <p>test test test1111</p>
          </div>
        </div>

        <div class="main_container_3_slide_right">
          <div class="main_container_3_slide_right_img">


          </div>
          <div class="main_container_3_slide_right_desc">

          </div>
        </div>


      </div>
    </div>


    <!-- main 4페이지 -->
    <div class="main_container_4">
      <div style="height: 742px; margin-left: 110px; margin-top: 30px; display: flex; flex-direction: row; ">
        <div style="width: 45%; height: 710px;">
          <h3 class="Black_Border_Heading Common_Margin"><span style="text-decoration:  2.5px underline black;">AESOP
              VIDEO</span></h3>
          <div style="height: 300px; margin-bottom: 100px;">youtube</div>
          <div style="display: flex; flex-direction: row; justify-content: center;">
            <div style="width: 25%; height: 120px;">1</div>
            <div style="width: 25%; height: 120px;">2</div>
            <div style="width: 25%; height: 120px;">3</div>
          </div>
        </div>


        <div style="width: 45%; height: 710px;">
          <h3 class="Black_Border_Heading Common_Margin">
            <span style="text-decoration: 2.5px underline black;">
              WHO IS AESOP?
            </span>
          </h3>
          <div style="height: 600px;  display: flex; flex-direction: row; flex-wrap: wrap;">
            <div style="width: 43%;"></div>
            <div style="width: 43%;"></div>
            <div style="width: 43%;"></div>
            <div style="width: 43%;"></div>
          </div>
        </div>

      </div>
    </div>










  </div>

<!-- footer -->
<jsp:include page="r_footer.jsp"/>
</body>
</html>