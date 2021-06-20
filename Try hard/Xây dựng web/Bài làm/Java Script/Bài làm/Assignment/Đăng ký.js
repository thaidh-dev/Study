for (var i = 2005; i >= 1950; i--) {
	var o = document.createElement("option");
	o.innerHTML = i;

	var a = document.getElementById("nam");
	a.appendChild(o);
}

for (var i = 1; i <= 31; i++) {
	var o = document.createElement("option");
	o.innerHTML = i;
	
	var a = document.getElementById("ngay");
	a.appendChild(o);
}

for (var i = 1; i <= 12; i++) {
	var o = document.createElement("option");
	o.innerHTML = i;

	var a = document.getElementById("thang");
	a.appendChild(o);
}

function se(input) {
	var row = input.parentElement.parentElement;
	var c = row.getElementsByClassName("check")[0];
	var tt = row.getElementsByClassName("thanhtien")[0];
	var s = row.getElementsByClassName("sl")[0];
	
	if (c.checked) {
		s.removeAttribute("readonly");
		tinhgia(input);
	}
	else {
		s.setAttribute("readonly", "");
		tt.innerHTML = "";
		tinhtong();
	}
}

function tinhgia(input) {
	var row = input.parentElement.parentElement;
	var g = row.getElementsByClassName("gia")[0];
	var tt = row.getElementsByClassName("thanhtien")[0];
	var s = row.getElementsByClassName("sl")[0];
	
	tt.innerHTML = Number(g.innerHTML) * Number(s.value);
	
	tinhtong();
}

function tinhtong() {
	var ttt = document.getElementsByClassName("thanhtien");
	var tog = 0;
	
	for (var i = 0; i < ttt.length; i++) {
		tog += Number(ttt[i].innerHTML);
	}
	document.getElementsByClassName("tong")[0].innerHTML = tog;
}

function kiemtra() {
	var txt = document.getElementsByClassName("ip");
	var sa = document.getElementsByTagName("span");
	var lol = ['họ tên', 'số điện thoại', 'email', 'mật khẩu', 'mật khẩu nhập lại'];
	var regexEmail = /^\w+@\w+(\.\w+){1,2}$/;
	var regexSDT = /^0[0-9]{9,10}$/;
	var regexPass = /.{6,32}/
	var trong = 0;
	var sai = 0;
	var regex = [regexSDT, regexEmail, regexPass];
	
	for (var i = 0; i < txt.length; i++) {
		if (txt[i].value == "") {
			sa[i].innerHTML = "Vui lòng nhập "+lol[i];
			sa[i].style.color = "red";
			trong++;
		}
		else {
			sa[i].innerHTML = "";
		}
		
		if (!document.getElementsByName("gt")[0].checked && !document.getElementsByName("gt")[1].checked) {
			trong++;
			sa[5].innerHTML = "Vui lòng chọn giới tính"
		}
		else {
			sa[5].innerHTML = "";
		}
	}
		
	if (trong == 0) {
		for (var i = 0; i < regex.length; i++) {
			if (regex[i].test(txt[i+1].value)) {
				sa[i+1].innerHTML = "";
			}
			else {
				sai++;
				sa[i+1].innerHTML = "Nhập sai "+lol[i+1];
			}
		}
	}
	
	if (sai == 0 && trong == 0) {
		if (txt[3].value != txt[4].value) {
			sa[4].innerHTML = "Mật khẩu nhập lại không đúng";
		}
		else {
			var mk = txt[3].value.length;
			if (mk > 6) {
				sa[3].innerHTML = "Yếu";
				sa[3].style.color = "red";
			}
			if (mk >= 10) {
				sa[3].innerHTML = "Trung bình";
				sa[3].style.color = "yellow";
			}
			if (mk >= 15) {
				sa[3].innerHTML = "Mạnh";
				sa[3].style.color = "green";
			}
		}
	}
}
