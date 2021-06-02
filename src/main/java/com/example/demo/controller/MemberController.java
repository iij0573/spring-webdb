package com.example.demo.controller;

import java.lang.ProcessBuilder.Redirect;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.domain.Book;
import com.example.demo.domain.Member;
import com.example.demo.service.BookService;
import com.example.demo.service.MemberService;

@Controller
@RequestMapping("/member/*")
public class MemberController {

	private final BookService bookService;
	private final MemberService service;
	
	@Autowired
	public MemberController(MemberService service, BookService bookService) {
		this.service = service;
		this.bookService = bookService;
	}
	@GetMapping("/signup")
	public void signup(Model model, Member member) {
	}
	
	@PostMapping("/signup")
	public String signup2(Model model, Member member , HttpSession session) {
		if(service.signup(member)) {
			System.out.println("회원가입 성공");
		}
		System.out.println(service.signup(member));
		return "member/login";
	}
	@GetMapping("/login")
	public void login(Model model) {
	}
	
	@PostMapping("/login") 
	public String login(Model model, @RequestParam("sessionId") String id, @RequestParam("password") String pw, RedirectAttributes rttr, HttpSession session) {
		List<Member> member = service.login(id, pw);
		if(member != null) {
			rttr.addFlashAttribute("memberResult", "success");
			session.setAttribute("member", service.login(id, pw));
			session.setAttribute("sessionId", id);
			rttr.addFlashAttribute("sessionId",id);
			System.out.println(id);
			return "redirect:/book/list";
		}else if(member == null){
			rttr.addFlashAttribute("memberResult", "error");
			return "redirect:/member/login";
		}
		return "redirect:/";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session, RedirectAttributes rttr) {
		session.invalidate();
		rttr.addFlashAttribute("member", null);
		return "redirect:/book/list";
	}
	
	@GetMapping("/myPage")
	public String myPage(Model model, @RequestParam("bookNum") int bookNum, HttpSession session) {
		String id = (String)session.getAttribute("sessionId");
		session.setAttribute("id", id);
		model.addAttribute("book", bookService.findBookNum(bookNum));
		return "member/myPage";
	}
}
