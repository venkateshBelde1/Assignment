package com.app.assignment.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.assignment.model.Employee;


public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}