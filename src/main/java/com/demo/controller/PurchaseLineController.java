package com.demo.controller;

import com.demo.model.Purchase;
import com.demo.model.PurchaseLine;
import com.demo.model.Product;
import com.demo.model.User;
import com.demo.model.enums.PurchaseStatus;
import com.demo.repository.PurchaseLineRepository;
import com.demo.repository.ProductRepository;
import com.demo.repository.PurchaseRepository;
import com.demo.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Controller
@RequestMapping("/purchase-lines")
public class PurchaseLineController {

    private PurchaseLineRepository purchaseLineRepository;
    private ProductRepository productRepository;
    private PurchaseRepository purchaseRepository;
    private UserRepository userRepository;

    @GetMapping
    public String findAll(Model model, @AuthenticationPrincipal User user){

        Optional<Purchase> purchaseOptional = purchaseRepository.findFirstByStatus(PurchaseStatus.PENDING);
        Purchase purchase;
        if (purchaseOptional.isPresent()) {
            purchase = purchaseOptional.get();
            List<PurchaseLine> purchaseLines = purchaseLineRepository.findByPurchaseId(purchase.getId());
            model.addAttribute("purchaseLines", purchaseLines);
            // precio total
            double total = 0;
            for (PurchaseLine line : purchaseLines) {
                double subtotal = line.getProduct().getPrice() * line.getQuantity();
                total += subtotal;
            }
            model.addAttribute("total", total);
        } else {
            purchase = new Purchase();
            purchase.setStatus(PurchaseStatus.PENDING);
            purchase.setTotalPrice(0d);
            purchase.setUser(user);
            model.addAttribute("total", 0.0);
        }
        purchaseRepository.save(purchase);
        model.addAttribute("purchase", purchase);

        model.addAttribute("products", productRepository.findAll());
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
    // TODO REVISAR STATUS
@PostMapping("/create")
public String createPurchaseLine(@RequestParam Long productId, @AuthenticationPrincipal User user) {

    Product product = productRepository.findById(productId).orElseThrow();

    Optional<Purchase> purchaseOptional = purchaseRepository.findFirstByStatus(PurchaseStatus.PENDING);
    Purchase purchase;
    if (purchaseOptional.isPresent()) {
        purchase = purchaseOptional.get();
    } else {
        purchase = new Purchase();
        purchase.setStatus(PurchaseStatus.PENDING);
        purchase.setTotalPrice(0d);
        purchase.setUser(user);
        purchaseRepository.save(purchase);
    }


    Optional<PurchaseLine> purchaseLineOptional = purchaseLineRepository.findByPurchaseIdAndProductId(purchase.getId(), productId);
    PurchaseLine line;

    if (purchaseLineOptional.isPresent()) {
        line = purchaseLineOptional.get();
        line.setQuantity(line.getQuantity() + 1);
    } else {
        line = new PurchaseLine();
        line.setProduct(product);
        line.setPurchase(purchase);
        line.setQuantity(1);
        line.setUnitPrice(product.getPrice());
    }
    purchaseLineRepository.save(line);


    // recalcular precio total purchase

    Double totalPrice = purchaseLineRepository.calculateTotalPrice(purchase.getId());
    purchase.setTotalPrice(totalPrice);
    purchaseRepository.save(purchase);

    return "redirect:/purchase-lines";
}
@GetMapping("/increase/{id}")
public String increaseQuantity(@PathVariable Long id) {

    PurchaseLine line = purchaseLineRepository.findById(id)
            .orElseThrow();

    if (line.getPurchase().getStatus() == PurchaseStatus.FINISHED)
        return "redirect:/purchase-lines";

    line.setQuantity(line.getQuantity() + 1);

    purchaseLineRepository.save(line);

    return "redirect:/purchase-lines";
}
    @GetMapping("/decrease/{id}")
    public String decreaseQuantity(@PathVariable Long id) {

        PurchaseLine line = purchaseLineRepository.findById(id)
                .orElseThrow();

        if (line.getPurchase().getStatus() == PurchaseStatus.FINISHED)
            return "redirect:/purchase-lines";

        if (line.getQuantity() > 1) {

            line.setQuantity(line.getQuantity() - 1);

            purchaseLineRepository.save(line);

        } else {

            purchaseLineRepository.delete(line);
        }

        return "redirect:/purchase-lines";
    }
    @PostMapping("/update")
    public String updateTalla(
            @RequestParam Long lineId,
            @RequestParam String talla
    ) {

        PurchaseLine line = purchaseLineRepository
                .findById(lineId)
                .orElseThrow();

        line.setTalla(talla);

        purchaseLineRepository.save(line);

        return "redirect:/purchase-lines";
    }
}