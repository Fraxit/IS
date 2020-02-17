package control.prodotti;


import model.ProductManager;
import model.VerifyInput;
import bean.ProductBean;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/RimuoviProdottoCatalogoServlet")
public class RimuoviProdottoCatalogoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	static ProductManager model = new ProductManager();

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("index.jsp");
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");

		try {

			if(action != null) {
				if(action.equalsIgnoreCase("delete")) {	
					int id = Integer.parseInt(request.getParameter("id")); 
					
					if( VerifyInput.getVerifyInput().inputIsEmpty(request.getParameter("id")) ) {
						response.sendRedirect("addItem.jsp");
						return;
					}
					ProductBean bean = model.doRetrieveByKey(id);
					if(bean != null) 
						model.doDelete(bean);
					request.getSession().removeAttribute("products");
					request.getSession().setAttribute("products",model.doRetrieveAll());
				} 
			}
		}
		catch(SQLException | NumberFormatException e)
		{
			System.out.println("errore:" + e.getMessage());
			request.setAttribute("error", e.getMessage());
			response.sendRedirect("exception.jsp");
		}

		try 
		{
			request.getSession().removeAttribute("products");
			request.getSession().setAttribute("products",model.doRetrieveAll());
		} 
		catch(SQLException e) 
		{
			System.out.println("errore:" + e.getMessage());
			request.getSession().setAttribute("error", e.getMessage());
			response.sendRedirect("exception.jsp");

		}
		response.sendRedirect("prodotti.jsp");
	}

}
