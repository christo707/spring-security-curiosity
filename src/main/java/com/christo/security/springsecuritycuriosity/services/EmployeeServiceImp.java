package com.christo.security.springsecuritycuriosity.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.christo.security.springsecuritycuriosity.exception.ResourceNotFoundException;
import com.christo.security.springsecuritycuriosity.interfaces.EmployeeService;
import com.christo.security.springsecuritycuriosity.model.Employee;
import com.christo.security.springsecuritycuriosity.repository.EmployeeRepository;

@Service
@Transactional
public class EmployeeServiceImp implements EmployeeService{

	@Autowired
	EmployeeRepository employeeRepository;
	
	public EmployeeServiceImp(EmployeeRepository employeeRepository) {
		super();
		this.employeeRepository = employeeRepository;
	}
	

	public EmployeeServiceImp() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Employee> getEmployees() {
		return (List<Employee>) employeeRepository.findAll();
	}


	@Override
	public Employee update(Employee employee, int id) {
		Optional<Employee> optEmployee = employeeRepository.findById(id); // returns java8 optional
	    if (optEmployee.isPresent()) {
	    	Employee emp = optEmployee.get();
	    	emp.setName(employee.getName());
	    	emp.setDept(employee.getDept());

	    	Employee updatedemp = employeeRepository.save(emp);
	        return updatedemp;
	    } else {
	    	throw new ResourceNotFoundException("Employee", "Id",id);
	    }
	}


	@Override
	public void createEmployee(Employee employee) {
		employeeRepository.save(employee);
	}


	@Override
	public Employee findById(int id) {
		 Optional<Employee> optEmployee = employeeRepository.findById(id); // returns java8 optional
		    if (optEmployee.isPresent()) {
		        return optEmployee.get();
		    } else {
		       throw new ResourceNotFoundException("Employee", "Id", id);
		    }
	}


	@Override
	public Boolean deleteEmployeeById(int id) {
		Optional<Employee> optEmployee = employeeRepository.findById(id); // returns java8 optional
	    if (optEmployee.isPresent()) {
	    	employeeRepository.delete(optEmployee.get());
	        return true;
	    } else {
	       throw new ResourceNotFoundException("Employee", "Id",id);
	    }
	}


}
