package agh.hafid.com.payroll.bootsrap;

import agh.hafid.com.payroll.models.Employee;
import agh.hafid.com.payroll.models.User;
import agh.hafid.com.payroll.repositories.EmployeeRepository;
import agh.hafid.com.payroll.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.List;


@Configuration
public class InitData {
    private static final Logger log = LoggerFactory.getLogger(InitData.class);
    List<User> users = Stream.of(
            new User("hafid", "password", "hafid@gmail.com"),
            new User("ahmed", "pwd1", "user1@gmail.com"),
            new User("said", "pwd2", "user2@gmail.com"),
            new User("omar", "pwd3", "user3@gmail.com")
    ).collect(Collectors.toList());
    @Bean
    CommandLineRunner init(EmployeeRepository employeeRepository, UserRepository userRepository){
        return args -> {
            log.info("Preloading "+employeeRepository.save(new Employee("hafid id-baha","developer")));
            log.info("Preloading "+employeeRepository.save(new Employee("said id-baha","student")));
            log.info("Preloading "+userRepository.saveAll(users));
        };
    }
}
