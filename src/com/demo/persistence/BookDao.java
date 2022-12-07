package com.demo.persistence;

import java.util.List;
import java.util.Optional;

import com.demo.entity.Book;

public interface BookDao {
	Optional<List<Book>> getAllBooks();
	Optional<Book> getBookById(int bookId);
	Optional<List<Book>> getAllBooksByType(String type);
	Optional<List<Book>> getAllBooksByAuthorName(String authorName);
	Optional<List<Book>> getAllBooksByStocks(int minStocks);
}
