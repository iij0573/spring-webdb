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

    public List<Book> BookList(){
        return bookRepository.bookList();
    }

    public List<Book> findBookNum(int bookNum){
        return bookRepository.read(bookNum);
    }
    
    public int getTotal(Criteria cri) {
    	return bookRepository.getTotal(cri);
    }
    
    public List<Book> getList(Criteria cri){
    	return bookRepository.getListWithPaging(cri);
    }
}
