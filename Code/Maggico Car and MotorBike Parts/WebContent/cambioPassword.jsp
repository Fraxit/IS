<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="bean.ClientBean,bean.AdminBean,bean.UserBean, javax.servlet.http.HttpSession"%>

<jsp:include page="topheader.jsp"></jsp:include>




<%
	if (request.getSession().getAttribute("user") == null) {
%>
<div class="containerBox">
	<h2>Impossibile accedere all'area. Effettuare il login</h2>
</div>
<%
	} else {

		if (request.getAttribute("errore") != null) {
%>

<h1>password errata</h1>

<%
	}
%>

<div class="clientPage">
	<form id="cambiapsw" action="CambioPasswordServlet?action=password"
		method="post" onsubmit="event.preventDefault(); validateChangePsw(this)">
		<h2>Reimposta Password</h2>

		<label for="vecchiapass">Inserisci la vecchia password</label><br>
		<input id="vecchiapass" name="vecchiapass" type="password" required
			placeholder="Inserire la vecchia password"> <label
			id="invalidPass"></label><br> <label for="nuovapass">Inserisci
			la nuova password</label><br> <input name="nuovapass" type="password"
			
			title="La password deve essere di almeno 8 caratteri, deve contenere almeno un numero, una lettera maiuscola ed una minuscola"
			required placeholder="Inserire la nuova password"><br> <label
			for="confermapass">Conferma la password</label><br> <input
			name="confermapass" type="password"
			
			title="La password deve essere di almeno 8 caratteri, deve contenere almeno un numero, una lettera maiuscola ed una minuscola"
			required placeholder="Conferma password"><br> <input
			type="submit">

	</form>

</div>

<%
	}
%>




<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<jsp:include page="footer.jsp"></jsp:include>

</html>