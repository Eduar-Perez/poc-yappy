package co.com.periferia.payment.yappy.service;

import co.com.periferia.payment.yappy.dto.TxDTO;

public interface TxService {

	TxDTO getTransact(String orderId);
	void updateTx(String orderId, String status);
	void createTx(String cc, String orderId, double total, String description, String paymentMeans) throws Exception;
	String createOrderId();

}
