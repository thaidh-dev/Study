$(function() { 
	$("input").checkboxradio(); 
});


// datepicker
$(function() {
	$("#datepicker-8").datepicker({
		prevText: "click for previous months",
		nextText: "click for next months",
		showOtherMonths: true,
		selectOtherMonths: false
	});
	$("#datepicker-9").datepicker({
		prevText: "click for previous months",
		nextText: "click for next months",
		showOtherMonths: true,
		selectOtherMonths: true
	});
});

// spinner
$(function() {
	var spinner = $("#spinner").spinner();
	
});