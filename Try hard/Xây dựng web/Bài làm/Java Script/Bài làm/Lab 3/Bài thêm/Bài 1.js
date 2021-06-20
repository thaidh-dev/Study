// JavaScript Document
var x = 0;
for (var i = 0; i < 3; i++) {
	var a = Number(prompt("Nhập số", ""));

	var b = Math.random()*99;

	if (a < 0 || a > 99) {
		alert("Hãy nhập số từ 0 đến 99");
		break;
	}
	else if (isNaN(a)) {
		alert("Hãy nhập số thôi");
		break;
	}
	else if (a == "") {
		alert("Bạn chưa nhập");
		break;
	}
	else if (a == b) {
		alert("Chúc mừng bạn");
		break;
	}
	else {
		if (a == b) {
			alert("Chúc mừng bạn");
			break;
		}
		else {
			alert("Đoán không đúng");
			if (x == 0) {
				var c = confirm("Bạn có muốn nhập tiếp không", "");
				if (c == true) {
					x++;
					continue;
				}
				else {
					i = 2;
				}
			}
			else {
				
			}
		}
	}
}