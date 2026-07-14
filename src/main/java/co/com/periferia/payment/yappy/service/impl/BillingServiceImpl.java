package co.com.periferia.payment.yappy.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import co.com.periferia.payment.yappy.entity.BillingEntity;
import co.com.periferia.payment.yappy.repository.BillingRepository;
import co.com.periferia.payment.yappy.service.BillingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class BillingServiceImpl implements BillingService {

	private final BillingRepository billingRepository;

	@Override
	public BillingEntity crateBilling(BillingEntity billingEntity) {
		log.info("Creando facturación");
		BillingEntity billingSave = billingRepository.save(billingEntity);
		return billingSave;

	}

	@Override
	public BillingEntity getLastBilling(String cc) {
		log.info("Obteniendo facturación");
		List<BillingEntity> billings = billingRepository.findLastBillingByCustomerCc(cc);

		return billings.isEmpty() ? null : billings.get(0);

	}


}
