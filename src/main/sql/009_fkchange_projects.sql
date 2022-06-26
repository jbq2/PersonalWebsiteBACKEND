ALTER TABLE localdb.projects
ADD course_id INT;

ALTER TABLE localdb.projects
ADD FOREIGN KEY (course_id) REFERENCES localdb.courses(id)
ON UPDATE CASCADE
ON DELETE SET NULL;

ALTER TABLE localdb.projects
DROP FOREIGN KEY projects_ibfk_1;

UPDATE localdb.projects
SET course_id = 1
WHERE id = 1;

UPDATE localdb.projects
SET course_id = 3
WHERE id = 2;

UPDATE localdb.projects
SET course_id = 2
WHERE id = 3;

ALTER TABLE localdb.projects
DROP COLUMN course;