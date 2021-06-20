var g = document.getElementsByClassName("gia");
var sl = document.getElementsByClassName("soluong");
var tt = document.getElementsByClassName("thanhtien");

function tinh() {
	var hd = document.getElementById("tongtien");
	var tong = 0;
	for (var i = 0; i < g.length; i++) {
		tt[i].innerHTML = Number(g[i].innerHTML.substr(0, g[i].innerHTML.length-4)) * Number(sl[i].value);
		tong += Number(tt[i].innerHTML); 
		hd.innerHTML = tong;
	}
}

function them(button) {
	var row = button.parentElement.parentElement.cloneNode(true);
	var btnXoa = row.getElementsByTagName("button")[0];
	btnXoa.innerHTML = "Xóa";
	btnXoa.setAttribute("onClick", 'xoa(this)');
	document.getElementById("lol").appendChild(row);
}

function xoa(button) {
	var row = button.parentElement.parentElement;
	document.getElementsByTagName("table")[1].removeChild(row);
}