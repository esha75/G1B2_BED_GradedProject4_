package com.esha.EmployeeManagementRestApi.controller;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.esha.EmployeeManagementRestApi.entity.Employee;
import com.esha.EmployeeManagementRestApi.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeesController {
	
	private EmployeeService employeeService;


	@Autowired
	public EmployeesController(EmployeeService theEmployeeService) {
		employeeService = theEmployeeService;
	}

	
	@GetMapping("/employees/employeesList")
	public List<Employee> findAll() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Collection<? extends GrantedAuthority> currentPrincipalName = authentication.getAuthorities();
		System.out.println(currentPrincipalName);
		return employeeService.findAll();
	}

	
	@GetMapping("/employees/{employee_id}")
	public Optional<Employee> getEmployee(@PathVariable int employee_id) {
		return employeeService.findById(employee_id);
	}

	
	@PostMapping("/employees/add")
	public String addEmployee(@RequestBody Employee theEmployees) {
		return employeeService.save(theEmployees);

	}

	

	@PutMapping("/employees/update")
	public String updateEmployee(@RequestBody Employee theEmployee) {
		return employeeService.updateEmployee(theEmployee);
	}

	
	@DeleteMapping("/employees/delete/{employeeId}")
	public String deleteEmployee(@PathVariable int employeeId) {
		return employeeService.deleteById(employeeId);

	}

	
	@GetMapping("/employees/search/{firstName}")
	public List<Employee> searchByFirstName(@PathVariable String firstName) {
		return employeeService.searchByFirstName(firstName);
	}

	
	@GetMapping("/employees/sort")
	public List<Employee> sortByFirstName(@RequestParam("order") String sortBy) {
		return employeeService.sortByFirstName(sortBy);
	}

}
