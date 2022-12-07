package com.demo.service;

import java.util.List;

import com.demo.entity.Book;
import com.demo.persistence.BookDao;
import com.demo.persistence.BookDaoImpl;

public class BookServiceImpl implements BookService {
	private BookDao bookDao = new BookDaoImpl();
	
	@Override
	public List<Book> getAllBooks() {
		return bookDao.getAllBooks().get();
	}
	@Override
	public List<Book> getAllBooksByType(String type) {
		return bookDao.getAllBooksByType(type).get();
	}
	@Override
	public List<Book> getAllBooksByAuthorName(String authorName) {
		return bookDao.getAllBooksByAuthorName(authorName).get();
	}
	@Override
	public List<Book> getAllBooksByStocks(int minStocks) {
		return bookDao.getAllBooksByStocks(minStocks).get();
	}
	@Override
	public Book getBookById(int bookId) {
		return bookDao.getBookById(bookId).get();
	}

}
