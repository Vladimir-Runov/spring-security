package ru.gb.runov.springsecurity.configs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.sql.DataSource;

//@EnableGlobalMethodSecurity(securedEnabled = true)
/*
Аннотация @Configuration говорит о том, что данный класс является конфигурационным.
@EnableWebSecurity отключает стандартные настройки безопасности Spring Security и начинает использовать правила, прописанные в SecurityConfig.
@EnableGlobalMethodSecurity активирует возможность ставить защиту на уровне методов (для этого над методами ставятся аннотации
  @Secured и @PreAuthorized).
 */

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)

@EnableWebSecurity(debug = true)
@Profile("jdbc")
public class JdbcSecurityConfig extends WebSecurityConfigurerAdapter {

    private Logger logger = LoggerFactory.getLogger(JdbcSecurityConfig.class.getName());

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        logger.info("Jdbc Authentication Manager");
        http.authorizeRequests()
                .antMatchers("/auth_page/**").authenticated()
                .antMatchers("/admin/**").hasAnyRole("ADMIN", "SUPERADMIN") // ROLE_ADMIN, ROLE_SUPERADMIN
                .anyRequest().permitAll()
                .and()
                .formLogin();
    }

    @Bean
    public JdbcUserDetailsManager users(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource);
    }
}
