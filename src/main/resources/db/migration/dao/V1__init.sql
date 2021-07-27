DROP TABLE IF EXISTS prodmag.usersDAO CASCADE;
CREATE TABLE prodmag.usersdao
(
    id bigserial NOT NULL,
    username VARCHAR(30) not null,
    password VARCHAR(255) not null,

    scope                 INT not null,
    email                 VARCHAR(50) unique,

    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS prodmag.roles CASCADE;
create table prodmag.roles (
  id                    serial,
  name                  varchar(50) not null,
  primary key (id)
);

DROP TABLE IF EXISTS prodmag.users_roles CASCADE;
CREATE TABLE prodmag.users_roles (
  user_id               bigint not null,
  role_id               int not null,
  primary key (user_id, role_id),
  foreign key (user_id) references prodmag.usersdao (id),
  foreign key (role_id) references prodmag.roles (id)
);




insert into prodmag.roles (id,name) values
(1,'ROLE_USER'), (2,'ROLE_ADMIN');

insert into prodmag.usersdao (username, password, email, scope) values
('userAdmin', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'userAdmin@gmail.com',11),
('user', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'userAdmin@gmail.com',11);

insert into prodmag.users (username, password, email, scope) values
('userAdmin', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'userAdmin@gmail.com',11),
('user', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'userAdmin@gmail.com',11);


insert into prodmag.users_roles (user_id, role_id) values
(1, 1), 
(1, 2),
(2, 1);