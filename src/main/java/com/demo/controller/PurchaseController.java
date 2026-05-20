package com.demo.controller;

import com.demo.model.Purchase;
import com.demo.model.enums.PurchaseStatus;
import com.demo.repository.ProductRepository;
import com.demo.repository.PurchaseLineRepository;
import com.demo.repository.PurchaseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
@GetMapping("purchase/{id}/finish")
public String finish(@PathVariable Long id, @RequestParam(required = false) Double tip) {
    Purchase purchase =  purchaseRepository.findById(id).orElseThrow();
    purchase.setStatus(PurchaseStatus.FINISHED);
    purchase.setTotalPrice(purchaseLinesRepository.calculateTotalPrice(purchase.getId()));
    // iva, service charge, terrace


    purchaseRepository.save(purchase);
    return "redirect:/purchases/" + id;
}


    // @GetMapping purchases/{id}/finish
    // TODO finish de compra
        // guardar comentario
        // guardar datos del pago
        // set status finished
        // set purchase date time

}