package com.demo.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import com.demo.entity.Employee;
import com.demo.entity.IssueBook;

public interface IssueBookService {

	boolean issueBook(int bookId, int employeeId,LocalDate issueDate,LocalDate scheduledReturnDate,LocalDate actualReturnDate);
	boolean returnBook(int employeeId,String type,LocalDate issueDate);
	Map<Employee,List<IssueBook>> getAllIssueBookDetails();
	List<IssueBook> getIssueBookDetailsByEmployeeId(int employeeId);
}
