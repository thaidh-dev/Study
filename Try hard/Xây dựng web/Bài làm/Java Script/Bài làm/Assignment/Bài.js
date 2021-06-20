function chon(a) {
	switch (a) {
		case "1":
			return "170.000 đ";
			break;
		case "2":
			return "80.000 đ";
			break;
		case "3":
			return "240.000 đ";
			break;
		case "4":
			return "60.000 đ";
			break;
		case "5":
			return "75.000 đ";
			break;
	}
}

var manganh = [];
manganh[0] = "images//b.jpg";
manganh[1] = "images//h.jpg";
manganh[2] = "images//o.jpg";

var radiobutton = document.getElementsByName("gt");
function chuyenanh() {
	for (var i = 0; i < radiobutton.length; i++) {
		if (radiobutton[i].checked == true) {
			document.getElementById("avatar").src = manganh[i];
		}
	}
}

var x = 0;
function next() {
	if (x == 2) {
		x = -1;
	}
	
	document.getElementById('avatar').src = manganh[x+1];
	x++;
}

setInterval('next()', 1000);