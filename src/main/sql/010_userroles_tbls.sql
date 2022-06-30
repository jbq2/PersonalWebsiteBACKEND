USE localdb;

CREATE TABLE users(
	id INT NOT NULL AUTO_INCREMENT,
    username VARCHAR(25) UNIQUE NOT NULL,
    password VARCHAR(50) NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE roles(
	id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(25) UNIQUE NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE userhasrole(
	id INT NOT NULL AUTO_INCREMENT,
    user_id INT NOT NULL,
    role_id INT NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (role_id) REFERENCES roles(id),
    UNIQUE(user_id, role_id)
);

INSERT INTO roles (name)
VALUES ('ADMIN');

INSERT INTO roles (name)
VALUES ('USER')