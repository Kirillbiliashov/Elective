# Elective
The project represents courses app with 3 roles: Admin, Teacher and Student. Each of them has personal account that they can log in to using email/username and password.

## Database schema

<img src="images/schema.png">

### Admin can do the following actions:
* Register teachers and assign courses to them
* Create, edit, delete courses
* Select certain courses by different criteria. This implies sort and filter by teacher or topic
* Block/unlock the students
* Sign in to admin account using login 'administrator' and password 'Admin123'

### Teacher:
* Has personal account that displays info about courses he/she teaches
* Can students assigned to courses
* Can grade students' courses

### Student
* When logs in, available courses (i.e. courses they can join) are displayed
* Can view registered courses
* Can view ongoing courses
* Can view completed courses and their grades
* If blocked, cannot log in

## Technologies used
* Spring MVC
* Spring Data JPA (with Hibernate as an implementation)
* Hibernate Validator
* Spring security (form-based authentication, authorization, password encryption, csrf)
* Model Mapper
* Thymeleaf
