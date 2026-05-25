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
        objects.setName("Creatina");
        objects.setDescription("La creatina es un aminoácido que se sintetiza en el hígado y se almacena en los músculos. Cuando se combina con fosfato, es una fuente de energía fácilmente disponible en el organismo. Aquí puedes encontrar todos los tipos.");
        objects.setImage("https://bodymania.com/cdn/shop/files/sophbSJk.png?v=1730919445&width=1946");
        objects.setActivo(true);
        categoryRepository.save(objects);

        Category objects2 = new Category();
        objects2.setName("Crema de arroz");
        objects2.setDescription("La crema de arroz en suplementación es un carbohidrato complejo de digestión rápida obtenido a partir de la harina de arroz. Aquí puedes encontrar todos los tipos.");
        objects2.setImage("https://imgs.search.brave.com/QxyiZt2DWpX8N-go0NCSbAlUxDJKVNMk_9-6GcBQVSU/rs:fit:860:0:0:0/g:ce/aHR0cHM6Ly93d3cu/aHNuc3RvcmUuY29t/L21lZGlhL2NhdGFs/b2cvcHJvZHVjdC9j/YWNoZS81YTE1YzU5/ODhhZmI0OTI5YTI1/MDExNjlhMzc0NjBl/Yy9oL2kvaGlnaC1w/cm90ZWluLXJpY2Ut/Y3JlYW0tbmV1dHJh/bC01MDBnLWZyb250/LWhzbl8xXzEud2Vi/cA");
        objects2.setActivo(true);
        categoryRepository.save(objects2);

        Category objects3 = new Category();
        objects3.setName("Proteínas");
        objects3.setDescription("La proteína en polvo es un tipo de suplemento que aporta de forma rápida y sencilla proteínas de alta calidad a nuestro organismo. Aquí puedes encontrar todos los tipos.");
        objects3.setImage("https://imgs.search.brave.com/8Su7Icxn-hx0Zf_KmjqBkN79zTXlsdMfDZra0T99qBU/rs:fit:860:0:0:0/g:ce/aHR0cHM6Ly9ub2ds/dXRlbnRlYW0uZXMv/d3AtY29udGVudC91/cGxvYWRzLzIwMjUv/MDUvMTQ4MTcwMmVm/NWMwMDNkMjIwYzZk/NWQwNjExYTg4YzQu/anBn");
        objects3.setActivo(true);
        categoryRepository.save(objects3);

        Category objects4 = new Category();
        objects4.setName("Ropa");
        objects4.setDescription("Ropa deportiva");
        objects4.setImage("https://bodymania.com/cdn/shop/files/sophbSJk.png?v=1730919445&width=1946");
        objects4.setActivo(true);
        categoryRepository.save(objects4);

        Category objects5 = new Category();
        objects5.setName("Suplementos");
        objects5.setDescription("Ayudas para rendir a tu 100%");
        objects5.setImage("https://bodymania.com/cdn/shop/files/sophbSJk.png?v=1730919445&width=1946");
        objects5.setActivo(true);
        categoryRepository.save(objects5);




        Product producto = Product.builder()
                .name("Creatina Creapure, Sabor Fresa")
                .shortDescription("Lorem ipsum dolor sit amet consectetur adipiscing elit dignissim netus ultricies.")
                .longDescription("Lorem ipsum dolor sit amet consectetur adipiscing elit dignissim netus ultricies, dui molestie condimentum mollis bibendum potenti iaculis conubia ut, nascetur facilisis nullam venenatis ridiculus consequat cum porta cras. Tortor vel fringilla tincidunt cum eros placerat neque pharetra sapien egestas.")
                .price(26.50)
                .stock(99)
                .category(objects)
                .activo(true)
                .image("https://bodymania.com/cdn/shop/files/sophbSJk.png?v=1730919445&width=1946")
                .build();
        Product producto2 = Product.builder()
                .name("Crema de arroz proteica")
                .shortDescription("Lorem ipsum dolor sit amet consectetur adipiscing elit dignissim netus ultricies.")
                .longDescription("Lorem ipsum dolor sit amet consectetur adipiscing elit dignissim netus ultricies, dui molestie condimentum mollis bibendum potenti iaculis conubia ut, nascetur facilisis nullam venenatis ridiculus consequat cum porta cras. Tortor vel fringilla tincidunt cum eros placerat neque pharetra sapien egestas.")
                .price(8.99)
                .stock(10)
                .category(objects2)
                .activo(true)
                .image("https://imgs.search.brave.com/QxyiZt2DWpX8N-go0NCSbAlUxDJKVNMk_9-6GcBQVSU/rs:fit:860:0:0:0/g:ce/aHR0cHM6Ly93d3cu/aHNuc3RvcmUuY29t/L21lZGlhL2NhdGFs/b2cvcHJvZHVjdC9j/YWNoZS81YTE1YzU5/ODhhZmI0OTI5YTI1/MDExNjlhMzc0NjBl/Yy9oL2kvaGlnaC1w/cm90ZWluLXJpY2Ut/Y3JlYW0tbmV1dHJh/bC01MDBnLWZyb250/LWhzbl8xXzEud2Vi/cA")
                .build();
        Product producto3 = Product.builder()
                .name("Proteina whey Isolate, Chocolate")
                .shortDescription("Lorem ipsum dolor sit amet consectetur adipiscing elit dignissim netus ultricies.")
                .longDescription("Lorem ipsum dolor sit amet consectetur adipiscing elit dignissim netus ultricies, dui molestie condimentum mollis bibendum potenti iaculis conubia ut, nascetur facilisis nullam venenatis ridiculus consequat cum porta cras. Tortor vel fringilla tincidunt cum eros placerat neque pharetra sapien egestas.")
                .price(34.99)
                .stock(0)
                .category(objects3)
                .activo(true)
                .image("https://imgs.search.brave.com/8Su7Icxn-hx0Zf_KmjqBkN79zTXlsdMfDZra0T99qBU/rs:fit:860:0:0:0/g:ce/aHR0cHM6Ly9ub2ds/dXRlbnRlYW0uZXMv/d3AtY29udGVudC91/cGxvYWRzLzIwMjUv/MDUvMTQ4MTcwMmVm/NWMwMDNkMjIwYzZk/NWQwNjExYTg4YzQu/anBn")
                .build();
        Product producto4 = Product.builder()
                .name("Energy bar flapjack (choco,berrys,almond)")
                .shortDescription("Lorem ipsum dolor sit amet consectetur adipiscing elit dignissim netus ultricies.")
                .longDescription("Lorem ipsum dolor sit amet consectetur adipiscing elit dignissim netus ultricies, dui molestie condimentum mollis bibendum potenti iaculis conubia ut, nascetur facilisis nullam venenatis ridiculus consequat cum porta cras. Tortor vel fringilla tincidunt cum eros placerat neque pharetra sapien egestas.")
                .price(7.99)
                .stock(20)
                .category(objects3)
                .activo(true)
                .image("https://www.hsnstore.com/media/catalog/product/cache/5a15c5988afb4929a2501169a37460ec/f/l/flapjack-chocolate-almond-50g-front-hsn_1_1_1.webp")
                .build();
        Product producto5 = Product.builder()
                .name("Isotonica en polvo (Limon)")
                .shortDescription("Lorem ipsum dolor sit amet consectetur adipiscing elit dignissim netus ultricies.")
                .longDescription("Lorem ipsum dolor sit amet consectetur adipiscing elit dignissim netus ultricies, dui molestie condimentum mollis bibendum potenti iaculis conubia ut, nascetur facilisis nullam venenatis ridiculus consequat cum porta cras. Tortor vel fringilla tincidunt cum eros placerat neque pharetra sapien egestas.")
                .price(29.99)
                .stock(99)
                .category(objects5)
                .activo(true)
                .image("https://imgs.search.brave.com/tsA2DIXjI-5Unu_ZaYGy3yEsf8hQU5U9YNOJhHp_KVU/rs:fit:860:0:0:0/g:ce/aHR0cHM6Ly9jb250/ZW50cy5tZWRpYWRl/Y2F0aGxvbi5jb20v/cDI2MzE4MTkvMWNy/MS9rJGQ0NDE2ZmU0/MzJmYWM5NjFjZTdi/NTkwODgwZGJhNTQ5/L3Byb2QuanBnP2Zv/cm1hdD1hdXRvJmY9/MTAyNHgw")
                .build();
        Product producto6 = Product.builder()
                .name("Camiseta gimnasio, nike©")
                .shortDescription("Lorem ipsum dolor sit amet consectetur adipiscing elit dignissim netus ultricies.")
                .longDescription("Lorem ipsum dolor sit amet consectetur adipiscing elit dignissim netus ultricies, dui molestie condimentum mollis bibendum potenti iaculis conubia ut, nascetur facilisis nullam venenatis ridiculus consequat cum porta cras. Tortor vel fringilla tincidunt cum eros placerat neque pharetra sapien egestas.")
                .price(24.99)
                .stock(50)
                .category(objects4)
                .activo(true)
                .image("https://imgs.search.brave.com/dOOXtflbe6vIZZVmAQByaCCuAuRW_e9wZo0YED0EPbM/rs:fit:860:0:0:0/g:ce/aHR0cHM6Ly9jb250/ZW50cy5tZWRpYWRl/Y2F0aGxvbi5jb20v/bTMzOTY5NjIvayQx/NzFjYTU4YjUxNjMx/MGMwYjE4YWRmODNi/YjAzYWQ0ZC9waWN0/dXJlLmpwZz9mb3Jt/YXQ9YXV0byZmPTMw/MDB4MA")
                .build();
        Product producto7 = Product.builder()
                .name("Pantalones cortos gimnasio, Puma©")
                .shortDescription("Lorem ipsum dolor sit amet consectetur adipiscing elit dignissim netus ultricies.")
                .longDescription("Lorem ipsum dolor sit amet consectetur adipiscing elit dignissim netus ultricies, dui molestie condimentum mollis bibendum potenti iaculis conubia ut, nascetur facilisis nullam venenatis ridiculus consequat cum porta cras. Tortor vel fringilla tincidunt cum eros placerat neque pharetra sapien egestas.")
                .price(22.99)
                .stock(50)
                .category(objects4)
                .activo(true)
                .image("https://imgs.search.brave.com/wbHmwexFEM4_u_k9maIMjFxeMhHca739EoRDNaC4D8k/rs:fit:860:0:0:0/g:ce/aHR0cHM6Ly9tZWRp/YS5hdG1vc2ZlcmFz/cG9ydC5lcy81NTM1/NzctaG9tZV9kZWZh/dWx0L3BhbnRhbG9u/ZXMtZGUtZml0bmVz/cy1wdW1hLW0tZmxl/eC1zdHJldGNoLXdv/dmVuLWJsYWNrLWhv/bWJyZS5qcGc")
                .build();
        Product producto8 = Product.builder()
                .name("Under Armour © Slipspeed, entrenamiento")
                .shortDescription("Lorem ipsum dolor sit amet consectetur adipiscing elit dignissim netus ultricies.")
                .longDescription("Lorem ipsum dolor sit amet consectetur adipiscing elit dignissim netus ultricies, dui molestie condimentum mollis bibendum potenti iaculis conubia ut, nascetur facilisis nullam venenatis ridiculus consequat cum porta cras. Tortor vel fringilla tincidunt cum eros placerat neque pharetra sapien egestas.")
                .price(162.95)
                .stock(89)
                .category(objects4)
                .activo(true)
                .image("https://imgs.search.brave.com/lh00FCYbM4Cjruqp_LKUgFBMIn2ZXpSmpAA9E-GZsfQ/rs:fit:860:0:0:0/g:ce/aHR0cHM6Ly93d3cu/dW5kZXJhcm1vdXIu/Y2wvY2RuL3Nob3Av/ZmlsZXMvMzAyNzcy/Ni0wMDFfTjExXzYu/anBnP3Y9MTc2Mjk3/Mzc1OCZ3aWR0aD0x/MjAw")
                .build();
        Product producto9 = Product.builder()
                .name("Camiseta futbol españa Mundial 2026, Adidas©")
                .shortDescription("Lorem ipsum dolor sit amet consectetur adipiscing elit dignissim netus ultricies.")
                .longDescription("Lorem ipsum dolor sit amet consectetur adipiscing elit dignissim netus ultricies, dui molestie condimentum mollis bibendum potenti iaculis conubia ut, nascetur facilisis nullam venenatis ridiculus consequat cum porta cras. Tortor vel fringilla tincidunt cum eros placerat neque pharetra sapien egestas.")
                .price(100.00)
                .stock(0)
                .category(objects4)
                .activo(true)
                .image("https://imgs.search.brave.com/UN5mv1jJce42NA_yOW0WrB5A016K6HF9BDBCxpcn2q4/rs:fit:860:0:0:0/g:ce/aHR0cHM6Ly93d3cu/Zm9ydW1zcG9ydC5j/b20vaW1hZ2VzL2Fk/aWRhcy1jYW1pc2V0/YS1kZS1mdXRib2wt/b2ZpY2lhbGVzLXBy/aW1lcmEtZXF1aXBh/Y2lvbi1lc3BhbmEt/MjYtb2ZpY2lhbC0w/NC0xMDAxMTExMjA4/LTUwMHg1MDAtZg")
                .build();
        productRepository.saveAll(List.of(producto,producto2,producto3,producto4,producto5,producto6,producto7,producto8,producto9));


        Review re = new Review();
        re.setTitle("Creatina Creapure, Sabor Fresa");
        re.setRating(5);
        re.setComment("Excelente producto");
        re.setUserVerified(true);
        re.setCreatedAt(java.time.LocalDateTime.now());
        re.setProduct(producto);
        reviewRepository.save(re);



        Review re2 = new Review();
        re2.setTitle("Crema de arroz proteica");
        re2.setRating(2);
        re2.setComment("Malo");
        re2.setUserVerified(false);
        re2.setProduct(producto2);
        re2.setCreatedAt(java.time.LocalDateTime.now());
        reviewRepository.save(re2);

        Review re3 = new Review();
        re3.setTitle("Proteina whey Isolate, Chocolate");
        re3.setRating(4);
        re3.setComment("Muy Buen producto");
        re3.setUserVerified(true);
        re3.setProduct(producto3);
        re3.setCreatedAt(java.time.LocalDateTime.now());
        reviewRepository.save(re3);

        Review re4 = new Review();
        re4.setTitle("Energy bar flapjack (choco,berrys,almond)");
        re4.setRating(1);
        re4.setComment("A penas se nota el sabor del almond");
        re4.setUserVerified(true);
        re4.setProduct(producto4);
        re4.setCreatedAt(java.time.LocalDateTime.now());
        reviewRepository.save(re4);

        Review re5 = new Review();
        re5.setTitle("Isotonica polvo de limón");
        re5.setRating(3);
        re5.setComment("No tiene más sabor pero le falta un poco más de dulzor");
        re5.setUserVerified(true);
        re5.setProduct(producto5);
        re5.setCreatedAt(java.time.LocalDateTime.now());
        reviewRepository.save(re5);

        Review re6 = new Review();
        re6.setTitle("Camiseta gimnasio,nike©");
        re6.setRating(5);
        re6.setComment("Buena calidad y muy comoda");
        re6.setUserVerified(true);
        re6.setProduct(producto6);
        re6.setCreatedAt(java.time.LocalDateTime.now());
        reviewRepository.save(re6);

        Review re7 = new Review();
        re7.setTitle("Pantalones cortos gimnasio, Puma©");
        re7.setRating(4);
        re7.setComment("Calidad buena y comodos, no le doy un 5 porque pienso que habrá alguno que me guste más");
        re7.setUserVerified(true);
        re7.setProduct(producto7);
        re7.setCreatedAt(java.time.LocalDateTime.now());
        reviewRepository.save(re7);

        Review re8 = new Review();
        re8.setTitle("Under Armour © Slipspeed, entrenamiento");
        re8.setRating(3);
        re8.setComment("Zapatillas decente, ideales para hacer cardio");
        re8.setUserVerified(true);
        re8.setProduct(producto8);
        re8.setCreatedAt(java.time.LocalDateTime.now());
        reviewRepository.save(re8);

        Review re9 = new Review();
        re9.setTitle("Camiseta futbol españa Mundial 2026, Adidas©");
        re9.setRating(5);
        re9.setComment("La mejor camiseta! , Arriba España papaa!!");
        re9.setUserVerified(true);
        re9.setProduct(producto9);
        re9.setCreatedAt(java.time.LocalDateTime.now());
        reviewRepository.save(re9);


