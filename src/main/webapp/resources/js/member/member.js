var checkObj = {
	id: false,
	name: false,
	email: false,
	pwd1: false,
	pwd2: false,
	phone3: false,
	emailConfirm: false,
	newPwd1: false,
	newPwd2: false
};



$("#id").on("input", function() {
	const regExp = /^[a-zA-Z0-9]{6,12}$/;
	const inputId = $("#id").val();

	if (inputId.length == 0) {
		$("#checkId").text("");
		checkObj.id = false;

	} else{
		if (regExp.test(inputId)) {
			$.ajax({
				url: "idDupCheck",
				data: { "id": inputId },
				type: "POST",

				success: function(result) {
					// console.log(result);

					if (result > 0) {
						$("#checkId").text("이미 사용중인 아이디 입니다.").css("color", "rgb(146 26 255)");
						checkObj.id = false;

					} else {
						$("#checkId").text(inputId + " 은 사용 가능한 아이디 입니다.").css("color", "rgb(146 26 255)");
						checkObj.id = true;
					}
				},

				error: function(e) {
					console.log(e);

				}
			});

		} else {
			$("#checkId").text("영어, 숫자 6~12글자로 작성해주세요.").css("color", "rgb(146 26 255)");
			checkObj.id = false;
		}
	}
});


