# Backlog de Ideas — Tienda (Grupo 2)

Documento orientativo y didáctico. El proyecto `g2_java` (la tienda "Five Chill Guys Gym") se entrega **tal cual está**: las ideas recogidas aquí **no se van a implementar** como parte de la entrega. El objetivo es ilustrar cómo se razona sobre un producto software despues de la primera versión: detectar deuda técnica real, imaginar mejoras de valor y entender el ciclo de vida del software (cómo una aplicación sigue evolucionando tras la entrega inicial mediante features, fixes, pruebas y despliegue continuo).

Todo lo que aparece a continuación está fundamentado en el código real del repositorio. Se citan clases, métodos, ficheros y plantillas concretas para que cualquiera pueda localizar el punto exacto al que se refiere cada idea.

---

## 1. Resumen del proyecto

- **Dominio:** tienda online de suplementación deportiva, ropa y equipamiento fitness ("Five Chill Guys Gym", ver `templates/welcome.html` y `templates/layout/navbar.html`). El `README.md` la describe como plataforma de comercio electrónico.

- **Stack (versiones reales del `pom.xml`):**
  - Java 25 (`<java.version>25</java.version>`).
  - Spring Boot 4.0.5 (parent `spring-boot-starter-parent` 4.0.5).
  - Starters: `spring-boot-starter-webmvc`, `spring-boot-starter-data-jpa`, `spring-boot-starter-thymeleaf`, `spring-boot-starter-security`.
  - Vistas: Thymeleaf + `thymeleaf-extras-springsecurity6`, Bootstrap 5.3.3 y Font Awesome 7.2.0 vía WebJars.
  - Base de datos: H2 en memoria (`com.h2database:h2`, scope runtime) con `spring-boot-h2console`.
  - Lombok como dependencia opcional con su `annotationProcessorPath` configurado.
  - Testing: `spring-boot-starter-webmvc-test`, `-data-jpa-test`, `-thymeleaf-test`, `spring-boot-starter-security-test`.
  - Configuración (`application.properties`): `spring.datasource.url=jdbc:h2:mem:g2_java_db`, `spring.jpa.hibernate.ddl-auto=create-drop`, `spring.jpa.show-sql=true`, `spring.h2.console.enabled=true`, `spring.servlet.multipart.max-file-size=10MB`.
  - Extra de tooling: hay un `compose.sonar.yaml` (análisis con SonarQube local), pero el proyecto **no** trae Postgres, Docker de la app ni workflows de CI.

- **Entidades principales y relaciones** (paquete `com.demo.model`):
  - `User` (`@Table("users")`) implementa directamente `UserDetails`. Campos: `username` y `email` únicos y no nulos, `password`, `imageUrl`, `role` (enum `Role`: `ROLE_USER`, `ROLE_ADMIN`) y `online` (Boolean). `isEnabled()` devuelve el valor de `online`.
  - `Product` (`@Table("product")`): `name`, `shortDescription`, `longDescription` (length 1000), `price` (Double), `stock` (Integer), `image` (`@Lob`), `medidas` (enum `Medidas`: `SHOES`, `CLOTHING`), `activo` (Boolean) y `@ManyToOne Category`.
  - `Category` (`@Table("categories")`): `name`, `description`, `activo`, `imageFile`. Su `@Id` usa `GenerationType.AUTO` (distinto del resto, que usan `IDENTITY`).
  - `Purchase`: `totalPrice`, `purchaseDate`, `status` (enum `PurchaseStatus`: `PENDING`, `IN_PROGRESS`, `FINISHED`, `CANCEL`), datos de tarjeta (`cardOwner`, `cardNumber`, `cardExpirationDate`) y `@ManyToOne User`.
  - `PurchaseLine`: `quantity`, `unitPrice`, `talla`, `discountCode`, `@ManyToOne Purchase`, `@ManyToOne Product`.
  - `Review`: `rating`, `comment`, `title`, `userVerified`, `active` (con valor por defecto `true`), `createdAt`, `@ManyToOne Product`, `@ManyToOne User`.
  - `Like` (`@Table("favorites")`): `createdAt`, `@ManyToOne User`, `@ManyToOne Product`.
  - Nota relevante: **todas las asociaciones son unidireccionales `@ManyToOne`**. No existe ningún `@OneToMany` inverso, ni `cascade`, ni `orphanRemoval`, ni estrategias de `fetch` declaradas. El enum `CategoryType` existe pero no se usa en ninguna entidad (`Product` se relaciona con `Category`, no con `CategoryType`).

