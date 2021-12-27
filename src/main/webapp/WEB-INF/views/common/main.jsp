<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- header -->
<jsp:include page="r_header.jsp" />

<!-- 메인 화면 이미지 -->
<div class="main_container">
	<div class="sidebar_wrapper">

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
	           <div><a href="${contextPath}/category/view?cate=${item.categoryNo2}">${item.categoryName}</a></div>
	        	</li>    
	          </c:when>
	          
	            <c:when  test="${item.categoryNo2%10 eq 0}">
	                           	</li>                       
	                            <li class="sidebar_categoty">
	                             ${item.categoryName} 
	                                                        
	          </c:when>
	          
	          <c:otherwise>
	                    <div><a href="${contextPath}/category/view?cate=${item.categoryNo2}">${item.categoryName}</a></div>
	          </c:otherwise>
	          
	          </c:choose>
	         
	 
	  </c:forEach>
	      
	     
	      
	      </ul>
	    </div>
	</div>
	<!-- main 1페이지 -->
	<div class="main_container_1">
		<img src="${contextPath}/resources/images/cnh/images/kakao.png"
			style="display: none;">
		<video autoplay muted loop style="width: 100%;">
			<source
				src="https://player.vimeo.com/external/638938995.hd.mp4?s=11da203a3eb6cefd91f8fbf6af2dacc20d102060&amp;profile_id=175"
				type="video/mp4">
		</video>
	</div>
	<div style="padding: 40px 0; border: none;"></div>

	<!-- main 2페이지 -->
	<div class="main_container_2">
		<div>
			<ul>
				<li>#TAG</li>
				<li>#이솝</li>
				<li>#이솝우화</li>
				<li>#화장품</li>
				<li>#자연</li>
			</ul>
		</div>


		<div>
			<div class="main_container_2_slide_left" style="overflow: hidden;">
				<div id="main_container_2_slide_left_carousel"
					class="carousel carousel-dark slide" data-bs-ride="carousel">

					<div class="carousel-inner">
						<div class="carousel-item active main_slide_2"
							data-interval="false">
							<img
								src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS1BezfYnOgM5zeSwLbkb3movHq6sFyBaCcCA&usqp=CAU">
						</div>
						<div class="carousel-item main_slide_2" data-interval="false">
							<img
								src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSZtKczGJQ2zHUcd53jhMLOO7IC9OLfiltsyA&usqp=CAU" />
						</div>
						<div class="carousel-item main_slide_2" data-interval="false">
							<img
								src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRnJ257V3Wci6Nk7HmCAnMWyS2s_CgvcsljiQ&usqp=CAU" />
						</div>
					</div>

				</div>




				<div class="main_container_2_slide_left_desc">
				<br><br>
					<h4>이솝</h4>
				</div>
			</div>

			<div class="main_container_2_slide_center">
				<div class="main_container_2_slide_center_image">
					<img
						src="${contextPath}/resources/images/common/main_item_img1.png"
						style="width: 100%; height: 100%;">
				</div>
				<div class="main_container_2_slide_center_desc">
				<br><br>
					<h4>더 포레저</h4>
					<p>생기를 북돋아주는 바디 케어 필수품 2종</p>
				</div>

			</div>
			<div class="main_container_2_slide_center_hover">
				<img
						src="${contextPath}/resources/images/common/main_item_img1.png"
						style="width: 100%; height: 100%;">
			</div>

			<div class="main_container_2_slide_right">
				<div>
					<img
						src="${contextPath}/resources/images/common/main_item_img2.png"
						style="width: 100%; height: 100%;">
				</div>
				<div>
				<br><br>
					<h4>더 애드보캣</h4>
					<p>핸드와 바디 케어 필수품 4종</p>
				</div>
			</div>

		</div>
	</div>


	<!-- main 3페이지 -->
	<div class="main_container_3">
		<div>
			<h3 class="Black_Border_Heading Common_Margin"
				style="margin-left: 165px;">
				<span>STORE LOCATOR</span>
			</h3>
		</div>

		<div>
			<div class="main_container_3_slide_left">
				<div class="main_container_3_slide_left_img">
					<img src="${contextPath}/resources/images/common/main_item2_img1.png" style="width:100%; height:100%;">
				</div>
				<div class="main_container_3_slide_left_desc">
					<br><br>
					<p>대한민국, 미국, 이탈리아, 프랑스, 독일, 스위스, 러시아, 대만, 홍콩, 스웨덴, 노르웨이, 브라질, 아랍에미리트, 영국에 235개 이상의 회사 소유의 시그니쳐 매장을 운영하고 있다. </p>
					
				</div>
			</div>

			<div class="main_container_3_slide_center">
				<div class="main_container_3_slide_center_img">
					<img src="${contextPath}/resources/images/common/main_item2_img2.png" style="width:100%; height:100%;">
				</div>
				<div class="main_container_3_slide_center_desc" >
					<br><br>
					<p>미국에서는 바니스 뉴욕, 노르트스트롬과 삭스5번가에서도 제품을 구매할 수 있다. 
					캐나다에서는 홀트 렌프루 , 및 오길비, 노드스트롬, 삭스 5번가에서 이솝 제품을 만나 볼 수 있다.</p>
				</div>
			</div>

			<div class="main_container_3_slide_right">
				<div class="main_container_3_slide_right_img">
					<img src="${contextPath}/resources/images/common/main_item2_img3.png" style="width:100%; height:100%;">
				</div>
				<div class="main_container_3_slide_right_desc">
					<br><br>
					<p>가장 큰 매장은 로스엔젤레스 다운타운에 위치한 브로드웨이 극장가에 있는 1,085평방 피트(약 30평) 규모의 공간으로, 벽면에는 인근 패션중심지에서 버린 패브릭 롤을 재생한 마분지 통이 붙어있다. 가게 내부의 다른 요소들 모두 재활용 종이로 만들어져 있다.</p>
				</div>
			</div>


		</div>
	</div>


	<!-- main 4페이지 -->
	<div class="main_container_4">
		<div
			style="height: 742px; margin-left: 110px; margin-top: 30px; display: flex; flex-direction: row;">
			<div style="width: 45%; height: 710px;">
				<h3 class="Black_Border_Heading Common_Margin">
					<span style="text-decoration: 2.5px underline black;">AESOP
						VIDEO</span>
				</h3>
				<div style="height: 400px;">
					<iframe width="100%" height="100%"
						src="https://www.youtube.com/embed/o7L-3ZOhVsY"
						title="YouTube video player" frameborder="0"
						allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
						allowfullscreen> </iframe>
				</div>
				<div
					style="display: flex; flex-direction: row; justify-content: center;">
					<div style="width: 25%; height: 140px;">
						<img src="${contextPath}/resources/images/common/youtube_img1.png"
							style="width: 100%; height: 100%;">
					</div>
					<div style="width: 25%; height: 140px;">
						<img src="${contextPath}/resources/images/common/youtube_img2.png"
							style="width: 100%; height: 100%;">
					</div>
					<div style="width: 25%; height: 140px;">
						<img src="${contextPath}/resources/images/common/youtube_img3.png"
							style="width: 100%; height: 100%;">
					</div>
				</div>
			</div>


			<div style="width: 45%; height: 710px;">
				<h3 class="Black_Border_Heading Common_Margin">
					<span style="text-decoration: 2.5px underline black;"> WHO
						IS AESOP? </span>
				</h3>
				<div id="whoIsAesop" style="height: 600px; display: flex; flex-direction: row; flex-wrap: wrap;">
					<div style="width: 43%; height:30%;" >
						<img src="${contextPath}/resources/images/common/whoisaesop_img1.png"
							style="width: 100%; height: 100%;">
							</div>
					<div style="width: 43%; height:30%;">
						<h4>브랜드 스토리</h4>
						<P>이솝은 세심하게 고안된 효과적이면서도, 감각적인 즐거움을 선사하는 스킨, 헤어, 바디 케어 제품을 제공합니다.</P>
							</div>
					<div style="width: 43%; height:33%;">
					<img src="${contextPath}/resources/images/common/whoisaesop_img5.png"
							style="width: 100%; height: 100%;">
					</div>
					<div style="width: 43%; height:33%;">
					<h4>이솝은 1987년에 설립되었고 멜버른에 본사를 두고 있으며 세계 다수 지역에 사무실과 스토어를 보유하고 있습니다.</h4>
					<p>이솝은 지적이고 지속가능한 디자인에 대하여 진정성 있는 관심을 가지고 있으며 이는 이솝의 모든 분야로 이어집니다.</p>
					</div>
				</div>
			</div>

		</div>
	</div>



</div>

<!-- footer -->
<jsp:include page="r_footer.jsp" />

<script>
//쿠키 불러오기
	function getCookie(name) 
	{ 
	    var obj = name + "="; 
	    var x = 0; 
	    while ( x <= document.cookie.length ) 
	    { 
	        var y = (x+obj.length); 
	        if ( document.cookie.substring( x, y ) == obj ) 
	        { 
	            if ((endOfCookie=document.cookie.indexOf( ";", y )) == -1 ) 
	                endOfCookie = document.cookie.length;
	            return unescape( document.cookie.substring( y, endOfCookie ) ); 
	        } 
	        x = document.cookie.indexOf( " ", x ) + 1; 
	        
	        if ( x == 0 ) break; 
	    } 
	    return ""; 
	}
	
	
    $(function(){    
    	
        if(getCookie("divpop1") !="Y"){
        	window.open('${contextPath}/pop','','width=500%, height=682px')
        }
    });
</script>
</body>
</html>