package ru.xpressed.javatemplatescoursework.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {
    @GetMapping({"/index", "/", "/home"})
    public String showIndexPage(HttpServletRequest request, Model model) {
        if (request.getUserPrincipal() != null) {
            model.addAttribute("username", request.getUserPrincipal().getName());
            model.addAttribute("linkOutOrUp", "/logout");
            model.addAttribute("textOutOrUp", "LogOut");
            model.addAttribute("linkInOrAccount", "/account");
            model.addAttribute("textInOrAccount", "Account");
        }
        else {
            model.addAttribute("username", "Stranger?");
            model.addAttribute("linkOutOrUp", "/registration");
            model.addAttribute("textOutOrUp", "SignUp");
            model.addAttribute("linkInOrAccount", "/login");
            model.addAttribute("textInOrAccount", "LogIn");
        }
        return "index";
    }
}
