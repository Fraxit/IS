package test.testManager;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;

import model.UserManager;
import bean.UserBean;

public class UserManagerTest {
	
	private Collection<UserBean> users;
	private UserBean user;
	private UserBean userTmp;
	private UserManager model;
	@Before
	public void setUp()
	{
		users = new ArrayList<UserBean>();
		model = UserManager.getUserManager();
		user = new UserBean();
		user.setUsername("LuigiCrisci");
		user.setEmail("Giggio.crisci@gmail.com");
		user.setPassword("Password1");
		
		userTmp = new UserBean();
		userTmp.setUsername("trfhjryteurhj");
		userTmp.setEmail("hjyugdmy");
		userTmp.setPassword("Passwjngtd");
	}
	
	@Test
	public void doRetriveByKeyTest() throws SQLException
	{
		UserManager.doRetrieveByKey("Raff93");
	}
	
	@Test
	public void doRetriveByKeyNullTest() throws SQLException
	{
		UserManager.doRetrieveByKey("vefrbtnbty");
	}

	@Test
	public void doRetriveAllTest() throws SQLException
	{
		users = UserManager.doRetrieveAll();
	}
	
	@Test
	public void doSaveTest() throws SQLException
	{
		UserManager.doSave(user);
	}
	
	@Test
	public void doUpdateTest() throws SQLException
	{
		UserManager.doUpdate(user);
	}
	
	@Test
	public void doDeleteTest() throws SQLException
	{
		UserManager.doDelete(user);
	}
	
	@Test
	public void doDeleteFailTest() throws SQLException
	{
		UserManager.doDelete(userTmp);
	}
	
}
