package com.demo.controller;

import com.demo.repository.UserRepository;
import com.demo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@AllArgsConstructor
@Controller
public class UserController {

    private final UserService userService;

    @GetMapping("admin/users")
    public String list(Model model) {
        model.addAttribute("users", userService.findAll());
        return "users/userList";
    }

    @GetMapping("admin/users/{id}")
    public String detail(Model model, @PathVariable Long id) {

        model.addAttribute("user", userService.findById(id)); // User
        model.addAttribute("userStats", userService.findStatsById(id));
        return "users/userDetail";

    }
}
