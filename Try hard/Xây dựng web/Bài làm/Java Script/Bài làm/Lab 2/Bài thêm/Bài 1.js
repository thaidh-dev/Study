var a = prompt("Nhập 1 số", "");

var b = Math.random()*999;

if (a == "") {
	alert("Bạn chưa nhập dữ liệu");
}
else if (a == null) {
	alert("Không nhập thì thôi");
}
else if (isNaN(a)) {
	alert("Hãy nhập số");
}
else if (a == b) {
	alert("Bạn đoán đúng rồi");
}
else {
	alert("Bạn đoán sai rồi");
}