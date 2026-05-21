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
                auth -> auth.requestMatchers("/hola", "/adios", "/login", "/register", "/css/**"
                                , "/images/**", "/webjars/**").permitAll()

                        //Rutas de Productos


                        // Rutas de Categorias
                        .requestMatchers(HttpMethod.GET, "/category").permitAll()
                        .requestMatchers(HttpMethod.GET, "/category/").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/categories/desactivate/*").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/categories/new").hasRole("ADMIN") // solo admin puede acceder a formulario de nuevo restaurante
                        .requestMatchers(HttpMethod.POST, "/categories/edit/*").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/category/*").permitAll()

                        // Rutas de Reviews(Opiniones)

                        .requestMatchers(HttpMethod.GET, "/reviews-productos").permitAll()
                        .requestMatchers(HttpMethod.POST, "/reviews").authenticated()
                        .requestMatchers(HttpMethod.GET, "/review/new").authenticated()
                        .requestMatchers(HttpMethod.GET, "/reviews/edit/*").authenticated()
                        .requestMatchers(HttpMethod.GET, "/reviews/disable/*").hasRole("ADMIN") //Falta crear disable
                        .requestMatchers(HttpMethod.GET, "/reviews/*").permitAll()

                        //Rutas Purchase(Compra)


                        .anyRequest().permitAll()

        );

        http.formLogin(form -> form.loginPage("/login"));
        http.logout(logout -> logout.logoutUrl("/logout").logoutSuccessUrl("/login?logout"));

        return http.build();
    }
}

