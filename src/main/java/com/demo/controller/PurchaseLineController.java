package com.demo.controller;

import com.demo.model.PurchaseLine;
import com.demo.repository.PurchaseLineRepository;
import com.demo.repository.PurchaseRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@AllArgsConstructor
@Builder
@Controller
@RequestMapping("/purchase-lines")
public class PurchaseLineController {

    @Autowired
    private PurchaseLineRepository purchaseLineRepository;

    @GetMapping
    public String findAll(Model model){

        List<PurchaseLine> purchaseLines =
                purchaseLineRepository.findAll();

        model.addAttribute("purchaseLines", purchaseLines);

        return "purchaseLines/purchaseLinesList";
    }
}