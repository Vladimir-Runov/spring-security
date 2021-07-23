package ru.gb.runov.springsecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringSecurityApplication {
/*
1. Создайте новый проект с spring-boot + spring-security (все на RestController'ах делаем).
Форму входа используем стандартную (formLogin()), подключите туда DaoAuthentication
2. Для каждого пользователя сделайте поле Score в котором указывается некий балл пользователя

			GET .../app/score/inc - увеличивает балл текущего пользователя
			GET .../app/score/dec - уменьшает балл текущего пользователя
			GET .../app/score/get/current - показывает балл вошедшего пользователя
			GET .../app/score/get/{id} - показывает балл пользователя с указанным id (доступнотолько для залогиненных пользователей)
*/

	public static void main(String[] args) {

		SpringApplication.run(SpringSecurityApplication.class, args);
	}


}
