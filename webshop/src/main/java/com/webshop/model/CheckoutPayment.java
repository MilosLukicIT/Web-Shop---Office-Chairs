package com.webshop.model;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CheckoutPayment {

		private String currency;
		private String successUrl;
		private String cancelUrl;
		private BigDecimal amount;
		private String orderId;

}
