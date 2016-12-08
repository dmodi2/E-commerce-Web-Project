package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.MongoDBDataStoreUtilities;
import database.MySQLDataStoreUtilities;
import bean.Product;


@WebServlet("/ManagerServlet")
public class ManagerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public ManagerServlet() {
		super();

	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HashMap <Integer, Product> prodMap = new HashMap<Integer, Product>();
		Product prod = new Product();
		MySQLDataStoreUtilities mySql = new MySQLDataStoreUtilities();
		prodMap = mySql.getProductFromDB();
		
		if(request.getParameter("value").equals("dataAnalytics")){
			
			response.setContentType("text/html");
			java.io.PrintWriter out = response.getWriter();

			/**
			 * Display Trending page.
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
					"<style>" +
					"table {" +
					//"background: #e6e6e6;" +
					"margin-left: 10%;" +
					"padding: 0;" +
					"font-family: 'Pontano Sans', Arial, Helvetica, sans-serif;" +
					"font-size: 16px;" +
					//"color: #555;" +
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
					"<h2>You won't find a better deal anywhere else.</h2>"
					+ "<h5 style=\"float: right; padding-right: 30px;\">" +
			"<a href=\"HomeServlet?value=displayHome\"> Logout</a>" +
			"</h5>" +
			"<h5 style=\"float: right; padding-right: 30px;\">" +
			" Hello, Shobhit" + 
			"</h5>"); 
			out.print("</header>" +
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
			"<section id=\"content\">"+
			"<div align=\"center\">" +
			"<h1 style=\"color: #555\">Analytics</h1>" +
			"</div>" +
			"<br><br>" +
			"<ul>"+
			"<li><a href=\"ManagerServlet?value=dataAnalyticsQueryShow&value1=query1\">Print the list of all products and their ratings.</a></li>"+
			"</ul>"+
			"<ul>"+
			"<li><a href=\"ManagerServlet?value=dataAnalyticsQueryShow&value1=query2\">Print a list of reviews where rating greater than 7.</a></li>"+
			"</ul>"+
			"<ul>"+
			"<li><a href=\"ManagerServlet?value=dataAnalyticsQueryShow&value1=query3\">Get a list of products that got review rating 10 and price more than thousand.</a></li>"+
			"</ul>"+
			"<ul>"+
			"<li><a href=\"ManagerServlet?value=dataAnalyticsQueryShow&value1=query7\">Find highest price product reviewed/sold in every zip-code.</a></li>"+
			"</ul>"+
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
		
		if(request.getParameter("value").equals("dataAnalyticsQueryShow")){
			
			String query = request.getParameter("value1");
			String heading = "";
			ArrayList<String> analytics = new ArrayList<String>();
			MongoDBDataStoreUtilities mongo = new MongoDBDataStoreUtilities();
			if(query.equals("query1")){
				heading= "Print the list of all products and their ratings.";
				analytics = mySql.extraCreditQuery("query1");
			}else if(query.equals("query2")){
				heading= "Print a list of reviews where rating greater than 7.";
				analytics = mongo.extraCreditQuery("query2");
			}else if(query.equals("query3")){
				heading= "Get a list of products that got review rating 10 and price more than thousand.";
				analytics = mongo.extraCreditQuery("query3");
			}else if(query.equals("query7")){
				heading= "Find highest price product reviewed/sold in every zip-code.";
				analytics = mySql.extraCreditQuery("query7");
			}else{
				
			}
			
			response.setContentType("text/html");
			java.io.PrintWriter out = response.getWriter();

			/**
			 * Display Trending page.
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
					"<h2>You won't find a better deal anywhere else.</h2>"
					+ "<h5 style=\"float: right; padding-right: 30px;\">" +
			"<a href=\"HomeServlet?value=displayHome\"> Logout</a>" +
			"</h5>" +
			"<h5 style=\"float: right; padding-right: 30px;\">" +
			" Hello, Shobhit" + 
			"</h5>"); 
			out.print("</header>" +
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
					"<section id=\"content\">"
					+ "<div align=\"center\">" +
					"<h1 style=\"color: #555\">Analytics</h1>" +
					"</div>" +
					"<br><br>" +
					"<table id=\"table\">"+
					"<th>" + heading + "</th>");
					for(String s : analytics){
						out.println("<tr>"+
								    "<td>" + s + "</td>"+
									"</tr>");
					}
			
			out.println("</table><br>"
					+ "</section>" +

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

		if(request.getParameter("value").equals("updtDelProduct")){

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
			"form {" +
				"border: 3px solid #f1f1f1;" +
			"width: 60%;" +
			"margin: 0 auto;" +
			"}" +

			"input[type=text], input[type=password] {"+
				"width: 100%;" +
			"padding: 12px 20px;"+
			"margin: 8px 0;" +
			"display: inline-block;" +
			"border: 1px solid #ccc;" +
			"box-sizing: border-box;" +
			"}" +

			"button {" +
				"background-color: #4CAF50;" +
			"color: white;" +
			"padding: 14px 20px;" +
			"margin: 8px 0;" +
			"border: none;" +
			"cursor: pointer;" +
			"width: 100%;" +
			"}" +

			".cancelbtn {" +
				"width: auto;" +
			"padding: 10px 18px;" +
			"background-color: #f44336;" +
			"}" +

			".imgcontainer {" +
				"text-align: center;" +
			"margin: 24px 0 12px 0;" +
			"}" +

			"img.avatar {" +
			"	width: 40%;" +
			"border-radius: 50%;" +
			"}" +

			".form_container {" +
				"padding: 16px;"+
			"}" +

			"span.psw {" +
				"float: right;" +
			"padding-top: 16px;" +
			"}" +

			"@media screen and (max-width: 200px) {" +
				"span.psw {" +
					"display: block;" +
				"float: none;" +
				"}" +
				".cancelbtn {" +
					"width: 100%;" +
				"}" +
			"}" +
			"</style>" +
			"</head>" +
			"<body onload=\"init()\">" +
			"<div id=\"container\">" +
			"<header>" +
			"<h1>" +
			"<a href=\"HomeServlet?value=displayHome\">Best<span>Deals</span></a>" +
			"</h1>" +
			"<h2>You won't find a better deal anywhere else.</h2>" +
			"<h5 style=\"float: right; padding-right: 30px;\">" +
			"<a href=\"HomeServlet?value=displayHome\"> Logout</a>" +
			"</h5>" +
			"<h5 style=\"float: right; padding-right: 30px;\">" +
			" Hello, Shobhit" + 
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

					"<div id=\"body\"><br><br>" +
					"<section id=\"content\">" +

					"<div align=\"center\">" +
					"<h2>Select Product to update or delete:</h2>" +
					"<br>" +
					"</div>" +

				"<form class=\"register\" action='ManagerServlet' method=\"GET\">" +
				"<input type=\"hidden\" id=\"form\" name=\"form\" value=\"updateDeleteProduct\">" +
				"<div class=\"form_container\">" +
				"<label>Product: </label>" +
					"<select name=\"product\">");
			for (Map.Entry<Integer, Product> m : prodMap.entrySet()) {
				prod = m.getValue();
				System.out.println(prod.getMake() + " " + prod.getModel());
				out.println("<option value=\"" + prod.getProductId() + "\">" + prod.getMake() + " " + prod.getModel() + "</option>");

			}
			out.println("</select><br><br>" +
					"<button class=\"btn btn-success\" name=\"value\" value=\"updateProduct\">Update</button>" +
					"<button class=\"btn btn-success\" name=\"value\" value=\"deleteProduct\">Delete</button>" +
					"</div>" +
					"</form>" +

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

		if(request.getParameter("value").equals("deleteProduct")){

			int prodId = Integer.parseInt(request.getParameter("product"));
			boolean flag  = mySql.deleteProductFromDB(prodId);

			response.sendRedirect("ManagerServlet?value=deleteSuccess");

		}

		if(request.getParameter("value").equals("updateProduct")){
			
			prod = prodMap.get(Integer.parseInt(request.getParameter("product")));
			
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
			"form {" +
				"border: 3px solid #f1f1f1;" +
			"width: 60%;" +
			"margin: 0 auto;" +
			"}" +

			"input[type=text], input[type=password] {"+
				"width: 100%;" +
			"padding: 12px 20px;"+
			"margin: 8px 0;" +
			"display: inline-block;" +
			"border: 1px solid #ccc;" +
			"box-sizing: border-box;" +
			"}" +

			"button {" +
				"background-color: #4CAF50;" +
			"color: white;" +
			"padding: 14px 20px;" +
			"margin: 8px 0;" +
			"border: none;" +
			"cursor: pointer;" +
			"width: 100%;" +
			"}" +

			".cancelbtn {" +
				"width: auto;" +
			"padding: 10px 18px;" +
			"background-color: #f44336;" +
			"}" +

			".imgcontainer {" +
				"text-align: center;" +
			"margin: 24px 0 12px 0;" +
			"}" +

			"img.avatar {" +
			"	width: 40%;" +
			"border-radius: 50%;" +
			"}" +

			".form_container {" +
				"padding: 16px;"+
			"}" +

			"span.psw {" +
				"float: right;" +
			"padding-top: 16px;" +
			"}" +

			"@media screen and (max-width: 200px) {" +
				"span.psw {" +
					"display: block;" +
				"float: none;" +
				"}" +
				".cancelbtn {" +
					"width: 100%;" +
				"}" +
			"}" +
			"</style>" +
			"</head>" +
			"<body onload=\"init()\">" +
			"<div id=\"container\">" +
			"<header>" +
			"<h1>" +
			"<a href=\"HomeServlet?value=displayHome\">Best<span>Deals</span></a>" +
			"</h1>" +
			"<h2>You won't find a better deal anywhere else.</h2>" +
			"<h5 style=\"float: right; padding-right: 30px;\">" +
			"<a href=\"HomeServlet?value=displayHome\"> Logout</a>" +
			"</h5>" +
			"<h5 style=\"float: right; padding-right: 30px;\">" +
			" Hello, Shobhit" + 
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

					"<div id=\"body\"><br><br>" +
					"<section id=\"content\">" +
					
					"<div align=\"center\">" +
					"<h2>Update Product</h2>" +
						"<br>" +
					"</div>" +
					"<form class=\"register\" action='ManagerServlet' method=\"GET\">" +
						"<div class=\"form_container\">" +
							"<input type=\"hidden\" name=\"product_id\" value=\"" + prod.getProductId() +"\">" + 
							
							"<label>Product: " + prod.getMake() + " " + prod.getModel() + "</label><br><br>" + 
							
							"<label>Image:</label><br>" +
							"<input type=\"file\" name=\"image\" accept=\"image/*\" value=\"" + prod.getImage_path() + "\"><br>" +
							
							"<label>Price:</label>" +
							"<input type=\"text\" id=\"price\" name=\"price\" placeholder=\"" + prod.getPrice() + "\" value=\"" + prod.getPrice() + "\">" +
							
							"<label>Discount:</label>" +
							"<input type=\"text\" id=\"discount\" name=\"discount\" placeholder=\"" + prod.getDiscount() + "(%)\" value=\"" + prod.getDiscount() + "\">" +
							
							"<label>Condition:</label>" +
							"<input type=\"text\" id=\"condition\" name=\"condition\" placeholder=\"" + prod.getCondition() + "\" value=\"" + prod.getCondition() + "\">" +
							
							"<label>Rating:</label>" +
							"<input type=\"text\" id=\"rating\" name=\"rating\" placeholder=\"" + prod.getRating() + "\" value=\"" + prod.getRating() + "\">" +
							
							"<label>Accessories:</label><br>" +
							"<input type=\"checkbox\" name=\"accessories\" value=\"chromecast\">Chromecast&nbsp&nbsp" +
							"<input type=\"checkbox\" name=\"accessories\" value=\"mouse\">Mouse&nbsp&nbsp" +
							"<input type=\"checkbox\" name=\"accessories\" value=\"earphone\">Earphone&nbsp&nbsp" +
							"<input type=\"checkbox\" name=\"accessories\" value=\"usb\">Usb&nbsp&nbsp" +
							"<input type=\"checkbox\" name=\"accessories\" value=\"charger\">Phone Charger&nbsp&nbsp" +
							"<input type=\"checkbox\" name=\"accessories\" value=\"phone_cover\">Phone Cover&nbsp&nbsp" +
							"<input type=\"checkbox\" name=\"accessories\" value=\"wall_mount\">Wall Mount&nbsp&nbsp" +
							"<br><br>" +
							
							"<button class=\"btn btn-success\" name=\"value\" value=\"updateProductInFile\">Update</button>" +
						"</div>" +
					"</form>" +

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
		
        if(request.getParameter("value").equals("updateProductInFile")){
			
			Product updateProd = new Product();
			updateProd = prodMap.get(Integer.parseInt(request.getParameter("product_id")));
			String accessorry_list="";
			
			if(!request.getParameter("image").equals("")){
				updateProd.setImage_path(request.getParameter("image"));
			}
			
			updateProd.setPrice(Float.parseFloat(request.getParameter("price")));
			updateProd.setDiscount(Integer.parseInt(request.getParameter("discount")));
			updateProd.setCondition(request.getParameter("condition"));
			updateProd.setRating(Float.parseFloat(request.getParameter("rating")));

			if(request.getParameterValues("accessories") != null){
				System.out.println("Accessories there");
				String[] al = request.getParameterValues("accessories");
				for(int i = 0; i < al.length; i++){
					accessorry_list = accessorry_list + al[i] + " ";
				}
				updateProd.setAccessorry_list(accessorry_list);
			}
			boolean flag = mySql.updateProductInDB(updateProd);
			response.sendRedirect("ManagerServlet?value=updateSuccess");
		}

		if(request.getParameter("value").equals("deleteSuccess")){

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
						"</head>" +
						"<body onload=\"init()\">" +
						"<div id=\"container\">" +
						"<header>" +
						"<h1>" +
						"<a href=\"HomeServlet?value=displayHome\">Best<span>Deals</span></a>" +
						"</h1>" +
						"<h2>You won't find a better deal anywhere else.</h2>" +
						"<h5 style=\"float: right; padding-right: 30px;\">" +
						"<a href=\"HomeServlet?value=displayHome\"> Logout</a>" +
						"</h5>" +
						"<h5 style=\"float: right; padding-right: 30px;\">" +
						" Hello, Shobhit" + 
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

					"<div id=\"body\"><br><br>" +
					"<section id=\"content\">" +
					"<h5 align=\"center\">" +
					"Product successfully deleted. Please check Product page." +
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
		
		if(request.getParameter("value").equals("updateSuccess")){

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
						"</head>" +
						"<body onload=\"init()\">" +
						"<div id=\"container\">" +
						"<header>" +
						"<h1>" +
						"<a href=\"HomeServlet?value=displayHome\">Best<span>Deals</span></a>" +
						"</h1>" +
						"<h2>You won't find a better deal anywhere else.</h2>" +
						"<h5 style=\"float: right; padding-right: 30px;\">" +
						"<a href=\"HomeServlet?value=displayHome\"> Logout</a>" +
						"</h5>" +
						"<h5 style=\"float: right; padding-right: 30px;\">" +
						" Hello, Shobhit" + 
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

					"<div id=\"body\"><br><br>" +
					"<section id=\"content\">" +
					"<h5 align=\"center\">" +
					"Product updated. Please check Product page." +
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

		if(request.getParameter("value").equals("addProduct")){

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
						"</head>" +
						"<body onload=\"init()\">" +
						"<div id=\"container\">" +
						"<header>" +
						"<h1>" +
						"<a href=\"HomeServlet?value=displayHome\">Best<span>Deals</span></a>" +
						"</h1>" +
						"<h2>You won't find a better deal anywhere else.</h2>" +
						"<h5 style=\"float: right; padding-right: 30px;\">" +
						"<a href=\"HomeServlet?value=displayHome\"> Logout</a>" +
						"</h5>" +
						"<h5 style=\"float: right; padding-right: 30px;\">" +
						" Hello, Shobhit" + 
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

					"<div id=\"body\"><br><br>" +
					"<section id=\"content\">" +
					"<h5 align=\"center\">" +
					"Product successfully added. Please check Product page." +
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


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
