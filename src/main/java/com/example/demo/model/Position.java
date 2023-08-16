package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Positions")
public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="name")
    private String name;

    @ManyToMany()
    @JoinTable(
            name = "employee_position",
            joinColumns = @JoinColumn(name="position_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name="employee_id", referencedColumnName = "id")
    )
    private List<Employee> employees_positions = new ArrayList<Employee>();

    public void insertEmployee(Employee employee){
        employees_positions.add(employee);
    }
}
