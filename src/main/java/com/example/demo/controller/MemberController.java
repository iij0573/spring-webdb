package com.example.demo.controller;

import java.lang.ProcessBuilder.Redirect;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
			model.addAttribute("result", "signup");
		}
		return "member/login";
	}
	
	@GetMapping("/login")
	public void login() {}
	
	
	@PostMapping("/login") 
	public String login(Model model, @RequestParam("sessionId") String id, @RequestParam("password") String pw, RedirectAttributes rttr, HttpSession session) {
	/*	Member member = new Member();
		pw = member.getPassword();
		System.out.println(pw);*/
		int count = service.login(id, pw);
		if(count > 0) {
			rttr.addFlashAttribute("memberResult", "success");
		
			rttr.addFlashAttribute("sessionId", id);
			System.out.println(id);
			// 세션 설정
			session.setAttribute("sessionId", id);
			// 세션 유지시간 설정(초단위)
			// 60*30 = 30분
			session.setMaxInactiveInterval(30*60);
		}else if(count == 0){
			session.invalidate();
			rttr.addFlashAttribute("memberResult", "error");
			return "redirect:/member/login";
		}
		return "redirect:/book/list";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session, RedirectAttributes rttr) {
		session.invalidate();
		rttr.addFlashAttribute("sessionId", null);
		return "redirect:/book/list";
	}
	
	@PostMapping("/myPage")
	public String myPage(Model model, HttpSession session, RedirectAttributes rttr) {
		String id = (String)session.getAttribute("sessionId");
		System.out.println(id);
		model.addAttribute("id", id);
		if(id != null){
			model.addAttribute("book", bookService.findMemberBook(id));
			System.out.println(bookService.findMemberBook(id));
		}else if(id == null) {
			rttr.addFlashAttribute("result", "IdNull");
			return "redirect:/member/login";
		}
		return "member/myPage";
	}
	
	@GetMapping("/findId")
	public void findId() {
	}

	@PostMapping("/findId")
	public String findId(Model model, @RequestParam("name") String name, @RequestParam("email") String email, RedirectAttributes rttr) {
		System.out.println(name);
		System.out.println(email);
		String id = service.findId(name, email);
		System.out.println(id);
		rttr.addFlashAttribute("result", "idSuccess");
		rttr.addFlashAttribute("id", id);
		return "redirect:/member/login";
	}
	
	@GetMapping("/findPw")
	public void findPw() {}
	
	
	@PostMapping("/findPw")
	public String findPw(Member member, @RequestParam("sessionId") String sessionId, @RequestParam("name") String name, RedirectAttributes rttr) {
		member.setId(sessionId);
		member.setName(name);
		
		member.setPassword(createRandomPw());
		if(service.updatePw(member)) {
			String newPw = member.getPassword();
			rttr.addFlashAttribute("result", "updatePw");
			rttr.addFlashAttribute("newPw", newPw);
			System.out.println(newPw);
		}
		
		return "redirect:/member/login";
	}
	
	public String createRandomPw() {
		int length = 10;
		int index = 0;
		char[] charSet = new char[] {
				'0','1','2','3','4','5','6','7','8','9'
				,'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O'
				,'P','Q','R','S','T','U','V','W','X','Y','Z'
				,'a','b','c','d','e','f','g','h','i','k','l','m','n','o','p'
				,'q','r','s','t','u','v','w','x','y','z'};
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i<length; i++) {
			index = (int)(charSet.length * Math.random());
			sb.append(charSet[index]);
		}
		return sb.toString();
	}
}
