package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/book/*")
public class BookContoller {
	
	@Autowired
	private BookDao bookDao;
	
    @GetMapping(value="/list")
    public String list(Model model){
        List<Book> books = bookDao.bookList();
        model.addAttribute("books", books);
        return "/book/bookList";
    }

    @PostMapping(value = "/borrow")
    public void borrow(){

    }

    @GetMapping("/get")
    public void get(@RequestParam("bookNum") int bookNum, Model model){
    	
        model.addAttribute("book", bookDao.read(bookNum));
    }
}
