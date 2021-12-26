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
    var IMP = window.IMP; // 생략가능
    IMP.init('imp63937946');
    // 'iamport' 대신 부여받은 "가맹점 식별코드"를 사용
    // i'mport 관리자 페이지 -> 내정보 -> 가맹점식별코드
    IMP.request_pay({
        pg: 'inicis', // version 1.1.0부터 지원.

        pay_method: 'card',
        // pay_method: 'trans',
        /*
        'samsung':삼성페이,
        'card':신용카드,
        'trans':실시간계좌이체,
        'vbank':가상계좌,
        'phone':휴대폰소액결제
        */
        merchant_uid: 'merchant_' + new Date().getTime(),
        /*
        merchant_uid에 경우
        https://docs.iamport.kr/implementation/payment
        위에 url에 따라가시면 넣을 수 있는 방법이 있습니다.
        참고하세요.
        나중에 포스팅 해볼게요.
        3-1에 써놓음
        //

        */
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
            /*
            모바일 결제시,
            결제가 끝나고 랜딩되는 URL을 지정
            (카카오페이, 페이코, 다날의 경우는 필요없음. PC와 마찬가지로 callback함수로 결과가 떨어짐)
            */
    }, function(rsp) {
        console.log(rsp);
        if (rsp.success) {
            var msg = '결제가 완료되었습니다.';
            msg += '고유ID : ' + rsp.imp_uid;
            msg += '상점 거래ID : ' + rsp.merchant_uid;
            msg += '결제 금액 : ' + rsp.paid_amount;
            msg += '카드 승인번호 : ' + rsp.apply_num;


            //여기에  결제 성공 여부에 따른 처리 로직이 작성되야 한다





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

 4. 결제 성공시, 결제 실패시에 대응하는 함수가 실행된다 
 블로그 예제는 클라이언트 단과 i'mport 서버에 기록이 남는 방식을 사용했고
 
 5. 나는 여기에 더해서 내 서버 즉 DB에 기록을 남기고, 그 기록을 i'mport 서버와 검증해서 맞으면 결제완료로
 아니면 추가적인 조치를 취하는 로직을 만들어야 된다.
 



*/