package com.example.demo;

import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class BookDao {
	private JdbcTemplate jdbcTemplate;

	public BookDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public List<Book> bookList() {
		return jdbcTemplate.query("select * from book", bookRowMapper());
	}

	public List<Book> read(int bookNum) {
		return jdbcTemplate.query("select * from book where bookNum = ?", bookRowMapper(), bookNum);	 
	}

	private RowMapper<Book> bookRowMapper() {
		return (rs, rowNum) -> {
			Book book = new Book();
			book.setBookNum(rs.getInt("bookNum"));
			book.setTitle(rs.getString("title"));
			book.setAuthor(rs.getString("author"));
			book.setGrade(rs.getFloat("grade"));
			book.setStock(rs.getInt("stock"));
			book.setRental(rs.getBoolean("rental"));
			return book;
		};
	}

}
