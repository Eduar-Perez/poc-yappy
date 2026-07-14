package co.com.periferia.payment.yappy.service;

import java.time.LocalDateTime;
import java.util.List;

import co.com.periferia.payment.yappy.dto.response.CustomerPaymentResponseDTO;
import co.com.periferia.payment.yappy.entity.CustomerYampyEntity;

public interface CustomerYappyService {

	CustomerPaymentResponseDTO getCustomer(String cc);
	List<CustomerYampyEntity> getdataUserAdmin(String status, LocalDateTime startDate, LocalDateTime endDate);

}
