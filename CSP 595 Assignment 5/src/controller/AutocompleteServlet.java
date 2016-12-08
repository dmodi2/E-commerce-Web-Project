package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Accessory;
import bean.Cart;
import bean.Product;
import bean.User;
import database.AjaxUtility;
import database.MySQLDataStoreUtilities;

@WebServlet("/AutocompleteServlet")
public class AutocompleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AutocompleteServlet() {
		super();
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if(request.getParameter("action").equals("complete")){
			String searchId = request.getParameter("searchId");
			try{
				StringBuffer sb = new StringBuffer();
				boolean namesAdded = false;
				if(!searchId.equals("")){
					AjaxUtility ajax  = new AjaxUtility();
					sb = ajax.readData(searchId);
					if(sb != null || !sb.equals("")){
						namesAdded = true;
						System.out.println(sb.toString());
					}
					if(namesAdded){
						response.setContentType("text/xml");
						response.getWriter().write("<products>" + sb.toString() + "</products>");
					}
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		if(request.getParameter("action").equals("lookup")){

			int prodId = Integer.parseInt(request.getParameter("searchId"));
			MySQLDataStoreUtilities mysql = new MySQLDataStoreUtilities();
			HashMap <Integer, Product> prodMap = new HashMap<Integer, Product>();
			prodMap = mysql.getProductFromDB();
			Product prod = new Product();
			prod = mysql.getProdfromId(prodId);
			request.getSession().setAttribute("ProductMap", prodMap);

			User user = new User();
			Cart cart = new Cart();
			if(request.getSession().getAttribute("userData") != null){
				user = (User)request.getSession().getAttribute("userData");
				cart = (Cart)request.getSession().getAttribute("Cart");
			}

			response.setContentType("text/html");
			java.io.PrintWriter out = response.getWriter();

			/**
			 * Display product page.
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
					"//-->" +
					"<script type=\"text/javascript\" src=\"ajax.js\"></script>" +
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
			if(request.getSession().getAttribute("userData") != null){
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
						"<a href=\"Registration.html\"> Signup</a>" +
						"</h5>" +
						"<h5 style=\"float: right; padding-right: 30px;\">" +
						"<a href=\"Login.html\"> Login</a>" +
						"</h5>");
			}
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
					"<section id=\"content\">");

			out.println("<div align=\"center\">" +
					"<h1 style=\"color: #555\">" + prod.getMake() + " " + prod.getModel()  + "</h1>" +
					"</div>" +
					"<br>" +
					"<table id=\"table\">" +
					"<tr>" +
					"<td><img class=\"header-image\" src=\"images/" + prod.getImage_path() + "\""  + "width=\"300\"" +
					"height=\"200\" alt=\"Buildings\" />" +
					"</td>" +
					"<td>" +
					"<h5 style=\"text-decoration: underline; color: blue;\">" +
					prod.getMake() + " " + prod.getModel() +
					"</h5>" +
					"<h5>" +
					"Price: " +
					" $" + prod.getPrice() +
					"</h5>" +
					"<h5>Discount: " +
					" " + prod.getDiscount() + "%" +

								"</h5>" +
								"<h5>" +
								"Condition: " +

									" " + prod.getCondition() +
									"</h5>" +
									"<h5>" +
									"Rating: " +

									" " + prod.getRating() +

									"</h5>" +
									"<h5>" +
					"Includes: "); 

			out.println(prod.getAccessorry_list() + " ");
			out.println("</h5>" +
					"<a href=\"CartServlet?value1=addToCart&value2=" + prod.getProductId() + "\" class=\"button\" >Add To Cart</a><br><br>" +
					"<a href=\"ReviewServlet?value1=showProdReviews&value2=" + prod.getProductId() + "\" class=\"button\" >Reviews</a>" +
					"</td>" +
					"</tr>") ;
			out.println("</table>");

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

	}

}