- **Qué se puede hacer hoy (por roles):**
  - **Visitante (sin login):** ver el catálogo de productos (`GET /products`, con búsqueda por nombre y orden por precio en `/filter-mintomax` y `/filter-maxtomin`), ver el detalle de un producto y sus reseñas, ver categorías y el listado de opiniones activas (`/reviews-productos`). Registrarse (`/register`) e iniciar sesión (`/login`).
  - **Usuario (`ROLE_USER`):** todo lo anterior más marcar favoritos (`POST /like/toggle`, `LikeController`), gestionar el carrito (`PurchaseLineController`: añadir, aumentar, disminuir y elegir talla), finalizar pedido introduciendo datos de tarjeta (`POST /purchases/{id}/finish`), ver sus pedidos (`/purchases`) y su perfil con estadísticas (`/profile`, `UserStatsDTO`), y crear/editar sus propias reseñas (`ReviewController`).
  - **Administrador (`ROLE_ADMIN`):** crear/editar/desactivar productos (`ProductController`) y categorías (`CategoryController`), desactivar reseñas, ver todos los pedidos, y gestionar usuarios desde `/admin/users` (`UserController`): listar, buscar, crear, editar, activar y desactivar.
  - **Inicialización:** `DataInitializer` solo crea dos usuarios (`user/user` y `admin/admin`). **No siembra productos ni categorías**, por lo que al arrancar la tienda aparece vacía hasta que un admin da de alta contenido.

---

## 2. Features (nuevas funcionalidades)

Ideas de funcionalidad que la app **no** tiene todavía y aportarían valor. Todas son de backend Spring server-side con Thymeleaf + Bootstrap, en línea con la filosofía del curso.

### F-01 · Sembrar catálogo de ejemplo en el arranque
- Qué: ampliar `DataInitializer` para crear categorías y productos de demostración (creatina, proteína, ropa…), no solo los dos usuarios actuales.
- Por qué aporta: hoy la tienda arranca vacía y, con `ddl-auto=create-drop`, cada reinicio borra todo. Tener datos de ejemplo permite probar la app y hacer demos sin dar de alta nada a mano.
- Qué habría que tocar: `config/DataInitializer` (inyectar `CategoryRepository` y `ProductRepository`, ya tiene `ProductRepository` disponible), enums `Medidas` y, si se quiere, `CategoryType`.
- Dificultad: Baja

### F-02 · Descuento por código en el carrito (`discountCode`)
- Qué: aplicar realmente el campo `discountCode` de `PurchaseLine` (hoy solo se almacena, ver comentario `//Dogo10%`) para reducir el precio en el resumen del pedido.
- Por qué aporta: existe la columna y el comentario `(discount*price)` en `PurchaseLine`, pero ningún cálculo la usa; es una funcionalidad a medio empezar que aportaría valor comercial.
- Qué habría que tocar: nueva entidad/tabla de cupones o un servicio de descuentos, `PurchaseLineController` (cálculo de subtotal), la query `calculateTotalPrice` de `PurchaseLineRepository` y la plantilla `purchaseLines/purchaseLinesList.html`.
- Dificultad: Media

### F-03 · Control y descuento de stock al comprar
- Qué: al finalizar el pedido (`PurchaseController.finish`), descontar `quantity` del `stock` del `Product` e impedir añadir al carrito más unidades de las disponibles.
- Por qué aporta: `Product.stock` existe y la plantilla `productsList.html` ya muestra "Disponible/Agotado", pero **nunca se decrementa** (no hay ninguna llamada a `setStock`). Hoy se puede comprar producto agotado.
- Qué habría que tocar: un `ProductService`/`PurchaseService` con la lógica transaccional, `PurchaseController.finish`, `PurchaseLineController.createPurchaseLine` y validaciones en plantilla.
- Dificultad: Media

### F-04 · Cálculo de nota media y número de reseñas por producto
- Qué: mostrar en el detalle del producto (`products/products-details.html`) la media de `rating` y el total de reseñas.
- Por qué aporta: el `ProductController.productDetail` ya carga las reseñas ordenadas, pero no hay agregados. Es información de alto valor para el comprador.
- Qué habría que tocar: `ReviewRepository` (consulta `@Query` con `AVG(r.rating)` y `COUNT`), `ProductController` y la plantilla de detalle.
- Dificultad: Baja

### F-05 · Verificación de "compra verificada" en reseñas
- Qué: marcar automáticamente `Review.userVerified = true` solo si el usuario tiene un `Purchase` con ese `Product`.
- Por qué aporta: el campo `userVerified` ya existe en `Review` pero nunca se rellena (siempre queda nulo). Aportaría credibilidad a las opiniones.
- Qué habría que tocar: `ReviewController.createReviews`, una consulta nueva en `PurchaseLineRepository`/`PurchaseRepository` para saber si el usuario compró el producto, y la plantilla `reviews/reviewsList.html`.
- Dificultad: Media

