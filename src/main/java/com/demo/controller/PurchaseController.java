package com.demo.controller;

import com.demo.model.Purchase;
import com.demo.repository.PurchaseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@AllArgsConstructor
@Controller
public class PurchaseController {
    private final PurchaseRepository purchaseRepository;

    @GetMapping("/purchases")
    public String purchasesList(Model model) {
        List<Purchase> purchases = purchaseRepository.findAll();
        model.addAttribute("purchases", purchases);
        return "purchase/purchaseList";
    }
}