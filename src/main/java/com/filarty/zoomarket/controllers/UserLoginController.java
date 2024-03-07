package com.filarty.zoomarket.controllers;

import com.filarty.zoomarket.models.User;
import com.filarty.zoomarket.models.enums.UserRole;
import com.filarty.zoomarket.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@Slf4j
public class UserLoginController {
    private final UserService userService;
    @GetMapping("/login")
    public String login() {;
        return "login";
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String postRegistration(User user) {
        userService.saveUserWithRole(user, UserRole.USER);
        return "redirect:/login";
    }

    @GetMapping("/registration/confirm")
    public String confirm(@RequestParam(name = "token") String token) {
        log.info("token: {}", token);
        userService.confirmUser(token);
        return "redirect:/";
    }
}
