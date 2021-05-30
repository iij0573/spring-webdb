package com.example.demo.repository;

import java.util.List;

import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Book;
import com.example.demo.pageMaker.Criteria;

@Repository
public class BookDao implements BookRepository{
	private JdbcTemplate jdbcTemplate;

	public BookDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public Book borrow() {
		
		return null;
	}


	@Override
	public void search() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void bookReturn() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void bookAdd() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public List<Book> bookList() {
		List<Book> book = jdbcTemplate.query("select * from book", bookRowMapper());
		return book;
	}


	@Override
	public List<Book> read(int bookNum) {
		List<Book> book = jdbcTemplate.query("select * from book where bookNum = ?", bookRowMapper(), bookNum);
		return book;
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

	@Override
	public int getTotal(Criteria cri) {
		String sql = "SELECT COUNT(*) FROM BOOK WHERE BOOKNUM > 0";
		int count = jdbcTemplate.queryForObject(sql, Integer.class);
		return count;
	}

	@Override
	public List<Book> getListWithPaging(Criteria cri) {
		
		return jdbcTemplate.query ("select * from (select rownum rn, book.* from book) "
				+ "where rn between ? and ?", new Object[] {cri.getPageNum(), cri.getAmount()}, bookRowMapper());
				
	}
	

}
