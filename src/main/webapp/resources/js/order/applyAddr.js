/* 
payment.jsp 파일에서 주소록 클릭했을때 나오는 팝업 주소창에서
부모 주소창으로 데이터를 전달하고 팝업 주소창을 닫는 함수

*/

function setParentAddress(event) {

    /* 
  값을 전달하는 방식
  opener : 팝업창을 부른 부모 페이지와 연결시키는 객체?

  opener.부모태그.value, src 등등... = 자식태그.value 

  예:  opener.document.getElementById("pInput").value = document.getElementById("cInput").value

출처: https://all-record.tistory.com/149 [세상의 모든 기록]
  

-> 값이 여러개니 객체로 전달해서 객체로 요소요소에 집어넣고 닫으면 될듯하다

값 담을 때 어떻게 긁어올지 tr들의 td로?  - 기본 배송지면 span이 걸리게 만듬

querySelector는 document에만 걸수있는건 아님 특정 element의 하위 element들을 CSS선택자 방식으로 검색하는것뿐임

*/

    const row = event.target.parentNode.parentNode;


    const arr = row.querySelectorAll('td>span:nth-child(1)');

    //1 객체에 팝업창 값 담기
    const addrInfo = {
        addressName: arr[0].innerText,
        zipCode: arr[1].innerText,
        receiverName: arr[2].innerText,
        addrPhone: arr[3].innerText,
        address1: arr[4].innerText,
        address2: row.querySelector('.addressName>span:nth-child(2)').innerText
    };

    // console.log(addrInfo);

    opener.document.getElementById('rName').value = addrInfo.receiverName;
    opener.document.getElementById('sample6_postcode').value = addrInfo.zipCode;
    opener.document.getElementById('sample6_address').value = addrInfo.address1;
    opener.document.getElementById('sample6_detailAddress').value = addrInfo.address2;


    const phoneArr = addrInfo.addrPhone.split('-');
    opener.document.getElementById('rPhone1').value = phoneArr[0];
    opener.document.getElementById('rPhone2').value = phoneArr[1];
    opener.document.getElementById('rPhone3').value = phoneArr[2];


    opener.document.getElementById('sameaddr1').checked = false;

    window.close();
}