package com.demo.controller;

import com.demo.model.User;
import com.demo.service.LikeService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@AllArgsConstructor
public class LikeController {
    private final LikeService likeService;
    @PostMapping("like/toggle")
    public String toggle(@RequestParam String type, @RequestParam Long markId, @RequestParam(defaultValue = "/products") String redirectUrl, @AuthenticationPrincipal User user, RedirectAttributes redirectAttributes) {
        boolean liked;
        if (type.equalsIgnoreCase("product")) {
            liked = likeService.toggleProduct(user, markId);
        } else {
            return "redirect:" + redirectUrl;
        }
        if (liked) {
            redirectAttributes.addFlashAttribute("message", "Producto añadido a favoritos");
        } else {
            redirectAttributes.addFlashAttribute("message", "Producto eliminado de favoritos");
        }
        return "redirect:" + redirectUrl;
    }
}
