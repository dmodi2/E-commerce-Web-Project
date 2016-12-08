package bean;

import java.io.Serializable;
import java.util.ArrayList;

public class Cart implements Serializable{
	
	private String username;
	private ArrayList<Product> prodList;
	private int cartSize;
	
	public Cart(String username, ArrayList<Product> prodList, int cartSize) {
		super();
		this.username = username;
		this.prodList = prodList;
		this.cartSize = cartSize;
	}
	
	public Cart() {
		this.cartSize = 0;
	}

	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public ArrayList<Product> getProdList() {
		return prodList;
	}
	
	public void setProdList(ArrayList<Product> prodList) {
		this.prodList = prodList;
	}
	
	public int getCartSize() {
		return cartSize;
	}
	
	public void setCartSize(int cartSize) {
		this.cartSize = cartSize;
	}
}
