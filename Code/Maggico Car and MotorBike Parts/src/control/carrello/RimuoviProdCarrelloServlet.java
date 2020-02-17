package control.carrello;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.CarrelloManager;
import model.ProductManager;
import bean.CartBean;
import bean.ClientBean;
import bean.ProductBean;

@WebServlet("/RimuoviProdCarrelloServlet")
public class RimuoviProdCarrelloServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
		response.sendRedirect("index.jsp");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductManager model = new ProductManager();
		String action = request.getParameter("action");

		CartBean cart = (CartBean) request.getSession().getAttribute("cart");

		CarrelloManager cartmodel = new CarrelloManager();

		if(action != null) {
			if(action.equals("remove")) {
				try {
					int id = Integer.parseInt(request.getParameter("id"));
					ProductBean prod = model.doRetrieveByKey(id);

					for(int i=0; i< cart.getItems().size(); i++)
					{
						if( cart.getItems().get(i).getId() == prod.getId() )
						{
							cart.deleteItem(prod);
							cartmodel.updateTable((CartBean) request.getSession().getAttribute("cart"),(ClientBean) request.getSession().getAttribute("user"));
						}
					}
				}
				catch(SQLException e)
				{
					e.printStackTrace();
					response.sendRedirect("exception.jsp");
				}
				request.getSession().removeAttribute("cart");
				request.getSession().setAttribute("cart", cart);	
				response.sendRedirect("carrello.jsp");
			}
		}
	}

}