//        Purchase p1 = new Purchase();
//        p1.setTotalPrice(129.98);
//        p1.setDiscountCode("David15%");
//        p1.setPurchaseDate(LocalDate.from(java.time.LocalDateTime.now()));
//        purchaseRepository.save(p1);
//
//        Purchase p2 = new Purchase();
//        p2.setTotalPrice(64.99);
//        p2.setDiscountCode("Dogo10%");
//        p2.setPurchaseDate(LocalDate.from(java.time.LocalDateTime.now()));
//        purchaseRepository.save(p2);
//
//        Purchase p3 = new Purchase();
//        p3.setTotalPrice(129.98);
//        p3.setDiscountCode("Adri5%");
//        p3.setPurchaseDate(LocalDate.from(java.time.LocalDateTime.now()));
//        purchaseRepository.save(p3);
//
//
//        p1.setTotalPrice(119.98);
//        p1.setStatus(PurchaseStatus.FINISHED);
//        purchaseRepository.save(p1);
//
//        p2.setTotalPrice(59.99);
//        p2.setStatus(PurchaseStatus.CANCEL);
//        purchaseRepository.save(p2);
//
//        p3.setTotalPrice(119.98);
//        p3.setStatus(PurchaseStatus.PENDING);
//        purchaseRepository.save(p3);
//
//        // crear cuatro reviews de un restaurante usando Builder de lombok
//
//
//        PurchaseLine pL1 = new PurchaseLine();
//        pL1.setPurchase(p1);
//        pL1.setProduct(producto);
//        pL1.setQuantity(2);
//        PurchaseLine pL2 = new PurchaseLine();
//        pL2.setPurchase(p2);
//        pL2.setProduct(producto);
//        pL2.setQuantity(1);
//        PurchaseLine pL3 = new PurchaseLine();
//        pL3.setPurchase(p2);
//        pL3.setProduct(producto);
//        pL3.setQuantity(2);
//
//        List<PurchaseLine> purchaseLines = PurchaseLineRepository.saveAll(List.of(pL1,pL2,pL3));
//
//        //Calcular precio total en java
//        double totalPrice = 0;
//        for (PurchaseLine lineaPedido : purchaseLines) {
//            // sacar el precio del plato
//            if (lineaPedido.getProduct() != null) {
//                double precioLinea = lineaPedido.getProduct().getPrice() * lineaPedido.getQuantity();
//                totalPrice += precioLinea;
//            }
//        }
//        // guardar el totalPrice en base de datos:
//        p1.setTotalPrice(totalPrice);
//        p1.setStatus(PurchaseStatus.FINISHED); // marcamos el pedido como completado
//        purchaseRepository.save(p1); // actualizar el totalPrice del pedido para saber cuanto dinero hemos ganado
//
//
//
//    }


}
}
