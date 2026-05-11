package co.com.periferia.payment.yappy.service.impl;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.com.periferia.payment.yappy.dto.TxDTO;
import co.com.periferia.payment.yappy.entity.BillingEntity;
import co.com.periferia.payment.yappy.entity.CustomerYampyEntity;
import co.com.periferia.payment.yappy.entity.TxYampyEntity;
import co.com.periferia.payment.yappy.mapper.TxYappyMapper;
import co.com.periferia.payment.yappy.repository.CustomerYappyRepository;
import co.com.periferia.payment.yappy.repository.TxRepository;
import co.com.periferia.payment.yappy.service.BillingService;
import co.com.periferia.payment.yappy.service.TxService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class TxServiceImpl implements TxService {

	private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	private static final SecureRandom RANDOM = new SecureRandom();

	private final TxRepository txRepository;
	private final BillingService billingService;
	private final CustomerYappyRepository customerYappyRepository;

	@Value("${yappy.ipnUrl}")
	private String ipnUrl;

	@Override
	@Transactional
	public void createTx(String cc, String orderId, double total) {
		log.info("Se inicia proceso de crear tx [{}]", cc);

		CustomerYampyEntity customer = customerYappyRepository
				.findById(cc)
				.orElseThrow(() ->
				new RuntimeException("Cliente no encontrado"));

		TxYampyEntity tx = new TxYampyEntity();
		tx.setOrderId(orderId);
		tx.setPaymentDate(LocalDateTime.now());
		tx.setIpnUrl(ipnUrl);
		tx.setCustomer(customer);
		tx.setStatus("PROCESS");

		BillingEntity billing = calculeBilling(total, billingService.getLastBilling(cc));
		tx.setBilling(billing);
		billing.setTx(tx);

		TxYampyEntity savedTx = txRepository.save(tx);
		log.info("Se crea la transacción con id N°: {} y N° de orden {} ",savedTx.getId(), savedTx.getOrderId());

	}

	@Override
	public TxDTO getTransact(String orderId) {
		log.info("Ingresa al servicio de consultar transacción en el service: con orderId {}", orderId);

		TxYampyEntity tx = txRepository
				.findTopByOrderIdOrderByPaymentDateDesc(orderId)
				.orElseThrow(() -> new RuntimeException("Transacción no encontrada"));

		TxYappyMapper txMapper = new TxYappyMapper();

		return txMapper.toDto(tx);
	}

	@Override
	public void updateTx(String orderId, String status) {
		log.info("Ingresa al servicio de actualizar transacción en el service: con orderId {}", orderId);

		Optional<TxYampyEntity> txEntity = txRepository.findTopByOrderIdOrderByPaymentDateDesc(orderId);
		txEntity.get().setStatus(status);

		log.info("Tx a modificar: {}", txEntity.get());
		txRepository.save(txEntity.get());

	}

	@Override
	public String createOrderId() {
		StringBuilder builder = new StringBuilder();
		for(int i = 0; i < 6; i++) {
			int index = RANDOM.nextInt(CHARACTERS.length());
			builder.append(CHARACTERS.charAt(index));
		}

		return builder.toString();

	}

	private BillingEntity calculeBilling(double total, BillingEntity billingEntity) {
		BillingEntity billing = new BillingEntity();

		double previousTotal = billingEntity != null ? billingEntity.getTotal() : 0;

		billing.setSubtotal(total);
		billing.setTaxes(total * 0.19);
		billing.setTotal(previousTotal - total);
		billing.setDiscount(billingEntity.getDiscount());
		billing.setValueMin(billingEntity.getValueMin());
		billing.setValueMax(billingEntity.getValueMax());

		return billing;

	}

}
