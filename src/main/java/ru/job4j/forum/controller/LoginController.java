package ru.job4j.forum.controller;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

import static java.util.Optional.ofNullable;

@Controller
public class LoginController {

    private final String errorMessage = "Username or Password is incorrect.";
    private final String logoutMessage = "You have been successfully logged out.";

    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "logout", required = false) String logout,
                        Model model
    ) {
        resolveMessage(error, logout).ifPresent(m -> model.addAttribute("message", m));
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        ofNullable(SecurityContextHolder.getContext())
                .map(SecurityContext::getAuthentication)
                .ifPresent(auth -> new SecurityContextLogoutHandler().logout(request, response, auth));
        return "redirect:login?logout=true";
    }

    private Optional<String> resolveMessage(String error, String logout) {
        return ofNullable(error).map(err -> errorMessage)
                .or(() -> ofNullable(logout).map(out -> logoutMessage));
    }

}