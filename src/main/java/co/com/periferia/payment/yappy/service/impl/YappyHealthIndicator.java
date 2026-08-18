package co.com.periferia.payment.yappy.service.impl;

import java.io.IOException;

import org.jspecify.annotations.Nullable;
import org.springframework.boot.health.contributor.Health;
import org.springframework.boot.health.contributor.HealthIndicator;
import org.springframework.stereotype.Component;

import co.com.periferia.payment.yappy.service.YappyService;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class YappyHealthIndicator implements HealthIndicator {

	private final YappyService yappyService;

	@Override
	public @Nullable Health health() {
		try {
			String token = yappyService.getAutorizathionToken();

			if(token != null && !token.isBlank()) {
				return Health.up()
						.withDetail("message", "Yappy disponible")
						.status("200")
						.build();
			}

			return Health.down()
					.withDetail("message", "No se obtuvo el token")
					.status("400")
					.build();

		} catch (IOException | InterruptedException e) {
			return Health.down()
					.withDetail("error", "Error no conocido")
					.status("500")
					.build();
		}
	}

}
