package com.example.demo.controller;

import com.example.demo.exception.ResourceNotFoundEcxeption;
import com.example.demo.model.Employee;
import com.example.demo.model.Login;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/login")
public class LoginController {

    @Autowired
    LoginRepository loginRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping
    public List<Login> getAllLogin(){
        return loginRepository.findAll();
    }

    @PostMapping
    public Login createLogin(@RequestBody Login login){
        return loginRepository.save(login);
    }

    @GetMapping("{id}")
    public ResponseEntity<Login> getEmployeeById(@PathVariable long id){
        Login login = loginRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundEcxeption("login not exist with id:"+id));
        return ResponseEntity.ok(login);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Login> deleteLoginById(@PathVariable long id){
        Login deletedLogin = loginRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundEcxeption("login not exist with id:"+id));
        loginRepository.delete(deletedLogin);

        return new ResponseEntity<Login>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("{id}")
    public ResponseEntity<Login> updateLogin(
            @PathVariable long id,
            @RequestBody Login login
    ){
        Login updatedLogin = loginRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundEcxeption("login not exist with id:"+id));
        updatedLogin.setEmail(login.getEmail());
        updatedLogin.setPassword(login.getPassword());

        loginRepository.save(updatedLogin);
        return ResponseEntity.ok(updatedLogin);
    }

    @PutMapping("{login_id}/employee/{employee_id}")
    public ResponseEntity<Login> addLoginToEmployee(
            @PathVariable long login_id,
            @PathVariable long employee_id
    ){
        Login login = loginRepository.findById(login_id).orElseThrow(() ->
                new ResourceNotFoundEcxeption("login not exist with id:"+login_id));
        Employee employee = employeeRepository.findById(employee_id).orElseThrow(() ->
                new ResourceNotFoundEcxeption("employee not exist with id:"+employee_id));

        employee.setLogin(login);
        login.setEmployee(employee);

        employeeRepository.save(employee);
        loginRepository.save(login);

        return ResponseEntity.ok(login);
    }
}
