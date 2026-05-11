package co.com.periferia.payment.yappy.repository;

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

	@Query(value = "SELECT c FROM CustomerYampyEntity c "
			+ "LEFT JOIN FETCH c.tx t "
			+ "LEFT JOIN FETCH t.billing b ")
	Optional<List<CustomerYampyEntity>> getAllUsers();
}

