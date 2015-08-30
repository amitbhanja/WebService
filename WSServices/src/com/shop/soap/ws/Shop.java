package com.shop.soap.ws;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import com.shop.management.Product;

@WebService
@SOAPBinding (style=Style.RPC)
public interface Shop {
	
	@WebMethod Product[] showProducts();

	//@WebMethod String placeOrder(String userId, Product[] products);
	
	@WebMethod String placeOrder(Product[] products);
}
