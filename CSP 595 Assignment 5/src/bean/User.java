package bean;

import java.io.Serializable;

public class User implements Serializable{
	
	private String username;
	private String email;
	private String address;
	private String dob;
	private int userId;
	private String pass;	
	private int cartSize;
	
	public User(String username, String email, String address, String dob,
			int userId, String pass, int cartSize) {
		
		super();
		this.username = username;
		this.email = email;
		this.address = address;
		this.dob = dob;
		this.userId = userId;
		this.pass = pass;
		this.cartSize = cartSize;
	}
	
	public User() {
	}

	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getDob() {
		return dob;
	}
	
	public void setDob(String dob) {
		this.dob = dob;
	}
	
	public int getUserId() {
		return userId;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public String getPass() {
		return pass;
	}
	
	public void setPass(String pass) {
		this.pass = pass;
	}

	public int getCartSize() {
		return cartSize;
	}

	public void setCartSize(int cartSize) {
		this.cartSize = cartSize;
	}
}
