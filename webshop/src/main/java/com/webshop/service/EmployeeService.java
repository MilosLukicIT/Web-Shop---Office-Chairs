package com.webshop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webshop.model.Employee;
import com.webshop.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepo;
	
	public List<Employee> getAllEmployees() {
		return employeeRepo.findAll();
	}
	
	public Optional<Employee> getCustomerById(String employeeId){
		return employeeRepo.findById(employeeId);
	}
	
	public Employee addEmployee(Employee employee) {
		return employeeRepo.save(employee);
	}
	
	public void deleteById(String employeeId) {
		employeeRepo.deleteById(employeeId);
	}
	
	public boolean existsById(String employeeId) {
		if(getCustomerById(employeeId).isPresent()) {
			return true;
		} else
			return false;
	}
}
