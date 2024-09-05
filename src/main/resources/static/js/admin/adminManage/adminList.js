$(document).ready(function() {
	$.ajax({
        type: "GET",
        url: "/admin/getAdminInfo",
        success: function(response) {
            // 응답으로 받은 Admin 객체에서 최고 권한 여부 확인
            if (response && response.admSuper === "Y") {
                $("#insertAdminBtn").show(); // 최고 권한자일 경우 버튼 표시
            } else {
                $("#insertAdminBtn").hide(); // 최고 권한자가 아닐 경우 버튼 숨기기
            }
        },
        error: function(xhr, status, error) {
            console.error("사용자 정보 요청 실패:", status, error);
            // 요청 실패 시 버튼 숨기기
            $("#create-btn").hide();
        }
    });
	
    $('#status-filter').change(function() {
        var selectedState = $(this).val();
        $('table tbody tr').each(function() {
            var rowStatus = $(this).data('status');
            if (selectedState === 'all' || rowStatus === selectedState) {
                $(this).removeClass('hidden');
            } else {
                $(this).addClass('hidden');
            }
        });
    });

    $(".goDetail").click(function() {
        // 클릭한 행(tr)에서 admNo를 가져옴
        let admNo = $(this).closest("tr").data("adminNo");
        // 현재 필터링된 상태에 맞는 행에서만 동작하도록 함
        let selectedStatus = $('#status-filter').val();
        let rowStatus = $(this).closest("tr").data("status");
        if (selectedStatus === 'all' || rowStatus === selectedStatus) {
               locationProcess("/admin/adminManage/" + admNo);
    	}
	});
	
	$("#insertAdminBtn").on("click", function(){
		locationProcess("/admin/adminManage/insertAdminForm");
	})
});