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


/*

주소록 팝업창 띄우는 메소드

요즘 팝업 창은 레이어로 만들어서, 화면에 display:none으로 배치시키고

클릭하면 드러나는 식으로 만든다. 그러나 널디는 그냥 팝업창을 만들었다

window.open(주소경로, 창 이름(딱히 안지을꺼면 ""), 창 옵션(넓이, 높이, 좌, 위, ):"width=1200,height=800,left=200,top=200");

*/

function getAddrInfo() {


    window.open("/teamSemiProject2/order/addrPop", "", "width=800,height=600,left=200,top=200");


}





/*
결제관련 비즈니스 로직


1)payment 페이지가 로드가 되면 계산 함수(calculatePayment())를 호출
1-1) 각각의 가격, 전체 가격 계산후 화면에 표시 - 완료

2-0) 사용자가 결제 버튼을 누름 : <<import가 켜지기 전에>> 
2-1) ajax로 주문번호만을 (주문수량, 전체 가격(totalPrice) X) 를 전달
2-2) 주문번호만 전달 : 주문수량 ,전체 가격을 화면에서 긁어와서 결제를 진행 시도하면 헛점 발생 : 개발자 도구로 주문금액을 내맘대로 바꾸기 가능 
2-3) 주문번호만 전달하게되면 결제페이지에서도 삭제, 수량조절이 가능한데: (서버값을 바꾸는거라), 번거로워서 안함

2-2) product table 수량변경 시도, 
2-2-1) 재고부족 시 체크 제약조건 위배로 발생하는 SQLException 에러코드 받고 rollback, 재고부족 메세지로 응답

2-3)배송번호를 만들고 - 만들어진 배송번호와 총가격정보가 담긴 배송레코드를 만들고, 
2-3-1)만들어지고 나면 주문 테이블에 배송번호를 업데이트하고, 배송번호를 가지고 온다
2-4)배송번호 가지고 오면 아임포트 실행 후 결제, 결제 정보(배송레코드의 기본키, 아임포트 결제기록 찾는 키)를 서버로 보낸다
2-4-1)아임포트에서 결제취소시의 처리도 해줘야됨: 줄어든 수량을 다시 살린다. - 어떻게? 해당 배송번호로 주문 테이블 조회 후 주문들의 수량만큼 다시 제품들을 + 해줌

2-5) 아임포트 결제 완료 : 받아온 키, 엑세스 토큰   내 배송테이블 레코드의 총액으로 비교검증해서 맞으면 진행 아니면, 예외라도 던져야되나? 

2-6) 맞으면 배송 테이블 업데이트, 주문 테이블 결제완료+ 배송번호 로 업데이트 진행하면 결제 끝



*/








