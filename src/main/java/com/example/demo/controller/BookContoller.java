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
@RequestMapping("/book/*")
public class BookContoller {

	private final BookService service;
	private final MemberService memberService;

	@Autowired
	public BookContoller(BookService service, MemberService memberService) {
		this.service = service;
		this.memberService = memberService;
	}

	/**
	 * 리스트 화면
	 * @param cri
	 * @param model
	 * @return
	 */
	@GetMapping("/list")
	public String list(Criteria cri, Model model) {
		model.addAttribute("list", service.getList(cri));
		model.addAttribute("pageMaker", new PageDTO(cri, service.getTotal(cri)));
		return "book/bookList";
	}

	@PostMapping("/borrow")
	public String myPage(Book book, @RequestParam("bookNum") int bookNum, Model model, RedirectAttributes rttr, HttpSession session) {
		if (service.borrow(book)) {
			rttr.addFlashAttribute("result", "success");
			String id = (String)session.getAttribute("sessionId");
			System.out.println(id);
			service.addInfo(id, bookNum);
			if (book.getStock() == 0) {
				rttr.addFlashAttribute("result", "fail");
			}
		}
		return "redirect:/book/list";
	}

	@GetMapping("/get")
	public void get(@RequestParam("bookNum") int bookNum, Model model) {
		model.addAttribute("book", service.findBookNum(bookNum));
	}

}
