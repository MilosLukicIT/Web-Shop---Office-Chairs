package com.webshop.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import com.webshop.model.CheckoutPayment;
import com.webshop.model.dto.payment.CheckoutResponseDto;
import com.webshop.model.dto.payment.WebhookPaymentDto;
import com.webshop.service.CustomerOrderService;

@CrossOrigin
@RestController
@RequestMapping("payment")
public class StripeController {
	
	@Autowired
	private CustomerOrderService orderService;
	
	
	public StripeController(@Value("${custom.stripe.secret}") String secretKey) {
		Stripe.apiKey = secretKey;
	}
	
	@PostMapping
	/**
	 * Payment with Stripe checkout page
	 * 
	 * @throws StripeException
	 */
	public CheckoutResponseDto paymentWithCheckoutPage(@RequestBody CheckoutPayment payment) throws StripeException {
		long quantity = 1;
		BigDecimal amount = payment.getAmount().multiply(new BigDecimal(100));
		SessionCreateParams params = SessionCreateParams.builder()
				// We will use the credit card payment method 
				.addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
				.setMode(SessionCreateParams.Mode.PAYMENT)
				.setSuccessUrl(payment.getSuccessUrl() + "?orderId=" + payment.getOrderId())
				.setCancelUrl(payment.getCancelUrl() +  "?orderId=" + payment.getOrderId())
				.addLineItem(
						SessionCreateParams.LineItem.builder().setQuantity(quantity)
								.setPriceData(
										SessionCreateParams.LineItem.PriceData.builder()
												.setCurrency(payment.getCurrency())
												.setUnitAmountDecimal(amount)
												.setProductData(SessionCreateParams.LineItem.PriceData.ProductData
														.builder().setName(payment.getOrderId()).build())
												.build())
								.build())
				.build();
		Session session = Session.create(params);
	
		orderService.updateCustomerOrder(payment.getOrderId(), session.getId());
		
		return new CheckoutResponseDto(session.getId());
	}

	
	@PostMapping("/webhook")
	/**
	 * Payment with Stripe checkout page
	 * 
	 * @throws StripeException
	 */
	public ResponseEntity<Void> webhook(@RequestBody WebhookPaymentDto event) throws StripeException {

	     // Handle the event
	     switch (event.getType()) {
	       case "payment_intent.succeeded": {
	         // Then define and call a function to handle the event payment_intent.succeeded
	         break;
	       }
	       case "checkout.session.completed": {
	    	    String w;
	    	    w = event.getData().toString().substring(12, 78);
	    	    orderService.updateCustomerOrderPayment(w);
	    	   //Get order by session iD (event.getData().getId())
	    	   //updateOrder.setPaid(LocalDate.now())
		         // Then define and call a function to handle the event payment_intent.succeeded
		         break;
		       }
	       // ... handle other event types
	       default:
	         System.out.println("Unhandled event type: " + event.getType());
	     }

	     return ResponseEntity.ok().build();
	}
	
}
