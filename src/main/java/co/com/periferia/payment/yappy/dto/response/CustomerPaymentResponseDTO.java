package co.com.periferia.payment.yappy.dto.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import co.com.periferia.payment.yappy.dto.BillingDTO;
import co.com.periferia.payment.yappy.dto.TxDTO;
import co.com.periferia.payment.yappy.dto.UserDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonPropertyOrder({
	"customer",
	"transaction",
	"billing"
})
public class CustomerPaymentResponseDTO {
	private UserDTO customer;
	private TxDTO transaction;
	private BillingDTO billing;
}
