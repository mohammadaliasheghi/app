package com.google.otp.controller;

import com.google.otp.entity.Users;
import com.google.otp.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.Optional;
import java.util.Random;

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

    @GetMapping("/getCookie")
    public String getCookie(HttpServletRequest request) {
        for (Cookie cookie : request.getCookies())
            System.out.println(cookie.getName() + " : " + cookie.getValue());
        return "index";
    }

    @GetMapping("/setCookie")
    public String setCookie(HttpServletResponse response) {
        Cookie cookie = new Cookie("mohammad", "42589");
        cookie.setMaxAge(24 * 60 * 60);
        response.addCookie(cookie);
        return "index";
    }

    @GetMapping("/info")
    @PreAuthorize("hasAuthority('OP_PRINCIPAL_INFO')")
    public @ResponseBody
    Principal getPrincipal(Principal principal) {
        return principal;
    }

    @GetMapping("/otp/login")
    public String optLogin() {
        return "otp-username";
    }

    @PostMapping("/otp/check-username")
    public String checkUsername(@ModelAttribute("email") String email, HttpSession httpSession) {
        Users users = (Users) usersService.loadUserByUsername(email);
        Random random = new Random();
        int password;
        if (users == null) {
            users = new Users();
            users.setEmail(email);
        }
        password = random.nextInt(999999);
        System.out.println(password);
        users.setPassword(String.valueOf(password));
        usersService.register(users);

        httpSession.setAttribute("email", email);
        return "redirect:/otp/check-password";
    }

    @GetMapping("/otp/check-password")
    public String otpPassword(HttpSession httpSession, Model model) {
        String email = httpSession.getAttribute("email").toString();
        model.addAttribute("email", email);
        return "otp-password";
    }

}
