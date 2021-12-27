document.querySelector('#emailSelect').addEventListener('change', function() {


    if (this.value != 'etc') {
        this.previousElementSibling.value = this.value;

    } else {
        this.previousElementSibling.value = '';
        this.previousElementSibling.focus();
    }

});





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












/* 
화면에서 가격 계산 함수
 
*/
document.addEventListener('DOMContentLoaded', calculatePayment);

// calculatePayment() 전체 가격 계산 함수
function calculatePayment() {
    // console.log('calculatePayment');
    //각 열의 가격정보 담은 span 태그
    const rowPriceArr = document.querySelectorAll('.rowPrice');

    //각 열의 수량담은 input 태그

    let sum = 0;


    for (let i = 0; i < rowPriceArr.length; i++) {

        //합계 정보도 계산해서 화면에 뿌려줘야한다

        sum += (Number)(rowPriceArr[i].value);



    }
    //전체 물품가를 class : allProPrice 위치에 넣어주어야함
    const allProPriceArr = document.querySelectorAll('.allProPrice');
    for (let i = 0; i < allProPriceArr.length; i++) {
        allProPriceArr[i].innerText = sum.toLocaleString('ko-KR');
    }

    //배송비 계산후 class shipPrice에 넣어주어야 함
    let ship = 2500;
    if (sum >= 50000 || sum == 0) {
        ship = 0;
    }
    const shipPriceArr = document.querySelectorAll('.shipPrice');

    for (let i = 0; i < shipPriceArr.length; i++) {
        shipPriceArr[i].innerText = ship.toLocaleString('ko-KR');
    }

    const calPrice = (Number)(sum) + ship;

    const calPriceArr = document.querySelectorAll('.calPrice');

    for (let i = 0; i < calPriceArr.length; i++) {
        calPriceArr[i].innerText = calPrice.toLocaleString('ko-KR');
    }


    //할인된 가격과 최종가격:totalPrice을 마무리짓기
    //할인율 : 1% 등으로 표시
    const discount = (Number)(document.querySelector('#n-membership-rate').innerText);

    const discountPrice = calPrice * (discount / 100);

    const discountPriceArr = document.querySelectorAll('.discountPrice');
    for (let i = 0; i < discountPriceArr.length; i++) {
        discountPriceArr[i].innerText = discountPrice.toLocaleString('ko-KR');
    }


    const totalPrice = calPrice - discountPrice;
    const totalPriceArr = document.querySelectorAll('.totalPrice');

    for (let i = 0; i < totalPriceArr.length; i++) {
        totalPriceArr[i].innerText = totalPrice.toLocaleString('ko-KR');
    }

}




function getAddrInfo() {


    window.open(contextPath + "/order/addrPop", "", "width=800,height=600,left=200,top=200");


}



















