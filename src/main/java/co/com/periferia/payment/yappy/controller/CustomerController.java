package co.com.periferia.payment.yappy.controller;

import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import co.com.periferia.payment.yappy.dto.request.RequestReportDTO;
import co.com.periferia.payment.yappy.dto.response.CustomerPaymentResponseDTO;
import co.com.periferia.payment.yappy.service.CustomerYappyService;
import co.com.periferia.payment.yappy.service.impl.ExcelReportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
@Slf4j
public class CustomerController {

	private final CustomerYappyService customYappyService;
	private final ExcelReportService excelReportService;

	@GetMapping("/{cc}")
	public ResponseEntity<CustomerPaymentResponseDTO> getCustomer(@PathVariable String cc) {

		log.info("Solicitud recibida para consultar cliente. CC={}", cc);

		CustomerPaymentResponseDTO response = customYappyService.getCustomer(cc);

		log.info("Respuesta enviada correctamente para CC={}", cc);

		return ResponseEntity.ok(response);

	}

	@PostMapping("/generar-report")
	public ResponseEntity<byte[]> generarExcel(@RequestBody RequestReportDTO request) throws IOException {
		log.info("Solicitud recibida para generar excel");

		byte[] excel = excelReportService.generateExcel(request.getStatus(), request.getStartDate(), request.getEndDate());

		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
				.header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=reporte-clientes.xlsx")
				.body(excel);
	}

}