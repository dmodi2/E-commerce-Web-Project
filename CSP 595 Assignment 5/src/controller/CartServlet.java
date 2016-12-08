package controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

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

@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CartServlet() {
		super();

	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if(request.getParameter("value1").equals("addToCart")){

			HashMap <Integer, Product> prodMap = new HashMap<Integer, Product>();
			HttpSession userSession = request.getSession(true);
			if(userSession.getAttribute("userData") == null){
				response.sendRedirect("Login.html");
			}
			else{
				User user = new User();
				Cart cart = new Cart();
				MySQLDataStoreUtilities mySql = new MySQLDataStoreUtilities();
				
				Product prodToCart = new Product();
				user = (User) userSession.getAttribute("userData");
				prodMap = (HashMap<Integer, Product>) userSession.getAttribute("ProductMap");
				System.out.println("prodMap size " + prodMap.size());
				prodToCart = prodMap.get(Integer.parseInt(request.getParameter("value2")));
				System.out.println("Product to be added to cart: " + prodToCart.getMake() + " " + prodToCart.getModel());

				//Update the cart to add the current product.
				cart = (Cart)userSession.getAttribute("Cart");
				System.out.println("Cart Details: " + cart.getUsername() + " has" + cart.getCartSize() + " in cart.");
				ArrayList<Product> prodList = new ArrayList<Product>();
				if(cart.getProdList() != null)
					prodList = cart.getProdList();
				prodList.add(prodToCart);
				cart.setProdList(prodList);
				cart.setCartSize(prodList.size());
				user.setCartSize(prodList.size());
				userSession.setAttribute("userData", user);
				userSession.setAttribute("Cart", cart);
				System.out.println("Cart Details: " + cart.getUsername() + " has" + cart.getCartSize() + " in cart.");

				mySql.writeProdToCartInDB(user, prodToCart);
				request.getRequestDispatcher("CartServlet?value1=viewCart").forward(request, response);
			}

		}
		if(request.getParameter("value1").equals("deleteProdFormCart")){


			Cart cart = new Cart();
			Product prod = new Product();
			User user = new User();
			user = (User) request.getSession().getAttribute("userData");
			//HashMap <Integer, Product> prodMap = new HashMap<Integer, Product>();
			cart = (Cart)request.getSession().getAttribute("Cart");
			//prodMap = (HashMap<Integer, Product>) request.getSession().getAttribute("ProductMap");
			//prod = prodMap.get(Integer.parseInt(request.getParameter("value2")));
			int prodId = Integer.parseInt(request.getParameter("value2"));
			MySQLDataStoreUtilities mySql = new MySQLDataStoreUtilities();
			mySql.removeProdToCartInDB((User)request.getSession().getAttribute("userData"), prodId);

			ArrayList<Product> prodList = new ArrayList<Product>();
			prodList = cart.getProdList();
			int count = 0;
			for(Product p : prodList){
				if(p.getProductId() == prodId)
					break;
				count++;
			}
			prodList.remove(count);
			cart.setProdList(prodList);
			cart.setCartSize(prodList.size());
			user.setCartSize(prodList.size());
			request.getSession().setAttribute("userData", user);
			request.getSession().setAttribute("Cart", cart);

			request.getRequestDispatcher("CartServlet?value1=viewCart").forward(request, response);

		}

		if(request.getParameter("value1").equals("viewCart")){


			User user = new User();
			Cart cart = new Cart();
			user = (User)request.getSession().getAttribute("userData");
			cart = (Cart)request.getSession().getAttribute("Cart");


			response.setContentType("text/html");
			java.io.PrintWriter out = response.getWriter();

			/**
			 * User home page.
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
						"width: 85%;" +
						"overflow: scroll;" +
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
						"<a href=\"HomeServlet?value=logout\"> Logout</a>" +
						"</h5>" +
						"<h5 style=\"float: right; padding-right: 30px;\">" +
						"<a href='CustomerOrderServlet?value=showOrders'> My Orders</a>" +
						"</h5>" +
						"<h5 style=\"float: right; padding-right: 30px;\">" +
						"<a href=\"CartServlet?value1=viewCart\"> Cart(" + cart.getCartSize() + ")" +
						"</a>" +
						"</h5>" +
						"<h5 style=\"float: right; padding-right: 30px;\">" +
						"Hello, " + user.getUsername() +
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
					"<h1 style=\"font-family: sans-serif; color: #2D97BA;\">My Cart</h1>" +
					"<br>" +
					"<br>" +
					"</div>");
			if (cart.getCartSize() == 0) {
				out.println("<h3>Your Shopping cart is empty.</h3>"); 
			} else {
				out.println("<form action=\"CartServlet\" method=\"post\">" +
						"<table id=\"table\">");
				ArrayList<Product> prodList = new ArrayList<Product>();
				prodList = cart.getProdList();
				for (Product prod : prodList) {
					out.println("<tr>" +
							"<td><img class=\"header-image\"" +
							"src=\"images/" + prod.getImage_path() + "\" width=\"300\" height=\"200\"" +
							"alt=\"Buildings\" /></td>" +
							"<td>" +
					"<h5 style=\"text-decoration: underline; color: blue;\">" +
					prod.getMake() + " " + prod.getModel() +
					"</h5>" +
					"<h5>" +
					"Price:" +
					   " $" + prod.getPrice() +
					"</h5>" +
					"<h5>" +
					"Discount:" +
						" " + prod.getDiscount() + "%" +
					
					"</h5>" +
					"<h5>" +
					"Condition: " +
						
						" " + prod.getCondition() +
					
					"</h5> " +
					"<label>Buy Warranty for $" + prod.getPrice()*0.1 + "</label> <select name=\"Warranty" + prod.getModel() + "\"Quantity>" +
					"<option value=\"yes\">Yes" +
					"<option value=\"no\">No" +
					"</select> <br>" +
					
					"<label>Quantity: </label> <select name=\"" + prod.getModel() + "\"Quantity>" +
					"<option value=\"1\">1" +
					"<option value=\"2\">2" +
					"<option value=\"3\">3" +
					"<option value=\"4\">4" +
					"<option value=\"5\">5" +
					"</select> <br>" +
					"<br>" +
					"<a href=\"CartServlet?value2=" + prod.getProductId() + "&value1=deleteProdFormCart\"" +
					"class=\"button\">Delete</a>" +
					"</td>" +
					"</tr>");
				}
				
				out.println("</table>" +
				"<div align=\"center\">" +
				"<br>" +
				"<button class=\"button\" name=\"action\" value=\"checkout\">Checkout</button>" +
				"</div>" +
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
					"<li><a href=\"DisplayProductServlet?value=accessories\">Accessories</a></li>" +
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

		if(request.getParameter("action").equals("checkout")){
			
			HashMap<String, Orders> orderMap = new HashMap<String, Orders>();
			Calendar cal = Calendar.getInstance();
			Calendar cal14 = Calendar.getInstance();
			cal.add(Calendar.DATE, 0);
			cal14.add(Calendar.DATE, 14);
			SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
			String purchase = format1.format(cal.getTime());
			String delivery = format1.format(cal14.getTime());
			
			Cart cart = new Cart();
			User user = new User();
			
			cart = (Cart)request.getSession().getAttribute("Cart");
			user = (User)request.getSession().getAttribute("userData");
			ArrayList<Product> prodList = new ArrayList<Product>();
			prodList = cart.getProdList();
			
			for(Product p : prodList){
				
				Orders ord = new Orders();
				ord.setUsername(user.getUsername());
				ord.setProd(p);
				ord.setQuantity(Integer.parseInt(request.getParameter(p.getModel())));
				ord.setPurchaseDate(purchase);
				ord.setDeliveryDate(delivery);
				System.out.println(request.getParameter("Warranty" + p.getModel()));
				if(request.getParameter("Warranty" + p.getModel()).equals("yes"))
					ord.setWarranty(true);
				else
					ord.setWarranty(false);
				ord.setTotal(Integer.parseInt(request.getParameter(p.getModel())) * p.getPrice());
				orderMap.put(p.getModel(), ord);
				
			}
			//Test date validation
			/*
			Product demoProd = new Product("TV", "Samsung", "OLED100", "demo.jpg", 2500f, 0, "New", 4, null);
			Orders demo = new Orders(user.getUsername(), demoProd, 2, 5000f, false, "2016-09-12", "2016-09-26");
			orderMap.put(demoProd.getModel(), demo);*/
			
			System.out.println("OrderMap size: "+ orderMap.size());
			request.getSession().setAttribute("OrderList", orderMap);
			
			request.getRequestDispatcher("CheckoutServlet?value=Checkout").forward(request, response);
			
		}

	}

}
