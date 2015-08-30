package com.shop.soap.ws;

import javax.jws.WebService;

import com.shop.management.Product;
import com.shop.management.ShopManagement;

@WebService(endpointInterface="com.shop.soap.ws.Shop")
public class ShopImpl implements Shop {

	@Override
	public Product[] showProducts() {
		return ShopManagement.getProducts();
	}

	@Override
	public String placeOrder(Product[] products) {
		System.out.println("Service got called.");
		return ShopManagement.placeOrder(products);
	}
}
