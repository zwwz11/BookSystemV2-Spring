package com.spring.booksystem.repository.book;

import com.spring.booksystem.domain.book.Book;
import com.spring.booksystem.domain.user.User;

import java.util.List;

public interface BookRepository {
    Book findById(Long id);
    List<Book> findAll();
    Book save(Book book);
    void update(Long id, Book updatedBook);
    void remove(Long id);
}
