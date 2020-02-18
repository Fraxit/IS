package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

import bean.ProductBean;
import connection.DriverManagerConnectionPool;

public class ProductManager {

	private static ProductManager sing = null;

	private ProductManager() {}

	public static synchronized ProductManager getVerifyInput()
	{
		if(sing == null)
		{
			sing = new ProductManager();
		}
		return sing;	
	}
	
	public static ProductBean doRetrieveByKey(int code) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ProductBean bean = new ProductBean();

		String selectSQL= "SELECT * FROM prodotto WHERE id_prod = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, code );

			System.out.println("doRetrieveByKey: " + preparedStatement.toString()); 
			ResultSet rs = preparedStatement.executeQuery();

			while(rs.next()) {
				bean.setId(rs.getInt("id_prod"));
				bean.setNome(rs.getString("nome"));
				bean.setDescr(rs.getString("descr"));
				bean.setPrezzo(rs.getDouble("prezzo"));
				bean.setQtprod(rs.getInt("qt_prod"));
				bean.setImglink(rs.getString("imglink"));
			}

		} finally {
			try {
				if(preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}

		return bean;
	}
	
	public static ProductBean doRetrieveByProduct(String nome) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ProductBean bean = new ProductBean();

		String selectSQL= "SELECT * FROM prodotto WHERE nome = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, nome );

			System.out.println("doRetrieveByKey: " + preparedStatement.toString()); 
			ResultSet rs = preparedStatement.executeQuery();

			while(rs.next()) {
				bean.setId(rs.getInt("id_prod"));
				bean.setNome(rs.getString("nome"));
				bean.setDescr(rs.getString("descr"));
				bean.setPrezzo(rs.getDouble("prezzo"));
				bean.setQtprod(rs.getInt("qt_prod"));
				bean.setImglink(rs.getString("imglink"));
			}

		} finally {
			try {
				if(preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}

		return bean;
	}

	public static Collection<ProductBean> doRetrieveAll() throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<ProductBean> products = new LinkedList<ProductBean>();

		String selectSQL = "SELECT * FROM prodotto";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			System.out.println("doRetrieveAll: " + preparedStatement.toString()); 
			ResultSet rs = preparedStatement.executeQuery();

			while(rs.next()) {
				ProductBean bean = new ProductBean();
				bean.setId(rs.getInt("id_prod"));
				bean.setNome(rs.getString("nome"));
				bean.setDescr(rs.getString("descr"));
				bean.setPrezzo(rs.getDouble("prezzo"));
				bean.setQtprod(rs.getInt("qt_prod"));
				bean.setImglink(rs.getString("imglink"));

				products.add(bean);
			}

		} finally {
			try {
					preparedStatement.close();
			}finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}

		return products;
	}


	public static void doSave(ProductBean product) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL="INSERT INTO prodotto " +
				" (nome, descr, prezzo, qt_prod, imglink) " +
				" VALUES (?, ?, ?, ?, ?)";

		try{
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);

			preparedStatement.setString(1,product.getNome());
			preparedStatement.setString(2,product.getDescr());
			preparedStatement.setDouble(3,product.getPrezzo());
			preparedStatement.setInt(4,product.getQtprod());
			preparedStatement.setString(5,product.getImglink());

			System.out.println("doSave: " + preparedStatement.toString());
			preparedStatement.executeUpdate();

			connection.commit();

		} finally {
			try {
				if(preparedStatement != null)
					preparedStatement.close();
			}finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}

	}


	public static void doUpdate(ProductBean product) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String updateSQL="UPDATE prodotto SET" +
				" nome = ?, descr = ?, prezzo = ?, qt_prod = ?, imglink = ? " +
				"WHERE id_prod = ? ";

		try{
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(updateSQL);

			preparedStatement.setString(1,product.getNome());
			preparedStatement.setString(2,product.getDescr());
			preparedStatement.setDouble(3,product.getPrezzo());
			preparedStatement.setInt(4,product.getQtprod());
			preparedStatement.setString(5,product.getImglink());
			preparedStatement.setInt(6,product.getId());


			System.out.println("doUpdate: " + preparedStatement.toString());
			preparedStatement.executeUpdate();

			connection.commit();

		} finally {
			try {
				if(preparedStatement != null)
					preparedStatement.close();
			}finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
	}


	public static boolean doDelete(ProductBean product) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;
		String deleteSQL ="DELETE FROM prodotto WHERE id_prod = ? OR nome = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setInt(1, product.getId());
			preparedStatement.setString(2, product.getNome());


			System.out.println("doDelete: " + preparedStatement.toString());
			result = preparedStatement.executeUpdate();

			connection.commit();

		} finally {
			try {
					preparedStatement.close();
			}finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}

		return (result != 0);

	}


	public static Collection<ProductBean> doRetrieveProdsByName(String search, Collection<ProductBean> prodotti) throws SQLException {
		Collection<ProductBean> tmpProd = new ArrayList<ProductBean>();
		for(ProductBean tmp : prodotti)
		{
			System.out.println(tmp.toString() + " " + search);
			System.out.println(tmp.getNome().toLowerCase().contains(".*"+search.toLowerCase()+".*"));
			if(tmp.getNome().toLowerCase().contains(search.toLowerCase()))
			{
				tmpProd.add(tmp);
				System.out.println(tmp.toString() + " " + search);
			}
		}
		if(tmpProd.isEmpty())
		{
			System.out.println("é Vuoto");
			System.out.println(search);

			tmpProd = doRetrieveAll();
		}

		return tmpProd;
	}


}