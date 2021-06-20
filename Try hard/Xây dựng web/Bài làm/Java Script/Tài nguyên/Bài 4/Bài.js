setTimeout('alert("hi các bạn")', 2000); //dùng 1 lần
var time2 = setInterval('alert("hi cac ban")', 4000); //dùng n lần

function huy() {
	clearInterval(time2);
}
//#
//#//var img[] = [];
//#//img[0] = "";
//#//img[1] = "";
//#//img[2] = "";
//#//img[3] = "";
//#//img[4] = "";
//#//var i = 0;
//#//var auto;
//#
//#//function autoLoad() {
//#//	i++;
//#//	document.getElementById("demoanh").src = img[i];
//#//	if (i == img.length) {
//#//		i = -1;
//#//	}
//#//	auto = setTimeout(autoLoad, 1000);
//#//}
//#
//#//window.onload = function() {
//#//	auto = setTimeout(autoLoad, 1000);
//#//}
//#
//#//hướng đối tượng


//function sinhvien(masv, tensv, diem) {
//	this.masv = masv;
//	this.tensv = tensv;
//	this.diem = diem;
//	
//	this.main = function() {
//		this.nhap();
//		this.xepLoai();
//		this.hienthi();
//	}
//	
//	this.hienthi = function() {
//		document.write("mã sinh viên: "+this.masv);
//		document.write("<br>tên sinh viên: "+this.tensv);
//		document.write("<br>điểm sinh viên: "+this.diem);
//		document.write("<br>Xếp loại: "+this.xepLoai());
//	}
//	
//	this.nhap = function() {
//		this.masv = prompt("Mã", "");	
//		this.tensv = prompt("Tên", "");
//		this.diem = prompt("Điểm", "");
//	}
//	
//	this.xepLoai = function() {
//		if (this.diem >= 9 && this.diem <= 10) {
//			return "Xuất sắc";
//		}
//		else if (this.diem >= 8) {
//			return "Giỏi";
//		}
//		else if (this.diem >= 6.5) {
//			return "Khá";
//		}
//		else if (this.diem >= 5) {
//			return "Trung bình";
//		}
//		else {
//			return "Yếu";
//		}
//	}
//}

//var sv1 = new sinhvien();
//sv1.main();



