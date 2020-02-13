<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.*,bean.ProductBean"%>


<%
Collection<?> products;
if(request.getSession().getAttribute("searchProducts") != null)
{
	products = (Collection<?>) request.getSession().getAttribute("searchProducts");
	request.getSession().removeAttribute("searchProducts");
}
else
{
	products = (Collection<?>) request.getSession().getAttribute("products"); 
}
	if (products == null) {
		response.sendRedirect("./VisualizzaProdottiServlet");
	}

%>


<jsp:include page="topheader.jsp"></jsp:include>

<div class="containerBox">
	<form action="RicercaProdottoServlet" method="post">
		<input id="searchBarProd" name="search" placeholder="Search...">
		<button id="searchBttn" type="submit">Cerca</button>
	</form>
	<%
	if(request.getSession().getAttribute("stringSearch") != null)
		if(request.getSession().getAttribute("nProds") != null)
			if(   products.size() < (int) request.getSession().getAttribute("nProds") )
			{ 
	%>
			<label>Ci sono <%=products.size() %> risultati per "<%=(String) request.getSession().getAttribute("stringSearch") %>"</label>
	<%
			}
			else
			{
	%>
				<label>Non ci sono risultati per "<%=(String) request.getSession().getAttribute("stringSearch")%>"</label>
	<%
			}
		
		request.getSession().removeAttribute("stringSearch");
	%>
		
</div>

<div class="wrapper">
	<div class="products category">
		<ul class="">
			<%
				if (request.getSession().getAttribute("isAdmin") != null)
					if (((boolean) request.getSession().getAttribute("isAdmin"))) {
			%>
			<button id="addProdBttn" onclick="location.href='addItem.jsp';">Aggiungi Prodotto</button>
			<br>
			<br>
			<br>
			<%
				}
				if (products != null && products.size() > 0) {
					Iterator<?> it = products.iterator();
					while (it.hasNext()) {
						ProductBean bean = (ProductBean) it.next();
			%>
			<li>
				<div class="wrapimg">
					<img src="<%=bean.getImglink()%>">
					<div class="addPro">
						<a href="VisualizzaProdCatalogoServlet?id=<%=bean.getId()%>">+ Show item</a>
					</div>
				</div>
				<div class="titlePro">
					<a href="VisualizzaProdCatalogoServlet?id=<%=bean.getId()%>"><%=bean.getNome()%></a> <span>&euro;
						<%=bean.getPrezzo()%></span>
					<p class="descTitle">Detailed item information</p>
					<p><%=bean.getDescr()%></p>
				</div>
			</li>
			<%
				}
			}
			%>
		</ul>
	</div>
</div>

<jsp:include page="footer.jsp"></jsp:include>

</html>