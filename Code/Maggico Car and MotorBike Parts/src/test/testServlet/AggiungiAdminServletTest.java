package test.testServlet;

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

import control.account.AggiungiNuovoAdminServlet;
import model.UserManager;
import bean.AdminBean;


public class AggiungiAdminServletTest extends Mockito {

	private UserManager model;
	private AdminBean admin;
	
	@Before
	public void setUp()
	{
		model = new UserManager();
		admin = new AdminBean();
		admin.setEmail("mikimik1@gmail.com");
		admin.setPassword("Password1");
		admin.setUsername("mikele1");
	}
	
	 @Test
	    public void testServlet() throws Exception {
	        HttpServletRequest request = mock(HttpServletRequest.class);       
	        HttpServletResponse response = mock(HttpServletResponse.class);    
	        when(request.getParameter("rusername")).thenReturn(admin.getUsername());
	        when(request.getParameter("rpassword")).thenReturn(admin.getPassword());
	        when(request.getParameter("email")).thenReturn(admin.getEmail());
	        when(request.getParameter("action")).thenReturn("admin");
	        when(request.getSession()).thenReturn(mock(HttpSession.class));
	        
	        StringWriter stringWriter = new StringWriter();
	        PrintWriter writer = new PrintWriter(stringWriter);
	        when(response.getWriter()).thenReturn(writer);

	        new AggiungiNuovoAdminServlet().doPost(request, response);
	        new AggiungiNuovoAdminServlet().doGet(request, response);

	        writer.flush();
	    }
	 
	 @Test
	    public void testEmptyUsernameServlet() throws Exception {
	        HttpServletRequest request = mock(HttpServletRequest.class);       
	        HttpServletResponse response = mock(HttpServletResponse.class);    
	        when(request.getParameter("rusername")).thenReturn("");
	        when(request.getParameter("rpassword")).thenReturn("fdfgd");
	        when(request.getParameter("email")).thenReturn("hgdbfg");
	        when(request.getParameter("action")).thenReturn("admin");
	        when(request.getSession()).thenReturn(mock(HttpSession.class));
	        
	        StringWriter stringWriter = new StringWriter();
	        PrintWriter writer = new PrintWriter(stringWriter);
	        when(response.getWriter()).thenReturn(writer);

	        new AggiungiNuovoAdminServlet().doPost(request, response);
	        new AggiungiNuovoAdminServlet().doGet(request, response);

	        writer.flush();
	    }
	 
	 @Test
	    public void testEmptyPasswordServlet() throws Exception {
	        HttpServletRequest request = mock(HttpServletRequest.class);       
	        HttpServletResponse response = mock(HttpServletResponse.class);    
	        when(request.getParameter("rusername")).thenReturn("rgerfg");
	        when(request.getParameter("rpassword")).thenReturn("");
	        when(request.getParameter("email")).thenReturn("hgdbfg");
	        when(request.getParameter("action")).thenReturn("admin");
	        when(request.getSession()).thenReturn(mock(HttpSession.class));
	        
	        StringWriter stringWriter = new StringWriter();
	        PrintWriter writer = new PrintWriter(stringWriter);
	        when(response.getWriter()).thenReturn(writer);

	        new AggiungiNuovoAdminServlet().doPost(request, response);
	        new AggiungiNuovoAdminServlet().doGet(request, response);

	        writer.flush();
	    }
	 
	 @Test
	    public void testEmptyEmailServlet() throws Exception {
	        HttpServletRequest request = mock(HttpServletRequest.class);       
	        HttpServletResponse response = mock(HttpServletResponse.class);    
	        when(request.getParameter("rusername")).thenReturn(admin.getUsername());
	        when(request.getParameter("rpassword")).thenReturn(admin.getPassword());
	        when(request.getParameter("email")).thenReturn("");
	        when(request.getParameter("action")).thenReturn("admin");
	        when(request.getSession()).thenReturn(mock(HttpSession.class));
	        
	        StringWriter stringWriter = new StringWriter();
	        PrintWriter writer = new PrintWriter(stringWriter);
	        when(response.getWriter()).thenReturn(writer);

	        new AggiungiNuovoAdminServlet().doPost(request, response);
	        new AggiungiNuovoAdminServlet().doGet(request, response);

	        writer.flush();
	    }
	 
	 @Test
	    public void testWrongActionServlet() throws Exception {
	        HttpServletRequest request = mock(HttpServletRequest.class);       
	        HttpServletResponse response = mock(HttpServletResponse.class);    
	        when(request.getParameter("rusername")).thenReturn(admin.getUsername());
	        when(request.getParameter("rpassword")).thenReturn(admin.getPassword());
	        when(request.getParameter("email")).thenReturn(admin.getEmail());
	        when(request.getParameter("action")).thenReturn("wgfrbt");
	        when(request.getSession()).thenReturn(mock(HttpSession.class));
	        
	        StringWriter stringWriter = new StringWriter();
	        PrintWriter writer = new PrintWriter(stringWriter);
	        when(response.getWriter()).thenReturn(writer);

	        new AggiungiNuovoAdminServlet().doPost(request, response);
	        new AggiungiNuovoAdminServlet().doGet(request, response);

	        writer.flush();
	    }
	 @After
	 public void remove() throws SQLException
	 {
		 model.doDelete(admin);
	 }
}