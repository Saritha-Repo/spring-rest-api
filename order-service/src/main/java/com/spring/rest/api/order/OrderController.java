/**
 * 
 */
package com.spring.rest.api.order;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 */
@RestController
@RequestMapping("/orders")
public class OrderController {
	
	@PostMapping("/create")
	public ResponseEntity<String> createOder(@RequestParam String productId, @RequestParam int quantity) {
		String orderId = "ORD" + System.currentTimeMillis();
		
		return ResponseEntity.ok("Order Created Successfully with Order ID "+ orderId);
	}

}