### F-06 · Página "Mis favoritos"
- Qué: una vista dedicada que liste los productos que el usuario ha marcado con `Like`.
- Por qué aporta: ya existe toda la maquinaria (`LikeService.findFavoriteProduct`, `LikeRepository.findByUser_IdAndProductIsNotNull`, e incluso `UserStatsDTO.favoriteProduct`), pero no hay una pantalla específica de favoritos en el navbar.
- Qué habría que tocar: nuevo método en `LikeController` (GET), una plantilla nueva `favorites/...` y un enlace en `layout/navbar.html`.
- Dificultad: Baja

### F-07 · Filtrar productos por categoría desde la ficha de categoría
- Qué: en `categories/categories-details.html`, listar y enlazar los productos de esa categoría como catálogo navegable.
- Por qué aporta: `CategoryController.categoryDetail` ya carga `productRepository.findByCategory_Id(id)` en el modelo, pero conviene reforzar la navegación catálogo-por-categoría (hoy el filtro principal de `/products` es solo por nombre y por precio).
- Qué habría que tocar: plantilla `categories/categories-details.html` y, opcionalmente, un selector de categoría en `products/productsList.html` apoyado en `ProductRepository.findByCategory_Id`.
- Dificultad: Baja

### F-08 · Estados de pedido y flujo de vida del `Purchase`
- Qué: permitir al admin avanzar el estado de un pedido (`PENDING` -> `IN_PROGRESS` -> `FINISHED`) y al usuario cancelarlo (`CANCEL`).
- Por qué aporta: el enum `PurchaseStatus` define cuatro estados con etiquetas en español, pero hoy solo se usan `PENDING` (carrito) y `FINISHED` (al pagar). `IN_PROGRESS` y `CANCEL` están huérfanos.
- Qué habría que tocar: `PurchaseController` (acciones POST de cambio de estado, con `@PreAuthorize` por rol), plantillas `purchases/purchaseDetails.html` y `purchases/purchaseList.html`.
- Dificultad: Media

### F-09 · Historial de pedidos finalizados separado del carrito
- Qué: distinguir claramente "carrito" (pedido `PENDING`) de "pedidos realizados" (`FINISHED`) en la vista `/purchases`.
- Por qué aporta: `PurchaseController.purchases` mezcla todos los `Purchase` del usuario sin filtrar por estado, de modo que el carrito en curso aparece junto a las compras ya pagadas.
- Qué habría que tocar: `PurchaseRepository` (derived query por `status` y usuario), `PurchaseController` y la plantilla `purchases/purchaseList.html`.
- Dificultad: Baja

### F-10 · Selección de talla validada según el tipo de producto
- Qué: que la `talla` de `PurchaseLine` se elija de una lista coherente con `Product.medidas` (`SHOES` vs `CLOTHING`) en lugar de texto libre.
- Por qué aporta: hoy `PurchaseLineController.updateTalla` acepta cualquier `String`. El enum `Medidas` existe precisamente para distinguir tallaje de calzado y ropa, pero no condiciona nada.
- Qué habría que tocar: `PurchaseLineController.updateTalla`, la plantilla del carrito y, posiblemente, un nuevo enum de tallas por tipo.
- Dificultad: Media

### F-11 · Buscador y filtros combinados de productos
- Qué: unificar en `/products` la búsqueda por nombre, el orden por precio y un filtro por categoría y por rango de precio en un único formulario.
- Por qué aporta: hoy hay tres endpoints separados (`/products?name=`, `/filter-mintomax`, `/filter-maxtomin`) que se pisan entre sí y pierden el criterio anterior al cambiar de orden.
- Qué habría que tocar: `ProductController`, consolidar las `@Query` de `ProductRepository` (`findActivoMinMax`, `findActivoMaxMin`, `findActivoFiltering`) en una sola parametrizada, y la barra de herramientas de `products/productsList.html`.
- Dificultad: Media

### F-12 · Datos globales del navbar con `@ControllerAdvice`
- Qué: exponer en todas las vistas (vía `@ModelAttribute` global) datos como el número de artículos en el carrito del usuario.
- Por qué aporta: el navbar muestra un icono de carrito, pero no indica cuántos productos hay. Centralizar estos datos evita repetir lógica en cada controller.
- Qué habría que tocar: una nueva clase `@ControllerAdvice` con `@ModelAttribute`, apoyada en `PurchaseLineRepository`/`PurchaseRepository`, y `layout/navbar.html`.
- Dificultad: Media

