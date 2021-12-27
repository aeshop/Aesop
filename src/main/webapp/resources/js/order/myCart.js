/* 
Cart 장바구니 관련 JS

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

document.addEventListener('DOMContentLoaded', function() {
    const chkBoxs = document.querySelectorAll('input[type="checkbox"]');
    for (let i = 0; i < chkBoxs.length; i++) {
        chkBoxs[i].checked = true;
    }

    calculatePrice();
});

const proChk = document.querySelectorAll('input[type="checkbox"]');

for (let i = 0; i < proChk.length; i++) {
    proChk[i].addEventListener('change', calculatePrice);

}

const proAmountArr = document.querySelectorAll('.proAmount');

// calculateRow() 행 가격 계산 함수
function calculateRow(checkBox, proPrice, amount) {

    const p = (Number)((proPrice.innerText).replaceAll(/[,원]/g, ''));
    const a = (Number)(amount.value);

    if (checkBox.checked) {
        return p * a;

    } else {
        return 0;
    }
}

// calculatePrice() 전체 가격 계산 함수
function calculatePrice() {

    //각 열의 체크박스 정보 담은 태그
    const checkSubArr = document.querySelectorAll('.n-order-chk');
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

        const orderPrice = calculateRow(checkSubArr[i], proPriceArr[i], proAmountArr[i]);

        orderPriceArr[i].innerText = orderPrice.toLocaleString('ko-KR');
        sum += orderPrice;

    }
    //전체 물품가 계산후 n-proCal에 넣어줌
    for (let i = 0; i < proCalArr.length; i++) {
        proCalArr[i].innerText = sum.toLocaleString('ko-KR');
    }

    //배송비 계산후 n-shipCal에 넣어줌
    let ship = 2500;
    if (sum >= 50000 || sum == 0) {
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




//수량 변환 함수
function amountUp(et) {
    const orderAmount = et.parentNode.parentNode.firstElementChild.firstElementChild;
    const productStock = et.parentNode.parentNode.firstElementChild.firstElementChild.nextElementSibling;

    if (orderAmount.value == productStock.value) {
        alert('재고 이상의 상품을 구매할 수 없습니다.');
        return;
    }

    orderAmount.value = (Number)(orderAmount.value) + 1;

    const nF = amountChanged.bind(et);
    nF();
}

function amountDown(et) {
    //버튼 클릭한 위치의 DOM을 활용하는 수밖에 없는듯
    const orderAmount = et.parentNode.parentNode.firstElementChild.firstElementChild;


    if (orderAmount.value == 1) {
        alert('1미만으로는 내릴 수 없습니다.');
        return;
    }

    orderAmount.value = (Number)(orderAmount.value) - 1;

    const nF = amountChanged.bind(et);
    nF();


}


//수량 변환 함수 및 input태그에 bind
function amountChanged() {
    //버튼 클릭한 위치의 DOM을 활용
    // 수량 input 태그 얻어오기
    console.log('변경함수실행');
    const orderAmount = this.parentNode.parentNode.firstElementChild.firstElementChild;
    const productStock = this.parentNode.parentNode.firstElementChild.firstElementChild.nextElementSibling;
    if (isNaN(orderAmount.value)) {
        alert('숫자를 입력해 주세요');
        orderAmount.value = "";
        return;
    } else if (orderAmount.value == 0) {
        alert('1미만으로 내릴 수 없습니다.');
        orderAmount.value = 1;
        return;
    } else if (orderAmount.value > (Number)(productStock.value)) {
        alert('수량을 재고 이상으로 올릴 수 없습니다.');
        orderAmount.value = productStock.value;
        return;
    }
    //클릭한 주문번호 얻어오기
    const orderNo = this.parentNode.parentNode.parentNode.parentNode.firstElementChild.firstElementChild.value;

    //ajax 실행
    $.ajax({
        url: contextPath + "/order/amountChange",
        type: "POST",
        data: {
            "orderNo": orderNo,
            "orderAmount": orderAmount.value
        },
        success: function(result) {
            if (result == 1) {
                // alert("수량변경완료");
                calculatePrice();
            }
        },
        error: function() {
            alert("수량변경과정에서 오류 발생");
        }

    });

}
//화면 load시  수량 input에 변경이벤트시 함수 바인딩
for (let i = 0; i < proAmountArr.length; i++) {
    proAmountArr[i].addEventListener('change', amountChanged);

}

/*행의 상품 삭제 : deleteOrder()

1.선택한 상품을 삭제할 것인지 묻는다 (confirm)
2.삭제한 다음에(DB에서 update로 order 상태 변경)
3. 화면을 reload함 : 다시 새로고침하는 효과를 요청주소를 보내고 거기서 redirect하는 방식으로 했음
*/

