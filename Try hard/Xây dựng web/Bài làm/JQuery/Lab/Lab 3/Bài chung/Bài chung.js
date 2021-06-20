$(document).ready(function() {
	$(".container").css("color", "yellowgreen");
	$(".container").eq(1).css("color", "red");
	$(".container").eq(2).css("color", "blue");
	$(".container").eq(3).css("color", "green");

	var x = $('.container');
	
	console.log($(x[3]).html()); // lấy nội dung trong class container ở ị trí thứ 3
	console.log($(x).html()); // tự động lấy nội dung trong class container ở vị trí 1
	
	console.log($(x).length); //công dụng như nhau
	console.log(x.length);
	
	console.log($(".container").eq(1).text()); // chả khác j .html
	
	
	
});


//for	(var i = 0; i < x; i++) {
//	value += $(".container").eq(i).text();
//}

