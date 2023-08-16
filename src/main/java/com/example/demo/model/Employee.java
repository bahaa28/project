package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="email_id")
    private String emailId;

    @JsonIgnore
    @ManyToMany (mappedBy = "employees_positions")
    private List<Position> employees_positions = new ArrayList<Position>();

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<PhoneNums> phoneNums = new ArrayList<>();

    @OneToOne(mappedBy = "employee",cascade = CascadeType.ALL)
    @JsonIgnore
    private Login login;


    public void addPhone(PhoneNums phone){
        this.phoneNums.add(phone);
    }
}
