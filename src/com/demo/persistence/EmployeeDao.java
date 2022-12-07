package com.demo.persistence;

import java.util.Optional;

import com.demo.entity.Employee;

public interface EmployeeDao {
	Optional<Employee> getEmployeeById(int empId);
	boolean updateEmployeeBookCount(int empId,int count);
}
