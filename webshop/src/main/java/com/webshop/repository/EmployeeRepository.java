package com.webshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webshop.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, String> {

}
