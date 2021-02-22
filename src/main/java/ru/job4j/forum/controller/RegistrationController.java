package ru.job4j.forum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.job4j.forum.model.User;
import ru.job4j.forum.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

import static java.util.Optional.ofNullable;
import static java.util.function.Predicate.not;

@Controller
public class RegistrationController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationController(UserService userService,
                                  PasswordEncoder passwordEncoder
    ) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/registration")
    public String initRegistration(@RequestParam(required = false) String error,
                                   Model model
    ) {
        ofNullable(error).ifPresent(v -> model.addAttribute("error", v));
        return "registration";
    }

    @PostMapping("/registration")
    public ModelAndView registerUser(@RequestParam String userName,
                                     @RequestParam String password,
                                     HttpServletRequest request,
                                     ModelMap model
    ) {
        Optional<String> validationError = validateUserData(userName, password);
        if (validationError.isPresent()) {
            model.addAttribute("errorMessage", validationError.orElse("Unexpected error."));
            return new ModelAndView("redirect:/registration?error=true", model);
        }

        User newUser = User.of(userName, passwordEncoder.encode(password));
        this.userService.saveUser(newUser);
        request.getSession(true).setAttribute("login", newUser.getLogin());
        return new ModelAndView("redirect:/index");
    }

    private Optional<String> validateUserData(String userName,
                                              String password
    ) {
        Optional<String> validUserName = ofNullable(userName).map(String::strip).filter(not(String::isBlank));
        Optional<String> validPassword = ofNullable(password).map(String::strip).filter(not(String::isBlank));
        if (validUserName.isEmpty()) {
            return Optional.of("Invalid username.");
        }
        if (validPassword.isEmpty()) {
            return Optional.of("Invalid password");
        }
        if (validUserName.equals(validPassword)) {
            return Optional.of("The username and password must be different");
        }
        if (validUserName.flatMap(userService::findByLogin).isPresent()) {
            return Optional.of("User already exists.");
        }

        return Optional.empty();
    }

}