### F-13 · Edición de perfil propio con avatar (flujo limpio)
- Qué: una pantalla de "editar mi perfil" segura, donde el usuario solo cambie username, email, contraseña y foto, sin tocar rol ni estado.
- Por qué aporta: existe `users/userLimitedForm.html` y los métodos `editLimitedUser`/`saveUser` en `UserController`, pero el flujo actual reaprovecha la entidad `User` completa con campos sensibles ocultos (ver B-01). Una feature de perfil bien acotada da valor y cierra ese hueco.
- Qué habría que tocar: nuevo DTO `ProfileForm`, `UserController` (`/profile/edit`, `POST /profile`), un método `updateLimited` en `UserService` (hoy comentado) y la plantilla `userLimitedForm.html`.
- Dificultad: Media

### F-14 · Recuperar/restaurar elementos desactivados (papelera)
- Qué: pantallas para que el admin vea y reactive productos, categorías y reseñas dadas de baja (`activo = false` / `active = false`).
- Por qué aporta: el borrado es lógico (soft-delete) en `Product`, `Category` y `Review`, pero solo existe el camino de desactivar; no hay forma desde la interfaz de volver a activarlos (en usuarios sí existe `/admin/users/activate`).
- Qué habría que tocar: `ProductController`, `CategoryController`, `ReviewController` (nuevas acciones de reactivar), repos (consultas por `activoFalse`) y plantillas de listado.
- Dificultad: Baja

### F-15 · Resumen económico del pedido (IVA, envío, propina)
- Qué: desglosar el total del pedido con IVA y, si aplica, gastos de envío, en lugar de un único `totalPrice`.
- Por qué aporta: en `PurchaseController.finish` hay comentarios `// iva, service charge, terrace` y un parámetro `tip` que se recibe pero **no se usa**. La plantilla del carrito ya muestra "Envío: Gratis" de forma fija.
- Qué habría que tocar: `Purchase` (campos de impuestos), `PurchaseController.finish`, `PurchaseLineRepository.calculateTotalPrice` y plantillas `purchaseLines/purchaseLinesList.html` y `purchases/purchaseDetails.html`.
- Dificultad: Media

### F-16 · Panel de administración con métricas básicas
- Qué: un dashboard para admin con totales (número de productos activos, pedidos por estado, usuarios, ingresos acumulados).
- Por qué aporta: ya existe `PurchaseRepository.calculateTotalMoneySpentByUserId` y varios `count...`; agregarlos en un panel da visión de negocio y reaprovecha consultas existentes.
- Qué habría que tocar: nuevo controller `/admin/dashboard`, consultas de agregación en los repos y una plantilla nueva.
- Dificultad: Media

---

## 3. Fixes (correcciones y deuda técnica)

Problemas, riesgos y deuda detectados en el código **actual**.

### B-01 · Escalada de privilegios al editar el perfil propio
- Problema: un usuario autenticado puede convertirse en administrador o reactivarse a sí mismo manipulando el formulario de perfil. El `POST /profile` hace binding de la entidad `User` completa, incluidos `role` y `online`, que viajan como campos ocultos.
- Dónde: `UserController.saveUser` (`@PostMapping("profile")`) que llama a `UserService.update`; plantilla `templates/users/userLimitedForm.html` (líneas con `<input type="hidden" th:field="*{role}">` y `<input type="hidden" th:field="*{online}">`). `UserService.update` copia `setRole(userForm.getRole())` sin comprobar quién hace la petición.
- Impacto: vulnerabilidad de control de acceso (mass assignment + IDOR potencial: no se verifica que el `id` enviado sea el del usuario logueado). Severidad crítica de seguridad.
- Propuesta: usar un DTO de perfil sin `role`/`online`, recuperar el usuario por el `@AuthenticationPrincipal` (no por un `id` del formulario) y separar el flujo limitado del flujo de admin. Encaja con F-13.
- Severidad: Alta

### B-02 · Datos de tarjeta almacenados en texto plano
- Problema: el número de tarjeta, titular y caducidad se guardan literalmente en la tabla `Purchase`.
- Dónde: entidad `Purchase` (`cardNumber`, `cardOwner`, `cardExpirationDate`) y `PurchaseController.finish`, que hace `purchase.setCardNumber(cardNumber)` con el valor en claro.
- Impacto: incumplimiento básico de protección de datos de pago (PCI). En un entorno real es inaceptable persistir el PAN completo.
- Propuesta: no almacenar el número completo (a lo sumo los 4 últimos dígitos), delegar el pago en una pasarela externa y nunca guardar el CVV (que aquí, acertadamente, no se persiste). En el ámbito del curso basta con guardar solo los 4 últimos dígitos enmascarados.
- Severidad: Alta

