function phuongTrinhBacHai(a, b, c) {
	this.a = a;
	this.b = b;
	this.c = c;
	
	this.tinh = function() {
		if (a == 0) {
			document.write("x = "+-c/b);
		}
		else {
			var delta = Math.pow(b, 2) - 4*a*c;
			if (delta == 0) {
				document.write("x1 = x2 = "+-b/(2*a));
			}
			else if (delta > 0) {
				document.write("x1 = " + (-b+Math.sqrt(delta))/(2*a));
				document.write("<br>x2 = " + (-b-Math.sqrt(delta))/(2*a));
			}
			else {
				document.write("Vô nghiệm");
			}
		}
	}
}

new phuongTrinhBacHai(0, 3, 3).tinh();