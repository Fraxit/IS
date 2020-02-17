package control.account;

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
import bean.UserBean;
import model.AdminManager;

@WebServlet("/AggiungiNuovoAdminServlet")
public class AggiungiNuovoAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	static UserManager usermodel = new UserManager();


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

			usermodel.doSave(userbean);

			if(request.getParameter("action").equals("admin")){
				AdminManager adminmodel = new AdminManager();
				AdminBean admin = new AdminBean();

				admin.setUsername(username);
				admin.setEmail(email);
				admin.setPassword(password);

				adminmodel.doSave(admin);
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
