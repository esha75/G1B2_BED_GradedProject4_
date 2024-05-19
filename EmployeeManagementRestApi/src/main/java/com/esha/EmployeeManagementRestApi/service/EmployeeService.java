package com.esha.EmployeeManagementRestApi.service;

import java.util.List;
import java.util.Optional;

import com.esha.EmployeeManagementRestApi.entity.Employee;

public interface EmployeeService {
	public List<Employee> findAll();

	public Optional<Employee> findById(int theId);

	public String save(Employee theEmployee);

	public String updateEmployee(Employee theEmployee);

	public String deleteById(int theId);

	public List<Employee> searchByFirstName(String firstName);

	public List<Employee> sortByFirstName(String sortBy);

	

	
}
