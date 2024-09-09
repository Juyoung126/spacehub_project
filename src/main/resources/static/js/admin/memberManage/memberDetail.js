var memberId = $("#memberId").text();
$("#memberListBtn").on("click", function(){
	locationProcess("/admin/memberManage");
});

$("#memberDeleteBtn").on("click", function(){
    // Confirm dialog
    if (confirm("정말 삭제하시겠습니까?")) {
        // AJAX request to delete the member
        $.ajax({
            url: '/admin/memberManage/deleteMember',  // Actual URL for deletion
            type: 'POST',
            data: { memberId: memberId }, 
            success: function(response) {
                // On successful deletion
                alert("회원이 삭제되었습니다.");
                locationProcess("/admin/memberManage");
            },
            error: function(xhr, status, error) {
                // On failure
                console.error("Status:", xhr.status);
                console.error("Response Text:", xhr.responseText);
                console.error("Error:", error);
                alert("회원 삭제 중 오류가 발생했습니다.");
            }
        });
    }
});
    