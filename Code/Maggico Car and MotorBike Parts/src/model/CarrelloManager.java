package model;

import java.sql.Connection;

import bean.CartBean;
import bean.ProductBean;
import bean.UserBean;
import connection.DriverManagerConnectionPool;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CarrelloManager {

	
	public CartBean doRetrieveAll(UserBean bean) throws SQLException 
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
	
	
}
