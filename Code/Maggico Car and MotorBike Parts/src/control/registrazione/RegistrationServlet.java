package control.registrazione;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.UserManager;
import model.VerifyInput;
import bean.AdminBean;
import bean.ClientBean;
import bean.UserBean;
import model.AdminManager;
import model.ClientManager;

@WebServlet("/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;



	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.sendRedirect("index.jsp");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String username = request.getParameter("rusername");
			String password = request.getParameter("rpassword");
			String email = request.getParameter("email");


			if(VerifyInput.getVerifyInput().inputIsEmpty(username) || VerifyInput.getVerifyInput().inputIsEmpty(password) || VerifyInput.getVerifyInput().inputIsEmpty(email)) {
				response.sendRedirect("Registrazione.jsp");
				return;
			}

			UserBean userbean = new UserBean();

			userbean.setUsername(username);
			userbean.setEmail(email);
			userbean.setPassword(password);

			UserManager.doSave(userbean);		//inserimento nella tabella utente

			if(request.getParameter("action").equals("admin")){
				AdminBean admin = new AdminBean();

				admin.setUsername(username);
				admin.setEmail(email);
				admin.setPassword(password);

				AdminManager.doSave(admin);
			} else {

				String iban = request.getParameter("iban");
				if(iban.equals("")) {
					request.getSession().setAttribute("campiVuoti", true);
					response.sendRedirect("Registrazione.jsp");
					return;
				} else {

					ClientBean clientbean = new ClientBean();

					clientbean.setUsername(username);
					clientbean.setIban(iban);

					ClientManager.doSave(clientbean);					//inserimento nella tabella clienti
				}
			}


			response.sendRedirect("index.jsp");
		}

		catch(SQLException | NumberFormatException e) {
			System.out.println("errore:" + e.getMessage()); 
			request.setAttribute("error", e.getMessage());
			response.sendRedirect("exception.jsp");

		}
	}

}
