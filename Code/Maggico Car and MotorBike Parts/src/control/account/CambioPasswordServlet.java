package control.account;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ClientManager;
import model.UserManager;
import model.VerifyInput;
import bean.ClientBean;
import bean.UserBean;

@WebServlet("/CambioPasswordServlet")
public class CambioPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	static ClientManager clientmodel = new ClientManager();
	static UserManager usermodel = new UserManager();

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.sendRedirect("index.jsp");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String action = request.getParameter("action");
		try {
			if (action.equals("password")) {

				if ((boolean) request.getSession().getAttribute("isAdmin")) {
					UserBean admin = (UserBean) request.getSession().getAttribute("user");

					String apsw = request.getParameter("vecchiapass");
					if (VerifyInput.getVerifyInput().inputIsEmpty(apsw)
							|| VerifyInput.getVerifyInput().inputIsEmpty(request.getParameter("nuovapass"))
							|| VerifyInput.getVerifyInput().inputIsEmpty(request.getParameter("confermapass"))) {
						response.sendRedirect("ClientPage.jsp");
						return;
					}
					if (apsw.equals(admin.getPassword())) {
						if (request.getParameter("nuovapass").equals(request.getParameter("confermapass"))) {
							admin.setPassword(request.getParameter("nuovapass"));
							usermodel.doUpdate(admin);
						}

					} else {
						request.setAttribute("errore", "password errata");
					}

				} 
				else {
					ClientBean client = (ClientBean) request.getSession().getAttribute("user");

					String psw = request.getParameter("vecchiapass");
					if (VerifyInput.getVerifyInput().inputIsEmpty(psw)
							|| VerifyInput.getVerifyInput().inputIsEmpty(request.getParameter("nuovapass"))
							|| VerifyInput.getVerifyInput().inputIsEmpty(request.getParameter("confermapass"))) {
						response.sendRedirect("ClientPage.jsp");
						return;
					}
					if (psw.equals(client.getPassword())) {
						if (request.getParameter("nuovapass").equals(request.getParameter("confermapass"))) {

							client.setPassword(request.getParameter("nuovapass"));
							clientmodel.doUpdate(client);
						}

					} else {
						request.setAttribute("errore", "password errata");
					}
				}

			}
		} catch (SQLException e) {
			e.printStackTrace();
			response.sendRedirect("exception.jsp");
		}
		response.sendRedirect("index.jsp");
	}

}
