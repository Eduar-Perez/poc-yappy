package co.com.periferia.payment.yappy.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import co.com.periferia.payment.yappy.dto.TxYappyMapper;
import co.com.periferia.payment.yappy.dto.response.TxResponseDTO;
import co.com.periferia.payment.yappy.entity.TxYampyEntity;
import co.com.periferia.payment.yappy.repository.TxRepository;
import co.com.periferia.payment.yappy.service.TxService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class TxServiceImpl implements TxService {

	private  final TxRepository txRepository;

	@Override
	public TxResponseDTO getTransact(String orderId) {
		log.info("Ingresa al servicio de consultar transacción en el service: con orderId {}", orderId);

		Optional<TxYampyEntity> txRs = txRepository.findTopByOrderIdOrderByPaymentDateDesc(orderId);
		TxYappyMapper txMapper = new TxYappyMapper();

		return txMapper.toDto(txRs.get());
	}

	@Override
	public void updateTx(String orderId, String status) {
		log.info("Ingresa al servicio de actualizar transacción en el service: con orderId {}", orderId);

		Optional<TxYampyEntity> txEntity = txRepository.findTopByOrderIdOrderByPaymentDateDesc(orderId);
		txEntity.get().setStatus(status);

		log.info("Tx a modificar: {}", txEntity.get());
		txRepository.save(txEntity.get());

	}



}
