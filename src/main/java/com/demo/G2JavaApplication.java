package com.demo;

import com.demo.model.Category;
import com.demo.model.CategoryType;
import com.demo.model.Purchase;
import com.demo.model.Review;
import com.demo.model.Product;
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

        Product producto = new Product("Producto_1","Lorem ipsum dolor sit amet","Lorem ipsum dolor sit ametconsectetur adipiscing elit. Quisque semper est et lectus condimentum, vel pellentesque sem laoreet",0.0,99);
        productRepository.save(producto);


        Review re = new Review();
        re.setRating(5);
        re.setComment("Excelente producto");
        re.setUserVerified(true);
        re.setCreatedAt(java.time.LocalDateTime.now());
        reviewRepository.save(re);

        Review re2 = new Review();
        re2.setRating(2);
        re2.setComment("Es mejorable");
        re2.setUserVerified(false);
        re2.setCreatedAt(java.time.LocalDateTime.now());
        reviewRepository.save(re2);

        Review re3 = new Review();
        re3.setRating(4);
        re3.setComment("Buen producto");
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

        Category objects = new Category();
        objects.setName("Vanilla");
        objects.setDescription("Proteins made of vanilla");
        categoryRepository.save(objects);



    }


}
