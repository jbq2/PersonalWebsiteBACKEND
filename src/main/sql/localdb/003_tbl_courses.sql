USE localdb;

CREATE TABLE IF NOT EXISTS courses(
	`id` INT NOT NULL AUTO_INCREMENT,
	`code` VARCHAR(10) UNIQUE NOT NULL,
    `title` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`id`)
);

INSERT INTO localdb.courses (`code`, `title`)
VALUES ('CS280', 'Programming Language Concepts');

INSERT INTO localdb.courses (`code`, `title`)
VALUES ('CS331', 'Database System Design and Management');

INSERT INTO localdb.courses (`code`, `title`)
VALUES ('IT202', 'Internet Applications');