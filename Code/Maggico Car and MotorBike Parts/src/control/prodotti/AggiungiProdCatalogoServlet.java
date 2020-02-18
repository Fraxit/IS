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


@WebServlet("/AggiungiProdCatalogoServlet")
public class AggiungiProdCatalogoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("index.jsp");
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			String name = request.getParameter("insname");
			String description = request.getParameter("insdescription");
			double price = Double.parseDouble(request.getParameter("insprice"));
			int quantity = Integer.parseInt(request.getParameter("insquantity"));
			String imglink = request.getParameter("insimglink");

			if (VerifyInput.getVerifyInput().inputIsEmpty(imglink) || VerifyInput.getVerifyInput().inputIsEmpty(name)
					|| VerifyInput.getVerifyInput().inputIsEmpty(description)
					|| VerifyInput.getVerifyInput().inputIsEmpty(request.getParameter("insprice"))
					|| VerifyInput.getVerifyInput().inputIsEmpty(request.getParameter("insquantity"))) {
				response.sendRedirect("addItem.jsp");
				return;
			}

			ProductBean bean = new ProductBean();
			bean.setNome(name);
			bean.setDescr(description);
			bean.setPrezzo(price);
			bean.setQtprod(quantity);
			bean.setImglink(imglink);

			ProductManager.doSave(bean);
			request.getSession().removeAttribute("products");
			request.getSession().setAttribute("products",ProductManager.doRetrieveAll());

			request.getSession().removeAttribute("products");
			request.getSession().setAttribute("products",ProductManager.doRetrieveAll());
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
