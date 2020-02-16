<%@page import="bean.ClientBean"%>
<%@page import="model.CarrelloManager"%>
<%@page import="bean.CartBean"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.*,bean.ProductBean"%>


<jsp:include page="topheader.jsp"></jsp:include>


<%
	ClientBean clientbean = (ClientBean) request.getSession().getAttribute("clientbean");
	CartBean cart = (CartBean) request.getSession().getAttribute("cart");
	
	if(request.getSession().getAttribute("isAdmin") == null || (boolean)request.getSession().getAttribute("isAdmin") == true){
		//response.setStatus(401);
		

		
	%>
<div class="containerBox">
	<h2>Impossibile accedere all'area.</h2>
</div>
<%} else { %>


<div class="megacart">
	<div class="shopping-cart">

		<div class="column-labels">
			<label class="prodImage">Img</label> <label class="prodDetails">Prodotto</label>
			<label class="prodPrice">Prezzo</label> <label class="prodQuantity">Quantità</label>
			<label class="prodRemoval">Remove</label> <label
				class="product-line-price">Totale prod.</label>
		</div>
		<%
		double tot = 0;
		if( cart.getItems().size() > 0){
			for(ProductBean prod : cart.getItems())
			{
	%>
		<div class="produ">
			<div class="prodImage">
				<img src="<%= prod.getImglink() %>">
			</div>
			<div class="prodDetails">
				<div class="prodTitle"><%= prod.getNome() %></div>
				<p class="prodDescription"><%= prod.getDescr() %></p>
			</div>
			<div class="prodPrice"><%= prod.getPrezzo() %></div>
			<div class="prodQuantity">
				<%if(prod.getQtprod() != -1){ %>
				<label><%= prod.getQtprod() %></label>
				<%}else{%>
				<label>Quantità scelta non disponibile!</label>
				<%} %>
			</div>
			<div class="prodRemoval">

				<form action="RimuoviProdCarrelloServlet?action=remove&id=<%=prod.getId()%>" method="post"
					class="remove-product">
					<input type="submit" class="remove-product" value="Rimuovi">
				</form>
			</div>
			<%
	    	double price = (prod.getPrezzo() * prod.getQtprod()); 
	    %>
			<div class="product-line-price"><%= price %></div>
		</div>
		<% 
		tot = tot + prod.getPrezzo()*prod.getQtprod() ;
			}
		}  
	%>


		<div class="totals">
			<div class="totals-item totals-item-total">
				<label>Totale</label>
				<div class="totals-value" id="cart-total"><%= tot %></div>
			</div>
		</div>

		<form action="CheckoutCarrelloServlet" method="post" class="checkout">
			<input type="submit" value="Checkout" class="checkout">
		</form>

	</div>
</div>
<br>
<br>

<% } %>
<jsp:include page="footer.jsp"></jsp:include>

</html>