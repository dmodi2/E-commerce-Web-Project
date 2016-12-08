package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.MySQLDataStoreUtilities;
import bean.Product;

@WebServlet("/DealMatchesUtilitiesServlet")
public class DealMatchesUtilitiesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public DealMatchesUtilitiesServlet() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		MySQLDataStoreUtilities mysql = new MySQLDataStoreUtilities();
		HashMap<Integer, Product> allProducts = new HashMap<Integer, Product>();
		HashMap<Integer, Product> productsToDisplay = new HashMap<Integer, Product>();
		String tweetsToDisplay = "";
		allProducts = mysql.getProductFromDB();
		List<Integer> keys = new ArrayList<Integer>(allProducts.keySet());
		Collections.shuffle(keys);
		request.getSession().setAttribute("ProductMap", allProducts);
		Product prod = new Product();
		
		
		//Get all Tweets from 'DealMatch.txt' that we got from twitter which matched our products.
		try{
			String line="";
			String productName = "";
			
			for(Integer i : keys){
				
				if(productsToDisplay.size()<2 && !productsToDisplay.containsKey(i)){
					BufferedReader tweets = new BufferedReader(new FileReader(new File("D:/IIT/CSP595/apache-tomcat-7.0.34/webapps/csj/DealMatches.txt")));
					line = tweets.readLine();
					productName = allProducts.get(i).getMake() + " " + allProducts.get(i).getModel();
					System.out.println(productName);
					if(line==null){
						tweetsToDisplay = "No Offers Found.";
						break;
					}else{
						do{
							if(line.contains(productName)){
								tweetsToDisplay = tweetsToDisplay + line +"<br>";
								productsToDisplay.put(i, allProducts.get(i));
								break;
							}
						}while((line=tweets.readLine())!=null);
					}
				}
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		response.setContentType("text/html");
		java.io.PrintWriter out = response.getWriter();
		
		//Display tweets.
		out.println("<h2>Welcome to BestDeals - Start saving now.</h2>" +
					"<p>We offer the best and genuine products in the market at the"+
				        "cheapest price possible.</p>"+
				   "<div style='background-color: #E6E7E9'>"+
					"<div style='margin-left:20px'>"+
					    "<h2>We beat our competitors in all aspects. Price-Match Gauranteed.</h2><br>"+
					    "<p>" + tweetsToDisplay + "</p>"+
				    "</div></div><br>");
		
		//Display products.
		out.println("<div style='background-color: #E6E7E9'>"+
				"<div style='margin-left:20px'>"+
					"<h2>Deal Matches:</h2><br>"+
					"<table id=\"table\" style='width:80%'>");
					for (Map.Entry<Integer, Product> m : productsToDisplay.entrySet()) {
						prod = m.getValue();
						out.println("<tr>" +
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
					}
				out.println("</table>"+
			    "</div></div><br>");
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
