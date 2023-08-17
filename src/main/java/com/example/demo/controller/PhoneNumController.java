package com.example.demo.controller;

import com.example.demo.exception.ResourceNotFoundEcxeption;
import com.example.demo.model.PhoneNums;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.PhoneNumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/phoneNums")
public class PhoneNumController {

    @Autowired
    PhoneNumRepository phoneNumRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping
    public List<PhoneNums> getAllPhoneNums(){
        return phoneNumRepository.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<PhoneNums> getPhoneNumById(@PathVariable long id){
        PhoneNums phone = phoneNumRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundEcxeption("phone number does not exist with id:" + id));
        return ResponseEntity.ok(phone);
    }

    @PostMapping
    public PhoneNums addPhoneNum(@RequestBody PhoneNums phone){
        return phoneNumRepository.save(phone);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<PhoneNums> deletePhoneNumsById(@PathVariable long id){
        PhoneNums phone = phoneNumRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundEcxeption("phone does not exists with id:"+id));

        return ResponseEntity.ok(phone);
    }

    @PutMapping("{id}")
    public ResponseEntity<PhoneNums> updatePhoneById(
            @PathVariable long id,
            @RequestBody PhoneNums phone
    ){
        PhoneNums updatedPhone = phoneNumRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundEcxeption("phone does not exists with id:"+id));
        updatedPhone.setNum(phone.getNum());
        updatedPhone.setEmployee(phone.getEmployee());

        phoneNumRepository.save(updatedPhone);

        return new ResponseEntity<PhoneNums>(HttpStatus.NO_CONTENT);
    }

}
