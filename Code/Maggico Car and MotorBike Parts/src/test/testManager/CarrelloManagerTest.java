package test.testManager;



import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import model.CarrelloManager;
import model.ProductManager;
import bean.CartBean;
import bean.ClientBean;
import bean.ProductBean;
import bean.UserBean;

public class CarrelloManagerTest {

	private CartBean cart;

	private CarrelloManager model;
	private ProductManager modelp;
	private UserBean user;
	ClientBean client;
	ProductBean prod;
	
	@Before
	public void setUp() throws SQLException
	{
		model = new CarrelloManager();
		modelp = new ProductManager();
		
		cart = new CartBean();
		user = new UserBean();
		prod = new ProductBean();
		
		user.setUsername("Raff93");
		client = new ClientBean();
		client.setUsername("Raff93");
		
		prod = modelp.doRetrieveByKey(4);
		prod.setQtprod(5);
		
		cart.addItem(prod);
		
	}
	
	@Test
	public void pasteInDbTest() throws SQLException
	{
		model.updateTable(cart, client);
	}
	
	@Test
	public void doRetriveAllTest() throws SQLException
	{
		cart = model.doRetrieveAll(user);
	}
	
	@Test
	public void doSaveTest() throws SQLException
	{
		model.doSave(client, 5, 3);
	}
	
	@Test
	public void doUpdateTest() throws SQLException
	{
		model.doUpdate(5, 2, client);
	}
	
}
