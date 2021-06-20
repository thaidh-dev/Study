// JavaScript Document
//$(function () {
//	$("button").click(function() {
//		$("p").hide();
//	})
//})
//
//$(function () {
//	$("button").click(function() {
//		$(".demo img").hide(2000);// delay 2 s
//	})
//})

$(function() {
	$("#an").click(function() {
		$(".demo img").hide(2000);
	})
	
	$("#hien").click(function() {
		$(".demo img").show(2000);
	})
	
	$("#anhien").click(function() {
		$(".demo img").toggle(2000);
	})
})