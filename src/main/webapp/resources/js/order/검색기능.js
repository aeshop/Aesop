function search() {
    const searchBar = document.querySelector('#searchKeyword');
    //클래스로 입력창 내용을 받아옴

    if (searchBar.value.trim().length == 0) {
        alert('검색어를 입력해 주세요.');
        return;
    }

    const keyword = searchBar.value;


    location.href = contextPath + '/category/search?keyword=' + keyword;



}


document.addEventListener('keydown', function(e) {
    if (searchBar.focus && e.key == 13) {
        search();
    }
});