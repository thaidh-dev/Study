var x = 10 + Math.round(Math.random()*5);
var a = prompt("Hãy nhập 1 số", "");

if (a == "") {
	alert("Bạn chưa nhập dữ liệu");
}
else if (a == null) {
	alert("Không nhập thì thôi")
}
else {
	if (isNaN(a)) {
		alert("Hãy nhập số");
	}
	else {
		if (a == x) {
			alert("Bạn đoán đúng rồi")
		}
		else if (a > x) {
			alert("Số bạn đoán lớn hơn số bí mật")
		}
		else {
			alert("Số bạn đoán nhỏ hơn số bí mật")
		}
	}
}