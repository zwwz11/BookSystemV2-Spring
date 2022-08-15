package com.spring.booksystem.repository.book;

import com.spring.booksystem.domain.book.Book;
import com.spring.booksystem.domain.user.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@Repository
public class BookMemoryRepository implements BookRepository{
    @Override
    public void rentBook(Long bookId, String userId) {

    }

    @Override
    public void returnBook(Long bookId) {

    }

    private static Long seq = 0L;
    private static final Map<Long, Book> store = new HashMap<>();

    @Override
    public Book findById(Long id) {
        return store.get(id);
    }

    @Override
    public List<Book> findAll() {
        return store.values().stream().toList();
    }

    @Override
    public List<Book> findRentListByUser(String userId) {
        return null;
    }

    @Override
    public Book save(Book book) {
        book.setId(++seq);
        store.put(book.getId(), book);
        return store.get(seq);
    }

    @Override
    public void update(Long id, Book updatedBook) {
        Book targetUser = findById(id);
        targetUser.setTitle(updatedBook.getTitle());
        targetUser.setPrice(updatedBook.getPrice());
        targetUser.setBookType(updatedBook.getBookType());
    }

    @Override
    public void remove(Long id) {
        store.remove(id);
    }
}
