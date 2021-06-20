var n = 10000000000;
document.write(n.toLocaleString('en-US')+'<u>đ</u>');

function xoaPhay(a) {
	a = a.replace(/,/g, '');
	a = a.substring(0, a.length - 1);
	return a;
}

var a = '100,000,000đ';
var x = xoaPhay(a);
document.write('<br>'+x);
