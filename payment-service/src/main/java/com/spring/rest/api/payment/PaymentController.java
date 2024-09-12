/**
 * 
 */
package com.spring.rest.api.payment;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 */
@RestController
@RequestMapping("/payment")
public class PaymentController {
	
	@PostMapping("/process")
	public ResponseEntity<String> process(@RequestParam String orderId, @RequestParam double amount){
		
		return ResponseEntity.ok("Payment Processed for the order " + orderId + " amount " + amount);
	}

}
