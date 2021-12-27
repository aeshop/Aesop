function deleteBoard(no) {
    if(confirm("정말로 삭제 하시겠습니까?")){
        $.ajax({
        url : contextPath + "/board/notice/delete",
        data : {"no":no},
        
        success : function(result){
            if(result>0){
                alert("글이 삭제되었습니다..");
               location.href=contextPath+"/board/notice/list?c=801";
            }
        },
        error : function(req,status,error){
            console.log("글 삭제 실패");
            console.log(req.responseText); 
        }
    });
}
}
function updateBoard(){
	document.requestForm.action = "updateBoard";
	document.requestForm.method = "POST";
	document.requestForm.submit();
}