package com.spring.booksystem.repository.book;

import com.spring.booksystem.domain.book.Book;
import com.spring.booksystem.domain.user.User;
import com.spring.booksystem.page.PageParam;

import java.util.List;

public interface BookRepository {
    Book findById(Long id);
    List<Book> findAll(PageParam pageParam, String title);
    List<Book> findRentListByUser(String userId);
    Book save(Book book);
    void update(Long id, Book updatedBook);
    void remove(Long id);
    void rentBook(Long bookId, String userId);
    void returnBook(Long bookId);
    int getBooksTotalCount(String title);
}
