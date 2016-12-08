package controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.MongoDBDataStoreUtilities;
import database.MySQLDataStoreUtilities;
import bean.Product;
import bean.Reviews;
import bean.User;

@WebServlet("/ReviewServlet")
public class ReviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ReviewServlet() {
		super();

	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("value1").equals("addProductReviewSuccess")){

			
			User user = new User();
			user = (User)request.getSession().getAttribute("userData");
			
			response.setContentType("text/html");
			java.io.PrintWriter out = response.getWriter();

			/**
			 * Product Review Added placed page.
			 */

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
						"</head>" +
						"<body onload=\"init()\">" +
						"<div id=\"container\">" +
						"<header>" +
						"<h1>" +
						"<a href=\"UserHomeServlet?value=userHome\">Best<span>Deals</span></a>" +
						"</h1>" +
						"<h2>You won't find a better deal anywhere else.</h2>" +
						"<h5 style=\"float: right; padding-right: 30px;\">" +
						"<a href=\"HomeServlet?value=logout\"> Logout</a>" +
						"</h5>" +
						"<h5 style=\"float: right; padding-right: 30px;\">" +
						"<a href='CustomerOrderServlet?value=showOrders'> My Orders</a>" +
						"</h5>" +
						"<h5 style=\"float: right; padding-right: 30px;\">" +
						"<a href=\"CartServlet?value1=viewCart\"> Cart(" + user.getCartSize() + ")</a>" +
						"</h5>" +
						"<h5 style=\"float: right; padding-right: 30px;\">" +
						" Hello, " + user.getUsername() +
						"</h5>" +
						"</header>" +
						"<nav>" +
						"<div style=\"float: left; width: 50%\">"+
							"<ul>" +
								"<li><a href=\"UserHomeServlet?value1=userHome\">Home</a></li>" +
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
					"<section id=\"content\">" +
					"<div align=\"center\"><h3>Product review has been posted succefully.</h3></div>" +
					"</section>" +

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
		
		if(request.getParameter("value1").equals("showProdReviews")){

			User user = new User();
			MySQLDataStoreUtilities mySql = new MySQLDataStoreUtilities();
			MongoDBDataStoreUtilities mongo = new MongoDBDataStoreUtilities();
			ArrayList<Reviews> reviewList = new ArrayList<Reviews>();
			
			user = (User) request.getSession().getAttribute("userData");
			int prodId = Integer.parseInt(request.getParameter("value2"));
			Product prod = new Product();
			prod = mySql.getProdfromId(prodId);
			System.out.println(prodId);
			reviewList = mongo.getProductReviewFromCollection(prodId);

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
						"<style>"+
						"table {"+
						"	border: 3px solid #f1f1f1;"+
						"	width: 60%;"+
						"	margin: 0 auto;"+
						"}"+
						"</style>"+
						"</head>" +
						"<body onload=\"init()\">" +
						"<div id=\"container\">" +
						"<header>" +
						"<h1>" +
						"<a href=\"HomeServlet?value=displayHome\">Best<span>Deals</span></a>" +
						"</h1>" +
					"<h2>You won't find a better deal anywhere else.</h2>"); 
			if(request.getSession().getAttribute("userData") != null){
				System.out.println("Session there");
				out.print("<h5 style=\"float: right; padding-right: 30px;\">" +
						"<a href=\"HomeServlet?value=logout\"> Logout</a>" +
						"</h5>" +
						"<h5 style=\"float: right; padding-right: 30px;\">" +
						"<a href='CustomerOrderServlet?value=showOrders'> My Orders</a>" +
						"</h5>" +
						"<h5 style=\"float: right; padding-right: 30px;\">" +
						"<a href=\"CartServlet?value1=viewCart\"> Cart(" + user.getCartSize() + ")</a>" +
						"</h5>" +
						"<h5 style=\"float: right; padding-right: 30px;\">" +
						" Hello, " + user.getUsername() +
						"</h5>");
			}
			else{
				out.println("<h5 style=\"float: right; padding-right: 30px;\">" +
						"<a href=\"Registration.html\"> Signup</a>" +
						"</h5>" +
						"<h5 style=\"float: right; padding-right: 30px;\">" +
						"<a href=\"Login.html\"> Login</a>" +
						"</h5>");
			}
			out.println("</header>" +
					"<nav>" +
					"<div style=\"float: left; width: 50%\">"+
						"<ul>" +
							"<li><a href=\"UserHomeServlet?value1=userHome\">Home</a></li>" +
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
					"<section id=\"content\">" +
					"<div align=\"center\">" +
					"<h2>Product Reviews for " + prod.getMake() + " " + prod.getModel() + "</h2><br><br>");
					String rebate = "No";
					if(prod.getDiscount() > 0)
						rebate = "Yes";
					out.println("<table cellspacing=\"0\">"+
							"<tr><td>Product Model Name: </td><td>" + prod.getMake() + " " + prod.getModel() + "</td></tr>" +
							"<tr><td>Product Category: </td><td>" + prod.getCategory() + "</td></tr>" +
							"<tr><td>Product Price: </td><td>" + prod.getPrice() + "</td></tr>" +
							"<tr><td>Retailer Name: </td><td>BestDeals</td></tr>" +
							"<tr><td>Retailer Zip: </td><td>60616</td></tr>" +
							"<tr><td>Retailer City: </td><td>Chicago</td></tr>" +
							"<tr><td>Retailer State: </td><td>IL</td></tr>" +
							"<tr><td>Manufacturer Name: </td><td>" + prod.getMake() + "</td></tr>" +
							"<tr><td>Manufacturer Rebate: </td><td>" + rebate + "</td></tr></table><br>");
					
					for(Reviews r : reviewList){
						
						out.println("<table cellspacing=\"0\">"+
									"<tr><td>User Id: </td><td>" + r.getUsername() + "</td></tr>" +
									"<tr><td>User Age: </td><td>" + r.getUserAge() + "</td></tr>" +
									"<tr><td>User Genger: </td><td>" + r.getUserGender() + "</td></tr>" +
									"<tr><td>User Occupation: </td><td>" + r.getUserOccupation() + "</td></tr>" +
									"<tr><td>Review Rating: </td><td>" + r.getReviewRating() + "/10</td></tr>" +
									"<tr><td>Review Date: </td><td>" + r.getReviewDate() + "</td></tr>" +
									"<tr><td>Review Text: </td><td>" + r.getReviewText() + "</td></tr></table><br>"
								);
					}
					out.println("<br><a href=\"ReviewServlet?value1=addProdReviews&value2=" + prodId + "\" class=\"button\" >Add Review</a>" +
					"</div>" +
					"</section>" +

					"<aside class=\"sidebar\">" +

					"<ul>" +
					"<li>" +
					"<h4>Categories</h4>" +
					"<ul>" +
					"<li><a href=\"DisplayProductServlet?value=Laptop\">Laptops</a></li>" +
					"<li><a href=\"DisplayProductServlet?value=Tablet\">Tablets</a></li>" +
					"<li><a href=\"DisplayProductServlet?value=SmartPhone\">Smart Phones</a></li>" +
					"<li><a href=\"DisplayProductServlet?value=TV\">TV</a></li>" +
					"<li><a href=\"DisplayProductServlet?value=accessories\">Accessories</a></li>" +
					"<li><a href=\"TrendingServlet?value=viewTrending\">Trending</a></li>" +
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

		if(request.getParameter("value1").equals("addProdReviews")){

			User user = new User();
			user = (User) request.getSession().getAttribute("userData");
			if(request.getSession().getAttribute("userData") == null){
				response.sendRedirect("Login.html");
			}else{
				MySQLDataStoreUtilities mySql = new MySQLDataStoreUtilities();
				Product prod = new Product();
				int prodId = Integer.parseInt(request.getParameter("value2"));
				prod = mySql.getProdfromId(prodId);

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
						"<style>"+
						"form {"+
						"	border: 3px solid #f1f1f1;"+
						"	width: 60%;"+
						"	margin: 0 auto;"+
						"}"+
						
						"input[type=text] {"+
						"	width: 100%;"+
						"	padding: 12px 20px;"+
						"	margin: 8px 0;"+
						"	display: inline-block;"+
						"	border: 1px solid #ccc;"+
						"	box-sizing: border-box;"+
						"}"+

						"button {"+
						"	background-color: #4CAF50;"+
						"	color: white;"+
						"	padding: 14px 20px;"+
						"	margin: 8px 0;"+
						"	border: none;"+
						"	cursor: pointer;"+
						"	width: 100%;"+
						"}"+

						".cancelbtn {"+
						"	width: auto;"+
						"	padding: 10px 18px;"+
						"	background-color: #f44336;"+
						"}"+

						".imgcontainer {"+
						"	text-align: center;"+
						"	margin: 24px 0 12px 0;"+
						"}"+

						"img.avatar {"+
						"	width: 40%;"+
						"	border-radius: 50%;"+
						"}"+

						".form_container {"+
						"	padding: 16px;"+
						"   text-align: left" +
						"}"+

						"span.psw {"+
						"	float: right;"+
						"	padding-top: 16px;"+
						"}"+
						/* Change styles for span and cancel button on extra small screens 
						"@media screen and (max-width: 200px) {"+
						"	span.psw {"+
						"		display: block;"+
						"		float: none;"+
						"	}"+
						"	.cancelbtn {"+
						"		width: 100%;"+
						"	}"+
						"}"+*/
						"</style>"+
						"</head>" +
						"<body onload=\"init()\">" +
						"<div id=\"container\">" +
						"<header>" +
						"<h1>" +
						"<a href=\"HomeServlet?value=displayHome\">Best<span>Deals</span></a>" +
						"</h1>" +
						"<h2>You won't find a better deal anywhere else.</h2>" +
						"<h5 style=\"float: right; padding-right: 30px;\">" +
						"<a href=\"HomeServlet?value=logout\"> Logout</a>" +
						"</h5>" +
						"<h5 style=\"float: right; padding-right: 30px;\">" +
						"<a href='CustomerOrderServlet?value=showOrders'> My Orders</a>" +
						"</h5>" +
						"<h5 style=\"float: right; padding-right: 30px;\">" +
						"<a href=\"CartServlet?value1=viewCart\"> Cart(" + user.getCartSize() + ")</a>" +
						"</h5>" +
						"<h5 style=\"float: right; padding-right: 30px;\">" +
						" Hello, " + user.getUsername() +
						"</h5>"+
						"</header>" +
						"<nav>" +
						"<div style=\"float: left; width: 50%\">"+
							"<ul>" +
								"<li><a href=\"UserHomeServlet?value1=userHome\">Home</a></li>" +
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

					"<div id=\"body\"><br><br>" +
					"<section id=\"content\">" +
					"<h5 align=\"center\">" +
					"<div align=\"center\">"+
					"<h2>Add Product Review : " + prod.getMake() + " " + prod.getModel() + "</h2>" +
					"<br>"+
					"</div>"+
					"<form class=\"register\" action='ReviewServlet?value=" + prod.getProductId() + "' method=\"POST\">"+
					"<input type=\"hidden\" id=\"form\" name=\"form\" value=\"addProductReview\">"+
					"<div class=\"form_container\">"+
						
						"<label class=\"control-label\">UserId: </label><br>"+
						"<input type=\"text\" id=\"userid\" name=\"userid\" placeholder=\"User Id\"><br>"+
						
						"<label class=\"control-label\">Age: </label><br>"+
						"<input type=\"text\" id=\"age\" name=\"age\" placeholder=\"Age\"><br>"+
						
						"<label class=\"control-label\">Gender: </label><br>"+
						"<input type=\"text\" id=\"gender\" name=\"gender\" placeholder=\"gender\"><br>"+
						
						"<label class=\"control-label\">Occupation: </label><br>"+
						"<input type=\"text\" id=\"occupation\" name=\"occupation\" placeholder=\"Occupation\"><br>"+
						
						"<label class=\"control-label\">Rating: </label><br>"+
						"<input type=\"text\" id=\"rating\" name=\"rating\" placeholder=\"Rating(0-10)\"><br>"+
						
						"<label class=\"control-label\">Date: </label><br>");
						DateFormat df = new SimpleDateFormat("dd/MM/yy");
					    Date dateobj = new Date();
						
						out.println(
						"<input type=\"text\" name=\"date\" value=\"" + df.format(dateobj)  + "\" readonly><br>" +		
						"<label class=\"control-label\">Comment:   </label><br>"+
						"<input type=\"text\" id=\"comment\" name=\"comment\" placeholder=\"Review\">"+
						
						"<button class=\"btn btn-success\">Post Review</button>"+
					"</div>"+
				"</form>"+

					"</h5>" +
					"</section>" +

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
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("form").equals("addProductReview")){
			
			//Get review details.
			Product prod = new Product();
			User user = new User();
			Reviews review;
			
			user = (User)request.getSession().getAttribute("userData");
			int prodId = Integer.parseInt(request.getParameter("value"));
			String userAge = request.getParameter("age");
			String userGender = request.getParameter("gender");
			String userOccupation = request.getParameter("occupation");
			float reviewRating = Float.parseFloat(request.getParameter("rating"));
			String date = request.getParameter("date");
			String reviewText = request.getParameter("comment");
			review = new Reviews(prodId, user.getUsername(), userAge, userGender, userOccupation, reviewRating, date, reviewText);
			
			//Add product review to mongoDB.
			MongoDBDataStoreUtilities mongo = new MongoDBDataStoreUtilities();
			mongo.addProductReviewToCollection(review);
			
			//Redirect to add review success page.
			response.sendRedirect("ReviewServlet?value1=addProductReviewSuccess");
			
		}
	}

}