### B-03 · Repository anidado por error dentro de `PurchaseLineRepository`
- Problema: hay una interfaz `ProductRepository` **declarada dentro** de `PurchaseLineRepository`, duplicando un repositorio que ya existe en su propio fichero.
- Dónde: `repository/PurchaseLineRepository.java`, líneas finales: `public interface ProductRepository extends JpaRepository<Product, Long> { ... }` anidada.
- Impacto: confusión y riesgo de que Spring Data genere un bean repetido/ambiguo de `Product`; código muerto que ensucia el diseño.
- Propuesta: eliminar la interfaz anidada y dejar únicamente `repository/ProductRepository.java`.
- Severidad: Media

### B-04 · El "carrito" se crea y persiste en una petición GET
- Problema: visitar el carrito provoca una escritura en base de datos. `PurchaseLineController.findAll` (`GET /purchase-lines`) ejecuta `purchaseRepository.save(purchase)` siempre, incluso cuando se crea un `Purchase` vacío por el simple hecho de entrar.
- Dónde: `PurchaseLineController.findAll`.
- Impacto: viola la semántica de GET (no debería mutar estado), genera pedidos `PENDING` vacíos "fantasma" y puede contaminar listados y métricas. Además dificulta el cacheo.
- Propuesta: no crear ni guardar `Purchase` en el GET; crear el pedido solo cuando se añade la primera línea (`POST /purchase-lines/create`), que ya contempla esa creación.
- Severidad: Media

### B-05 · `replace` con regex que no limpia los espacios de la tarjeta
- Problema: la intención es quitar espacios del número de tarjeta, pero `String.replace` interpreta el patrón como texto literal, no como expresión regular.
- Dónde: `PurchaseController.finish`: `cardNumber.replace("\\s", "")`. La cadena literal `"\\s"` nunca aparece en el número, así que no se elimina nada; un número con espacios ("1111 2222 ...") fallará la validación `matches("\\d{16}")`.
- Impacto: bug funcional: el pago con un número formateado con espacios se rechaza siempre.
- Propuesta: usar `cardNumber.replaceAll("\\s", "")` (o `replace(" ", "")`).
- Severidad: Media

### B-06 · Sin manejo global de errores: `orElseThrow` devuelve 500 crudos
- Problema: numerosas rutas lanzan excepción sin página de error amigable. No existe `@ControllerAdvice` ni `@ExceptionHandler` en todo el proyecto.
- Dónde: por ejemplo `ProductController.productDetail`/`productsEdit`, `PurchaseController.purchase`/`finish`, `ReviewController.editReviews`/`reviewDetail`, `CategoryController.editCategory`, `PurchaseLineController.increaseQuantity`… todos con `findById(...).orElseThrow()`. Una URL con un id inexistente produce un error 500 sin maquetar.
- Impacto: mala experiencia de usuario y fuga de trazas internas. Inconsistente con el `try/catch` que sí hay en `AuthController` y partes de `UserController`.
- Propuesta: añadir un `@ControllerAdvice` global con `@ExceptionHandler` para `NoSuchElementException`/`IllegalArgumentException` que muestre una vista de error 404/500 propia.
- Severidad: Media

### B-07 · Validación de datos ausente (sin Bean Validation)
- Problema: no se usa `@Valid` ni anotaciones de validación (`@NotNull`, `@Size`, `@Email`, `@Min`, `@Max`) en ninguna entidad ni DTO.
- Dónde: `Product` (en el propio fichero hay comentarios reconociendo que `price` y `stock` deberían ser `nullable=false`), `Review.rating` (sin rango 1–5), `RegisterForm` (email/username sin formato ni longitud). Los `@PostMapping` reciben `@ModelAttribute` sin validar.
- Impacto: se pueden guardar productos sin precio, reseñas con rating arbitrario o negativo, etc. La integridad depende solo de los atributos HTML del formulario, fácilmente evitables.
- Propuesta: añadir constraints de Jakarta Validation a entidades/DTOs y `@Valid` + `BindingResult` en los controllers, mostrando errores en las plantillas.
- Severidad: Media

### B-08 · Falta `@Transactional` en operaciones de varios pasos
- Problema: no hay ni una sola anotación `@Transactional` en servicios o controllers. El único import de `jakarta.transaction.Transactional` (en `PurchaseLineController`) está sin usar.
- Dónde: `UserService`, `LikeService`, y especialmente los flujos multi-escritura de `PurchaseLineController` (crear línea + recalcular total + guardar purchase) y `PurchaseController.finish`.
- Impacto: una operación que escribe en varias tablas puede quedar a medias ante un fallo, dejando datos inconsistentes (p. ej. línea creada pero total no actualizado).
- Propuesta: introducir una capa de servicio para compras (`PurchaseService`) y anotar con `@Transactional` los métodos que modifican varias entidades.
- Severidad: Media

