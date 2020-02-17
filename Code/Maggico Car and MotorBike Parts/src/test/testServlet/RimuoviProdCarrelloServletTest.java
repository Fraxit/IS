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

import control.carrello.AddProdCarrelloServlet;
import control.carrello.RimuoviProdCarrelloServlet;
import model.ProductManager;
import bean.CartBean;
import bean.ProductBean;

public class RimuoviProdCarrelloServletTest extends Mockito{

	private HttpServletRequest request;
	private HttpServletResponse response;
	private ProductBean prod;
	private CartBean cart;
	ProductManager model;
	private Integer id;
	
	
	@Before
	public void setUp() throws SQLException
	{
		request = mock(HttpServletRequest.class);       
		response = mock(HttpServletResponse.class);
		model = new ProductManager();
		cart = new CartBean();
		prod = new ProductBean();
		prod.setNome("oggetto");
		prod.setDescr("qejkd");
		prod.setImglink("fdbgf");
		prod.setPrezzo(13);
		prod.setQtprod(100);
		
		model.doSave(prod);
		id = model.doRetrieveByProduct(prod.getNome()).getId();
		cart.addItem(prod);
	}

	@Test
	public void testServlet() throws Exception
	{
		when(request.getSession()).thenReturn(mock(HttpSession.class));

		when(request.getParameter("action")).thenReturn("remove");
		when(request.getParameter("id")).thenReturn(id.toString());

		when(request.getSession().getAttribute("cart")).thenReturn(cart);

		
		when(request.getParameter("action")).thenReturn("remove");

		StringWriter stringWriter = new StringWriter();
		PrintWriter writer = new PrintWriter(stringWriter);
		when(response.getWriter()).thenReturn(writer);

		new RimuoviProdCarrelloServlet().doPost(request, response);
		new RimuoviProdCarrelloServlet().doGet(request, response);

		writer.flush();
	}

	@Test
	public void testWrongIdServlet() throws Exception
	{
		when(request.getSession()).thenReturn(mock(HttpSession.class));

		when(request.getParameter("action")).thenReturn("remove");
		when(request.getParameter("id")).thenReturn("9");

		when(request.getSession().getAttribute("cart")).thenReturn(cart);

		
		when(request.getParameter("action")).thenReturn("remove");

		StringWriter stringWriter = new StringWriter();
		PrintWriter writer = new PrintWriter(stringWriter);
		when(response.getWriter()).thenReturn(writer);

		new RimuoviProdCarrelloServlet().doPost(request, response);
		new RimuoviProdCarrelloServlet().doGet(request, response);

		writer.flush();
	}

	@Test
	public void testActionWrongServlet() throws Exception
	{
		when(request.getSession()).thenReturn(mock(HttpSession.class));

		when(request.getParameter("action")).thenReturn("gt4grb");
		when(request.getParameter("id")).thenReturn("2");

		when(request.getSession().getAttribute("cart")).thenReturn(cart);

		StringWriter stringWriter = new StringWriter();
		PrintWriter writer = new PrintWriter(stringWriter);
		when(response.getWriter()).thenReturn(writer);

		new RimuoviProdCarrelloServlet().doPost(request, response);
		new RimuoviProdCarrelloServlet().doGet(request, response);

		writer.flush();
	}
	
	@Test
	public void testActionNullServlet() throws Exception
	{
		when(request.getSession()).thenReturn(mock(HttpSession.class));


		when(request.getSession().getAttribute("cart")).thenReturn(cart);

		

		StringWriter stringWriter = new StringWriter();
		PrintWriter writer = new PrintWriter(stringWriter);
		when(response.getWriter()).thenReturn(writer);

		new RimuoviProdCarrelloServlet().doPost(request, response);
		new RimuoviProdCarrelloServlet().doGet(request, response);

		writer.flush();
	}
	
	@After
	public void remove() throws SQLException
	{
		model.doDelete(prod);
	}

}
