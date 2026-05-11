package co.com.periferia.payment.yappy.controller;

import co.com.periferia.payment.yappy.dto.request.PaymentRequestDTO;
import co.com.periferia.payment.yappy.service.impl.YappyServiceImpl;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments/yappy")
@Slf4j
public class YappyController {

	private final YappyServiceImpl yappyServiceImpl;

	YappyController(YappyServiceImpl yappyServiceImpl) {
		this.yappyServiceImpl = yappyServiceImpl;
	}

	@PostMapping("/createPayment")
	public ResponseEntity<?> createPayment(@RequestBody PaymentRequestDTO request) throws IOException, InterruptedException {
		log.info("Ingresa a crear orden en el controlador");

		String token = yappyServiceImpl.getAutorizathionToken();
		String response = yappyServiceImpl.createOrdenPayment(token, request.getTotal(), request.getCc());

		return ResponseEntity.ok(response);
	}

}