### B-09 · Lógica de negocio dentro de los controllers
- Problema: gran parte de la lógica vive en los controllers y accede directamente a repositorios, sin capa de servicio. Solo existen `UserService`, `LikeService` y `FileService`.
- Dónde: `PurchaseLineController` (cálculo de carrito, totales, alta de pedido), `PurchaseController.finish` (validación de pago y total), `ProductController`/`CategoryController`/`ReviewController` (CRUD directo sobre repos).
- Impacto: dificulta los tests unitarios, la reutilización y el control transaccional; mezcla responsabilidades (presentación vs negocio).
- Propuesta: extraer `ProductService`, `PurchaseService`, `ReviewService` y `CategoryService` que encapsulen las reglas, dejando los controllers finos.
- Severidad: Media

### B-10 · Total del pedido calculado con el precio actual del producto, no el comprado
- Problema: el total se recalcula multiplicando por el precio vivo del `Product`, ignorando el `unitPrice` que la propia `PurchaseLine` guarda al añadirse.
- Dónde: `PurchaseLineRepository.calculateTotalPrice` usa `SUM(pl.quantity * pl.product.price)`; el bucle de `PurchaseLineController.findAll` también usa `line.getProduct().getPrice()`. Sin embargo `createPurchaseLine` sí almacena `line.setUnitPrice(product.getPrice())`.
- Impacto: si un admin cambia el precio de un producto, los pedidos ya creados (incluso finalizados) ven alterado su importe; el `unitPrice` histórico queda inservible.
- Propuesta: calcular siempre sobre `pl.unitPrice` (`SUM(pl.quantity * pl.unitPrice)`) para congelar el precio en el momento de la compra.
- Severidad: Media

### B-11 · Posibles consultas N+1 al recorrer asociaciones en plantillas
- Problema: las asociaciones `@ManyToOne` son `EAGER` por defecto, pero al iterar colecciones y acceder a entidades relacionadas en las vistas se disparan consultas adicionales por cada fila.
- Dónde: `purchaseLines/purchaseLinesList.html` accede a `line.product.image`, `line.product.name`, `line.product.price` por cada línea; `UserStatsDTO` arrastra listas completas de `reviews`, `purchases` y `favorites` que las plantillas de perfil recorren accediendo a sus productos/usuarios.
- Impacto: degradación de rendimiento al crecer el volumen; con `spring.jpa.show-sql=true` se observan múltiples SELECT.
- Propuesta: usar `@Query` con `JOIN FETCH` o proyecciones/DTO para traer en una sola consulta lo que la vista necesita.
- Severidad: Baja

### B-12 · Inconsistencias de rutas entre `SecurityConfig` y los controllers
- Problema: varias reglas de seguridad apuntan a rutas que no coinciden exactamente con los `@GetMapping`/`@PostMapping` reales, dejando huecos o reglas inertes.
- Dónde:
  - `SecurityConfig` protege `POST /categories/` (con barra final) pero `CategoryController` mapea `@PostMapping("/categories")` (sin barra). La regla con barra no aplica; el alta de categoría cae en `anyRequest().authenticated()` (cualquier usuario logueado, no solo admin).
  - `SecurityConfig` restringe `GET /products/new` a admin, pero el formulario real de alta es `GET /products/create` (`ProductController.productsCreate`), que no está en la lista y por tanto solo exige estar autenticado.
  - Hay reglas con typos como `/categories/desactivate/*` mientras el método es `deactivate`.
- Impacto: la protección por rol no es la que se cree; un usuario normal podría llegar a formularios o endpoints pensados para admin. La plantilla `products-form.html` mitiga visualmente con `sec:authorize`, pero el `POST` no queda blindado por la cadena de seguridad.
- Propuesta: alinear cuidadosamente patrones de `SecurityConfig` con los mappings reales y, mejor aún, usar `@PreAuthorize` a nivel de método para no depender de coincidencias de cadena.
- Severidad: Alta

### B-13 · `UserRepository.findByPassword` innecesario y peligroso
- Problema: existe un derived query que busca usuarios por su contraseña (cifrada).
- Dónde: `repository/UserRepository.findByPassword(String password)`.
- Impacto: no tiene caso de uso legítimo (con BCrypt no se busca por hash) y fomenta malas prácticas; código muerto que invita a usos inseguros.
- Propuesta: eliminar el método.
- Severidad: Baja

