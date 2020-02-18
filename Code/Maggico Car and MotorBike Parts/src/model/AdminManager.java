package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.AdminBean;
import connection.DriverManagerConnectionPool;

public class AdminManager  {

	private static AdminManager sing = null;

	private AdminManager(){}
	
	public static synchronized AdminManager getVerifyInput()
	{
		if(sing == null)
		{
			sing = new AdminManager();
		}
		return sing;	
	}
	
	public static boolean isAdmin(String username) throws SQLException 
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;


		String selectSQL= "SELECT * FROM admin WHERE Utente_username = ?";
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, username );

			System.out.println("doRetrieveByKey: " + preparedStatement.toString()); 
			ResultSet rs = preparedStatement.executeQuery();

			if(rs.next()) 
			{
				System.out.println("sono nell'if ");
				return true;
			}

		}
		finally 
		{
			try
			{
				preparedStatement.close();
			} 
			finally 
			{
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return false;

	}


	public static void doSave(AdminBean admin) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL="INSERT INTO admin " +
				" (Utente_username) " +
				" VALUES (?)";

		try{
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);

			preparedStatement.setString(1,admin.getUsername());

			System.out.println("doSave: " + preparedStatement.toString());
			preparedStatement.executeUpdate();

			connection.commit();

		} finally {
			try {
					preparedStatement.close();
			}finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}

	}


}
