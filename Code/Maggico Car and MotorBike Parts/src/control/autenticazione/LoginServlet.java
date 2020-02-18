package control.autenticazione;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.AdminManager;
import model.CarrelloManager;
import model.ClientManager;
import model.UserManager;
import model.VerifyInput;
import bean.CartBean;
import bean.ClientBean;
import bean.UserBean;


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;


	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.sendRedirect("index.jsp");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		boolean isAdmin = false; 

		if( VerifyInput.getVerifyInput().inputIsEmpty( username ) || VerifyInput.getVerifyInput().inputIsEmpty( password ) ) {
			response.sendRedirect("index.jsp");
			return;
		}
		request.getSession().setMaxInactiveInterval(0);
		try 
		{
			
			UserBean userbean = UserManager.doRetrieveByKey(username);
			
			isAdmin = AdminManager.isAdmin(username);

			
			if(userbean != null) 
			{
				if(userbean.getUsername().toLowerCase().equals(username.toLowerCase()) && userbean.getPassword().equals(password)) 
				{
					if(isAdmin == true)   
					{
						request.getSession().setAttribute("user", userbean);
						request.getSession().setAttribute("isAdmin", isAdmin);
					}
					else  
					{
						ClientBean clientbean = ClientManager.doRetrieveByKey(username);

						request.getSession().setAttribute("isAdmin", isAdmin);
						
						request.getSession().setAttribute("user", clientbean);

						CartBean cart = CarrelloManager.doRetrieveAll(clientbean);
						request.getSession().setAttribute("cart", cart);
					}
				}
				else
				{
					request.getSession().setAttribute("loginerror", true);
				}

			}
			else
			{
				request.getSession().setAttribute("loginerror", true);
			}
		} 
		catch(SQLException e) 
		{
			e.printStackTrace();
			response.sendRedirect("exception.jsp");
		}

		
		response.sendRedirect("index.jsp");
	}

}
