package test.testServlet;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.mockito.Mockito;

import org.junit.Test;

import control.autenticazione.LoginServlet;


public class LoginServletTest extends Mockito {

	 @Test
	    public void testServlet() throws Exception {
	        HttpServletRequest request = mock(HttpServletRequest.class);       
	        HttpServletResponse response = mock(HttpServletResponse.class);    
	        when(request.getParameter("username")).thenReturn("Raff93");
	        when(request.getParameter("password")).thenReturn("Password123");
	        when(request.getSession()).thenReturn(mock(HttpSession.class));
	        
	        StringWriter stringWriter = new StringWriter();
	        PrintWriter writer = new PrintWriter(stringWriter);
	        when(response.getWriter()).thenReturn(writer);

	        new LoginServlet().doPost(request, response);
	        new LoginServlet().doGet(request, response);

	        writer.flush();
	    }
	 
	 @Test
	    public void testAdminServlet() throws Exception {
	        HttpServletRequest request = mock(HttpServletRequest.class);       
	        HttpServletResponse response = mock(HttpServletResponse.class);    
	        when(request.getParameter("username")).thenReturn("Vincenzo");
	        when(request.getParameter("password")).thenReturn("Password1");

	        when(request.getSession()).thenReturn(mock(HttpSession.class));
	        
	        StringWriter stringWriter = new StringWriter();
	        PrintWriter writer = new PrintWriter(stringWriter);
	        when(response.getWriter()).thenReturn(writer);

	        new LoginServlet().doPost(request, response);
	        new LoginServlet().doGet(request, response);

	        writer.flush();
	    }
	 
	 @Test
	    public void testEmptyUsernameServlet() throws Exception {
	        HttpServletRequest request = mock(HttpServletRequest.class);       
	        HttpServletResponse response = mock(HttpServletResponse.class);    
	        when(request.getParameter("username")).thenReturn("");
	        when(request.getParameter("password")).thenReturn("vfd b");

	        when(request.getSession()).thenReturn(mock(HttpSession.class));
	        
	        StringWriter stringWriter = new StringWriter();
	        PrintWriter writer = new PrintWriter(stringWriter);
	        when(response.getWriter()).thenReturn(writer);

	        new LoginServlet().doPost(request, response);
	        new LoginServlet().doGet(request, response);

	        writer.flush();
	    }
	 
	 @Test
	    public void testEmptyPasswordServlet() throws Exception {
	        HttpServletRequest request = mock(HttpServletRequest.class);       
	        HttpServletResponse response = mock(HttpServletResponse.class);    
	        when(request.getParameter("username")).thenReturn("cwdvef");
	        when(request.getParameter("password")).thenReturn("");

	        when(request.getSession()).thenReturn(mock(HttpSession.class));
	        
	        StringWriter stringWriter = new StringWriter();
	        PrintWriter writer = new PrintWriter(stringWriter);
	        when(response.getWriter()).thenReturn(writer);

	        new LoginServlet().doPost(request, response);
	        new LoginServlet().doGet(request, response);

	        writer.flush();
	    }
	 
}
