package com.spring.booksystem.repository.book;

import com.spring.booksystem.domain.book.Book;
import com.spring.booksystem.domain.book.BookType;
import com.spring.booksystem.domain.user.User;
import com.spring.booksystem.domain.user.UserAuth;
import com.spring.booksystem.domain.user.UserSex;
import com.spring.booksystem.page.PageParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.awt.print.Pageable;
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
    public void rentBook(Long bookId, String userId) {
        jdbcTemplate.update("UPDATE TB_COM_BOOK " +
                                "SET RENT_YN = 0 " +
                                "  , RENT_COUNT = RENT_COUNT + 1 " +
                                "WHERE BOOK_ID = ?", bookId);

        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("TB_BOOK_RENT");

        Map<String, Object> param = new HashMap<>();
        param.put("BOOK_ID", bookId);
        param.put("USER_ID", userId);
        param.put("RENT_DT", LocalDateTime.now());

        jdbcInsert.execute(param);
    }

    @Override
    public void returnBook(Long bookId) {
        jdbcTemplate.update("UPDATE TB_COM_BOOK " +
                                "SET RENT_YN = 1 " +
                                "WHERE BOOK_ID = ?", bookId);

        jdbcTemplate.update("DELETE FROM TB_BOOK_RENT " +
                                "WHERE BOOK_ID = ?", bookId);
    }

    @Override
    public Book findById(Long id) {
        List<Book> findBook = jdbcTemplate.query("SELECT * FROM TB_COM_BOOK WHERE BOOK_ID = ?", bookRowMapper(), id);
        return findBook.stream().findFirst().orElse(null);
    }

    @Override
    public int getTotalCount() {
        List<Integer> totalCount = jdbcTemplate.query("SELECT COUNT(BOOK_ID) AS TOTAL FROM TB_COM_BOOK", bookTotalCountRowMapper());
        return totalCount.get(0);
    }

    @Override
    public List<Book> findAll(PageParam pageParam) {
        int startPage = pageParam.getStart();
        int amount = pageParam.getAmount();

        List<Book> findBooks = jdbcTemplate.query("SELECT * " +
                                                      "FROM TB_COM_BOOK " +
                                                      "LIMIT " + startPage + ", " + amount
                                                      , bookRowMapper());
        return findBooks;
    }

    @Override
    public List<Book> findRentListByUser(String userId) {
        List<Book> findRentList = jdbcTemplate.query("SELECT TCB.* " +
                                                         "FROM TB_BOOK_RENT TBR " +
                                                         "INNER JOIN TB_COM_BOOK TCB ON TBR.BOOK_ID = TCB.BOOK_ID " +
                                                         "WHERE TBR.USER_ID = '" + userId + "'", bookRowMapper());
        return findRentList;
    }

    @Override
    public Book save(Book book) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("TB_COM_BOOK").usingGeneratedKeyColumns("BOOK_ID");

        Map<String, Object> param = new HashMap<>();
        param.put("TITLE", book.getTitle());
        param.put("PRICE", book.getPrice());
        param.put("TYPE_COM_CD", book.getBookType());
        param.put("RENT_YN", true);
        param.put("RENT_COUNT", 0);
        param.put("DESCRIPTION", book.getDescription());
        param.put("FILE_NM", book.getFileName());
        param.put("FILE_NM_UUID", book.getFileNameUUID());
        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(param));

        return findById(key.longValue());
    }

    @Override
    public void update(Long id, Book updatedBook) {
        jdbcTemplate.update("UPDATE TB_COM_BOOK " +
                                "SET TITLE = ?" +
                                "  , PRICE = ?" +
                                "  , TYPE_COM_CD = ?" +
                                "  , DESCRIPTION = ?" +
                                "  , FILE_NM = IFNULL(?, FILE_NM)" +
                                "  , FILE_NM_UUID = IFNULL(?, FILE_NM_UUID) " +
                                "WHERE BOOK_ID = ?" , updatedBook.getTitle()
                                                    , updatedBook.getPrice()
                                                    , updatedBook.getBookType().toString()
                                                    , updatedBook.getDescription()
                                                    , updatedBook.getFileName()
                                                    , updatedBook.getFileNameUUID()
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
            book.setRentYN(rs.getBoolean("RENT_YN"));
            book.setRentCount(rs.getInt("RENT_COUNT"));
            book.setDescription(rs.getString("DESCRIPTION"));
            book.setFileName(rs.getString("FILE_NM"));
            book.setFileNameUUID(rs.getString("FILE_NM_UUID"));
            return book;
        };
    }

    private RowMapper<Integer> bookTotalCountRowMapper() {
        return (rs, rowNum) -> rs.getInt("TOTAL");
    }
}
