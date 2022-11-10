DROP TABLE IF EXISTS course_student;
DROP TABLE IF EXISTS course;
DROP TABLE IF EXISTS teacher;
DROP TABLE IF EXISTS student;
DROP TABLE IF EXISTS account;
DROP TABLE IF EXISTS topic;

CREATE TABLE account(
    id INT PRIMARY KEY AUTO_INCREMENT,
    login VARCHAR(30) UNIQUE,
    password VARCHAR(20) UNIQUE
);

CREATE TABLE teacher(
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(20) NOT NULL,
    lastname VARCHAR(20) NOT NULL,
    accountId INT NOT NULL,
    FOREIGN KEY (accountId) REFERENCES account(id)
);

CREATE TABLE student(
    id INT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(20) NOT NULL,
    last_name VARCHAR(20) NOT NULL,
    is_blocked BOOL NOT NULL DEFAULT FALSE,
    account_id INT NOT NULL,
    FOREIGN KEY (account_id) REFERENCES account(id)
);

CREATE TABLE topic(
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(30)
);

CREATE TABLE course(
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL UNIQUE,
    start_date DATE NOT NULL,
    duration SMALLINT NOT NULL,
    teacher_id INT NOT NULL,
    topic_id INT NOT NULL,
    FOREIGN KEY (teacher_id) REFERENCES teacher(id),
    FOREIGN KEY (topic_id) REFERENCES topic(id)
);

CREATE TABLE course_student(
    id INT PRIMARY KEY AUTO_INCREMENT,
    course_id INT NOT NULL,
    student_id INT NOT NULL,
    grade SMALLINT(100),
    registration_date DATE NOT NULL,
    UNIQUE(course_id, student_id),
    FOREIGN KEY (course_id) REFERENCES course(id),
    FOREIGN KEY (student_id) REFERENCES student(id)
);

INSERT INTO account(login, password) VALUES('admin', 'admin');