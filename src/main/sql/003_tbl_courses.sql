USE localdb;

CREATE TABLE IF NOT EXISTS courses(
	code VARCHAR(10) NOT NULL,
    title VARCHAR(255) NOT NULL,
    PRIMARY KEY (code)
);

INSERT INTO localdb.courses
VALUES ('CS280', 'Programming Language Concepts');

INSERT INTO localdb.courses
VALUES ('CS331', 'Database System Design and Management');

INSERT INTO localdb.courses
VALUES ('IT202', 'Internet Applications');