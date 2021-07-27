package ru.gb.runov.springsecurity.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.runov.springsecurity.model.entities.DbUser;

import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Profile("dao")
public interface UserRepository
          extends CrudRepository<DbUser, Long>
        //    extends JpaRepository<DbUser, Long>
{
    Optional<DbUser> findByUsername(String username);

}
