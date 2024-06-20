package com.humber.Week7Security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        //these are chains
        http.authorizeHttpRequests((authorize) -> authorize
                //each route and what role can go where, ** means everything following that path
                .requestMatchers("/restaurant/home","/login/**").permitAll()
                .requestMatchers("/restaurant/menu/**").hasAnyRole("USER","ADMIN")
                .requestMatchers("/restaurant/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
        //send what we want the security to use as login instead of the default
        ).formLogin(customLogin -> {
            customLogin.loginPage("/login")
                    .defaultSuccessUrl("/restaurant/home")
                    .permitAll();

        })
                .logout(l -> l
                        .logoutUrl("/logout")
                        .permitAll());

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user1 = User.withUsername("user-one")
                .password(passwordEncoder().encode("password"))
                .roles("USER")
                .build();
        UserDetails admin1 = User.withUsername("admin-one")
                .password(passwordEncoder().encode("password"))
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user1,admin1);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebSecurityCustomizer ignoreResources(){
        return(webSecurity) -> webSecurity
                .ignoring()
                .requestMatchers("css/**","h2-console/**");
    }
}
