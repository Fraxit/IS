<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.*,bean.ProductBean"%>


<%
	ProductBean bean = (ProductBean) request.getSession().getAttribute("bean");
%>


<jsp:include page="topheader.jsp"></jsp:include>


<div id="wrap">
	<div id="product_layout_1">
		<div class="top">
			<div class="product_images">
				<div class="product_image_1">
					<img src="<%=bean.getImglink()%>">
				</div>
			</div>
			<div class="product_info">
				<%
					if (request.getSession().getAttribute("isAdmin") != null
							&& ((boolean) request.getSession().getAttribute("isAdmin"))) {
				%>

				<form id="updateprod"
					action="AggiornaProdServlet?action=update&id=<%=bean.getId()%>" method="post" onsubmit="event.preventDefault(); validateUpdate(this)">

					<label for="pname">Nome</label><br> 
					<input name="pname"	type="text" required placeholder="enter name"
					value="<%=bean.getNome()%>"><br> 
					
					<label for="pdescription">Descrizione</label><br>
					<textarea name="pdescription" type="text" rows="3" maxlength="100" required><%=bean.getDescr()%></textarea>
					<br> 
					
					<label for="pprice">Prezzo</label><br> 
					<input name="pprice" type="number" step="any" min="1" value="<%=bean.getPrezzo()%>" required value="<%=bean.getPrezzo()%>""><br> 
					
					<label for="pquantity">Quantità</label><br> 
					<input name="pquantity" type="number" min="1" value="<%=bean.getQtprod()%>" required value="<%=bean.getQtprod()%>""><br> 
					
					<label for="pimglink">Immagine:</label><br> 
					<input name="pimglink" type="text" value="<%=bean.getImglink()%>" required value="<%=bean.getImglink()%>""><br> 
					
					<input id="submitBttn" type="submit" value="Update">

				</form>


				<form id="deleteprod" action="RimuoviProdottoCatalogoServlet?action=delete&id=<%=bean.getId()%>" method="post" onsubmit="event.preventDefault(); conferma(this)">

					<input id="deleteProdBttn" type="submit" value="Cancella prodotto">
				</form>
				<%
					} else {
				%>
				<h1><%=bean.getNome()%></h1>
				<div class="price">
					<h2 class="sale_price">
						&euro;<%=bean.getPrezzo()%></h2>
				</div>
				<div class="product_description">
					<p><%=bean.getDescr()%>
				</div>
				<div class="related_info">
					<span class="sku">ID: <%=bean.getId()%></span><span
						class="quantity">Quantità disponibile: <%=bean.getQtprod()%></span> 
				</div>
				<div class="options">
					<div class="buying">
						<form <%if (request.getSession().getAttribute("user") != null) {%>
							action="AddProdCarrelloServlet" method="post" <%}%>>
							<%
								request.getSession().setAttribute("bean", bean);
							%>
							<%
								if (request.getSession().getAttribute("user") != null) {
							%>
							<div class="quantity">
								<%
									if (bean.getQtprod() > 0) {
								%>
								<label for="quantity">QTY:</label> <input type="number" min="1"
									max="<%=bean.getQtprod()%>" name="qtcart" value="1"
									onkeydown="return false">
								<%
									} else {
								%>
								<label for="empty" style="color: red;">Prodotto non
									disponibile</label>
								<%
									}
								%>

							</div>
							<%
								}
							%>
							<div class="cart">
								<%
									if (request.getSession().getAttribute("user") != null) {
										if (bean.getQtprod() > 0) {
								%>
								<button type="submit">
									Add to Cart <i class="fa fa-shopping-cart fa-lg"></i>
								</button>
								<%
									}
								%>
								<%
									} else {
								%>
								<label id="labelLog">Per acquistare devi loggarti!</label>
								<%
									}
								%>
							</div>
						</v>
					</div>
				</div>
				<%
					}
				%>
			</div>

		</div>
	</div>
</div>

<script>

function conferma(obj){
	var ok = confirm("Vuoi davvero cancellare il prodotto dal sito? (Quest'azione è irreversibile)");
	if (ok==true){
		obj.submit();
	} 
}



</script>

<jsp:include page="footer.jsp"></jsp:include>

</html>
