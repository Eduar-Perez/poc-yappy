package co.com.periferia.payment.yappy.dto.response;

import java.util.Date;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class UserYappyResponseDTO {

	//User
	private String name;
	private String email;
	private String phone;
	private String cc;
	private String documentType;

	//TX
	private String merchantid;
	private String orderId;
	private Date paymentDate;
	private String ipnUrl;
	private String domain;

	//Billing
	private String discount;
	private double taxes;
	private double subtotal;
	private double total;
	private double valueMin;
	private double valueMax;

}
