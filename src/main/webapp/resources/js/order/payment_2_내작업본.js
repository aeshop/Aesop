document.addEventListener('DOMContentLoaded', calculatePayment);

// calculatePayment() 전체 가격 계산 함수
function calculatePayment() {
    console.log('calculatePayment');
    //각 열의 가격정보 담은 span 태그
    const rowPriceArr = document.querySelectorAll('.rowPrice');

    //각 열의 수량담은 input 태그

    let sum = 0;


    for (let i = 0; i < rowPriceArr.length; i++) {

        //합계 정보도 계산해서 화면에 뿌려줘야한다

        sum += rowPriceArr[i].value;



    }
    //전체 물품가를 클래스 :  위치에 넣어주어야함
    const allProPriceArr = document.querySelectorAll('.allProPrice');
    for (let i = 0; i < allProPriceArr.length; i++) {
        allProPriceArr[i] = sum.toLocaleString('ko-KR');
    }

    //배송비 계산후 클래스 : 에 넣어주어야 함
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


    // //전체합 구해서 n-sumCal에 넣어줌

    // for (let i = 0; i < sumCalArr.length; i++) {
    //     sumCalArr[i].innerText = ((Number)(sum + ship)).toLocaleString('ko-KR');

    // }


    //할인된 가격과 최종가격을 마무리짓기
    //할인율 : 1% 등으로 나와 있음
    const discount = (Number)(document.querySelector('#n-membership-rate').innerText);

    const discountPrice = calPrice * (discount / 100);

    const discountPriceArr = document.querySelectorAll('.discountPrice');
    for (let i = 0; i < discountPriceArr.length; i++) {
        discountPriceArr[i].innerText = discountPrice.toLocaleString('ko-KR');
    }


    const totalPrice = calPrice - discountPrice;
    const totalPriceArr = document.querySelectorAll('.totalPrice');

    console.log('totalPrice');

    for (let i = 0; i < totalPriceArr.length; i++) {
        totalPriceArr[i].innerText = totalPrice.toLocaleString('ko-KR');
    }

}














/*https://docs.iamport.kr/implementation/payment에 클라단에서 서버단까지의 상세정보가 나와있음

블로그 검색으로 보면 자기 서버단에 어떻게 저장하는지에 대한 정보는 나와있지 않은 경우도 존재한다.

검증이 성공해야 결제 정보를 데이터베이스에 저장하는 방식으로 진행하는데, 코드가 길고 흐름읽기가 쉽지 않았음


1. 결제 정보를 클라이언트 단에서 카드사가 제공하는 기능 이용해서 결제
2. 결제 성공시 결제에 대한 정보는 2개의 위치로 보내져야함 : 내 서버와 i'mport 서버

3. 서로 다른 uid 에 들어있는 값을 이용해 찾은 두 서버의 결제정보을 비교해서 같음을 검증하고: 

같으면 결제를 이때 데이터베이스에 저장한다
->같음을 검증하는 방법? 을 알아내는 것이 중요 : 두 개의 키를 사용하고 어쩌고 했었음
예시는 node.js를 사용했고 나는 이걸 servlet controller에서 해야되던지 해야 할 것 같다.

저장 후에 가상계좌 발급: 무통장입금, 결제성공, 실패 이렇게 세개의 분류가 있는데
그에 맞게 대응하는 코드를 써주면 됨 : 여기까지가 기본이고


결제 정보를 
data: {
                imp_uid: rsp.imp_uid,
                merchant_uid: rsp.merchant_uid
            }
            의 방식으로 제이쿼리를 이용해서 내 서버로 던짐

            아임포트 서버로도 결과값이 가는데

            두  서버의 값을 비교해서 검증이 성공하면 결제 정보를 데이터베이스에 저장

*/






//이 코드는 프론트단, 버튼을 클릭했을때 화면에 결제창이 뜨고 amount만큼의 돈이 표시가 된다.
$("#check_module").click(function() {

    /* 화면 안에 있는 것들 끌어모아서 에이잭스 객체 안에 집어넣기

    주문번호( merchant_uid), 가격 (amount), 구매자이메일(buyer_email), 구매자 이름(buyer_name)
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
    const totalPrice = document.querySelector('.totalPrice').innerText.replaceAll(/[,]/g, '');

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

        merchant_uid: pay_deliveryNo,

        name: pay_deliveryName,
        //결제창에서 보여질 이름
        // amount: totalPrice,
        amount: 100,
        //가격 
        buyer_email: pay_buyerEmail,
        buyer_name: pay_buyerName,
        buyer_tel: pay_buyerTel,
        buyer_addr: pay_buyerZipCode,
        buyer_postcode: pay_buyerAddress,
        m_redirect_url: 'https://www.yourdomain.com/payments/complete'
            /*
            모바일 결제시, 결제가 끝나고 랜딩되는 URL을 지정         
            */
    }, function(rsp) {
        console.log(rsp);
        if (rsp.success) {
            var msg = '결제가 완료되었습니다.';
            msg += '고유ID : ' + rsp.imp_uid;
            msg += '상점 거래ID : ' + rsp.merchant_uid;
            msg += '결제 금액 : ' + rsp.paid_amount;
            msg += '카드 승인번호 : ' + rsp.apply_num;
            //여기에  결제 성공 여부에 따른 처리 로직이 작성되야 한다. ajax로 작성되야 한다. 
            $.ajax({
                url: "/teamSemiProject2/order/validation", // 예: https://www.myservice.com/payments/complete
                //url은 controller일 것이고, 
                method: "POST",
                data: {
                    imp_uid: rsp.imp_uid,
                    merchant_uid: rsp.merchant_uid
                }
                /*이걸 보내고 servlet에서는 이것을 받을 것이다

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



            })



        } else {
            var msg = '결제에 실패하였습니다.';
            msg += '에러내용 : ' + rsp.error_msg;
        }
        alert(msg);
    });
});

/* 
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
(프로세스 창이 결제가 되고 꺼지던 도중에 취소해서 안되고 꺼지던 success로 취급된다)

 블로그 예제는 클라이언트 단과 i'mport 서버에 기록이 남는 방식을 사용했고
 
 5. 나는 여기에 더해서 내 서버 즉 DB에 기록을 남기고, 그 기록을 i'mport 서버와 검증해서 맞으면 결제완료로
 아니면 추가적인 조치를 취하는 로직을 만들어야 된다.
 



*/