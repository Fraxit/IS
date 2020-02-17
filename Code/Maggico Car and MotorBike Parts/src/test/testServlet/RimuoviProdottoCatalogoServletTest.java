package test.testServlet;


import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.mockito.Mockito;

import control.prodotti.AggiungiProdCatalogoServlet;
import control.prodotti.RimuoviProdottoCatalogoServlet;

import org.junit.Before;
import org.junit.Test;


public class RimuoviProdottoCatalogoServletTest extends Mockito {

	private HttpServletRequest request;
	private HttpServletResponse response;
	
	@Before
	public void setUp()
	{
		request = mock(HttpServletRequest.class);       
		response = mock(HttpServletResponse.class);
	}
	
	@Test
	public void testServlet() throws Exception
	{		
		when(request.getSession()).thenReturn(mock(HttpSession.class));
		
		when(request.getParameter("action")).thenReturn("delete");
		when(request.getParameter("id")).thenReturn("1");

		StringWriter stringWriter = new StringWriter();
		PrintWriter writer = new PrintWriter(stringWriter);
		when(response.getWriter()).thenReturn(writer);

		new RimuoviProdottoCatalogoServlet().doPost(request, response);
		new RimuoviProdottoCatalogoServlet().doGet(request, response);

		writer.flush();
	}
	
	@Test
	public void testWrongActionServlet() throws Exception
	{		
		when(request.getSession()).thenReturn(mock(HttpSession.class));
		
		when(request.getParameter("action")).thenReturn("gfngh");
		when(request.getParameter("id")).thenReturn("1");

		StringWriter stringWriter = new StringWriter();
		PrintWriter writer = new PrintWriter(stringWriter);
		when(response.getWriter()).thenReturn(writer);

		new RimuoviProdottoCatalogoServlet().doPost(request, response);
		new RimuoviProdottoCatalogoServlet().doGet(request, response);

		writer.flush();
	}
	
	@Test
	public void testExceptionServlet() throws Exception
	{		
		when(request.getSession()).thenReturn(mock(HttpSession.class));
		
		when(request.getParameter("insname")).thenReturn("Ruote Bridgstone R18");
		when(request.getParameter("insdescription")).thenReturn("Oggetto molto bello");
		when(request.getParameter("insprice")).thenReturn("15");
		when(request.getParameter("insquantity")).thenReturn("100");
		when(request.getParameter("insimglink")).thenReturn("https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.ebay.it%2Fitm%2FPNEUMATICI-GOMME-AUTO-INVERNALI-BRIDGESTONE-LM-30-195-60-R15-88-T-%2F383047079690&psig=AOvVaw2sEMSWY_4v9Jm69oi3naGo&ust=1580509119986000&source=images&cd=vfe&ved=0CAIQjRxqFwoTCJjB4betrOcCFQAAAAAdAAAAABAF");

		StringWriter stringWriter = new StringWriter();
		PrintWriter writer = new PrintWriter(stringWriter);
		when(response.getWriter()).thenReturn(writer);

		new RimuoviProdottoCatalogoServlet().doPost(request, response);
		new RimuoviProdottoCatalogoServlet().doGet(request, response);

		writer.flush();
	}
	
}
