package com.demo.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.demo.entity.Book;

public class BookDaoImpl implements BookDao {

	@Override
	public Optional<List<Book>> getAllBooks() {
		List<Book> list = new ArrayList<>();
		try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/libraryManagementSystem", "root", "wiley")) {
			Class.forName("com.mysql.cj.jdbc.Driver");
			PreparedStatement statement = connection.prepareStatement("select * from books");
			ResultSet rs = statement.executeQuery();
			Book book;
			while (rs.next()) {
				int bookId = rs.getInt("bookId");
				String bookName = rs.getString("bookName");
				String bookType = rs.getString("bookType");
				String authorName = rs.getString("authorName");
				int stocks = rs.getInt("stocks");
				book = new Book(bookId, bookName,bookType,authorName,stocks);
				list.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return Optional.ofNullable(list);
	}

	@Override
	public Optional<Book> getBookById(int bookId) {
		Book book = null;
		try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/libraryManagementSystem", "root", "wiley")) {
			Class.forName("com.mysql.cj.jdbc.Driver");
			PreparedStatement statement = connection.prepareStatement("select * from books where bookId = ?");
			statement.setInt(1,bookId);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				int bookID = rs.getInt("bookId");
				String bookName = rs.getString("bookName");
				String bookType = rs.getString("bookType");
				String authorName = rs.getString("authorName");
				int stocks = rs.getInt("stocks");
				book = new Book(bookID, bookName,bookType,authorName,stocks);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return Optional.ofNullable(book);
	}

	@Override
	public Optional<List<Book>> getAllBooksByType(String type) {
		List<Book> list = new ArrayList<>();
		try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/libraryManagementSystem", "root", "wiley")) {
			Class.forName("com.mysql.cj.jdbc.Driver");
			PreparedStatement statement = connection.prepareStatement("select * from books where bookType = ?");
			statement.setString(1,type);
			ResultSet rs = statement.executeQuery();
			Book book;
			while (rs.next()) {
				int bookId = rs.getInt("bookId");
				String bookName = rs.getString("bookName");
				String bookType = rs.getString("bookType");
				String authorName = rs.getString("authorName");
				int stocks = rs.getInt("stocks");
				book = new Book(bookId, bookName,bookType,authorName,stocks);
				list.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return Optional.ofNullable(list);
	}

	@Override
	public Optional<List<Book>> getAllBooksByAuthorName(String authorName) {
		List<Book> list = new ArrayList<>();
		try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/libraryManagementSystem", "root", "wiley")) {
			Class.forName("com.mysql.cj.jdbc.Driver");
			PreparedStatement statement = connection.prepareStatement("select * from books where authorName = ?");
			statement.setString(1,authorName);
			ResultSet rs = statement.executeQuery();
			Book book;
			while (rs.next()) {
				int bookId = rs.getInt("bookId");
				String bookName = rs.getString("bookName");
				String bookType = rs.getString("bookType");
				String authorName1 = rs.getString("authorName");
				int stocks = rs.getInt("stocks");
				book = new Book(bookId, bookName,bookType,authorName1,stocks);
				list.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return Optional.ofNullable(list);
	}

	@Override
	public Optional<List<Book>> getAllBooksByStocks(int minStocks) {
		List<Book> list = new ArrayList<>();
		try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/libraryManagementSystem", "root", "wiley")) {
			Class.forName("com.mysql.cj.jdbc.Driver");
			PreparedStatement statement = connection.prepareStatement("select * from books where stocks <= ?");
			statement.setInt(1,minStocks);
			ResultSet rs = statement.executeQuery();
			Book book;
			while (rs.next()) {
				int bookId = rs.getInt("bookId");
				String bookName = rs.getString("bookName");
				String bookType = rs.getString("bookType");
				String authorName = rs.getString("authorName");
				int stocks = rs.getInt("stocks");
				book = new Book(bookId, bookName,bookType,authorName,stocks);
				list.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return Optional.ofNullable(list);
	}

}
