 //다음 주소 api
 function sample6_execDaumPostcode() {
     new daum.Postcode({
         oncomplete: function(data) {
             // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

             // 각 주소의 노출 규칙에 따라 주소를 조합한다.
             // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
             var addr = ''; // 주소 변수
             var extraAddr = ''; // 참고항목 변수

             //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
             if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                 addr = data.roadAddress;
             } else { // 사용자가 지번 주소를 선택했을 경우(J)
                 addr = data.jibunAddress;
             }

             // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
             if (data.userSelectedType === 'R') {
                 // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                 // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                 if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
                     extraAddr += data.bname;
                 }
                 // 건물명이 있고, 공동주택일 경우 추가한다.
                 if (data.buildingName !== '' && data.apartment === 'Y') {
                     extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                 }
                 // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                 if (extraAddr !== '') {
                     extraAddr = ' (' + extraAddr + ')';
                 }
             }

             // 우편번호와 주소 정보를 해당 필드에 넣는다.
             document.getElementById('sample6_postcode').value = data.zonecode;
             document.getElementById("sample6_address").value = addr;
             // 커서를 상세주소 필드로 이동한다.
             document.getElementById("sample6_detailAddress").focus();
         }
     }).open();
 }

 /* 유효성 검사 상황 판단 객체 */

 const checkValObj = {
     pwd1: false,
     pwd2: false,
     //  tel: false,
     //  email: false,
 };

 /* 비밀번호 유효성 검사 해주는 함수 */
 const pwd1 = document.querySelector('#pwd1');
 const pwd2 = document.querySelector('#pwd2');
 const pw1ConfirmMsg = document.querySelector('#pw1ConfirmMsg');
 const pw2ConfirmMsg = document.querySelector('#pw2ConfirmMsg');
 const regexPw = /^[a-zA-Z\d\!\@\#\-\_]{4,12}$/;

 pwd1.addEventListener('input', function() {
     pwd2.value = "";

     if (!regexPw.test(pwd1.value)) {
         pw1ConfirmMsg.innerText = '비밀번호를 조건에 맞게 작성해 주세요.';
         pw1ConfirmMsg.style.color = 'red';
         checkValObj.pwd1 = false;
     } else {
         pw1ConfirmMsg.innerText = '유효한 비밀번호입니다.';
         pw1ConfirmMsg.style.color = 'green';
         checkValObj.pwd1 = true;

     }

 });
 pwd2.addEventListener('input', function() {
     if (pwd1.value === pwd2.value) {
         pw2ConfirmMsg.innerText = '비밀번호가 일치합니다.';
         pw2ConfirmMsg.style.color = 'green';
         checkValObj.pwd2 = true;


     } else {
         pw2ConfirmMsg.innerText = '비밀번호가 일치하지 않습니다.';
         pw2ConfirmMsg.style.color = 'red';
         checkValObj.pwd2 = false;

     }

 });




 /* 유효성 검사 진행해서 모두 만족하면 true 반환하는 함수 */
 function validate() {
     if (checkValObj.pwd1 && checkValObj.pwd2) {
         return true;
     }
     alert('정보를 정확히 입력해 주세요.');
     return false;

 }

 // 회원정보 수정 버튼 클릭시 post로 제출 함수

 /* form인데 제출되지 않는 상황이라 왜 그런지 알아야 한다. */

 //  document.querySelector('#editSubmitBtn').addEventListener('click', function() {

 //      const submitForm = document.forms.update_form;

 //      submitForm.method = 'POST';

 //      if (validate()) {
 //          submitForm.submit();
 //      }
 //  });

 function updateMemberInfo() {
     const submitForm = document.forms.update_form;

     submitForm.method = 'POST';

     if (validate()) {
         submitForm.submit();
     }
 }

 /*폼담은 변수.submit() 하면 이후에는 submit되지 않는다, 개발자가 이미 해당 form에서 해야 할 것을 다 끝내 놓았다고 가정하기 때문에
    
     참고 사이트 : https://velog.io/@longroadhome/%EB%AA%A8%EB%8D%98JS-%EB%B8%8C%EB%9D%BC%EC%9A%B0%EC%A0%80-%ED%8F%BC%EA%B3%BC-%ED%8F%BC-%EC%A1%B0%EC%9E%91
         */


 //널디 회원탈퇴 함수 -confirm 창으로 정말로 탈퇴할껀지 물어보고, 탈퇴하고 끝냄
 //탈퇴 : 회원정보의 상태변경, 세션의 만료, alert
 function secession() {

     if (confirm('정말로 탈퇴하시겠습니까?')) {

         location.href = contextPath + '/myPage/secession';
     }

 }