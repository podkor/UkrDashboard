CREATE TABLE IF NOT EXISTS user
(
    id       int AUTO_INCREMENT PRIMARY KEY,
    username varchar(255) not null,
    password varchar(255) null,
    email    varchar(255) not null,
    role     varchar(255) not null,
    CONSTRAINT idx_user_email UNIQUE (email),
    CONSTRAINT idx_user_username UNIQUE (username)
    );
