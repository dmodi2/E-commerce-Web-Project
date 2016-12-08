package controller;

import bean.Accessory;
import bean.Product;
import bean.User;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.MySQLDataStoreUtilities;

public class HomeServlet extends HttpServlet {

	private static final long serialVersionUID = 102831973239L;
	int userid = 1000;


	public HomeServlet() {
		super();

	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("value").equals("logout")){
			
			request.getSession().invalidate();
			response.sendRedirect("HomeServlet?value=displayHome");
		}
		if(request.getParameter("value").equals("displayHome")){
			
			response.setContentType("text/html");
			java.io.PrintWriter out = response.getWriter();
			
			out.println("<!doctype html>" +
					"<html>" +
					"<head>" +
					"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />" +
					"<title>BestDeals</title>" +
					"<link rel=\"stylesheet\" href=\"styles.css\" type=\"text/css\" />" +
					"<!--[if lt IE 9]>" +
					"<script src=\"http://html5shiv.googlecode.com/svn/trunk/html5.js\"></script>" +
					"<![endif]-->" +
					"<!--" +
					"spigot, a free CSS web template by ZyPOP (zypopwebtemplates.com/)" +

				"Download: http://zypopwebtemplates.com/" +

					"License: Creative Commons Attribution" +
					"//--><script type=\"text/javascript\" src=\"ajax.js\"></script>" +
					"<style>" +
					"table {" +
					"background: #e6e6e6;" +
					"margin-left: 10%;" +
					"padding: 0;" +
					"font-family: 'Pontano Sans', Arial, Helvetica, sans-serif;" +
					"font-size: 16px;" +
					"color: #555;" +
					"width: 80%;" +
					"overflow: scroll;" +
					"}" +
					"</style>" +
					"</head>" +
					"<body onload=\"init()\">" +
					"<div id=\"container\">" +
					"<header>" +
					"<h1>" +
					"<a href=\"UserHomeServlet?value1=userHome\">Best<span>Deals</span></a>" +
					"</h1>" +
					"<h2>You won't find a better deal anywhere else.</h2>"); 
			out.println("<h5 style=\"float: right; padding-right: 30px;\">" +
						"<a href=\"Registration.html\"> Signup</a>" +
						"</h5>" +
						"<h5 style=\"float: right; padding-right: 30px;\">" +
						"<a href=\"Login.html\"> Login</a>" +
						"</h5>");
			out.print("</header>" +
					"<nav>" +
					"<div style=\"float: left; width: 50%\">"+
						"<ul>" +
							"<li><a href=\"HomeServlet?value=displayHome\">Home</a></li>" +
							"<li><a href=\"DisplayProductServlet?value=displayCategory\">Products</a></li>" +
							"<li><a href=\"#\">Careers</a></li>" +
							"<li><a href=\"#\">Customer Reviews</a></li>" +
						"</ul>" +
					"</div>"+
					"<div style=\"float:left; width:25%; height:30px; margin-top: 25px;\" align=\"right\">"+
						"<label style=\"color: #eee;\">Search BestDeals: &nbsp&nbsp</label>"+
					"</div>"+
					"<div class=\"autofillform\" style=\"float:left; height:30px; margin-top: 25px;\">" +
						"<input type=\"text\" name=\"searchId\" id=\"searchId\" value=\"\" class=\"input\" onkeyup=\"doCompletion()\""+
						"placeholder=\"  Search here..\" autocomplete=\"on\" style=\"height: 25px; font-size: 15px; width: 220px;\"/>"+
						"<div id=\"autocompleteContainer\" style=\"height: auto;\">"+
						"<table id=\"complete-table\" style=\"margin-left:0% ;position: absolute; border-collapse: collapse; background: white; font-size: 14px; width: 222px;\">"+
						"</table>"+
						"</div>"+
					"</div>"+
				"</nav>"+

			"<img class=\"header-image\" src=\"images/BestDealsHome.jpg\" width=\"960\"" +
			"height=\"250\" alt=\"Buildings\" />" +

			"<div id=\"body\">" +
					"<section id=\"content\">");
			
			//Print 'DealMatch' content here.
			DealMatchesUtilitiesServlet dealMatch = new DealMatchesUtilitiesServlet();
			dealMatch.doGet(request, response);
			/*out.println("<article>"+


			"<h2>Welcome to BestDeals - Start saving now.</h2>"+

			"<p>We offer the best and genuine products in the market at the"+
				"cheapest price possible. We assure you that you will not find a"+
				"cheaper product anywhere else. If you do we will refund your money"+
				"and you can keep the product.</p>"+

			"<p>We sell the following products:</p>"+


			"<ul class=\"styledlist\">"+
				"<li><a href=\"DisplayProductServlet?value=SmartPhone\">Smart phones</a></li>"+
				"<li><a href=\"DisplayProductServlet?value=Tablet\">Tablets</a></li>"+
				"<li><a href=\"DisplayProductServlet?value=Laptop\">Laptops</a></li>"+
				"<li><a href=\"DisplayProductServlet?value=TV\">TV</a></li>"+
			"</ul>"+

			"<a href=\"DisplayProductServlet?value=displayCategory\" class=\"button\">Shop"+
				"Now</a> <a href=\"#\" class=\"button\">Feedback</a>"+



		"</article>");*/

			
			out.println("</section>" +

				"<aside class=\"sidebar\">" +

				"<ul>" +
				"<li>" +
				"<h4>Categories</h4>" +
				"<ul>" +
				"<li><a href=\"DisplayProductServlet?value=Laptop\">Laptops</a></li>" +
				"<li><a href=\"DisplayProductServlet?value=Tablet\">Tablets</a></li>" +
				"<li><a href=\"DisplayProductServlet?value=SmartPhone\">Smart Phones</a></li>" +
				"<li><a href=\"DisplayProductServlet?value=TV\">TV</a></li>" +
				"<li><a href=\"DisplayProductServlet?value=accessories\">Accessories</a></li>"+
				"<li><a href=\"TrendingServlet?value=viewTrending\">Trending</a></li>" +
				"</ul>" +
				"</li>" +

				"<li>" +
				"<h4>About us</h4>" +
				"<ul>" +
				"<li class=\"text\">" +
				"<p style=\"margin: 0;\">We are the second-largest discount" +
				"retailer in the United States, behind Walmart, and is a" +
				"component of the S&P 500 Index. Founded by George Dayton and" +
				"headquartered in Minneapolis, Minnesota, the company was" +
				"originally named Goodfellow Dry Goods in June 1902 before being" +
				"renamed the Dayton\'s Dry Goods Company in 1903 and later the" +
				"Dayton Company in 1910.</p>" +
				"</li>" +
				"</ul>" +
				"</li>" +


				"<li>" +
				"<h4>Helpful Links</h4>" +
				"<ul>" +
				"<li><a" +
				"href=\"http://www.xbox.com/en-US/xbox-one-s?&ocid=PORTALS_SEM_google_xbox%20one&cid=PORTALS_SEM_google_xbox%20one\"" +
				"title=\"premium templates\">Microsoft Xbox OneS</a></li>" +
				"<li><a href=\"http://www.bestbuy.com/\" title=\"web hosting\">Best" +
				"Buy</a></li>" +
				"<li><a href=\"https://www.playstation.com/en-us/explore/ps4/\"" +
				"title=\"Tux Themes\">Sony PS4</a></li>" +
				"</ul>" +
				"</li>" +

				"</ul>" +

				"</aside>" +
				"<div class=\"clear\"></div>" +
				"</div>" +
				"<footer>" +
				"<div class=\"footer-content\">" +
				"<ul>" +
				"<li><h4>About Us</h4></li>" +
				"</ul>" +

				"<ul>" +
				"<li><h4>Contact Us</h4></li>" +
				"</ul>" +

				"<ul class=\"endfooter\">" +
				"<li><h4>Careers</h4></li>" +
				"</ul>" +

				"<div class=\"clear\"></div>" +
				"</div>" +
				"<div class=\"footer-bottom\">" +
				"<p>" +
				"<a href=\"http://web.iit.edu/\">This website was built at IIT.</a> by" +
				"Dhruvit Modi" +
				"</p>" +
				"</div>" +
				"</footer>" +
				"</div>" +
				"</body>" +
					"</html>");

			out.close();
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		/**
		 * User Registration: Get user registration details and save it.
		 */
		HttpSession userSession;
		MySQLDataStoreUtilities mySql;

		if(request.getParameter("form").equals("register") || request.getParameter("form").equals("custregister")){

			User user;
			mySql = new MySQLDataStoreUtilities();

			try{

				String username = request.getParameter("username");
				String email = request.getParameter("email");
				String address = request.getParameter("address");
				String dob = request.getParameter("dob");
				String pass = request.getParameter("password");
				user = new User(username, email, address, dob, userid++, pass, 0);
				
				boolean ret = mySql.addUserToDB(user);
				if(ret)
					System.out.println("User " + user.getUsername() + " added to Mysql DB.");
				else
					System.out.println("User " + user.getUsername() + " cannot be added to Mysql DB.");

			}catch(Exception e){
				e.printStackTrace();
			}

			// Redirect page to registration successfully.
			if(request.getParameter("form").equals("register"))
				response.sendRedirect("RegistrationSuccess.html");
			if(request.getParameter("form").equals("custregister"))
				response.sendRedirect("CustRegistrationSuccess.html");
		}

		/**
		 * Authenticate login and re-direct to user home.
		 */
		if(request.getParameter("form").equals("login")){

			String username = request.getParameter("uname");
			String pwd = request.getParameter("psw");
			User user;
			mySql = new MySQLDataStoreUtilities();
			user = mySql.getUserDataFromDB(username);
			
			if(username.equals("Shobhit") && pwd.equals("manager")){

				response.sendRedirect("ManagerHome.html");

			}
			else if(username.equals("Sahil") && pwd.equals("sales")){

				response.sendRedirect("SalesmanHome.html");

			}
			else if(user == null){
				//prompt to register.
				System.out.println("User not found");
			}
			else if(user.getPass().equals(pwd)){
				
				//continue to user home
				System.out.println("User verified");
				UserHomeServlet uHome = new UserHomeServlet();
				userSession = request.getSession(true);
				userSession.setAttribute("userData", user);
				uHome.doPost(request, response);
			}
			else{
				//incorrect pwd.
				System.out.println("password incorrect");
			}

		}

		/**
		 * Get product detials from html form.
		 */
		if(request.getParameter("form").equals("addProduct")){

			Product prod;
			String accessorry_list = "";
			mySql = new MySQLDataStoreUtilities();

			String category = request.getParameter("category");
			String make = request.getParameter("retailer");
			String model = request.getParameter("model");
			String image_path = request.getParameter("image");
			float price = Float.parseFloat(request.getParameter("price"));
			int discount = Integer.parseInt(request.getParameter("discount"));
			String condition = request.getParameter("condition");
			float rating = Float.parseFloat(request.getParameter("rating"));

			if(request.getParameterValues("accessories") != null){
				System.out.println("Accessories there");
				String[] al = request.getParameterValues("accessories");
				for(int i = 0; i < al.length; i++){
					accessorry_list = accessorry_list + al[i] + " ";			}
			}
			prod =  new Product(category, make, model, image_path, price, discount, condition, rating, accessorry_list);
			boolean ret = mySql.addProductToDB(prod);
			
			if(ret)
				System.out.println("Product successfully added to DB.");
			else
				System.out.println("Product can not be added to DB.");

			// Redirect page to product successfully added page.
			response.sendRedirect("ManagerServlet?value=addProduct");
		}
	}

}

