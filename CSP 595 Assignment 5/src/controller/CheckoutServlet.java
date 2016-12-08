package controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import database.MySQLDataStoreUtilities;
import bean.Cart;
import bean.Orders;
import bean.Product;
import bean.User;


@WebServlet("/CheckoutServlet")
public class CheckoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public CheckoutServlet() {
		super();

	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if(request.getParameter("value").equals("Checkout")){

			System.out.println("In checkout servlet");
			Orders order = new Orders(); 
			User user = new User();
			Cart cart = new Cart();
			Product prod = new Product();
			HashMap<String, Orders> orderMap = new HashMap<String, Orders>();
			user = (User) request.getSession().getAttribute("userData");
			orderMap = (HashMap<String, Orders>)request.getSession().getAttribute("OrderList");
			cart = (Cart) request.getSession().getAttribute("Cart");
			float grandTotal = 0.0f;
			float warrantyTotal = 0.0f;

			response.setContentType("text/html");
			java.io.PrintWriter out = response.getWriter();

			/**
			 * Checkout page.
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
						"<style>"+
						"table {" +
						"background: #e6e6e6;" +
						"margin-left: 20%;"+
						"padding: 0;"+
						"font-family: 'Pontano Sans', Arial, Helvetica, sans-serif;"+
						"font-size: 16px;"+
						"color: #555;"+
						"width: 60%;"+
						"overflow: scroll;"+
						"}" +
						"form {"+
						"border: 3px solid #f1f1f1;"+
						"width: 55%;"+
						"margin: 0 auto;"+
						"}"+

						"input[type=text], input[type=password] {"+
						"width: 100%;"+
						"padding: 12px 20px;"+
						"margin: 8px 0;"+
						"display: inline-block;"+
						"border: 1px solid #ccc;"+
						"box-sizing: border-box;"+
						"}" +

						"button {" +
						"background-color: #4CAF50;"+ 
						"color: white;"+
						"padding: 14px 20px;"+
						"margin: 8px 0;"+
						"border: none;"+
						"cursor: pointer;"+
						"width: 100%;"+
						"}"+

						".cancelbtn {"+
						"width: auto;"+
						"padding: 10px 18px;"+
						"background-color: #f44336;"+
						"}"+

						".imgcontainer {"+
						"text-align: center;"+
						"margin: 24px 0 12px 0;"+
						"}" +

						"img.avatar {" +
						"width: 40%;" +
						"border-radius: 50%;"+
						"}"+

						".form_container {"+
						"padding: 16px;"+

						"}"+

						"span.psw {"+
						"float: right;"+
						"padding-top: 16px;"+
						"}"+

						"/* Change styles for span and cancel button on extra small screens */"+
						"@media screen and (max-width: 200px) {"+
						"span.psw {"+
						"display: block;"+
						"float: none;"+
						"}"+
						".cancelbtn {"+
						"width: 100%;"+
						"}"+
						"}"+

						"</style>"+
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
						"<a href=\"CartServlet?value1=viewCart\"> Cart(" + cart.getCartSize() + ")</a>" +
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

					"<div align=\"center\">" +
					"<h2>Order Summary</h2>" +
					"<br>" +
					"</div>" +
					"<table id=\"table\">");
			for (Map.Entry<String, Orders> o : orderMap.entrySet()) {
				order = o.getValue();
				prod = order.getProd();
				grandTotal = (float) (grandTotal + order.getTotal() * (100 - prod.getDiscount()) * 0.01);
				if(order.isWarranty())
					warrantyTotal = (float) (warrantyTotal + order.getTotal() * 0.1);
				out.println("<tr>" +
						"<td>" +
						"<h5 style=\"text-decoration: underline; color: blue;\">" +
						prod.getMake() + " " + prod.getModel() +
						"</h5>" +
						"<h5>" +
						"Quantity: " +
						order.getQuantity() +
						"</h5>" +
						"<h5>" +
						"Total: " +
						" $" + order.getTotal() * (100 - prod.getDiscount()) * 0.01 +
						"</h5>");
				if(order.isWarranty()){
					out.println("<h5>" +
							"Warranty: " +
							"Yes" +
							"</h5>");
				}else{
					out.println("<h5>" +
							"Warranty: " +
							"No" +
							"</h5>");
				}
				out.println("Purchase Date: " +
						order.getPurchaseDate() +
						"</h5>" +
						"</td>" +
						"<td>" +
						"</td>" +
						"</tr>");
			}
			grandTotal = grandTotal + warrantyTotal;
			out.println("</table><br>" +
					"<div align=\"center\"><h4>Grand Total = $" +  grandTotal  + "</h4></div><br>" +
					"<form action=\"CheckoutServlet?value=OrderSuccess\" method=\"post\">" +

					"<div class=\"form_container\">" +
					"<div align=\"center\">" +
					"<h2>Credit Card Information</h2>" +
					"<br>"+
					"</div>" +
					"<label class=\"control-label\" for=\"cardname\">Name on card</label>"+ 
					"<input type=\"text\" id=\"cardname\" name=\"cardname\" class=\"input-xlarge\" required>" + 

					"<label class=\"control-label\" for=\"cardnum\">Card Number</label>" +
					"<input type=\"text\" id=\"cardnum\" name=\"cardnum\" class=\"input-xlarge\" required>" + 

					"<label class=\"control-label\" for=\"expiry\">Expiry</label><br> " +
					"<input type=\"date\" id=\"expiry\" name=\"expiry\" class=\"input-xlarge\" required><br>"+
					"<br>"+ 

					"<label class=\"control-label\" for=\"billaddr\">Billing Address</label>"+ 
					"<input type=\"text\" id=\"billaddr\" name=\"billaddr\" class=\"input-xlarge\" required>"+
					
					"<label class=\"control-label\" for=\"zipcode\">Zipcode</label>"+ 
					"<input type=\"text\" id=\"zipcode\" name=\"zipcode\" class=\"input-xlarge\" required>"+

					"<button class=\"button\" name=\"action\" value=\"checkout\">Confirm Purchase</button>" +
					"</div>"+
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

		if(request.getParameter("value").equals("OrderSuccess")){

			Cart cart = new Cart();
			User user = new User();
			
			MySQLDataStoreUtilities mySql = new MySQLDataStoreUtilities();
			Random randomGenerator = new Random();
			cart = (Cart)request.getSession().getAttribute("Cart");
			user = (User)request.getSession().getAttribute("userData");
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, 14);
			SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
			String delivery = format1.format(cal.getTime());

			cart.getProdList().removeAll(cart.getProdList());
			cart.setCartSize(0);
			user.setCartSize(0);
			request.getSession().setAttribute("Cart", cart);
			request.getSession().setAttribute("userData", user);

			//Write order in DB and empty cart.
			HashMap<String, Orders> orderMap = new HashMap<String, Orders>();
			orderMap = (HashMap<String, Orders>)request.getSession().getAttribute("OrderList");
			for (Map.Entry<String, Orders> o : orderMap.entrySet()){
				o.getValue().setZipcode(request.getParameter("zipcode"));
			}
			
			mySql.writeOrderDetailsInDb(user, orderMap);
			mySql.deleteCartFromDB(user.getUsername());

			//Display success page.
			response.setContentType("text/html");
			java.io.PrintWriter out = response.getWriter();

			/**
			 * Order placed page.
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
						"<a href=\"CartServlet?value1=viewCart\"> Cart(" + cart.getCartSize() + ")</a>" +
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
					"<div align=\"center\"><h3>Thank You for purchase. Your order will be delivered on " + delivery + ". </h3><br>" +
					"<h3>Your confirmation number is:" + randomGenerator.nextInt(1000) + randomGenerator.nextInt(100) + "</h3></div>" +
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
