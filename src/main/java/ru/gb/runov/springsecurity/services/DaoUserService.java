package ru.gb.runov.springsecurity.services;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gb.runov.springsecurity.configs.DaoSecurityConfig;
import ru.gb.runov.springsecurity.model.entities.DaoUser;
import ru.gb.runov.springsecurity.model.entities.Role;
import ru.gb.runov.springsecurity.repositories.DaoUserRepository;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Profile("dao")
@RequiredArgsConstructor
public class DaoUserService implements UserDetailsService {

    private final DaoUserRepository userRepository;

    private Logger logger = LoggerFactory.getLogger(DaoSecurityConfig.class.getName());

    public Optional<DaoUser> findByUsername(String username) {
        logger.info("++++++ DAO UserService   findByUsername " + username);
        return userRepository.findByUsername(username);
    }

    public Optional<DaoUser> findById(Long id) {
        logger.info("++++++ DAO UserService   findByUser ID " + id.toString());
        return userRepository.findById(id);
    }


    public DaoUser saveOrUpdate(DaoUser user) {
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        DaoUser user = findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(String.format("DAO-User '%s' not found", username)));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}

