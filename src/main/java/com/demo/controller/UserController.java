package com.demo.controller;

import com.demo.model.User;
import com.demo.model.enums.Role;
import com.demo.service.FileService;
import com.demo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@AllArgsConstructor
@Controller
public class UserController {

    private final UserService userService;
    private final FileService fileService;

    @GetMapping("admin/users")
    public String list(@RequestParam(required = false) String username, Model model) {

        if (username != null && !username.isBlank()) {
            model.addAttribute("users",
                    userService.findByUsernameContaining(username));
        } else {
            model.addAttribute("users", userService.findAll());
        }
        model.addAttribute("username", username); // para mantener el valor en el input

        return "users/userList";
    }

    @GetMapping("admin/users/{id}")
    public String detailAdmin(Model model, @PathVariable Long id) {

        model.addAttribute("user", userService.findById(id)); // User
        model.addAttribute("userStats", userService.findStatsById(id));
        return "users/userDetail";

    }
    @GetMapping("/profile")
    public String profile(Authentication authentication, Model model) {

        User principal = (User) authentication.getPrincipal();

        User user = userService.findById(principal.getId());

        model.addAttribute("user", userService.findById(user.getId())); // User
        model.addAttribute("userStats", userService.findStatsById(user.getId()));

        return "users/userUDetail";
    }
    @GetMapping("admin/users/edit/{id}")
    public String editUser(Model model, @PathVariable Long id) {
        User user = userService.findById(id);
        user.setPassword(null); // no devolver esta password cifrada para evitar exponerla
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        model.addAttribute("edit", true);
        return "users/userForm";
    }

    @GetMapping("admin/users/deactivate/{id}")
    public String deactivate(@PathVariable Long id) {

        User user = userService.findById(id);
        user.setOnline(false);
        userService.update(user);
        return "redirect:/admin/users";
    }


    @GetMapping("admin/users/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", Role.values());
        model.addAttribute("edit", false);
        return "users/userForm";
    }

    @PostMapping("/admin/users")
    public String save(@ModelAttribute User user, RedirectAttributes ra,@RequestParam("imageFile") MultipartFile imageFile) {
        String gympcic = fileService.store(imageFile);
        if (gympcic != null)
            user.setImageUrl(gympcic);
        try {
            if (user.getId() == null) {
                userService.create(user);
                ra.addFlashAttribute("message", "Usuario creado");
            } else {
                userService.update(user);
                ra.addFlashAttribute("message", "Usuario actualizado");
            }

            return "redirect:/admin/users";

        } catch (Exception e) {
            ra.addFlashAttribute("error", e.getMessage());

            return (user.getId() == null)
                    ? "redirect:/admin/users/new"
                    : "redirect:/admin/users/edit/" + user.getId();
        }
    }
    @GetMapping("profile/edit/{id}")
    public String editLimitedUser(Model model, @PathVariable Long id) {
        User user = userService.findById(id);
        user.setPassword(null); // no devolver esta password cifrada para evitar exponerla
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        model.addAttribute("edit", true);
        return "users/userLimitedForm";
    }
    @PostMapping("profile")
    public String saveUser(@ModelAttribute User user, RedirectAttributes ra, @RequestParam("imageFile") MultipartFile imageFile) {

        String gympcic = fileService.store(imageFile);
        if (gympcic != null)
            user.setImageUrl(gympcic);
        try {
            if (user.getId() == null) {
                userService.create(user);
                ra.addFlashAttribute("message", "Usuario creado");
            } else {
                userService.update(user);
                ra.addFlashAttribute("message", "Usuario actualizado");
            }

            return "redirect:/login?logout";

        } catch (Exception e) {
            ra.addFlashAttribute("error", e.getMessage());

            return (user.getId() == null)
                    ? "redirect:/products"
                    : "redirect:/profile/edit/" + user.getId();
        }
    }

}//