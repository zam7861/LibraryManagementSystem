package com.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Book {
	
	private int bookId;
	private String bookName;
	private String bookType;
	private String authorName;
	private int Stock;
	
	
	public Book(int bookId, String bookName, String bookType, String authorName, int stock) {
		super();
		this.bookId = bookId;
		this.bookName = bookName;
		this.bookType = bookType;
		this.authorName = authorName;
		Stock = stock;
	}


	public int getBookId() {
		return bookId;
	}





	public String getBookName() {
		return bookName;
	}


	

	public String getBookType() {
		return bookType;
	}




	public String getAuthorName() {
		return authorName;
	}


	


	public int getStock() {
		return Stock;
	}


	
	
	
	
	
	
}
