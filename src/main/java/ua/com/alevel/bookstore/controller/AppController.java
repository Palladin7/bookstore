package ua.com.alevel.bookstore.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.com.alevel.bookstore.entity.Order;
import ua.com.alevel.bookstore.entity.User;
import ua.com.alevel.bookstore.service.OrderService;
import ua.com.alevel.bookstore.service.UserService;

import java.util.Arrays;

@Controller
@RequestMapping("/")
public class AppController {

    private final UserService userService;
    private final OrderService orderService;
    private final PasswordEncoder passwordEncoder;

    public AppController(UserService userService, OrderService orderService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.orderService = orderService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/")
    public String homePage(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;

        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }

        User user = userService.findByLogin(username);
        boolean isAdmin = false;
        boolean isLogged = false;

        if (user != null) {
            String[] roles = user.getRoles().split(",");
            isAdmin = Arrays.stream(roles).anyMatch(e -> e.contains("ADMIN"));
            isLogged = true;
        }

        model.addAttribute("isAdmin", isAdmin);
        model.addAttribute("isLogged", isLogged);

        return "homePage";
    }

    @GetMapping("/register")
    public String signUpForm(Model model) {
        model.addAttribute("user", new User());
        return "auth/sign-up";
    }

    @PostMapping("/process_register")
    public String processRegistration(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());

        user.setPassword(encodedPassword);
        user.setRoles("USER");
        userService.save(user);

        return "auth/register_success";
    }

    @PostMapping("/process_order")
    public String processOrder(Order order) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;

        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }

        User user = userService.findByLogin(username);
        order.setUser(user);

        orderService.save(order);

        return "user/order_success";
    }
}
