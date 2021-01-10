package agh.hafid.com.payroll.bootsrap;

import agh.hafid.com.payroll.models.Employee;
import agh.hafid.com.payroll.repositories.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class InitData {
    private static final Logger log = LoggerFactory.getLogger(InitData.class);

    @Bean
    CommandLineRunner init(EmployeeRepository employeeRepository){
        return args -> {
            log.info("Preloading "+employeeRepository.save(new Employee("hafid id-baha","developer")));
            log.info("Preloading "+employeeRepository.save(new Employee("said id-baha","student")));
        };
    }
}
