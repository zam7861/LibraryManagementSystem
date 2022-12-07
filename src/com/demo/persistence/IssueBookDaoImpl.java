package com.demo.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.demo.entity.IssueBook;

public class IssueBookDaoImpl implements IssueBookDao {

	List<IssueBook> list;
	
	@Override
	public boolean issueBook(int bookId, int employeeId,LocalDate issueDate,LocalDate scheduledReturnDate,LocalDate actualReturnDate) {
		try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/libraryManagementSystem", "root", "wiley")) {
			Class.forName("com.mysql.cj.jdbc.Driver");
			PreparedStatement statement = connection.prepareStatement("insert into issuebook (bookId,employeeId,issueDate,scheduledReturnDate,actualReturnDate) values(?,?,?,?,?)");
			statement.setInt(1,bookId);
			statement.setInt(2,employeeId);
			statement.setObject(3,issueDate);
			statement.setObject(4,scheduledReturnDate);
			statement.setObject(5,actualReturnDate);
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

	@Override
	public boolean returnBook(int employeeId, String type, LocalDate issueDate) {
		try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/libraryManagementSystem", "root", "wiley")) {
			Class.forName("com.mysql.cj.jdbc.Driver");
			PreparedStatement statement = connection.prepareStatement("update issueBook set actualReturnDate = ? , status = ? where employeeId = ? and issueDate =? and bookId =(select bookId from books where bookType =?)");
			statement.setObject(1,LocalDate.now());
			statement.setBoolean(2,true);
			statement.setInt(3,employeeId);
			statement.setObject(4,issueDate);
			statement.setString(5, type);
			int rows = statement.executeUpdate();
			if(rows > 0) {
					return true;
			}
				
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return false;
	}

	

	@Override
	public Optional<List<IssueBook>> getIssueBookDetailsByEmployeeId(int employeeId) {
		IssueBook issueBook = null;
		try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/libraryManagementSystem", "root", "wiley")) {
			Class.forName("com.mysql.cj.jdbc.Driver");
			PreparedStatement statement = connection.prepareStatement("select * from issueBook where employeeId = ?");
			statement.setInt(1,employeeId);
			ResultSet rs = statement.executeQuery();
			
			while(rs.next()) {
				int issueId = rs.getInt("issueId");
				int bookId = rs.getInt("bookId");
				LocalDate issueDate = rs.getDate("issueDate").toLocalDate();
				LocalDate scheduledReturnDate = rs.getDate("scheduledReturnDate").toLocalDate();
				LocalDate actualReturnDate = rs.getDate("actualReturnDate").toLocalDate();
				boolean status = rs.getBoolean("status");
				int fine = rs.getInt("fine");
				
				issueBook = new IssueBook(issueId,employeeId,bookId,issueDate, scheduledReturnDate, actualReturnDate,status, fine);
				list.add(issueBook);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
			
		return Optional.ofNullable(list);
	}

	@Override
	public Optional<IssueBook> getSpecificIssueBookDetail(int employeeId, String type, LocalDate issueDate) {
		IssueBook issueBook = null;
		try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/libraryManagementSystem", "root", "wiley")) {
			Class.forName("com.mysql.cj.jdbc.Driver");
			PreparedStatement statement = connection.prepareStatement("select * from issueBook where employeeId = ? and issueDate = ? and bookId =(select bookId from books where bookType =?)");
			statement.setInt(1,employeeId);
			statement.setObject(2,issueDate);
			statement.setString(3, type);
			ResultSet rs = statement.executeQuery();
			
			while(rs.next()) {
				int issueId = rs.getInt("issueId");
				int bookId = rs.getInt("bookId");
				LocalDate scheduledReturnDate = rs.getDate("scheduledReturnDate").toLocalDate();
				LocalDate actualReturnDate = rs.getDate("actualReturnDate").toLocalDate();
				boolean status = rs.getBoolean("status");
				int fine = rs.getInt("fine");
				
				issueBook = new IssueBook(issueId,employeeId,bookId,issueDate, scheduledReturnDate, actualReturnDate,status, fine);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
			
		return Optional.ofNullable(issueBook);
	}

	@Override
	public boolean updateFine(int issueId, int fine) {
		try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/libraryManagementSystem", "root", "wiley")) {
			Class.forName("com.mysql.cj.jdbc.Driver");
			PreparedStatement statement = connection.prepareStatement("update issueBook set fine=fine+? where issueId = ?");
			statement.setInt(1,fine);
			statement.setInt(2, issueId);
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

	@Override
	public Optional<List<IssueBook>> getAllIssueBookDetails() {
		IssueBook issueBook = null;
		try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/libraryManagementSystem", "root", "wiley")) {
			Class.forName("com.mysql.cj.jdbc.Driver");
			PreparedStatement statement = connection.prepareStatement("select * from issueBook");
			ResultSet rs = statement.executeQuery();
			
			while(rs.next()) {
				int issueId = rs.getInt("issueId");
				int bookId = rs.getInt("bookId");
				int employeeId = rs.getInt("employeeId");
				LocalDate issueDate = rs.getDate("issueDate").toLocalDate();
				LocalDate scheduledReturnDate = rs.getDate("scheduledReturnDate").toLocalDate();
				LocalDate actualReturnDate = rs.getDate("actualReturnDate").toLocalDate();
				boolean status = rs.getBoolean("status");
				int fine = rs.getInt("fine");
				
				issueBook = new IssueBook(issueId,employeeId,bookId,issueDate, scheduledReturnDate, actualReturnDate,status, fine);
				list.add(issueBook);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
			
		return Optional.ofNullable(list);
	}

}
