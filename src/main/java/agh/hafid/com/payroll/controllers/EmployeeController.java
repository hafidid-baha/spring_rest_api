package agh.hafid.com.payroll.controllers;

import agh.hafid.com.payroll.helpers.EmployeeModelAssembler;
import agh.hafid.com.payroll.models.Employee;
import agh.hafid.com.payroll.repositories.EmployeeRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
public class EmployeeController {
    private final EmployeeRepository employeeRepository;
    private final EmployeeModelAssembler employeeModelAssembler;

    public EmployeeController(EmployeeRepository employeeRepository,EmployeeModelAssembler employeeModelAssembler) {
        this.employeeRepository = employeeRepository;
        this.employeeModelAssembler = employeeModelAssembler;
    }

    @GetMapping("/employees")
    public CollectionModel<EntityModel<Employee>> all(){
        List<EntityModel<Employee>> employees = employeeRepository.findAll().stream()
                .map(employeeModelAssembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(employees,
                linkTo(methodOn(EmployeeController.class).all()).withSelfRel());

    }

    @PostMapping("/employees")
    ResponseEntity<?> newEmployee(@RequestBody Employee newEmployee) {

        EntityModel<Employee> entityModel = employeeModelAssembler.toModel(employeeRepository.save(newEmployee));

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @GetMapping("/employees/{id}")
    public EntityModel<Employee> one(@PathVariable Long id){
        Employee employee =  employeeRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Could Not Found Employee With Id "+id));

//        return EntityModel.of(employee,
//                linkTo(methodOn(EmployeeController.class).one(id)).withSelfRel(),
//                linkTo(methodOn(EmployeeController.class).all()).withRel("employees"));
        return employeeModelAssembler.toModel(employee);
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<?> update(@RequestBody Employee employee, @PathVariable Long id){
        EntityModel<Employee> entityModel =  employeeRepository.findById(id)
                .map(emp->{
                    emp.setName(employee.getName());
                    emp.setRole(employee.getRole());
                    return employeeModelAssembler.toModel(employeeRepository.save(emp));
                }).orElseGet(()->{
                    employee.setId(id);
                    return employeeModelAssembler.toModel(employeeRepository.save(employee));
                });

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<?> remove(@PathVariable Long id){
        employeeRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
