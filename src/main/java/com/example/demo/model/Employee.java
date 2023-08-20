package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="employees")
@OnDelete(action = OnDeleteAction.CASCADE)
public class Employee extends UserEntity {

    @NotNull
    @NotEmpty(message = "first name must not be empty")
    @Column(name="first_name")
    private String firstName;

    @NotNull
    @NotEmpty(message = "last name must not be empty")
    @Column(name="last_name")
    private String lastName;

    @JsonIgnore
    @ManyToMany (mappedBy = "employees_positions")
    private List<Position> employees_positions = new ArrayList<Position>();
}
