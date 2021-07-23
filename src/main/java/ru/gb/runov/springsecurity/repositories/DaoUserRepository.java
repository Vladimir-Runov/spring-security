package ru.gb.runov.springsecurity.repositories;

import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.gb.runov.springsecurity.model.entities.DaoUser;

import java.util.Optional;

@Repository
@Profile("dao")
public interface DaoUserRepository
        extends CrudRepository<DaoUser, Long>
        //    extends JpaRepository<DaoUser, Long>
{
    Optional<DaoUser> findByUsername(String username);

    Optional<DaoUser> findById(Long id);
}
