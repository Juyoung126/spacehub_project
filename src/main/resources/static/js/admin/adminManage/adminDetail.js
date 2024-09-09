$(document).ready(function() {
	var admId = $("#admId").text();
	// 관리자 정보를 가져와 최고 관리자 여부를 확인
	$("#adminListBtn").on("click", function(){
		locationProcess("/admin/adminManage");
	});
	
	$("#adminDeleteBtn").on("click", function(){
	    if (confirm("정말 삭제하시겠습니까?")) {
	        $.ajax({
	            url: '/admin/deleteAdmin',
	            type: 'POST',
	            data: { admId: admId },
	            success: function(response) {
	                alert("관리자가 삭제되었습니다.");
	                locationProcess("/admin/adminManage");
	            },
	            error: function(xhr, status, error) {
	                console.error("Status:", xhr.status);
	                console.error("Response Text:", xhr.responseText);
	                console.error("Error:", error);
	                alert("관리자 삭제 중 오류가 발생했습니다.");
	            }
	        });
	    }
	});
	
	$("#adminListBtn").on("click", function(){
		locationProcess("/admin/adminManage");
	});
});
