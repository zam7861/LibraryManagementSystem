package com.demo.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.demo.entity.Book;
import com.demo.entity.Employee;

public class EmployeeDaoImpl implements EmployeeDao {
	Statement statement;
	
	@Override
	public Optional<Employee> getEmployeeById(int empId) {
		Employee employee = null;
		try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/libraryManagementSystem", "root", "wiley")) {
			Class.forName("com.mysql.cj.jdbc.Driver");
			statement = connection.createStatement();
			ResultSet rs =  statement.executeQuery("SELECT * FROM employees where employeeId ="+empId);
			while (rs.next()) {
				int empID = rs.getInt("employeeId");
				String employeeName = rs.getString("employeeName");
				String employeeEmail = rs.getString("employeeMail");
				String employeePhone = rs.getString("employeePhone");
				String password = rs.getString("password");
				int noOfBooks = rs.getInt("noOfBooks");
				employee = new Employee(empID, employeeName, employeeEmail,employeePhone, password, noOfBooks);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return Optional.ofNullable(employee);
	}

	@Override
	public boolean updateEmployeeBookCount(int empId,int count) {
		try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/libraryManagementSystem", "root", "wiley")) {
			Class.forName("com.mysql.cj.jdbc.Driver");
			PreparedStatement statement = connection.prepareStatement("update employees set noOfBooks = ? where employeeId =?");
			statement.setInt(1, (count));
			statement.setInt(2,empId);
			int rows = statement.executeUpdate();
			
			if(rows > 0)
				return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return false;
	}


}
