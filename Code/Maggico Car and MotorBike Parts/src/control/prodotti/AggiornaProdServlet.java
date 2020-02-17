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


@WebServlet("/AggiornaProdServlet")
public class AggiornaProdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	static ProductManager model = new ProductManager();

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("index.jsp");
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");

		try {

			if(action != null) {
				if(action.equalsIgnoreCase("update"))
				{
					int id = Integer.parseInt(request.getParameter("id"));
					String nome = request.getParameter("pname");
					String description = request.getParameter("pdescription");
					double price = Double.parseDouble(request.getParameter("pprice"));
					int quantity = Integer.parseInt(request.getParameter("pquantity"));
					String imglink = request.getParameter("pimglink");

					if (VerifyInput.getVerifyInput().inputIsEmpty(request.getParameter("id"))
							|| VerifyInput.getVerifyInput().inputIsEmpty(imglink)
							|| VerifyInput.getVerifyInput().inputIsEmpty(nome)
							|| VerifyInput.getVerifyInput().inputIsEmpty(description)
							|| VerifyInput.getVerifyInput().inputIsEmpty(request.getParameter("pprice"))
							|| VerifyInput.getVerifyInput().inputIsEmpty(request.getParameter("pquantity"))) {
						response.sendRedirect("addItem.jsp");
						return;
					}

					ProductBean bean = new ProductBean();
					bean.setId(id);
					bean.setNome(nome);
					bean.setDescr(description);
					bean.setPrezzo(price);
					bean.setQtprod(quantity);
					bean.setImglink(imglink);

					model.doUpdate(bean);
				}
			}
		
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
