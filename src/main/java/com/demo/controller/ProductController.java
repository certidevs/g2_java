package com.demo.controller;
import com.demo.model.Product;
import com.demo.model.Review;
import com.demo.repository.ProductRepository;
import com.demo.repository.ReviewRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor

public class ProductController {
    private final ProductRepository productRepository;
    private final ReviewRepository reviewRepository;

    @GetMapping("/products")
    public String productList(Model model) {
        List<Product> products = productRepository.findAll();
        model.addAttribute("products", products);
        return "products/productsList";
    }

    @GetMapping("products/{id}")
    public String productDetail(@PathVariable Long id, Model model) {

        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()) {

            Product product = productOptional.get();
            model.addAttribute("product", product);
            // opcional:
            // cargar los platos (Dish) de este restaurant en el model
            List<Review> reviews = reviewRepository.findByIdOrderByRatingAsc(id);
            model.addAttribute("reviews", reviews);

            // reviews
            //List<Review> reviews = reviewRepository.findAll();
            //List<Review> reviews = reviewRepository.findByRestaurant_IdOrderByCreationDateDesc(restaurant.getId());
            //model.addAttribute("reviews", reviews); // accesibles desde HTML

            return "products/products-details";

        }

        // El restaurante NO existe
        // CUIDADO no apunta a HTML
        // APUNTA al Controller
        return "redirect:/productsList";
    }
}
