package pl.haladyj.spring_data_caching.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.haladyj.spring_data_caching.domain.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
