package ua.com.alevel.bookstore.service;

import org.springframework.stereotype.Service;
import ua.com.alevel.bookstore.entity.Book;
import ua.com.alevel.bookstore.repository.BookRepository;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id).get();
    }

    public List<Book> getBooksByCategory(String category) {
        return bookRepository.findByCategoryName(category);
    }

    public void save(Book book) {
        bookRepository.save(book);
    }

    public Book findByTitle(String title) {
        return bookRepository.findByTitle(title);
    }

    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }
}
