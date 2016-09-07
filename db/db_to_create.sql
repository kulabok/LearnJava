create database learnjava;

use learnjava;

CREATE TABLE learnjava.user
(
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL,
    login VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL,
    isadmin BOOLEAN NOT NULL
);
CREATE UNIQUE INDEX user_id_uindex ON learnjava.user (id);

CREATE TABLE learnjava.article
(
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    branch VARCHAR(30) NOT NULL,
    title VARCHAR(200) NOT NULL
);
CREATE UNIQUE INDEX article_id_uindex ON learnjava.article (id);