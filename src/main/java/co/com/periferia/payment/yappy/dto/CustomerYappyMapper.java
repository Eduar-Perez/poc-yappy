package co.com.periferia.payment.yappy.dto;

import co.com.periferia.payment.yappy.dto.response.UserYappyResponseDTO;
import co.com.periferia.payment.yappy.entity.CustomerYampyEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerYappyMapper {


	public static UserYappyResponseDTO toDto(CustomerYampyEntity customer) {

		if (customer == null) {
			return null;
		}

		UserYappyResponseDTO dto = new UserYappyResponseDTO();

		dto.setCc(customer.getCc());
		dto.setName(customer.getName());
		dto.setEmail(customer.getEmail());
		dto.setPhone(customer.getPhone());
		dto.setDocumentType(customer.getDocumentType());

		if (customer.getTx() != null) {
			dto.setMerchantid(customer.getTx().getMerchantid());
			dto.setOrderId(customer.getTx().getOrderId());
			dto.setPaymentDate(customer.getTx().getPaymentDate());
			dto.setIpnUrl(customer.getTx().getIpnUrl());
			dto.setDomain(customer.getTx().getDomain());
			dto.setStatus(customer.getTx().getStatus());

			if (customer.getTx().getBilling() != null) {
				dto.setDiscount(customer.getTx().getBilling().getDiscount());
				dto.setTaxes(customer.getTx().getBilling().getTaxes());
				dto.setSubtotal(customer.getTx().getBilling().getSubtotal());
				dto.setTotal(customer.getTx().getBilling().getTotal());
				dto.setValueMin(customer.getTx().getBilling().getValueMin());
				dto.setValueMax(customer.getTx().getBilling().getValueMax());
			}
		}

		return dto;
	}

}

