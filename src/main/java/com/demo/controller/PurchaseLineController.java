package com.demo.controller;

import com.demo.model.PurchaseLine;
import com.demo.model.Product;
import com.demo.repository.PurchaseLineRepository;
import com.demo.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Builder
@Controller
@RequestMapping("/purchase-lines")
public class PurchaseLineController {

    @Autowired
    private PurchaseLineRepository purchaseLineRepository;

    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public String findAll(Model model){

        List<PurchaseLine> purchaseLines =
                purchaseLineRepository.findAll();

        model.addAttribute("purchaseLines", purchaseLines);

        return "purchaseLines/purchaseLinesList";
    }

    @GetMapping("/form/{id}")
    public String showForm(@PathVariable Long id, Model model) {
        PurchaseLine purchaseLine = new PurchaseLine();
        if (id != null) {
            Optional<Product> product = productRepository.findById(id);
            if (product.isPresent()) {
                purchaseLine.setProduct(product.get());
                purchaseLine.setUnitPrice(product.get().getPrice());
                model.addAttribute("category", product.get().getCategory());
                model.addAttribute("purchaseLine", purchaseLine);
                return "purchaseLines/purchaseLines-form";
            }
        }
            return "redirect:/products";


    }

    @PostMapping
    public String createPurchaseLine(@ModelAttribute PurchaseLine purchaseLine) {
        System.out.println("ACCION COMPLETADA CON EXITO: " + purchaseLine);
        purchaseLineRepository.save(purchaseLine);
        return "redirect:/purchase-lines";
    }
}