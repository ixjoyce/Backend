use testdb;

INSERT INTO Customers VALUES
(1,'Arjun','arjun123@gmail.com',888888881),
(2,'Meera','meera.k@gmail.com',888888882),
(3,'Vikram','vikram07@gmail.com',888888883),
(4,'Ananya','ananya.p@gmail.com',888888884),
(5,'Rahul','rahul_dev@gmail.com',888888885),
(6,'Sneha','sneha.m@gmail.com',888888886),
(7,'Kiran','kiran.tech@gmail.com',888888887),
(8,'Divya','divya22@gmail.com',888888888),
(9,'Aditya','aditya.work@gmail.com',888888889);


INSERT INTO Orders 
(order_id, order_number, customer_id, order_date, total_amount)
VALUES
(1,201,2,'2026-02-01',8000),
(2,202,3,'2026-02-02',5500),
(3,203,4,'2026-02-03',6200),
(4,204,5,'2026-02-04',7100),
(5,205,6,'2026-02-05',4800),
(6,206,2,'2026-02-06',3900),
(7,207,7,'2026-02-07',8600),
(8,208,8,'2026-02-08',2700),
(9,209,9,'2026-02-09',9400);


INSERT INTO Department VALUES
(1,'IT'),
(2,'Accounts'),
(3,'Operations'),
(4,'Customer Support'),
(5,'Research & Development');


INSERT INTO Employees VALUES
(201,'Rahul','rahul@company.com',1,82000,NULL),
(202,'Sneha','sneha@company.com',1,60000,201),
(203,'Vikram','vikram@company.com',2,73000,NULL),
(204,'Pooja','pooja@company.com',2,54000,203),
(205,'Nikhil','nikhil@company.com',3,68000,NULL),
(206,'Aisha','aisha@company.com',3,50000,205),
(207,'Manoj','manoj@company.com',4,59000,NULL),
(208,'Ritika','ritika@company.com',4,44000,207),
(209,'Karthik','karthik@company.com',5,75000,NULL),
(210,'Neha','neha@company.com',5,47000,209);

INSERT INTO Suppliers VALUES
(6,'NextGen Supplies','help@nextgen.com',9123456780,'Canada'),
(7,'Prime Tech','contact@primetech.com',9234567810,'India'),
(8,'Vision Electronics','support@vision.com',9345678910,'UK'),
(9,'Future Devices','info@futuredevices.com',9456789101,'Singapore'),
(10,'Elite Components','sales@elitecomponents.com',9567890123,'Japan');

select * from Products;
SELECT * FROM Suppliers;


INSERT INTO Products 
(product_id, product_name, price, supplier_id)
VALUES
(1,'Tablet',30000,6),
(2,'Printer',15000,7),
(3,'Router',2500,8),
(4,'External Hard Drive',5000,9),
(5,'Smartphone',40000,10);



INSERT INTO Order_Items (order_id, product_id, quantity)
VALUES
(1,1,2),
(1,2,1),
(2,3,3),
(3,4,1),
(4,5,2);

select * from Customers
select * from Orders
SELECT * FROM Order_Items;
select * from Employees
select * from Department
select * from products
select * from Suppliers
select * from Students;
CREATE TABLE Students(
    student_id INT PRIMARY KEY,
    student_name VARCHAR(100) NOT NULL
);

CREATE TABLE Courses(
    course_id INT PRIMARY KEY,
    course_name VARCHAR(100) NOT NULL
);

CREATE TABLE Student_Courses(
    student_id INT,
    course_id INT,
    PRIMARY KEY(student_id, course_id),
    FOREIGN KEY(student_id) REFERENCES Students(student_id),
    FOREIGN KEY(course_id) REFERENCES Courses(course_id)
);


INSERT INTO Students VALUES
(1,'Arjun'),
(2,'Meera'),
(3,'Rahul'),
(4,'Sneha'),
(5,'Vikram');


INSERT INTO Courses VALUES
(101,'Database Systems'),
(102,'Operating Systems'),
(103,'Computer Networks'),
(104,'Java Programming'),
(105,'Data Structures');

INSERT INTO Student_Courses VALUES
(1,101),
(1,104),
(2,101),
(2,105),
(3,102),
(3,103),
(4,104),
(5,101),
(5,103);

