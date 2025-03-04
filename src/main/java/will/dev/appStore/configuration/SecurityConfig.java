package will.dev.appStore.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import will.dev.appStore.service.UserService;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final UserService userService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return http
                .csrf(csrf ->csrf.disable())
                .authorizeHttpRequests(auth ->
                        auth.requestMatchers("/","/userAuth/login", "/userAuth/register","/product-unit/{id}",
                                        "/user/{id}","/category/{id}","/subCategory/{id}","/categoryProduct/{id}",
                                        "/brand/{id}","/discount/{id}","/product/{id}","/category-products/{id}",
                                        "/brand/{id}","/discount/{id}","/product/{id}","/category-products/{id}",
                                        "/product-variation/{id}","/product-variation-value/{id}","/product-reviews/{id}",
                                        "/product-descriptions/{id}","/product-features/{id}","/favorites/{id}",
                                        "/product-images/{id}","/orders/{id}","/order-items/{id}","/delivery-modes/{id}",
                                "/payment-modes/{id}","/payments/{id}","/addresses/{id}"
                                ).permitAll()
                                .anyRequest().authenticated())
                /*.oauth2Login(oauth2Login -> oauth2Login // Nouvelle approche pour configurer OAuth2
                        //.loginPage("/login") // Page de connexion personnalisée (optionnelle)
                        .defaultSuccessUrl("/home") // URL de redirection après une connexion réussie
                )*/
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, PasswordEncoder passwordEncoder) throws Exception{
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userService).passwordEncoder(passwordEncoder);
        return authenticationManagerBuilder.build();
    }
}
