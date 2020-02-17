package test.testBean;


import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import bean.UserBean;

public class UserBeanTest {

	private UserBean user;
	private String tmp;
	
	@Before
	public void setUp()
	{
		user = new UserBean();
		user.setEmail("ciro@gmail.com");
		user.setPassword("Password1");
		user.setUsername("Ciro");
	}
	
	@Test
	public void getUsernameTest()
	{
		assertEquals("Ciro", user.getUsername());
	}
	
	@Test
	public void getPasswordTest()
	{
		assertEquals("Password1", user.getPassword());
	}
	
	@Test
	public void getEmailTest()
	{
		assertEquals("ciro@gmail.com", user.getEmail());
	}
	
	@Test
	public void toStringTest()
	{
		assertEquals("[Username: Ciro][email: ciro@gmail.com][password: Password1]",user.toString());
	}
	
	

}
