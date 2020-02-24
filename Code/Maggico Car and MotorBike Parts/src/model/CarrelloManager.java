package model;

import java.sql.Connection;

import bean.CartBean;
import bean.ClientBean;
import bean.ProductBean;
import bean.UserBean;
import connection.DriverManagerConnectionPool;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CarrelloManager {

	private static CarrelloManager sing = null;

	private CarrelloManager(){}
	
	public static synchronized CarrelloManager getCarrelloManager()
	{
		if(sing == null)
		{
			sing = new CarrelloManager();
		}
		return sing;	
	}
	
	public static CartBean doRetrieveAll(UserBean bean) throws SQLException 
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		CartBean cart = new CartBean();

		String selectSQL = "SELECT * FROM carrello NATURAL JOIN prodotto WHERE Prodotto_id_prod = id_prod AND Cliente_Utente_username = ?";

		String username = bean.getUsername();
		ArrayList<ProductBean> tmpItems = new ArrayList<ProductBean>();

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, username);


			System.out.println("doRetrieveAll: " + preparedStatement.toString()); 
			ResultSet rs = preparedStatement.executeQuery();

			while(rs.next())
			{
				ProductBean itembean = new ProductBean();
				itembean.setId(rs.getInt("id_prod"));
				itembean.setNome(rs.getString("nome"));
				itembean.setDescr(rs.getString("descr"));
				itembean.setPrezzo(rs.getDouble("prezzo"));
				itembean.setQtprod(rs.getInt("quantita"));
				itembean.setImglink(rs.getString("imglink"));
				tmpItems.add(itembean);
			}

			cart.setItems(tmpItems);

		} finally {
			try {
				preparedStatement.close();
			}finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}

		return cart;
	}


	public static void truncateCart(ClientBean client) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL="DELETE from carrello WHERE cliente_utente_username = ?";

		try{
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, client.getUsername());
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

	public static void pasteInDb(CartBean cart, ClientBean client) throws SQLException
	{

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL="INSERT INTO carrello " +
				" (Cliente_Utente_username, Prodotto_id_prod, quantita) " +
				" VALUES (?, ?, ?)";

		for(ProductBean prod : cart.getItems()) {
			try{
				connection = DriverManagerConnectionPool.getConnection();
				preparedStatement = connection.prepareStatement(insertSQL);

				preparedStatement.setString(1,client.getUsername());
				preparedStatement.setInt(2, prod.getId());
				preparedStatement.setInt(3, prod.getQtprod());

				System.out.println("pasteInDb: " + preparedStatement.toString());
				preparedStatement.executeUpdate();

				connection.commit();

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
	}

	public static void updateTable(CartBean cart, ClientBean client) throws SQLException
	{
		try
		{
			truncateCart(client);
			pasteInDb(cart, client);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	public static void doSave(ClientBean user, int id_prod, int quantita) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL="INSERT INTO carrello " +
				" (Cliente_Utente_username, Prodotto_id_prod, quantita) " +
				" VALUES (?, ?, ?)";

		try{
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);

			preparedStatement.setString(1,user.getUsername());
			preparedStatement.setInt(2,id_prod);
			preparedStatement.setInt(3,quantita);

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


	public static void doUpdate(int id, int quantita, ClientBean client) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String updateSQL="UPDATE carrello SET quantita = ? WHERE Prodotto_id_prod = ? AND Cliente_Utente_username = ? ";


		try{
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(updateSQL);

			preparedStatement.setInt(1, quantita);
			preparedStatement.setInt(2, id);
			preparedStatement.setString(3,client.getUsername());


			System.out.println("doUpdate: " + preparedStatement.toString());
			preparedStatement.executeUpdate();

			connection.commit();

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

}
