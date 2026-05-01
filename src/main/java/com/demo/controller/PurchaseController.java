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
    private final PurchaseLineRepository purchaseLineRepository;

    @GetMapping("/purchases")
    public String purchasesList(Model model) {

        //if user admin
        //      findAll
        //else
        //      findById
        List<Purchase> purchases = purchaseRepository.findAll();
        model.addAttribute("purchases", purchases);
        return "purchase/purchaseList";
    }
    @GetMapping("purchases/{id}")
    public String purchase(Model model, @PathVariable Long id) {
        Purchase purchase = purchaseRepository.findById(id).orElseThrow();
        model.addAttribute("purchase", purchase);
        model.addAttribute("purchaseLines", purchaseLineRepository.findAll());
        return "purchase/purchaseDetails";
    }
}