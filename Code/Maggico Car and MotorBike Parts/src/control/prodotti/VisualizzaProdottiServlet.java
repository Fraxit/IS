package control.prodotti;


import model.ProductManager;
import bean.ProductBean;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/VisualizzaProdottiServlet")
public class VisualizzaProdottiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	static ProductManager model = new ProductManager();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		try 
		{
			request.getSession().removeAttribute("products");
			request.getSession().setAttribute("products",model.doRetrieveAll());
			Collection<?> prods =(Collection<?>) request.getSession().getAttribute("products");
			request.getSession().setAttribute("nProds", prods.size());
		} 
		catch(SQLException e) 
		{
			request.getSession().setAttribute("error", e.getMessage());
			response.sendRedirect("exception.jsp");

		}



		response.sendRedirect("prodotti.jsp");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