function deleteOrder() {
    const orderNo = event.target.parentNode.parentNode.parentNode.firstElementChild.firstElementChild.value;

    const deleteConfirm = confirm('선택하신 상품을 삭제하시겠습니까?');

    if (deleteConfirm) {
        location.href = contextPath + '/order/delete?' + "orderNo=" + orderNo;
    }

}

/*
선택 상품 주문 doOrder()
*/

function doOrder() {
    const orderNo = event.target.parentNode.parentNode.parentNode.firstElementChild.firstElementChild.value;
    location.href = contextPath + '/order/orderAll?' + "orderNo=" + orderNo;

}



/*선택상품 여러개의 삭제 : 

또는 getParameterValue로 받을 수 있도록 queryString을 조작, 또는 post방식으로 보내도록 조작
-->자바스크립트는 본문에 form이 없는 경우 post방식으로 데이터를 넘기기 힘들다 : JS로 form을 만들고 진행한다.

1.선택한 상품을 삭제할 것인지 묻는다 (confirm)
2.삭제한 다음에(DB에서 update로 order 상태 변경)
3. 화면을 reload함 : 다시 새로고침하는 효과를 요청주소를 보내고 거기서 redirect하는 방식으로 했음
*/

function deleteSelectedOrder(message) {
    // console.log('deleteSelectedOrder');
    const orderCheckBox = document.querySelectorAll('.n-order-chk');

    const orderNoArr = new Array();
    let deleteQueryString = "";
    for (let i = 0; i < orderCheckBox.length; i++) {
        if (orderCheckBox[i].checked) {
            deleteQueryString += ("&orderNo=" + orderCheckBox[i].value);
            orderNoArr.push(orderCheckBox[i].value);
        }
    }
    if (orderNoArr.length == 0) {
        alert('선택된 상품이 없습니다.');
        return;
    }

    //post로 보낼것인지,아니면 parameter로 보낼수도 있나? 그냥 get방식으로 보내기로
    if (message == null) {
        message = '선택하신 상품들을 삭제하시겠습니까?';
    }
    const deleteConfirm = confirm(message);

    if (deleteConfirm) {
        location.href = contextPath + '/order/deleteAll?' + deleteQueryString;
    }

}
//전체 물품 체크,체크해제 하는 함수
function checkAll(e) {


    const chkStatus = e.checked;
    const chkBoxArr = document.querySelectorAll('.n-order-chk');
    for (const iterator of chkBoxArr) {
        iterator.checked = chkStatus;
    }
}

(function() {
    const chks = document.querySelectorAll('.n-order-chk');

    for (let i = 0; i < chks.length; i++) {
        chks[i].addEventListener('change', function() {
            if (!chks[i].checked) {
                document.querySelector('#allChk').checked = false;
            }
        });

    }
})();



function deleteAll() {
    const chkBoxArr = document.querySelectorAll('.n-order-chk');
    for (const iterator of chkBoxArr) {
        iterator.checked = true;
    }

    deleteSelectedOrder('전체 상품을 삭제하시겠습니까?');
}


/* 
상품 주문: 체크한 상품번호를 넘겨주고, 다음 페이지로 이동해서 보여주어야 한다.
똑같이 get 방식으로 진행? 
*/

//선택상품 주문버튼 클릭
function orderSelectedProduct() {
    const orderCheckBox = document.querySelectorAll('.n-order-chk');
    let orderQueryString = "";
    for (let i = 0; i < orderCheckBox.length; i++) {
        if (orderCheckBox[i].checked) {
            orderQueryString += ("&orderNo=" + orderCheckBox[i].value);
        }
    }
    if (orderQueryString === "") {
        alert("선택된 상품이 없습니다.");
        return;
    }

    location.href = contextPath + '/order/orderAll?' + orderQueryString;

}


//전체상품주문 버튼 클릭
function orderAll() {
    // document.querySelector('#allChk').checked = true;
    //가장 상단의 체크박스를 change한다고 해서 다른 함수가 실행되지는 않는다

    const chk = document.querySelectorAll('input[type="checkbox"]');

    for (const iterator of chk) {
        iterator.checked = true;
    }


    orderSelectedProduct();

}