package com.example.demo.repository;

import java.util.List;

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
	public List<Book> bookList() {
		List<Book> book = jdbcTemplate.query("select * from book", bookRowMapper());
		return book;
	}

	private RowMapper<Book> bookRowMapper() {
		return (rs, rowNum) -> {
			Book book = new Book();
			book.setBookNum(rs.getInt("BOOKNUM"));
			book.setTitle(rs.getString("TITLE"));
			book.setAuthor(rs.getString("AUTHOR"));
			book.setGrade(rs.getFloat("GRADE"));
			book.setStock(rs.getInt("STOCK"));
			book.setRental(rs.getBoolean("RENTAL"));
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
		

		return jdbcTemplate.query ("SELECT * FROM (\n" +
				"    SELECT" +
				"        ROW_NUMBER() over () AS ROWNO\n" +
				"        ,BOOKNUM\n" +
				"        ,TITLE\n" +
				"        ,AUTHOR\n" +
				"        ,GRADE\n" +
				"        ,STOCK\n" +
				"        ,RENTAL\n" +
				"    FROM BOOK\n" +
				"    ) A\n" +
				"WHERE A.ROWNO between ? and ?", new Object[] {cri.getPageNum(), cri.getAmount()}, bookRowMapper());

	}

	@Override
	public int borrow(Book book) {
		String sql = "update book set stock= ?, rental = ? where booknum = ?";
		book.setRental("대여불가능");
		int stock = book.getStock() - 1;
		if(stock < 0) {
			stock = 0;
		}
		int count = jdbcTemplate.update(sql, new Object[] {stock, book.getRental(), book.getBookNum()});
		return count;
	}

	@Override
	public void bookReturn() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Book> read(int bookNum) {
		List<Book> book = jdbcTemplate.query("SELECT * FROM BOOK WHERE BOOKNUM = ?", bookRowMapper(), bookNum);
		return book;
	}

}
