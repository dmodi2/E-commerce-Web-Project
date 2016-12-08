package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import bean.Cart;
import bean.Orders;
import bean.Product;
import bean.User;

public class MySQLDataStoreUtilities implements DatabaseConstants{

	private Connection conn = null;
	private Statement stmt = null;
	private String sqlQuery = "";

	public MySQLDataStoreUtilities() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public boolean addUserToDB(User user){

		int flag = 0;
		try{
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			stmt = conn.createStatement();
			sqlQuery = "insert into Users values('" + user.getUsername() + "', '" + user.getEmail() + "', '" + 
					user.getAddress() + "', '" + user.getDob() + "', '" + user.getPass() + "', 0);";
			flag = stmt.executeUpdate(sqlQuery);

			//Close db connection.
			stmt.close();
			conn.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		if(flag == 0)
			return false;
		else
			return true;
	}

	public User getUserDataFromDB(String username) {

		User user = new User();
		try{
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			stmt = conn.createStatement();
			sqlQuery = "select * from Users where username = '" + username + "'";
			ResultSet rs = stmt.executeQuery(sqlQuery);

			//Fetch user data from result set.
			while(rs.next()){
				user.setUsername(rs.getString("username"));
				user.setEmail(rs.getString("email"));
				user.setAddress(rs.getString("address"));
				user.setDob(rs.getString("dob"));
				user.setPass(rs.getString("password"));
				user.setCartSize(rs.getInt("cartSize"));
			}

			//Close db connection.
			stmt.close();
			conn.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return user;
	}

	public boolean addProductToDB(Product prod) {

		int flag = 0;
		try{
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			stmt = conn.createStatement();
			sqlQuery = "insert into product (category, make, model, imagePath, price, discount, cond, rating, accessories, active) "
					+ "values('" + prod.getCategory() + "', '" + prod.getMake() + "', '" + prod.getModel() + "', '"
					+ prod.getImage_path() + "', " + prod.getPrice() + ", " + prod.getDiscount() + ", '"
					+ prod.getCondition() + "', " + prod.getRating() + ", '" + prod.getAccessorry_list() + "', 1)";
			System.out.println(sqlQuery);
			flag = stmt.executeUpdate(sqlQuery);


			//Close db connection.
			stmt.close();
			conn.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		if(flag == 0)
			return false;
		else
			return true;
	}

	public HashMap<Integer, Product> getProductFromDB() {

		HashMap <Integer, Product> prodMap = new HashMap<Integer, Product>();
		Product prod;
		try{
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			stmt = conn.createStatement();
			sqlQuery = "select * from product where active = 1";
			ResultSet rs = stmt.executeQuery(sqlQuery);

			//Fetch user data from result set.
			while(rs.next()){
				
				prod = new Product();
				prod.setAccessorry_list(rs.getString("accessories"));
				prod.setCondition(rs.getString("cond"));
				prod.setDiscount(rs.getInt("discount"));
				prod.setImage_path(rs.getString("imagePath"));
				prod.setMake(rs.getString("make"));
				prod.setModel(rs.getString("model"));
				prod.setPrice(rs.getFloat("price"));
				prod.setProductId(rs.getInt("productId"));
				prod.setRating(rs.getFloat("rating"));
				prod.setCategory(rs.getString("category"));
				System.out.println(prod.getMake() + " " + prod.getModel());
				prodMap.put(prod.getProductId(), prod);
			}

			//Close db connection.
			stmt.close();
			conn.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return prodMap;
	}

	public boolean deleteProductFromDB(int prodId) {

		int flag = 0;
		try{
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			stmt = conn.createStatement();
			sqlQuery = "update product set active = 0 where productId = " + prodId;
			flag = stmt.executeUpdate(sqlQuery);

			//Close db connection.
			stmt.close();
			conn.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		if(flag == 0)
			return false;
		else
			return true;
	}

	public boolean updateProductInDB(Product updateProd) {

		int flag1 = 0;
		boolean flag2 = false;
		try{
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			stmt = conn.createStatement();
			sqlQuery = "update product set active = 0 where productId = " + updateProd.getProductId();
			flag1 = stmt.executeUpdate(sqlQuery);
			flag2 = addProductToDB(updateProd);

			//Close db connection.
			stmt.close();
			conn.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		if((flag1 == 0) || !flag2)
			return false;
		else
			return true;
	}

	public boolean writeProdToCartInDB(User user, Product prodToCart) {

		int flag1 = 0;
		int flag2 = 0;
		try{
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			stmt = conn.createStatement();
			sqlQuery = "insert into userCart values('" + user.getUsername() + "', " + prodToCart.getProductId() + ")";
			flag1 = stmt.executeUpdate(sqlQuery);
			sqlQuery = "update Users set cartSize = cartSize + 1 where username = '" + user.getUsername() + "'";
			flag2 = stmt.executeUpdate(sqlQuery);
			//Close db connection.
			stmt.close();
			conn.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		if((flag1 == 0) || (flag2 == 0))
			return false;
		else
			return true;
	}

	public boolean removeProdToCartInDB(User user, int prodId) {

		int flag1 = 0;
		int flag2 = 0;
		try{
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			stmt = conn.createStatement();
			sqlQuery = "delete from userCart where username = '" + user.getUsername() + "' and productId = " + prodId;
			flag1 = stmt.executeUpdate(sqlQuery);
			sqlQuery = "update Users set cartSize = cartSize - 1 where username = '" + user.getUsername() + "'";
			flag2 = stmt.executeUpdate(sqlQuery);
			//Close db connection.
			stmt.close();
			conn.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		if((flag1 == 0) || (flag2 == 0))
			return false;
		else
			return true;

	}

	public Cart getUserCartFromDB(User user) {

		Cart cart = new Cart();
		Product prod;
		ArrayList<Product> prodList = new ArrayList<Product>();

		cart.setUsername(user.getUsername());
		cart.setCartSize(user.getCartSize());

		try{
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			stmt = conn.createStatement();
			sqlQuery = "select * from product where productId in (select productId from userCart where username = '" + user.getUsername() + "')";
			ResultSet rs = stmt.executeQuery(sqlQuery);

			//Fetch user data from result set.
			while(rs.next()){

				prod = new Product();
				prod.setAccessorry_list(rs.getString("accessories"));
				prod.setCondition(rs.getString("cond"));
				prod.setDiscount(rs.getInt("discount"));
				prod.setImage_path(rs.getString("imagePath"));
				prod.setMake(rs.getString("make"));
				prod.setModel(rs.getString("model"));
				prod.setPrice(rs.getFloat("price"));
				prod.setProductId(rs.getInt("productId"));
				prod.setRating(rs.getFloat("rating"));
				prod.setCategory(rs.getString("category"));

				prodList.add(prod);

			}
			cart.setProdList(prodList);
			//Close db connection.
			stmt.close();
			conn.close();
		}catch(SQLException e){
			e.printStackTrace();
		}		
		return cart;
	}

	public boolean writeOrderDetailsInDb(User user, HashMap<String, Orders> orderMap) {

		int[] flag = new int[orderMap.size()];
		Orders order;
		Product prod;

		try{
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			stmt = conn.createStatement();
			for (Map.Entry<String, Orders> o : orderMap.entrySet()){

				order = new Orders();
				prod = new Product();
				order = o.getValue();
				prod = order.getProd();
				String warranty = "";
				if(order.isWarranty())
					warranty = "Yes";
				else
					warranty = "No";
				sqlQuery = "insert into orders (username, productId, quantity, total, warranty, purchaseDate, deliveryDate, zipcode) "
						+ "values('" + user.getUsername() + "', " + prod.getProductId() + ", " + order.getQuantity() + ", " + 
						order.getTotal() + ", '" + warranty + "', '" + order.getPurchaseDate() + "', '" + order.getDeliveryDate() + 
						"', '" + order.getZipcode() + "')";
				stmt.addBatch(sqlQuery);
			}
			flag = stmt.executeBatch();

			//Close db connection.
			stmt.close();
			conn.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return true;
	}

	public boolean deleteCartFromDB(String username) {

		int flag1 = 0;
		int flag2 = 0;
		try{
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			stmt = conn.createStatement();
			sqlQuery = "delete from userCart where username = '" + username + "'";
			flag1 = stmt.executeUpdate(sqlQuery);
			sqlQuery = "update Users set cartSize = 0 where username = '" + username + "'";
			flag2 = stmt.executeUpdate(sqlQuery);
			//Close db connection.
			stmt.close();
			conn.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		if((flag1 == 0) || (flag2 == 0))
			return false;
		else
			return true;
	}

	public boolean removeOrderFromUserDB(String username, int prodId) {

		int flag = 0;
		try{
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			stmt = conn.createStatement();
			sqlQuery = "delete from orders where username = '" + username + "' and productId = " + prodId;
			flag = stmt.executeUpdate(sqlQuery);

			//Close db connection.
			stmt.close();
			conn.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		if(flag == 0)
			return false;
		else
			return true;

	}

	public ArrayList<String> getAllUsersFromDb() {

		ArrayList<String> userList = new ArrayList<String>();
		try{
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			stmt = conn.createStatement();
			sqlQuery = "select username from Users";
			ResultSet rs = stmt.executeQuery(sqlQuery);

			//Fetch user data from result set.
			while(rs.next()){
				userList.add(rs.getString("username"));
			}

			//Close db connection.
			stmt.close();
			conn.close();
		}catch(SQLException e){
			e.printStackTrace();
		}		
		return userList;
	}

	public HashMap<Integer, Orders> getUserOrderFromDB(String user) {

		HashMap<Integer, Orders> userOders = new HashMap<Integer, Orders>();
		Orders order;
		Product prod;
		try{
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			stmt = conn.createStatement();
			sqlQuery = "select * from orders where username = '" + user + "'";
			ResultSet rs = stmt.executeQuery(sqlQuery);

			//Fetch order data from result set.
			while(rs.next()){

				order = new Orders();
				prod = new Product();
				prod = getProdfromId(rs.getInt("productId"));
				order.setProd(prod);
				order.setUsername(rs.getString("username"));
				order.setQuantity(rs.getInt("quantity"));
				order.setTotal(rs.getInt("total"));
				order.setZipcode(rs.getString("zipcode"));
				if(rs.getString("warranty").equals("Yes"))
					order.setWarranty(true);
				else
					order.setWarranty(false);
				order.setDeliveryDate(rs.getString("deliveryDate"));
				order.setPurchaseDate(rs.getString("purchaseDate"));

				userOders.put(order.getProd().getProductId(), order);
			}

			//Close db connection.
			stmt.close();
			conn.close();
		}catch(SQLException e){
			e.printStackTrace();
		}		
		return userOders;
	}
	
	private boolean addProductsToDB(Product p) {
		
		return true;
	}

	public Product getProdfromId(int prodId) {

		Product prod = new Product();
		try{
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			stmt = conn.createStatement();
			sqlQuery = "select * from product where productId = " + prodId;
			ResultSet rs = stmt.executeQuery(sqlQuery);

			//Fetch user data from result set.
			while(rs.next()){

				prod.setAccessorry_list(rs.getString("accessories"));
				prod.setCondition(rs.getString("cond"));
				prod.setDiscount(rs.getInt("discount"));
				prod.setImage_path(rs.getString("imagePath"));
				prod.setMake(rs.getString("make"));
				prod.setModel(rs.getString("model"));
				prod.setPrice(rs.getFloat("price"));
				prod.setProductId(rs.getInt("productId"));
				prod.setRating(rs.getFloat("rating"));
				prod.setCategory(rs.getString("category"));

			}

			//Close db connection.
			stmt.close();
			conn.close();
		}catch(SQLException e){
			e.printStackTrace();
		}		
		return prod;
	}

	public ArrayList<String> getTrending(String query) {

		ArrayList<String> trendList = new ArrayList<String>();
		try{
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			stmt = conn.createStatement();
			if(query.equals("Query1"))
				sqlQuery = "select zipcode, count(*) as count from orders group by zipcode order by zipcode desc limit 5;";
			else
				sqlQuery = "select make, model from product where productId in (select productId from" +
						"(select productId, count(*) as count from orders group by productId)t1 " +
						"order by t1.count desc) limit 5;";
			ResultSet rs = stmt.executeQuery(sqlQuery);

			//Fetch user data from result set.
			while(rs.next()){
				if(query.equals("Query1"))
					trendList.add(rs.getString("zipcode"));
				else
					trendList.add(rs.getString("make") + " " + rs.getString("model"));
			}

			//Close db connection.
			stmt.close();
			conn.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return trendList;
	}

	public ArrayList<String> extraCreditQuery(String query) {
		ArrayList<String> analytics = new ArrayList<String>();

		try{
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			stmt = conn.createStatement();
			if(query.equals("query1")){
				sqlQuery = "select make, model, rating from product;";
				ResultSet rs = stmt.executeQuery(sqlQuery);
				while(rs.next()){			
					analytics.add("Product: " + rs.getString("make") + " " + rs.getString("model") + " Rating: " + Float.toString(rs.getFloat("rating")));
				}
			}
			if(query.equals("query7")){
				System.out.println("In query 7");
				sqlQuery = "select make, model, price, zipcode from (select p.make, p.model, o.zipcode, max(p.price) as price from orders o inner join product p " +  
						   "on o.productId = p.productId group by o.zipcode)t;";
				ResultSet rs = stmt.executeQuery(sqlQuery);
				while(rs.next()){			
					analytics.add("Zipcode: "+ rs.getString("zipcode") + " | Product: " + rs.getString("make") + " " + rs.getString("model") + " Price: " + Integer.toString(rs.getInt("price")));
				}
			}
			//Close db connection.
			stmt.close();
			conn.close();
		}catch(SQLException e){
			e.printStackTrace();
		}		
		return analytics;
	}

	public ArrayList<String> getProductAbove1000() {
		ArrayList<String> prod1000 = new ArrayList<String>();

		try{
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			stmt = conn.createStatement();
			sqlQuery = "select productId from product where price > 1000;";
			ResultSet rs = stmt.executeQuery(sqlQuery);

			while(rs.next()){			
				prod1000.add(Integer.toString(rs.getInt("productId")));
			}

			//Close db connection.
			stmt.close();
			conn.close();
		}catch(SQLException e){
			e.printStackTrace();
		}		
		return prod1000;
	}
	
public boolean insertProductFromXML(ArrayList<Product> prodList){
		
		boolean flag = true;
		System.out.println("From index.html");
		//Delete contents from existing table if any;
		try{
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			stmt = conn.createStatement();
			sqlQuery = "delete from products";
			int i = stmt.executeUpdate(sqlQuery);

			//Close db connection.
			stmt.close();
			conn.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		for(Product p : prodList){
			flag = addProductsToDB(p);
			if(!flag)
				break;
		}
		return flag;
	}
}
