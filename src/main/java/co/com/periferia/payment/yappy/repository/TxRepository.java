package co.com.periferia.payment.yappy.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.com.periferia.payment.yappy.entity.TxYampyEntity;

@Repository
public interface TxRepository extends JpaRepository<TxYampyEntity, Long> {

    Optional<TxYampyEntity> findTopByOrderIdOrderByPaymentDateDesc(String orderId);
	
}
