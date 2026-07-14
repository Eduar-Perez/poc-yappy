package co.com.periferia.payment.yappy.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {

	private String name;
	private String email;
	private String phone;
	private String cc;
	private String documentType;
	private String role;
	private TxDTO tx;
	private BillingDTO billingResponseDTO;

}
