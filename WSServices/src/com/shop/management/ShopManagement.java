package com.shop.management;


public class ShopManagement {
	static Product[] products = new Product[3];
	
	static {
		Product product = new Product();
		product.setProductId("1");
		product.setProductName("P1");
		product.setPrice(100.99);
		product.setQuantityAvailable(100);
		products[0] = product;
		
		product = new Product();
		product.setProductId("2");
		product.setProductName("P2");
		product.setPrice(10.99);
		product.setQuantityAvailable(10);
		
		products[1] = product;
		
		product = new Product();
		product.setProductId("3");
		product.setProductName("P3");
		product.setPrice(1.99);
		product.setQuantityAvailable(5);
		products[2] = product;
	}
	
	public static Product[] getProducts() {
		return products;
	}
	
	public static String placeOrder(Product[] orderedProducts) {
		String status = "SUCCESS~Order is placed.";
		
		boolean qtyAvailable = true;
		
		for (Product p: products) {
			for (Product eachOrderedProduct: orderedProducts) {
				if (p.getProductName().equals(eachOrderedProduct.getProductName())) {
					if (p.getQuantityAvailable() < eachOrderedProduct.getQuantity()) {
						qtyAvailable = false;
						break;
					}
				}	
			}
		}
		if (qtyAvailable) {
			for (Product p: products) {
				for (Product eachOrderedProduct: orderedProducts) {
					if (p.getProductName().equals(eachOrderedProduct.getProductName()))
						p.setQuantityAvailable(p.getQuantityAvailable() - eachOrderedProduct.getQuantity());
				}	
			}
		}
		else
			status = "SORRY~Ordered amount is not available.";
		
		return status;
	}
}
