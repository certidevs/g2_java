# G2 Java - Plataforma de Tienda Online

<p align="center">
  <img src="https://img.shields.io/badge/Java-25-orange?logo=openjdk&logoColor=white" alt="Java 25">
  <img src="https://img.shields.io/badge/Spring%20Boot-4.0.5-6DB33F?logo=springboot&logoColor=white" alt="Spring Boot 4">
  <img src="https://img.shields.io/badge/Thymeleaf-server--side-005F0F?logo=thymeleaf&logoColor=white" alt="Thymeleaf">
  <img src="https://img.shields.io/badge/Bootstrap-5.3-7952B3?logo=bootstrap&logoColor=white" alt="Bootstrap 5">
  <img src="https://img.shields.io/badge/DB-H2%20%2F%20PostgreSQL-1F6FEB" alt="H2 / PostgreSQL">
  <img src="https://img.shields.io/badge/Security-Spring%20Security-6DB33F?logo=springsecurity&logoColor=white" alt="Spring Security">
</p>


## Descripción

G2 Java es una aplicación web desarrollada con Spring Boot que implementa una plataforma de comercio electrónico. El sistema permite la gestión de usuarios, productos, categorías, compras, favoritos y reseñas, proporcionando una experiencia completa de tienda online.

La aplicación ha sido diseñada siguiendo la arquitectura MVC (Model-View-Controller) y utiliza Spring Security para la autenticación y autorización de usuarios.

---

# Objetivos del Proyecto

* Aplicar el desarrollo de aplicaciones web con Spring Boot.
* Implementar persistencia de datos mediante JPA/Hibernate.
* Gestionar la autenticación y autorización de usuarios.
* Diseñar una aplicación siguiendo buenas prácticas de arquitectura software.
* Utilizar una base de datos relacional para almacenar la información del sistema.

---

# Tecnologías Utilizadas

## Backend

* Java 25
* Spring Boot 4.0.5
* Spring MVC
* Spring Security
* Spring Data JPA
* Hibernate
* Lombok

## Frontend

* Thymeleaf
* Bootstrap 5.3.3
* Font Awesome 7.2
* HTML5
* CSS3

## Base de Datos

* H2 Database (Base de datos en memoria)

## Herramientas de Construcción

* Maven

---

# Arquitectura del Proyecto

El proyecto sigue una arquitectura por capas:

```text
src/main/java/com/demo
│
├── config
│   ├── SecurityConfig
│   ├── WebConfig
│   └── DataInitializer
│
├── controller
│   ├── AuthController
│   ├── UserController
│   ├── ProductController
│   ├── CategoryController
│   ├── PurchaseController
│   ├── PurchaseLineController
│   ├── ReviewController
│   └── LikeController
│
├── dto
│   ├── RegisterForm
│   └── UserStatsDTO
│
├── model
│   ├── User
│   ├── Product
│   ├── Category
│   ├── Purchase
│   ├── PurchaseLine
│   ├── Review
│   └── Like
│
├── repository
│   └── Repositorios JPA
│
├── service
│   └── Lógica de negocio
│
└── G2JavaApplication
```

---

# Modelo de Datos

## Usuario (User)

Representa los usuarios registrados en el sistema.

Funciones:

* Registro
* Inicio de sesión
* Gestión del perfil
* Realización de compras
* Publicación de reseñas
* Gestión de favoritos

## Producto (Product)

Representa los artículos disponibles en la tienda.

Información típica:

* Nombre
* Descripción
* Precio
* Imagen
* Categoría

## Categoría (Category)

Permite clasificar los productos para facilitar la navegación.

## Compra (Purchase)

Representa un pedido realizado por un usuario.

## Línea de Compra (PurchaseLine)

Contiene los productos incluidos en una compra.

## Reseña (Review)

Permite que los usuarios valoren productos.

## Favorito (Like)

Gestiona los productos marcados como favoritos por cada usuario.

---

# Seguridad

La aplicación utiliza Spring Security para implementar:

* Autenticación de usuarios.
* Inicio y cierre de sesión.
* Protección de rutas privadas.
* Control de acceso basado en roles.
* Restricción de operaciones administrativas.

---

# Funcionalidades Principales

## Gestión de Usuarios

