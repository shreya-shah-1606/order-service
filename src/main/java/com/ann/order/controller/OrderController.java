package com.ann.order.controller;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ann.order.entity.Order;
import com.ann.order.service.OrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/orders/")
public class OrderController {

	@Autowired
	OrderService orderService;

	@RequestMapping(value = "getAllOrders", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Get Orders", description = "Get Orders")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
			@ApiResponse(responseCode = "401", description = "Unauthorized"),
			@ApiResponse(responseCode = "403", description = "Forbidden"),
			@ApiResponse(responseCode = "404", description = "Not Found") })
	public String getAllOrders(HttpServletRequest request, HttpServletResponse response) {
		String res = null;
		ObjectMapper Obj = new ObjectMapper();
		List<Order> orders = orderService.getAllOrders();
		response.setStatus(200);
		try {
			res = Obj.writeValueAsString(orders);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return res;
	}

	@RequestMapping(value = "addOrder", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Add Order", description = "Add Order")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
			@ApiResponse(responseCode = "201", description = "Created"),
			@ApiResponse(responseCode = "401", description = "Unauthorized"),
			@ApiResponse(responseCode = "403", description = "Forbidden"),
			@ApiResponse(responseCode = "404", description = "Bad Request") })
	public Order addEBook(@RequestBody @Valid Order product, HttpServletRequest request, HttpServletResponse response) {
		return orderService.saveOrder(product);
	}
}
