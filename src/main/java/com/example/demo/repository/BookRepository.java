package com.example.demo.repository;

import java.util.List;
import com.example.demo.domain.Book;
import com.example.demo.pageMaker.Criteria;


public interface BookRepository {

	public int bookReturn(Book book); //반납

	List<Book> read(int bookNum);

	List<Book> bookList();
	
	public int getTotal(Criteria cri);
	
	public List<Book> getListWithPaging(Criteria cri);

	int borrow(Book book); //대여

	public int addInfo(String id, int bookNum); //내가 대여한 책정보
	
}
