<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="${contextPath}/resources/css/member/member.css">


<jsp:include page="../common/r_header.jsp" />
	<jsp:include page="../common/sidebar_n.jsp" />

<div class="join_wrap">

    <div id="join">
      <div class="join_container">
        <div class="join_container_form">
          <div class="join_title"><h1>회원가입</h1></div>
          <fieldset>
            <div class="join_form">
              <div class="join_form_input" style="padding: 30px 0;">
                <button type="button" class="join_btn" style="margin-bottom: 15px;"
                onclick="location.href='${contextPath}/member/signup'">일반 회원가입</button>
                <!-- <button type="button" class="join_btn">카카오 회원가입</button> -->
                
              </div>
              
            </div>
          </fieldset>
        </div>


        <div class="join_container_img" >
			<img src="${contextPath}/resources/images/common/footer_img1.png" style="width:100%; height:100%;margin-left:30px;">
				
        </div>
      </div>
    </div>

  </div>


<!-- footer -->
<jsp:include page="../common/r_footer.jsp" />


</body>
</html>