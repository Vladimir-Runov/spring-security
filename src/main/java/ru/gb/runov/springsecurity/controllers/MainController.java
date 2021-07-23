package ru.gb.runov.springsecurity.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class MainController {
    @GetMapping("/")
    public String homePage() {
        return "home";
    }

    @GetMapping("/unsecured")
    public String usecuredPage() {
        return "unsecured";
    }

    @GetMapping("/auth_page")
    public String authenticatedPage() {
        return "authenticated";
    }

    @GetMapping("/admin")
    public String adminPage() {
        return "admin";
    }
}
