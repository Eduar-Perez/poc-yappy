package co.com.periferia.payment.yappy.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import co.com.periferia.payment.yappy.dto.CustomerYappyMapper;
import co.com.periferia.payment.yappy.dto.response.UserYappyResponseDTO;
import co.com.periferia.payment.yappy.entity.CustomerYampyEntity;
import co.com.periferia.payment.yappy.repository.CustomerYappyRepository;
import co.com.periferia.payment.yappy.service.CustomYappyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomYappyServiceImpl implements CustomYappyService{

	private final CustomerYappyRepository customerYappyRepository;

	@Override
	public UserYappyResponseDTO getCustomer(String cc) {
		log.info("Iniciando consulta del cliente. CC={}", cc);

		try {
			Optional<CustomerYampyEntity> customerOpt = customerYappyRepository.getCustomer(cc);

			if (customerOpt.isEmpty()) {
				log.warn("No se encontró el cliente con CC={}", cc);

				throw new RuntimeException("Cliente no encontrado: " + cc);
			}

			CustomerYampyEntity customer = customerOpt.get();

			log.info("Cliente encontrado correctamente. CC={}, Nombre={}", customer.getCc(),customer.getName());

			UserYappyResponseDTO response = CustomerYappyMapper.toDto(customer);

			log.info("Transformación a DTO completada exitosamente para CC={}", cc);

			return response;

		} catch (Exception e) {
			log.error("Error al consultar el cliente. CC={}, Mensaje={}", cc , e.getMessage(), e);

			throw new RuntimeException("Error al obtener la información del cliente: " + cc, e);
		}
	}

}
