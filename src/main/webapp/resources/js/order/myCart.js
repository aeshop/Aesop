console.log("제대로 읽힙니다.");

/* 
JS는 화면에 뿌려진 것으로 계산하기 때문에 태그 안의 내용의 쉼표나 원을 빼고,
숫자로 바꿔주고 계산을진행해야한다

= replaceAll(/[,원]/g, ''), Number의 활용이 필요하다

*/

/*calculatePrice함수 : 화면에 놓인 상품 전체의 가격과 배송비의 유무를 구해서 합을 화면에 뿌려주는 함수
상품 전체의 가격: 상품 한 열의 가격의 총합, 상품 한 열의 가격: 상품가, 수량

배송비: 상품 전체의 가격이 5만원이 넘으면 무료, 아니면 2500원

합계가격: 상품 전체가 + 배송비

calculateRow 함수 : 한 열의 상품가와 수량을 계산하는 함수

*/

document.addEventListener('DOMContentLoaded', calculatePrice);


function calculatePrice() {
    //각 열의 가격정보 담은 span 태그
    const proPriceArr = document.querySelectorAll('.proPrice');

    //각 열의 수량담은 input 태그
    const proAmountArr = document.querySelectorAll('.proAmount');

    const orderPriceArr = document.querySelectorAll('.orderPrice');

    const proCalArr = document.querySelectorAll('.n-proCal');
    const shipCalArr = document.querySelectorAll('.n-shipCal');
    const sumCalArr = document.querySelectorAll('.n-sumCal');
    let sum = 0;


    for (let i = 0; i < proPriceArr.length; i++) {

        //합계 정보도 계산해서 화면에 뿌려줘야한다

        const orderPrice = calculateRow(proPriceArr[i], proAmountArr[i]);

        orderPriceArr[i].innerText = orderPrice.toLocaleString('ko-KR');
        sum += orderPrice;

    }
    //전체 물품가 계산후 n-proCal에 넣어줌
    for (let i = 0; i < proCalArr.length; i++) {
        proCalArr[i].innerText = sum.toLocaleString('ko-KR');
    }

    //배송비 계산후 n-shipCal에 넣어줌
    let ship = 2500;
    if (sum >= 50000) {
        ship = 0;
    }

    for (let i = 0; i < shipCalArr.length; i++) {
        shipCalArr[i].innerText = ship.toLocaleString('ko-KR');
    }
    //전체합 구해서 n-sumCal에 넣어줌

    for (let i = 0; i < sumCalArr.length; i++) {
        sumCalArr[i].innerText = (sum + ship).toLocaleString('ko-KR');

    }

}

function calculateRow(proPrice, amount) {

    const p = (Number)((proPrice.innerText).replaceAll(/[,원]/g, ''));
    const a = (Number)(amount.value);


    return p * a;
}