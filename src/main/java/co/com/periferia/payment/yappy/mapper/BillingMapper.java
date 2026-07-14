package co.com.periferia.payment.yappy.mapper;

import co.com.periferia.payment.yappy.dto.BillingDTO;
import co.com.periferia.payment.yappy.entity.BillingEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BillingMapper {

	 public BillingDTO toDto(BillingEntity entity) {

	        BillingDTO dto = new BillingDTO();

	        dto.setIdBilling(entity.getId());
	        dto.setDiscount(entity.getDiscount());
	        dto.setTaxes(entity.getTaxes());
	        dto.setSubtotal(entity.getSubtotal());
	        dto.setTotal(entity.getTotal());
	        dto.setValueMin(entity.getValueMin());
	        dto.setValueMax(entity.getValueMax());

	        return dto;
	    }
	
}
