package com.spring.booksystem.service.book;

import com.spring.booksystem.domain.book.Book;
import com.spring.booksystem.domain.user.User;

import java.util.List;

public interface BookService {
    Book findBook(Long id);
    List<Book> findAllBook();
    Book join(Book book);
    void editBook(Long id, Book book);
    void deleteBook(Long id);
}
