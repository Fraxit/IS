package bean;

import java.util.ArrayList;

public class CartBean {

	private ArrayList<ProductBean> items;
	
	public CartBean() {
		items = new ArrayList<ProductBean>();
	}
	
	public void addItem(ProductBean item) {
		this.items.add(item);
	}
	
	public void setItems(ArrayList<ProductBean> items) {
		this.items = items;
	}
	
	public void deleteItem(ProductBean item) {
		for(ProductBean it : items) {
			if(it.equals(item)) {
				items.remove(it);
				break;
			}
		}
	}
	
	public ArrayList<ProductBean> getItems() {
		return items;
	}
	
	public void deleteAll( ) {
		items.clear();
	}
	
}
