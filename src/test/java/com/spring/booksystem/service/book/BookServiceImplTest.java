package com.spring.booksystem.service.book;

import com.spring.booksystem.domain.book.Book;
import com.spring.booksystem.domain.book.BookType;
import com.spring.booksystem.repository.book.BookJdbcRepository;
import com.spring.booksystem.repository.book.BookRepository;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class BookServiceImplTest {

    @Autowired private BookService bookService;


    @Test
    @DisplayName("Book 찾기 테스트")
    void booksTest() {
        Book book = new Book();
        book.setTitle("BookA");
        book.setPrice(1000);
        book.setBookType(BookType.EDUCATION);

        Book joinedBook = bookService.join(book);
        Book findBook = bookService.findBook(joinedBook.getId());

        assertThat(joinedBook.getId()).isEqualTo(findBook.getId());
    }

}