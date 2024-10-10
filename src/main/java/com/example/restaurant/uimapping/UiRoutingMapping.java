package com.example.restaurant.uimapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UiRoutingMapping {

	@RequestMapping("/")
	public String index()
	{
		return "index.html";
	}
	
	@RequestMapping("/index.html")
	public String index1()
	{
		return "index.html";
	}
	
	@RequestMapping("/about.html")
	public String about()
	{
		return "about.html";
	}
	
	@RequestMapping("/cart.html")
	public String cart()
	{
		return "cart.html";
	}
	
	@RequestMapping("/checkout.html")
	public String checkout()
	{
		return "checkout.html";
	}
	
	@RequestMapping("/contact.html")
	public String contact()
	{
		return "contact.html";
	}
	
	@RequestMapping("/reservation.html")
	public String reservation()
	{
		return "reservation.html";
	}
	
	@RequestMapping("/shop-details.html")
	public String shopDetails()
	{
		return "shop-details.html";
	}
	
	@RequestMapping("/shop.html/*")
	public String shop()
	{
		return "shop.html";
	}
	
	@RequestMapping("/wishlist.html")
	public String wishlist()
	{
		return "wishlist.html";
	}
	
	@RequestMapping("/orders.html")
	public String orders()
	{
		return "orders.html";
	}
}
