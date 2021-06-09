package com.example.demo.service;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Book;
import com.example.demo.pageMaker.Criteria;
import com.example.demo.repository.BookRepository;

@Service
public class BookService {

	private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> bookList(){
        return bookRepository.bookList();
    }

    public List<Book> findBookNum(int bookNum){
        return bookRepository.read(bookNum);
    }

    public List<Book> findMemberBook(String id){
        return bookRepository.findMemberBook(id);
    }
    
    public int getTotal(Criteria cri) {
    	return bookRepository.getTotal(cri);
    }
    
    public List<Book> getList(Criteria cri){
    	return bookRepository.getListWithPaging(cri);
    }
    
    public boolean borrow(Book book) {
    	return bookRepository.borrow(book) == 1;
    }
    
    public boolean addInfo(String id, int bookNum) {
   	 	return bookRepository.addInfo(id, bookNum) == 1;
   }
   
    public boolean bookReturn(Book book) {
    	return bookRepository.bookReturn(book) == 1;
    }
    
    public boolean popMemberinfo(int bookNum) {
    	return bookRepository.popMemberInfo(bookNum) == 1;
    }
    
    public List<Book> search(String title){
    	return bookRepository.search(title);
    }
   
}
