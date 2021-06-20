function check() {
	var dl = document.getElementsByTagName('input')
	var info = document.getElementsByTagName('span')

	var no = document.getElementsByName('u')
	
//	var str = "9"
//	var regex = /[a-z]/;
//	if (regex.test(str)) {
//		alert("Đúng")
//	}
//	else {
//		alert("Sai")
//	}
	
	
	for (var i = 0; i < dl.length; i++) {
		if (dl[i].value == "") {
			//alert("Bắt buộc nhập liệu đủ");
			info[i].innerHTML = "Điền đủ dữ liệu"
		}
		else {
			info[i].innerHTML = ""
		}

		if (no[0].value.length > 5) {
			info[0].innerHTML = "Tên user không quá 5 kí tự"
		}
		
		if (dl[1].value != dl[2].value) {
			info[2].innerHTML = "Repass phải trùng pass"
		}
	}
	
	//check user
	
}

	var regexEmail = /\w+@\w+(\\.\w+){1,2}/
	var a = "@thaidhph06986gmail.edu.vn"
	
//	if (regexEmail.test(a)) {
//		alert("Đúng");
//	}
//	else {
//		alert("Sai")
//	}



//var regex = //;
//regex.test(dsdsđsds) trả về true or false
//vào trang regex101.com có toàn bộ kiểu regex

// bắt buộc nhập liệu
//đúng ràng buộc email (regex)
//pass == repass
//độ dài kí tự
//check số
//thêm (độ mạnh mật khẩu, capcha, ...)
