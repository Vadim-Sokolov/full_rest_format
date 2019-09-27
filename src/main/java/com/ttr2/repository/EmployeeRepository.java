package com.ttr2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ttr2.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
