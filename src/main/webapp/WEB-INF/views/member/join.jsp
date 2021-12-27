<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="${contextPath}/resources/css/member/member.css">
<jsp:include page="../common/r_header.jsp" />

<div class="join_wrap">

	<jsp:include page="../common/sidebar_n.jsp" />
    <div id="join">
      <div class="join_container">
        <div class="join_container_form">
          <div class="join_title">회원가입</div>
          <fieldset>
            <div class="join_form">
              <div class="join_form_input" style="padding: 30px 0;">
                <button type="button" class="join_btn" style="margin-bottom: 15px;"
                onclick="location.href='${contextPath}/member/signup'">일반 회원가입</button>
                <button type="button" class="join_btn">카카오 회원가입</button>
                
              </div>
              
            </div>
          </fieldset>
        </div>


        <div class="join_container_img" style="border: 1px solid black;">

        </div>
      </div>
    </div>

  </div>


<!-- footer -->
<jsp:include page="../common/r_footer.jsp" />


</body>
</html>