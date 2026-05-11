package co.com.periferia.payment.yappy.service.impl;

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
		log.info("Iniciando consulta del cliente. CC={}", cc);

		try {
			Optional<CustomerYampyEntity> customerOpt = customerYappyRepository.getCustomer(cc);

			if (customerOpt.isEmpty()) {
				log.warn("No se encontró el cliente con CC={}", cc);
				return new CustomerPaymentResponseDTO();
			}

			CustomerYampyEntity customer = customerOpt.get();

			log.info("Cliente encontrado correctamente. {}", customer.getCc());

			CustomerPaymentResponseDTO response = customerYappyMapper.toDto(customer);
			log.info("Transformación a DTO completada exitosamente para CC={}", cc);

			return response;

		} catch (Exception e) {
			log.error("Error al consultar el cliente. CC={}, Mensaje={}", cc , e.getMessage(), e);

			throw new RuntimeException("Error al obtener la información del cliente: " + cc, e);
		}
	}

	@Override
	public List<CustomerYampyEntity> getdataUserAdmin() {
		log.info("Iniciando consulta de datos para administrador total");

		List<CustomerYampyEntity> allDataTx = new ArrayList<>();
		allDataTx = customerYappyRepository.getAllUsers().get();

		log.info("Se consulta los datos para administrador total");

		return allDataTx;
	}

}
