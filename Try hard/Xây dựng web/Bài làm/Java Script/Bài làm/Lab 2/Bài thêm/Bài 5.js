function chonsp(sp) {
	switch (sp) {
		case "tao":
			return 170;
			break;
		case "cam":
			return 80;
			break;
		case "nho":
			return 240;
			break;
		case "mit":
			return 60;
			break;
		case "le":
			return 75;
			break;
	}
}

function thanhtien(gia, soluong) {
	var tongtien;
	tongtien = gia * soluong;
	return tongtien;
}

function inhoadon(a, b, c, d ,e) {
	alert("Tên khách hàng: "+a
		 +"\nSản phẩm: "+b
		  +"\nGiá: "+c
		  +"\nSố lượng: "+d
		  +"\nThành tiền: "+e
		 );
}
