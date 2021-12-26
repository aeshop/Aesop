function search() {
    const searchBar = document.querySelector('검색창 태그에 주는 아이디');
    //클래스로 입력창 내용을 받아옴

    if (searchBar.value.trim().length == 0) {
        alert('검색어를 입력해 주세요.');
        return;
    }

    const keyword = searchBar.value;


    location.href = '/teamSemiProject2/category/search?keyword=' + keyword;



}


document.querySelector('.r-form-control-button').addEventListener('click', search);