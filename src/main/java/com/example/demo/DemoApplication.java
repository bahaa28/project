package com.example.demo;

import com.example.demo.model.Employee;
import com.example.demo.model.Login;
import com.example.demo.model.PhoneNums;
import com.example.demo.model.Position;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.LoginRepository;
import com.example.demo.repository.PhoneNumRepository;
import com.example.demo.repository.PositionRepository;
import org.aspectj.asm.IModelFilter;
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
	private LoginRepository loginRepository;

	@Override
	public void run(String... args) throws Exception {

/*
		Employee[] employees = new Employee[6];

		for(int i=1; i<6; i++){
			employees[i] = new Employee();
			employees[i].setFirstName("Bahaa"+i);
			employees[i].setLastName("Assad"+i);
			employees[i].setEmailId("bahaaassad"+i+"@gmail.com");
			employees[i].setEmployees_positions(new ArrayList<Position>());
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

		Login[] logins = new Login[6];

		for(int i=1; i<6; i++){
			logins[i] = new Login();
			logins[i].setEmail("email"+i);
			logins[i].setPassword("password"+i);
			loginRepository.save(logins[i]);
		}
		
 */



	}
}
