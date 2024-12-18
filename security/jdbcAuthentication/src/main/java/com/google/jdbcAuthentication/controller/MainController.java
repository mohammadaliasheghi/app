package com.google.jdbcAuthentication.controller;

import com.google.jdbcAuthentication.entity.Users;
import com.google.jdbcAuthentication.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final UsersService usersService;

    @GetMapping("/signUp")
    public String signUpPage() {
        return "signUp";
    }

    @GetMapping("/")
    public String indexPage() {
        return "index";
    }

    @GetMapping("/error")
    public String errorPage() {
        return "error";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('OP_ACCESS_ADMIN')")
    public String adminPage() {
        return "admin";
    }

    @GetMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('OP_DELETE_USER')")
    public String delete(@PathVariable Long id) {
        Optional<Users> users = usersService.get(id);
        users.ifPresent(usersService::deleteUser);
        return "redirect:/admin";
    }

    @GetMapping("/get/{id}")
    @PreAuthorize("hasAuthority('OP_GET_USER')")
    public ResponseEntity<Users> get(@PathVariable Long id) {
        return new ResponseEntity<>(usersService.getUsers(id), HttpStatus.OK);
    }

    @GetMapping("/user")
    @PreAuthorize("hasAuthority('OP_ACCESS_USER')")
    public String userPage() {
        return "user";
    }
}
