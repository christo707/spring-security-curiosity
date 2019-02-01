package com.christo.security.springsecuritycuriosity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.christo.security.springsecuritycuriosity.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
