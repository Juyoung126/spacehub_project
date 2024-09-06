$("#memberListBtn").on("click", function(){
	locationProcess("/admin/memberManage");
});

$("#memberDeleteBtn").on("click", function(){
    // 확인 대화상자 띄우기
    if (confirm("정말 삭제하시겠습니까?")) {
        // 확인 버튼을 눌렀을 때
        // 실제 삭제 요청을 서버에 보냅니다.
        $.ajax({
            url: '/myPage/deleteMember',  // 실제 삭제 처리 URL로 변경
            type: 'POST',
            data: { /* 삭제할 회원의 식별자 등 필요한 데이터 */ },
            success: function(response) {
                // 삭제 성공 시
                alert("회원이 삭제되었습니다.");
                // 삭제 후 페이지 이동이나 갱신 등을 수행할 수 있습니다.
                locationProcess("/admin/memberManage");
            },
            error: function(xhr, status, error) {
                // 삭제 실패 시
                console.error("Status:", xhr.status);
                console.error("Response Text:", xhr.responseText);
                console.error("Error:", error);
                alert("회원 삭제 중 오류가 발생했습니다.");
            }
        });
    } else {
        // 취소 버튼을 눌렀을 때
        // 아무 작업도 하지 않음
    }
});
