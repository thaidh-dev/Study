var ten = prompt("Tên của bạn là", "");

if (ten == "") {
	alert("Bạn chưa nhập dữ liệu");
}
else if (ten == null) {
	alert("Không nhập thì thôi");
}
else if (isNaN(ten)) {
	alert("Tên bạn là "+ten);
}
else {
	alert("Hãy nhập tên bạn cho nó hẳn hoi");
}

var diem = prompt("Điểm", "");
if (diem == "") {
	alert("Bạn chưa nhập dữ liệu");
}
else if (diem == null) {
	alert("Không nhập thì thôi");
}
else if (isNaN(diem)) {
	alert("Hãy nhập số");
}
else if (diem < 5) {
	alert("Rớt");
}
else {
	alert("Đậu");
}