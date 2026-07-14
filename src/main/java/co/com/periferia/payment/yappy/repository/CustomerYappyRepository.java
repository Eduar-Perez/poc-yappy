package co.com.periferia.payment.yappy.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.com.periferia.payment.yappy.entity.CustomerYampyEntity;

@Repository
public interface CustomerYappyRepository extends JpaRepository<CustomerYampyEntity, String> {

	@Query(value = "SELECT c FROM CustomerYampyEntity c "
			+ "LEFT JOIN FETCH c.tx t "
			+ "LEFT JOIN FETCH t.billing b "
			+ "WHERE c.cc = :cc")
	Optional<CustomerYampyEntity> getCustomer(@Param("cc") String cc);

	@Query("SELECT DISTINCT c FROM CustomerYampyEntity c "
			+ "LEFT JOIN FETCH c.tx t "
			+ "LEFT JOIN FETCH t.billing b "
			+ "WHERE (:status IS NULL OR t.status = :status) "
			+ "AND (CAST(:startDate AS timestamp) "
			+ "IS NULL OR t.paymentDate >= :startDate) "
			+ "AND (CAST(:endDate AS timestamp) IS NULL OR t.paymentDate <= :endDate)")
		List<CustomerYampyEntity> getAllUsers(
		        @Param("status") String status,
		        @Param("startDate") LocalDateTime startDate,
		        @Param("endDate") LocalDateTime endDate);
}

