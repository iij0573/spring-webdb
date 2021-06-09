package com.example.demo.repository;

import java.util.List;

import com.example.demo.domain.MemberInfo;
import com.example.demo.domain.Member;

public interface MemberRepository {

	public int singup(Member member);
	
	public int login(String id, String pw);
	
	public List<MemberInfo> getInfo(String id);
	
	public String findId(String name, String email);
	
	public int updatePw(Member member);
}
