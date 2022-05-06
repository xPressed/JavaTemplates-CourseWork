package ru.xpressed.javatemplatescoursework.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.xpressed.javatemplatescoursework.entity.Customer;
import ru.xpressed.javatemplatescoursework.entity.Order;
import ru.xpressed.javatemplatescoursework.repository.CustomerRepository;
import ru.xpressed.javatemplatescoursework.repository.OrderRepository;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class AccountController {
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    OrderRepository orderRepository;

    @GetMapping("/account")
    public String showAccountPage(HttpServletRequest request, Model model) {
        model.addAttribute("username", request.getUserPrincipal().getName());
        model.addAttribute("linkOutOrUp", "/logout");
        model.addAttribute("textOutOrUp", "LogOut");
        model.addAttribute("linkInOrAccount", "/account");
        model.addAttribute("textInOrAccount", "Account");
        model.addAttribute("welcome", "Welcome back, " + request.getUserPrincipal().getName() + "! How's your day?");
        model.addAttribute("date", new SimpleDateFormat("dd.MM.y").format(new Date()));

        Customer customer = customerRepository.findByUsername(request.getUserPrincipal().getName());

        for (Order order : customer.orders) {
            if (new Date().compareTo(order.getArrivalDate()) >= 0) {
                order.setBtn(1);
            } else {
                order.setBtn(0);
            }
        }

        model.addAttribute("orders", customer.getOrders());

        return "account";
    }

    @GetMapping("/account/{id}")
    public String deleteOrder(@PathVariable int id, HttpServletRequest request, Model model) {
        model.addAttribute("username", request.getUserPrincipal().getName());
        model.addAttribute("linkOutOrUp", "/logout");
        model.addAttribute("textOutOrUp", "LogOut");
        model.addAttribute("linkInOrAccount", "/account");
        model.addAttribute("textInOrAccount", "Account");
        model.addAttribute("welcome", "Welcome back, " + request.getUserPrincipal().getName() + "! How's your day?");
        model.addAttribute("date", new SimpleDateFormat("dd.MM.y").format(new Date()));

        Customer customer = customerRepository.findByUsername(request.getUserPrincipal().getName());
        if (orderRepository.findById(id).isPresent()) {
            customer.orders.remove(orderRepository.getById(id));
            orderRepository.deleteById(id);
        }
        customerRepository.save(customer);

        for (Order order : customer.orders) {
            if (new Date().compareTo(order.getArrivalDate()) >= 0) {
                order.setBtn(1);
            } else {
                order.setBtn(0);
            }
        }

        model.addAttribute("orders", customer.getOrders());

        return "account";
    }
}
