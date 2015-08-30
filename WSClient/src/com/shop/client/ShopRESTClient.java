package com.shop.client;

import javax.ws.rs.core.MediaType;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.shop.clientui.ClientUI;
import com.shop.soap.ws.Product;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;


public class ShopRESTClient {
	
	static final String REST_URI = "http://localhost:8080/shop/";
    
    
    
	public static void main(String[] args) {
		
		ClientConfig config = new DefaultClientConfig();
        Client client = Client.create(config);
        WebResource service = client.resource(REST_URI);
 
        /*WebResource allProductsService = service.path(SHOW_ALL_PRODUCTS_PATH);
        System.out.println("Tested .. "+allProductsService.accept(MediaType.APPLICATION_XML).get(String.class));
        */
        ClientUI client1 = new ClientUI("1",service);
        client1.createAndShowGUI(true);
        System.out.println("Hello");
	}
}
