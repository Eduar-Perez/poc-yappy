package co.com.periferia.payment.yappy.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.periferia.payment.yappy.dto.response.TxResponseDTO;
import co.com.periferia.payment.yappy.service.TxService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/tx")
@RequiredArgsConstructor
@Slf4j
public class TxController {

	private final TxService txService;

	@GetMapping("/get/{orderId}")
	public ResponseEntity<TxResponseDTO> getTx(@PathVariable String orderId) {
		log.info("Ingresa al controlador para consultar transacción con orderId {}", orderId);

		TxResponseDTO response = txService.getTransact(orderId);

		return ResponseEntity.ok(response);
	}

}
