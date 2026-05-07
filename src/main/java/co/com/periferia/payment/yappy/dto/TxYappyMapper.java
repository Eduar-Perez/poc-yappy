package co.com.periferia.payment.yappy.dto;

import co.com.periferia.payment.yappy.dto.response.TxResponseDTO;
import co.com.periferia.payment.yappy.entity.TxYampyEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TxYappyMapper {

	public TxResponseDTO toDto(TxYampyEntity entity) {
		if (entity == null)
			return new TxResponseDTO();

		TxResponseDTO dto = new TxResponseDTO();

		dto.setId(entity.getId());
		dto.setMerchantid(entity.getMerchantid());
		dto.setOrderId(entity.getOrderId());
		dto.setPaymentDate(entity.getPaymentDate());
		dto.setIpnUrl(entity.getIpnUrl());
		dto.setDomain(entity.getDomain());
		dto.setStatus(entity.getStatus());

		return dto;
	}

	public TxYampyEntity toEntity(TxResponseDTO dto) {

		if (dto == null)
			return new TxYampyEntity();

		TxYampyEntity entity = new TxYampyEntity();

		entity.setId(dto.getId());
		entity.setMerchantid(dto.getMerchantid());
		entity.setOrderId(dto.getOrderId());
		entity.setPaymentDate(dto.getPaymentDate());
		entity.setIpnUrl(dto.getIpnUrl());
		entity.setDomain(dto.getDomain());
		entity.setStatus(dto.getStatus());

		return entity;
	}

}
