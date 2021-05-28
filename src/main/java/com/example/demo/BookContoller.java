package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.demo.pageMaker.Criteria;
import com.example.demo.pageMaker.PageDTO;

import java.util.List;

@Controller
@RequestMapping("/book/*")
public class BookContoller {
	
	private final BookService service;

    @Autowired
    public BookContoller(BookService service) {
        this.service = service;
    }
    
    @GetMapping("/list")
    public String list(Criteria cri, Model model) {
    	model.addAttribute("list", service.getList(cri));
    	model.addAttribute("pageMaker", new PageDTO(cri, service.getTotal(cri)));
    	return "book/bookList";
    }

    @PostMapping(value = "/borrow")
    public void borrow(){

    }
    
    @GetMapping("/get")
    public void get(@RequestParam("bookNum") int bookNum, Model model) {
    	model.addAttribute("book", service.findBookNum(bookNum));
    }
}
