DROP TABLE IF EXISTS USERS;

CREATE TABLE IF NOT EXISTS USERS (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(100) NOT NULL,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    birthday TIMESTAMP NOT NULL,
    age INTEGER NOT NULL,
    gender VARCHAR(6) NOT NULL,
    registration_date TIMESTAMP NOT NULL,
    is_admin BOOLEAN NOT NULL,
    is_vip BOOLEAN NOT NULL,
    is_test BOOLEAN NOT NULL,
    is_suspended BOOLEAN NOT NULL
);
