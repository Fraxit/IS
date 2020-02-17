package test.testManager;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import model.ProductManager;
import bean.ProductBean;

public class ProductManagerTest {

	private ProductManager model;
	private volatile ProductBean prodTmp;
	private int idTmp;
	private ArrayList<ProductBean> prodotti;
	
	@Before
	public void setUp() throws SQLException
	{
		model = new ProductManager();
		prodotti = new ArrayList<>();
		prodotti.addAll(model.doRetrieveAll());
		
		prodTmp = new ProductBean();
		prodTmp.setNome("ProdottoTest");
		prodTmp.setPrezzo(100);
		prodTmp.setDescr("Casco della Momo Design taglia unica");
		prodTmp.setQtprod(300);
		prodTmp.setImglink("https://i.ebayimg.com/images/g/yOoAAOSwJRldwaRc/s-l640.png");
		idTmp = prodotti.size() + 1;
	}

	@Test
	public void doRetriveAllTest() throws SQLException
	{
		prodotti.addAll(model.doRetrieveAll());
		
	}
	
	@Test
	public void doSaveTest() throws SQLException
	{
		model.doSave(prodTmp);
	}
	
	@Test
	public void doRetrieveProdsByNameTest() throws SQLException
	{
		prodotti.addAll(model.doRetrieveProdsByName("nsjdvbfd", prodotti));
		

	}	

	
	@Test
	public void doUpdateTest() throws SQLException
	{
		model.doUpdate(prodTmp);
	}
	
	@Test
	public void doRetriveByKeyTest() throws SQLException
	{
		model.doRetrieveByKey(prodTmp.getId());
	}
	
	@Test
	public void doDeleteTest() throws SQLException
	{
		model.doDelete(prodTmp);
	}
}
