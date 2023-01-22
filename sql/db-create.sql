DROP TABLE IF EXISTS journal;
DROP TABLE IF EXISTS blocklist;
DROP TABLE IF EXISTS course;
DROP TABLE IF EXISTS account;
DROP TABLE IF EXISTS topic;

CREATE TABLE account
(
    id         INT PRIMARY KEY AUTO_INCREMENT,
    username   VARCHAR(50)                          NOT NULL UNIQUE,
    email      VARCHAR(50) UNIQUE,
    password   VARCHAR(64)                          NOT NULL UNIQUE,
    first_name VARCHAR(20),
    last_name  VARCHAR(25),
    role       ENUM ('Admin', 'Student', 'Teacher') NOT NULL
);

CREATE TABLE topic
(
    id   INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE course
(
    id          INT PRIMARY KEY AUTO_INCREMENT,
    name        VARCHAR(50)   NOT NULL UNIQUE,
    description VARCHAR(1024) NOT NULL,
    start_date  DATE          NOT NULL,
    end_date    DATE          NOT NULL,
    topic_id    INT           NOT NULL,
    teacher_id  INT           NOT NULL,
    FOREIGN KEY (teacher_id) REFERENCES account (id) ON DELETE CASCADE,
    FOREIGN KEY (topic_id) REFERENCES topic (id) ON DELETE CASCADE,
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

CREATE TABLE blocklist
(
    id INT PRIMARY KEY AUTO_INCREMENT,
    student_id INT NOT NULL,
    FOREIGN KEY (student_id) REFERENCES account (id) ON DELETE CASCADE
);

INSERT INTO topic(name) VALUES('History');
INSERT INTO topic(name) VALUES('Data Science');
INSERT INTO topic(name) VALUES('Software development');
INSERT INTO topic(name) VALUES('Math');
INSERT INTO topic(name) VALUES('Mobile development');

INSERT INTO account(username, password, role) VALUES('administrator', '$2a$10$VWVESIu46sFfWNs3jnmYW.J08SgCXnPk3u9.9X86rXKQEbjEiBko.', 'Admin');
