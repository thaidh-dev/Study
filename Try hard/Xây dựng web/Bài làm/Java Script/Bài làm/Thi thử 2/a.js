function tinhgia(input) {
	var row = input.parentElement.parentElement;
	var tt = row.getElementsByClassName("tien")[0];
	var g = row.getElementsByClassName("gia")[0];
	var sl = row.getElementsByClassName("sl")[0];
	
	tt.innerHTML = Number(g.innerHTML) * Number(sl.value);
	tinhtong();
}

function tinhtong() {
	var tt = document.getElementsByClassName("tien");
	
	tt[2].innerHTML = Number(tt[0].innerHTML) + Number(tt[1].innerHTML)
}

function kiemtra() {
	var regexten = /^[a-zA-Z ]{6,}$/;
	var regexsdt = /^0[0-9]{9}$/
	var txt = document.getElementsByClassName("txt");
	var trong = 0;
	var s = document.getElementsByTagName("span");
	var regex = [regexten, regexsdt];
	var sai = ["tên", "số điện thoại"];
	
	for (var i = 0; i < txt.length; i++) {
		if (txt[i].value == "") {
			trong++;
			s[i].innerHTML = "Vui lòng nhập liệu";
		}
		else {
			s[i].innerHTML = "";
		}
	}
	
	if (trong == 0) {
		for (var i = 0; i < txt.length; i++) {
			if (regex[i].test(txt[i].value)) {
				s[i].innerHTML = "";
			}
			else {
				s[i].innerHTML = "Nhập sai "+sai[i];
			}
		}
	}
}

var anhnen = [];
anhnen[0] = "images//gà.jpg";
anhnen[1] = "images//trà sữa.jpg";
anhnen[2] = "images//phở.jpg";


function slideshow() {
	var a = document.getElementsByName("gt");
	
	for (var i = 0; i < a.length; i++) {
		if (a[i].checked == true) {
			document.getElementsByTagName("img")[0].src = anhnen[i];
			document.getElementById("anh").innerHTML = "Ảnh "+(i+1)
		}
	}
}
