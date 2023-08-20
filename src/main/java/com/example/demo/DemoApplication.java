package com.example.demo;

import com.example.demo.model.Employee;
import com.example.demo.model.PhoneNums;
import com.example.demo.model.Position;
import com.example.demo.model.Role;
import com.example.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	public static void main(String[] args) {

		SpringApplication.run(DemoApplication.class, args);

	}

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private PositionRepository positionRepository;

	@Autowired
	private PhoneNumRepository phoneNumRepository;

	@Autowired
	private UserEntityRepository userEntityRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public void run(String... args) throws Exception {

/*
		Employee[] employees = new Employee[6];

		for(int i=1; i<6; i++){
			employees[i] = new Employee();
			employees[i].setFirstName("Bahaa"+i);
			employees[i].setLastName("Assad"+i);
			employees[i].setUsername("bahaaassad"+i+"@gmail.com");
			employees[i].setEmployees_positions(new ArrayList<Position>());
			employees[i].setPhoneNums(new ArrayList<PhoneNums>());
			employees[i].setPassword("{noop}pass"+i);
			employees[i].setRole(new ArrayList<Role>());
			employeeRepository.save(employees[i]);
		}

		Position[] positions = new Position[6];

		for(int i=1; i<=5; i++){
			positions[i] = new Position();
			positions[i].setName("position"+i);
			positions[i].setEmployees_positions(new ArrayList<Employee>());
			positionRepository.save(positions[i]);
		}

		PhoneNums[] phones = new PhoneNums[10];

		for(int i=1; i<10; i++){
			phones[i] = new PhoneNums();
			phones[i].setNum("000000"+i);
			phoneNumRepository.save(phones[i]);
		}

		Role[] roles = new Role[2];
		roles[0] = new Role();
		roles[1] = new Role();
		roles[0].setName("USER");
		roles[1].setName("ADMIN");
		roleRepository.save(roles[0]);
		roleRepository.save(roles[1]);
*/
	}
}
