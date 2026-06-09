package com.demo.controller;

import com.demo.model.Purchase;
import com.demo.model.User;
import com.demo.model.enums.PurchaseStatus;
import com.demo.model.enums.Role;
import com.demo.repository.ProductRepository;
import com.demo.repository.PurchaseLineRepository;
import com.demo.repository.PurchaseRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.List;


@Controller
@AllArgsConstructor


public class PurchaseController {

    private final PurchaseRepository purchaseRepository;
    private final PurchaseLineRepository purchaseLinesRepository;
private final ProductRepository productRepository;

//     filtrar por restaurante, filtrar por usuario

    //Para guardar las compras en tu cuenta de usuario
    @GetMapping("purchases")
    public String purchases(Model model,
                            @AuthenticationPrincipal User user) {
        model.addAttribute("purchases",  purchaseRepository.findAll());

        if (user.getRole().equals(Role.ROLE_ADMIN)) {
            model.addAttribute("purchases",purchaseRepository.findAll());
        } else {
            model.addAttribute("purchases",purchaseRepository.findByUser_IdOrderByPurchaseDateDesc(user.getId())            );
        }

        return "purchases/purchaseList";
    }

    @GetMapping("purchases/{id}")
    public String purchase(Model model, @PathVariable Long id){
        Purchase purchase = purchaseRepository.findById(id).orElseThrow();
        model.addAttribute("purchase", purchase);
        model.addAttribute("purchaseLines", purchaseLinesRepository.findByPurchaseId(id));
        model.addAttribute("countUserPurchases", purchaseRepository.countByUser_Id(purchase.getUser().getId()));
        model.addAttribute("totalMoneyUserSpent", purchaseRepository.calculateTotalMoneySpentByUserId(purchase.getUser().getId()));

        return "purchases/purchaseDetails";
    }
@PostMapping("purchases/{id}/finish")
    public String finish(
            @PathVariable Long id,
            @RequestParam(required = false) Double tip,
            @RequestParam(required = false) String cardOwner,
            @RequestParam(required = false) String cardNumber,
            @RequestParam(required = false) String cardExpirationDate,
            @RequestParam(required = false) String cardSecretCode,
            RedirectAttributes redirectAttributes
    ) {
        Purchase purchase =  purchaseRepository.findById(id).orElseThrow();
        // 1111 2222 3333 4444 -> 1111222233334444
        String number = cardNumber == null ? "" : cardNumber.replace("\\s", "");
        if (!number.matches("\\d{16}")) {
            redirectAttributes.addFlashAttribute("error", "Invalid card number");
            return "redirect:/purchases/" + id;
        }
        // TODO verificar que no esté caducada
        if(cardExpirationDate == null || !cardExpirationDate.matches("\\d{2}/\\d{2}")) {
            redirectAttributes.addFlashAttribute("error", "La caducidad debe tener formato MM/YY");
            return "redirect:/purchases/" + id;
        }
        if(cardSecretCode == null ||  !cardSecretCode.matches("\\d{3}")) {
            redirectAttributes.addFlashAttribute("error", "Invalid card secret code");
            return "redirect:/purchases/" + id;
        }
        if(cardOwner == null || cardOwner.isBlank()) {
            redirectAttributes.addFlashAttribute("error", "Card owner is required");
            return "redirect:/purchases/" + id;
        }
        purchase.setCardNumber(cardNumber);
        purchase.setCardOwner(cardOwner);
        purchase.setCardExpirationDate(cardExpirationDate);
        purchase.setStatus(PurchaseStatus.FINISHED);
        purchase.setTotalPrice(purchaseLinesRepository.calculateTotalPrice(purchase.getId()));
        purchase.setPurchaseDate(LocalDateTime.now());
        // iva, service charge, terrace


        purchaseRepository.save(purchase);
        redirectAttributes.addFlashAttribute("message", "Pedido finalizado correctamente");
        return "redirect:/purchases/" + id;
    }

    // @GetMapping purchases/{id}/finish
    // TODO finish de compra
        // guardar comentario
        // guardar datos del pago
        // set status finished
        // set purchase date time

}