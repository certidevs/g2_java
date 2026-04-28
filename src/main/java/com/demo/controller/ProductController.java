package com.demo.controller;
import com.demo.model.Product;
import com.demo.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@AllArgsConstructor

public class ProductController {
    private final ProductRepository productRepository;

    @GetMapping("/products")
    public String productList( Model model){
        List<Product> products = productRepository.findAll();
        model.addAttribute("products", products);
        return "products/productsList";
    }
}