// 비밀번호 유효성 검사
$("#pwd1").on("input", function(){
	const inputPw = $("#pwd1").val();
	const regExp = /^[a-zA-Z\d\!\@\#\-\_]{4,12}$/;

	if (inputPw.length == 0) {
		$("#checkPwd1").text("");

		checkObj.pwd1 = false;
	} else if (regExp.test(inputPw)) {
		$("#checkPwd1").text("");

		checkObj.pwd1 = true;

		$("#pwd1,  #pwd2").on("input", function() {
			const pwd11 = $("#pwd1").val();
			const pwd22 = $("#pwd2").val();

			if (pwd11.trim() == "" && pwd22.trim() == "") {
				$("#checkPwd1, #checkPwd2").text("");

				checkObj.pwd2 = false;

			} else if (pwd11 == pwd22) {
				$("#checkPwd2").text("비밀번호가 일치합니다.").css("color", "rgb(146 26 255)");

				checkObj.pwd2 = true;

			} else {
				$("#checkPwd2").text("비밀번호 불일치").css("color", "rgb(146 26 255)");

				checkObj.pwd2 = false;
			}
		});


	} else {
		$("#checkPwd1").text("비밀번호는 영문,숫자,특수문자(!,@,#,-,_)포함 4 ~ 12글자로 작성해주세요.").css("color","rgb(146 26 255)");

		checkObj.pwd1 = false;
	}
});


// 이메일 유효성 검사
document.getElementById("email").addEventListener("input", function() {
	const inputEmail = document.getElementById("email").value;
	const regExp = /^[\w]{4,}@[\w]+(\.[\w]+){1,3}$/;
	const checkEmail = document.getElementById("checkEmail");

	if (inputEmail.length == 0) {
		checkEmail.innerText = "";
		checkObj.email = false;

	} else{
			if (regExp.test(inputEmail)) {
				$.ajax({
					url: "emailDupCheck",
					type: "POST",
					data: { "inputEmail": inputEmail },
					success: function(result) {
						
					if (result == 0) {
						checkEmail.innerText = "";
						$("#emailConfirm_btn").css("display", "inline-block");
							

							checkObj.email = true;
						} else {
							$("#checkEmail").text("중복된 이메일입니다.").css("color", "rgb(146 26 255)");
		
							checkObj.email = false;
						}
					},
		
					error: function(e) {
						console.log(e);
					}
		
				});
			} else {
				$("#checkEmail").text("이메일 형식이 올바르지 않습니다.").css("color", "rgb(146 26 255)");
		
				checkObj.email = false;
			}
	}
});

// 이메일 인증 검사
// $("#emailCheck_btn").on("click", function(){
// 	if(checkObj.email == true){
// 		emailConfirm_check();
// 	}

// });

// 이메일 인증 함수
function emailConfirm_check() {
	const inputEmailConfirm = document.getElementById("email").value;
	$("#emailConfirm").css("display", "inline-block");
	if(inputEmailConfirm.length == 0){
		inputEmailConfirm.text("");
		checkObj.emailConfirm = false;
	}else{
		$.ajax({
			url: "emailConfirm",
			type: "GET",
			data: {"inputEmailConfirm": inputEmailConfirm},
			
			success: function(result){
				$("#emailConfirm").on("input",function(){
					if (result == $("#emailConfirm").val()){
					
						$("#emailConfirm_btn").css("display", "none");
						$("#checkEmail2").text("인증이 확인되었습니다.").css("color", "rgb(146 26 255)");
						checkObj.emailConfirm = true;
					}else{
						$("#emailConfirm_btn").css("display", "blocinline-blockk");
						$("#checkEmail2").text("잘못된 인증 번호입니다. 다시 확인해주세요.").css("color", "rgb(146 26 255)");
						checkObj.emailConfirm = false;
					}
				});
			},error:function(e){
				console.log(e);
			}
		})
	}
};





// 이름 유효성 검사
	$("#name").on("input", function(){
	const inputName = $("#name").val();
	const regExp = /^[가-힣]{2,7}$/;

	if (inputName.length == 0) {
		$("#checkName").text("");

		checkObj.name = false;
	} 
	if (regExp.test(inputName)) {
		$("#checkName").text("");

		checkObj.name = true;
	} else {
		$("#checkName").text("정확한 이름을 적어주세요.").css("color", "rgb(146 26 255)");

		checkObj.name = false;
	}
});



// 전화번호 글자수 제한 + 유효성 검사
$(".phone").on("input", function (e) {
  if ($(this).val().length > 4 ) {
    
    const num = $(this).val().slice(0,4);

    $(this).val(num);
  }

	if(e.originalEvent.data == "e"){
        $(this).val();
		return;
    }

  const inputPhone2 = $("#phone2").val();
  const inputPhone3 = $("#phone3").val();

  const regExp2 = /^\d{3,4}$/;
  const regExp3 = /^\d{4}$/;
	
  const checkPhone = document.getElementById("checkPhone");

  if(inputPhone2.length == 0 && inputPhone3.length == 0) {
    checkPhone.innerText = "";

    checkObj.phone3 = false;
  }else if(regExp2.test(inputPhone2) && regExp3.test(inputPhone3)){ 
    checkPhone.innerText = "";

    checkObj.phone3 = true;
  } else {
    checkPhone.innerText = "유효하지 않은 전화번호입니다";
    checkPhone.style.color = "rgb(146 26 255)";

    checkObj.phone3 = false;
  }

});



function validate() {
	for (const key in checkObj) {

		if (!checkObj[key]) { 
			let msg;
			switch (key) {
				case "id":
					msg = "아이디가 유효하지 않습니다.";
					break;
				case "pwd1":
					msg = "비밀번호가 유효하지 않습니다.";
					break;
				case "pwd2":
					msg = "비밀번호가 일치하지 않습니다. ";
					break;
				case "email":
					msg = "이메일이 유효하지 않습니다.";
					break;
				case "emailConfirm":
					msg = "이메일 인증이 유효하지 않습니다.";
					break;
				case "name":
					msg = "이름이 유효하지 않습니다.";
					break;
				case "phone3":
					msg = "전화번호가 유효하지 않습니다. ";
					break;
				case "newPwd1":
					msg = "비밀번호가 유효하지 않습니다. ";
					break;
				case "newPwd2":
					msg = "비밀번호가 일치하지 않습니다. ";
					break;
				
			}

			swal(msg).then(function() {

				const selector = "#" + key;

				$(selector).focus();
			});

			return false; 

		}
	}
};