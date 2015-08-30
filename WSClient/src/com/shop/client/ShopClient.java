package com.shop.client;

import java.util.List;

import com.shop.clientui.ClientUI;
import com.shop.soap.ws.Product;
import com.shop.soap.ws.ProductArray;
import com.shop.soap.ws.Shop;
import com.shop.soap.ws.ShopImplService;

public class ShopClient {

	public static void main(String[] args) {

		ShopImplService shopService = new ShopImplService();
		Shop shop = shopService.getShopImplPort();
		
		ProductArray products = shop.showProducts();
		
		List<Product> productsList = products.getItem();
		
		System.out.println("productsList :"+productsList);
		ClientUI client1 = new ClientUI("1", shop, productsList);
		client1.createAndShowGUI(false);
		
		Product eachProduct = null;
		for (int i=0; i<productsList.size(); i++) {
			eachProduct = (Product) productsList.get(i);
			System.out.println("Name:"+eachProduct.getProductName());
		} 
	}
}
