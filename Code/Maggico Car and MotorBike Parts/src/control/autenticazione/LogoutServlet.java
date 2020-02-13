package control.autenticazione;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.CarrelloManager;
import bean.CartBean;
import bean.ClientBean;


@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	CarrelloManager cartmodel = new CarrelloManager();
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.sendRedirect("index.jsp");
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		boolean isAdmin = (boolean) request.getSession().getAttribute("isAdmin");

		HttpSession session = request.getSession();

		if(session != null) 
		{
			if(isAdmin == false)
			{
				try {
					cartmodel.updateTable((CartBean) request.getSession().getAttribute("cart"),(ClientBean) request.getSession().getAttribute("user"));
				} catch (SQLException e) {
					response.sendRedirect("exception.jsp");
					e.printStackTrace();
				}

				session.invalidate();
				request.removeAttribute("clientbean");
				request.removeAttribute("username");
				request.removeAttribute("cart");
			} 
			else 
			{

				session.invalidate();
			}
			response.sendRedirect("index.jsp");
		} 
		else
		{
			response.sendRedirect("index.jsp");
		}

	}

}
