package ru.gb.runov.springsecurity.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.gb.runov.springsecurity.configs.DaoSecurityConfig;
import ru.gb.runov.springsecurity.model.entities.DaoUser;
import ru.gb.runov.springsecurity.model.entities.DbUser;
import ru.gb.runov.springsecurity.services.DaoUserService;
import ru.gb.runov.springsecurity.services.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@Profile("dao")
@Slf4j
@RequiredArgsConstructor
public class DaoController {

    private final DaoUserService userService;

    private Logger logger = LoggerFactory.getLogger(DaoSecurityConfig.class.getName());

 //   @GetMapping("/")
 //   public String homePage() {
 //       return "home (DAO controller)";
 //   }

    @GetMapping("/dao")
    public String daoTestPage(Principal principal) {
        logger.info("****Dao AdaoTestPage");
        DaoUser user = userService.findByUsername(principal.getName()).orElseThrow(() -> new RuntimeException("unable to find a user by username: " + principal.getName()));
        user.setScope(1+user.getScope());
        userService.saveOrUpdate(user);
        return "authenticated: " + user.getUsername() + " Scope: " + user.getScope();
    }
    /*

			GET .../app/score/inc - увеличивает балл текущего пользователя
			GET .../app/score/dec - уменьшает балл текущего пользователя
			GET .../app/score/get/current - показывает балл вошедшего пользователя
			GET .../app/score/get/{id} - показывает балл пользователя с указанным id (доступнотолько для залогиненных пользователей)
     */
    @GetMapping("/app/score/inc")
    public String daoInc(Principal principal) {
        logger.info("****Dao daoInc");
        DaoUser user = userService.findByUsername(principal.getName()).orElseThrow(() -> new RuntimeException("unable to find a DAO-user by username: " + principal.getName()));
        user.setScope(1+user.getScope());
        userService.saveOrUpdate(user);
        return "INC authenticated: " + user.getUsername() + " INC.Scope: " + user.getScope();
    }
    @GetMapping("/app/score/dec")
    public String daoDec(Principal principal) {
        logger.info("****Dao daoDec");
        DaoUser user = userService.findByUsername(principal.getName()).orElseThrow(() -> new RuntimeException("unable to find a DAO-user by username: " + principal.getName()));
        user.setScope(user.getScope()-1);
        userService.saveOrUpdate(user);
        return "authenticated: " + user.getUsername() + " DEC.Scope: " + user.getScope();
    }
    @GetMapping("/app/score/get/current")
    public String currentScore(Principal principal) {
        logger.info("****Dao daoDec");
        DaoUser user = userService.findByUsername(principal.getName()).orElseThrow(() -> new RuntimeException("/app/score/get/current unable to find a DAO-user by username: " + principal.getName()));
        return "/app/score/get/current  (" + user.getUsername() + ") Scope: " + user.getScope();
    }
    @GetMapping("/app/score/get/{id}")
    public String currentScoreByID(Principal principal, @PathVariable Long id)  {
        logger.info("****Dao get/{id}");
        DaoUser user = userService.findById(id).orElseThrow(() -> new RuntimeException("/app/score/get/current unable to find a DAO-user by user iD: " + id.toString()));
        return "/app/score/get/ID  (" + user.getUsername() + ") Scope: " + user.getScope();
    }

}
