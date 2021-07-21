package ua.com.alevel.bookstore.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.com.alevel.bookstore.entity.Book;
import ua.com.alevel.bookstore.entity.Category;
import ua.com.alevel.bookstore.entity.Order;
import ua.com.alevel.bookstore.entity.User;
import ua.com.alevel.bookstore.service.BookService;
import ua.com.alevel.bookstore.service.CategoryService;
import ua.com.alevel.bookstore.service.OrderService;
import ua.com.alevel.bookstore.service.UserService;

import java.util.List;

@Controller
public class UserController {

    private final UserService userService;
    private final BookService bookService;
    private final OrderService orderService;
    private final CategoryService categoryService;

    public UserController(UserService userService, BookService bookService, OrderService orderService, CategoryService categoryService) {
        this.userService = userService;
        this.bookService = bookService;
        this.orderService = orderService;
        this.categoryService = categoryService;
    }

    @GetMapping("/user/makeOrder")
    public String makeOrder(@ModelAttribute Book book, Model model) {
        Book bookInDb = bookService.getBookById(book.getId());

        Order order = new Order();
        order.setBook(bookInDb);

        model.addAttribute("order", order);
        return "user/make_order";
    }

    @GetMapping("user/history")
    public String history(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;

        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }

        User user = userService.findByLogin(username);

        List<Order> orders = user.getOrders();
        model.addAttribute("orders", orders);

        return "user/history";
    }

    @GetMapping("/admin")
    public String adminPanel() {
        return "/admin/admin";
    }

    @GetMapping("/admin/list_users")
    public String viewUsers(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);

        return "/admin/users";
    }

    @GetMapping("/admin/orders")
    public String viewOrders(Model model) {
        List<Order> orders = orderService.getAllOrders();
        model.addAttribute("orders", orders);

        return "/admin/orders";
    }

    @GetMapping("/admin/books")
    public String viewBooks(Model model) {
        List<Book> books = bookService.getAllBooks();
        model.addAttribute("books", books);

        return "/admin/books";
    }

    @GetMapping("/admin/books/{id}")
    public String editBook(@PathVariable("id") Long id, Model model) {
        Book book = bookService.getBookById(id);
        model.addAttribute("book", book);

        return "/admin/editBook";
    }

    @PostMapping("/admin/books/{id}")
    public String editBook(@ModelAttribute("book") Book updatedBook) {
        bookService.save(updatedBook);
        return "redirect:/admin/books";
    }

    @GetMapping("/admin/addBook")
    public String addBook(Model model) {
        Book book = new Book();
        model.addAttribute("book", book);

        return "/admin/addBook";
    }

    @PostMapping("/admin/addBook")
    public String addBook(@ModelAttribute(name = "book") Book book) {
        if (bookService.findByTitle(book.getTitle()) != null) {
            throw new IllegalStateException("Book already exists");
        }

        if (categoryService.findByName(book.getCategory().getName()) != null) {
            book.setCategory(categoryService.findByName(book.getCategory().getName()));
        } else {
            Category newCategory = new Category();
            newCategory.setName(book.getCategory().getName());
            book.setCategory(newCategory);
            categoryService.save(newCategory);
        }

        bookService.save(book);

        return "redirect:/admin/books";
    }

    @PostMapping("/admin/deleteBook/{id}")
    public String deleteBook(@PathVariable("id") Long id) {
        bookService.deleteById(id);

        return "redirect:/admin/books";
    }
}
