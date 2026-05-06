package co.com.periferia.payment.yappy.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class PaymentRequestDTO {

	private String orderId;
	private double total;

}
