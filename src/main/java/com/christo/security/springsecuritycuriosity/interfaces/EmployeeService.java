package com.christo.security.springsecuritycuriosity.interfaces;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.christo.security.springsecuritycuriosity.model.Employee;


@Service
@Transactional
public interface EmployeeService {
	
	public void createEmployee(Employee employee);
    public List<Employee> getEmployees();
    public Employee findById(int id);
    public Employee update(Employee employee, int id);
    public Boolean deleteEmployeeById(int id);
}
