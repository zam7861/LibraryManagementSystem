package com.demo.entity;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class IssueBook {

	private int issueId;
	private int bookId;
	private int employeeId;
	private LocalDate issueDate;
	private LocalDate scheduledReturnDate;
	private LocalDate actualReturnDate;
	private boolean status;
	private int fine;
	
	public IssueBook(int issueId, int bookId, int employeeId, LocalDate issueDate, LocalDate scheduledReturnDate,
			LocalDate actualReturnDate, boolean status, int fine) {
		super();
		this.issueId = issueId;
		this.bookId = bookId;
		this.employeeId = employeeId;
		this.issueDate = issueDate;
		this.scheduledReturnDate = scheduledReturnDate;
		this.actualReturnDate = actualReturnDate;
		this.status = status;
		this.fine = fine;
	}

	public int getIssueId() {
		return issueId;
	}


	public int getBookId() {
		return bookId;
	}

	

	public int getEmployeeId() {
		return employeeId;
	}

	

	public LocalDate getIssueDate() {
		return issueDate;
	}

	

	public LocalDate getScheduledReturnDate() {
		return scheduledReturnDate;
	}

	
	public LocalDate getActualReturnDate() {
		return actualReturnDate;
	}

	
	public boolean isStatus() {
		return status;
	}

	

	public int getFine() {
		return fine;
	}

	
	
	
	
}
