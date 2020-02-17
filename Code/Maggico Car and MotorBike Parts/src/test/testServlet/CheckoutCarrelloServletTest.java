package test.testServlet;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import control.carrello.CheckoutCarrelloServlet;
import model.ProductManager;
import bean.CartBean;
import bean.ProductBean;

public class CheckoutCarrelloServletTest extends Mockito{

	private HttpServletRequest request;
	private HttpServletResponse response;
	private ProductBean prod;
	private CartBean cart;
	private CartBean tmpCart;
	ProductManager model;
	
	
	@Before
	public void setUp() throws SQLException
	{
		request = mock(HttpServletRequest.class);       
		response = mock(HttpServletResponse.class);
		model = new ProductManager();
		cart = new CartBean();
		tmpCart = new CartBean();
		prod = new ProductBean();
		prod.setNome("oggetto");
		prod.setDescr("qejkd");
		prod.setImglink("fdbgf");
		prod.setPrezzo(13);
		prod.setQtprod(100);
		cart.addItem(prod);
	}

	@Test
	public void testServlet() throws Exception
	{
		when(request.getSession()).thenReturn(mock(HttpSession.class));

		when(request.getSession().getAttribute("cart")).thenReturn(cart);

		StringWriter stringWriter = new StringWriter();
		PrintWriter writer = new PrintWriter(stringWriter);
		when(response.getWriter()).thenReturn(writer);

		new CheckoutCarrelloServlet().doPost(request, response);
		new CheckoutCarrelloServlet().doGet(request, response);

		writer.flush();
	}

	@Test
	public void testEmptyCartServlet() throws Exception
	{
		when(request.getSession()).thenReturn(mock(HttpSession.class));

		when(request.getSession().getAttribute("cart")).thenReturn(tmpCart);

		StringWriter stringWriter = new StringWriter();
		PrintWriter writer = new PrintWriter(stringWriter);
		when(response.getWriter()).thenReturn(writer);

		new CheckoutCarrelloServlet().doPost(request, response);
		new CheckoutCarrelloServlet().doGet(request, response);

		writer.flush();
	}

}
