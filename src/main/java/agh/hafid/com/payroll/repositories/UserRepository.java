package agh.hafid.com.payroll.repositories;

import agh.hafid.com.payroll.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

    User findByName(String username);
}
