package com.shop.rest.ws;

import java.io.IOException;

import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import com.sun.net.httpserver.HttpServer;

public class ShopRESTStartUp {
	
static final String BASE_URI = "http://localhost:8080/shop";
	
	public static void main(String[] args) {
		
		try {
            HttpServer server = HttpServerFactory.create(BASE_URI);
            server.start();
            System.out.println("Press Enter to stop the server. ");
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
	}
}
