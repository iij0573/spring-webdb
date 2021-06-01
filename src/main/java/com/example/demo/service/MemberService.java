package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.BookInfo;
import com.example.demo.domain.Member;
import com.example.demo.repository.MemberRepository;

@Service
public class MemberService {
	private final MemberRepository repository;
	
	@Autowired
	public MemberService(MemberRepository repository) {
		this.repository = repository;
	}
	
	public void signup(Member member) {
		repository.singup(member);
	}
	
	public List<Member> login(String id, String pw) {
		return repository.login(id, pw);
	}
	  
   
    public List<BookInfo> getInfo(String id){
    	return repository.getInfo(id);
    }
}
