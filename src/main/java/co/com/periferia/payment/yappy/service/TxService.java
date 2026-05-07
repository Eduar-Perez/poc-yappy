package co.com.periferia.payment.yappy.service;

import co.com.periferia.payment.yappy.dto.response.TxResponseDTO;

public interface TxService {

	TxResponseDTO getTransact(String orderId);
	void updateTx(String orderId, String status);

}
