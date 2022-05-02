package ru.xpressed.javatemplatescoursework.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.xpressed.javatemplatescoursework.config.SecurityConfig;
import ru.xpressed.javatemplatescoursework.entity.Customer;
import ru.xpressed.javatemplatescoursework.repository.CustomerRepository;

@Controller
public class RegistrationController {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    SecurityConfig securityConfig;

    @GetMapping("/registration")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new Customer());
        return "registration";
    }

    @PostMapping("/registration")
    public String completeRegistration(Model model, Customer customer) {
        if (customerRepository.findByUsername(customer.getUsername()) == null) {
            customer.setPassword(securityConfig.encoder().encode(customer.getPassword()));
            customerRepository.save(customer);
            return "registrationCompleted";
        } else {
            return "registrationFailed";
        }
    }
}
