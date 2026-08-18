package co.com.periferia.payment.yappy.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import co.com.periferia.payment.yappy.dto.response.CustomerPaymentResponseDTO;
import co.com.periferia.payment.yappy.entity.CustomerYampyEntity;
import co.com.periferia.payment.yappy.mapper.CustomerYappyMapper;
import co.com.periferia.payment.yappy.repository.CustomerYappyRepository;
import co.com.periferia.payment.yappy.service.CustomerYappyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomYappyServiceImpl implements CustomerYappyService {

	private final CustomerYappyRepository customerYappyRepository;
	private final CustomerYappyMapper customerYappyMapper = new CustomerYappyMapper();

	@Override
	public CustomerPaymentResponseDTO getCustomer(String cc) {
		log.info("Consultando cliente. CC={}", cc);

		try {
			Optional<CustomerYampyEntity> customerOpt = customerYappyRepository.getCustomer(cc);

			if (customerOpt.isEmpty()) {
				log.warn("No se encontró el cliente con CC={}", cc);
				throw new Exception("El usuario ingresado no se encuentra registrado con CC: {}" + cc);
			}

			CustomerYampyEntity customer = customerOpt.get();

			CustomerPaymentResponseDTO response = customerYappyMapper.toDto(customer);

			return response;

		} catch (Exception e) {
			log.error("Error al consultar el cliente. CC={}, Mensaje={}", cc , e.getMessage(), e);
			throw new RuntimeException("Error al obtener la información del cliente: " + cc, e);
		}
	}

	@Override
	public List<CustomerYampyEntity> getdataUserAdmin(String status, LocalDateTime startDate, LocalDateTime endDate) {
		log.info("Consulta de datos para administrador total");

		List<CustomerYampyEntity> allDataTx = new ArrayList<>();
		allDataTx = customerYappyRepository.getAllUsers(status, startDate, endDate);

		log.info("Termina consulta de datos para administrador total");

		return allDataTx;
	}

}
