package co.com.periferia.payment.yappy.mapper;

import co.com.periferia.payment.yappy.dto.TxDTO;
import co.com.periferia.payment.yappy.entity.TxYampyEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TxYappyMapper {

	public TxDTO toDto(TxYampyEntity entity) {
		if (entity == null)
			return new TxDTO();

		TxDTO dto = new TxDTO();

		dto.setId(entity.getId());
		dto.setOrderId(entity.getOrderId());
		dto.setPaymentDate(entity.getPaymentDate());
		dto.setIpnUrl(entity.getIpnUrl());
		dto.setStatus(entity.getStatus());
		dto.setPayment(entity.getBilling().getTotal());

		return dto;
	}

	public TxYampyEntity toEntity(TxDTO dto) {

		if (dto == null)
			return new TxYampyEntity();

		TxYampyEntity entity = new TxYampyEntity();

		entity.setId(dto.getId());
		entity.setOrderId(dto.getOrderId());
		entity.setPaymentDate(dto.getPaymentDate());
		entity.setIpnUrl(dto.getIpnUrl());
		entity.setStatus(dto.getStatus());

		return entity;
	}

}
