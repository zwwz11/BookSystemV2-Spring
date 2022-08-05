package com.spring.booksystem.service.book;

import com.spring.booksystem.domain.book.Book;
import com.spring.booksystem.domain.book.BookType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class BookServiceTest {

    @Autowired private BookService bookService;


    @Test
    @DisplayName("Book 찾기 테스트")
    void findBook() {
        // given
        Book book = new Book();
        book.setTitle("BookA");
        book.setPrice(1000);
        book.setBookType(BookType.EDUCATION);

        // when
        Book joinedBook = bookService.join(book);
        Book findBook = bookService.findBook(joinedBook.getId());

        // then
        assertThat(joinedBook.getId()).isEqualTo(findBook.getId());
    }

    @Test
    @DisplayName("Book 등록 테스트")
    void saveBook() {
        // given
        Book book = new Book();
        book.setTitle("BookA");
        book.setPrice(1000);
        book.setBookType(BookType.EDUCATION);

        // when
        Book joinedBook = bookService.join(book);
        Book findBook = bookService.findBook(joinedBook.getId());

        // then
        assertThat(joinedBook.getId()).isEqualTo(findBook.getId());
    }

    @Test
    @DisplayName("Book 업데이트 테스트")
    void updateBook() {
        // given
        Book bookA = new Book();
        bookA.setTitle("BookA");
        bookA.setPrice(1000);
        bookA.setBookType(BookType.EDUCATION);

        Book bookB = new Book();
        bookB.setTitle("BookB");
        bookB.setPrice(10000);
        bookB.setBookType(BookType.EDUCATION);

        // when
        Book joinedBook = bookService.join(bookA);
        bookService.editBook(joinedBook.getId(), bookB);
        Book findBook = bookService.findBook(joinedBook.getId());

        // then
        assertThat(findBook.getTitle()).isEqualTo(bookB.getTitle());
    }

    @Test
    @DisplayName("Book 삭제 테스트")
    void deleteBook() {
        // given
        Book bookA = new Book();
        bookA.setTitle("BookA");
        bookA.setPrice(1000);
        bookA.setBookType(BookType.EDUCATION);

        // when
        Book joinedBook = bookService.join(bookA);
        bookService.deleteBook(joinedBook.getId());
        Book findBook = bookService.findBook(joinedBook.getId());

        // then
        assertThat(findBook).isEqualTo(null);
    }


}