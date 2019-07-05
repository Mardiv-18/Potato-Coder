package in.phase1.creditmgt.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import in.phase1.creditmgt.bean.CustomerInfo;
import in.phase1.creditmgt.model.*;

public class UserSearch extends HttpServlet {
	private RequestDispatcher rd = null;
	
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws IOException, ServletException {
		
		String name= request.getParameter("search");
		CreditmgtDAOImpl cdaoimpl= new CreditmgtDAOImpl();
		CustomerInfo customer= new CustomerInfo();
		try {
			customer= cdaoimpl.search(name);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(customer!=null) {
			request.setAttribute("NAME", customer.getNAME());
			request.setAttribute("EMAIL", customer.getEMAIL());
			request.setAttribute("CONTACT_NO", customer.getCONTACT_NO());
			request.setAttribute("CREDITS", customer.getCREDITS());
			
			rd = request.getRequestDispatcher("searchUser.jsp");
		}else {
			request.setAttribute("error", "User Not Found");
			rd = request.getRequestDispatcher("searchUser.jsp");
		}
		rd.forward(request, response);
		
	}
}
