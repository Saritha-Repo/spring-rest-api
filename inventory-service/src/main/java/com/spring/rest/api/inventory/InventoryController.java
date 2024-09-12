/**
 * 
 */
package com.spring.rest.api.inventory;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 */
@RestController
@RequestMapping("/inventory")
public class InventoryController {
	
	@GetMapping("/check")
	public ResponseEntity<String> checkInventory(@RequestParam String productId,@RequestParam int quantity) {
		
		boolean isAvailable = quantity <= 100;
		
		if(isAvailable) {
			return ResponseEntity.ok("Product is available");
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product is out of stock");
		}
	}

}
