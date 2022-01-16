package com.theatre.models;

import java.util.ArrayList;
import java.util.List;

public class CartDTO  {

	private String user;
	private List<TicketsDTO> cartItems = new ArrayList<TicketsDTO>();
	private double totalCost;

	public CartDTO() {
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public List<TicketsDTO> getCartItems() {
		return cartItems;
	}

	public void setCartItems(List<TicketsDTO> cartItems) {
		this.cartItems = cartItems;
	}

	public double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}

}
