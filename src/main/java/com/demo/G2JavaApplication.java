package com.demo;

import com.demo.model.*;
import com.demo.model.enums.PurchaseStatus;
import com.demo.repository.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class G2JavaApplication {

    public static void main(String[] args) {
        var context = SpringApplication.run(G2JavaApplication.class, args);
        ProductRepository productRepository = context.getBean(ProductRepository.class);


        ReviewRepository reviewRepository = context.getBean(ReviewRepository.class);
        CategoryRepository categoryRepository = context.getBean(CategoryRepository.class);
        PurchaseRepository purchaseRepository = context.getBean(PurchaseRepository.class);
        PurchaseLineRepository PurchaseLineRepository = context.getBean(PurchaseLineRepository.class);

        Category objects = new Category();
        objects.setName("Vanilla");
        objects.setDescription("Proteins made of vanilla");
        categoryRepository.save(objects);

        Category objects2 = new Category();
        objects2.setName("Chocolate");
        objects2.setDescription("Proteins made of Chocolate");
        categoryRepository.save(objects2);

        Product producto = Product.builder()
                .name("Creatina Creapure, Sabor Vanilla")
                .shortDescription("Lorem ipsum dolor sit amet consectetur adipiscing elit dignissim netus ultricies.")
                .longDescription("Lorem ipsum dolor sit amet consectetur adipiscing elit dignissim netus ultricies, dui molestie condimentum mollis bibendum potenti iaculis conubia ut, nascetur facilisis nullam venenatis ridiculus consequat cum porta cras. Tortor vel fringilla tincidunt cum eros placerat neque pharetra sapien egestas.")
                .price(26.50)
                .stock(99)
                .category(objects)
                .build();
        Product producto2 = Product.builder()
                .name("Crema de arroz proteica")
                .shortDescription("Lorem ipsum dolor sit amet consectetur adipiscing elit dignissim netus ultricies.")
                .longDescription("Lorem ipsum dolor sit amet consectetur adipiscing elit dignissim netus ultricies, dui molestie condimentum mollis bibendum potenti iaculis conubia ut, nascetur facilisis nullam venenatis ridiculus consequat cum porta cras. Tortor vel fringilla tincidunt cum eros placerat neque pharetra sapien egestas.")
                .price(8.99)
                .stock(99)
                .category(null)
                .build();
        Product producto3 = Product.builder()
                .name("Proteina whey Isolate, Chocolate")
                .shortDescription("Lorem ipsum dolor sit amet consectetur adipiscing elit dignissim netus ultricies.")
                .longDescription("Lorem ipsum dolor sit amet consectetur adipiscing elit dignissim netus ultricies, dui molestie condimentum mollis bibendum potenti iaculis conubia ut, nascetur facilisis nullam venenatis ridiculus consequat cum porta cras. Tortor vel fringilla tincidunt cum eros placerat neque pharetra sapien egestas.")
                .price(34.99)
                .stock(99)
                .category(objects2)
                .build();
        productRepository.saveAll(List.of(producto,producto2,producto3));


        Review re = new Review();
        re.setTitle(" Opinión de Creatina Creapure");
        re.setRating(5);
        re.setComment("Excelente producto");
        re.setUserVerified(true);
        re.setCreatedAt(java.time.LocalDateTime.now());
        re.setProduct(producto);
        reviewRepository.save(re);



        Review re2 = new Review();
        re2.setTitle("Opinión de Crema de Arroz");
        re2.setRating(2);
        re2.setComment("Malo");
        re2.setUserVerified(false);
        re2.setProduct(producto2);
        re2.setCreatedAt(java.time.LocalDateTime.now());
        reviewRepository.save(re2);

        Review re3 = new Review();
        re3.setTitle("Opinión de Proteina whey Isolate");
        re3.setRating(4);
        re3.setComment("Muy Buen producto");
        re3.setUserVerified(true);
        re3.setCreatedAt(java.time.LocalDateTime.now());
        reviewRepository.save(re3);


        Purchase p1 = new Purchase();
        p1.setQuantity(2);
        p1.setUnitPrice(64.99);
        p1.setTotal(129.98);
        p1.setDiscountCode("David15%");
        p1.setPurchaseDate(LocalDate.from(java.time.LocalDateTime.now()));
        purchaseRepository.save(p1);

        Purchase p2 = new Purchase();
        p2.setQuantity(1);
        p2.setUnitPrice(64.99);
        p2.setTotal(64.99);
        p2.setDiscountCode("Dogo10%");
        p2.setPurchaseDate(LocalDate.from(java.time.LocalDateTime.now()));
        purchaseRepository.save(p2);

        Purchase p3 = new Purchase();
        p3.setQuantity(2);
        p3.setUnitPrice(64.99);
        p3.setTotal(129.98);
        p3.setDiscountCode("Adri5%");
        p3.setPurchaseDate(LocalDate.from(java.time.LocalDateTime.now()));
        purchaseRepository.save(p3);


        p1.setTotal(119.98);
        p1.setStatus(PurchaseStatus.FINISHED);
        purchaseRepository.save(p1);

        p2.setTotal(59.99);
        p2.setStatus(PurchaseStatus.CANCEL);
        purchaseRepository.save(p2);

        p3.setTotal(119.98);
        p3.setStatus(PurchaseStatus.PENDING);
        purchaseRepository.save(p3);

        PurchaseLine pL1 = new PurchaseLine(2,p1,producto);
        PurchaseLine pL2 = new PurchaseLine(1,p2,producto2);
        PurchaseLine pL3 = new PurchaseLine(2,p3,producto2);
        List<PurchaseLine> purchaseLines = PurchaseLineRepository.saveAll(List.of(pL1,pL2,pL3));





    }


}
