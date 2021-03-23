CREATE TABLE users
(
    `id`       INT          NOT NULL,
    `username` VARCHAR(20)  NOT NULL,
    `password` VARCHAR(100) NOT NULL,
    `score`    INT          NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE roles
(
    `id`   INT         NOT NULL,
    `name` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE users_roles
(
    `user_id` INT NOT NULL,
    `role_id` INT NOT NULL,
    CONSTRAINT `uq` UNIQUE (user_id, role_id),
    CONSTRAINT `us_id`
        FOREIGN KEY (`user_id`)
            REFERENCES `security`.`users` (`id`),
    CONSTRAINT `r_id`
        FOREIGN KEY (`role_id`)
            REFERENCES `security`.`roles` (`id`)
);

INSERT INTO users(id, username, password, score)
VALUES (1, 'user1', '$2y$12$ShPpZKdk25GU1mNmtt8xqOIJw0R2hENA0ziZk1Jd92RBzYddR8EVm', 10),
       (2, 'user2', '$2y$12$dIzCx4ZgO1RynpU1Q7ARpu/cmtIhuJeiXHOms.H7Z3u0Sv5/bzNhW', 8),
       (3, 'user3', '$2y$12$bTi4JxSC4M/ZUOrwoWsqF.BMmZ7cJ72cRaZ6oJjxkpXwz5ynkOmnW', 3),
       (4, 'admin', '$2y$12$akZyBZEZvfJzxNC7X8qKquaTs/ynmwnCDPi0Y2SwpWFaLpwZnSMqK', 1000);

INSERT INTO roles(id, name)
VALUES (1, 'ROLE_USER'),
       (2, 'ROLE_ADMIN');

INSERT INTO users_roles (user_id, role_id)
VALUES (1, 1),
       (2, 1),
       (3, 1),
       (4, 1),
       (4, 2);

