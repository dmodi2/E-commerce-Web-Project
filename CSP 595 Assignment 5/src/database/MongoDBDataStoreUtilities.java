package database;

import java.util.ArrayList;

import com.mongodb.AggregationOutput;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

import bean.Product;
import bean.Reviews;


public class MongoDBDataStoreUtilities {

	static DBCollection prodReviews;
	static MongoClient mongo;

	public static void getConnection(){

		mongo = new MongoClient("localhost", 27017);
		DB db = mongo.getDB("ProductReviews");
		prodReviews= db.getCollection("prodReviews");

	}

	public static void insertReview(Reviews review)
	{
		try{
			getConnection();
			BasicDBObject doc = new BasicDBObject("title", "prodReviews").
					append("productId", review.getProductId()).
					append("retailerName", "BestDeals").
					append("retailerCity", "Chicago").
					append("retailerState", "IL").
					append("retailerZip", "60616").
					append("userId", review.getUsername()).
					append("userAge", review.getUserAge()).
					append("userGender", review.getUserGender()).
					append("userOccupation", review.getUserOccupation()).
					append("reviewRating", review.getReviewRating()).
					append("reviewDate", review.getReviewDate()).
					append("reviewText", review.getReviewText());
			prodReviews.insert(doc);
			mongo.close();
		}catch(Exception e){
			e.printStackTrace();
		}

	}

	//Wrapper class for inserReview
	public void addProductReviewToCollection(Reviews review) {
		insertReview(review);
	}

	public ArrayList<Reviews> getProductReviewFromCollection(int prodId) {

		ArrayList<Reviews> reviewList = new ArrayList<Reviews>();
		Reviews review;
		BasicDBObject searchQuery = new BasicDBObject();

		try{
			getConnection();
			searchQuery.put("productId", prodId);
			DBCursor cursor = MongoDBDataStoreUtilities.prodReviews.find(searchQuery);

			while(cursor.hasNext()){

				BasicDBObject obj = (BasicDBObject) cursor.next();
				review = new Reviews();
				review.setProductId(prodId);
				review.setReviewDate(obj.getString("reviewDate"));
				review.setReviewRating(obj.getLong("reviewRating"));
				review.setReviewText(obj.getString("reviewText"));
				review.setUserAge(obj.getString("userAge"));
				review.setUserGender(obj.getString("userGender"));
				review.setUsername(obj.getString("userId"));
				review.setUserOccupation(obj.getString("userOccupation"));

				reviewList.add(review);
				mongo.close();
			}
		}catch(Exception e){
			e.printStackTrace();
		}

		return reviewList;
	}

	public ArrayList<String> getTrending() {

		MySQLDataStoreUtilities mySql = new MySQLDataStoreUtilities();
		Product prod;
		ArrayList<String> trendList = new ArrayList<String>();
		try{
			getConnection();
			DBObject groupFields = new BasicDBObject("_id", 0);
			groupFields.put("_id", "$productId");
			groupFields.put("Average Rating", new BasicDBObject("$avg", "$reviewRating"));
			DBObject group = new BasicDBObject("$group", groupFields);

			DBObject sort = new BasicDBObject();
			sort.put("Average Rating",-1);
			DBObject limit=new BasicDBObject();
			DBObject orderby=new BasicDBObject();
			orderby=new BasicDBObject("$sort",sort);
			limit=new BasicDBObject("$limit",5);

			AggregationOutput aggregate = prodReviews.aggregate(group, orderby, limit);

			for(DBObject obj : aggregate.results()){
				BasicDBObject bobj = (BasicDBObject)obj;
				String id = bobj.getString("_id");
				prod = new Product();
				prod = mySql.getProdfromId(Integer.parseInt(id));
				trendList.add(prod.getMake() + " " + prod.getModel() + " [Average Rating: " + bobj.getString("Average Rating") + "]");
			}
			mongo.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return trendList;
	}

	public ArrayList<String> extraCreditQuery(String query) {

		ArrayList<String> analytics = new ArrayList<String>();
		MySQLDataStoreUtilities mySql = new MySQLDataStoreUtilities();
		Product prod;
		getConnection();

		if(query.equals("query2")){
			BasicDBObject gtQuery = new BasicDBObject();
			gtQuery.put("reviewRating", new BasicDBObject("$gt", 7));
			DBCursor cursor = MongoDBDataStoreUtilities.prodReviews.find(gtQuery);
			while(cursor.hasNext()){
				BasicDBObject obj = (BasicDBObject) cursor.next();
				String id = obj.getString("productId");
				prod = new Product();
				prod = mySql.getProdfromId(Integer.parseInt(id));
				analytics.add("UserID: " + obj.getString("userId") + " Product: " + prod.getMake() + " " + prod.getModel() + " Review Rating: " + obj.getString("reviewRating"));
			}
		}
		if(query.equals("query3")){
			
			System.out.println("in query 3");
			ArrayList<String> prod1000 = new ArrayList<String>();
			prod1000 = mySql.getProductAbove1000();
			BasicDBObject gtQuery = new BasicDBObject();
			gtQuery.put("reviewRating", 10);
			DBCursor cursor = MongoDBDataStoreUtilities.prodReviews.find(gtQuery);
			while(cursor.hasNext()){
				BasicDBObject obj = (BasicDBObject) cursor.next();
				if(prod1000.contains(obj.getString("productId"))){
					String id = obj.getString("productId");
					prod = new Product();
					prod = mySql.getProdfromId(Integer.parseInt(id));
					analytics.add(" Product: " + prod.getMake() + " " + prod.getModel());
				}
			}
		}


		return analytics;
	}
}
