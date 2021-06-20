function them(button) {
	var row = button.parentElement.parentElement.cloneNode(true);
	var btnXoa = row.getElementsByTagName("button")[0];
	btnXoa.innerHTML = "Xóa";
	btnXoa.setAttribute("onClick", 'xoa(this)');
	document.getElementById("hoadon").appendChild(row);
}

function xoa(button) {
	var row = button.parentElement.parentElement;
	document.getElementById("hoadon").removeChild(row);
}