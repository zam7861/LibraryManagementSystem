package com.demo.presentation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.demo.entity.Book;
import com.demo.entity.Employee;
import com.demo.entity.IssueBook;
import com.demo.service.BookService;
import com.demo.service.BookServiceImpl;
import com.demo.service.EmployeeService;
import com.demo.service.EmployeeServiceImpl;
import com.demo.service.IssueBookService;
import com.demo.service.IssueBookServiceImpl;

public class PresentationImpl implements Presentation{

	EmployeeService employeeService = new EmployeeServiceImpl();
	BookService bookService = new BookServiceImpl();
	IssueBookService issueBookService = new IssueBookServiceImpl();
	Scanner sc = new Scanner(System.in);
	List<Book> list;
	
	@Override
	public void showMenu() {
		System.out.println("\n **********************LIBRAY MANAGEMENT SYSTEM**************************");
		System.out.println("1.Books Section");
		System.out.println("2.Library Desk Section");
		System.out.println("3.Logout");
	}

	@Override
	public void performChoice(int choice) {
		switch (choice) {
		case 1:if(login()) {
			while(true) {
				showMenu();
				System.out.println("Enter your choice :");
				choice = sc.nextInt();
				switch (choice) {
				case 1 :
						boolean condition = true;
						while(condition) {
							bookMenu();
							System.out.println("Enter your choice :");
							choice = sc.nextInt();
							switch(choice){
							case 1:list = bookService.getAllBooks();
								   for(Book book : list)
									   System.out.println(book);
								break;
							case 2:System.out.println("Enter Book Id");
									int bookId = sc.nextInt();
									System.out.println(bookService.getBookById(bookId));
								break;
							case 3: 
									System.out.println("Enter Book Type ");
									String type = sc.next();
									list = bookService.getAllBooksByType(type);
							   	    for(Book book : list)
							   		    System.out.println(book);
								break;
							case 4:System.out.println("Enter Author Name ");
									String authorName = sc.next();
									list = bookService.getAllBooksByAuthorName(authorName);
								    for(Book book : list)
									    System.out.println(book);
								break;
							case 5:System.out.println("Enter minimum stocks of books");
									int stock = sc.nextInt();
									list = bookService.getAllBooksByStocks(stock);
								    for(Book book : list)
									    System.out.println(book);
								break;
							case 6:System.out.println("Exit from book section!!!");
									condition = false;
								break;
							default : System.out.println("Invalid Choice!!");
							}
						}
							
					break;
				case 2:
						boolean condition1 = true;
						while(condition1) {
							libraryDeskMenu();
							System.out.println("Enter your choice :");
							choice = sc.nextInt();
							int employeeId;
							LocalDate issueDate;
							switch(choice){
							case 1:System.out.println("Enter Book id:");
									int bookId = sc.nextInt();
									System.out.println("Enter Employee id:");
									employeeId = sc.nextInt();
									issueDate = LocalDate.now();
									LocalDate scheduledDate = issueDate.plusDays(7);
									if(issueBookService.issueBook(bookId,employeeId, issueDate, scheduledDate,issueDate)) {
										System.out.println("Book is Issued!!");
									}
									else {
										System.out.println("Cannot issue more books Employee already have 3 books ");
									}
								break;
							case 2:System.out.println("Enter Employee id: ");
									employeeId = sc.nextInt();
									System.out.println("Enter Type of book: ");
									String type = sc.next();
									System.out.println("Enter issue Date (like yyyy-MM-dd) :");
									LocalDate date = LocalDate.parse(sc.next());
									if(issueBookService.returnBook(employeeId, type, date))
											System.out.println("Book is returned");
									else
										System.out.println("Book is not issued yet");
								break;
							case 3:
									Map<Employee,List<IssueBook>> map = issueBookService.getAllIssueBookDetails();
									List<IssueBook> list = new ArrayList<>();
									for(Map.Entry<Employee,List<IssueBook>> entry : map.entrySet()) {
										list =  map.get(entry.getKey());
										int bId;
										Book book1;
										int fine;
										LocalDate actualReturnDate;
										System.out.println("Employee Name : " +entry.getKey().getEmpName());
										for(IssueBook book : list) {
											bId = book.getBookId();
											issueDate = book.getIssueDate();
											actualReturnDate = book.getActualReturnDate();
											fine = book.getFine();
											book1 = bookService.getBookById(bId);
											System.out.println("Book Type : " +book1.getBookType());
											System.out.println("Issue Date : " + issueDate);
											System.out.println("Return Date : " + actualReturnDate);
											System.out.println("Fine : "+fine);
										}		
										
									}
								break;
							case 4: System.out.println("Enter employee Id :");
								employeeId = sc.nextInt();
								Employee employee = employeeService.getEmployeeById(employeeId);
								System.out.println("Total fine of Employee : ");
								System.out.println(employee);
								int fine = 0;
								for(IssueBook issueBook : issueBookService.getIssueBookDetailsByEmployeeId(employeeId)){
									fine += issueBook.getFine();
								}
								if(fine == 0)
									System.out.println("Employee dont have fine");
								else
									System.out.println("Total fine  : "+fine);
							
								break;
							case 5:System.out.println("Exit from library desk section");
									condition1 = false;
								break;
							default: System.out.println("Invalid choice!!");
							}
						}
					break;
				case 3: System.out.println("Thank you for using Library Management System!!!");
						System.exit(1);
						break;
				default : System.out.println("Invalid Choice!!");
				}
			}
			}
		break;
		case 2: System.out.println("Exit from System");
				System.exit(1);
				break;
				
		default : System.out.println("Invalid Choice!!");
		}
		
	}

	@Override
	public boolean login() {
		System.out.println("Enter ID : ");
		int employeeId = sc.nextInt();
		System.out.println("Enter Password : ");
		String password = sc.next();
		
		if(employeeService.validateUser(employeeId,password)) {
			System.out.println("Login Successfully!!");
			return true;
		}
		else {
			System.out.println("Invalid credentials!!!!");
		}
		return false;
	}

	@Override
	public void loginMenu() {
		System.out.println("\n ****************LOGIN SECTION******************");
		System.out.println("1.Login");
		System.out.println("2.Exit");
	}

	@Override
	public void bookMenu() {
		System.out.println("\n **********BOOK SECTION************");
		System.out.println("1.Show all books");
		System.out.println("2.Search a book by book ID");
		System.out.println("3.Search all books by type");
		System.out.println("4.Search all books by Author Name");
		System.out.println("5.Search all books by stocks");
		System.out.println("6.Exit");
	}

	@Override
	public void libraryDeskMenu() {
		System.out.println("\n **********LIBRARY DESK SECTION************");
		System.out.println("1.Issue book to employee");
		System.out.println("2.Return the books ");
		System.out.println("3.Show all issue book details");
		System.out.println("4.Show total fine of employee");
		System.out.println("5.Exit");
		
	}
	

}
