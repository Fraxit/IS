package test.testServlet;


import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.mockito.Mockito;

import control.prodotti.RicercaProdottoServlet;
import model.ProductManager;
import bean.ProductBean;

import org.junit.Before;
import org.junit.Test;


public class RicercaProdottoServletTest extends Mockito {

	private HttpServletRequest request;
	private HttpServletResponse response;
	ProductBean prod;
	Collection<ProductBean> products;
	ProductManager model;
	
	@Before
	public void setUp() throws SQLException
	{
		request = mock(HttpServletRequest.class);       
		response = mock(HttpServletResponse.class);
		products = new ArrayList<ProductBean>();
		prod = new ProductBean();
		prod.setNome("Oggetto");
		products.add(prod);
	}
	
	@Test
	public void testServlet() throws Exception
	{		
		when(request.getSession()).thenReturn(mock(HttpSession.class));
		
		when(request.getParameter("search")).thenReturn("kit");
		when(request.getSession().getAttribute("products")).thenReturn(products);


		
		StringWriter stringWriter = new StringWriter();
		PrintWriter writer = new PrintWriter(stringWriter);
		when(response.getWriter()).thenReturn(writer);

		new RicercaProdottoServlet().doPost(request, response);
		new RicercaProdottoServlet().doGet(request, response);

		writer.flush();
	}
	
	@Test
	public void testSearchNullServlet() throws Exception
	{		
		when(request.getSession()).thenReturn(mock(HttpSession.class));
		
		when(request.getParameter("search")).thenReturn(null);


		
		StringWriter stringWriter = new StringWriter();
		PrintWriter writer = new PrintWriter(stringWriter);
		when(response.getWriter()).thenReturn(writer);

		new RicercaProdottoServlet().doPost(request, response);
		new RicercaProdottoServlet().doGet(request, response);

		writer.flush();
	}
	
	
}
