var img = [];
img[0] = "images/chân gà.jpg";
img[1] = "images/phở.jpg";
img[2] = "images/khoai.jpg"

var i = 0;
function next() {
	if (i == 2) {
		i = -1;
	}
	document.getElementsByTagName("img")[0].src = img[i+1];
	i++;
}

function pre() {
	if (i == 0) {
		i = 3;
	}
	document.getElementsByTagName('img')[0].src = img[i-1];
	i--;
}

function gia(ten) {
	switch (ten) {
		case "tao":
			return 50000;
			break;
		case "cam":
			return 55000;
			break;
		case "nho":
			return 60000;
			break;
	}
}

function tt(x, y) {
	document.getElementsByTagName("span")[0].innerHTML = "Tổng tiền = "+x*y;
}