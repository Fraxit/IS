package test.testServlet;

import static org.junit.Assert.assertEquals;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.mockito.Mockito;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import control.prodotti.AggiornaProdServlet;
import model.ProductManager;
import bean.ProductBean;


public class AggiornaProdServletTest extends Mockito {

	private HttpServletRequest request;
	private HttpServletResponse response;
	private ProductBean prod;
	private ProductManager model;
	@Before
	public void setUp()
	{
		request = mock(HttpServletRequest.class);       
		response = mock(HttpServletResponse.class);
		model = new ProductManager();
		prod = new ProductBean();
		prod.setNome("Oggetto");
		
		
	}
	
	@Test
	public void testActionOtherServlet() throws Exception
	{		
		when(request.getParameter("action")).thenReturn("altro");

		when(request.getSession()).thenReturn(mock(HttpSession.class));
		
	
		StringWriter stringWriter = new StringWriter();
		PrintWriter writer = new PrintWriter(stringWriter);
		when(response.getWriter()).thenReturn(writer);

		new AggiornaProdServlet().doPost(request, response);
		new AggiornaProdServlet().doGet(request, response);

		writer.flush();
	}
	
	@Test
	public void testActionNullServlet() throws Exception
	{		
		when(request.getParameter("action")).thenReturn(null);

		when(request.getSession()).thenReturn(mock(HttpSession.class));
		
	
		StringWriter stringWriter = new StringWriter();
		PrintWriter writer = new PrintWriter(stringWriter);
		when(response.getWriter()).thenReturn(writer);

		new AggiornaProdServlet().doPost(request, response);
		new AggiornaProdServlet().doGet(request, response);

		writer.flush();
	}
	
	@Test
	public void testServlet() throws Exception
	{		
		when(request.getSession()).thenReturn(mock(HttpSession.class));
		
		when(request.getParameter("action")).thenReturn("update");
		when(request.getParameter("id")).thenReturn("1");
		when(request.getParameter("pname")).thenReturn("Oggetto");
		when(request.getParameter("pdescription")).thenReturn("Oggetto molto bello");
		when(request.getParameter("pprice")).thenReturn("1");
		when(request.getParameter("pquantity")).thenReturn("100");
		when(request.getParameter("pimglink")).thenReturn("Oggetto");

		StringWriter stringWriter = new StringWriter();
		PrintWriter writer = new PrintWriter(stringWriter);
		when(response.getWriter()).thenReturn(writer);

		new AggiornaProdServlet().doPost(request, response);
		new AggiornaProdServlet().doGet(request, response);

		writer.flush();
	}
	
	
	@Test
	public void testEx2Servlet() throws Exception
	{		
		when(request.getSession()).thenReturn(mock(HttpSession.class));
		
		when(request.getParameter("action")).thenReturn("update");
		when(request.getParameter("id")).thenReturn("1");
		when(request.getParameter("pname")).thenReturn("");
		when(request.getParameter("pdescription")).thenReturn("Oggetto molto bello");
		when(request.getParameter("pprice")).thenReturn("1");
		when(request.getParameter("pquantity")).thenReturn("100");
		when(request.getParameter("pimglink")).thenReturn("Oggetto");

		StringWriter stringWriter = new StringWriter();
		PrintWriter writer = new PrintWriter(stringWriter);
		when(response.getWriter()).thenReturn(writer);

		new AggiornaProdServlet().doPost(request, response);
		new AggiornaProdServlet().doGet(request, response);

		writer.flush();
	}
	
	@Test
	public void testEx3Servlet() throws Exception
	{		
		when(request.getSession()).thenReturn(mock(HttpSession.class));
		
		when(request.getParameter("action")).thenReturn("update");
		when(request.getParameter("id")).thenReturn("1");
		when(request.getParameter("pname")).thenReturn("Oggetto");
		when(request.getParameter("pdescription")).thenReturn("");
		when(request.getParameter("pprice")).thenReturn("1");
		when(request.getParameter("pquantity")).thenReturn("100");
		when(request.getParameter("pimglink")).thenReturn("Oggetto");

		StringWriter stringWriter = new StringWriter();
		PrintWriter writer = new PrintWriter(stringWriter);
		when(response.getWriter()).thenReturn(writer);

		new AggiornaProdServlet().doPost(request, response);
		new AggiornaProdServlet().doGet(request, response);

		writer.flush();
	}
	
	
	
	
	@After
	public void remove() throws SQLException
	{
		model.doDelete(prod);
	}
	
	
}