package com.shop.rest.ws;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;

import java.util.Iterator;

import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.shop.management.Product;
import com.shop.management.ShopManagement;


@Path("/shop")
public class ShopREST {
	
	@GET
	@Path("/allProducts")
	@Produces({MediaType.APPLICATION_XML})
	public CartInfo[] getAllProducts() {
		Product[] product = ShopManagement.getProducts();
		CartInfo[] cart = new CartInfo[product.length];
		System.out.println(cart.length);
		System.out.println(product.length);
		for(int i = 0;i < product.length;i++)
		{
			System.out.println(i);
			cart[i] = new CartInfo();
			cart[i].price = product[i].getPrice();
			cart[i].productName = product[i].getProductName();
			//cart[i].setQuantity(product[i].getQuantity());
			cart[i].quantityAvailable = product[i].getQuantityAvailable();
		}
		return cart;
	}
	@POST
	@Path("/Order")
	@Consumes ({MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON})
	public String placeOrder(CartInfo[] cart) {
		System.out.println("Service got called.");
		Product[] products = new Product[cart.length];
		int i = 0;
		while(i != cart.length)
		{
			products[i] = new Product();
			products[i].setProductName(cart[i].getProductName());
			products[i].setQuantity(cart[i].getQuantity());
			i++;
		}
		return ShopManagement.placeOrder(products);
	}
}
