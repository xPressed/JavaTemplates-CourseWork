package ru.xpressed.javatemplatescoursework.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {
    @GetMapping("/index")
    public String showIndexPage(HttpServletRequest request) {
        System.out.println(request.getUserPrincipal().getName());
        return "index";
    }
}
