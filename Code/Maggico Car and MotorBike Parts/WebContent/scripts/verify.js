var password = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}$/;
var rusername = /^[a-zA-Z0-9]+([a-zA-Z0-9](_|-| )[a-zA-Z0-9])*[a-zA-Z0-9]+$/;
var email = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}$/;
var iban = /^IT\d{2}[a-zA-Z]\d{22}$/;
var descr = /^\s*[a-zA-Z0-9,\s\-\'\.]+\s*$/;
var price = /^\d*\.?\d*$/;
var qt = /^[0-9]{0,}$/;
var imglink = /([A-Za-z--9])\w+$/;


function checkPass(inputtxt){
	if(inputtxt.value.match(password)){
		return true;
	}
	console.log("no psw");
	return false;
}

function checkName(inputtxt){
	if (inputtxt.value.match(rusername)){
		return true;
	}
	console.log("no name");
	return false;
}

function checkEmail(inputtxt){
	if(inputtxt.value.match(email)){
		return true;
	}
	console.log("no email");
	return false;
}

function checkIban(inputtxt){
	if (inputtxt.value.match(iban)){
		return true;
	}
	console.log("no iban");
	return false;
}

//--------------------------------------------------REGEX PRODOTTI-----------------------------------------------------

function checkPname(inputtxt){
	if (inputtxt.value.match(descr)){
		return true;
	}
	console.log("no pname");
	return false;
}

function checkDescr(inputtxt){
	if (inputtxt.value.match(descr)){
		return true;
	}
	console.log("no descr");
	return false;
}

function checkPrice(inputtxt){
	if (inputtxt.value.match(price)){
		return true;
	}
	console.log("no price");
	return false;
}

function checkQt(inputtxt){
	if (inputtxt.value.match(qt)){
		return true;
	}
	console.log("no quantity");
	return false;
}

function checkImglink(inputtxt){
	if(inputtxt.value.match(imglink)){
		return true;
	}
	console.log("no imglink");
	return false;
}

//-----------------------------------------AJAX/VALIDATE CAMBIO PSW--------------------------------------------
var valid = true;


$(document).ready(function(){
	$('#vecchiapass').blur(function(event) {
		var pass = $('#vecchiapass').val();
		$.post('VerifyServlet', {
			oldPass : pass, 
			action: 'password'
		}, function(responseText) {
			if(responseText == "false")
			{
				$('#invalidPass').text("La password non coincide con quella attualmente settata!");
				$('#invalidPass').css("color", "red");
				valid = false;
			}
			else
			{
				$('#invalidPass').text("");
				valid = true;
			}
		});
	});
});





function validateChangePsw(obj){
	console.log("start validation");

	var vpass = document.getElementsByName("vecchiapass")[0];
	if(!checkPass(vpass)){
		console.log(vpass);
		valid = false;
		document.getElementsByName("vecchiapass")[0].focus();

	} 

	var npass = document.getElementsByName("nuovapass")[0];
	if(!checkPass(npass)){
		valid = false;
		document.getElementsByName("nuovapass")[0].focus();
		alert("Nuova pass non valida");
	} 

	var cpass = document.getElementsByName("confermapass")[0];
	if(!checkPass(cpass)){
		valid = false;
		document.getElementsByName("confermapass")[0].focus();
		alert("Conferma non valida");
	} 



	console.log("end validation");


	if(valid) {obj.submit();}
}
//--------------------------------------------------AJAX/VALIDATE REGISTRAZIONE---------------------------------
var verifyUser = true;

$(document).ready(function(){	
	$('#rusername').blur(function(event){

		var username = $('#rusername').val();
		$.post('VerifyServlet', {
			olduser : username,
			action : 'userCheck'
		}, function(responseText){
			if(responseText == "false")
			{
				$('#invalidUser').text("L'username non è disponibile! ");
				$('#invalidUser').css("color", "red");
				verifyUser = false;
			}
			else
			{
				$('#invalidUser').text("");
				verifyUser = true;
			}
			
		});
	});
});

function validate(obj){
	
	console.log("ciao")
	
	var rusername = document.getElementsByName("rusername")[0];
	if(!checkName(rusername)){
		verifyUser = false;
		document.getElementsByName("rusername")[0].focus();
		alert("Username non valido, usare solo caratteri")
	} 
	
	var email = document.getElementsByName("email")[0];
	if(!checkEmail(email)){
		console.log("secondo if")
		verifyUser  = false;
		document.getElementsByName("email")[0].focus;
		alert("Email non valida, rispettare il formato 'email@email.it/com'");
	} 

	var rpassword = document.getElementsByName("rpassword")[0];
	if(!checkPass(rpassword)){
		console.log("terzo if")
		verifyUser  = false;
		document.getElementsByName("rpassword")[0].focus();
		alert("Password non valida, deve contenere almeno 8 caratteri, una lettera maiuscola una minuscola e un numero");
	}
	
	var iban = document.getElementsByName("iban")[0];
	if(!checkIban(iban)){
		verifyUser  = false;
		document.getElementsByName("iban")[0].focus();
		alert("Iban non valido, rispettare il formato IT XX X XXXXXXXXXXXXXXXXXXXXXX");
	} 
	
	console.log("finish")
	if(verifyUser) {
		obj.submit(); 
	} 
	
}

//-------validate admin

function validateA(obj){
	
	console.log("ciao")
	
	var rusername = document.getElementsByName("rusername")[0];
	if(!checkName(rusername)){
		verifyUser = false;
		document.getElementsByName("rusername")[0].focus();
		alert("Username non valido, usare solo caratteri")
	} 
	
	var email = document.getElementsByName("email")[0];
	if(!checkEmail(email)){
		console.log("secondo if")
		verifyUser  = false;
		document.getElementsByName("email")[0].focus;
		alert("Email non valida, rispettare il formato 'email@email.it/com'");
	} 

	var rpassword = document.getElementsByName("rpassword")[0];
	if(!checkPass(rpassword)){
		console.log("terzo if")
		verifyUser  = false;
		document.getElementsByName("rpassword")[0].focus();
		alert("Password non valida, deve contenere almeno 8 caratteri, una lettera maiuscola una minuscola e un numero");
	} 
	
	console.log("finish")
	if(verifyUser) {
		obj.submit(); 
	} 
	
}


//-------------------------topheader------------------------
function validateLogin(obj)
{
	var valid = true;
	var username = document.getElementsByName("username")[0];
	if( !checkName(username) )
	{
		valid = false;
		document.getElementsByName("username")[0].focus();
		alert("I campi non sono compilati correttamente. Usare solo Lettere, numeri e - name");
	} 
	
	
	if(valid) {obj.submit();}
}



//----------------Validate Add-----------------------


function validateAdd(obj){
	var valid = true;
	console.log("start validation");
	
	var insname = document.getElementsByName("insname")[0];
	if(!checkPname(insname)){
		valid = false;
		document.getElementsByName("insname")[0].focus();
		alert("Nome non valido");
	} 
	
	var insdescription = document.getElementsByName("insdescription")[0];
	if(!checkDescr(insdescription)){
		valid = false;
		document.getElementsByName("insdescription")[0].focus(); 
		alert("Descrizione non valida");
	} 
	
	var insprice = document.getElementsByName("insprice")[0];
	if(!checkPrice(insprice)){
		valid = false;
		document.getElementsByName("insprice")[0].focus();
		alert("Prezzo non valido");
	} 
	
	var insquantity = document.getElementsByName("insquantity")[0];
	if(!checkQt(insquantity)){
		valid = false;
		document.getElementsByName("insquantity")[0].focus();
		alert("Quantità non valida");
	} 
	
	var insimglink = document.getElementsByName("insimglink")[0];
	if(!checkImglink(insimglink)){
		valid = false;
		document.getElementsByName("insimglink")[0].focus();
		alert("Link non valido");
		
	} 
	
	console.log("end validation");
	
	
	if(valid) {obj.submit();}
}

//-----------------------------------------------------VALIDATE UPDATE PRODOTTI----------------------------------


function validateUpdate(obj){
	var valid = true;
	
	var pname = document.getElementsByName("pname")[0];
	if(!checkPname(pname)){
		valid = false;
		document.getElementsByName("pname")[0].focus();
		alert("Nome non valido");
	}
	
	var pdescription = document.getElementsByName("pdescription")[0];
	if(!checkDescr(pdescription)){
		valid = false;
		document.getElementsByName("pdescription")[0].focus();
		alert("Descrizione non valida");
	} 
	
	var pprice = document.getElementsByName("pprice")[0];
	if(!checkPrice(pprice)){
		valid = false;
		document.getElementsByName("pprice")[0].focus();
		alert("Prezzo non valido");
	} 
	
	var pquantity = document.getElementsByName("pquantity")[0];
	if(!checkQt(pquantity)){
		valid = false;
		document.getElementsByName("pquantity")[0].focus();
		alert("Quantità non valida");
	} 
	
	var pimglink = document.getElementsByName("pimglink")[0];
	if(!checkImglink(pimglink)){
		valid = false;
		document.getElementsByName("pimglink")[0].focus();
		alert("Link non valido");
		
	}
	
	
	if(valid) {obj.submit();}
}