CREATE TABLE IF NOT EXISTS PROJECTS(
	`id` INT NOT NULL,
    `name` VARCHAR(100) NOT NULL UNIQUE,
    `description` TEXT NOT NULL,
    `course` VARCHAR(25),
    `startdate` DATE NOT NULL,
    `enddate` DATE,
    `url` VARCHAR(255),
    PRIMARY KEY(`id`)
);