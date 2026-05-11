package co.com.periferia.payment.yappy.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TxDTO {
	private Long id;
	private String orderId;
	private LocalDateTime paymentDate;
	private String ipnUrl;
	private String status;
	private double payment;
	
}
