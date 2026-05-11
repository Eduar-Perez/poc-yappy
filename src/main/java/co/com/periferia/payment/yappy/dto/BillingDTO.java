package co.com.periferia.payment.yappy.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class BillingDTO {
	private Long idBilling;
	private String discount;
	private double taxes;
	private double subtotal;
	private double total;
	private double valueMin;
	private double valueMax;
}