/*https://docs.iamport.kr/implementation/payment에 클라단에서 서버단까지의 상세정보가 나와있음

1. 결제 정보를 클라이언트 단에서 카드사가 제공하는 기능 이용해서 결제
2. 결제 성공시 결제에 대한 정보는 2개의 위치로 보내져야함 : 내 서버와 i'mport 서버

3. 서로 다른 uid 에 들어있는 값을 이용해 찾은 두 서버의 결제정보을 비교해서 같음을 검증하고: 

같으면 결제를 이때 데이터베이스에 저장한다
->같음을 검증하는 방법? 을 알아내는 것이 중요 : 두 개의 키를 사용하고 어쩌고 했었음
예시는 node.js를 사용했고 나는 이걸 servlet controller에서 진행

저장 후에 가상계좌 발급: 무통장입금, 결제성공, 실패 이렇게 세개의 분류가 있는데
그에 맞게 대응하는 코드를 써주면 됨 : 여기까지가 기본이고


결제 정보를 
data: {
                imp_uid: rsp.imp_uid,
                merchant_uid: rsp.merchant_uid
            }
            의 방식으로 제이쿼리를 이용해서 내 서버로 요청

            아임포트 서버로도 결과값이 가는데

            두  서버의 값을 비교해서 검증이 성공하면 결제 정보를 데이터베이스에 저장

*/





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
        dReceiverPhone: document.querySelector('#rPhone1').value + "-" + document.querySelector('#rPhone2').value + "-" + document.querySelector('#rPhone3').value,
        dMessage: document.querySelector('#rMessage').value

    }












    //주문번호를 전달하고, 비즈니스 로직 이후에 배송번호(20121123-000000)을 받아오는 AJAX
    /*2-3) 로직 : 1. orderNo 주문테이블의 수량만큼 제품 테이블에서 감소, 실패시 재고부족 메세지 
    2. 주문 테이블의 제품번호로 제품정보 얻어와서 가격 * 할인율 * 수량 계산 = 총가격 : 5만원 이상이면 +0, 미만이면 +2500
    3. 배송 번호 만들고, 배송 레코드 삽입, 총가격 삽입, 주문 테이블의 배송번호 컬럼update
    4. 배송번호 반환
    


    */
    // const orderNoArr2 = [1, 2, 3];
    let deliveryNo;
    let totalPrice;
    $.ajax({

        url: "/teamSemiProject2/order/beforeImport",
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
                location.href = '/teamSemiProject2/order/view';
            }


        },
        error: function(jqXHR, textStatus, errorThrown) {
            console.log("ajax 통신 중 오류 발생");
            console.log(jqXHR.responseText);
        }

    });
    console.log("새 배송 레코드 생성 완료");

    // console.log(deliveryNo, totalPrice);
    /* 화면 안에 있는 것들 끌어모아서 에이잭스 객체 안에 집어넣기

     구매자이메일(buyer_email), 구매자 이름(buyer_name)
    구매자 전화번호(buyer_tel) 구매자 주소(buyer_addr) 구매자 우편번호(buyer_postcode)

    name: "노르웨이 회전 의자", : 이런으로 상품명을 쓰긴 좀.... 쓸까 : 뭐뭐뭐뭐 외 (카운트 -1 )건 등으로

      name: '주문명:결제테스트',
            //결제창에서 보여질 이름
            amount: 100,
            //가격 
            buyer_email: 'iamport@siot.do',
            buyer_name: '구매자이름',
            buyer_tel: '010-1234-5678',
            buyer_addr: '서울특별시 강남구 삼성동',
            buyer_postcode: '123-456',
            m_redirect_url: 'https://www.yourdomain.com/payments/complete'




    */


    /* 
    전역에서 선언한 변수+ 값으로 만든 변수를 사용함
    */







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
        // amount: totalPrice,
        amount: totalPrice,
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
                url: "/teamSemiProject2/order/validation",
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
                    dReceiverPhone: deliveryInfo.dReceiverPhone,
                    dMessage: deliveryInfo.dMessage,
                    orderNoList: orderNoArr


                }
                /*이걸 보내고 servlet에서는 하단의 것을 수행

                1. 결제번호, 주문번호를 객체에서 추출하기 - 지금 파라미터 안얻어지는데 제이슨 파싱으로 접근해야하나?
                2. 결제 정보 조회하기 - REST API access token을 발급받습니다. 라고 적힘
                3. 결제 정보 검증 후 저장하기

2. access 토큰 발급받기를 nodeJS 쪽은 나와있는데 servet쪽은 없음
자바에서 요청시 access 토큰 발급 받는 과정

 curl  : client url을 의미한다 : api 정보를 기술한 것이라고 생각하면 될 것 같다. 이 양식에 맞춰서 작성해 주면 된다는 의미
  curl  
  -H "Content-Type: application/json" POST 
  -d '{"imp_key": "REST API키", "imp_secret":"REST API Secret"}' https://api.iamport.kr/users/getToken


2.1 액세스토큰 받을 변수, 액세스 토큰 받아올 요청 주소 입력

결과값으로 오는 getPaymentData에서 정보를 빼내야 한다
- 지금 access토큰 발급받기를 시도하지 않은 상태라, 어떤 것이 반환될지가 감이 안잡힌다.



*/



            }).done(function(data) { //AJAX done 함수는 요청이 성공하면 요청한 데이터가 done()메소드로 전달된다는 의미 data는 요청 데이터이다
                // 가맹점 서버 결제 API 완료시 로직 : 여기가 뭔지 모르겠네 : - 아직은 후순위, 가상ㄱ좌 발급시 로직, 결제 성공시 로직 이런식으로 나뉨
                //지금 중요한 것은 server단에서의 imp_uid,merchant_uid처리이다

                console.log(data);

                console.log(deliveryInfo);


                //결제가 성공하였습니다. 페이지로 이동
                //사진 하나랑 결제내역 조회 페이지로,홈으로 두 개 버튼 만들고 싶음
                //: 근데 만들 시간이 없어서 그냥 마이페이지로 보내버림


                location.href = '/teamSemiProject2/myPage';

            })



        } else {


            /* 결제취소시의 내 서버 DB를 조작할 내용
            1. 선택한 order들에 의한 재고 삭감 복구
            2. 선택한 order들의 상태코드 변경
            3. 해당 배송의 상태코드 변경

            order배열, 배송 코드를 서버로 보내주어야 한다.

             같은 페이지 내에서 작업후 메세지를 보여주어야하니까 ajax, 끝나고 메세지를 보여주고 싶으므로 동기 방식

            */

            $.ajax({
                url: "/teamSemiProject2/order/payCancel",


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

/* 
다시한번 설명

1. 버튼에 click 이벤트가 발생했을때, 익명 함수를 실행한다, 
2. 부여받은 가맹점식별코드 적고, 결제정보 객체를 인수로 하는 request_pay함수를 실행한다
3. 결제정보 객체에는 요금, 등이 담겨있고, 주문번호는 내가 생성해야 된다.
3-1. IMP.request_pay({}) 함수를 실행하기 전에, 서버에서 데이터베이스에 주문 레코드를 생성해서 주문번호를
param.merchant_uid에 지정하기를 권장한다 라고 한다

즉 먼저 주문번호를 생성하고, 그 주문번호를 은행 번호표처럼 사용하라는 뜻이다. 
널디처럼 보여주고 싶으면 이렇게 할뿐만 아니라 아예 주문번호 레코드부터 생성하고
 이걸 추가한다음에 update로 레코드를 계속 수정해 나가야 할듯?
 그러니까 구매 버튼 누르자 마자 가장 먼저 해야 할 일이 3-1인 것

 4. 결제 성공시, 결제 실패시에 대응하는 함수가 실행된다 : 정확히는 결제 성공시 실패시가 아니라 imp_success 파라미터는 결제 프로세스 정상 종료 여부
 이고, 클라이언트 상에서 하는 거기 때문에 위변조의 가능성이 있으므로 이 값으로 결제의 성공 여부를 판단해서는 안된다

 */

function addrClear() {
    const addrInputs = document.querySelectorAll('#receiverInfo input');


    for (let i = 0; i < addrInputs.length; i++) {
        if (addrInputs[i].type != 'button') {
            addrInputs[i].value = '';
        }

    }
}