DROP TABLE IF EXISTS journal;
DROP TABLE IF EXISTS course;
DROP TABLE IF EXISTS account;
DROP TABLE IF EXISTS role;
DROP TABLE IF EXISTS topic;

CREATE TABLE role
(
    id   INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(30) NOT NULL UNIQUE
);

CREATE TABLE account
(
    id         INT PRIMARY KEY AUTO_INCREMENT,
    login      VARCHAR(50) NOT NULL UNIQUE,
    password   VARCHAR(64) NOT NULL UNIQUE,
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
    name       VARCHAR(50) NOT NULL UNIQUE,
    start_date DATE        NOT NULL,
    end_date   DATE        NOT NULL,
    topic_id   INT         NOT NULL,
    teacher_id INT,
    FOREIGN KEY (teacher_id) REFERENCES account (id) ON DELETE CASCADE,
    CHECK ( end_date > start_date )
);

CREATE TABLE journal
(
    id              INT PRIMARY KEY AUTO_INCREMENT,
    grade           INT(100) DEFAULT -1,
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

INSERT INTO topic(name) VALUES('History');
INSERT INTO topic(name) VALUES('Data Science');
INSERT INTO topic(name) VALUES('Software development');
INSERT INTO topic(name) VALUES('Math');
INSERT INTO topic(name) VALUES('Mobile development');

INSERT INTO account(login, password, role_id) VALUES('admin', 'admin', 2);
