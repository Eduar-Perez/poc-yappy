package co.com.periferia.payment.yappy.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.com.periferia.payment.yappy.service.TxService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api")
@Slf4j
@RequiredArgsConstructor
public class YappyWebhookController {

	private final TxService txService;

	@GetMapping("/yappy-webhook")
	public ResponseEntity<String> recibirConfirmacionPago(
			@RequestParam("orderId") String orderId,
			@RequestParam("status") String status,
			@RequestParam("hash") String hash) {

		log.info("Webhook recibido - orderId: {}, status: {}", orderId, status);
		txService.updateTx(orderId, status);

		if ("SUCCESS".equalsIgnoreCase(status) || "E".equalsIgnoreCase(status)) {
			log.info("Pago confirmado para orden {}", orderId);
			return ResponseEntity.ok("OK");
		} else {
			log.warn("Pago no exitoso para orden {}", orderId);
			return ResponseEntity.badRequest().body("Estado no exitoso: " + status);
		}

	}

}
