package co.com.periferia.payment.yappy.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.com.periferia.payment.yappy.entity.CustomerYampyEntity;

@Repository
public interface CustomerYappyRepository extends JpaRepository<CustomerYampyEntity, String> {

	@Query(value = "SELECT c FROM CustomerYampyEntity c "
			+ "INNER JOIN FETCH c.tx t "
			+ "INNER JOIN FETCH t.billing b "
			+ "WHERE c.cc = :cc")
	Optional<CustomerYampyEntity> getCustomer(@Param("cc") String cc);
}

