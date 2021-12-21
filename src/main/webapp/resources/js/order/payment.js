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
$("#check_module").click(function() {
    var IMP = window.IMP; // 생략가능
    IMP.init('imp63937946');
    // 'iamport' 대신 부여받은 "가맹점 식별코드"를 사용
    // i'mport 관리자 페이지 -> 내정보 -> 가맹점식별코드
    IMP.request_pay({
        pg: 'inicis', // version 1.1.0부터 지원.
        /*
        'kakao':카카오페이,
        html5_inicis':이니시스(웹표준결제)
        'nice':나이스페이
        'jtnet':제이티넷
        'uplus':LG유플러스
        'danal':다날
        'payco':페이코
        'syrup':시럽페이
        'paypal':페이팔
        */
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
        
        //

        */
        name: '주문명:결제테스트',
        //결제창에서 보여질 이름
        amount: 25000,
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
        } else {
            var msg = '결제에 실패하였습니다.';
            msg += '에러내용 : ' + rsp.error_msg;
        }
        alert(msg);
    });
});