package com.demo.controller;

import com.demo.repository.ReviewRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReviewController {

    //Ponemos la dependecia del repositorio(ponemos el repositorio Review)
    //Para poder usar el repositorio en el controlador
    private final ReviewRepository reviewRepository;

    //Creamos el constructor para poner la dependencia del repositorio
    public ReviewController(ReviewRepository reviewRepository){
        this.reviewRepository = reviewRepository;
    }

    //Hacemos el mapeo para mostrar la lista de reseñas
    @GetMapping("/reviewsProductos")
    //Cargamos los datos
    public String reviewsList(Model model){
        model.addAttribute("reviewsproductos", reviewRepository.findAll());
        return "reviews/reviewsList";
    }


}
