package co.com.periferia.payment.yappy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import co.com.periferia.payment.yappy.entity.BillingEntity;

public interface BillingRepository extends JpaRepository<BillingEntity, Long>{

	@Query("""
		    SELECT b
		    FROM BillingEntity b
		    JOIN b.tx t
		    JOIN t.customer c
		    WHERE c.cc = :cc
		    ORDER BY t.paymentDate DESC
		""")
		List<BillingEntity> findLastBillingByCustomerCc(
		        @Param("cc") String cc);

}	

