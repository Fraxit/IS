package test.testServlet;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.mockito.Mockito;
import org.junit.Before;
import org.junit.Test;

import control.account.CambioPasswordServlet;
import bean.ClientBean;
import bean.UserBean;


public class CambioPasswordAdminServletTest extends Mockito {

	private HttpServletRequest request;
	private HttpServletResponse response;
	private UserBean user;
	private ClientBean client;
	
	@Before
	public void setUp()
	{
		request = mock(HttpServletRequest.class);       
		response = mock(HttpServletResponse.class);
		user = new UserBean();
		client = new ClientBean();
		user.setPassword("Password1");
		client.setPassword("Password1");
	}
	
	@Test
	public void testAdminIsEmptyServlet() throws Exception
	{		
		when(request.getSession()).thenReturn(mock(HttpSession.class));
		
		when(request.getParameter("action")).thenReturn("password");
		when(request.getSession().getAttribute("user")).thenReturn(user);
		when(request.getSession().getAttribute("isAdmin")).thenReturn(true);

		when(request.getParameter("vecchiapass")).thenReturn("");
		when(request.getParameter("nuovapass")).thenReturn("");
		when(request.getParameter("confermapass")).thenReturn("");


		StringWriter stringWriter = new StringWriter();
		PrintWriter writer = new PrintWriter(stringWriter);
		when(response.getWriter()).thenReturn(writer);

		new CambioPasswordServlet().doPost(request, response);
		new CambioPasswordServlet().doGet(request, response);

		writer.flush();
	}
	

	@Test
	public void testClienteIsEmptyServlet() throws Exception
	{
		when(request.getSession()).thenReturn(mock(HttpSession.class));
		
		when(request.getParameter("action")).thenReturn("password");
		when(request.getSession().getAttribute("user")).thenReturn(client);
		when(request.getSession().getAttribute("isAdmin")).thenReturn(false);

		when(request.getParameter("vecchiapass")).thenReturn("");
		when(request.getParameter("nuovapass")).thenReturn("");
		when(request.getParameter("confermapass")).thenReturn("");


		StringWriter stringWriter = new StringWriter();
		PrintWriter writer = new PrintWriter(stringWriter);
		when(response.getWriter()).thenReturn(writer);

		new CambioPasswordServlet().doPost(request, response);
		new CambioPasswordServlet().doGet(request, response);

		writer.flush();
	}
	
	@Test
	public void testClienteIsWrongServlet() throws Exception {
		
		
		when(request.getSession()).thenReturn(mock(HttpSession.class));
		
		when(request.getParameter("action")).thenReturn("password");
		when(request.getSession().getAttribute("user")).thenReturn(client);
		when(request.getSession().getAttribute("isAdmin")).thenReturn(false);

		when(request.getParameter("vecchiapass")).thenReturn("password");
		when(request.getParameter("nuovapass")).thenReturn("");
		when(request.getParameter("confermapass")).thenReturn("");


		StringWriter stringWriter = new StringWriter();
		PrintWriter writer = new PrintWriter(stringWriter);
		when(response.getWriter()).thenReturn(writer);

		new CambioPasswordServlet().doPost(request, response);
		new CambioPasswordServlet().doGet(request, response);

		writer.flush();
	}
	
	@Test
	public void testAdminIsWrongServlet() throws Exception {		
		when(request.getSession()).thenReturn(mock(HttpSession.class));
		
		when(request.getParameter("action")).thenReturn("password");
		when(request.getSession().getAttribute("user")).thenReturn(user);
		when(request.getSession().getAttribute("isAdmin")).thenReturn(true);

		when(request.getParameter("vecchiapass")).thenReturn("password");
		when(request.getParameter("nuovapass")).thenReturn("");
		when(request.getParameter("confermapass")).thenReturn("");


		StringWriter stringWriter = new StringWriter();
		PrintWriter writer = new PrintWriter(stringWriter);
		when(response.getWriter()).thenReturn(writer);

		new CambioPasswordServlet().doPost(request, response);
		new CambioPasswordServlet().doGet(request, response);

		writer.flush();
	}
	
	
	@Test
	public void testAdminServlet() throws Exception {		
		when(request.getSession()).thenReturn(mock(HttpSession.class));
		
		when(request.getParameter("action")).thenReturn("password");
		when(request.getSession().getAttribute("user")).thenReturn(user);
		when(request.getSession().getAttribute("isAdmin")).thenReturn(true);

		when(request.getParameter("vecchiapass")).thenReturn("Password1");
		when(request.getParameter("nuovapass")).thenReturn("Password123");
		when(request.getParameter("confermapass")).thenReturn("Password123");


		StringWriter stringWriter = new StringWriter();
		PrintWriter writer = new PrintWriter(stringWriter);
		when(response.getWriter()).thenReturn(writer);

		new CambioPasswordServlet().doPost(request, response);
		new CambioPasswordServlet().doGet(request, response);

		writer.flush();
	}
	
	@Test
	public void testClienteServlet() throws Exception {
		
		
		when(request.getSession()).thenReturn(mock(HttpSession.class));
		
		when(request.getParameter("action")).thenReturn("password");
		when(request.getSession().getAttribute("user")).thenReturn(client);
		when(request.getSession().getAttribute("isAdmin")).thenReturn(false);

		when(request.getParameter("vecchiapass")).thenReturn("Password1");
		when(request.getParameter("nuovapass")).thenReturn("Password123");
		when(request.getParameter("confermapass")).thenReturn("Password123");

		StringWriter stringWriter = new StringWriter();
		PrintWriter writer = new PrintWriter(stringWriter);
		when(response.getWriter()).thenReturn(writer);

		new CambioPasswordServlet().doPost(request, response);
		new CambioPasswordServlet().doGet(request, response);

		writer.flush();
	}
	
	@Test
	public void testConfermaPassEmptyServlet() throws Exception {
		
		
		when(request.getSession()).thenReturn(mock(HttpSession.class));
		
		when(request.getParameter("action")).thenReturn("password");
		when(request.getSession().getAttribute("user")).thenReturn(client);
		when(request.getSession().getAttribute("isAdmin")).thenReturn(true);

		when(request.getParameter("vecchiapass")).thenReturn("Password1");
		when(request.getParameter("nuovapass")).thenReturn("Password123");
		when(request.getParameter("confermapass")).thenReturn("");

		StringWriter stringWriter = new StringWriter();
		PrintWriter writer = new PrintWriter(stringWriter);
		when(response.getWriter()).thenReturn(writer);

		new CambioPasswordServlet().doPost(request, response);
		new CambioPasswordServlet().doGet(request, response);

		writer.flush();
	}
	
	
	@Test
	public void testActionIsWrongServlet() throws Exception {
		
		
		when(request.getSession()).thenReturn(mock(HttpSession.class));
		
		when(request.getParameter("action")).thenReturn("ergrh");
		when(request.getSession().getAttribute("user")).thenReturn(client);
		when(request.getSession().getAttribute("isAdmin")).thenReturn(false);

		when(request.getParameter("vecchiapass")).thenReturn("Password1");
		when(request.getParameter("nuovapass")).thenReturn("Password123");
		when(request.getParameter("confermapass")).thenReturn("Password123");

		StringWriter stringWriter = new StringWriter();
		PrintWriter writer = new PrintWriter(stringWriter);
		when(response.getWriter()).thenReturn(writer);

		new CambioPasswordServlet().doPost(request, response);
		new CambioPasswordServlet().doGet(request, response);

		writer.flush();
	}
	
	
	
}