function kiemtra() {
	var regexten = /^\w+$/
	var regexsdt = /^0[0-9]{9,10}$/
	var regexpass = /^[A-Z].{7,}$/
	var regexpass2 = /[0-9]+/ 
	var trong  = 0
	var txt = document.getElementsByClassName("txt")
	var span = document.getElementsByTagName("span")
	
	for (var i = 0; i < txt.length; i++) {
		if (txt[i].value == "") {
			trong++;
			span[i].innerHTML = "Vui lòng nhập liệu"
		}
		else {
			span[i].innerHTML = ""
		}
	}
	
	if (trong == 0) {
		if (regexten.test(txt[0].value)) {
			span[0].innerHTML = ""
		}
		else {
			span[0].innerHTML = "Tên chỉ bao gồm số, chữ và dấu gạch chân"
		}

		if (regexsdt.test(txt[3].value)) {
			span[3].innerHTML = ""
		}
		else {
			span[3].innerHTML = "Phone có 10 hoặc 11 số và bắt đầu bằng 0"
		}
		
		if (regexpass.test(txt[1].value)) {
			span[1].innerHTML = ""
			if (regexpass2.test(txt[1].value)) {
				span[1].innerHTML = ""
				if (txt[1].value == txt[2].value) {
					span[2].innerHTML = ""
				}
				else {
					span[2].innerHTML = "Repass không giống pass"
				}
			}
			else {
				span[1].innerHTML = "Pass phải có ít nhất 1 số"
			}
		}
		else {
			span[1].innerHTML = "Pass phải có 8 kí tự trở lên và có 1 chữ hoa ở đầu"
		}
	}
}

var manganh = []
manganh[0] = "Images/phở.jpg"
manganh[1] = "Images/trà sữa.jpg"
manganh[2] = "Images/chân gà.jpg"
manganh[3] = "Images/gà.jpg"
manganh[4] = "Images/táo.png"

var i = 0

function next() {
	if (i == 4) {
		i = -1;
	}
	document.getElementsByTagName("img")[0].src = manganh[i+1];
	i++;
}

function pre() {
	if (i == 0) {
		i = 5;
	}
	document.getElementsByTagName("img")[0].src = manganh[i-1];
	i--;
}
