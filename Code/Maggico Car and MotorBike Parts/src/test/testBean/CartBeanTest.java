package test.testBean;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import bean.CartBean;
import bean.ProductBean;

public class CartBeanTest {

	private ProductBean prod;
	private ProductBean prodTmp;
	private ArrayList<ProductBean> items;
	private CartBean cart;
	
	@Before
	public void setUp()
	{
		prod = new ProductBean();
		prodTmp = new ProductBean();
		items = new ArrayList<ProductBean>();
		cart = new CartBean();
		prod.setId(3);
		prodTmp.setId(6);
		cart.addItem(prod);
		items.add(prod);
		cart.setItems(items);

	}
	
	@Test
	public void deleteItemTest()
	{
		cart.deleteItem(prod);
	}
	
	@Test
	public void deleteItemIfTest()
	{
		cart.deleteItem(prodTmp);
	}
	
	@Test
	public void getItemsTest()
	{
		items = cart.getItems();
	}
	
	@Test
	public void deleteAllTest()
	{
		cart.deleteAll();
	}
	

}
