package com.demo.controller;

import com.demo.model.Purchase;
import com.demo.repository.PurchaseLineRepository;
import com.demo.repository.PurchaseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@Controller
@AllArgsConstructor
public class PurchaseController {

    private final PurchaseRepository purchaseRepository;
    private final PurchaseLineRepository purchaseLinesRepository;

    // @GetMapping orders
    // filtrar por restaurante, filtrar por usuario
    @GetMapping("purchases")
    public String orders(Model model) {
        model.addAttribute("purchase",  purchaseRepository.findAll());
        return "purchase/purchaseList";
    }

    // @GetMapping orders/{id}
    @GetMapping("purchases/{id}")
    public String order(Model model, @PathVariable Long id){
        model.addAttribute("purchase", purchaseRepository.findById(id).orElseThrow());
        model.addAttribute("purchaseLines", purchaseLinesRepository.findByPurchaseId(id));
        return "purchase/purchaseDetails";
    }
}