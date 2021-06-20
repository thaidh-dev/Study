$(function() {
	$(".btnKinhLup").click(function() {
		$(".hidden").slideToggle()
	});
});

$(function() {
	var banner = [];
	banner[0] = "Hình//banner1.png";
	banner[1] = "Hình//banner2.png";
	banner[2] = "Hình//banner3.png";
	
	$(".btnBanner").each(function(index) {
		$(this).click(function() {
			$("#bannerImg").fadeOut(500)
			setTimeout(function() {
				$("#bannerImg").attr("src", banner[index])
			}, 500);
			$("#bannerImg").fadeIn(500);
		})
	})
});