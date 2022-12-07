package com.demo.service;

import java.util.Optional;

import com.demo.entity.Employee;
import com.demo.persistence.EmployeeDao;
import com.demo.persistence.EmployeeDaoImpl;

public class EmployeeServiceImpl implements EmployeeService {
	
	private EmployeeDao employeeDao = new EmployeeDaoImpl();
	
	@Override
	public boolean validateUser(int empId, String password) {
		Optional<Employee> employee = employeeDao.getEmployeeById(empId);
		if(employee.isPresent()) {
			if(employee.get().getEmpName().equals("Admin") && employee.get().getPassword().equals(password))
				return true;
		}
		return false;
	}

	@Override
	public Employee getEmployeeById(int empId) {
		return employeeDao.getEmployeeById(empId).get();
	}

	@Override
	public boolean updateEmployeeBookCount(int empId,int count) {
		return employeeDao.updateEmployeeBookCount(empId,count);
	}


}
