package ua.com.alevel.bookstore.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.bookstore.entity.Category;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
public class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    private static final String categoryName = "Category";

    @BeforeEach
    public void init() {
        Category category = new Category();
        category.setName(categoryName);

        categoryRepository.save(category);
    }

    @AfterEach
    public void destroy() {
        Category category = categoryRepository.findByName(categoryName);

        if (category != null) {
            categoryRepository.delete(category);
        }
    }

    @Test
    public void testCreateCategory() {
        String name = "newCategory";
        Category category = new Category();
        category.setName(name);
        categoryRepository.save(category);

        category = categoryRepository.findByName(name);
        assertThat(category).isNotNull();
    }

    @Test
    public void testFindCategoryByName() {
        Category category = categoryRepository.findByName(categoryName);
        assertThat(category).isNotNull();
    }

    @Test
    public void testUpdateCategory() {
        String newName = "newName";

        Category category = categoryRepository.findByName(categoryName);
        category.setName(newName);

        categoryRepository.save(category);

        category = categoryRepository.findByName(newName);
        assertThat(category).isNotNull();
    }

    @Test
    public void testDeleteCategory() {
        Category category = categoryRepository.findByName(categoryName);
        categoryRepository.delete(category);
        category = categoryRepository.findByName(categoryName);

        assertThat(category).isNull();
    }
}
