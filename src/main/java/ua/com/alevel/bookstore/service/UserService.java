package ua.com.alevel.bookstore.service;

import org.springframework.stereotype.Service;
import ua.com.alevel.bookstore.entity.User;
import ua.com.alevel.bookstore.repository.UserRepository;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findByLogin(String username) {
        return userRepository.findByEmail(username);
    }
}
