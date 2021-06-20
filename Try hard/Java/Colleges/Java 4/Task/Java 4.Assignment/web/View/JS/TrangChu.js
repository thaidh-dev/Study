$(function () {
    $(".btnKinhLup").click(function () {
        $(".hidden").slideToggle();
    });

    $(".anhTenMuiTen").click(function () {
        $("#dangXuat").slideToggle();
    });
});

$(function () {
    var banner = [];
    banner[0] = "Hinh/banner1.png";
    banner[1] = "Hinh/banner2.png";
    banner[2] = "Hinh/banner3.png";

    $(".btnBanner").each(function (index) {
        $(this).click(function () {
            $("#bannerImg").fadeOut(500);
            setTimeout(function () {
                $("#bannerImg").attr("src", banner[index]);
            }, 500);
            $("#bannerImg").fadeIn(500);
        });
    });
});

window.onload = function () {
    var sl = document.getElementsByClassName("soLuongHangTrongKho");
    var muaNgay = document.getElementsByClassName("muaNgay");

    for (var i = 0; i < sl.length; i++) {
        if (sl[i].value == 0) {
            muaNgay[i].setAttribute("value", "HẾT HÀNG");
            muaNgay[i].setAttribute("type", "button");
        };
    };
};