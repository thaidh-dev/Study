function xoaPhay(a) {
    var x = a.innerHTML;
    x = x.replace(/,/g, '');
    x = x.substring(0, x.length - 8);
    return x;
}

function tinhThanhTien() {
    var gia = document.getElementsByClassName("gia");
    var number = document.getElementsByClassName("number");
    var thanhTien = document.getElementsByClassName("thanhTien");
    var tongTien = document.getElementById("tongTien");
    var soLuongDaMua = document.getElementById("soLuong");
    
    for (var i = 0; i < gia.length; i++) {
        thanhTien[i].innerHTML = (xoaPhay(gia[i]) * number[i].value).toLocaleString("en-US") + "<u>đ</u>";
    }
    
    tinhTongTien_SoLuongDaMua(number, thanhTien, tongTien, soLuongDaMua);
};

function xoa(input) {
    var row = input.parentElement.parentElement;
    document.getElementsByTagName("tbody")[0].removeChild(row);
    
    var number = document.getElementsByClassName("number");
    var thanhTien = document.getElementsByClassName("thanhTien");
    var tongTien = document.getElementById("tongTien");
    var soLuongDaMua = document.getElementById("soLuong");

    tinhTongTien_SoLuongDaMua(number, thanhTien, tongTien, soLuongDaMua);
}

function tinhTongTien_SoLuongDaMua(number, thanhTien, tongTien, soLuongDaMua) {
    var tt = 0;
    var sl = 0;
    for (var i = 0; i < thanhTien.length; i++) {
        tt += Number(xoaPhay(thanhTien[i]));
        sl += Number(number[i].value);
    }
    
    tongTien.innerHTML = tt.toLocaleString("en-US") + "<u>đ</u>";
    soLuongDaMua.innerHTML = sl; 
}

window.onload = function() {
    tinhThanhTien();
};