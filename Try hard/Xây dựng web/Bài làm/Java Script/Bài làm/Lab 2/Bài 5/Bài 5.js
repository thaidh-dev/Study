var a = Number(prompt("Số a", ""));
var b = Number(prompt("Số b", ""));
var c = Number(prompt("Số c", ""));

var delta = Math.pow(b, 2) - 4*a*c;

if (a == "" | b == "" | c == "") {
	alert("Bạn chưa nhập đủ ô")
} 
else if (a == null | b == null | c == null) {
	alert("Không nhập thì thôi");
}
else if (delta == 0) {
	alert("Nghiệm kép x1 = x2 = "+ -b/(2*a))
}
else if (delta < 0) {
	alert("Vô nghiệm");
} 
else {
	alert("x1 = " + (-b + Math.sqrt(delta))/(2*a) +
		"\nx2 = " + (-b - Math.sqrt(delta))/(2*a));
}