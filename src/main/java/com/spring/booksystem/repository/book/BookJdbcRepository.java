package com.spring.booksystem.repository.book;

import com.spring.booksystem.domain.book.Book;
import com.spring.booksystem.domain.book.BookType;
import com.spring.booksystem.domain.user.User;
import com.spring.booksystem.domain.user.UserAuth;
import com.spring.booksystem.domain.user.UserSex;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Repository
public class BookJdbcRepository implements BookRepository{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookJdbcRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Book findById(Long id) {
        List<Book> findBook = jdbcTemplate.query("SELECT * FROM TB_COM_BOOK WHERE BOOK_ID = ?", bookRowMapper(), id);
        return findBook.stream().findFirst().get();
    }

    @Override
    public List<Book> findAll() {
        List<Book> findBooks = jdbcTemplate.query("SELECT * FROM TB_COM_BOOK", bookRowMapper());
        return findBooks;
    }

    @Override
    public Book save(Book book) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("TB_COM_BOOK").usingGeneratedKeyColumns("BOOK_ID");

        Map<String, Object> param = new HashMap<>();
        param.put("TITLE", book.getTitle());
        param.put("PRICE", book.getPrice());
        param.put("TYPE_COM_CD", book.getBookType());
        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(param));

        return findById(key.longValue());
    }

    @Override
    public void update(Long id, Book updatedBook) {
        jdbcTemplate.update("UPDATE TB_COM_BOOK " +
                                "SET TITLE = ?" +
                                "  , PRICE = ?" +
                                "  , TYPE_COM_CD = ?" +
                                "WHERE BOOK_ID = ?" , updatedBook.getTitle()
                                                    , updatedBook.getPrice()
                                                    , updatedBook.getBookType().toString()
                                                    , id);
    }

    @Override
    public void remove(Long id) {
        jdbcTemplate.update("DELETE FROM TB_COM_BOOK WHERE BOOK_ID = ?", id);
    }

    private RowMapper<Book> bookRowMapper() {
        return (rs, rowNum) -> {
            Book book = new Book();
            book.setId(rs.getLong("BOOK_ID"));
            book.setTitle(rs.getString("TITLE"));
            book.setPrice(rs.getInt("PRICE"));
            book.setBookType(BookType.valueOf(rs.getString("TYPE_COM_CD")));
            return book;
        };
    }
}