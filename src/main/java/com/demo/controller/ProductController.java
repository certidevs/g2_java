package com.demo.controller;

import com.demo.model.Category;
import com.demo.model.Product;
import com.demo.repository.CategoryRepository;
import com.demo.repository.ProductRepository;
import com.demo.repository.PurchaseRepository;
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
    //private final ReviewRepository reviewRepository;
    //private final PurchaseRepository purchaseRepository;
    private final CategoryRepository categoryRepository;

    @GetMapping("/products")
    public String productList(Model model){
        List<Product> products = productRepository.findAll();
        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("products", products);
        model.addAttribute("categories", categories);
        return "products/productsList";
    }
}
