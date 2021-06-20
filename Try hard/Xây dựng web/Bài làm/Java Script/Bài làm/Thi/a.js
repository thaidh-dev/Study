function kiemtra() {
	var txt = document.getElementsByClassName("txt");
	var span = document.getElementsByTagName("span")
	var trong = 0
	var regexten = /^\w{8,}$/
	
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
			span[0].innerHTML = "Nhập sai tên"
		}
		
		if (txt[1].value == txt[2].value) {
			span[2].innerHTML = ""
		}
		else {
			span[2].innerHTML = "Repass không giống Pass"
		}
		
		if (document.getElementById("c").value == document.getElementById("rc").value) {
			span[3].innerHTML = ""
		}
		else {
			span[3].innerHTML = "Cap không giống Recap"
		}
	}
}

function loadlai() {
	var r = Math.round(Math.random()*(99999-10000) + 10000)
	document.getElementById("c").value = r
}

var manganh = []
manganh[0] = "Images/phở.jpg"
manganh[1] = "Images/trà sữa.jpg"
manganh[2] = "Images/chân gà.jpg"
manganh[3] = "Images/táo.png"
manganh[4] = "Images/khoai.jpg"
var i = 0
var img = document.getElementsByTagName("img");

function autoload() {
	if (i == 4) {
		i = -1;
	}
	img[0].src = manganh[i+1]
	document.getElementById("tenanh").innerHTML = (i+2)+"/5"
	i++
}
var auto;

function sta() {
	auto = setInterval(autoload, 1500);
}

function sto() {
	clearInterval(auto)
}


