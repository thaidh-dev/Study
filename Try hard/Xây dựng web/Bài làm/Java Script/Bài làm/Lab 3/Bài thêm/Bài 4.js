// JavaScript Document
var ab = [];
ab[0] = "Images//a.jpg";
ab[1] = "Images//gà.jpg";
ab[2] = "Images//chân gà.jpg"

var i = 0;
function next() {
	if (i == ab.length - 1) {
		i = -1;
	}
	document.getElementById("anh").src = ab[i+1];
	i++;
}

function pre() {
	if (i == 0) {
		i = ab.length;
	}
	document.getElementById("anh").src = ab[i-1];
	i--;
}
