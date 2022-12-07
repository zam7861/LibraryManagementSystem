package com.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString


public class Employee {
	
	private int empId ;
	private String empName;
	private String empMail;
	private String empPhone;
	private String password;
	private int noOfBooks;
	
	public Employee(int empId, String empName, String empMail, String empPhone, String password, int noOfBooks) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.empMail = empMail;
		this.empPhone = empPhone;
		this.password = password;
		this.noOfBooks = noOfBooks;
	}

	public int getEmpId() {
		return empId;
	}



	public String getEmpName() {
		return empName;
	}

	

	public String getEmpMail() {
		return empMail;
	}


	public String getEmpPhone() {
		return empPhone;
	}

	

	public String getPassword() {
		return password;
	}

	

	public int getNoOfBooks() {
		return noOfBooks;
	}

		
	
	
}
