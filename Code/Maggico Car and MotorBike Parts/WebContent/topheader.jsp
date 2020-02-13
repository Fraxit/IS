<%@page import="bean.UserBean"%>
<%@page import="model.CarrelloManager"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE HTML>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Maggico Car &amp; Motorbike Parts</title>

<meta name="description" content="Layout template">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0 user-scalable=0">
<meta name="author"
	content="Raffaele Coscione, Francesco Carotenuto, Vincenzo Tortora, Giovanni Renzulli">


<link type="text/css" rel="stylesheet" href="style.css">
<link
	href="//netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css" rel="stylesheet">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="scripts/verify.js"></script>
</head>
<body>

	<a class="logo" href="index.jsp"><img src="images/logo/Logo5.png" alt="logo"></a>

	<%
		if (request.getSession().getAttribute("user") == null) {
	%>
	<form class="formlogin" action="LoginServlet" method="post" onsubmit="event.preventDefault(); validateLogin(this)">
		<%
			if (request.getSession().getAttribute("loginerror") != null)
					if ((boolean) request.getSession().getAttribute("loginerror")) {
		%>
		<label id="errorLabel">Username o password errati</label>
		<%
		
			request.getSession().setAttribute("loginerror", false);
			}
		%>
		<label for="usr"><b>Username</b></label>
		<input type="text" name="username" value="" placeholder="Username" required> 
		<label for="psw"><b>Password</b></label>
		<input type="password" name="password" value="" placeholder="Password" required>

		<button class="logbutton" type="submit">Login</button>
	</form>
	<script>
	$(document).ready(function(){
		$("#errorLabel").delay(5000).hide(0);
		});
	</script>
	<%
		} else {
	%>
	<div id="welcomeDiv">
		<h1 id="msgBenvenuto">
			Benvenuto,
			<%
			UserBean user = (UserBean) request.getSession().getAttribute("user");
				out.print(user.getUsername());
			%>
			<div class="dropdown">
				<button class="dropbtn"></button>
				<div class="dropdown-content">
					<a href="cambioPassword.jsp">Cambia password</a>

					<%
						if ((request.getSession().getAttribute("isAdmin") != null))
								if ((boolean) request.getSession().getAttribute("isAdmin") == false) {
					%>
					<a href="carrello.jsp">Carrello</a>

					<%
						} else {
							%>
							<a href="registrazione.jsp">Crea un admin</a>
							<%
							
						}
					%>
					<form action="LogoutServlet" method="post">
					<button id="logoutBttn" type="submit">Logout</button>
					</form>
				</div>
			</div>
		</h1>

	</div>
	<%
		}
	%>
	<ul class="topnav">
		<li><a href="index.jsp">Home</a></li>
		<li><a href="prodotti.jsp">Prodotti</a></li>
		<li><a href="contatti.jsp">Contattaci</a></li>
	</ul>