//이 코드는 프론트단, 버튼을 클릭했을때 화면에 결제창이 뜨고 amount만큼의 돈이 표시가 된다.
$("#check_module").click(function() {
    console.log("결제버튼 누름");
    // 2-0) 사용자가 결제 버튼을 누름 : import가 켜지기 전에 
    // 2-1) ajax로 주문번호들을 전달
    // service에서 계산 진행할 것이기 때문에, 주문수량, 전체 가격(totalPrice)은 전달하지 않는다.

    const orderNoTags = document.querySelectorAll('.orderNumber');

    const orderNoArr = new Array();
    for (let i = 0; i < orderNoTags.length; i++) {
        orderNoArr.push(orderNoTags[i].value);

    }

    //결제 위해 화면 정보 긁어옴
    const deliveryInfo = {


        // dZipCode: document.querySelector('#rZipcode').value,
        dZipCode: document.querySelector('#sample6_postcode').value,
        // dAddress1: document.querySelector('#rAddr1').value,
        dAddress1: document.querySelector('#sample6_address').value,
        // dAddress2: document.querySelector('#rAddr2').value,
        dAddress2: document.querySelector('#sample6_detailAddress').value,
        dReceiverName: document.querySelector('#rName').value,
        dReceiverPhone1: document.querySelector('#rPhone1').value,
        dReceiverPhone2: document.querySelector('#rPhone2').value,
        dReceiverPhone3: document.querySelector('#rPhone3').value,
        dMessage: document.querySelector('#rMessage').value

    }



    //빈거 어캄? 
    const orderInputs = document.querySelector('#n-order-info').querySelectorAll('input');
    console.log(orderInputs);

    for (const iterator of orderInputs) {
        if (iterator.value === "") {
            alert('요구 항목을 모두 입력해 주세요.');
            return;
        }
    }

    for (const key in deliveryInfo) {
        if (key != 'dMessage' && (deliveryInfo[key] == "")) {

            alert('요구 항목을 모두 입력해 주세요.');
            return;
        }
    }





    let deliveryNo;
    let totalPrice;
    $.ajax({

        url: contextPath + "/order/beforeImport",
        type: "post",
        data: { orderNoList: orderNoArr },
        dataType: "json",
        async: false, //에이젝스 데이터 반환은 자바스크립트 코드의 실행보다 느리기 때문에 동기로 해두어야함 

        success: function(result) { //json으로 넘어옴 : 에이젝스가 자바스크립트 코드의 실행보다 느리기 때문에 발생하는 일이다 
            if (result.stCode == 200) {
                deliveryNo = result.deliveryNo;
                totalPrice = result.totalPrice;
            } else if (result.stCode == 199) {
                alert("제품 재고 부족으로 결제를 취소합니다.");
                location.href = contextPath + '/order/view';
            }


        },
        error: function(jqXHR, textStatus, errorThrown) {
            console.log("ajax 통신 중 오류 발생");
            console.log(jqXHR.responseText);
        }

    });
    console.log("새 배송 레코드 생성 완료");









    var IMP = window.IMP; // 생략가능
    IMP.init('imp63937946');
    // 'iamport' 대신 부여받은 "가맹점 식별코드"를 사용
    // i'mport 관리자 페이지 -> 내정보 -> 가맹점식별코드

    IMP.request_pay({
        pg: 'inicis', // version 1.1.0부터 지원.

        pay_method: 'card',

        merchant_uid: deliveryNo,

        name: pay_info.deliveryName,
        //결제창에서 보여질 이름
        amount: totalPrice,
        // amount: 100,
        //가격 
        buyer_email: pay_info.buyerEmail,
        buyer_name: pay_info.buyerName,
        buyer_tel: pay_info.buyerTel,
        buyer_addr: pay_info.buyerAddress,
        buyer_postcode: pay_info.buyerZipCodes,
        m_redirect_url: 'https://www.yourdomain.com/payments/complete'
            /*
            모바일 결제시, 결제가 끝나고 랜딩되는 URL을 지정         
            */
    }, function(rsp) {
        console.log(rsp);

        //rsp 안에 success 프로퍼티가 true, false로 결제 성공, 결제 실패&취소 여부를 알려줌

        if (rsp.success) {
            var msg = '결제가 완료되었습니다.';
            msg += '고유ID : ' + rsp.imp_uid;
            msg += '상점 거래ID : ' + rsp.merchant_uid;
            msg += '결제 금액 : ' + rsp.paid_amount;
            msg += '카드 승인번호 : ' + rsp.apply_num;
            //여기에  결제 성공 여부에 따른 처리 로직이 ajax로 작성되야 한다. 
            $.ajax({
                url: contextPath + "/order/validation",
                //url은 controller로 보낼 주소
                method: "POST",
                data: {
                    imp_uid: rsp.imp_uid,
                    merchant_uid: rsp.merchant_uid,

                    /* const deliveryInfo 에서 정보 빼와 담기 */
                    //추가적으로 배송관련 정보들을 보내야 한다.
                    dZipCode: deliveryInfo.dZipCode,
                    dAddress1: deliveryInfo.dAddress1,
                    dAddress2: deliveryInfo.dAddress2,
                    dReceiverName: deliveryInfo.dReceiverName,
                    dReceiverPhone: deliveryInfo.dReceiverPhone1 + "-" + deliveryInfo.dReceiverPhone2 + "-" + deliveryInfo.dReceiverPhone3,
                    dMessage: deliveryInfo.dMessage,
                    orderNoList: orderNoArr


                }




            }).done(function(data) { //AJAX done 함수는 요청이 성공하면 요청한 데이터가 done()메소드로 전달된다는 의미 data는 요청 데이터이다
                // 가맹점 서버 결제 API 완료시 로직 : 여기가 뭔지 모르겠네 : - 아직은 후순위, 가상ㄱ좌 발급시 로직, 결제 성공시 로직 이런식으로 나뉨
                //지금 중요한 것은 server단에서의 imp_uid,merchant_uid처리이다

                console.log(data);

                console.log(deliveryInfo);




                location.href = contextPath + '/myPage';

            })



        } else {



            $.ajax({
                url: contextPath + "/order/payCancel",


                method: "POST",
                data: {
                    //배송코드
                    merchant_uid: rsp.merchant_uid,
                    //주문코드 배열
                    orderNoList: orderNoArr

                },
                success: function(result) {
                    var msg = '결제에 실패하였습니다.   ' + result;
                    // msg += '에러내용 : ' + rsp.error_msg;
                    alert(msg);

                },
                error: function(jqXHR, textStatus, errorThrown) {
                    console.log("ajax 통신 중 오류 발생");
                    console.log(jqXHR.responseText);
                }


            });






        }
    });
});



function addrClear() {
    const addrInputs = document.querySelectorAll('#receiverInfo input');


    for (let i = 0; i < addrInputs.length; i++) {
        if (addrInputs[i].type != 'button') {
            addrInputs[i].value = '';
        }

    }
}