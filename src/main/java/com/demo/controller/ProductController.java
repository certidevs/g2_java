package com.demo.controller;
import com.demo.model.Product;
import com.demo.model.Review;
import com.demo.repository.CategoryRepository;
import com.demo.repository.ProductRepository;
import com.demo.repository.ReviewRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor

public class ProductController {
    private final ProductRepository productRepository;
    private final ReviewRepository reviewRepository;
    private final CategoryRepository categoryRepository;

    @GetMapping("/products")
    public String productList(Model model, @RequestParam(required = false)String name) {
        List<Product> products = productRepository.findActivoFiltering(name);
        model.addAttribute("products", products);
        return "products/productsList";
    }
    @GetMapping("filter-mintomax")
    public String productMinToMax(Model model, @RequestParam(required = false)Double price) {
        List<Product> products = productRepository.findActivoMinMax(price);
        model.addAttribute("products", products);
        return "products/productsList";
    }
    @GetMapping("filter-maxtomin")
    public String productMaxToMin(Model model, @RequestParam(required = false)Double prices){
        List<Product> products = productRepository.findActivoMaxMin(prices);
        model.addAttribute("products", products);
        return "products/productsList";
    }
    @GetMapping("products/deactivate/{id}")
    public String productsDeactivate(@PathVariable Long id, Model model) {
        Optional<Product> restaurantOptional = productRepository.findById(id);

        if(restaurantOptional.isPresent()) {
            Product down = restaurantOptional.get();
            down.setActivo(false);
            productRepository.save(down);
        }

        return "redirect:/products";
    }

    @GetMapping("products/{id}")
    public String productDetail(@PathVariable Long id, Model model) {

        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()) {
            Product products = productOptional.get();
            model.addAttribute("product", products);
            // opcional:
            // cargar los platos (Dish) de este restaurant en el model
            List<Review> reviews = reviewRepository.findByProduct_IdOrderByRatingDesc(id);
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
        return "redirect:/products";
    }

    @GetMapping("products/create")
    public String productsCreate(Model model){
        model.addAttribute("product", new Product());
        model.addAttribute("category", categoryRepository.findAll());
        return "products/products-form";
    }

    @GetMapping("products/edit/{id}")
    public String productsEdit(@PathVariable Long id, Model model) {
            model.addAttribute("product", productRepository.findById(id).orElseThrow());
            model.addAttribute("category", categoryRepository.findAll());
            return "products/products-form";
        }


    @PostMapping("products")
    public String createProduct(@ModelAttribute Product product) {

        System.out.println("ACCION COMPLETADA CON EXITO: " + product);
        product.setActivo(true);
        productRepository.save(product);
        return "redirect:/products/" + product.getId();
    }
}
