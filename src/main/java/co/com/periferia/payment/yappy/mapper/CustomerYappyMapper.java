package co.com.periferia.payment.yappy.mapper;

import java.util.Comparator;
import java.util.Optional;

import co.com.periferia.payment.yappy.dto.UserDTO;
import co.com.periferia.payment.yappy.dto.response.CustomerPaymentResponseDTO;
import co.com.periferia.payment.yappy.entity.CustomerYampyEntity;
import co.com.periferia.payment.yappy.entity.TxYampyEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerYappyMapper {


	public CustomerPaymentResponseDTO toDto(CustomerYampyEntity entity) {

		CustomerPaymentResponseDTO response = new CustomerPaymentResponseDTO();

		UserDTO customerDto = new UserDTO();
		customerDto.setCc(entity.getCc());
		customerDto.setName(entity.getName());
		customerDto.setEmail(entity.getEmail());
		customerDto.setPhone(entity.getPhone());
		customerDto.setDocumentType(entity.getDocumentType());
		customerDto.setRole(entity.getRole());

		response.setCustomer(customerDto);

		Optional<TxYampyEntity> lastTx = entity.getTx()
				.stream()
				.max(Comparator.comparing(TxYampyEntity::getPaymentDate));

		if (lastTx.isPresent()) {

			TxYampyEntity tx = lastTx.get();

			TxYappyMapper txMapper = new TxYappyMapper();
			response.setTransaction(txMapper.toDto(tx));

			if (tx.getBilling() != null) {

				BillingMapper billingMapper = new BillingMapper();

				response.setBilling(
						billingMapper.toDto(tx.getBilling())
						);
			}
		}

		return response;
	}

}

