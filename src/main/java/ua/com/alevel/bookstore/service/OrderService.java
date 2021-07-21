package ua.com.alevel.bookstore.service;

import org.springframework.stereotype.Service;
import ua.com.alevel.bookstore.entity.Book;
import ua.com.alevel.bookstore.entity.Order;
import ua.com.alevel.bookstore.repository.BookRepository;
import ua.com.alevel.bookstore.repository.OrderRepository;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final BookRepository bookRepository;

    public OrderService(OrderRepository orderRepository, BookRepository bookRepository) {
        this.orderRepository = orderRepository;
        this.bookRepository = bookRepository;
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public void save(Order order) {
        Book book = bookRepository.findById(order.getBook().getId()).get();

        if (book.getAvailable() > 0) {
            book.setAvailable(book.getAvailable() - 1);
            orderRepository.save(order);
        } else {
            throw new IllegalStateException("No such books left");
        }
    }
}
