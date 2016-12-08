package bean;

import java.io.Serializable;

public class Product implements Serializable{
	
	private int productId;
	private String category;
	private String make;
	private String model;
	private String image_path;
	private float price;
	private int discount;
	private String condition;
	private float rating;
	private String accessorry_list;
	
	public Product(String category, String make, String model,
			String image_path, float price, int discount, String condition,
			float rating, String accessorry_list) {
		super();
		this.category = category;
		this.make = make;
		this.model = model;
		this.image_path = image_path;
		this.price = price;
		this.discount = discount;
		this.condition = condition;
		this.rating = rating;
		this.accessorry_list = accessorry_list;
	}

	public Product() {
		
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getImage_path() {
		return image_path;
	}

	public void setImage_path(String image_path) {
		this.image_path = image_path;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	public String getAccessorry_list() {
		return accessorry_list;
	}

	public void setAccessorry_list(String accessorry_list) {
		this.accessorry_list = accessorry_list;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}
	
}
