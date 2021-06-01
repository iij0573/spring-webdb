package com.example.demo.repository;

import java.util.List;

import com.example.demo.domain.MemberInfo;
import com.example.demo.domain.Member;

public interface MemberRepository {

	public void singup(Member member);
	
	public List<Member> login(String id, String pw);
	
	public List<MemberInfo> getInfo(String id);
}
