function tggleSidebar(){
	document.getElementById("sidebar").classList.toggle('active');
}
$(function(){
	$(document).on("click", ".disabled a, .active a", function(){
		return false;
	});
});