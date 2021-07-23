package ru.gb.runov.springsecurity.repositories;

import ru.gb.runov.springsecurity.model.entities.Role;

import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Profile("dao")
public interface RoleRepository extends CrudRepository<Role, Long> {

}
