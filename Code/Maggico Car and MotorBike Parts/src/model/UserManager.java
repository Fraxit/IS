package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import bean.UserBean;
import connection.DriverManagerConnectionPool;

public class UserManager {

	private static UserManager sing = null;

	private UserManager() {}

	public static synchronized UserManager getVerifyInput()
	{
		if(sing == null)
		{
			sing = new UserManager();
		}
		return sing;	
	}
	
	public static UserBean doRetrieveByKey(String username) throws SQLException 
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		UserBean bean = new UserBean();

		String selectSQL= "SELECT * FROM utente where username = ?";
		try 
		{
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, username );

			System.out.println("doRetrieveByKey: " + preparedStatement.toString()); 
			ResultSet rs = preparedStatement.executeQuery();

			if(rs.next()) 
			{
				bean.setUsername(rs.getString("username"));
				bean.setPassword(rs.getString("password"));
				bean.setEmail(rs.getString("email"));
				return bean;
			}
			else 
			{
				return null;
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
	}


	public static Collection<UserBean> doRetrieveAll() throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<UserBean> users = new LinkedList<UserBean>();

		String selectSQL = "SELECT * FROM utente";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			System.out.println("doRetrieveAll: " + preparedStatement.toString()); 
			ResultSet rs = preparedStatement.executeQuery();

			while(rs.next()) {
				UserBean bean = new UserBean();
				bean.setUsername(rs.getString("username"));
				bean.setPassword(rs.getString("password"));
				bean.setEmail(rs.getString("email"));

				users.add(bean);
			}

		} finally {
			try {
					preparedStatement.close();
			}finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}

		return users;

	}


	public static void doSave(UserBean user) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL="INSERT INTO utente " +
				" (username, password, email) " +
				" VALUES (?, ?, ?)";

		try{
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);

			preparedStatement.setString(1,user.getUsername());
			preparedStatement.setString(2,user.getPassword());
			preparedStatement.setString(3,user.getEmail());

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


	public static void doUpdate(UserBean user) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String updateSQL="UPDATE utente SET" +
				" username = ?, password = ?, email = ? " +
				"WHERE username = ? ";


		try{
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(updateSQL);

			preparedStatement.setString(1,user.getUsername());
			preparedStatement.setString(2,user.getPassword());
			preparedStatement.setString(3,user.getEmail());
			preparedStatement.setString(4,user.getUsername());

			System.out.println("doUpdate: " + preparedStatement.toString());
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


	public static boolean doDelete(UserBean user) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String deleteSQL ="DELETE FROM utente WHERE username = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setString(1, user.getUsername());

			System.out.println("dodelete: " + preparedStatement.toString());
			preparedStatement.executeUpdate();

			connection.commit();

		} finally {
			try {
					preparedStatement.close();
			}finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}

		return true;
	}

}
