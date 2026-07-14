package co.com.periferia.payment.yappy.service;

import co.com.periferia.payment.yappy.entity.BillingEntity;

public interface BillingService {
	
	BillingEntity crateBilling(BillingEntity billingEntity);
	BillingEntity getLastBilling(String cc);
}
