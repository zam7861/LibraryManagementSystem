package com.demo.service;

import java.util.List;

import com.demo.entity.Book;

public interface BookService {
	List<Book> getAllBooks();
	List<Book> getAllBooksByType(String type);
	List<Book> getAllBooksByAuthorName(String authorName);
	List<Book> getAllBooksByStocks(int minStocks);
	Book getBookById(int bookId);
}
