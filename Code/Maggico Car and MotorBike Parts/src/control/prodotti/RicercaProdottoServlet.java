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


@WebServlet("/RicercaProdottoServlet")
public class RicercaProdottoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	static ProductManager model = new ProductManager();
 
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("search") != null) 
		{
			try {
				request.getSession().setAttribute("searchProducts",model.doRetrieveProdsByName((String) request.getParameter("search"),(Collection<ProductBean>) request.getSession().getAttribute("products")));
				request.getSession().setAttribute("stringSearch", request.getParameter("search"));
			} catch (SQLException e) {
				response.sendRedirect("exception.jsp");
			}
		}
		response.sendRedirect("prodotti.jsp");
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
