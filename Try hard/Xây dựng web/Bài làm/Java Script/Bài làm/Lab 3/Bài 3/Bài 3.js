var b = Math.round(Math.random()*10);

while (true) {
	var a = Number(prompt("Nhập số", ""));
	if (a == b) {
		alert("Chúc mừng");
		break;
	}
	else if (a > b) {
		alert("Số bạn đoán lớn hơn số bí mật");
	}
	else if (a < b) {
		alert("Số bạn đoán nhỏ hơn số bí mật");
	}
}