use testdb;
/* Self Join */

SELECT e.emp_name AS Employee,m.emp_name AS Manager
FROM Employees e
LEFT JOIN Employees m
ON e.manager_id = m.emp_id;

SELECT DISTINCT m.emp_id,m.emp_name
FROM Employees e
JOIN Employees m
ON e.manager_id = m.emp_id;

SELECT e.emp_name AS Employee, m.emp_name AS Mentor
FROM Employees e
INNER JOIN Employees m
ON e.manager_id = m.emp_id;

SELECT m.emp_name AS Manager, e.emp_name AS Employee
FROM Employees e
JOIN Employees m
ON e.manager_id = m.emp_id
ORDER BY m.emp_name;

/* Cross Join */

SELECT c.name AS Customer,p.product_name AS Product
FROM Customers c
CROSS JOIN Products p;

CREATE TABLE Roles(
    role_id INT PRIMARY KEY,
    role_name VARCHAR(100)
);

INSERT INTO Roles VALUES
(1,'Developer'),
(2,'Tester'),
(3,'Manager'),
(4,'Analyst');

SELECT e.emp_name,r.role_name
FROM Employees e
CROSS JOIN Roles r;

SELECT s.student_name,c.course_name
FROM Students s
CROSS JOIN Courses c;

/* Multi-Table JOIN */

SELECT c.name AS Customer,o.order_id,oi.product_id,oi.quantity,o.total_amount
FROM Customers c
JOIN Orders o 
    ON c.customer_id = o.customer_id
JOIN Order_Items oi 
    ON o.order_id = oi.order_id;

SELECT o.order_id,p.product_name,p.price,s.supplier_name,oi.quantity
FROM Orders o
JOIN Order_Items oi 
    ON o.order_id = oi.order_id
JOIN Products p 
    ON oi.product_id = p.product_id
JOIN Suppliers s 
    ON p.supplier_id = s.supplier_id;

CREATE TABLE Locations(
    location_id INT PRIMARY KEY,
    location_name VARCHAR(100)
);

ALTER TABLE Department
ADD location_id INT;

ALTER TABLE Department
ADD FOREIGN KEY (location_id) REFERENCES Locations(location_id);

INSERT INTO Locations VALUES
(1,'Hyderabad'),
(2,'Chennai'),
(3,'Mumbai');

UPDATE Department SET location_id = 1 WHERE dep_id = 1;
UPDATE Department SET location_id = 2 WHERE dep_id = 2;
UPDATE Department SET location_id = 3 WHERE dep_id = 3;

SELECT e.emp_name,d.dep_name,l.location_name
FROM Employees e
JOIN Department d ON e.dep_id = d.dep_id
JOIN Locations l ON d.location_id = l.location_id;

SELECT c.name AS Customer,o.order_id,p.product_name,oi.quantity,p.price,(oi.quantity * p.price) AS Product_Total,s.supplier_name
FROM Customers c
JOIN Orders o 
    ON c.customer_id = o.customer_id
JOIN Order_Items oi 
    ON o.order_id = oi.order_id
JOIN Products p 
    ON oi.product_id = p.product_id
JOIN Suppliers s 
    ON p.supplier_id = s.supplier_id;

SELECT c.name,c.email,o.order_date,o.total_amount,p.product_name,oi.quantity,s.country
FROM Customers c
JOIN Orders o 
    ON c.customer_id = o.customer_id
JOIN Order_Items oi 
    ON o.order_id = oi.order_id
JOIN Products p 
    ON oi.product_id = p.product_id
JOIN Suppliers s 
    ON p.supplier_id = s.supplier_id;

/* JOIN + GROUP BY */


select c.customer_id,COUNT(*) as order_count from Orders as o 
join Customers as c on 
o.customer_id = c.customer_id group by c.customer_id order by c.customer_id 

select c.customer_id,c.name as customer_name,SUM(o.total_amount) as total_order_amount from Customers as c
join Orders as o on 
c.customer_id = o.customer_id 
group by c.customer_id, c.name;

select d.dep_id,Count(*) as count_per_dep from Department as d 
join Employees as e on 
d.dep_id = e.dep_id 
group by d.dep_id

select p.product_id, Count(ot.order_id) as product_count from products as p
left join order_items as ot 
on p.product_id = ot.product_id group by p.product_id

select d.dep_id,sum(e.salary) as total from Employees as e 
join Department as d on 
e.dep_id = d.dep_id group by d.dep_id






