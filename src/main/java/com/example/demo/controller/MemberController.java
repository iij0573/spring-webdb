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

	/**
	 * 회원가입 로직
	 * @param model
	 * @param member 회원정보
	 * @param session
	 * @return
	 */
	@PostMapping("/signup")
	public String signup2(Model model, Member member , HttpSession session) {
		if(service.signup(member)) {
			model.addAttribute("result", "signup");
		}
		return "member/login";
	}
	@GetMapping("/login")
	public void login(Model model) {
	}
	
	@PostMapping("/login") 
	public String login(Model model, @RequestParam("sessionId") String id, @RequestParam("password") String pw, RedirectAttributes rttr, HttpSession session) {
		List<Member> member = service.login(id, pw);
		System.out.println(member);
		if(member != null) {
			rttr.addFlashAttribute("memberResult", "success");
		/*	session.setAttribute("member", service.login(id, pw));*/
			rttr.addFlashAttribute("sessionId", id);
			System.out.println(id);
			// 세션 설정
			session.setAttribute("sessionId", id);
			// 세션 유지시간 설정(초단위)
			// 60*30 = 30분
			session.setMaxInactiveInterval(30*60);

		}else if(member.isEmpty()){
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
	public String myPage(Model model, HttpSession session) {
		String id = (String)session.getAttribute("sessionId");
		System.out.println(id);
		model.addAttribute("id", id);
		if(id != null){
			model.addAttribute("book", bookService.findMemberBook(id));
			System.out.println(bookService.findMemberBook(id));
		}
		return "member/myPage";
	}
}
