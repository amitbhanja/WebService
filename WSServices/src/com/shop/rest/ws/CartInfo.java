package com.shop.rest.ws;

import java.util.Map;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CartInfo {
	
	String productName;
	int quantity;
	double price;
	long quantityAvailable;
	
	public int getQuantity() {
		return quantity;
	}
	
	@XmlElement
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
	public String getProductName() {
		return productName;
	}
	
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public long getQuantityAvailable() {
		return quantityAvailable;
	}
	
	public void setQuantityAvailable(long quantityAvailable) {
		this.quantityAvailable = quantityAvailable;
	}
}
