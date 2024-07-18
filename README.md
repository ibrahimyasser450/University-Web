
University Web: Similar to Student Affairs where students can be added, updated, deleted, department selected, students sorted by order andall students viewed.

Key Features:

1. User Sign-Up and Validation: Seamless data entry with comprehensive validation ensures accuracy and integrity.
2. Add New Student: Effortlessly add new students while preventing duplicates and ensuring data quality.
3. Data Storage and Retrieval: Store student information securely in a database and retrieve it with flexible search options.
4. Update Student Details: Empower users to update student information with thorough validation and preservation of existing data.
5. Department Selection: Facilitate department selection for students with rigorous validation criteria.
6. Sorting and Display: Sort and display student data based on various attributes for easy accessibility.
7. Delete Operations: Enable deletion of individual or all student records as needed.

Technologies Used:
1. HTML
2. CSS
3. Java
4. Spring Boot
5. Postman for testing and validation
6. MySQL Workbench to store the students

To run the project on Eclipse or IntelliJ or VS Code IDEs:
1. Download Java EE version 21 (jdk-21)
2. Download Tomcat
3. Add Tomcat to server
4. Import maven project
5. Import data-jpa
6. Import thymeleaf 
7. Import web 
8. Import devtools
9. Import mysql (MySQL Workbench)
10. Run the project
11. Open postman then make request
12. Enter URL, ex. Signup Student: http://localhost:8080/add
13. Choose request, ex. POST
14. Enter data in params
15. Click send

--------------------------------- use the System as admin -----------------------------------

1. sign up student (POST) --> (url) http://localhost:8080/add --> take 8 arguments student id, first name, last name, department(read only), gender, gpa, level and address  --> to create new student. 
2. update student (PUT) --> (url) http://localhost:8080/update?id=student id --> take 8 arguments student id(read only), first name, last name, department, gender, gpa, level and address  --> to update student
3. search for student (POST) --> (url) http://localhost:8080/search --> take 2 arguments search attribute, value --> search attribute such as student id or first name or last name and so on and the value
4. add department for student (POST) --> (url) http://localhost:8080/department --> take 2 arguments student id and department --> add department for this student 
5. display all students (GET) --> (url) http://localhost:8080 --> don't take any arguments --> View all students and they can edit or delete a specific student
6. sort all students (POST) --> (url) http://localhost:8080/sort --> take 2 arguments sort attribute and order --> sort attribute such as student id or first name and so on and the order (Ascending or Descending)
7. delete student (DELETE) --> (url) http://localhost:8080/delete/{id} --> don't take any arguments --> to delete a specific student 
