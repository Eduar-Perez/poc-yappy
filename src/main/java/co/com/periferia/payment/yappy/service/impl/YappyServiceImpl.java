package co.com.periferia.payment.yappy.service.impl;

import co.com.periferia.payment.yappy.repository.TxRepository;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Instant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import co.com.periferia.payment.yappy.service.TxService;
import co.com.periferia.payment.yappy.service.YappyService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@Slf4j
@Getter
@Setter
@RequiredArgsConstructor
public class YappyServiceImpl implements YappyService {

	private final TxRepository txRepository;
	private final TxService txService;

	@Value("${yappy.merchant-id}")
	private String merchantID;

	@Value("${yappy.secret-key}")
	private String secretKey;

	@Value("${yappy.base-url}")
	private String baseUrl;

	@Value("${yappy.endpoint-token}")
	private String endpointToken;

	@Value("${yappy.endpoint-order}")
	private String endpointOrder;

	@Value("${yappy.webhook-url}")
	private String webHookUrl;

	@Value("${yappy.domain}")
	private String domain;

	@Override
	public String getAutorizathionToken() throws IOException, InterruptedException {

		long epochTime = Instant.now().toEpochMilli();
		String requestBody = 
				String.format("{\"merchantId\":\"%s\", \"requestDate\":%d}", merchantID, epochTime);

		log.info("Inicia obtención de token servicios YAPPY");
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(baseUrl + endpointToken))
				.header("Content-Type", "application/json")
				.header("x-merchant-id", merchantID)
				.header("x-secret-key", secretKey)
				.POST(HttpRequest.BodyPublishers.ofString(requestBody))
				.build();

		HttpClient client = HttpClient.newBuilder()
				.version(HttpClient.Version.HTTP_1_1)
				.build();

		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

		if(response.statusCode() == 200) {
			log.info("Termina obtención de token servicios YAPPY con exito");
			
			ObjectMapper mapper = new ObjectMapper();
			JsonNode json = mapper.readTree(response.body());

			return json.get("token").asText();
		} else {
			throw new RuntimeException("Error obteniendo token de autorización en YAPPY: " + response.statusCode());
		}

	}

	@Override
	public String createOrdenPayment(String token, double total, String cc, String description, String paymentsMeans) throws Exception {
		String orderId = "ORDER-" + txService.createOrderId();

		String requestBody = String.format("{\"orderId\": \"%s\", \"total\": %.2f, \"currency\": "
				+ "\"USD\",\"webhookUrl\": \"%s\"}", orderId, total, webHookUrl);

		log.info("Inicia creación de orden servicios YAPPY");
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(baseUrl + endpointOrder))
				.header("Content-Type","application/json")
				.header("Authorization", "Bearer " + token)
				.POST(HttpRequest.BodyPublishers.ofString(requestBody))
				.build();

		HttpClient client = HttpClient.newBuilder()
				.version(HttpClient.Version.HTTP_1_1)
				.build();

		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
		
		ObjectMapper mapper = new ObjectMapper();
		JsonNode jsonNode = mapper.readTree(response.body());

		if(response.statusCode() == 200 || response.statusCode() == 201) {
			log.info("Termina creación de orden servicios YAPPY");
			
			txService.createTx(cc, orderId, total, description, paymentsMeans);
			((com.fasterxml.jackson.databind.node.ObjectNode) jsonNode).put("orderId", orderId);

			return mapper.writeValueAsString(jsonNode);
		} else {

			return mapper.writeValueAsString(jsonNode);
		}

	}

}
