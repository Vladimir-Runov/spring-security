package ru.gb.runov.springsecurity.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.gb.runov.springsecurity.configs.DaoSecurityConfig;
import ru.gb.runov.springsecurity.model.entities.Role;
import ru.gb.runov.springsecurity.model.entities.DbUser;
import ru.gb.runov.springsecurity.repositories.UserRepository;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Profile("jdbc")
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    private Logger logger = LoggerFactory.getLogger(DaoSecurityConfig.class.getName());

    public Optional<DbUser> findByUsername(String username) {
        logger.info("++++++ UserService   findByUsername " + username);
        return userRepository.findByUsername(username);
    }

    public DbUser saveOrUpdate(DbUser user) {
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        DbUser user = findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(String.format("User '%s' not found", username)));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
