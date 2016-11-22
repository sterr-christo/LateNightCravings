var txtUsername = document.getElementById("username");
var txtPassword = document.getElementById("password");
var chkRemember = document.getElementById("rememberMe");
var btnLogin = document.getElementById("login");
var txtSearch = document.getElementById("searchbox");
var btnLoc = document.getElementById("btnLoc");

//btnSubmit.addEventListener("click",submit);
//studentID.addEventListener("blur",isNumeric);
document.getElementById("btnSearch").addEventListener("click", retreiver);
btnLoc.addEventListener("Click", getLocation());

function isNumeric(elem){
	var numericExpression = /^[0-9]+$/;
	if(elem.value.match(numericExpression)){
		return true;
	}else{
	    elme.value = '';
		elem.focus();
		return false;
	}
}

function restrictLength(element,max_chars) {
    if(element.value.length > max_chars) {
        element.value = element.value.substr(0,max_chars);
    }
}

function retreiver() {
	txtSearch.value = "asdf";
}
function getLocation() {
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(showPosition);
    } else {
    	alert("Geolocation is not supported by this browser.");
    }
}
function showPosition(position) {
	txtSearch.value = position.coords.latitude +
    ", " + position.coords.longitude;
}