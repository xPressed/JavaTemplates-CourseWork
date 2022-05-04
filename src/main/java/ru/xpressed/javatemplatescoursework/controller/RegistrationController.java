package ru.xpressed.javatemplatescoursework.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.xpressed.javatemplatescoursework.config.SecurityConfig;
import ru.xpressed.javatemplatescoursework.entity.Customer;
import ru.xpressed.javatemplatescoursework.repository.CustomerRepository;

import javax.validation.Valid;

@Controller
public class RegistrationController {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    SecurityConfig securityConfig;

    @GetMapping("/registration")
    public String showRegistrationForm(Customer customer, Model model) {
        model.addAttribute("customer", new Customer());
        return "registration";
    }

    @PostMapping("/registration")
    public String completeRegistration(@Valid Customer customer, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("customer", customer);
            return "registration";
        }

        if (!customer.getPassword().equals(customer.getRepeated())) {
            model.addAttribute("reperr", "Passwords don't match!");
            return "registration";
        }

        if (customerRepository.findByUsername(customer.getUsername()) == null) {
            customer.setPassword(securityConfig.encoder().encode(customer.getPassword()));
            customerRepository.save(customer);
            model.addAttribute("message", "Registration Completed");
            model.addAttribute("onload", "redirect()");
        } else {
            bindingResult.rejectValue("username", "customer.username", "This username is already taken!");
        }
        return "registration";
    }
}
