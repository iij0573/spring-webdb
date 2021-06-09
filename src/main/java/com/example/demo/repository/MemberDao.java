package com.example.demo.repository;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.MemberInfo;
import com.example.demo.domain.Book;
import com.example.demo.domain.Member;

@Repository
public class MemberDao implements MemberRepository{
	private JdbcTemplate jdbcTemplate;
	
	public MemberDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		}
	
	private RowMapper<Member> MemberRowMapper() {
		return (rs, rowNum) -> {
			Member member = new Member();
			member.setId(rs.getString("ID"));
			member.setName(rs.getString("NAME"));
			member.setEmail(rs.getString("EMAIL"));
			member.setPassword(encryptPw(rs.getString("PASSWORD")));
			member.setTel(rs.getString("TEL"));
			return member;
		};
	}

	@Override
	public int singup(Member member) {
		String sql = "INSERT INTO member(ID, NAME, EMAIL, PASSWORD, TEL) VALUES(?,?,?,?,?)";
		return jdbcTemplate.update(sql, member.getId(), member.getName(), member.getEmail(), encryptPw(member.getPassword()), member.getTel());
	}

	@Override
	public int login(String id, String pw) {
		String sql = "SELECT COUNT(*) FROM member WHERE ID = ? AND PASSWORD = ?";
		int count = jdbcTemplate.queryForObject(sql, new Object[] {id, encryptPw(pw)}, Integer.class);
		return count;
	}
	
	private RowMapper<MemberInfo> infoRowMapper(){
		return (rs, rowNum) -> {
			MemberInfo bookInfo = new MemberInfo();
			bookInfo.setInfoNum(rs.getInt("INFONUM"));
			bookInfo.setId(rs.getString("ID"));
			bookInfo.setBookNum(rs.getInt("BOOKNUM"));
			return bookInfo;
		};
	}


	@Override
	public List<MemberInfo> getInfo(String id) {
		String sql = "SELECT * FROM memberinfo WHERE ID = ?";
		return jdbcTemplate.query(sql, new Object[] {id}, infoRowMapper());
	}

	@Override
	public String findId(String id, String email) {
		String sql = "SELECT ID FROM member WHERE NAME = ? and EMAIL = ?";
		try {
			return jdbcTemplate.queryForObject(sql, new Object[] {id, email}, String.class);
		}catch(EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public int updatePw(Member member) {
		String sql = "update MEMBER SET PASSWORD = ? WHERE NAME = ? AND ID =?";
		return jdbcTemplate.update(sql, new Object[] {encryptPw(member.getPassword()), member.getName(), member.getId()});
	}
	
	//패스워드 암호화
	public static int key = 5;
	public String encryptPw(String pw) {
		String result = "";
		for (int i = 0; i < pw.length(); i++) {
			result += (char)(pw.charAt(i) * key);
		}
		return result;
	}
	
	

}
