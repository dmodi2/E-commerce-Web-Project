package bean;

import java.io.Serializable;

public class Reviews implements Serializable{
	
	private int productId;
	private String username;
	private String userAge;
	private String userGender;
	private String userOccupation;
	private float reviewRating;
	private String reviewDate;
	private String reviewText;
	
	public Reviews(){}
	
	public Reviews(int productId, String username, String userAge,
			String userGender, String userOccupation, float reviewRating,
			String reviewDate, String reviewText) {
		super();
		this.productId = productId;
		this.username = username;
		this.userAge = userAge;
		this.userGender = userGender;
		this.userOccupation = userOccupation;
		this.reviewRating = reviewRating;
		this.reviewDate = reviewDate;
		this.reviewText = reviewText;
	}
	
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public float getReviewRating() {
		return reviewRating;
	}
	public void setReviewRating(float reviewRating) {
		this.reviewRating = reviewRating;
	}
	public String getReviewDate() {
		return reviewDate;
	}
	public void setReviewDate(String reviewDate) {
		this.reviewDate = reviewDate;
	}
	public String getReviewText() {
		return reviewText;
	}
	public void setReviewText(String reviewText) {
		this.reviewText = reviewText;
	}

	public String getUserAge() {
		return userAge;
	}

	public void setUserAge(String userAge) {
		this.userAge = userAge;
	}

	public String getUserGender() {
		return userGender;
	}

	public void setUserGender(String userGender) {
		this.userGender = userGender;
	}

	public String getUserOccupation() {
		return userOccupation;
	}

	public void setUserOccupation(String userOccupation) {
		this.userOccupation = userOccupation;
	}
	
}
