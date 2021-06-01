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
			member.setId(rs.getString("ID"));
			member.setName(rs.getString("NAME"));
			member.setEmail(rs.getString("EMAIL"));
			member.setPassword(rs.getString("PASSWORD"));
			member.setTel(rs.getString("TEL"));
			return member;
		};
	}

	@Override
	public int singup(Member member) {
		String sql = "INSERT INTO member(ID, NAME, EMAIL, PASSWORD, TEL) VALUES(?,?,?,?,?)";
		return jdbcTemplate.update(sql, member.getId(), member.getName(), member.getEmail(), member.getPassword(), member.getTel());
	}

	@Override
	public List<Member> login(String id, String pw) {
		String sql = "SELECT * FROM member WHERE ID = ? AND PASSWORD = ?";
		return jdbcTemplate.query(sql, new Object[] {id, pw}, MemberRowMapper());
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
		String sql = "SELECT * FROM MEMBERINFO WHERE ID = ?";
		return jdbcTemplate.query(sql, new Object[] {id}, infoRowMapper());
	}

}
