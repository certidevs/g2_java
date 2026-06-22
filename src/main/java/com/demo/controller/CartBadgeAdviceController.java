package com.demo.controller;

import com.demo.model.PurchaseLine;
import com.demo.model.User;
import com.demo.model.enums.PurchaseStatus;
import com.demo.repository.PurchaseLineRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
@AllArgsConstructor
public class CartBadgeAdviceController {


        private final PurchaseLineRepository purchaseLineRepository;

        @ModelAttribute("cartItemCount")
        public Integer cartItemCount(@AuthenticationPrincipal User user) {
            if (user == null) return 0;
            Integer count = purchaseLineRepository.countCartItems(
                    user.getId(),
                    PurchaseStatus.PENDING
            );
            return count != null ? count : 0;
        }
    }

