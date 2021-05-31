package com.example.demo.repository;

import java.util.List;
import com.example.demo.domain.Book;
import com.example.demo.pageMaker.Criteria;


public interface BookRepository {

	void search();

	void bookReturn();

	List<Book> read(int bookNum);

	List<Book> bookList();
	
	public int getTotal(Criteria cri);
	
	public List<Book> getListWithPaging(Criteria cri);

	int borrow(Book book);
	
}
