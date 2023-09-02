# Online-Bookstore-Management-System
System that have two roles (admin,user) the user can browse for available books and can borrow some books with manage the borrowing and return dates. and Admin can add,remove,update the books.

#Technology
-Springboot for Backend RESTful APIs.
-JWT Token for Security.
-JPA/Hibrnate for data access.
-Oracle SQL for Database.


# Business

# Admin
- add book 
- update book
- Delete book (Delete completelty from database)
- remove book (Remove is just not show in available books)

# User
- can browse all avaiable books
- can see the categories of books in the store
- can browse books by category
- can borrow books

# Postman Collection
- path: src/main/resources/bookstore.postman_collection.json


# DATABASE DIAGRAM
(note: you can check oracle sql code for the database from this path: /src/main/resources/bookstore.sql)
<img src = "/src/main/resources/database_diagram.PNG" height="500" width="500">


