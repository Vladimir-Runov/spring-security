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
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)

@EnableWebSecurity(debug = true)
@Profile("inmemory")
public class InMemorySecurityConfig extends WebSecurityConfigurerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(InMemorySecurityConfig.class.getName());

    @Bean
    public UserDetailsService users() {
        UserDetails user = User.builder()
                .username("user")
                .password("{bcrypt}$2y$12$BGMFlWNQk8wFpMpei4ixGeaSyntPo1.2LUvxIzCc6rwxXKkiwwHdO")
                .roles("USER")
                .build();
        UserDetails admin = User.builder()
                .username("admin")
                .password("{bcrypt}$2y$12$BGMFlWNQk8wFpMpei4ixGeaSyntPo1.2LUvxIzCc6rwxXKkiwwHdO")
                .roles("USER", "ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user, admin);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        logger.info("In-Memory Security Configuration");
        http.authorizeRequests()
                .antMatchers("/auth_page/**").authenticated()
                .antMatchers("/admin/**").hasAnyRole("ADMIN", "SUPERADMIN")
                .anyRequest().permitAll()
                .and()
                .formLogin();
    }
}
