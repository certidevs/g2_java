package com.demo.controller;

import com.demo.model.Review;
import com.demo.model.User;
import com.demo.repository.ProductRepository;
import com.demo.repository.ReviewRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;

@AllArgsConstructor
@Controller
public class ReviewController {

    //Ponemos la dependecia del repositorio(ponemos el repositorio Review)
    //Para poder usar el repositorio en el controlador
    private final ReviewRepository reviewRepository;
    private final ProductRepository productRepository;

    //Creamos el constructor para poner la dependencia del repositorio
    /*
    public ReviewController(ReviewRepository reviewRepository){
        this.reviewRepository = reviewRepository;
    } */

    //Hacemos el mapeo para mostrar la lista de reseñas
    @GetMapping("/reviews-productos")
    //Cargamos los datos
    public String reviewsList(Model model, @AuthenticationPrincipal User user) {
        // model.addAttribute("reviewsproductos", reviewRepository.findAll()); para que no se sobreescriba con el filtro de que una review esté activa
        model.addAttribute("reviewsproductos", reviewRepository.findByActiveTrueOrderByCreatedAtDesc());
        model.addAttribute("currentPath", "/reviews-productos");
        model.addAttribute("currentUser", user);
        return "reviews/reviewsList";
    }

    //Formulario de Reviews Vacio, para crear Review existente
    @GetMapping("/reviews/new")
    public String newReviews(Model model) {
        model.addAttribute("review", new Review());
        model.addAttribute("products", productRepository.findAll());
        return "reviews/reviewForm";
    }

    //Para que al escribir una opinion en "product" funcione ese boton para ese producto específico
    @GetMapping("/reviews/new/{id}")
    public String WritteReviewInProduct(@PathVariable Long id, Model model) {

        Review review = new Review();
        review.setProduct(productRepository.findById(id).orElseThrow());

        model.addAttribute("review", review);
        model.addAttribute("products", productRepository.findAll());

        return "reviews/reviewForm";
    }

    //Formulario (Reviews) Con datos

    //Todo Editar un Review Existente

    @GetMapping("/reviews/edit/{id}")
    public String editReviews(@PathVariable Long id, Model model, @AuthenticationPrincipal User user) {
        //model.addAttribute("review", reviewRepository.findById(id).orElseThrow()); //Review existente para editarlo(Actualizarlo)
        Review review = reviewRepository.findById(id).orElseThrow();
        if (user == null || review.getUser() == null || user.getId() == null
                || !review.getUser().getId().equals(user.getId())) {
            return "redirect:/reviews-productos";
        }
        model.addAttribute("review", review);
        model.addAttribute("products", productRepository.findAll());
        return "reviews/reviewForm";
    }

    @GetMapping("reviews/detail/{id}")
    public String reviewDetail(@PathVariable Long id, Model model) {
        model.addAttribute("review", reviewRepository.findById(id).orElseThrow());
        model.addAttribute("products", productRepository.findAll());
        return "reviews/reviewDetail";
    }

    @GetMapping("reviews/desactivate/{id}")
    public String desactivateReviews(@PathVariable Long id, RedirectAttributes ra) {

        Review review = reviewRepository.findById(id).orElseThrow();
        review.setActive(false);
        reviewRepository.save(review);
        ra.addFlashAttribute("message", "Opinión desactivada correctamente.");
        return "redirect:/reviews-productos";
    }
    @GetMapping("reviews/activate/{id}")
    public String activateReviews(@PathVariable Long id, RedirectAttributes ra) {
        Review review = reviewRepository.findById(id).orElseThrow();
        review.setActive(true);
        reviewRepository.save(review);
        ra.addFlashAttribute("message", "Opinión reactivada correctamente.");
        // Redirige a la lista de desactivadas para que el Admin siga gestionando
        return "redirect:/reviews/desactivated";
    }
// Esto es para
    @GetMapping("/reviews/desactivated")
    public String listDesactivatedReviews(Model model, @AuthenticationPrincipal User user) {
        // Usamos el método que ya tienes en tu repositorio
        model.addAttribute("reviewsproductos", reviewRepository.findByActiveFalseOrderByCreatedAtDesc());
        model.addAttribute("currentPath", "/reviews/desactivated");
        model.addAttribute("currentUser", user);

        // Reutilizamos la misma vista que ya tienes
        return "reviews/reviewsList";
    }

    //Recibir datos , Guardar DB
    @PostMapping("/reviews")
    public String createReviews(@ModelAttribute Review review,
                                @AuthenticationPrincipal User user) {

        System.out.println("Review recibida " + review);

        if (review.getId() != null) {

            Review existing = reviewRepository.findById(review.getId()).orElseThrow();

            if (user == null || existing.getUser() == null ||
                    !existing.getUser().getId().equals(user.getId())) {
                return "redirect:/reviews-productos";
            }
            review.setUser(existing.getUser());
            review.setCreatedAt(existing.getCreatedAt());
        } else {
            // CREATE nuevo
            review.setUser(user);
            review.setCreatedAt(LocalDateTime.now());
        }
        reviewRepository.save(review);

        return "redirect:/reviews-productos";
    }
}
