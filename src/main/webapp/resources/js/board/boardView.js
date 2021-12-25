function deleteBoard(boardNo) {
    if(confirm("정말로 삭제 하시겠습니까?")){
        $.ajax({
        url : contextPath + "/delete",
        data : {"boardNo":boardNo},
        
        success : function(result){
            if(result>0){
                alert("글이 삭제되었습니다..");
               
            }
        },
        error : function(req,status,error){
            console.log("글 삭제 실패");
            console.log(req.responseText); 
        }
    });
}
}
