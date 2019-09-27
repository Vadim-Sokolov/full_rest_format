package com.ttr2.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.Resource;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
import org.springframework.hateoas.Resources;

import com.ttr2.model.Employee;
import com.ttr2.repository.EmployeeRepository;

@RestController
public class EmployeeController {

	private final EmployeeRepository repository;
	private final EmployeeResourceAssembler assembler;

	public EmployeeController(EmployeeRepository repository, 
			EmployeeResourceAssembler assembler) {
		this.repository = repository;
		this.assembler = assembler;
	}

	@GetMapping("/employees")
	public Resources<Resource<Employee>> all() {
		List<Resource<Employee>> employees = repository.findAll().stream()
			.map(assembler::toResource)
			.collect(Collectors.toList());
		
		return new Resources<>(employees,
			linkTo(methodOn(EmployeeController.class).all()).withSelfRel());
	}

	@PostMapping("/employees")
	public Employee create(@RequestBody Employee newEmployee) {
		return repository.save(newEmployee);
	}

	@GetMapping("/employees/{id}")
	public Resource<Employee> one(@PathVariable Long id) {
		Employee employee = repository.findById(id).get();
		return assembler.toResource(employee);
	}

	@PutMapping("employees/{id}")
	public Employee update(@RequestBody Employee newEmployee, @PathVariable Long id) {

		Employee employee = repository.findById(id).get();
		employee.setName(newEmployee.getName());
		employee.setRole(newEmployee.getRole());
		return repository.save(employee);
	}
	
	@DeleteMapping("/employees/{id}")
	public void deleteEmployee(@PathVariable Long id) {
		repository.deleteById(id);
	}
}
