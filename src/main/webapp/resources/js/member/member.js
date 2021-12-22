// 로그인 시 유효성 검사
function loginValidate(){

    const memberId = document.getElementById("memberId");
    const memberPw = document.getElementById("memberPw");

    if(memberId.value.trim().length == 0){
        alert("아이디를 입력해주세요.");
        memberId.focus();
        return false;
    }

    if(memberPw.value.trim().length == 0){
        alert("비밀번호를 입력해주세요.");
        memberPw.focus();
        return false;
    }

};

