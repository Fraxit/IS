package test.testServlet;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import control.autenticazione.LoginServlet;
import control.autenticazione.LogoutServlet;
import bean.CartBean;
import bean.ClientBean;

public class LogoutServletTest extends Mockito{

	private CartBean cart;
	private ClientBean client;
	
	@Before
	public void setUp()
	{
		cart = new CartBean();
		client = new ClientBean();
		
		client.setUsername("Raff93");
	}

	@Test
	public void testServlet() throws IOException, ServletException
	{
		HttpServletRequest request = mock(HttpServletRequest.class);       
		HttpServletResponse response = mock(HttpServletResponse.class);
		when(request.getSession()).thenReturn(mock(HttpSession.class));

		when(request.getSession().getAttribute("isAdmin")).thenReturn(false);
		when(request.getSession().getAttribute("cart")).thenReturn(cart);
		when(request.getSession().getAttribute("user")).thenReturn(client);


		StringWriter stringWriter = new StringWriter();
		PrintWriter writer = new PrintWriter(stringWriter);
		when(response.getWriter()).thenReturn(writer);

		new LogoutServlet().doPost(request, response);
		new LogoutServlet().doGet(request, response);

		writer.flush();
	}
	
	
	@Test
	public void testIsAdminIsTrueServlet() throws IOException, ServletException
	{
		HttpServletRequest request = mock(HttpServletRequest.class);       
		HttpServletResponse response = mock(HttpServletResponse.class);
		when(request.getSession()).thenReturn(mock(HttpSession.class));

		when(request.getSession().getAttribute("isAdmin")).thenReturn(true);


		StringWriter stringWriter = new StringWriter();
		PrintWriter writer = new PrintWriter(stringWriter);
		when(response.getWriter()).thenReturn(writer);

		new LogoutServlet().doPost(request, response);
		new LogoutServlet().doGet(request, response);

		writer.flush();
	}


}
