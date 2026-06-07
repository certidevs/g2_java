package com.demo;

import com.demo.model.*;
import com.demo.model.enums.Medidas;
import com.demo.model.enums.PurchaseStatus;
import com.demo.model.enums.Role;
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
        PurchaseLineRepository purchaseLineRepository = context.getBean(PurchaseLineRepository.class);
        UserRepository userRepository = context.getBean(UserRepository.class);

        User us = new User();
        us.setEmail("Andrea@gmail.com");
        us.setUsername("Andrea");
        us.setPassword("1234");
        us.setRole(Role.ROLE_USER);
        userRepository.save(us);

        User us2 = new User();
        us2.setEmail("Ivan@gmail.com");
        us2.setUsername("Ivan");
        us2.setPassword("1234");
        us2.setRole(Role.ROLE_ADMIN);
        userRepository.save(us2);

        User us3 = new User();
        us3.setEmail("Gema@gmail.com");
        us3.setUsername("Gema");
        us3.setPassword("1234");
        us3.setRole(Role.ROLE_USER);
        userRepository.save(us3);

        User us4 = new User();
        us4.setEmail("Joaquin@gmail.com");
        us4.setUsername("Joaquin");
        us4.setPassword("1234");
        us4.setRole(Role.ROLE_ADMIN);
        userRepository.save(us4);


        Category c1 = new Category();
        c1.setName("Creatina");
        c1.setDescription("La creatina es un aminoácido que se sintetiza en el hígado y se almacena en los músculos. Cuando se combina con fosfato, es una fuente de energía fácilmente disponible en el organismo. Aquí puedes encontrar todos los tipos.");
        c1.setImageFile("/images/categories/creatina.png");
        c1.setActivo(true);
        categoryRepository.save(c1);

        Category objects2 = new Category();
        objects2.setName("Crema de arroz");
        objects2.setDescription("La crema de arroz  es un carbohidrato complejo de digestión rápida obtenido a partir de la harina de arroz. Lo que la convierte en una excelente opicón para proporcionar energía antes , durante o después del ejercicio.");
        objects2.setImageFile("/images/categories/CremaArroz.png");
        objects2.setActivo(true);
        categoryRepository.save(objects2);

        Category objects3 = new Category();
        objects3.setName("Proteínas");
        objects3.setDescription("La proteína en polvo es un tipo de suplemento que aporta de forma rápida y sencilla proteínas de alta calidad a nuestro organismo. Aquí puedes encontrar todos los tipos.");
        objects3.setImageFile("/images/categories/proteinas.png");
        objects3.setActivo(true);
        categoryRepository.save(objects3);

        Category objects4 = new Category();
        objects4.setName("Ropa y accesorios");
        objects4.setDescription("Ropa y accesorios deportivos");
        objects4.setImageFile("/images/categories/RopaYAccesorios.png");
        objects4.setActivo(true);
        categoryRepository.save(objects4);

        Category objects5 = new Category();
        objects5.setName("Suplementos");
        objects5.setDescription("Ayudas para rendir a tu 100%");
        objects5.setImageFile("/images/categories/vitaminas.png");
        objects5.setActivo(true);
        categoryRepository.save(objects5);


        Product producto = Product.builder()
                .name("Creatina Creapure, Sabor Fresa")
                .shortDescription("Lorem ipsum dolor sit amet consectetur adipiscing elit dignissim netus ultricies.")
                .longDescription("Lorem ipsum dolor sit amet consectetur adipiscing elit dignissim netus ultricies, dui molestie condimentum mollis bibendum potenti iaculis conubia ut, nascetur facilisis nullam venenatis ridiculus consequat cum porta cras. Tortor vel fringilla tincidunt cum eros placerat neque pharetra sapien egestas.")
                .price(44.99)
                .stock(99)
                .category(c1)
                .activo(true)
                .image("/images/products/sophbSJk.webp")
                .build();
        Product producto2 = Product.builder()
                .name("Crema de arroz proteica")
                .shortDescription("Lorem ipsum dolor sit amet consectetur adipiscing elit dignissim netus ultricies.")
                .longDescription("Lorem ipsum dolor sit amet consectetur adipiscing elit dignissim netus ultricies, dui molestie condimentum mollis bibendum potenti iaculis conubia ut, nascetur facilisis nullam venenatis ridiculus consequat cum porta cras. Tortor vel fringilla tincidunt cum eros placerat neque pharetra sapien egestas.")
                .price(8.99)
                .stock(10)
                .category(objects2)
                .activo(true)
                .image("/images/products/high-protein-rice-cream-neutral-500g-front-hsn_1_1-removebg-preview.webp")
                .build();
        Product producto3 = Product.builder()
                .name("Proteina whey Isolate, Chocolate")
                .shortDescription("Lorem ipsum dolor sit amet consectetur adipiscing elit dignissim netus ultricies.")
                .longDescription("Lorem ipsum dolor sit amet consectetur adipiscing elit dignissim netus ultricies, dui molestie condimentum mollis bibendum potenti iaculis conubia ut, nascetur facilisis nullam venenatis ridiculus consequat cum porta cras. Tortor vel fringilla tincidunt cum eros placerat neque pharetra sapien egestas.")
                .price(34.99)
                .stock(0)
                .category(objects3)
                .activo(true)
                .image("/images/products/1481702ef5c003d220c6d5d0611a88c4-removebg-preview.webp")
                .build();
        Product producto4 = Product.builder()
                .name("Energy bar flapjack (choco,berrys,almond)")
                .shortDescription("Lorem ipsum dolor sit amet consectetur adipiscing elit dignissim netus ultricies.")
                .longDescription("Lorem ipsum dolor sit amet consectetur adipiscing elit dignissim netus ultricies, dui molestie condimentum mollis bibendum potenti iaculis conubia ut, nascetur facilisis nullam venenatis ridiculus consequat cum porta cras. Tortor vel fringilla tincidunt cum eros placerat neque pharetra sapien egestas.")
                .price(7.99)
                .stock(20)
                .category(objects3)
                .activo(true)
                .image("/images/products/flapjack-chocolate-almond-50g-front-hsn_1_1_1-removebg-preview.png")
                .build();
        Product producto5 = Product.builder()
                .name("Isotonica en polvo (Limon)")
                .shortDescription("Lorem ipsum dolor sit amet consectetur adipiscing elit dignissim netus ultricies.")
                .longDescription("Lorem ipsum dolor sit amet consectetur adipiscing elit dignissim netus ultricies, dui molestie condimentum mollis bibendum potenti iaculis conubia ut, nascetur facilisis nullam venenatis ridiculus consequat cum porta cras. Tortor vel fringilla tincidunt cum eros placerat neque pharetra sapien egestas.")
                .price(29.99)
                .stock(99)
                .category(objects5)
                .activo(true)
                .image("/images/products/prod-removebg-preview.png")
                .build();
        Product producto6 = Product.builder()
                .name("Camiseta gimnasio, nike©")
                .shortDescription("Lorem ipsum dolor sit amet consectetur adipiscing elit dignissim netus ultricies.")
                .longDescription("Lorem ipsum dolor sit amet consectetur adipiscing elit dignissim netus ultricies, dui molestie condimentum mollis bibendum potenti iaculis conubia ut, nascetur facilisis nullam venenatis ridiculus consequat cum porta cras. Tortor vel fringilla tincidunt cum eros placerat neque pharetra sapien egestas.")
                .price(24.99)
                .stock(50)
                .category(objects4)
                .activo(true)
                .image("/images/products/camisa_nike-removebg-preview.png")
                .medidas(Medidas.CLOTHING)
                .build();
        Product producto7 = Product.builder()
                .name("Pantalones cortos gimnasio, Puma©")
                .shortDescription("Lorem ipsum dolor sit amet consectetur adipiscing elit dignissim netus ultricies.")
                .longDescription("Lorem ipsum dolor sit amet consectetur adipiscing elit dignissim netus ultricies, dui molestie condimentum mollis bibendum potenti iaculis conubia ut, nascetur facilisis nullam venenatis ridiculus consequat cum porta cras. Tortor vel fringilla tincidunt cum eros placerat neque pharetra sapien egestas.")
                .price(22.99)
                .stock(50)
                .category(objects4)
                .activo(true)
                .image("/images/products/pantalones-de-fitness-puma-m-flex-stretch-woven-black-hombre-removebg-preview.png")
                .medidas(Medidas.CLOTHING)
                .build();
        Product producto8 = Product.builder()
                .name("Under Armour © Slipspeed, entrenamiento")
                .shortDescription("Lorem ipsum dolor sit amet consectetur adipiscing elit dignissim netus ultricies.")
                .longDescription("Lorem ipsum dolor sit amet consectetur adipiscing elit dignissim netus ultricies, dui molestie condimentum mollis bibendum potenti iaculis conubia ut, nascetur facilisis nullam venenatis ridiculus consequat cum porta cras. Tortor vel fringilla tincidunt cum eros placerat neque pharetra sapien egestas.")
                .price(162.95)
                .stock(89)
                .category(objects4)
                .activo(true)
                .image("/images/products/3027726-001_N11_6-removebg-preview.png")
                .medidas(Medidas.SHOES)
                .build();
        Product producto9 = Product.builder()
                .name("Camiseta futbol españa Mundial 2026, Adidas©")
                .shortDescription("Lorem ipsum dolor sit amet consectetur adipiscing elit dignissim netus ultricies.")
                .longDescription("Lorem ipsum dolor sit amet consectetur adipiscing elit dignissim netus ultricies, dui molestie condimentum mollis bibendum potenti iaculis conubia ut, nascetur facilisis nullam venenatis ridiculus consequat cum porta cras. Tortor vel fringilla tincidunt cum eros placerat neque pharetra sapien egestas.")
                .price(99.99)
                .stock(0)
                .category(objects4)
                .activo(true)
                .image("/images/products/Camiseta_primera_equipacion_Espana_26_Oficial_Rojo_JN4366_HM5.webp")
                .medidas(Medidas.CLOTHING)
                .build();
        Product producto10 = Product.builder()
                .name("Creatina Creapure Vegana©")
                .shortDescription("Te ayuda a rendir mejor en los entrenos")
                .longDescription("Creatina Vegana con certificado 'Creapure' para asegurar su máxima calidad")
                .price(49.99)
                .stock(0)
                .category(c1)
                .activo(true)
                .image("/images/categories/creatina2.png")
                .medidas(Medidas.CLOTHING)
                .build();
        Product producto11 = Product.builder()
                .name("Quema Grasas 120Caps")
                .shortDescription("Te ayuda a definir")
                .longDescription("QuemaGrasas con ingredientes naturales para ayudarte a definir el cuerpo")
                .price(19.99)
                .stock(2)
                .category(objects5)
                .activo(true)
                .image("/images/categories/QuemaGrasas.png")
                .medidas(Medidas.CLOTHING)
                .build();
        Product producto12 = Product.builder()
                .name("Glutanima")
                .shortDescription("Te ayuda a recuperarte de los entrenos")
                .longDescription("La glutamina es un aminoácido clave para la recuperación muscular después de un entreno, la salud intestinal y el fortalecimiento del sistema inmunológico.")
                .price(24.49)
                .stock(2)
                .category(objects5)
                .activo(true)
                .image("/images/categories/Glutamina.png")
                .medidas(Medidas.CLOTHING)
                .build();
        Product producto13 = Product.builder()
                .name("Crema de Arroz 1000g , Vegana y sin Lactosa - Neutro")
                .shortDescription("Crema de Arroz 1000g , Sabor Neutro")
                .longDescription("La textura suave de esta crema de arroz es lo que la distingue. Se disuelve rapidamente en agua, leche o bebida vegetal, facilitando su preparacion en cualquier momento del dia. Puede combinarse con frutas, frutos secos, miel o cualquier complemento de tu eleccion, haciendo de cada porcion una experiencia personalizada. " +
                                 "No importa si la prefieres como parte de un desayuno energetico o como una opcion ligera antes de dormir, " +"esta crema se adapta a tus necesidades.")
                .price(15.49)
                .stock(5)
                .category(objects2)
                .activo(true)
                .image("/images/categories/CremaArroz2.png")
                .medidas(Medidas.CLOTHING)
                .build();
        Product producto14 = Product.builder()
                .name("Crema De Arroz (1 Kg) - Sin gluten, sin azúcar - Chocolate Avellana")
                .shortDescription("Crema De Arroz (1KG) , sin azucar - Sabor Avellana")
                .longDescription(" Como fuente de hidratos de carbono, ayuda a reponer las reservas de glucógeno en los músculos y a evitar la fatiga. Una fórmula sencilla sin ingredientes de origen animal, por lo que es ideal para veganos y vegetarianos." +
                                 " La ausencia de gluten en la harina de arroz significa que puede ser utilizado con seguridad por personas con alergias o intolerancias.Disponible en los sabores Chocolate, Chocolate Avellana y Galletas.")
                .price(17.45)
                .stock(3)
                .category(objects2)
                .activo(true)
                .image("/images/categories/CremaArroz3.png")
                .medidas(Medidas.CLOTHING)
                .build();
        productRepository.saveAll(List.of(producto, producto2, producto3, producto4, producto5, producto6, producto7, producto8, producto9, producto10,producto11 , producto12, producto13,producto14));


        Review re = new Review();
        re.setTitle("Creatina Creapure, Sabor Fresa");
        re.setRating(5);
        re.setComment("Excelente producto");
        re.setUserVerified(true);
        re.setCreatedAt(java.time.LocalDateTime.now());
        re.setProduct(producto);
        re.setUser(us4);
        reviewRepository.save(re);


        Review re2 = new Review();
        re2.setTitle("Crema de arroz proteica");
        re2.setRating(2);
        re2.setComment("Malo");
        re2.setUserVerified(false);
        re2.setProduct(producto2);
        re2.setCreatedAt(java.time.LocalDateTime.now());
        re.setUser(us2);
        reviewRepository.save(re2);

        Review re3 = new Review();
        re3.setTitle("Proteina whey Isolate, Chocolate");
        re3.setRating(4);
        re3.setComment("Muy Buen producto");
        re3.setUserVerified(true);
        re3.setProduct(producto3);
        re3.setCreatedAt(java.time.LocalDateTime.now());
        re.setUser(us4);
        reviewRepository.save(re3);

        Review re4 = new Review();
        re4.setTitle("Energy bar flapjack (choco,berrys,almond)");
        re4.setRating(1);
        re4.setComment("A penas se nota el sabor del almond");
        re4.setUserVerified(true);
        re4.setProduct(producto4);
        re4.setCreatedAt(java.time.LocalDateTime.now());
        re.setUser(us2);
        reviewRepository.save(re4);

        Review re5 = new Review();
        re5.setTitle("Isotonica polvo de limón");
        re5.setRating(3);
        re5.setComment("No tiene más sabor pero le falta un poco más de dulzor");
        re5.setUserVerified(true);
        re5.setProduct(producto5);
        re5.setCreatedAt(java.time.LocalDateTime.now());
        re.setUser(us);
        reviewRepository.save(re5);

        Review re6 = new Review();
        re6.setTitle("Camiseta gimnasio,nike©");
        re6.setRating(5);
        re6.setComment("Buena calidad y muy comoda");
        re6.setUserVerified(true);
        re6.setProduct(producto6);
        re6.setCreatedAt(java.time.LocalDateTime.now());
        re.setUser(us3);
        reviewRepository.save(re6);

        Review re7 = new Review();
        re7.setTitle("Pantalones cortos gimnasio, Puma©");
        re7.setRating(4);
        re7.setComment("Calidad buena y comodos, no le doy un 5 porque pienso que habrá alguno que me guste más");
        re7.setUserVerified(true);
        re7.setProduct(producto7);
        re7.setCreatedAt(java.time.LocalDateTime.now());
        re.setUser(us);
        reviewRepository.save(re7);

        Review re8 = new Review();
        re8.setTitle("Under Armour © Slipspeed, entrenamiento");
        re8.setRating(3);
        re8.setComment("Zapatillas decente, ideales para hacer cardio");
        re8.setUserVerified(true);
        re8.setProduct(producto8);
        re8.setCreatedAt(java.time.LocalDateTime.now());
        re.setUser(us3);
        reviewRepository.save(re8);

        Review re9 = new Review();
        re9.setTitle("Camiseta futbol españa Mundial 2026, Adidas©");
        re9.setRating(5);
        re9.setComment("La mejor camiseta! , Arriba España papaa!!");
        re9.setUserVerified(true);
        re9.setProduct(producto9);
        re9.setCreatedAt(java.time.LocalDateTime.now());
        re.setUser(us2);
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
