function sinhVien() {
	var ten = prompt("Nhập tên", "");
	var diem = Number(prompt("Nhập điểm", ""));
	
	this.xepLoai = function() {
		if (diem >= 5) {
			return "Đậu"
		}
		else {
			return "Rớt"
		}
	}
	
	this.hienThi = function() {
		alert("Tên: " + ten
			 + "\nĐiểm: " + diem
			  + "\nXếp loại: " + this.xepLoai()
			 )
	}
}

new sinhVien().hienThi();