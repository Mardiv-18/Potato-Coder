package in.phase1.creditmgt.model;

import java.sql.SQLException;

import in.phase1.creditmgt.bean.CustomerInfo;

public interface CreditmgtDAO {
	final String DRIVER= "com.mysql.cj.jdbc.Driver";
	final String DATABASE="creditmgt_db";
	final String URL="jdbc:mysql://db4free.net:3306/creditmgt_db";
	final String USERNAME="marvensizer";
	final String PASSWORD="marvensiser";
	
	public CustomerInfo search(String NAME) throws SQLException;
	public Boolean searchForTransfer(String From_NAME,String To_NAME) throws SQLException;
	public Boolean creditTransfer(String From_NAME,String To_NAME,double Amount) throws SQLException;
	public double searchCredit(String NAME) throws SQLException;
	public boolean updateincreaseCredit(double amount,String NAME) throws SQLException;
	public boolean compareCredit(double amount,String Name) throws SQLException;
	public boolean updatedecreaseCredit(double amount,String FROMNAME) throws SQLException;
}
