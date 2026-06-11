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
        c1.setDescription("La creatina, el aliado indiscutible de los deportistas. Recuperación muscular, aumento de fuerza y mejora en el rendimiento. Todo en uno.");
        c1.setImageFile("/images/categories/CreatinaHardCore.png");
        c1.setActivo(true);
        categoryRepository.save(c1);

        Category objects2 = new Category();
        objects2.setName("Crema de arroz");
        objects2.setDescription("Carbohidrato de absorción rápida, 50g y 200ml agua/leche, ideal para antes o después de entrenar. Disponible en varios sabores, vegana y sin lactosa.");
        objects2.setImageFile("/images/categories/CremaDeArrozHardCore.jpg");
        objects2.setActivo(true);
        categoryRepository.save(objects2);

        Category objects3 = new Category();
        objects3.setName("Proteínas");
        objects3.setDescription("El suplemento deportivo más famoso, ideal para aumentar tu ingesta de proteínas, mejorar tu recuperación y ayudarte a alcanzar tus objetivos de entrenamiento.");
        objects3.setImageFile("/images/categories/ProteinaHardCore.jpg");
        objects3.setActivo(true);
        categoryRepository.save(objects3);

        Category objects4 = new Category();
        objects4.setName("Ropa y accesorios");
        objects4.setDescription("Material y outfits imprescindibles para darlo todo en tu deporte favorito");
        objects4.setImageFile("/images/categories/RopaYAccesorios3.jpg");
        objects4.setActivo(true);
        categoryRepository.save(objects4);

        Category objects5 = new Category();
        objects5.setName("Suplementos");
        objects5.setDescription("Suplementos deportivos que llevarán tu entrenamiento al siguiente nivel");
        objects5.setImageFile("/images/categories/SupleHardCore.jpg");
        objects5.setActivo(true);
        categoryRepository.save(objects5);


        Product producto = Product.builder()
                .name("Creatina Creapure, Sabor Fresa")
                .shortDescription("Creatina Creapure® sabor fresa de alta pureza para mejorar fuerza, rendimiento y recuperación muscular.")
                .longDescription("La Creatina Creapure® sabor fresa está formulada con materia prima de máxima pureza para ayudarte a aumentar la fuerza, mejorar el rendimiento físico y potenciar la recuperación muscular. Su excelente disolución y agradable sabor la convierten en una opción ideal para deportistas que buscan mejorar su rendimiento en entrenamientos intensos y sesiones de alta exigencia. Perfecta para combinar con cualquier rutina deportiva y objetivos de ganancia muscular.")
                .price(44.99)
                .stock(99)
                .category(c1)
                .activo(true)
                .image("/images/products/sophbSJk.webp")
                .build();
        Product producto2 = Product.builder()
                .name("Crema de arroz proteica")
                .shortDescription("Crema de arroz rica en proteínas, ideal para desayunos y comidas pre o post entrenamiento.")
                .longDescription("Nuestra crema de arroz proteica combina carbohidratos de fácil digestión con un alto aporte proteico para ofrecerte energía sostenida y recuperación muscular. Perfecta para deportistas y personas activas, su textura cremosa y preparación rápida la convierten en una opción ideal para desayunos fitness, meriendas saludables o comidas pre y post entreno. Fácil de mezclar con frutas, frutos secos o suplementos.")
                .price(8.99)
                .stock(10)
                .category(objects2)
                .activo(true)
                .image("/images/products/high-protein-rice-cream-neutral-500g-front-hsn_1_1-removebg-preview.webp")
                .build();
        Product producto3 = Product.builder()
                .name("Proteina whey Isolate, Chocolate")
                .shortDescription("Proteína Whey Isolate sabor chocolate con alta concentración proteica y rápida absorción.")
                .longDescription("La Whey Isolate sabor chocolate aporta proteína de alta calidad con una absorción rápida para favorecer el desarrollo y mantenimiento muscular. Su fórmula baja en grasas y azúcares es ideal para deportistas que buscan maximizar la recuperación después del entrenamiento. Disfruta de un delicioso sabor chocolate y una textura suave en cada batido.")
                .price(34.99)
                .stock(0)
                .category(objects3)
                .activo(true)
                .image("/images/products/1481702ef5c003d220c6d5d0611a88c4-removebg-preview.webp")
                .build();
        Product producto4 = Product.builder()
                .name("Energy bar flapjack (choco,berrys,almond)")
                .shortDescription("Barrita energética tipo flapjack con avena y deliciosos sabores para obtener energía inmediata.")
                .longDescription("Las Energy Bar Flapjack son el snack perfecto para mantener la energía durante el día o antes de entrenar. Elaboradas con avena de calidad y disponibles en sabores chocolate, frutos rojos y almendra, aportan carbohidratos de liberación sostenida para mejorar el rendimiento físico y combatir la fatiga. Ideales para deportistas, excursiones o como snack saludable.")
                .price(7.99)
                .stock(20)
                .category(objects3)
                .activo(true)
                .image("/images/products/flapjack-chocolate-almond-50g-front-hsn_1_1_1-removebg-preview.png")
                .build();
        Product producto5 = Product.builder()
                .name("Isotonica en polvo (Limon)")
                .shortDescription("Bebida isotónica en polvo sabor limón para reponer electrolitos e hidratar durante el ejercicio.")
                .longDescription("Nuestra isotónica en polvo sabor limón ayuda a mantener una hidratación óptima durante entrenamientos intensos y actividades deportivas. Su fórmula con electrolitos contribuye a reponer minerales perdidos por el sudor y mantener el rendimiento físico. Fácil de preparar y con un refrescante sabor cítrico, es ideal para running, ciclismo, gimnasio y deportes de resistencia.")
                .price(29.99)
                .stock(99)
                .category(objects5)
                .activo(true)
                .image("/images/products/prod-removebg-preview.png")
                .build();
        Product producto6 = Product.builder()
                .name("Camiseta gimnasio, nike©")
                .shortDescription("Camiseta deportiva Nike® ligera y transpirable para entrenamientos y uso diario.")
                .longDescription("La camiseta de gimnasio Nike® combina comodidad, estilo y transpirabilidad para acompañarte en cada entrenamiento. Fabricada con materiales ligeros y de secado rápido, ayuda a mantener la frescura incluso en sesiones intensas. Su diseño moderno y ajuste cómodo la convierten en una prenda ideal tanto para el gimnasio como para uso casual deportivo.")
                .price(24.99)
                .stock(50)
                .category(objects4)
                .activo(true)
                .image("/images/products/camisa_nike-removebg-preview.png")
                .medidas(Medidas.CLOTHING)
                .build();
        Product producto7 = Product.builder()
                .name("Pantalones cortos gimnasio, Puma©")
                .shortDescription("Pantalones cortos Puma® diseñados para máxima comodidad y libertad de movimiento.")
                .longDescription("Entrena con total comodidad gracias a los pantalones cortos Puma®. Su tejido ligero y flexible permite una movilidad óptima durante ejercicios de fuerza, cardio o entrenamiento funcional. Incorporan un diseño moderno y deportivo ideal para quienes buscan rendimiento y estilo dentro y fuera del gimnasio.")
                .price(22.99)
                .stock(50)
                .category(objects4)
                .activo(true)
                .image("/images/products/pantalones-de-fitness-puma-m-flex-stretch-woven-black-hombre-removebg-preview.png")
                .medidas(Medidas.CLOTHING)
                .build();
        Product producto8 = Product.builder()
                .name("Under Armour © Slipspeed, entrenamiento")
                .shortDescription("Zapatillas Under Armour® de entrenamiento con gran amortiguación, estabilidad y confort.")
                .longDescription("Las Under Armour® Slipspeed están diseñadas para ofrecer rendimiento, estabilidad y comodidad en entrenamientos de alta intensidad. Su estructura ligera y transpirable proporciona un ajuste seguro y una excelente amortiguación para proteger cada pisada. Perfectas para gimnasio, cardio, HIIT o uso deportivo diario.")
                .price(162.95)
                .stock(89)
                .category(objects4)
                .activo(true)
                .image("/images/products/3027726-001_N11_6-removebg-preview.png")
                .medidas(Medidas.SHOES)
                .build();
        Product producto9 = Product.builder()
                .name("Camiseta futbol españa Mundial 2026, Adidas©")
                .shortDescription("Camiseta oficial Adidas® selección España Mundial 2026 con diseño moderno y deportivo.")
                .longDescription("Luce los colores de la selección española con la camiseta oficial Adidas® Mundial 2026. Diseñada con materiales transpirables y tecnología de secado rápido, ofrece comodidad tanto en el campo como en el día a día. Su diseño moderno combina estilo, pasión por el fútbol y máxima calidad deportiva.")
                .price(99.99)
                .stock(0)
                .category(objects4)
                .activo(true)
                .image("/images/products/Camiseta_primera_equipacion_Espana_26_Oficial_Rojo_JN4366_HM5.webp")
                .medidas(Medidas.CLOTHING)
                .build();
        Product producto10 = Product.builder()
                .name("Creatina Creapure Vegana©")
                .shortDescription("Creatina vegana Creapure® de máxima calidad para potenciar fuerza y rendimiento deportivo.")
                .longDescription("La Creatina Creapure® Vegana ofrece una fórmula pura y de alta calidad pensada para mejorar el rendimiento físico, aumentar la fuerza y favorecer la recuperación muscular. Ideal para deportistas veganos y personas que buscan suplementos de máxima pureza y eficacia. Fácil de mezclar y perfecta para cualquier rutina fitness.")
                .price(49.99)
                .stock(0)
                .category(c1)
                .activo(true)
                .image("/images/categories/creatina2.png")
                .medidas(Medidas.CLOTHING)
                .build();
        Product producto11 = Product.builder()
                .name("Quema Grasas 120Caps")
                .shortDescription("Complemento alimenticio diseñado para apoyar la definición y el metabolismo energético.")
                .longDescription("El Quema Grasas 120 Caps combina ingredientes seleccionados para ayudarte en tus objetivos de definición y control de peso. Su fórmula está diseñada para complementar una alimentación equilibrada y una rutina de ejercicio regular, favoreciendo el metabolismo energético y aportando un extra de motivación en etapas de definición muscular.")
                .price(19.99)
                .stock(2)
                .category(objects5)
                .activo(true)
                .image("/images/categories/QuemaGrasas.png")
                .medidas(Medidas.CLOTHING)
                .build();
        Product producto12 = Product.builder()
                .name("Glutanima")
                .shortDescription("Glutamina de alta calidad para favorecer la recuperación muscular y reducir el desgaste físico.")
                .longDescription("La glutamina es un aminoácido esencial en procesos de recuperación y mantenimiento muscular. Este suplemento ayuda a disminuir el desgaste tras entrenamientos intensos, favorece la recuperación física y contribuye al correcto funcionamiento del sistema inmunológico. Ideal para deportistas de fuerza, resistencia y alto rendimiento.")
                .price(24.49)
                .stock(2)
                .category(objects5)
                .activo(true)
                .image("/images/categories/Glutamina.png")
                .medidas(Medidas.CLOTHING)
                .build();
        Product producto13 = Product.builder()
                .name("Crema de Arroz 1000g , Vegana y sin Lactosa - Neutro")
                .shortDescription("Crema de arroz vegana y sin lactosa, perfecta para aportar energía de fácil digestión.")
                .longDescription("La crema de arroz 1000g sabor neutro es una excelente fuente de carbohidratos de rápida digestión, ideal para deportistas y personas activas. Su textura suave y cremosa permite prepararla fácilmente con agua," +
                                 "leche o bebida vegetal. Perfecta para desayunos, comidas pre entreno o recuperación post ejercicio. Además, su fórmula vegana y sin lactosa la hace apta para múltiples tipos de alimentación.")
                .price(15.49)
                .stock(5)
                .category(objects2)
                .activo(true)
                .image("/images/categories/CremaArroz2.png")
                .medidas(Medidas.CLOTHING)
                .build();
        Product producto14 = Product.builder()
                .name("Crema De Arroz (1 Kg) - Sin gluten, sin azúcar - Chocolate Avellana")
                .shortDescription("Crema de arroz sabor chocolate avellana sin gluten y sin azúcar añadida.")
                .longDescription(" Disfruta de una fuente de energía deliciosa y fácil de digerir con esta crema de arroz sabor chocolate avellana. Elaborada sin gluten y sin azúcar añadido, es perfecta para deportistas que buscan carbohidratos de calidad para mejorar el rendimiento y recuperar glucógeno muscular." +
                                 " Su excelente sabor y textura cremosa la convierten en una opción ideal para desayunos, meriendas y comidas pre o post entrenamiento.")
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
