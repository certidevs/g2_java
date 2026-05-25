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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class CategoryController {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    @GetMapping("categories")
    public String categoriesList( Model model){
        List<Category> categories = categoryRepository.findAllByActivoTrue();
        model.addAttribute("categories", categories);
        return "categories/categoriesList";
    }

    @GetMapping("categories/{id}")
    public String categoryDetail(@PathVariable Long id, Model model) {

        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if (categoryOptional.isPresent()) {

            Category category = categoryOptional.get();
            model.addAttribute("category", category);


            model.addAttribute("products", productRepository.findByCategory_Id(id));
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
    @GetMapping("categories/new")
    public String newCategory(Model model){
        model.addAttribute("category", new Category());
        return "categories/category-form";
    }
    @GetMapping("categories/edit/{id}")
    public String editCategory(@PathVariable Long id, Model model){
        model.addAttribute("category", categoryRepository.findById(id).orElseThrow());
        return "categories/category-form";
    }
    @GetMapping("categories/deactivate/{id}")
    public String deactivateCategory(@PathVariable Long id, Model model) {
        Optional<Category> categoryOptional = categoryRepository.findById(id);

        if(categoryOptional.isPresent()) {
            Category down = categoryOptional.get();
            down.setActivo(false);
            categoryRepository.save(down);
            return "redirect:/categories";
        }

        return "redirect:/category";
    }
    @PostMapping("categories")
    public String saveCategory(@ModelAttribute Category category) {
        categoryRepository.save(category);
        return "redirect:/categories/" + category.getId();
    }
}
