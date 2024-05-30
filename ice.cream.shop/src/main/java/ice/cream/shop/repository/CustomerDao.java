package ice.cream.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ice.cream.shop.entity.Customer;

public interface CustomerDao extends JpaRepository<Customer, Long> {
}
