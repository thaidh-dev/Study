// JavaScript Document
var n = Number(prompt("Nhập n", ""));
var tong = 0;
var a = [n];


if (isNaN(n)) {
	alert("Hãy nhập số thôi");
}
else if (n == "") {
	alert("Bạn chưa nhập dữ liệu");
}
else {
	for (var i = 0; i < n; i++) {
		a[i] = Number(prompt("Nhập phần tử "+i, ""));
		tong += a[i];
	}
	alert("Tổng = "+tong);
}
