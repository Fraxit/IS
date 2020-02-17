package test.testServlet;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import control.carrello.AddProdCarrelloServlet;
import bean.CartBean;
import bean.ProductBean;

public class AddProdCarrServletTest extends Mockito{

	private HttpServletRequest request;
	private HttpServletResponse response;
	private ProductBean prod;
	private ProductBean prod1;
	private CartBean cart;
	
	@Before
	public void setUp()
	{
		request = mock(HttpServletRequest.class);       
		response = mock(HttpServletResponse.class);
		cart = new CartBean();
		prod = new ProductBean();
		prod.setId(1);
		prod1 = new ProductBean();
		prod1.setId(2);
		
	}

	@Test
	public void testIfServlet() throws Exception
	{
		when(request.getSession()).thenReturn(mock(HttpSession.class));

		when(request.getSession().getAttribute("cart")).thenReturn(cart);
		when(request.getSession().getAttribute("bean")).thenReturn(prod);

		
		when(request.getParameter("qtcart")).thenReturn("10");

		StringWriter stringWriter = new StringWriter();
		PrintWriter writer = new PrintWriter(stringWriter);
		when(response.getWriter()).thenReturn(writer);

		new AddProdCarrelloServlet().doPost(request, response);
		new AddProdCarrelloServlet().doGet(request, response);

		writer.flush();
	}

	
	@Test
	public void testElseServlet() throws Exception
	{
		
		cart.addItem(prod);
		when(request.getSession()).thenReturn(mock(HttpSession.class));

		when(request.getSession().getAttribute("cart")).thenReturn(cart);
		when(request.getSession().getAttribute("bean")).thenReturn(prod);

		
		when(request.getParameter("qtcart")).thenReturn("10");

		StringWriter stringWriter = new StringWriter();
		PrintWriter writer = new PrintWriter(stringWriter);
		when(response.getWriter()).thenReturn(writer);

		new AddProdCarrelloServlet().doPost(request, response);
		new AddProdCarrelloServlet().doGet(request, response);

		writer.flush();
	}
	
	@Test
	public void testElseElseServlet() throws Exception
	{
		cart = new CartBean();
		cart.addItem(prod1);
		when(request.getSession()).thenReturn(mock(HttpSession.class));

		when(request.getSession().getAttribute("cart")).thenReturn(cart);
		when(request.getSession().getAttribute("bean")).thenReturn(prod);

		when(request.getParameter("qtcart")).thenReturn("10");

		StringWriter stringWriter = new StringWriter();
		PrintWriter writer = new PrintWriter(stringWriter);
		when(response.getWriter()).thenReturn(writer);

		new AddProdCarrelloServlet().doPost(request, response);
		new AddProdCarrelloServlet().doGet(request, response);

		writer.flush();
	}

}
