
var txtUsername = document.getElementById("username");
var txtPassword = document.getElementById("password");
var chkRemember = document.getElementById("rememberMe");
var btnLogin = document.getElementById("login");
var txtSearch = document.getElementById("searchbox");

//btnSubmit.addEventListener("click",submit);
//studentID.addEventListener("blur",isNumeric);
document.getElementById("btnSearch").addEventListener("click", retreiver);


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

}