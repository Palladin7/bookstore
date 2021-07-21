package ua.com.alevel.bookstore.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.bookstore.entity.Book;
import ua.com.alevel.bookstore.entity.Category;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    private static final String title = "Title";
    private static final String categoryName = "Category";

    @BeforeEach
    public void init() {
        Category category = new Category();
        category.setName(categoryName);
        categoryRepository.save(category);

        Book book = new Book();
        book.setTitle(title);
        book.setAuthor("Author");
        book.setCategory(category);
        book.setAvailable(20);
        book.setPages(400);
        book.setPrice(13.99);
        bookRepository.save(book);
    }

    @AfterEach
    public void destroy() {
        Book book = bookRepository.findByTitle(title);
        Category category = categoryRepository.findByName(categoryName);

        if (book != null) {
            bookRepository.delete(book);
        }

        if (category != null) {
            categoryRepository.delete(category);
        }
    }

    @Test
    public void testCreateBook() {
        Category category = categoryRepository.findByName(categoryName);
        String bookTitle = "bookTitle";

        Book book = new Book();
        book.setTitle(bookTitle);
        book.setAuthor("Author");
        book.setCategory(category);
        book.setAvailable(20);
        book.setPages(400);
        book.setPrice(13.99);

        bookRepository.save(book);

        book = bookRepository.findByTitle(bookTitle);

        assertThat(book).isNotNull();
    }

    @Test
    public void testFindByCategory() {
        List<Book> books = bookRepository.findByCategoryName(categoryName);

        assertThat(books).isNotNull();
    }

    @Test
    public void testUpdateBook() {
        String newTitle = "newTitle";

        Book book = bookRepository.findByTitle(title);
        book.setTitle(newTitle);

        bookRepository.save(book);

        book = bookRepository.findByTitle(newTitle);
        assertThat(book).isNotNull();
    }

    @Test
    public void testDeleteBook() {
        Book book = bookRepository.findByTitle(title);
        bookRepository.delete(book);
        book = bookRepository.findByTitle(title);

        assertThat(book).isNull();
    }
}
