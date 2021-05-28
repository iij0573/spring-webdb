package com.example.demo.repository;

import java.util.List;


import java.util.Optional;

import com.example.demo.domain.Book;
import com.example.demo.pageMaker.Criteria;

public interface BookRepository {

	Book borrow();

	void search();

	void bookReturn();

	void bookAdd();

	List<Book> read(int bookNum);

	List<Book> bookList();
	
	public int getTotal(Criteria cri);
	
	public List<Book> getListWithPaging(Criteria cri);
	
}
