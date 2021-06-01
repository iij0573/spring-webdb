package com.example.demo.repository;

import java.util.List;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Book;
import com.example.demo.domain.MemberInfo;
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
			member.setId(rs.getString("id"));
			member.setName(rs.getString("name"));
			member.setEmail(rs.getString("email"));
			member.setPassword(rs.getString("password"));
			member.setTel(rs.getString("tel"));
			return member;
		};
	}

	@Override
	public void singup(Member member) {
		String sql = "insert into member(id,name,email,password,tel) values (?,?,?,?,?)";
		jdbcTemplate.update(sql, member.getId(), member.getName(), member.getEmail(), member.getPassword(), member.getTel());
		
	}

	@Override
	public List<Member> login(String id, String pw) {
		String sql = "select * from member where id = ? and password = ?";
		return jdbcTemplate.query(sql, new Object[] {id, pw}, MemberRowMapper());
	}
	
	private RowMapper<MemberInfo> infoRowMapper(){
		return (rs, rowNum) -> {
			MemberInfo bookInfo = new MemberInfo();
			bookInfo.setInfoNum(rs.getInt("infoNum"));
			bookInfo.setId(rs.getString("id"));
			bookInfo.setBookNum(rs.getInt("bookNum"));
			return bookInfo;
		};
	}


	@Override
	public List<MemberInfo> getInfo(String id) {
		String sql = "select * from bookInfo where id = ?";
		return jdbcTemplate.query(sql, new Object[] {id}, infoRowMapper());
	}

}
