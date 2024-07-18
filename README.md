                                                            
 University Web



To run the project on Eclipse or IntelliJ or VS Code IDEs:
1. Download Java EE version 21 (jdk-21)
2. Download Tomcat
3. Add Tomcat to server
4. Import maven project
5. Import data-jpa
6. Import thymeleaf 
7. Import web 
8. Import devtools
9. Import mysql
 10. Run the project
 11. Open postman then make request
 12. Enter URL, ex. Signup Student: http://localhost:8080/add
 13. Choose request, ex. POST
 14. Enter data in params
 15. Click send

--------------------------------- use the System as admin -----------------------------------


1. sign up student (POST) --> (url) http://localhost:8080/add --> take 8 arguments student id, first name, last name, department(read only), gender, gpa, level and address  --> to create new student 
2. update student (PUT) --> (url) http://localhost:8080/update?id=student id --> take 8 arguments student id(read only), first name, last name, department, gender, gpa, level and address  --> to update student
3. search for student (POST) --> (url) http://localhost:8080/search --> take 2 arguments search attribute, value --> search attribute such as student id or first name or last name and so on and the value
4. add department for student (POST) --> (url) http://localhost:8080/department --> take 2 arguments student id and department --> add department for this student 
5. display all students (GET) --> (url) http://localhost:8080 --> don't take any arguments --> View all students and they can edit or delete a specific student
6. sort all students (POST) --> (url) http://localhost:8080/sort --> take 2 arguments sort attribute and order --> sort attribute such as student id or first name and so on and the order (Ascending or Descending)
7. delete student (DELETE) --> (url) http://localhost:8080/delete/{id} --> don't take any arguments --> to delete a specific student 
