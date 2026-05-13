package co.com.periferia.payment.yappy.dto.request;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestReportDTO {

	private String status;
	private LocalDateTime startDate;
	private LocalDateTime endDate;
}
