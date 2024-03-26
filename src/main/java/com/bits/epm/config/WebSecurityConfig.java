package com.bits.epm.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;
import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.securityMatcher(PathRequest.toH2Console());
//        http.csrf((csrf) -> csrf.disable());
//        http.headers((headers) -> headers.frameOptions((frame) -> frame.sameOrigin()));

        http.authorizeHttpRequests(authz -> authz
                        .requestMatchers("/js/**", "/css/**", "/img/**").permitAll()
                        .requestMatchers("/").permitAll()
//                .requestMatchers("/logout").permitAll()
                        .requestMatchers("/login*").permitAll()
//                        .anyRequest().authenticated()
                        .anyRequest().permitAll()
        );

        http
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/dashboard", true)
                );

        /*http.formLogin(Customizer.withDefaults());*/

        http.logout(logout ->
                logout.invalidateHttpSession(true)
                        .clearAuthentication(true)
//                        .logoutUrl("/logout")
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
        );


        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {

        UserDetails user = User.withUsername("user")
                .password(passwordEncoder().encode("user"))
                .roles("USER")
                .build();
        UserDetails admin = User.withUsername("admin")
                .password(passwordEncoder().encode("admin"))
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user, admin);
    }

}
