package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.domain.Book;
import com.example.demo.pageMaker.Criteria;
import com.example.demo.pageMaker.PageDTO;
import com.example.demo.service.BookService;

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

    @GetMapping("/borrow")
    public String borrow (Model model, Book book, RedirectAttributes rttr, @RequestParam("bookNum") int bookNum) {
   
    /*	model.addAttribute("book", service.findBookNum(bookNum));*/
    	return "member/myPage";
    }
    
    @PostMapping("/borrow")
    public String myPage(Book book, @RequestParam("bookNum") int bookNum, Model model, RedirectAttributes rttr) {
    	if(service.borrow(book)) {
    		rttr.addFlashAttribute("result", "success");
    		model.addAttribute("book", service.findBookNum(bookNum));
    		if(book.getStock() == 0) {
    			rttr.addFlashAttribute("result", "fail");
    		}
    	}
    	return "redirect:/book/list";
    }
    
    @GetMapping({"/get"})
    public void get(@RequestParam("bookNum") int bookNum , Model model) {
    		
    	model.addAttribute("book", service.findBookNum(bookNum));
    }
}
