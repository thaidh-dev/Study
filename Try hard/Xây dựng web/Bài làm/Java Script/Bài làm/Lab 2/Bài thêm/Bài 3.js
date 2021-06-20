var a = Number(prompt("Cạnh a", ""));
var b = Number(prompt("Cạnh b", ""));
var c = Number(prompt("Cạnh c", ""));

if (a == "" | b == "" | c == "") {
	alert("bạn chưa nhập đủ");
}
else if (isNaN(a) | isNaN(b) | isNaN(c)) {
	alert("Hãy nhập số");
}
else if (a+b > c && b+c > a && a+c > b) {
	alert("Là tam giác");
}
else {
	alert("Không là tam giác");
}