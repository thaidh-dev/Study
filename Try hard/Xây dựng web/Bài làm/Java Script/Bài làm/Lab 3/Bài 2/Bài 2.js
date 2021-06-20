var a = Number(prompt("Số a", ""));
var b = Number(prompt("Số b", ""));
var c = 0;

for (var i = 0; i < 10; i++) {
	c = b;
	b += a;
	a = c;
	document.write(b + " ");
}
