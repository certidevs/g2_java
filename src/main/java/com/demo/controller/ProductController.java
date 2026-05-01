package com.demo.controller;
import com.demo.model.Product;
import com.demo.model.Review;
import com.demo.repository.ProductRepository;
import com.demo.repository.ReviewRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@AllArgsConstructor

public class ProductController {
    private final ProductRepository productRepository;
    private final ReviewRepository reviewRepository;

    @GetMapping("/products")
    public String productList( Model model){
        List<Product> products = productRepository.findAll();
        model.addAttribute("products", products);
        return "products/productsList";
    }
    @GetMapping("/products-details")
    public String productDetails(Model model){
        List<Product> products = productRepository.findAll();
        List<Review> reviews = reviewRepository.findAll();
        model.addAttribute(products);
        model.addAttribute(reviews);
        return "products/productsList";
    }
}
