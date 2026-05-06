package co.com.periferia.payment.yappy.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class UserYampyRequestDTO {

	private String firstName;
	private String lastName;
	private String cc;

}
