<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<jsp:include page="/WEB-INF/views/common/r_header.jsp"/>

<jsp:include page="/WEB-INF/views/common/sidebar_n.jsp"/>
    <!-------------------------------------------------- 낙희 --------------------------------->
    <!-- main 1페이지 -->
    <!-- content -->
    <div class="content">
      <div class="mypage-board-list">
          <div class="mypage-board-title">
              <p>
                  분류 선택
                  <select>
                      <option>작성 일자별</option>
                      <option>분류별</option>
                  </select>
              </p>
          </div>
          <div class="mypage-board-body">
              <table>
                  <colgroup>
                      <col style="width: 70px;">
                      <col style="width: 135px;">
                      <col style="width: 776px;">
                      <col style="width: 84px;">
                      <col style="width: 80px;">
                      <col style="width: 55px;">

                  </colgroup>
                  <thead>
                      <tr>
                          <th>번호</th>
                          <th>분류</th>
                          <th>제목</th>
                          <th>작성자</th>
                          <th>작성일</th>
                          <th>조회</th>
                      </tr>
                  </thead>
                  <tbody class="my-board-message"> <!-- 등록한 게시글 없을 경우: class="displaynone" -->
                      <tr>
                          <td colspan="6">게시물이 없습니다.</td>
                      </tr>
                  </tbody>
                  <tbody> <!-- 등록한 게시글 있을 경우 -->
                      <tr>
                          <td>2</td>
                          <td>
                              <a href="#">Q&A</a> <!-- Q&A일경우 문의하기 게시판 목록 페이지로 이동 / 리뷰일경우 리뷰목록 링크-->
                          </td>
                          <td class="left">
                              <a href="#">상품문의</a> <!-- 게시글 상세 내용 페이지로 이동-->
                          </td>
                          <td>
                              ㄴ**** <!-- 앞 첫글자만 보이고 뒤에는 **** 으로 ! -->
                          </td>
                          <td>
                              <span class="txt-num">2021-12-20</span> <!-- 작성일-->
                          </td>
                          <td>
                              <span class="txt-num">0</span> <!-- 조회 수-->
                          </td>
                      </tr>
                      <tr>
                          <td>1</td>
                          <td>
                              <a href="#">리뷰</a> <!-- 리뷰일경우 리뷰목록 링크-->
                          </td>
                          <td class="left">
                              <a href="#">리뷰남깁니다.</a> <!-- 게시글 상세 내용 페이지로 이동-->
                          </td>
                          <td>
                              ㄴ**** <!-- 앞 첫글자만 보이고 뒤에는 **** 으로 ! -->
                          </td>
                          <td>
                              <span class="txt-num">2021-12-20</span> <!-- 작성일-->
                          </td>
                          <td>
                              <span class="txt-num">0</span> <!-- 조회 수-->
                          </td>
                      </tr>
                  </tbody>
              </table>
          </div>
      </div>
      <div class="page-div"> <!-- pagination -->
          <a href="#" class="my-board-pagination"> 1 </a> <!-- 페이지네이션 자리-->
      </div>
      <form id="my-board-search" method="get" action="#" naem="myBoardSearch">
          <div>
              <fieldset style="border: none;">
                  <p>
                      <select id="my-search-select">
                          <option value="title">제목</option>
                          <option value="content">내용</option>
                      </select>
                      <input type="text" id="my-search-input">
                      <a href="#">
                          <img src="${contextPath}/resources/images/cnh/images/btn_find.gif">
                      </a>
                  </p>
              </fieldset>
          </div>
      </form>
    </div> <!--content end-->

  <!-- footer include -->
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>	
</body>
</html>