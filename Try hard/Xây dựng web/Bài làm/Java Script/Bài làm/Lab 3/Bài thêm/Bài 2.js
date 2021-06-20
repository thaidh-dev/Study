// JavaScript Document
var x = Number(prompt("Nhập số"), "");
var tong = 0;

if (isNaN(x)) {
	alert("Hãy nhập số thôi");
}
else if (x == "") {
	alert("Bạn chưa nhập dữ liệu");
}
else {
	for (var i = 0; i <= x; i++) {
		if (i % 3 == 0 && i % 5 == 0) {
			tong += i;
		}
	}
	alert("Tổng các số chia hết cho 3 và 5 = " + tong);
}

