DROP TABLE IF EXISTS role;
DROP TABLE IF EXISTS account;
DROP TABLE IF EXISTS topic;
DROP TABLE IF EXISTS course;
DROP TABLE IF EXISTS journal;

CREATE TABLE role
(
    id   INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(30) NOT NULL UNIQUE
);

CREATE TABLE account
(
    id         INT PRIMARY KEY AUTO_INCREMENT,
    email      VARCHAR(50) NOT NULL UNIQUE,
    password   VARCHAR(25) NOT NULL UNIQUE,
    first_name VARCHAR(20),
    last_name  VARCHAR(25),
    is_blocked BOOLEAN     NOT NULL DEFAULT FALSE,
    role_id    INT         NOT NULL,
    FOREIGN KEY (role_id) REFERENCES role (id) ON DELETE CASCADE
);

CREATE TABLE topic
(
    id   INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE course
(
    id         INT PRIMARY KEY AUTO_INCREMENT,
    name       VARCHAR(50) NOT NULL,
    duration   SMALLINT    NOT NULL,
    start_date DATE        NOT NULL,
    topic_id   INT         NOT NULL,
    teacher_id INT         NOT NULL,
    FOREIGN KEY (teacher_id) REFERENCES account (id) ON DELETE CASCADE
);

CREATE TABLE journal
(
    id              INT PRIMARY KEY AUTO_INCREMENT,
    grade           INT(100),
    enrollment_date DATE NOT NULL,
    course_id       INT  NOT NULL,
    student_id      INT  NOT NULL,
    FOREIGN KEY (course_id) REFERENCES course (id) ON DELETE CASCADE,
    FOREIGN KEY (student_id) REFERENCES account (id) ON DELETE CASCADE,
    UNIQUE (course_id, student_id)
);

INSERT INTO role(name) VALUES ('Student');
INSERT INTO role(name) VALUES ('Admin');
INSERT INTO role(name) VALUES ('Teacher');