package test.testManager;


import java.sql.SQLException;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

import model.ProductManager;
import bean.ProductBean;

public class ProductManagerTest {

	private volatile ProductBean prodTmp;
	private int idTmp;
	private ArrayList<ProductBean> prodotti;
	
	@Before
	public void setUp() throws SQLException
	{
		prodotti = new ArrayList<>();
		prodotti.addAll(ProductManager.doRetrieveAll());
		
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
		prodotti.addAll(ProductManager.doRetrieveAll());
		
	}
	
	@Test
	public void doSaveTest() throws SQLException
	{
		ProductManager.doSave(prodTmp);
	}
	
	@Test
	public void doRetrieveProdsByNameTest() throws SQLException
	{
		prodotti.addAll(ProductManager.doRetrieveProdsByName("nsjdvbfd", prodotti));
		

	}	

	
	@Test
	public void doUpdateTest() throws SQLException
	{
		ProductManager.doUpdate(prodTmp);
	}
	
	@Test
	public void doRetriveByKeyTest() throws SQLException
	{
		ProductManager.doRetrieveByKey(prodTmp.getId());
	}
	
	@Test
	public void doDeleteTest() throws SQLException
	{
		ProductManager.doDelete(prodTmp);
	}
}
