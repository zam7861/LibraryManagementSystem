package com.demo.persistence;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.demo.entity.IssueBook;

public interface IssueBookDao {

	boolean issueBook(int bookId, int employeeId,LocalDate issueDate,LocalDate scheduledReturnDate,LocalDate actualReturnDate);
	boolean returnBook(int employeeId,String type,LocalDate issueDate);
	Optional<List<IssueBook>> getAllIssueBookDetails();
	Optional<List<IssueBook>> getIssueBookDetailsByEmployeeId(int employeeId);
	Optional<IssueBook> getSpecificIssueBookDetail(int employeeId,String type,LocalDate issueDate);
	boolean updateFine(int issueId,int fine);
}
