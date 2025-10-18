# Student-Management-System
A simple system to manage student records - display student information and it is created using JDBC and SQL.  
Student Management System

A simple Java console application to manage student records using JDBC and SQL. The system allows you to add, update, delete, and display student information stored in a MySQL database.

💡 Features

Add Student – Insert a new student record into the database.

Update Student – Modify an existing student’s details.

Delete Student – Remove a student record from the database.

Display Students – View all student records in a tabular format.

🛠️ Technologies Used

Java – Programming language

JDBC – Java Database Connectivity

MySQL – Relational Database

SQL – Queries for CRUD operations

📁 Database Setup

Create a database:

CREATE DATABASE StudentDB;


Use the database:

USE StudentDB;


Create the students table:

CREATE TABLE students (
    id INT PRIMARY KEY,
    name VARCHAR(50),
    course VARCHAR(50),
    marks INT
);
