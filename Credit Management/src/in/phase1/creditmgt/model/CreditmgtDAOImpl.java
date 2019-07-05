package in.phase1.creditmgt.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import in.phase1.creditmgt.bean.CustomerInfo;

public class CreditmgtDAOImpl implements CreditmgtDAO {
	private String query;

	private ResultSet rs = null;
	private Connection connection;
	private PreparedStatement pr = null;
	private String password = null;

	public CreditmgtDAOImpl() {
		try {
			Class.forName(DRIVER);
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public CustomerInfo search(String NAME) throws SQLException {

		query = "SELECT * FROM customer_tbl WHERE NAME=?";
		pr = connection.prepareStatement(query);
		pr.setString(1, NAME);
		rs = pr.executeQuery();
		if (rs.next()) {
			CustomerInfo customer = new CustomerInfo();
			customer.setNAME(rs.getString("NAME"));
			customer.setEMAIL(rs.getString("EMAIL"));
			customer.setCREDITS(rs.getDouble("CREDITS"));
			customer.setCONTACT_NO(rs.getString("CONTACT_NO"));

			return customer;
		} else {
			return null;
		}
	}

	public Boolean searchForTransfer(String From_NAME, String To_NAME) throws SQLException {

		query = "SELECT * FROM customer_tbl WHERE NAME=?";
		pr = connection.prepareStatement(query);
		pr.setString(1, From_NAME);
		rs = pr.executeQuery();
		if (rs.next()) {
			query = "SELECT * FROM customer_tbl WHERE NAME=?";
			pr = connection.prepareStatement(query);
			pr.setString(1, To_NAME);
			rs = pr.executeQuery();
			if (rs.next()) {
				return true;
			}
		}
		return false;
	}

	public Boolean creditTransfer(String From_NAME, String To_NAME, double Amount) throws SQLException {

		boolean ans= updatedecreaseCredit(Amount, From_NAME);
		boolean ansx= false;
		if(ans!=false) {
			ansx= updatedecreaseCredit(Amount, To_NAME);
		}
		if(ansx!=false) {
			query = "INSERT INTO transaction_tbl values(?,?,?)";
			int ans1 = 0;
			pr = connection.prepareStatement(query);
			pr.setString(1, From_NAME);
			pr.setString(2, To_NAME);
			pr.setDouble(3, Amount);
			ans1=pr.executeUpdate();
			if(ans1>0) {
				return true;
			}
		}
		return false;
	}

	public double searchCredit(String NAME) throws SQLException {
		query = "SELECT CREDITS FROM customer_tbl WHERE NAME=?";
		double ans;
		pr = connection.prepareStatement(query);
		pr.setString(1, NAME);
		rs = pr.executeQuery();
		if (rs.next()) {
			ans = rs.getDouble("CREDITS");
			return ans;
		}
		return 0;
	}

	public boolean updateincreaseCredit(double amount,String TONAME) throws SQLException {
		double amount1= searchCredit(TONAME);
		amount1=amount1+amount;
		query = "UPDATE customer_tbl SET CREDITS="+amount1+"WHERE NAME=?";
		pr = connection.prepareStatement(query);
		pr.setString(1, TONAME);
		int ans = 0;
		ans=pr.executeUpdate();
		if(ans!=0) {
			return true;
		}
		return false;
	}

	

	public boolean updatedecreaseCredit(double amount, String FROMNAME) throws SQLException {
		double amount1= searchCredit(FROMNAME);
		amount1=amount1-amount;
		query = "UPDATE customer_tbl SET CREDITS="+amount1+"WHERE NAME=?";
		
		pr = connection.prepareStatement(query);
		pr.setString(1, FROMNAME);
		int ans = 0;
		ans=pr.executeUpdate();
		if(ans!=0) {
			System.out.println(ans);
			return true;
			
		}
		return false;
	}
	
	public boolean compareCredit(double amount,String NAME) throws SQLException {
		query = "SELECT CREDITS FROM customer_tbl WHERE NAME=?";
		double ans;
		pr = connection.prepareStatement(query);
		pr.setString(1, NAME);
		rs = pr.executeQuery();
		if (rs.next()) {
			ans = rs.getDouble("CREDITS");
			if(ans>amount)
			return true;
		}
		return false;
	}

}
