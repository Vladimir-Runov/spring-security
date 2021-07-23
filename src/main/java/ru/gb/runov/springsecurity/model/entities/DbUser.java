package ru.gb.runov.springsecurity.model.entities;

import lombok.Data;
import org.springframework.context.annotation.Profile;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
@Table(name = "users")
public class DbUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "scope")
    private int scope;

    @Column(name = "email")
    private String email;

    @ManyToMany
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))

    private Collection<Role> roles;

    public String getUsername() {
        return username;
    }

    public int getScope() {
        return scope;
    }

    public String getPassword() {
        return password;
    }

    public Collection<Role> getRoles() {
        return roles;
    }
}
