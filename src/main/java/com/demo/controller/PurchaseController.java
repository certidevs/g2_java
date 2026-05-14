package com.demo.controller;

import com.demo.model.Purchase;
import com.demo.repository.ProductRepository;
import com.demo.repository.PurchaseLineRepository;
import com.demo.repository.PurchaseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
@AllArgsConstructor


public class PurchaseController {

    private final PurchaseRepository purchaseRepository;
    private final PurchaseLineRepository purchaseLinesRepository;
private final ProductRepository productRepository;

//     @GetMapping orders
//     filtrar por restaurante, filtrar por usuario
    @GetMapping("purchases")
    public String purchases(Model model) {
        model.addAttribute("purchases",  purchaseRepository.findAll());
        return "purchases/purchaseList";
    }

    // @GetMapping orders/{id}
    @GetMapping("purchases/{id}")
    public String purchase(Model model, @PathVariable Long id){
        model.addAttribute("purchase", purchaseRepository.findById(id).orElseThrow());
        model.addAttribute("purchaseLines", purchaseLinesRepository.findByPurchaseId(id));
        return "purchases/purchaseDetails";
    }

    // TODO finish de compra
        // guardar comentario
        // guardar datos del pago
        // set status finished
        // set purchase date time

}