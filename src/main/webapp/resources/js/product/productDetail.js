/* 제품 상세 페이지 JS

상품번호를 가지고 가서, 일단은 주문번호를 은행마냥 발급받고, 

발급받은걸로 주문테이블에 추가하면서 

가지고올때도 발급받은 번호의 주문 밸류 오브젝트만 가지고온다 
상품번호를 hidden input, 숨겨서 받아올꺼고 get방식을 쓸꺼다

productDetail..jsp 수정 부탁
*/



//가격 변동 에 따라서 계산하는 함수
document.querySelector('#inputAmount').addEventListener('change', showPrice);

function showPrice() {
    //원래 가격, 할인 가격을 JSP로 계산된 부분에서 가져옴
    const originPrice = (Number)(document.querySelector('#originPrice').innerText.replaceAll(/[,원]/g, ''));
    const salePrice = (Number)(document.querySelector('#salePrice').innerText.replaceAll(/[,원]/g, ''));




    //가격 변동에 직접적 영향을 미치는 element
    const amount = document.querySelector('#inputAmount');


    if (amount.value == 0) {
        alert('0 이하로 수량을 내릴 수 없습니다.');
        amount.value = 1;
        return;
    }

    //가격에 따라 가변적인 엘리먼트

    //배송비
    const shipment = document.querySelector('#shipment');

    const calOriginPrice = document.querySelector('#calculatedOriginPrice');
    const calPrice = document.querySelector('#calculatedPrice');
    const calAmount = document.querySelector('#calculatedAmount');

    const calOp = originPrice * (Number)(amount.value);
    const calSp = salePrice * (Number)(amount.value);
    if (calSp >= 50000) {
        shipment.innerText = "무료";
    } else {
        shipment.innerText = "2,500원";
    }

    calOriginPrice.innerText = calOp.toLocaleString('ko-KR') + "원";

    calPrice.innerText = calSp.toLocaleString('ko-KR') + "원";

    calAmount.innerText = amount.value;


}




//원래는 보안을 위해서 post 방식으로 진행하는게 맞지만, 데이터 흐름 보려고, 그리고 시간이 없어서 get방식으로 진행함
//자바스크립트 세션 받아오기 : jsp파일에서 script 태그 안에 js로 변수명 만들어주고 그 이후에 자스 파일을 실행한다.

function addCart() {

    console.log("addCart");

    const productNo = document.getElementById('n-proNumber').value;




}


function buyNow() {

    console.log("buyNow");

    const productNo = document.getElementById('n-proNumber').value;





}