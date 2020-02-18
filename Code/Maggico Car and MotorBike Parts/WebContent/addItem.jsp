<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<jsp:include page="topheader.jsp"></jsp:include>
<%
if(request.getSession().getAttribute("user") != null)
	if((boolean)request.getSession().getAttribute("isAdmin")){
	%>
<div class="containerBox">
	<form action="AggiungiProdCatalogoServlet" method="post" onsubmit="event.preventDefault(); validateAdd(this)">

		<label for="insname">Nome</label><br> 
		<input name="insname" type="text" maxlength="100" required placeholder="Inserire nome"><br>

		<label for="insdescription">Descrizione</label><br>
		<textarea name="insdescription" type="text" maxlength="100" rows="3" required placeholder="Inserire descrizione"></textarea><br> 
		
		<label for="insprice">Prezzo</label><br> 
		<input name="insprice" step="any" type="number" min="1" required placeholder="Inserire prezzo"><br> 
		
		<label for="insquantity">Quantità</label><br>
		<input name="insquantity" type="number" min="1" required placeholder="Inserire quantità disponibile"><br> 
		
		<label for="insimglink">Immagine </label><br> 
		<input type="text" name="insimglink" placeholder="Inserire link immagine" value="images/errori/notfound.png"> 
		
		<input type="submit" value="Inserisci">


	</form>
</div>
<%
	} else {
%>
<jsp:include page="exception.jsp"></jsp:include>
<%
	}
	else {
%>
<jsp:include page="exception.jsp"></jsp:include>
<%
	}
%>
<br>
<br>
<br>
<br>
<br>
<br>

<jsp:include page="footer.jsp"></jsp:include>
</html>