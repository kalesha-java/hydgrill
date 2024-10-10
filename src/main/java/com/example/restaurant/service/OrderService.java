package com.example.restaurant.service;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.restaurant.model.OrderEntity;
import com.example.restaurant.repository.OrderRespository;

import net.bytebuddy.asm.Advice.OffsetMapping.Sort;

@Service
public class OrderService {

	@Autowired
	private OrderRespository orderRepository;

	public OrderEntity createOrder(String order, Long userId, Double price) {
		
		OrderEntity data = new OrderEntity();
		data.setOrderJson(order.toString());
		data.setUserId(userId);
		data.setOrderPrice(price);
		data.setCreatedDate(new Date());
		
		return orderRepository.save(data);
	}

	public OrderEntity getOrder(Long orderId) {
		return orderRepository.getById(orderId);
	}

	public List<OrderEntity> createOgetOrderByUserrder(Long userId) {
		
		return orderRepository.findByUserId(userId).stream().sorted(Comparator.comparing(OrderEntity::getCreatedDate).reversed()) // Sort by createdDate in descending order
			    .collect(Collectors.toList());
	}
	
	
}
