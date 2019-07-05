package in.phase1.creditmgt.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import in.phase1.creditmgt.bean.CustomerInfo;
import in.phase1.creditmgt.model.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UserProfile
 */
public class UserProfile extends HttpServlet {
	
	private RequestDispatcher rd = null;
	
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws IOException, ServletException {
		
		String name= request.getParameter("uname");
		CreditmgtDAOImpl cdaoimpl= new CreditmgtDAOImpl();
		CustomerInfo customer= new CustomerInfo();
		try {
			customer= cdaoimpl.search(name);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(customer!=null) {
			request.setAttribute("USER", name);
			rd = request.getRequestDispatcher("viewProfile.jsp");
		}else {
			request.setAttribute("error", "User Not Found");
			rd = request.getRequestDispatcher("viewAll.jsp");
		}
		rd.forward(request, response);
	}

}
