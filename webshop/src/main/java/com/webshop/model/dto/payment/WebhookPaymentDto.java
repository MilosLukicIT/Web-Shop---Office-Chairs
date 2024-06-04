package com.webshop.model.dto.payment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class WebhookPaymentDto {

	String type;
	Object data;
}
