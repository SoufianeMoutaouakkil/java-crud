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
    `users` (`id`, `name`, `email`, `password`)
VALUES
    (
        1,
        'Mary Jane',
        'email1@sm.dev',
        'ZK+1KBlUeOf8YxCdxHzU/w==:fVzBYpv0fpPTCK5oq7AiT7xi/GDvxGzP4Sg9ticfG58='
    ),
    (
        2,
        'John Doe',
        'email2@sm.dev',
        'ZK+1KBlUeOf8YxCdxHzU/w==:fVzBYpv0fpPTCK5oq7AiT7xi/GDvxGzP4Sg9ticfG58='
    ),
    (
        3,
        'Soufiane M',
        'email3@sm.dev',
        'ZK+1KBlUeOf8YxCdxHzU/w==:fVzBYpv0fpPTCK5oq7AiT7xi/GDvxGzP4Sg9ticfG58='
    );

UNLOCK TABLES;

-- create task table
DROP TABLE IF EXISTS `tasks`;

CREATE TABLE
    `tasks` (
        `id` int (11) NOT NULL AUTO_INCREMENT,
        `name` varchar(45) DEFAULT NULL,
        `description` varchar(100) DEFAULT NULL,
        `status` varchar(45) DEFAULT 'todo',
        `user_id` int (11) DEFAULT NULL,
        PRIMARY KEY (`id`),
        KEY `user_id_idx` (`user_id`),
        CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
    ) ENGINE = InnoDB AUTO_INCREMENT = 6 DEFAULT CHARSET = latin1;

LOCK TABLES `tasks` WRITE;

INSERT INTO
    `tasks` (`id`, `name`, `description`, `status`, `user_id`)
VALUES
    (
        1,
        'Task 1',
        'Description 1',
        'todo',
        1
    ),
    (
        2,
        'Task 2',
        'Description 2',
        'done',
        1
    ),
    (
        3,
        'Task 3',
        'Description 3',
        'todo',
        2
    ),
    (
        4,
        'Task 4',
        'Description 4',
        'done',
        2
    ),
    (
        5,
        'Task 5',
        'Description 5',
        'done',
        3
    );

UNLOCK TABLES;
