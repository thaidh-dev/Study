function thanhtien(input) {
	var row = input.parentElement.parentElement;
	var gia = row.getElementsByClassName("gia")[0];
	var sl = row.getElementsByClassName("soluong")[0];
	var tt = row.getElementsByClassName("tien")[0];
	
	tt.innerHTML = gia.innerHTML*sl.value;
	
	tongtien();
}

function tongtien() {
	var tt = document.getElementsByClassName("tien");
	var tong = document.getElementById("tong");
	
	tong.innerHTML = Number(tt[0].innerHTML) + Number(tt[1].innerHTML)
}

function kiemtra() {
	var regexten = /^[a-zA-Z0-9 ]{6,}$/
	var regexsdt = /^0[0-9]{9}$/
	var trong = 0
	var txt = document.getElementsByClassName("txt")
	var span = document.getElementsByTagName("span")
	var regex = [regexten, regexsdt]
	var sai = ["tên", "số điện thoại"]
	
	for (var i = 0; i < txt.length; i++) {
		if (txt[i].value == "") {
			trong++;
			span[i].innerHTML = "Vui lòng nhập đủ dữ liệu"
		}
	}
	
	if (trong == 0) {
		for (var i = 0; i < txt.length; i++) {
			if (regex[i].test(txt[i].value)) {
				span[i].innerHTML = ""
			}
			else {
				span[i].innerHTML = "Nhập sai "+sai[i]
			}
		}
	}
}

var manganh = []
manganh[0] = "Images/phở.jpg"
manganh[1] = "Images/trà sữa.jpg"
manganh[2] = "Images/chân gà.jpg"

function check() {
	var rad = document.getElementsByName("gt")
	var img = document.getElementsByTagName("img")
	var s = document.getElementById("tenanh");
	
	for (var i = 0; i < rad.length; i++) {
		if (rad[i].checked == true) {
			img[0].src = manganh[i]
			s.innerHTML = "Ảnh "+(i+1);
		}
	}
}

