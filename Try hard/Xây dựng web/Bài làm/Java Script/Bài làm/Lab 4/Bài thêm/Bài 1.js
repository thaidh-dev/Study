function nhanVien(ma, ten, chucVu) {
	this.ma = ma;
	this.ten = ten;
	this.chucVu = chucVu;
	this.luongCoBan = 3750000;
	this.heSo;
	this.phuCap;
	this.luong;
	
	this.main = function() {
		this.heSoLuong();
		this.tinhLuong();
		this.hienThi();
	}
	
	this.heSoLuong = function() {
		switch (this.chucVu) {
			case "Giám đốc":
				this.heSo = 10;
				this.phuCap = 2500000;
				break;
			case "Phó giám đốc":
				this.heSo = 9;
				this.phuCap = 2000000;
				break;
			case "Trưởng phòng":
				this.heSo = 8;
				this.phuCap = 1500000;
				break;
			case "Phó phòng":
				this.heSo = 7;
				this.phuCap = 1000000;
				break;
			case "Nhân viên":
				this.heSo = 6;
				this.phuCap = 500000;
				break;
		}
	}
	
	this.nhap = function() {
		this.ma = prompt("Nhập mã", "");
		this.ten = prompt("Nhập tên", "");
		this.chucVu = prompt("Nhập chức vụ", "");
	}
	
	this.tinhLuong = function() {
		this.luong = this.luongCoBan*this.heSo + this.phuCap;
	}
	
	this.hienThi = function() {
		document.write("Mã: " + this.ma
					  +"<br>Tên: " + this.ten
					   +"<br>Chức vụ: " + this.chucVu
					   +"<br>Hệ số: " + this.heSo
					   +"<br>Lương: " + this.luong + "<br>" + "<br>"
					  );
	}
}

var nv1 = new nhanVien('nv1', 'thai', 'Phó giám đốc');
nv1.main();

var nv2 = new nhanVien();
nv2.nhap();
nv2.main();