package com.demo;

import com.demo.model.*;
import com.demo.model.enums.PurchaseStatus;
import com.demo.repository.CategoryRepository;
import com.demo.repository.PurchaseRepository;
import com.demo.repository.ReviewRepository;
import com.demo.repository.ProductRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class G2JavaApplication {

    public static void main(String[] args) {
        var context = SpringApplication.run(G2JavaApplication.class, args);
        ProductRepository productRepository = context.getBean(ProductRepository.class);


        ReviewRepository reviewRepository = context.getBean(ReviewRepository.class);
        CategoryRepository categoryRepository = context.getBean(CategoryRepository.class);
        PurchaseRepository purchaseRepository = context.getBean(PurchaseRepository.class);

        Category objects = new Category();
        objects.setName("Vanilla");
        objects.setDescription("Proteins made of vanilla");
        categoryRepository.save(objects);

        Product producto = new Product("Producto_1","Lorem ipsum dolor sit amet","Lorem ipsum dolor sit ametconsectetur adipiscing elit. Quisque semper est et lectus condimentum, vel pellentesque sem laoreet",0.0,99,objects);
        productRepository.save(producto);

        Review re = new Review();
        re.setTitle(" Opinión de Creatina Creapure");
        re.setRating(5);
        re.setComment("Excelente producto");
        re.setUserVerified(true);
        re.setCreatedAt(java.time.LocalDateTime.now());
        re.setProduct(producto);
        reviewRepository.save(re);

        Product producto2 = new Product("Producto_2","Lorem ipsum dolor sit amet","Lorem ipsum dolor sit ametconsectetur adipiscing elit. Quisque semper est et lectus condimentum, vel pellentesque sem laoreet",0.0,99,objects);
        productRepository.save(producto2);

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
        p1.setPurchaseDate(LocalDate.from(java.time.LocalDateTime.now()));
        purchaseRepository.save(p1);

        Purchase p2 = new Purchase();
        p2.setQuantity(1);
        p2.setUnitPrice(64.99);
        p2.setTotal(64.99);
        p2.setPurchaseDate(LocalDate.from(java.time.LocalDateTime.now()));
        purchaseRepository.save(p2);

        Purchase p3 = new Purchase();
        p3.setQuantity(2);
        p3.setUnitPrice(64.99);
        p3.setTotal(129.98);
        p3.setPurchaseDate(LocalDate.from(java.time.LocalDateTime.now()));
        purchaseRepository.save(p3);

        PurchaseLine CremaDeArroz = new PurchaseLine(2,p1,producto);
        PurchaseLine ProteinaWheyIsolate = new PurchaseLine(1,p2,producto2);
        PurchaseLine pL3 = new PurchaseLine(2,p3,producto2);


        p1.setTotal(119.98);
        p1.setStatus(PurchaseStatus.FINISHED);
        purchaseRepository.save(p1);

        p2.setTotal(59.99);
        p2.setStatus(PurchaseStatus.CANCEL);
        purchaseRepository.save(p2);

        p3.setTotal(119.98);
        p3.setStatus(PurchaseStatus.PENDING);
        purchaseRepository.save(p3);

        Category objects2 = new Category();
        objects2.setName("Chocolate");
        objects2.setDescription("Proteins made of Chocolate");
        categoryRepository.save(objects2);

    }


}
