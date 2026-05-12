package com.demo.controller;

import com.demo.model.Purchase;
import com.demo.model.PurchaseLine;
import com.demo.model.Product;
import com.demo.repository.PurchaseLineRepository;
import com.demo.repository.ProductRepository;
import com.demo.repository.PurchaseRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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
    @Autowired
    private PurchaseRepository purchaseRepository;

    @GetMapping
    public String findAll(Model model){

        List<PurchaseLine> purchaseLines =
                purchaseLineRepository.findAll();

        model.addAttribute("purchaseLines", purchaseLines);
        double total = 0;

        for (PurchaseLine line : purchaseLines) {

            double subtotal =
                    line.getProduct().getPrice() * line.getQuantity();

            total += subtotal;
        }

        model.addAttribute("total", total);
        return "purchaseLines/purchaseLinesList";
    }

//    @GetMapping("/form/{id}")
//    public String showForm(@PathVariable Long id, Model model) {
//        PurchaseLine purchaseLine = new PurchaseLine();
//        if (id != null) {
//            Optional<Product> product = productRepository.findById(id);
//            if (product.isPresent()) {
//                purchaseLine.setProduct(product.get());
//                purchaseLine.setUnitPrice(product.get().getPrice());
//                model.addAttribute("category", product.get().getCategory());
//                model.addAttribute("purchaseLine", purchaseLine);
//                return "purchaseLines/purchaseLines-form";
//            }
//        }
//            return "redirect:/products";
//
//
//    }
//
//    @PostMapping
//    public String createPurchaseLine(@ModelAttribute PurchaseLine purchaseLine) {
//        System.out.println("ACCION COMPLETADA CON EXITO: " + purchaseLine);
//        purchaseLineRepository.save(purchaseLine);
//        return "redirect:/purchase-lines";
//    }
@PostMapping("/create")
public String createPurchaseLine(@RequestParam Long productId) {

    Product product = productRepository.findById(productId)
            .orElseThrow();

    Purchase purchase = new Purchase();

    purchase.setPurchaseDate(LocalDate.now());

    purchaseRepository.save(purchase);

    PurchaseLine line = new PurchaseLine();

    line.setProduct(product);
    line.setPurchase(purchase);
    line.setQuantity(1);
    line.setUnitPrice(product.getPrice());

    purchaseLineRepository.save(line);

    return "redirect:/purchase-lines";
}
@GetMapping("/increase/{id}")
public String increaseQuantity(@PathVariable Long id) {

    PurchaseLine line = purchaseLineRepository.findById(id)
            .orElseThrow();

    line.setQuantity(line.getQuantity() + 1);

    purchaseLineRepository.save(line);

    return "redirect:/purchase-lines";
}
    @GetMapping("/decrease/{id}")
    public String decreaseQuantity(@PathVariable Long id) {

        PurchaseLine line = purchaseLineRepository.findById(id)
                .orElseThrow();

        if (line.getQuantity() > 1) {

            line.setQuantity(line.getQuantity() - 1);

            purchaseLineRepository.save(line);

        } else {

            purchaseLineRepository.delete(line);
        }

        return "redirect:/purchase-lines";
    }


}