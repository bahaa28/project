package com.example.demo.controller;

import com.example.demo.exception.ResourceNotFoundEcxeption;
import com.example.demo.model.Employee;
import com.example.demo.model.PhoneNums;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.PhoneNumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    PhoneNumRepository phoneNumRepository;

    @GetMapping
    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }

    @PostMapping
    public Employee createEmployee(@Valid @RequestBody Employee employee){
        return employeeRepository.save(employee);
    }

    @GetMapping("{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable long id){
        Employee employee = employeeRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundEcxeption("employee not exist with id:"+id));
        return ResponseEntity.ok(employee);
    }

    @PutMapping("{id}")
    public ResponseEntity<Employee> updateEmployeeById(@PathVariable long id,@Valid @RequestBody Employee employee){
        Employee updatedEmployee = employeeRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundEcxeption("employee not exist with id:"+id));

        updatedEmployee.setFirstName(employee.getFirstName());
        updatedEmployee.setLastName(employee.getLastName());
        updatedEmployee.setEmployees_positions(employee.getEmployees_positions());
        updatedEmployee.setUsername(employee.getUsername());
        updatedEmployee.setId(employee.getId());
        updatedEmployee.setPassword(employee.getPassword());
        updatedEmployee.setRole(employee.getRole());
        updatedEmployee.setPhoneNums(employee.getPhoneNums());

        employeeRepository.save(updatedEmployee);

        return ResponseEntity.ok(updatedEmployee);

    }

    @DeleteMapping("{id}")
    public ResponseEntity<Employee> deleteEmployeeById(@PathVariable long id){
        Employee deletedEmployee = (Employee) employeeRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundEcxeption("employee not exist with id:"+id));
        employeeRepository.delete(deletedEmployee);

        return new ResponseEntity<Employee>(HttpStatus.NO_CONTENT);
    }
}
