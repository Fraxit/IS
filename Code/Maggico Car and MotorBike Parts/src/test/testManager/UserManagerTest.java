package test.testManager;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;

import model.UserManager;
import bean.UserBean;

public class UserManagerTest {
	
	private UserManager model;
	private Collection<UserBean> users;
	private UserBean user;
	private UserBean userTmp;

	@Before
	public void setUp()
	{
		model = new UserManager();
		users = new ArrayList<UserBean>();
		
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
		model.doRetrieveByKey("Raff93");
	}
	
	@Test
	public void doRetriveByKeyNullTest() throws SQLException
	{
		model.doRetrieveByKey("vefrbtnbty");
	}

	@Test
	public void doRetriveAllTest() throws SQLException
	{
		users = model.doRetrieveAll();
	}
	
	@Test
	public void doSaveTest() throws SQLException
	{
		model.doSave(user);
	}
	
	@Test
	public void doUpdateTest() throws SQLException
	{
		model.doUpdate(user);
	}
	
	@Test
	public void doDeleteTest() throws SQLException
	{
		model.doDelete(user);
	}
	
	@Test
	public void doDeleteFailTest() throws SQLException
	{
		model.doDelete(userTmp);
	}
	
}
