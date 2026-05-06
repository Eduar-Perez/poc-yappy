package co.com.periferia.payment.yappy.service;

import co.com.periferia.payment.yappy.dto.response.UserYappyResponseDTO;

public interface CustomYappyService {

	UserYappyResponseDTO getCustomer(String cc);

}
