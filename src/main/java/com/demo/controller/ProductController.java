package com.demo.controller;
import com.demo.model.Product;
import com.demo.model.Review;
import com.demo.model.User;
import com.demo.repository.CategoryRepository;
import com.demo.repository.ProductRepository;
import com.demo.repository.ReviewRepository;
import com.demo.service.FileService;
import com.demo.service.LikeService;
import lombok.AllArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
@AllArgsConstructor

public class ProductController {
    private final ProductRepository productRepository;
    private final ReviewRepository reviewRepository;
    private final CategoryRepository categoryRepository;
    private final FileService fileService;
    private final LikeService likeService;

    @GetMapping("/products")
    public String productList(Model model, @RequestParam(required = false)String name ,
                              @AuthenticationPrincipal User user) {
        List<Product> products = productRepository.findActivoFiltering(name);
        if (user != null) {
            model.addAttribute("likeproductsIds",
                    likeService.findProductsIdsByUserid(user.getId()));
        }        model.addAttribute("products", products);
        return "products/productsList";
    }
    @GetMapping("filter-mintomax")
    public String productMinToMax(
            Model model,
            @AuthenticationPrincipal User user) {

        List<Product> products =
                productRepository.findActivoMinMax(null);

        if (user != null) {
            model.addAttribute(
                    "likeproductsIds",
                    likeService.findProductsIdsByUserid(user.getId()));
        }

        model.addAttribute("products", products);

        return "products/productsList";
    }
    @GetMapping("filter-maxtomin")
    public String productMaxToMin(
            Model model,
            @AuthenticationPrincipal User user) {

        List<Product> products =
                productRepository.findActivoMaxMin(null);

        if (user != null) {
            model.addAttribute(
                    "likeproductsIds",
                    likeService.findProductsIdsByUserid(user.getId()));
        }

        model.addAttribute("products", products);

        return "products/productsList";
    }
    @GetMapping("products/deactivate/{id}")
    public String productsDeactivate(@PathVariable Long id, Model model , RedirectAttributes ra) {
        Optional<Product> productOptional = productRepository.findById(id);

        if(productOptional.isPresent()) {
            Product down = productOptional.get();
            down.setActivo(false);
            productRepository.save(down);
        }
        ra.addFlashAttribute("message", "Producto desactivado correctamente.");
        return "redirect:/products";
    }

    @GetMapping("products/deactivated")
    public String deactivatedProducts(
            Model model,
            @AuthenticationPrincipal User user) {

        model.addAttribute(
                "products",
                productRepository.findByActivoFalse()
        );

        model.addAttribute("deactivatedView", true);

        if (user != null) {
            model.addAttribute(
                    "likeproductsIds",
                    likeService.findProductsIdsByUserid(user.getId())
            );
        }

        return "products/productsList";
    }
    @GetMapping("products/activate/{id}")
    public String activateProduct(
            @PathVariable Long id,
            RedirectAttributes ra) {

        Product product = productRepository.findById(id)
                .orElseThrow();

        product.setActivo(true);
        productRepository.save(product);

        ra.addFlashAttribute(
                "message",
                "Producto reactivado correctamente."
        );

        return "products/productsList";
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
    public String createProduct(@ModelAttribute Product product, @RequestParam("imageFile")MultipartFile imageFile) {
        String photo = fileService.store(imageFile);
        if (photo != null) {
            product.setImage(photo);
        }
        System.out.println("ACCION COMPLETADA CON EXITO: " + product);
        product.setActivo(true);
        productRepository.save(product);
        return "redirect:/products/" + product.getId();
    }
}
