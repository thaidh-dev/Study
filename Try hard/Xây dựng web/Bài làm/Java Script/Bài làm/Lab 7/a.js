var txt = document.getElementsByClassName("te");

function moi() {
	for (var i = 0; i < txt.length; i++) {
		txt[i].value = "";
	}
}

function nhancapcha() {
	txt[5].value = Math.round(1000 + Math.random()*(9999 - 1000));
}

function kiemtra() {
	var sa = document.getElementsByTagName("span");
	var lol = ['tên', 'mật khẩu', 'mật khẩu nhập lại', 'số điện thoại', 'capcha'];
	var trong = 0;
	var regexUser = /^[a-zA-Z]{6,}$/;
	var regexPass = /^\w+$/;
	var regexPass2 = /^[A-Z]+$/;
	var regexPass3 = /^[0-9]+$/;
	var regexSdt = /^[0-9]{9,10}$/;
	
	for (var i = 0; i < 4; i++) {
		if (txt[i].value == "") {
			sa[i].innerHTML = "Vui lòng nhập "+lol[i];
			trong++;
		}
		else {
			sa[i].innerHTML = ""
		}
	}
	
	if (trong == 0) {
		if (!regexUser.test(txt[0].value)) {
			sa[0].innerHTML = "Tên phải có nhiều hơn 6 kí tự và không có dấu cách, số hay kí tự đặc biệt";
		}
		else {
			sa[0].innerHTML = "";
		}
		
		if (regexPass.test(txt[1].value) && regexPass2.test(txt[1].value) && regexPass3.test(txt[1].value)) {
			sa[1].innerHTML = ""
		}
		else {
			sa[1].innerHTML = "Pass phải có ít nhất 1 chữ hoa và 1 số"
		}
	}
}