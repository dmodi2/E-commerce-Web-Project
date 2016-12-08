package bean;

import java.io.Serializable;

public class Orders implements Serializable{
	
	private String username;
	private Product prod;
	private int quantity;
	private float total; 
	private boolean warranty;
	private String purchaseDate;
	private String deliveryDate;
	private String zipcode; 
	
	public Orders(String username, Product prod, int quantity, float total,
			boolean warranty, String purchaseDate, String deliveryDate, String zipcode) {
		super();
		this.username = username;
		this.prod = prod;
		this.quantity = quantity;
		this.total = total;
		this.warranty = warranty;
		this.purchaseDate = purchaseDate;
		this.deliveryDate = deliveryDate;
		this.zipcode = zipcode;
	}
	
	
	public String getZipcode() {
		return zipcode;
	}


	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}


	public boolean isWarranty() {
		return warranty;
	}
	public void setWarranty(boolean warranty) {
		this.warranty = warranty;
	}
	public Orders() {
	
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Product getProd() {
		return prod;
	}
	public void setProd(Product prod) {
		this.prod = prod;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public float getTotal() {
		return total;
	}
	public void setTotal(float total) {
		this.total = total;
	}
	public String getPurchaseDate() {
		return purchaseDate;
	}
	public void setPurchaseDate(String purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	public String getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

}
