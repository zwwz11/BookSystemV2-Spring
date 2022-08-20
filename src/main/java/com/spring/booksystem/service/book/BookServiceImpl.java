package com.spring.booksystem.service.book;

import com.spring.booksystem.domain.book.Book;
import com.spring.booksystem.page.PageParam;
import com.spring.booksystem.repository.book.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService{

    private final BookRepository bookRepository;

    @Override
    public void rent(Long bookId, String userId) {
        bookRepository.rentBook(bookId, userId);
    }

    @Override
    public void returnBook(Long bookId) {
        bookRepository.returnBook(bookId);
    }

    @Override
    public Book findBook(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public List<Book> findAllBook(PageParam pageParam) {
        return bookRepository.findAll(pageParam);
    }

    @Override
    public List<Book> findRentListByUser(String userId) {
        return bookRepository.findRentListByUser(userId);
    }

    @Override
    public Book join(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public void editBook(Long id, Book book) {
        bookRepository.update(id, book);
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.remove(id);
    }

    @Override
    public int getBooksTotalCount() {
        return bookRepository.getBooksTotalCount();
    }
}
