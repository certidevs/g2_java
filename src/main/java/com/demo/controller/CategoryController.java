package com.demo.controller;

import com.demo.model.Category;
import com.demo.model.Product;
import com.demo.model.Review;
import com.demo.repository.CategoryRepository;
import com.demo.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class CategoryController {

    private final CategoryRepository categoryRepository;

    @GetMapping("category")
    public String categoriesList( Model model){
        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);
        return "categories/categoriesList";
    }

    @GetMapping("categories/{id}")
    public String categoryDetail(@PathVariable Long id, Model model) {

        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if (categoryOptional.isPresent()) {

            Category category = categoryOptional.get();
            model.addAttribute("category", category);
            // opcional:
            // cargar los platos (Dish) de este restaurant en el model

            // reviews
            //List<Review> reviews = reviewRepository.findAll();
            //List<Review> reviews = reviewRepository.findByRestaurant_IdOrderByCreationDateDesc(restaurant.getId());
            //model.addAttribute("reviews", reviews); // accesibles desde HTML

            return "categories/categories-details";
        }
        return "redirect:/categoriesList";
    }
}
