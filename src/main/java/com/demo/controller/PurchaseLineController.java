package com.demo.controller;

import com.demo.model.PurchaseLine;
import com.demo.repository.PurchaseLineRepository;
import com.demo.repository.PurchaseRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@AllArgsConstructor
@Builder
@Controller
public class PurchaseLineController {
    private final PurchaseLineRepository purchaseLineRepository;
    @GetMapping("/purchaseLines")
    public String purchaseLinesList(Model model) {
        model.addAttribute("purchaseLines", purchaseLineRepository.findAll());
        return "purchase/purchaseList";
    }
}
