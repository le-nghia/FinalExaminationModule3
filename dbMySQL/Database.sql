create database Module3;
use Module3;

CREATE TABLE IF NOT EXISTS Classroom (
    classID INT PRIMARY KEY AUTO_INCREMENT,
    nameClass VARCHAR(255) NOT NULL
);
CREATE TABLE IF NOT EXISTS Student (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nameStudent VARCHAR(255) NOT NULL,
    email VARCHAR(255),
    dob DATE,
    address VARCHAR(255),
    phoneNumber VARCHAR(20),
    classID INT,
    FOREIGN KEY (classID) REFERENCES Classroom(classID)
);

select * from student INNER JOIN classroom ON student.classID = classroom.classID ;