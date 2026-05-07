package com.demo.controller;

import com.demo.model.Review;
import com.demo.repository.ProductRepository;
import com.demo.repository.ReviewRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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
    public String reviewsList(Model model){
        model.addAttribute("reviewsproductos", reviewRepository.findAll());
        return "reviews/reviewsList";
    }
//Formulario de Reviews Vacio, para crear Review existente
    @GetMapping("/reviews/new")
    public String newReviews(Model model){
        model.addAttribute("review", new Review());
        model.addAttribute("products" , productRepository.findAll());
        return "reviews/reviewForm";
    }

    //Formulario (Reviews) Con datos

    //Todo Editar un Review Existente

    @GetMapping("/reviews/edit/{id}")
    public String editReviews(@PathVariable Long id, Model model){
        model.addAttribute("review", reviewRepository.findById(id).orElseThrow()); //Review existente para editarlo(Actualizarlo)
        model.addAttribute("products" , productRepository.findAll());
        return "reviews/reviewForm";
    }

    //Recibir datos , Guardar DB
    @PostMapping("/reviews")
    public String createReviews(@ModelAttribute Review review){
        System.out.println("Review recibida" +review);
        reviewRepository.save(review);
        return "redirect:/reviews-productos" +review.getId();
    }


}
