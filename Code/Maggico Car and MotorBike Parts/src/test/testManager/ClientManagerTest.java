package test.testManager;



import java.sql.SQLException;
import java.util.Collection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import model.ClientManager;
import model.UserManager;
import bean.ClientBean;
import bean.UserBean;

public class ClientManagerTest {

	private Collection<ClientBean> clients;
	
	private ClientBean client;
	private UserBean user;
	
	@Before
	public void setUp() throws SQLException
	{
		client = new ClientBean();
		user = new UserBean();
		
		user.setUsername("Michelino93");
		user.setEmail("MicheleCoscione@gmail.com");
		user.setPassword("Password123");
		
		client.setUsername("Michelino93");
		client.setEmail("MicheleCoscione@gmail.com");
		client.setPassword("Password123");
		
		UserManager.doSave(user);
		
		client.setUsername("Michelino93");
		client.setIban("IT54f7564758697057684536457");
	}
	
	@Test
	public void doRetriveByKeyTest() throws SQLException {
		ClientManager.doRetrieveByKey("Raff93");
	}
	
	@Test
	public void doRetriveByKeyNullTest() throws SQLException {
		ClientManager.doRetrieveByKey("jwhvfeyud");
	}

	@Test
	public void doRetriveAllTest() throws SQLException {
		clients = ClientManager.doRetrieveAll();
	}
	
	@Test
	public void doDeleteTest() throws SQLException {
		ClientManager.doSave(client);
	}
	
	@Test
	public void doUpdateTest() throws SQLException {
		ClientManager.doUpdate(client);
	}
	
	@After
	public void reset() throws SQLException
	{
		UserManager.doDelete(user);
	}
}
