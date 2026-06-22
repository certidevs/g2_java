package com.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    // passwordEncoder se usa para para cifrar las contraseñas

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf -> csrf.ignoringRequestMatchers("/h2-console/**"));

        http.headers(headers -> headers.frameOptions(frame ->
                frame.sameOrigin())); // h2 usa iframes

        http.authorizeHttpRequests(
                auth -> auth.requestMatchers("/hola", "/adios", "/login", "/register", "/css/**",
                                "/uploads/**", "/images/**", "/webjars/**").permitAll()


                        //Ruta de Home
                        .requestMatchers(HttpMethod.GET, "/").permitAll()
                        .requestMatchers(HttpMethod.GET, "/home").permitAll()
                        //Rutas de Productos
                        .requestMatchers(HttpMethod.GET, "/products").permitAll()
                        .requestMatchers(HttpMethod.POST, "/products").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/products/deactivate/*").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/products/new").hasRole("ADMIN") // solo admin puede acceder a formulario de nuevo restaurante
                        .requestMatchers(HttpMethod.GET, "/products/edit/*").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/products/*").permitAll()
                        // Rutas de Categorias
                        .requestMatchers(HttpMethod.GET, "/categories").permitAll()
                        .requestMatchers(HttpMethod.POST, "/categories/").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/categories/desactivate/*").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/categories/new").hasRole("ADMIN") // solo admin puede acceder a formulario de nuevo restaurante
                        .requestMatchers(HttpMethod.GET, "/categories/edit/*").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/categories/*").permitAll()

                        // Rutas de Reviews(Opiniones)
                        .requestMatchers(HttpMethod.GET, "/reviews-productos").permitAll()
                        .requestMatchers(HttpMethod.POST, "/reviews").authenticated()
                        .requestMatchers(HttpMethod.GET, "/reviews/new").authenticated()
                        .requestMatchers(HttpMethod.GET, "/reviews/new/*").authenticated()
                        //requestMatchers("/reviews/new/**").authenticated() con esta , sería como poner los 2 news de arriba con * y sin él
                        .requestMatchers(HttpMethod.GET, "/reviews/edit/*").authenticated()
                        .requestMatchers(HttpMethod.GET, "/reviews/desactivate/*").hasRole("ADMIN") //Falta crear disable
                        //Para proteger las reviews activadas y desactivadas
                        .requestMatchers(HttpMethod.GET, "/reviews/desactivated").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/reviews/activate/*").hasRole("ADMIN")
                        //esto es para permitir que cualquiera pueda ver las reviews
                        .requestMatchers(HttpMethod.GET, "/reviews/*").permitAll()


                        //Rutas Purchase(Compra)
                        .requestMatchers(HttpMethod.GET, "/purchase-lines").authenticated()
                        .requestMatchers(HttpMethod.GET, "/purchases").authenticated()

                        // panel de usuarios para admins
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        //.requestMatchers("/profile/**").hasRole("USER")
                        // TODO
                        .anyRequest().authenticated()
                      //.anyRequest().permitAll()




        );

        http.formLogin(form ->
                form.loginPage("/login")
                        .defaultSuccessUrl("/home",true)
                        .permitAll()
        );
        http.logout(logout -> logout.logoutUrl("/logout").logoutSuccessUrl("/login?logout"));

        return http.build();
    }
}
