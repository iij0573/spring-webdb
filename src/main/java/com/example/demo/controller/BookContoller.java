package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.domain.Book;
import com.example.demo.domain.Member;
import com.example.demo.pageMaker.Criteria;
import com.example.demo.pageMaker.PageDTO;
import com.example.demo.service.BookService;
import com.example.demo.service.MemberService;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class BookContoller {

	private final BookService service;
	private final MemberService memberService;

	@Autowired
	public BookContoller(BookService service, MemberService memberService) {
		this.service = service;
		this.memberService = memberService;
	}

	@GetMapping("book/list")
	public String list(Criteria cri, Model model) {
		model.addAttribute("list", service.getList(cri));
		model.addAttribute("pageMaker", new PageDTO(cri, service.getTotal(cri)));
		return "book/bookList";
	}

	@PostMapping("book/borrow")
	public String myPage(Book book, @RequestParam("bookNum") int bookNum, Model model, RedirectAttributes rttr, HttpSession session) {
		if (service.borrow(book)) {
			rttr.addFlashAttribute("result", "success");
			model.addAttribute("book", service.findBookNum(bookNum));
			if (book.getStock() == 0) {
				rttr.addFlashAttribute("result", "fail");
			}
		}
		String id = (String)session.getAttribute("sessionId");
		System.out.println(id);
		if (service.addInfo(id, bookNum)) {
			System.out.println("memberService.g추가성공");
		}
		return "redirect:/book/list";
	}

	@GetMapping({ "book/get" })
	public void get(@RequestParam("bookNum") int bookNum, Model model) {

		model.addAttribute("book", service.findBookNum(bookNum));
	}

}
