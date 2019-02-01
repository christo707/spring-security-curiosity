package com.christo.security.springsecuritycuriosity.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.christo.security.springsecuritycuriosity.interfaces.EmployeeService;
import com.christo.security.springsecuritycuriosity.model.Employee;
import com.christo.security.springsecuritycuriosity.model.Test;


@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService employeeData;
	
	
	@RequestMapping(value = "/test",  method = RequestMethod.GET, produces = "application/json")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN') or hasRole('FM')")
	public Test test(){
		Test t = new Test(1, "API IS UP", new Date());
		return t;
	}

	@RequestMapping(value = "/addNewEmployee", method = RequestMethod.POST, headers="Accept=application/json")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> newEmployee(@RequestBody Employee employee) {
		System.out.println("Creating Employee: " + employee.getDept() + " : " + employee.getName());
		employeeData.createEmployee(employee);
		return new ResponseEntity<String>("Employee Created Successfully", HttpStatus.CREATED);
		
	}

	@RequestMapping(value = "/listEmployees", method = RequestMethod.GET, produces = "application/json")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN') or hasRole('FM')")
	public List<Employee>  employees() {
		List<Employee> allEmployees = employeeData.getEmployees();
		return allEmployees;
	}
	
	@GetMapping(value = "/{id}", produces = "application/json")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN') or hasRole('FM')")
    public ResponseEntity<Employee> getUserById(@PathVariable("id") int id) {
        System.out.println("Fetching Employee with id " + id);
        Employee emp = employeeData.findById(id);
        if (emp == null) {
            return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Employee>(emp, HttpStatus.OK);
    }
	
	@DeleteMapping(value="/{id}", headers ="Accept=application/json")
	@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") int id){
        Employee room = employeeData.findById(id);
        if (room == null) {
            return new ResponseEntity<String>("No such Emp", HttpStatus.NOT_FOUND);
        }
        employeeData.deleteEmployeeById(id);
        return new ResponseEntity<String>("Meeting Room Deleted", HttpStatus.NO_CONTENT);
    }

	@PutMapping(value="/update", headers="Accept=application/json")
	@PreAuthorize("hasRole('ADMIN') or hasRole('FM')")
    public ResponseEntity<Employee> updateUserPartially(@RequestBody Employee empp){
        Employee emp = employeeData.findById(empp.getId());
        if(emp ==null){
            return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
        }
        Employee r1 = employeeData.update(empp, empp.getId());
        return new ResponseEntity<Employee>(r1, HttpStatus.OK);
    }
	
	
	

}
