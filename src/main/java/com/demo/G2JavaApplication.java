package com.demo;

import com.demo.model.*;
import com.demo.model.enums.PurchaseStatus;
import com.demo.repository.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
                .stock(10)
                .category(null)
                .build();
        Product producto3 = Product.builder()
                .name("Proteina whey Isolate, Chocolate")
                .shortDescription("Lorem ipsum dolor sit amet consectetur adipiscing elit dignissim netus ultricies.")
                .longDescription("Lorem ipsum dolor sit amet consectetur adipiscing elit dignissim netus ultricies, dui molestie condimentum mollis bibendum potenti iaculis conubia ut, nascetur facilisis nullam venenatis ridiculus consequat cum porta cras. Tortor vel fringilla tincidunt cum eros placerat neque pharetra sapien egestas.")
                .price(34.99)
                .stock(0)
                .category(objects2)
                .build();
        producto.setImage("https://imgs.search.brave.com/Tw982nqmr6Z3Yey11hMIjA2uW4vnqLtlnBj8oQg-Ew0/rs:fit:860:0:0:0/g:ce/aHR0cHM6Ly9jb250/ZW50cy5tZWRpYWRl/Y2F0aGxvbi5jb20v/cDI5MzE3NzAvayQ0/MTFmNjEwMTUxYzk3/ZjhhY2E1YTIxNWNl/N2NlYjRlNC9waWN0/dXJlLmpwZz9mb3Jt/YXQ9YXV0byZmPTQz/MHg0MzA");
        producto2.setImage("https://imgs.search.brave.com/8Su7Icxn-hx0Zf_KmjqBkN79zTXlsdMfDZra0T99qBU/rs:fit:860:0:0:0/g:ce/aHR0cHM6Ly9ub2ds/dXRlbnRlYW0uZXMv/d3AtY29udGVudC91/cGxvYWRzLzIwMjUv/MDUvMTQ4MTcwMmVm/NWMwMDNkMjIwYzZk/NWQwNjExYTg4YzQu/anBn");
        producto3.setImage("https://imgs.search.brave.com/QxyiZt2DWpX8N-go0NCSbAlUxDJKVNMk_9-6GcBQVSU/rs:fit:860:0:0:0/g:ce/aHR0cHM6Ly93d3cu/aHNuc3RvcmUuY29t/L21lZGlhL2NhdGFs/b2cvcHJvZHVjdC9j/YWNoZS81YTE1YzU5/ODhhZmI0OTI5YTI1/MDExNjlhMzc0NjBl/Yy9oL2kvaGlnaC1w/cm90ZWluLXJpY2Ut/Y3JlYW0tbmV1dHJh/bC01MDBnLWZyb250/LWhzbl8xXzEud2Vi/cA");
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
        p1.setTotalPrice(129.98);
        p1.setDiscountCode("David15%");
        p1.setPurchaseDateTime(LocalDateTime.from(java.time.LocalDateTime.now()));
        purchaseRepository.save(p1);

        Purchase p2 = new Purchase();
        p2.setTotalPrice(64.99);
        p2.setDiscountCode("Dogo10%");
        p2.setPurchaseDateTime(LocalDateTime.from(java.time.LocalDateTime.now()));
        purchaseRepository.save(p2);

        Purchase p3 = new Purchase();
        p3.setTotalPrice(129.98);
        p3.setDiscountCode("Adri5%");
        p3.setPurchaseDateTime(LocalDateTime.from(java.time.LocalDateTime.now()));
        purchaseRepository.save(p3);


        p1.setTotalPrice(119.98);
        p1.setStatus(PurchaseStatus.FINISHED);
        purchaseRepository.save(p1);

        p2.setTotalPrice(59.99);
        p2.setStatus(PurchaseStatus.CANCEL);
        purchaseRepository.save(p2);

        p3.setTotalPrice(119.98);
        p3.setStatus(PurchaseStatus.PENDING);
        purchaseRepository.save(p3);

        // crear cuatro reviews de un restaurante usando Builder de lombok


        PurchaseLine pL1 = new PurchaseLine(null,2,p1,producto);
        PurchaseLine pL2 = new PurchaseLine(null,1,p2,producto2);
        PurchaseLine pL3 = new PurchaseLine(null,2,p3,producto2);

        List<PurchaseLine> purchaseLines = PurchaseLineRepository.saveAll(List.of(pL1,pL2,pL3));

        //Calcular precio total en java
        double totalPrice = 0;
        for (PurchaseLine lineaPedido : purchaseLines) {
            // sacar el precio del plato
            double precioLinea = lineaPedido.getProduct().getPrice() * lineaPedido.getQuantity();
             totalPrice += precioLinea;
        }
        // guardar el totalPrice en base de datos:
        p1.setTotalPrice(totalPrice);
        p1.setStatus(PurchaseStatus.FINISHED); // marcamos el pedido como completado
        purchaseRepository.save(p1); // actualizar el totalPrice del pedido para saber cuanto dinero hemos ganado



    }


}
