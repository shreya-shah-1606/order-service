package com.ann.order.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ann.order.entity.Order;
import com.ann.order.repository.OrderRepository;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	public List<Order> getAllOrders(){
		List<Order> product = orderRepository.findAll();
		return product;
	}
	
	public Order saveOrder(Order order) {
		return orderRepository.save(order);
	}

}
