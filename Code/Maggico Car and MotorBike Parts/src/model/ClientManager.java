package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import bean.ClientBean;
import connection.DriverManagerConnectionPool;

public class ClientManager {


	public ClientBean doRetrieveByKey(String username) throws SQLException 
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ClientBean bean;

		String selectSQL= "SELECT * FROM utente NATURAL JOIN cliente where username = Utente_username AND username = ?";

		try 
		{
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, username );

			System.out.println("doRetrieveByKey: " + preparedStatement.toString()); 
			ResultSet rs = preparedStatement.executeQuery();


			if(rs.next())
			{
				bean = new ClientBean();
				bean.setUsername(rs.getString("username"));
				bean.setPassword(rs.getString("password"));
				bean.setEmail(rs.getString("email"));
				bean.setIban(rs.getString("iban"));
			}
			else
			{
				return null;
			}
			System.out.println(bean.toString());

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
		return bean;
	}


	public Collection<ClientBean> doRetrieveAll() throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<ClientBean> clients = new LinkedList<ClientBean>();

		String selectSQL = "SELECT * FROM cliente NATURAL JOIN utente WHERE Utente_username = username";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			System.out.println("doRetrieveAll: " + preparedStatement.toString()); 
			ResultSet rs = preparedStatement.executeQuery();

			while(rs.next()) {
				ClientBean bean = new ClientBean();
				bean.setUsername(rs.getString("Utente_username"));
				bean.setPassword(rs.getString("password"));
				bean.setEmail(rs.getString("email"));
				bean.setIban(rs.getString("iban"));

				clients.add(bean);
			}

		} finally {
			try {
					preparedStatement.close();
			}finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}

		return clients;

	}


	public void doSave(ClientBean client) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertClienteSQL="INSERT INTO cliente " +
				" (Utente_username, iban) " +
				" VALUES (?, ?)";

		try{
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertClienteSQL);

			preparedStatement.setString(1,client.getUsername());
			preparedStatement.setString(2,client.getIban());

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


	public void doUpdate(ClientBean client) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String updateSQL="UPDATE utente SET" +
				" username = ?, password = ?, email = ? " +
				"WHERE username = ? ";
		try{
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(updateSQL);

			preparedStatement.setString(1,client.getUsername());
			preparedStatement.setString(2,client.getPassword());
			preparedStatement.setString(3,client.getEmail());
			preparedStatement.setString(4,client.getUsername());

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

}
