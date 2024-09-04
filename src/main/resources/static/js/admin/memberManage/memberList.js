$(document).ready(function() {
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
        // 클릭한 행(tr)에서 memberNo를 가져옴
        let memberNo = $(this).closest("tr").data("memberNo");
        // 현재 필터링된 상태에 맞는 행에서만 동작하도록 함
        let selectedStatus = $('#status-filter').val();
        let rowStatus = $(this).closest("tr").data("status");
        if (selectedStatus === 'all' || rowStatus === selectedStatus) {
               locationProcess("/admin/memberManage/" + memberNo);
    	}
	});
});