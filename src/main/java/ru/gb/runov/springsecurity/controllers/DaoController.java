package ru.gb.runov.springsecurity.controllers;

import ru.gb.runov.springsecurity.model.entities.DbUser;
import ru.gb.runov.springsecurity.services.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@Profile("dao")
@Slf4j
@RequiredArgsConstructor
public class DaoController {

    private final UserService userService;

    @GetMapping("/dao")
    public String daoTestPage(Principal principal) {
        DbUser user = userService.findByUsername(principal.getName()).orElseThrow(() -> new RuntimeException("unable to find a user by username: " + principal.getName()));
        return "authenticated: " + user.getUsername() + " Scope: " + user.getScope();
    }
}
