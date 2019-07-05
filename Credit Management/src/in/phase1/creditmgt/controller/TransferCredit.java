package in.phase1.creditmgt.controller;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.phase1.creditmgt.model.CreditmgtDAOImpl;

public class TransferCredit extends HttpServlet {

	private RequestDispatcher rd = null;
	boolean ans, checker, finalchecker;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		String FromUser = request.getParameter("FromUser");
		String ToUser = request.getParameter("ToUser");
		String Amount = request.getParameter("Amount");
		double amount = Double.parseDouble(Amount);

		CreditmgtDAOImpl cdimpl = new CreditmgtDAOImpl();
		try {
			checker = cdimpl.compareCredit(amount, FromUser);
			ans = cdimpl.searchForTransfer(FromUser, ToUser);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (ans == true && checker == true && amount != 0) {
			try {
				finalchecker = cdimpl.creditTransfer(FromUser, ToUser, amount);
				request.setAttribute("notification", "Transaction Successful");
				rd = request.getRequestDispatcher("transferedCredits.jsp");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("notification", "Transaction Successful");
			rd = request.getRequestDispatcher("transferedCredits.jsp");

		} else {

			if (ans != true)// user not found==false
			{
				request.setAttribute("error", "User Not Found! Check the User List");
				rd = request.getRequestDispatcher("viewAll.jsp");

			} else if (checker != true) {// does not have enough fund==false
				request.setAttribute("error", "Does not have enough Credits");
				rd = request.getRequestDispatcher("transfer.jsp");
			} else if (amount == 0) {// amount empty=0
				request.setAttribute("error", "Enter Amount");
				rd = request.getRequestDispatcher("transfer.jsp");
			}

		}
		if (rd == null) {
			
			rd = request.getRequestDispatcher("error.jsp");
		}
		rd.forward(request, response);
	}

}
