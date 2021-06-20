
function tinh(a, b, c) {
	var delta = Math.pow(b, 2) - 4*a*c;
	if (a == "" | b == "" | c == "") {
		alert("Bạn chưa nhập đủ ô")
	} 
	else if (a == null | b == null | c == null) {
		alert("Không nhập thì thôi");
	}
	else if (delta == 0) {
		return (-b/(2*a));
	}
	else if (delta < 0) {
		return "Vô nghiệm";
	} 
	else {
		return "x1 = " + (-b + Math.sqrt(delta))/(2*a) +
			"\n x2 = " + (-b - Math.sqrt(delta))/(2*a);
	}
}