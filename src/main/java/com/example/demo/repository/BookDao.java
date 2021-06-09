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
		List<Book> book = jdbcTemplate.query("SELECT * FROM book", bookRowMapper());
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
			book.setRental(rs.getString("RENTAL"));
			return book;
		};
	}


	@Override
	public int getTotal(Criteria cri) {
			String sql = "SELECT COUNT(*) FROM book WHERE BOOKNUM > 0";
			int count = jdbcTemplate.queryForObject(sql, Integer.class);
			return count;
	}

/*	@Override
	public List<Book> getListWithPaging(Criteria cri) {
		if(cri.getKeyword()!=null && cri.getType() != null) {
		return jdbcTemplate.query ("SELECT * FROM (\n" +
				"    SELECT" +
				"        ROW_NUMBER() over () AS ROWNO\n" +
				"        ,BOOKNUM\n" +
				"        ,TITLE\n" +
				"        ,AUTHOR\n" +
				"        ,GRADE\n" +
				"        ,STOCK\n" +
				"        ,RENTAL\n" +
				"    FROM book\n" +
				"    ) A\n" +
				"WHERE A.ROWNO between ? and ? and TITLE = ? ORDER BY BOOKNUM", new Object[] {cri.getPageNum(), cri.getAmount(), cri.getKeyword()}, bookRowMapper());
	}else if(cri.getKeyword() == null) {
		return jdbcTemplate.query ("SELECT * FROM (\n" +
				"    SELECT" +
				"        ROW_NUMBER() over () AS ROWNO\n" +
				"        ,BOOKNUM\n" +
				"        ,TITLE\n" +
				"        ,AUTHOR\n" +
				"        ,GRADE\n" +
				"        ,STOCK\n" +
				"        ,RENTAL\n" +
				"    FROM book\n" +
				"    ) A\n" +
				"WHERE A.ROWNO between ? and ? ORDER BY BOOKNUM", new Object[] {cri.getPageNum(), cri.getAmount()}, bookRowMapper());
	}
		return null;
	}*/
	
	@Override
	public List<Book> getListWithPaging(Criteria cri) {
		if(cri.getKeyword() == null) {
		String sql = "select @rownum:=@rownum+1 as no, BOOKNUM, TITLE, AUTHOR, GRADE, STOCK, RENTAL, AUTHOR, GRADE,STOCK,RENTAL from book\r\n" + 
				"LIMIT ?, ?";
		return jdbcTemplate.query(sql, new Object[] {cri.getPageNum()-1, cri.getAmount()}, bookRowMapper());
	}else if(cri.getKeyword() != null && cri.getType() != null) {
		String sql ="select @rownum:=@rownum+1 as no, BOOKNUM, TITLE, AUTHOR, GRADE, STOCK, RENTAL, AUTHOR, GRADE,STOCK,RENTAL from book WHERE TITLE = ? LIMIT ?, ?";
		return jdbcTemplate.query(sql, new Object[] {cri.getKeyword(), cri.getPageNum()-1, cri.getAmount()}, bookRowMapper());
	}
		return null;
	}
	@Override
	public int borrow(Book book) {
		String sql = "UPDATE book SET STOCK = ?, RENTAL =? WHERE BOOKNUM =?";
		int stock = book.getStock() - 1;
		if(stock <= 0) {
			stock = 0;
			book.setRental("대여불가능");
		}else {
			book.setRental("대여가능");
		}
		int count = jdbcTemplate.update(sql, new Object[] {stock, book.getRental(), book.getBookNum()});
		return count;
	}

	@Override
	public int bookReturn(Book book) {
		String sql = "UPDATE book SET STOCK = ?, RENTAL =? WHERE BOOKNUM =?";
		int stock = book.getStock() + 1;
		if(stock > 0) {
			book.setRental("대여가능");
		}
		int count = jdbcTemplate.update(sql, new Object[] {stock, book.getRental(), book.getBookNum()});
		return count;
	}

	@Override
	public List<Book> read(int bookNum) {
		List<Book> book = jdbcTemplate.query("SELECT * FROM book WHERE BOOKNUM = ?", bookRowMapper(), bookNum);
		return book;
	}


	@Override
	public int addInfo(String id, int bookNum) {
		String sql = "INSERT INTO memberinfo(ID, BOOKNUM) VALUES(?, ?)";
		int res = jdbcTemplate.update(sql, id , bookNum);
		return res;
	}

	@Override
	public List<Book> findMemberBook(String id){
		List<Book> book = jdbcTemplate.query("SELECT * FROM book A\n" +
				"JOIN memberinfo B ON A.BOOKNUM = B.BOOKNUM\n" +
				"JOIN member C ON B.ID = C.ID\n" +
				"WHERE B.ID = ?", bookRowMapper(), id);
		return book;
	}

	@Override
	public int popMemberInfo(int bookNum) {
		String sql = "delete from memberinfo where BOOKNUM = ?";
		int res = jdbcTemplate.update(sql, bookNum);
		return res;
	}

	@Override
	public List<Book> search(String title) {
		List<Book> book = jdbcTemplate.query("select * from book where title LIKE ?", bookRowMapper(), title);
		return book;
	}


}
