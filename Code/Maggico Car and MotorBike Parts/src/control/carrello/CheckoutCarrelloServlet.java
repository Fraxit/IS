package control.carrello;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ProductManager;
import bean.CartBean;
import bean.ProductBean;

@WebServlet("/CheckoutCarrelloServlet")
public class CheckoutCarrelloServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
		response.sendRedirect("index.jsp");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductManager model = new ProductManager();

		CartBean cart = (CartBean) request.getSession().getAttribute("cart");

		try 
		{
			for(int i = 0; i<cart.getItems().size(); i++) 
			{
				ProductBean prod = model.doRetrieveByKey(cart.getItems().get(i).getId());
				int newqt;
				if(cart.getItems().get(i).getQtprod() == -1)
				{
					newqt = -1;
				}
				else
				{
					newqt = prod.getQtprod() - cart.getItems().get(i).getQtprod();
				}

				if(newqt >= 0)
				{
					prod.setQtprod(newqt);
					model.doUpdate(prod);
					cart.getItems().remove(cart.getItems().get(i));
					i = i-1;
				}
				else
				{
					cart.getItems().get(i).setQtprod(-1);
					cart.getItems().get(i).setPrezzo(0);
				}
			}

			request.getSession().setAttribute("cart", cart);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			response.sendRedirect("exception.jsp");
		}

		request.getSession().setAttribute("cart", cart);	
		response.sendRedirect("carrello.jsp");

	}

}
