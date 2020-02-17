package test.testServlet;


import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.mockito.Mockito;

import control.prodotti.AggiungiProdCatalogoServlet;
import model.ProductManager;
import bean.ProductBean;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class AggiungiProdCatalogoServletTest extends Mockito {

	private HttpServletRequest request;
	private HttpServletResponse response;
	private ProductManager model;
	private ProductBean prod;
	
	@Before
	public void setUp()
	{
		request = mock(HttpServletRequest.class);       
		response = mock(HttpServletResponse.class);
		model = new ProductManager();
		prod = new ProductBean();
		prod.setNome("Ruote Bridgstone R18");
		prod.setDescr("Oggetto molto bello");
		prod.setPrezzo(15);
		prod.setQtprod(100);
		prod.setImglink("https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.ebay.it%2Fitm%2FPNEUMATICI-GOMME-AUTO-INVERNALI-BRIDGESTONE-LM-30-195-60-R15-88-T-%2F383047079690&psig=AOvVaw2sEMSWY_4v9Jm69oi3naGo&ust=1580509119986000&source=images&cd=vfe&ved=0CAIQjRxqFwoTCJjB4betrOcCFQAAAAAdAAAAABAF");
		
	}
	
	@Test
	public void testServlet() throws Exception
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

		new AggiungiProdCatalogoServlet().doPost(request, response);
		new AggiungiProdCatalogoServlet().doGet(request, response);

		writer.flush();
	}
	
	@Test
	public void testExceptionServlet() throws Exception
	{		
		when(request.getSession()).thenReturn(mock(HttpSession.class));
		
		when(request.getParameter("insname")).thenReturn("");
		when(request.getParameter("insdescription")).thenReturn("Oggetto molto bello");
		when(request.getParameter("insprice")).thenReturn("15");
		when(request.getParameter("insquantity")).thenReturn("100");
		when(request.getParameter("insimglink")).thenReturn("https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.ebay.it%2Fitm%2FPNEUMATICI-GOMME-AUTO-INVERNALI-BRIDGESTONE-LM-30-195-60-R15-88-T-%2F383047079690&psig=AOvVaw2sEMSWY_4v9Jm69oi3naGo&ust=1580509119986000&source=images&cd=vfe&ved=0CAIQjRxqFwoTCJjB4betrOcCFQAAAAAdAAAAABAF");

		StringWriter stringWriter = new StringWriter();
		PrintWriter writer = new PrintWriter(stringWriter);
		when(response.getWriter()).thenReturn(writer);

		new AggiungiProdCatalogoServlet().doPost(request, response);
		new AggiungiProdCatalogoServlet().doGet(request, response);

		writer.flush();
	}
	@Test
	public void testEx2Servlet() throws Exception
	{		
		when(request.getSession()).thenReturn(mock(HttpSession.class));
		
		when(request.getParameter("insname")).thenReturn("Ruote Bridgstone R18");
		when(request.getParameter("insdescription")).thenReturn("");
		when(request.getParameter("insprice")).thenReturn("15");
		when(request.getParameter("insquantity")).thenReturn("100");
		when(request.getParameter("insimglink")).thenReturn("https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.ebay.it%2Fitm%2FPNEUMATICI-GOMME-AUTO-INVERNALI-BRIDGESTONE-LM-30-195-60-R15-88-T-%2F383047079690&psig=AOvVaw2sEMSWY_4v9Jm69oi3naGo&ust=1580509119986000&source=images&cd=vfe&ved=0CAIQjRxqFwoTCJjB4betrOcCFQAAAAAdAAAAABAF");

		StringWriter stringWriter = new StringWriter();
		PrintWriter writer = new PrintWriter(stringWriter);
		when(response.getWriter()).thenReturn(writer);

		new AggiungiProdCatalogoServlet().doPost(request, response);
		new AggiungiProdCatalogoServlet().doGet(request, response);

		writer.flush();
	}
	
	@Test
	public void testEx5Servlet() throws Exception
	{		
		when(request.getSession()).thenReturn(mock(HttpSession.class));
		
		when(request.getParameter("insname")).thenReturn("Ruote Bridgstone R18");
		when(request.getParameter("insdescription")).thenReturn("Oggetto molto bello");
		when(request.getParameter("insprice")).thenReturn("15");
		when(request.getParameter("insquantity")).thenReturn("100");
		when(request.getParameter("insimglink")).thenReturn("");

		StringWriter stringWriter = new StringWriter();
		PrintWriter writer = new PrintWriter(stringWriter);
		when(response.getWriter()).thenReturn(writer);

		new AggiungiProdCatalogoServlet().doPost(request, response);
		new AggiungiProdCatalogoServlet().doGet(request, response);

		writer.flush();
	}
	
	@After
	public void remove() throws SQLException
	{
		model.doDelete(prod);
	}
}
