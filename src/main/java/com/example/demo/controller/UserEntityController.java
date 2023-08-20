package com.example.demo.controller;

import com.example.demo.exception.ResourceNotFoundEcxeption;
import com.example.demo.model.Employee;
import com.example.demo.model.PhoneNums;
import com.example.demo.model.UserEntity;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.PhoneNumRepository;
import com.example.demo.repository.UserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/api/v1/login")
public class UserEntityController {

    @Autowired
    UserEntityRepository userEntityRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    PhoneNumRepository phoneNumRepository;

    @GetMapping
    public List<UserEntity> getAllLogin(){
        return userEntityRepository.findAll();
    }

    @PostMapping
    public UserEntity createLogin(@RequestBody UserEntity userEntity){
        return userEntityRepository.save(userEntity);
    }

    @GetMapping("{id}")
    public ResponseEntity<UserEntity> getEmployeeById(@PathVariable long id){
        UserEntity userEntity = userEntityRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundEcxeption("login not exist with id:"+id));
        return ResponseEntity.ok(userEntity);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<UserEntity> deleteLoginById(@PathVariable long id){
        UserEntity deletedUserEntity = userEntityRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundEcxeption("login not exist with id:"+id));
        userEntityRepository.delete(deletedUserEntity);

        return new ResponseEntity<UserEntity>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("{id}")
    public ResponseEntity<UserEntity> updateLogin(
            @PathVariable long id,
            @RequestBody UserEntity userEntity
    ){
        UserEntity updatedUserEntity = userEntityRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundEcxeption("login not exist with id:"+id));
        updatedUserEntity.setUsername(userEntity.getUsername());
        updatedUserEntity.setPassword(userEntity.getPassword());

        userEntityRepository.save(updatedUserEntity);
        return ResponseEntity.ok(updatedUserEntity);
    }

    @PutMapping("{user_id}/phoneNums/{phoneNum_id}")
    @Transactional
    public ResponseEntity<UserEntity> putPhoneToUser(
            @PathVariable long user_id,
            @PathVariable long phoneNum_id
    ) {
        UserEntity userEntity = userEntityRepository.findById(user_id).orElseThrow(() ->
                new ResourceNotFoundEcxeption("User not found with id:"+user_id));
        PhoneNums phone = phoneNumRepository.findById(phoneNum_id).orElseThrow(() ->
                new ResourceNotFoundEcxeption("Phone number not found with id:"+phoneNum_id));

        // Set the relationship on both sides
        phone.setUserEntity(userEntity);
        userEntity.addPhone(phone);

        // Save both entities
        userEntityRepository.save(userEntity);
        phoneNumRepository.save(phone);

        return ResponseEntity.ok(userEntity);
    }
}
