<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<jsp:include page="topheader.jsp"></jsp:include>

<div class="menu">
	<ul id="menuriga" style="padding: 0">
		<li><div id="prod" onclick="location.href='prodotti.jsp';">
				<h1>VISITA IL NOSTRO CATALOGO</h1>
			</div></li>
		<li><div id="contatti" onclick="location.href='contatti.jsp';">
				<h1>CONTATTACI</h1>
			</div></li>
		<%if (request.getSession().getAttribute("user") == null) {%>
		<li><div id="reg" onclick="location.href='registrazione.jsp';">
				<h1>SCOPRI I VANTAGGI DI AVERE UN ACCOUNT</h1>
			</div></li>
		<%}%>
	</ul>
</div>
<jsp:include page="footer.jsp"></jsp:include>
</html>