* Registro de usuarios.
* Inicio de sesión.
* Gestión de perfiles.
* Control de acceso.

## Gestión de Productos

* Crear productos.
* Modificar productos.
* Eliminar productos.
* Consultar catálogo.

## Gestión de Categorías

* Crear categorías.
* Editar categorías.
* Eliminar categorías.
* Filtrar productos.

## Sistema de Compras

* Realizar pedidos.
* Consultar historial de compras.
* Gestionar líneas de compra.

## Sistema de Favoritos

* Añadir productos a favoritos.
* Eliminar favoritos.
* Consultar lista de favoritos.

## Sistema de Reseñas

* Publicar valoraciones.
* Consultar opiniones.
* Asociar reseñas a productos.

---

# Configuración

## Base de Datos

El proyecto utiliza H2 Database en memoria.

Configuración actual:

```properties
spring.application.name=g2_java

spring.datasource.url=jdbc:h2:mem:g2_java_db
spring.datasource.driver-class-name=org.h2.Driver

spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.show-sql=true

spring.h2.console.enabled=true

spring.servlet.multipart.max-file-size=10MB
```

### Características

* La base de datos se crea automáticamente al iniciar la aplicación.
* Todos los datos se eliminan al detener la aplicación.
* Adecuada para desarrollo y pruebas.
* Consola H2 habilitada.

---

# Consola H2

Una vez iniciada la aplicación:

```text
http://localhost:8080/h2-console
```

Configuración:

```text
JDBC URL: jdbc:h2:mem:g2_java_db
User Name: sa
Password:
```

---

# Instalación

## Requisitos

* JDK 25
* Maven 3.9 o superior

## Clonar el repositorio

```bash
git clone <url-del-repositorio>
cd g2_java
```

## Compilar

```bash
mvn clean install
```

## Ejecutar

```bash
mvn spring-boot:run
```

o

```bash
java -jar target/g2_java-0.0.1-SNAPSHOT.jar
```

---

# Testing

Ejecutar todas las pruebas:

```bash
mvn test
```

---

# Dependencias Principales

### Spring

* spring-boot-starter-webmvc
* spring-boot-starter-security
* spring-boot-starter-data-jpa
* spring-boot-starter-thymeleaf

### Base de Datos

* H2 Database

### Frontend

* Bootstrap
* Font Awesome
* WebJars

### Utilidades

* Lombok

### Testing

* Spring Security Test
* Spring Data JPA Test
* Spring MVC Test
* Thymeleaf Test

---

# Roles del Sistema

## Usuario

Puede:

* Registrarse.
* Iniciar sesión.
* Consultar productos.
* Añadir favoritos.
* Realizar compras.
* Escribir reseñas.

## Administrador

Puede:

* Gestionar productos.
* Gestionar categorías.
* Gestionar usuarios.
* Supervisar compras.
* Administrar el contenido de la plataforma.

| Acción                                                  | Visitante | Usuario | Admin |
|---------------------------------------------------------|:--------:|:-------:|:-----:|
| Ver Tienda, productos y reseñas                         | Sí | Sí | Sí |
| Escribir reseñas                                        | No | Sí | Sí |
| Crear y gestionar Compras                               | No | Sí | Sí |
| Marcar favoritos                                        | No | Sí | Sí |
| Crear/editar/desactivar productos, categorias y reseñas | No | No | Sí |
| Moderar reseñas y gestionar usuarios                    | No | No | Sí |
---

# Aprendizajes Obtenidos

Durante el desarrollo de este proyecto se han trabajado los siguientes conceptos:

* Arquitectura MVC.
* Programación orientada a objetos.
* Persistencia con JPA/Hibernate.
* Seguridad con Spring Security.
* Gestión de dependencias con Maven.
* Integración de bases de datos.
* Desarrollo de aplicaciones web con Spring Boot.
* Uso de Thymeleaf para la generación dinámica de vistas.

---

# Equipo de Desarrollo

Proyecto académico desarrollado por el Grupo 2 como práctica de desarrollo de aplicaciones Java utilizando Spring Boot.

---

# Licencia

Proyecto desarrollado con fines educativos y formativos.

Todos los derechos pertenecen a sus respectivos autores.
