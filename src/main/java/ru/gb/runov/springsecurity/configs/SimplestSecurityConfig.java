package ru.gb.runov.springsecurity.configs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
//@EnableGlobalMethodSecurity(securedEnabled = true)
//@EnableGlobalMethodSecurity(prePostEnabled = true)

/*
Аннотация @Configuration говорит о том, что данный класс является конфигурационным.
@EnableWebSecurity отключает стандартные настройки безопасности Spring Security и начинает использовать правила, прописанные в SecurityConfig.
@EnableGlobalMethodSecurity активирует возможность ставить защиту на уровне методов (для этого над методами ставятся аннотации
  @Secured и @PreAuthorized).
 */

@EnableWebSecurity(debug = true)
@Profile("simplest")
public class SimplestSecurityConfig extends WebSecurityConfigurerAdapter {
    private Logger logger = LoggerFactory.getLogger(SimplestSecurityConfig.class.getName());

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        logger.error("Simplest Security Config Initialization");
        http.authorizeRequests()
                .antMatchers("/auth_page/**").authenticated()
                .antMatchers("/admin/**").hasAnyRole("ADMIN", "SUPERADMIN") // ROLE_ADMIN, ROLE_SUPERADMIN
                .anyRequest().permitAll()
                .and()
                .formLogin();
//                .and()
//                .csrf().disable()
//                .logout().logoutSuccessUrl("/");

//                .loginPage("/login") // default GET /login
//                .loginProcessingUrl("/authenticateTheUser") // default: POST /login
//                .permitAll()
//                .and()
//                .logout()
//                .logoutSuccessUrl("/")
//                .permitAll();
    }
}