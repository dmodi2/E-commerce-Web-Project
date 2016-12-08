package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.MySQLDataStoreUtilities;
import database.SaxParserProduct;
import bean.Product;


@WebServlet("/InitServlet")
public class InitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public InitServlet() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("InitServlet(): In Get");
		
		//Load products from XML file to MySql database when application starts.
		ArrayList<Product> productFromXML = new ArrayList<Product>();
		MySQLDataStoreUtilities mySql = new MySQLDataStoreUtilities();
		SaxParserProduct saxpp = new SaxParserProduct("CSP595/CSP 595 Assignment 4/WebContent/ProductData.xml");
		productFromXML = saxpp.getProductList();
		mySql.insertProductFromXML(productFromXML);
				
		response.sendRedirect("HomeServlet?value=displayHome");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
