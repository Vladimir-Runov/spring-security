DROP TABLE IF EXISTS prodmag.usersJDBC CASCADE;
CREATE TABLE prodmag.usersJDBC
(
    username VARCHAR(255) not null unique,
    password VARCHAR(255) not null,
    enabled boolean not null
);

DROP TABLE IF EXISTS prodmag.authorities CASCADE;
create table prodmag.authorities (
    username varchar(255) not null,
    authority varchar(255) not null,
    foreign key (username) references prodmag.usersJDBC (username),
    unique (username, authority)
);

insert into prodmag.usersJDBC (username, password, enabled) values
('admin1', '{bcrypt}$2y$12$mFUdPh8.ESnhu.eyDjxrYuSigUIOboDP94mt7vuNhf604Yw0iuKQa', true);

insert into prodmag.authorities (username, authority) values ('admin1', 'ROLE_ADMIN');


