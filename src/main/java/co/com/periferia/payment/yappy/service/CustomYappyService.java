package co.com.periferia.payment.yappy.service;

import co.com.periferia.payment.yappy.dto.response.CustomerPaymentResponseDTO;

public interface CustomYappyService {

	CustomerPaymentResponseDTO getCustomer(String cc);

}
