package control.prodotti;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ProductManager;
import bean.ProductBean;

@WebServlet("/VisualizzaProdCatalogoServlet")
public class VisualizzaProdCatalogoServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	ProductManager model = new ProductManager();

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if( request.getParameter("id") == null )
		{
			response.sendRedirect("index.jsp");
			return;
		}

		int id = Integer.parseInt(request.getParameter("id"));

		try {
			ProductBean bean = model.doRetrieveByKey(id);
			request.getSession().setAttribute("bean", bean);

		}
		catch(SQLException e) {
			response.sendRedirect("exception.jsp");

		}

		response.sendRedirect("prodotto.jsp");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}