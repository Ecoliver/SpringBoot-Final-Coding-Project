package ice.cream.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ice.cream.shop.entity.Employee;

public interface EmployeeDao extends JpaRepository<Employee, Long> {
}
