 /* 69 */


var formElement;
var songNo = 1;

function addInput() {
	var inputElement = $("<input>");
	++songNo;
	inputElement.attr("type","text");
	inputElement.attr("name","songName"+songNo);
	formElement.append(inputElement);
	var lineBreak = $("<br>");
	formElement.append(lineBreak);
}

function sendAlbum1() {
	var jsonObj = $("#registerForm").serializeArray();
	console.log("json"+JSON.stringify(jsonObj));
	$.ajax({
		url: "api/registerList",
		type: "post",
		dataType: "json",
		contentType: "application/json",
		scriptCharset: "utf-8",
		data: JSON.stringify(jsonObj)
	}).done(function(respData) {
		alert("Server request"+ respData["status"]);
	}).fail(function(jqXhrObj) {
		alert("Error"+jqXhrObj.responseText);
	});
}

function sendAlbum2() {
	var jsonObj = {};
	jsonObj["artistName"] = $("input[name='artistName']").val;
	jsonObj["albumName"] = $("input[name='albumName']").val;
	jsonObj["songList"] = [];
	
	$("input[name^=songName]").each(function(index,element) {
		var keyVal = {};
		keyVal[element.name] = element.value;
		jsonObj["songList"].push(keyVal);
	});
	console.log("json: "+JSON.stringify(jsonObj));
	
	$.ajax({
		url: "api/registerStruct",
		type: "post",
		ContentType: "application/json",
		dataType: "json",
		scriptCharset: "utf-8",
		data: JSON.stringify(jsonObj)
	}).done(function(respData) {
		alert("Server response"+ respData["status"]);
	}).fail(function(jqXhrObj) {
		alert("Error"+jqXhrObj.responseText);
	});
}

$(function(){
	formElement = $("#registerForm");
});