package com.filarty.zoomarket.config;

import com.filarty.zoomarket.models.enums.UserRole;
import com.filarty.zoomarket.prop.SecurityProperties;
import com.filarty.zoomarket.services.CustomUserDetailService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.boot.autoconfigure.orm.jpa.JpaBaseConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final CustomUserDetailService customUserDetailService;
    private final SecurityProperties securityProperties;
    private final DataSource dataSource;
    @SneakyThrows
    @Bean
    public SecurityFilterChain UsersecurityFilterChain(HttpSecurity http){
        return http
                .authorizeHttpRequests(auth ->
                        auth.requestMatchers("/image/**", "/get_product/**", "/search/**", "/registration/**", "/login")
                                .permitAll()
                                .requestMatchers("/js/**", "/css/**")
                                .permitAll()
                                .requestMatchers("/admin")
                                .hasAuthority(UserRole.ADMIN.name())
                                .requestMatchers("/test")
                                .authenticated()
                                .anyRequest()
                                .permitAll())
                .formLogin(formLogin ->
                        formLogin.loginPage("/login").permitAll())
                .logout(logout -> logout.logoutSuccessUrl("/").permitAll())
                .rememberMe(remember -> remember.key(securityProperties.getSecretKey())
                        .tokenValiditySeconds(2629824)
                        .tokenRepository(persistentTokenRepository())
                        .userDetailsService(customUserDetailService))
                .build();

    }


    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        return jdbcTokenRepository;
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(customUserDetailService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
