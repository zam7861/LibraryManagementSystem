package com.demo.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.demo.entity.Employee;
import com.demo.entity.IssueBook;
import com.demo.persistence.EmployeeDao;
import com.demo.persistence.EmployeeDaoImpl;
import com.demo.persistence.IssueBookDao;
import com.demo.persistence.IssueBookDaoImpl;

public class IssueBookServiceImpl implements IssueBookService {
	
	EmployeeDao employeeDao = new EmployeeDaoImpl();
	IssueBookDao issueBookDao = new IssueBookDaoImpl();
	List<IssueBook> list;

	@Override
	public boolean issueBook(int bookId, int employeeId, LocalDate issueDate, LocalDate scheduledReturnDate,LocalDate actualReturnDate) {
			Employee employee = employeeDao.getEmployeeById(employeeId).get();
		if((employee.getNoOfBooks() < 4)) {
			if(issueBookDao.issueBook(bookId, employeeId, issueDate,scheduledReturnDate,actualReturnDate)) {
				int count = employee.getNoOfBooks();
				employeeDao.updateEmployeeBookCount(employeeId,count+1);
				return true;
			};
		}
		return false;
	}

	@Override
	public boolean returnBook(int employeeId, String type, LocalDate issueDate) {
		Employee employee = employeeDao.getEmployeeById(employeeId).get();
		if(employee.getNoOfBooks() > 0) {
			if(issueBookDao.returnBook(employeeId,type,issueDate)) {
				int count = employee.getNoOfBooks();
				employeeDao.updateEmployeeBookCount(employeeId,count-1);
				return calculateFine(employeeId,type,issueDate);
			}
		}
			
		return false;
	}

	
	
	private boolean calculateFine(int employeeId, String type, LocalDate issueDate) {
		IssueBook issueBook = issueBookDao.getSpecificIssueBookDetail(employeeId, type, issueDate).get();
		if(!issueBook.equals(null)) {
			int issueId = issueBook.getIssueId();
			LocalDate scheduledReturnDate = issueBook.getScheduledReturnDate();
			LocalDate actualReturnDate = issueBook.getActualReturnDate();
			int noOfDays=0;
			int fine=0;
			if(scheduledReturnDate.isBefore(actualReturnDate))
				noOfDays = Math.abs((Period.between(scheduledReturnDate, actualReturnDate)).getDays());	
			if(noOfDays > 0) {
				if(type.equals("Data Analytics")) 
					fine = noOfDays*5;
				else if(type.equals("Technology"))
					fine = noOfDays*6;
				else if(type.equals("Management"))
					fine = noOfDays*7;
				}
			if(issueBookDao.updateFine(issueId, fine))
				return true;
			
		}
		return false;
		
	}

	@Override
	public Map<Employee,List<IssueBook>> getAllIssueBookDetails() {
		Map<Employee,List<IssueBook>> map = new HashMap<Employee,List<IssueBook>>();
		list = issueBookDao.getAllIssueBookDetails().get();
		Employee employee;
		for(IssueBook issueBook : list) {
			employee = employeeDao.getEmployeeById(issueBook.getEmployeeId()).get();
			if(map.containsKey(employee)) {
				map.get(employee).add(issueBook);
			}
			else {
				map.put(employee, new ArrayList<IssueBook>());
				map.get(employee).add(issueBook);
			}
		}
		return map;
	}

	@Override
	public List<IssueBook> getIssueBookDetailsByEmployeeId(int employeeId) {
		Optional<List<IssueBook>> list = issueBookDao.getIssueBookDetailsByEmployeeId(employeeId);
		if(list.isPresent())
			return list.get();
		return null;
	}

	
}
