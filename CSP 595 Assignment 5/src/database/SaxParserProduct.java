package database;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import bean.Product;

public class SaxParserProduct extends DefaultHandler{

	Product product;
	ArrayList<Product> products;
	String productXmlFileName;
	String elementValueRead;


	public SaxParserProduct(String productXmlFileName) {
		this.productXmlFileName = productXmlFileName;
		products = new ArrayList<Product>();
		parseDocument();
	}


	private void parseDocument() {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		try {
			SAXParser parser = factory.newSAXParser();
			parser.parse(productXmlFileName, this);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Product> getProductList(){
		return this.products;
	}

	public void startElement(String str1, String str2, String elementName, Attributes attributes) throws SAXException {

		if (elementName.equalsIgnoreCase("product")) {
			product = new Product();
			product.setProductId(Integer.parseInt(attributes.getValue("id")));
		}

	}

	public void endElement(String str1, String str2, String element) throws SAXException {

		if (element.equals("product")) {
			products.add(product);
			return;
		}
		if (element.equalsIgnoreCase("image")) {
			product.setImage_path(elementValueRead);
			return;
		}
		if(element.equalsIgnoreCase("price")){
			product.setPrice(Integer.parseInt(elementValueRead));
			return;
		}
		if (element.equalsIgnoreCase("make")) {
			product.setMake(elementValueRead);
			return;
		}
		if (element.equalsIgnoreCase("model")) {
			product.setModel(elementValueRead);
			return;
		}
		if (element.equalsIgnoreCase("condition")) {
			product.setCondition(elementValueRead);
			return;
		}
		if(element.equalsIgnoreCase("accessory")){
			product.setAccessorry_list(elementValueRead);
			return;
		}
		if(element.equalsIgnoreCase("rating")){
			product.setRating(Float.parseFloat(elementValueRead));
			return;
		}
		if(element.equalsIgnoreCase("category")){
			product.setCategory(elementValueRead);
			return;
		}
		if(element.equalsIgnoreCase("discount")){
			product.setDiscount(Integer.parseInt(elementValueRead));
			return;
		}
	}

	public void characters(char[] content, int begin, int end) throws SAXException {
		elementValueRead = new String(content, begin, end);
	}
}
