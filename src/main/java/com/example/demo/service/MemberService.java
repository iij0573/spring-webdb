package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.MemberInfo;
import com.example.demo.domain.Member;
import com.example.demo.repository.MemberRepository;

@Service
public class MemberService {
	private final MemberRepository repository;
	
	@Autowired
	public MemberService(MemberRepository repository) {
		this.repository = repository;
	}
	
	public boolean signup(Member member) {
		 return repository.singup(member) == 1;
	}
	
	public int login(String id, String pw) {
		return repository.login(id, pw);
	}
	  
   
    public List<MemberInfo> getInfo(String id){
    	return repository.getInfo(id);
    }
    
    public String findId(String name, String email) {
    	return repository.findId(name, email);
    }
    
    public boolean updatePw(Member member) {
    	return repository.updatePw(member) == 1;
    }
}
