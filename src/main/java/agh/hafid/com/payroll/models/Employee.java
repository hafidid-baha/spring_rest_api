package agh.hafid.com.payroll.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@NoArgsConstructor
@Table(name = "employee")
public class Employee {
    private @Id @GeneratedValue Long id;
    private String name;
    private String role;

    public Employee(String name, String role) {
        this.name = name;
        this.role = role;
    }
}
