package controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.MySQLDataStoreUtilities;

import bean.Cart;
import bean.Orders;
import bean.Product;
import bean.User;


@WebServlet("/CustomerOrderServlet")
public class CustomerOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public CustomerOrderServlet() {
		super();

	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		User user = new User();
		Cart cart = new Cart();
		user = (User)request.getSession().getAttribute("userData");
		cart = (Cart)request.getSession().getAttribute("Cart");

		if(request.getParameter("value").equals("getallcust")){

			MySQLDataStoreUtilities mySql = new MySQLDataStoreUtilities();
			ArrayList<String> allUsers = new ArrayList<String>();
			allUsers = mySql.getAllUsersFromDb();

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
						" Hello, Sahil" + 
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
					"<section id=\"content\">");
			out.println("<br>" +
					"<form action=\"CustomerOrderServlet\" method=\"get\" style=\"margin-left: 25%;\">"+
					"<input type=\"hidden\" name=\"value\" value=\"showOrders\">" +
					"<h3>Select a customer to process order details.</h3>"+
					"<select name=\"customer\" style=\"margin-left: 25%;\">");
			for(String s : allUsers){
				System.out.println(s);
				out.println("<option value=\"" + s + "\">" + s);
			}
			out.println("</select><br><br>"+
					"<button class=\"button\" style=\"margin-left: 25%;\">Submit</button>" +
					"</form>"+
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

		if(request.getParameter("value").equals("showOrders")){

			HashMap<Integer, Orders> orderMap = new HashMap<Integer, Orders>();
			MySQLDataStoreUtilities mySql = new MySQLDataStoreUtilities();
			String customer = "";
			if(user == null){
				
				HttpSession session = request.getSession(true);
				
				customer = request.getParameter("customer");
				session.setAttribute("cust", customer);
				orderMap = mySql.getUserOrderFromDB(customer);
			}
			else{
				orderMap = mySql.getUserOrderFromDB(user.getUsername());
			}
			Date d1 = null, d2 = null;

			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, 0);
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String today = format.format(cal.getTime());

			/*request.getSession().setAttribute("UserOrder", orderMap);
			System.out.println("Orders" + orderMap.size());

			request.getRequestDispatcher("CustomerOrders.jsp").forward(request, response);*/

			response.setContentType("text/html");
			java.io.PrintWriter out = response.getWriter();
			/**
			 * User order page.
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
						"table {"+
						"background: #e6e6e6;"+
						"margin-left: 10%;"+
						"padding: 0;"+
						"font-family: 'Pontano Sans', Arial, Helvetica, sans-serif;"+
						"font-size: 16px;"+
						"color: #555;"+
						"width: 80%;"+
						"overflow: scroll;"+
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
			if(user != null){
				out.print("<h5 style=\"float: right; padding-right: 30px;\">" +
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
						"</h5>");
			}
			else{
				out.println("<h5 style=\"float: right; padding-right: 30px;\">" +
						"<a href=\"Registration.html\"> Logout</a>" +
						"</h5>"+
						"<h5 style=\"float: right; padding-right: 30px;\">" +
						" Hello, Sahil" +
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

					"<div align=\"center\">");
			if(user != null){	
				out.println("<h1 style=\"font-family: sans-serif; color: #2D97BA;\">My Orders</h1>");
			}
			else{
				out.println("<h1 style=\"font-family: sans-serif; color: #2D97BA;\">Customer Orders</h1>");
			}
			out.println("<br> <br>"+
					"</div>");

			if (orderMap.size() == 0) {

				out.println("<h2>No orders found.</h2>");

			} else {

				out.println("<form action=\"CartServlet\" method=\"post\">"+
						"<table id=\"table\">");
				Orders order = new Orders();
				Product prod = new Product();
				for (Map.Entry<Integer, Orders> o : orderMap.entrySet()) {
					order = o.getValue();
					prod = order.getProd();

					out.println("<tr>"+
							"<td><img class=\"header-image\" src=\"images/" + prod.getImage_path() + "\" width=\"250\" height=\"170\"alt=\"Buildings\" /></td>"+
							"<td>"+
							"<h5 style=\"text-decoration: underline; color: blue;\">" +

										prod.getMake() + " " + prod.getModel() +

										"</h5>"+
										"<h5>"+
										"Price:" +

									" $" + prod.getPrice() +

									"</h5>"+
									"<h5>"+
									"Quantity:"+

									order.getQuantity()+

									"</h5>"+
									"<h5>"+
							"Warranty Taken:");

					if(order.isWarranty())
						out.println(" Yes");
					else
						out.println(" No");

					out.println("</h5>"+
							"<h5>"+
							"Purchase Date:"+
							order.getPurchaseDate()+
							"</h5>"+
							"<h5>"+
							"Expected Delivery Date:" +
							order.getDeliveryDate()+
							"</h5>");
					try {
						d1 = new SimpleDateFormat("yyyy-M-dd").parse(order.getDeliveryDate());
						d2 = new SimpleDateFormat("yyyy-M-dd").parse(today);
					} catch (ParseException e) {
						e.printStackTrace();
					}
					long diff = d1.getTime() - d2.getTime();
					if((diff / (1000 * 60 * 60 * 24)) < 0){

						out.println("<h5>Status: Delivered</h5>"); 

					}else{

						out.println("<h5>Status: In transit</h5>"); 

					} 
					if(((diff / (1000 * 60 * 60 * 24)) > 5)){
						out.println("<a href=\"CustomerOrderServlet?value2=" + prod.getProductId() + "&value=cancelProdFormOrder\" class=\"button\">Cancel</a>");
					}

					out.println("</td>"+
							"</tr>");

				}

				out.println("</table>"+
						"</form>");

			}


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

		if(request.getParameter("value").equals("cancelProdFormOrder")){
			
			MySQLDataStoreUtilities mySql = new MySQLDataStoreUtilities();
			int prodId = Integer.parseInt(request.getParameter("value2"));
			if(request.getSession().getAttribute("cust") != null){
				mySql.removeOrderFromUserDB((String)request.getSession().getAttribute("cust"), prodId);
			}
			else{
				mySql.removeOrderFromUserDB(user.getUsername(), prodId);
			}

			response.setContentType("text/html");
			java.io.PrintWriter out = response.getWriter();

			/**
			 * Cancel order success page.
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
						"<a href=\"HomeServlet?value=displayHome\">Best<span>Deals</span></a>" +
						"</h1>" +
						"<h2>You won't find a better deal anywhere else.</h2>"); 
						if(user != null){
							out.print("<h5 style=\"float: right; padding-right: 30px;\">" +
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
									"</h5>");
						}
						else{
							out.println("<h5 style=\"float: right; padding-right: 30px;\">" +
									"<a href=\"Registration.html\"> Logout</a>" +
									"</h5>"+
									"<h5 style=\"float: right; padding-right: 30px;\">" +
									" Hello, Sahil" +
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

				"<div align=\"center\">"+
				"<br><h4>Order has been cancelled. Money will be refunded in 2 business days.</h4>"+
				"</div>"+

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

	}

}
