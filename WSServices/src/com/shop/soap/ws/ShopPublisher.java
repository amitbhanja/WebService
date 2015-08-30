package com.shop.soap.ws;

import javax.xml.ws.Endpoint;

public class ShopPublisher {

	public static void main(String[] args) {
		Endpoint.publish("http://localhost:8090/ws/soap/shop", new ShopImpl());
	}
}
