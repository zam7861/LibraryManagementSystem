package com.demo.service;

import com.demo.entity.Employee;

public interface EmployeeService {
	boolean validateUser(int empId, String password);
	Employee getEmployeeById(int empId);
	boolean updateEmployeeBookCount(int empId,int count);
}
