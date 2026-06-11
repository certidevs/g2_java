package com.demo.controller;

import com.demo.model.Category;
import com.demo.model.Product;
import com.demo.model.Review;
import com.demo.repository.CategoryRepository;
import com.demo.repository.ProductRepository;
import com.demo.service.FileService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class CategoryController {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final FileService fileService;

    @GetMapping("categories")
    public String categoriesList(Model model) {
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
    public String newCategory(Model model) {
        model.addAttribute("category", new Category());
        return "categories/category-form";
    }

    @GetMapping("categories/edit/{id}")
    public String editCategory(@PathVariable Long id, Model model) {
        model.addAttribute("category", categoryRepository.findById(id).orElseThrow());
        return "categories/category-form";
    }

    @GetMapping("categories/deactivate/{id}")
    public String deactivateCategory(@PathVariable Long id, Model model , RedirectAttributes ra) {
        Optional<Category> categoryOptional = categoryRepository.findById(id);

        if (categoryOptional.isPresent()) {
            Category down = categoryOptional.get();
            down.setActivo(false);
            categoryRepository.save(down);
            ra.addFlashAttribute("message", "Categoria desactivada correctamente.");

            return "redirect:/categories";
        }
        return "redirect:/category";
    }

    @PostMapping("/categories")
    public String saveCategory(
            @ModelAttribute Category category,
            @RequestParam MultipartFile file , RedirectAttributes red) {

        try {
            String photo = fileService.store(file);

            if (photo != null) {
                category.setImageFile(photo);
            }

            category.setActivo(true);
            categoryRepository.save(category);
            red.addFlashAttribute("message", "Categoría guardada con éxito.");
            return "redirect:/categories";
        } catch (IllegalArgumentException e){
            red.addFlashAttribute("error" , e.getMessage());
            return "redirect:/categories/new";
        }catch (Exception e) {
            red.addFlashAttribute("error" , "Ocurrio un error al guardar.");
            return "redirect:/categories/new";
        }
    }
}

