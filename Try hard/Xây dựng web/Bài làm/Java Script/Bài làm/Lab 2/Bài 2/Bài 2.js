var a = prompt("Nhập 1 số", "");

if (a == "") {
	alert("Bạn chưa nhập dữ liệu");
}
else if (a == null) {
	alert("không nhập thì thôi")
}
else if (isNaN(a)) {
	alert("Hãy nhập 1 số");
} 
else {
	alert("Căn a = "+Math.sqrt(a)
		+"\na bình = "+Math.pow(a, 2)
		+"\nLàm tròn a = "+Math.round(a)
	
	);
}
