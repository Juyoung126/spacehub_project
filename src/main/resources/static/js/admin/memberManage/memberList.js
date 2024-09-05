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
        let memberNo = $(this).closest("tr").data("memberNo");
        let selectedStatus = $('#status-filter').val();
        let rowStatus = $(this).closest("tr").data("status");
        if (selectedStatus === 'all' || rowStatus === selectedStatus) {
            window.location.href = "/admin/memberManage/" + memberNo;
        }
    });
});
