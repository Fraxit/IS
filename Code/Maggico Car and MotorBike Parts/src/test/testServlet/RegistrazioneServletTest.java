package test.testServlet;


import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.mockito.Mockito;

import control.registrazione.RegistrationServlet;
import model.AdminManager;
import model.ClientManager;
import model.UserManager;
import bean.AdminBean;
import bean.ClientBean;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class RegistrazioneServletTest extends Mockito {

	private HttpServletRequest request;
	private HttpServletResponse response;
	UserManager model;
	AdminManager modelAdmin;
	ClientManager modelClient;
	AdminBean admin;
	ClientBean client;

	@Before
	public void setUp() throws SQLException
	{
		request = mock(HttpServletRequest.class);       
		response = mock(HttpServletResponse.class);
		model = new UserManager();
		modelAdmin = new AdminManager();
		modelClient = new ClientManager();
		admin = new AdminBean();
		client = new ClientBean();
		admin.setUsername("ma93");
		client.setUsername("maf93");
	}
	
	@Test
	public void testClienteEmptyIbanServlet() throws Exception
	{		
		when(request.getSession()).thenReturn(mock(HttpSession.class));

		when(request.getParameter("rusername")).thenReturn("maf93");
		when(request.getParameter("rpassword")).thenReturn("Password1");
		when(request.getParameter("email")).thenReturn("raffica93@gmail.com");
		when(request.getParameter("action")).thenReturn("client");
		when(request.getParameter("iban")).thenReturn("");

		StringWriter stringWriter = new StringWriter();
		PrintWriter writer = new PrintWriter(stringWriter);
		when(response.getWriter()).thenReturn(writer);

		new RegistrationServlet().doPost(request, response);
		new RegistrationServlet().doGet(request, response);

		writer.flush();
	}
	
	@Test
	public void testClienteServlet() throws Exception
	{		
		when(request.getSession()).thenReturn(mock(HttpSession.class));

		when(request.getParameter("rusername")).thenReturn("maf93");
		when(request.getParameter("rpassword")).thenReturn("Password1");
		when(request.getParameter("email")).thenReturn("raffica93@gmail.com");
		when(request.getParameter("action")).thenReturn("client");
		when(request.getParameter("iban")).thenReturn("IT54f7564758697057684536457");

		StringWriter stringWriter = new StringWriter();
		PrintWriter writer = new PrintWriter(stringWriter);
		when(response.getWriter()).thenReturn(writer);

		new RegistrationServlet().doPost(request, response);
		new RegistrationServlet().doGet(request, response);

		writer.flush();
	}
	
	@Test
	public void testAdminServlet() throws Exception
	{		
		when(request.getSession()).thenReturn(mock(HttpSession.class));

		when(request.getParameter("rusername")).thenReturn("ma93");
		when(request.getParameter("rpassword")).thenReturn("Password1");
		when(request.getParameter("email")).thenReturn("raffica93@gmail.com");
		when(request.getParameter("action")).thenReturn("admin");

		StringWriter stringWriter = new StringWriter();
		PrintWriter writer = new PrintWriter(stringWriter);
		when(response.getWriter()).thenReturn(writer);

		new RegistrationServlet().doPost(request, response);
		new RegistrationServlet().doGet(request, response);

		writer.flush();
	}
	
	@Test
	public void testOtherActionServlet() throws Exception
	{		
		when(request.getSession()).thenReturn(mock(HttpSession.class));

		when(request.getParameter("rusername")).thenReturn("m93");
		when(request.getParameter("rpassword")).thenReturn("Password1");
		when(request.getParameter("email")).thenReturn("raffica93@gmail.com");
		when(request.getParameter("action")).thenReturn("altro");

		StringWriter stringWriter = new StringWriter();
		PrintWriter writer = new PrintWriter(stringWriter);
		when(response.getWriter()).thenReturn(writer);

		new RegistrationServlet().doPost(request, response);
		new RegistrationServlet().doGet(request, response);

		writer.flush();
	}

	@After
	public void remove() throws SQLException
	{
		model.doDelete(client);
		model.doDelete(admin);
	}

}
