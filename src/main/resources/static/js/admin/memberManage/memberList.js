$(document).ready(function() {
    $(".goDetail").click(function() {
        // 클릭한 행(tr)에서 memberNo를 가져옴
        let memberNo = $(this).closest("tr").data("memberNo");
        
        locationProcess("/admin/memberManage/"+memberNo);
    });
});
