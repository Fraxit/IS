package control.carrello;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.CartBean;
import bean.ClientBean;
import bean.ProductBean;
import model.CarrelloManager;

@WebServlet("/AddProdCarrelloServlet") 
public class AddProdCarrelloServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
		response.sendRedirect("index.jsp");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		CartBean cart = (CartBean) request.getSession().getAttribute("cart");
		CarrelloManager cartmodel = new CarrelloManager();


		ProductBean prod = (ProductBean) request.getSession().getAttribute("bean");
		prod.setQtprod(Integer.parseInt(request.getParameter("qtcart")));


		if( cart.getItems().isEmpty() )
		{
			cart.addItem(prod);
			try {
				cartmodel.updateTable(cart,(ClientBean) request.getSession().getAttribute("user"));
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		else
		{
			for(int i=0; i< cart.getItems().size(); i++)
			{
				if( cart.getItems().get(i).getId() == prod.getId())
				{
					cart.getItems().set(i, prod);
					cart.getItems().get(i).setQtprod( prod.getQtprod() );
					
					request.getSession().setAttribute("cart", cart);	
					try {
						cartmodel.updateTable(cart,(ClientBean) request.getSession().getAttribute("user"));
					} catch (SQLException e) {
						e.printStackTrace();
					}
					response.sendRedirect("prodotti.jsp");
					return;
				}
			}
			cart.addItem(prod);
			try {
				cartmodel.updateTable(cart,(ClientBean) request.getSession().getAttribute("user"));
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		request.getSession().setAttribute("cart", cart);	
		response.sendRedirect("prodotti.jsp");
	}


}