### B-14 · Estrategias de generación de `@Id` heterogéneas
- Problema: `Category` usa `GenerationType.AUTO` mientras el resto de entidades usan `GenerationType.IDENTITY`.
- Dónde: `Category.id` (`@GeneratedValue(strategy = GenerationType.AUTO)`) frente a `Product`, `Purchase`, `User`, etc. (`IDENTITY`).
- Impacto: con `AUTO`, Hibernate puede elegir una secuencia/tabla distinta del autoincremento, generando comportamiento inconsistente entre tablas y posibles sorpresas al migrar de H2 a otra base de datos.
- Propuesta: unificar la estrategia (normalmente `IDENTITY` para H2/MySQL) en todas las entidades.
- Severidad: Baja

### B-15 · `System.out.println` y comentarios/código muerto por el código
- Problema: trazas con `System.out.println` y bloques comentados extensos.
- Dónde: `ProductController.createProduct` ("ACCION COMPLETADA CON EXITO: "), `ReviewController.createReviews` ("Review recibida "), método `updateLimited` íntegro comentado en `UserService`, opción funcional comentada en `loadUserByUsername`, bloques `// reviews ...` repetidos en `ProductController`/`CategoryController`.
- Impacto: ruido en logs y en el código; dificulta el mantenimiento. Las trazas deberían ir por el logger (`@Slf4j`, ya usado en `FileService`).
- Propuesta: sustituir `System.out` por `log.debug(...)` y limpiar el código comentado.
- Severidad: Baja

### B-16 · Subida de ficheros: validación y limpieza incompletas
- Problema: `FileService.store` valida el `contentType` declarado por el cliente (manipulable) y no limita extensiones ni borra la imagen anterior al reemplazarla.
- Dónde: `service/FileService` (lista `AllowedTypes` por content-type) y los controllers que la usan (`ProductController.createProduct`, `CategoryController.saveCategory`, `UserController.save`/`saveUser`). Al editar, la imagen previa queda huérfana en `uploads/`.
- Impacto: posible subida de ficheros con tipo falseado y acumulación de archivos huérfanos en disco. Además, las imágenes guardadas en `uploads/` no se versionan ni se limpian.
- Propuesta: validar también la extensión y el contenido real, generar nombres seguros (ya se usa `UUID`), y borrar la imagen anterior al sustituirla.
- Severidad: Baja

### B-17 · `WelcomeController` e `IndexController` solapados / endpoints sueltos
- Problema: hay dos controllers triviales (`IndexController` redirige `/` a `/home`, `WelcomeController` sirve `/home`) y rutas de prueba (`/hola`, `/adios`) permitidas en `SecurityConfig` sin controller asociado visible.
- Dónde: `IndexController`, `WelcomeController`, y `SecurityConfig` (`requestMatchers("/hola", "/adios", ...)`).
- Impacto: deuda menor de organización; reglas de seguridad para rutas inexistentes.
- Propuesta: unificar la home en un solo controller y retirar las rutas de ejemplo (`/hola`, `/adios`) del filtro de seguridad.
- Severidad: Baja

### B-18 · Cobertura de pruebas prácticamente nula
- Problema: el único test es `G2JavaApplicationTests.contextLoads()`, vacío.
- Dónde: `src/test/java/com/demo/G2JavaApplicationTests.java`.
- Impacto: ninguna garantía automática de que login, carrito, pago o seguridad funcionen; cualquier refactor es a ciegas. El `pom.xml` ya incluye los starters de test (`webmvc-test`, `data-jpa-test`, `security-test`), así que la infraestructura está disponible y sin aprovechar.
- Propuesta: añadir tests de slice (`@WebMvcTest` para controllers, `@DataJpaTest` para repos) y de seguridad (`@WithUserDetails`) cubriendo al menos login, alta de producto por admin, flujo de carrito y las reglas de acceso de B-12.
- Severidad: Alta

---

## 4. Cómo evolucionaría en un entorno real (ciclo de vida del software)

Este proyecto representa la **primera entrega** de un producto. En un equipo real, esa entrega no es el final, sino el punto de partida de un ciclo continuo de mejora. El backlog anterior (features y fixes) es justamente la materia prima de ese ciclo: se prioriza, se implementa en pequeñas iteraciones, se prueba y se despliega, y la realimentación de los usuarios genera nuevas entradas en el backlog. Aterrizado a "Five Chill Guys Gym", la evolución natural pasaría por:

