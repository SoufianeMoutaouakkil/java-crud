CREATE DATABASE IF NOT EXISTS `crud_demo`;

USE `crud_demo`;

DROP TABLE IF EXISTS `users`;

CREATE TABLE
    `users` (
        `id` int (11) NOT NULL AUTO_INCREMENT,
        `name` varchar(45) DEFAULT NULL,
        `email` varchar(45) DEFAULT NULL,
        `password` varchar(100) DEFAULT NULL,
        PRIMARY KEY (`id`)
    ) ENGINE = InnoDB AUTO_INCREMENT = 6 DEFAULT CHARSET = latin1;

LOCK TABLES `users` WRITE;

-- password is 'Password0' for all users
INSERT INTO
    `users` (`name`, `email`, `password`)
VALUES
    (
        'Mary Jane',
        'email1@sm.dev',
        'ZK+1KBlUeOf8YxCdxHzU/w==:fVzBYpv0fpPTCK5oq7AiT7xi/GDvxGzP4Sg9ticfG58='
    ),
    (
        'John Doe',
        'email2@sm.dev',
        'ZK+1KBlUeOf8YxCdxHzU/w==:fVzBYpv0fpPTCK5oq7AiT7xi/GDvxGzP4Sg9ticfG58='
    ),
    (
        'Soufiane M',
        'email3@sm.dev',
        'ZK+1KBlUeOf8YxCdxHzU/w==:fVzBYpv0fpPTCK5oq7AiT7xi/GDvxGzP4Sg9ticfG58='
    );

UNLOCK TABLES;