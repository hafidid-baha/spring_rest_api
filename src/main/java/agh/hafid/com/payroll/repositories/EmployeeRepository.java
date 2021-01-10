package agh.hafid.com.payroll.repositories;

import agh.hafid.com.payroll.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
}
