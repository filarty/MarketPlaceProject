package com.filarty.zoomarket.controllers;


import com.filarty.zoomarket.models.User;
import com.filarty.zoomarket.models.enums.UserRole;
import com.filarty.zoomarket.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
@Slf4j
public class AdminController {
    private final UserService userService;
    @GetMapping()
    public String getAdminPanel (Principal principal, Model model) {
        log.info("user: {}", principal.getName());
        model.addAttribute("admin", userService.getUserByPrincipal(principal));
            return "admin_panel";
    }

    @GetMapping("/login")
    public String loginAdmin() {
        return "login";
    }



}
