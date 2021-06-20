var a = Number(prompt("Nhập a", ''));
var b = Number(prompt("nhập b", ""));
var phepTinh = prompt("Toán tử", "");

switch (phepTinh) {
	case "+":
		alert(a+b);
		break;
	case "-":
		alert(a-b);
		break;
	case "*":
		alert(a*b);
		break;
	case "/":
		alert(a/b);
		break;
	default:
		alert("Hãy nhập phép tính +, -, *, /");
		break;
}