- **Control de versiones y ramas:** trabajar cada idea del backlog en su propia rama (`feature/descuento-cupon`, `fix/tarjeta-replace`), con Pull Requests revisados por pares. Hoy el repositorio vive con código entremezclado y comentarios de trabajo en curso; un flujo de ramas obligaría a revisar B-01 o B-12 antes de integrarlos.
- **Tests automatizados y cobertura:** convertir B-18 en una suite real. Con los starters de test ya presentes, se cubrirían controllers, repositorios y reglas de seguridad. La cobertura se mediría y se vigilaría para que no baje en cada cambio.
- **Análisis estático y calidad:** el repositorio ya incluye `compose.sonar.yaml`; integrar SonarQube en el flujo permitiría detectar automáticamente cosas como el `replace` de B-05, el repositorio anidado de B-03 o el código muerto de B-15.
- **CI/CD:** un pipeline (por ejemplo GitHub Actions) que en cada push compile, ejecute los tests y lance el análisis de calidad antes de permitir el merge; y que, al integrar en la rama principal, publique el artefacto.
- **Contenedores y despliegue:** empaquetar la app en una imagen Docker y sustituir la H2 en memoria (`ddl-auto=create-drop`, que borra todo en cada arranque) por una base de datos persistente (PostgreSQL) gestionada con `docker compose` y perfiles `dev`/`prod`. Esto resolvería que hoy el catálogo desaparezca en cada reinicio (relacionado con F-01).
- **Observabilidad y logs:** sustituir los `System.out.println` (B-15) por logging estructurado y exponer métricas/health con Spring Boot Actuator, de modo que en producción se pueda diagnosticar un fallo de pago o un pico de errores 500 (B-06).
- **Seguridad:** tratar B-01, B-02 y B-12 como bloqueantes antes de cualquier despliegue público; añadir gestión de secretos (nada de credenciales en el repo), HTTPS y revisión periódica de dependencias.
- **Recogida de feedback y priorización continua:** instrumentar la app para saber qué productos se buscan o se compran, escuchar a los usuarios y reordenar el backlog según valor y esfuerzo. Lo que hoy son comentarios `// TODO` dispersos en el código (finalizar compra, impedir que un admin se desactive a sí mismo, etc.) pasaría a ser tickets priorizados.

Importante para el contexto del curso: las direcciones "modernas" del párrafo anterior (API REST, pasarela de pago real, Docker, CI/CD, observabilidad, despliegue en la nube) se mencionan como **horizonte** para entender hacia dónde evoluciona una aplicación real. **Exceden el alcance de esta asignatura**, centrada en Spring server-side con Thymeleaf y Bootstrap; aquí sirven solo para situar el proyecto dentro del ciclo de vida completo del software.

---

## 5. Priorización orientativa

Valoración cualitativa (no es un compromiso de implementación). "Horizonte": Corto = primeros pasos lógicos / Medio = requiere capa de servicio o refactor / Largo = excede el curso o implica infraestructura.

| Idea | Tipo | Valor | Esfuerzo | Horizonte |
|------|------|-------|----------|-----------|
| B-01 Escalada de privilegios en perfil | Fix | Muy alto | Medio | Corto |
| B-12 Rutas `SecurityConfig` vs controllers | Fix | Muy alto | Medio | Corto |
| B-02 Tarjeta en texto plano | Fix | Alto | Medio | Corto |
| B-05 `replace` regex en pago | Fix | Alto | Bajo | Corto |
| B-03 Repository anidado | Fix | Medio | Bajo | Corto |
| B-18 Tests automatizados | Fix | Alto | Alto | Corto |
| B-06 Manejo global de errores | Fix | Medio | Bajo | Corto |
| F-01 Sembrar catálogo de ejemplo | Feature | Alto | Bajo | Corto |
| B-04 Carrito persistido en GET | Fix | Medio | Medio | Corto |
| B-10 Total con precio actual vs comprado | Fix | Medio | Bajo | Corto |
| B-07 Bean Validation | Fix | Medio | Medio | Medio |
| B-08 `@Transactional` en flujos multi-paso | Fix | Medio | Medio | Medio |
| B-09 Extraer capa de servicio | Fix | Medio | Alto | Medio |
| F-03 Control y descuento de stock | Feature | Alto | Medio | Medio |
| F-04 Media de valoraciones por producto | Feature | Alto | Bajo | Corto |
| F-06 Página "Mis favoritos" | Feature | Medio | Bajo | Corto |
| F-08 Flujo de estados del pedido | Feature | Medio | Medio | Medio |
| F-11 Buscador y filtros combinados | Feature | Medio | Medio | Medio |
| F-02 Descuento por cupón | Feature | Medio | Medio | Medio |
| F-12 Datos globales del navbar (`@ControllerAdvice`) | Feature | Medio | Medio | Medio |
| F-15 Desglose económico (IVA/envío) | Feature | Medio | Medio | Medio |
| B-11 Consultas N+1 | Fix | Medio | Medio | Medio |
| F-16 Panel de métricas admin | Feature | Medio | Medio | Medio |
| Migración a PostgreSQL + Docker + CI/CD | Evolución | Alto | Alto | Largo |
| API REST + pasarela de pago real | Evolución | Alto | Alto | Largo |
