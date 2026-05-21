package com.demo.config;


import com.demo.model.User;
import com.demo.model.enums.Role;
import com.demo.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DataInitializer  implements ApplicationRunner {

     private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
//    private final UserService userService;

    @Override
public void run(ApplicationArguments args) throws Exception {

    //Opción usando el service:
        //Opción usando el service:
//        User user = userService.register(RegisterForm.builder()
//                .username("user")
//                .email("user@gmail.com")
//                .password("user")
//                .passwordConfirm("user")
//                .build());
        User user = userRepository.save(User.builder()
                .username("user")
                .email("user@gmail.com")
                .password(passwordEncoder.encode("user"))
                .role(Role.ROLE_USER)
                .build());

        //Opción usando directamente el repository
        User admin = userRepository.save(User.builder()
                .username("admin")
                .email("admin@gmail.com")
                .password(passwordEncoder.encode("admin"))
                .role(Role.ROLE_ADMIN)
                .build());

    }
}
