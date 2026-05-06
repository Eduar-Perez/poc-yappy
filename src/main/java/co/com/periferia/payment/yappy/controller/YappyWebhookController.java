package co.com.periferia.payment.yappy.controller;

import java.util.concurrent.TimeUnit;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api")
@Slf4j
public class YappyWebhookController {

	@GetMapping("/yappy-webhook")
	public ResponseEntity<String> recibirConfirmacionPago(
			@RequestParam("orderId") String orderId,
			@RequestParam("status") String status,
			@RequestParam("hash") String hash) {

		log.info("Webhook recibido - orderId: {}, status: {}", orderId, status);

		try {
			TimeUnit.SECONDS.sleep(30);
		} catch (Exception e) {
			Thread.currentThread().interrupt();
			log.error("Error en delay del webhook", e);
		}
		if ("SUCCESS".equalsIgnoreCase(status)) {
			log.info("Pago confirmado para orden {}", orderId);
			return ResponseEntity.ok("OK");
		} else {
			log.warn("Pago no exitoso para orden {}", orderId);
			return ResponseEntity.badRequest().body("Estado no exitoso");
		}

	}

}
