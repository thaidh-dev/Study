// tạo đối tượng trong javascript bà jquery
//var emp = {
//	name: "Zara",
//	age: 10
//}
//
//// tạo mảng
//var x = [];
//
//// tạo hàm
//function a(x) {
//	console.log(x);
//}
//
//a(7);

$(document).ready(function() {
	var title = $("em").attr("title");
	$("#divid").text(title);
});

$(document).ready(function() {
	$("p").css("background-color", "yellow");
});

$(document).ready(function() {
	$("li").eq(2).css("color", "red");
	$("li").eq(0).css({"color": "red", "font-family": "tahoma", "background-color": "green"});
});
