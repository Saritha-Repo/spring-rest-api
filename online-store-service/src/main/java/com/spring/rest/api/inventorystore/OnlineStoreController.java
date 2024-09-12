/**
 * 
 */
package com.spring.rest.api.inventorystore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * 
 */
@RestController
@RequestMapping("/store")
public class OnlineStoreController {
	
	@Autowired 
	private RestTemplate restTempalte;
	
	@PostMapping("/placeorder")
	public ResponseEntity<String> placeOrder(@RequestParam String productId,@RequestParam int quantity, @RequestParam double amount){
		
		//Step 1 : Check Inventory
		String inventoryUrl ="http://localhost:8081/inventory/check?productId= "+ productId + "&quantity=" + quantity;
		ResponseEntity<String> inventoryResponse = restTempalte.getForEntity(inventoryUrl, String.class);
		if(inventoryResponse.getStatusCode() == HttpStatus.NOT_FOUND) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Product is out of stock");
		}
		
		//Create the Order
		String orderUrl ="http://localhost:8082/orders/create?productId="+ productId + "&quantity=" + quantity;
		ResponseEntity<String> orderResponse = restTempalte.getForEntity(orderUrl, String.class);
		
		String orderId = orderResponse.getBody().split(":")[1];
		
		//Step 3 : Process the payment
		String paymentUrl ="http://localhost:8083/payment/process?orderId= "+ orderId + "&amount=" + amount;
		ResponseEntity<String> paymentResponse = restTempalte.getForEntity(paymentUrl, String.class);
		
		return ResponseEntity.ok("Order Placed Sucessfully " + paymentResponse.getBody());
	}


}
