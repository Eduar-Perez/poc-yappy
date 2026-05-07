package co.com.periferia.payment.yappy.dto.response;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TxResponseDTO {
	private Long id;
	private String merchantid;
	private String orderId;
	private Date paymentDate;
	private String ipnUrl;
	private String domain;
	private String status;
}
