
const flag1 = true; // id
const flag2 = true; // pw
const flag3 = true; // email

const signUpCheckObj = {
  id: false,
  name: false,
  email: false,
  pwd1: false,
  pwd2: false,
  phone3: false 
};

function validate() {
  for (key in signUpCheckObj) {
    if (!signUpCheckObj[key]) {
      let message;

      switch (key) {
        case "id":
          message = "아이디가 유효하지 않습니다.";
          break;
        case "name":
          message = "이름이 유효하지 않습니다.";
          break;
        case "email":
          message = "이메일이 유효하지 않습니다.";
          break;
        case "pwd1":
          message = "비밀번호가 유효하지 않습니다.";
          break;
        case "pwd2":
          message = "비밀번호가 일치하지 않습니다.";
          break;
        case "phone3":
          message = "전화번호가 일치하지 않습니다.";
          break;
      }
      alert(message);

      document.getElementById(key).focus();

      return false;
    }
  }
}


// 아이디 유효성 검사
document.getElementById("id").addEventListener("input", function (e) {
  const inputId = this.value;

  const regExp = /^[a-zA-Z0-9]{6,12}$/;

  const checkId = document.getElementById("checkId");

  // 유효성 검사
  if (inputId.length == 0) {
	document.getElementById("checkId").innerText = "";
	
    signUpCheckObj.id = false; // 유효x (기록)
  } else if (regExp.test(inputId)) {
  $.ajax({ 
    url : "idDupCheck",
    data : {"inputId" : inputId},
    type : "GET", 

    success : function (result) {

      // console.log(result);
      if (result == 0) {
        checkId.innerText = "사용가능한 아이디입니다.";
        checkId.style.color = "green";
        signUpCheckObj.id = true;

      } else {
        checkId.innerText = "중복된 아이디입니다.";
        checkId.style.color = "red";
        signUpCheckObj.id = false;
      }
    },

    error : function () {
      console.log(e);
    },

    complete : function () {
      
    }
  });

  } else {
    $(checkId).text("아이디가 유효하지 않습니다.").css("color", "red");

    signUpCheckObj.id = false;
  }
});


// 이름 유효성 검사
document.getElementById("name").addEventListener("input", function () {
  const inputName = this.value;

  const regExp = /^[가-힣]{2,7}$/;

  const checkName = document.getElementById("checkName");

  // 유효성 검사
  if (this.value.length == 0) {
    $(checkName).text("");

    signUpCheckObj.name = false;
  } else if (regExp.test(inputName)) {
    checkName.innerText = "유효한 이름입니다.";
    checkName.style.color = "green";

    signUpCheckObj.name = true;
  } else {
    checkName.innerText = "유효하지 않은 이름입니다.";
    checkName.style.color = "red";

    signUpCheckObj.name = false;
  }
});

// 이메일 유효성 검사
document.getElementById("email").addEventListener("input", (e) => {
  const inputEmail = e.target.value; // 입력받은 이메일
  const regExp = /^[\w]{4,}@[\w]+(\.[\w]+){1,3}$/;
  const checkEmail = document.getElementById("checkEmail");

  if (inputEmail.length == 0) {
    checkEmail.innerText = "";

    signUpCheckObj.email = false;
  } else if (regExp.test(inputEmail)) {
    $.ajax({
      url : "emailDupCheck", // 필수 속성
      type : "GET",
      data : {"inputEmail" : inputEmail}, // 파라미터
      success : function (result) {
        if (result == 0) {
          checkEmail.innerText = "사용가능한 이메일입니다.";
          checkEmail.style.color = "green";

          signUpCheckObj.email = true;
        } else {
          checkEmail.innerText = "중복된 이메일입니다.";
          checkEmail.style.color = "red";

          signUpCheckObj.email = false;
        }
      },

      error : function (request, status, error) {
        // console.log(request);
        // console.log(status);
        if( request.status == 404){
          console.log("ajax 요청 주소가 올바르지 않습니다.");
        }
        else if( request.status == 500){
          console.log("서버 내부 에러 발생");
          console.log(request.responseText);
        }
      },

      complete : function () {
        console.log("complete 수행");
        
      }

    });
  } else {
    checkEmail.innerText = "유효하지않은 이메일입니다.";
    checkEmail.style.color = "red";

    signUpCheckObj.email = false;
  }
});

// 비밀번호 유효성 검사
document.getElementById("pwd1").addEventListener("input", function() {
  const inputPw = this.value;
  const regExp = /^[a-zA-Z\d\!\@\#\-\_]{7,12}$/;
  const checkPwd1 = document.getElementById("checkPwd1");

  if (inputPw.length == 0) {
    checkPwd1.innerText = "";

    signUpCheckObj.pwd1 = false;
  } else if (regExp.test(inputPw)) {
    checkPwd1.innerText = "유효한 비밀번호입니다.";
    checkPwd1.style.color = "green";

    signUpCheckObj.pwd1 = true;
    
  } else {
    checkPwd1.innerText = "유효하지않은 비밀번호입니다.";
    checkPwd1.style.color = "red";

    signUpCheckObj.pwd1 = false;
  }
});

$("#pwd2, #pwd1").on("input", function (e) {
  const pwd1 = document.getElementById("pwd1").value;
  const pwd2 = document.getElementById("pwd2").value;
  const checkPwd2 = document.getElementById("checkPwd2");

  if (pwd2.length == 0) {
    checkPwd2.innerText = "";

    signUpCheckObj.pwd2 = false;
  } else if (pwd1 == pwd2) {
    checkPwd2.innerText = "비밀번호가 일치합니다.";
    checkPwd2.style.color = "green"
    signUpCheckObj.pwd2 = true;
  } else {
    checkPwd2.innerText = "비밀번호가 일치하지 않습니다.";
    checkPwd2.style.color = "red"
    signUpCheckObj.pwd2 = false;
  }
});
