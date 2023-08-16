package com.example.demo.controller;

import com.example.demo.exception.ResourceNotFoundEcxeption;
import com.example.demo.model.Employee;
import com.example.demo.model.Position;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/v1/positions")
public class PositionController {

    @Autowired
    PositionRepository positionRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping
    public List<Position> getAllPositions(){
        return positionRepository.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<Position> getOnePosition(@PathVariable long id){
        Position position = positionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundEcxeption("position does not exist with id:" + id));
        return ResponseEntity.ok(position);
    }

    @PostMapping
    public Position addPosition(@RequestBody Position position){
        return positionRepository.save(position);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Position> deletePositionById(@PathVariable long id){
        Position deleted = positionRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundEcxeption("position not exist with id:"+id));
        positionRepository.delete(deleted);

        return new ResponseEntity<Position>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("{id}")
    public ResponseEntity<Position> updatePositionById(@PathVariable long id, @RequestBody Position position){
        Position updated = positionRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundEcxeption("position not exist with id:"+id));

        updated.setName(position.getName());

        positionRepository.save(updated);

        return ResponseEntity.ok(updated);
    }

    @PutMapping("{position_id}/employees/{employee_id}")
    public ResponseEntity<Position> addEmployeePosition(
            @PathVariable long position_id,
            @PathVariable long employee_id
            ){
        Position position = positionRepository.findById(position_id).orElseThrow(() ->
                new ResourceNotFoundEcxeption("position does not exit with id:"+position_id));
        Employee employee = employeeRepository.findById(employee_id).orElseThrow(() ->
                new ResourceNotFoundEcxeption("employee does not found with id:"+employee_id));
        position.insertEmployee(employee);

        positionRepository.save(position);

        return ResponseEntity.ok(position);
    }
}
