function addrRegister() {

    const chkSub = document.querySelectorAll('.addr-chk-sub'); //그냥 배송지만큼 서브-체크박스 있으니까 씀

    if (chkSub.length == 10) {
        alert('배송지는 최대 10개까지만 등록할 수 있습니다.');
        return;
    } else {
        location.href = contextPath + '/myPage/addr/Register';
    }


}


//배송지의 삭제 : 상황에 따라 화면의 요소(삭제버튼)를 안보여주는 JSP가 엮여 있어서 
//삭제 후 페이지 재로드 - 깜빡 거리기 하는게 제일 편함 
//에이젝스로 깜빡 안거리게 할수 있긴 한데 하단 배송지삭제 버튼 때문에 추가적으로 화면 element개수 측정
//js 함수가 더 필요하다
function checkSub(e) {
    const chkSub = document.querySelectorAll('.addr-chk-sub');

    for (let i = 0; i < chkSub.length; i++) {
        chkSub[i].checked = e.checked;

    }

}

function delChkAddr() {

    const chkAddrNo = new Array();

    const chkSub = document.querySelectorAll('.addr-chk-sub');

    for (let i = 0; i < chkSub.length; i++) {
        if (chkSub[i].checked) {
            chkAddrNo.push(chkSub[i].nextElementSibling.value);
        }

    }

    if (confirm(chkAddrNo.length + '개의 주소를 삭제하시겠습니까?')) {



        $.ajax({

            url: contextPath + "/myPage/addr/delete",
            type: "post",
            data: { addrNoList: chkAddrNo },
            success: function(result) {

                if (result == 1) {
                    alert('성공적으로 삭제되었습니다.');
                    location.reload();
                } else {
                    alert('삭제 과정에서 에러 발생.');
                }

            },
            error: function(jqXHR, textStatus, errorTh) {
                alert('ajax 과정에서 문제 발생');
            }


        });




    }





}