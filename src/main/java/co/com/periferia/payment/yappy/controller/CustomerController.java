package co.com.periferia.payment.yappy.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import co.com.periferia.payment.yappy.dto.response.CustomerPaymentResponseDTO;
import co.com.periferia.payment.yappy.service.CustomYappyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
@Slf4j
public class CustomerController {

	private final CustomYappyService customYappyService;

	@GetMapping("/{cc}")
	public ResponseEntity<CustomerPaymentResponseDTO> getCustomer(@PathVariable String cc) {

		log.info("Solicitud recibida para consultar cliente. CC={}", cc);

		CustomerPaymentResponseDTO response = customYappyService.getCustomer(cc);

		log.info("Respuesta enviada correctamente para CC={}", cc);

		return ResponseEntity.ok(response);

	}

}