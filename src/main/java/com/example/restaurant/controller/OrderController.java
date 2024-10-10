package com.example.restaurant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.restaurant.service.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@PostMapping("/create/{userId}")
	public ResponseEntity<?> createOrder(@RequestParam String orderItems, @PathVariable Long userId, @RequestParam Double price)
	{
		return new ResponseEntity<Object>(orderService.createOrder(orderItems, userId, price), HttpStatus.OK);
	}
	
	@GetMapping("/getOrder/{orderId}")
	public ResponseEntity<?> getOrder(@PathVariable Long orderId)
	{
		return new ResponseEntity<Object>(orderService.getOrder(orderId), HttpStatus.OK);
	}
	
	@GetMapping("/getOrderByUser/{userId}")
	public ResponseEntity<?> getOrderByUser(@PathVariable Long userId)
	{
		return new ResponseEntity<Object>(orderService.createOgetOrderByUserrder(userId), HttpStatus.OK);
	}
}
