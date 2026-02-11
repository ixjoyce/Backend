use testdb;
/* Inner Joins */

SELECT * FROM Customers c
INNER JOIN Orders o
ON c.customer_id = o.customer_id;

SELECT e.emp_name,d.dep_name, e.salary
FROM Employees e
INNER JOIN Department d
ON e.dep_id = d.dep_id;

SELECT o.order_id,c.name,p.product_name,oi.quantity
FROM Orders o
INNER JOIN Customers c ON o.customer_id = c.customer_id
INNER JOIN Order_Items oi ON o.order_id = oi.order_id
INNER JOIN Products p ON oi.product_id = p.product_id;

SELECT c.name AS Customer_Name,c.email,o.order_number,o.order_date,o.total_amount
FROM Customers c
INNER JOIN Orders o
ON c.customer_id = o.customer_id;

SELECT c.name,
       o.order_number,
       o.total_amount
FROM Customers c
INNER JOIN Orders o
ON c.customer_id = o.customer_id
WHERE o.total_amount > 7000;

SELECT c.name,
       o.order_number
FROM Customers c
INNER JOIN Orders o
ON c.customer_id = o.customer_id
WHERE c.name LIKE 'A%';

/* Left Joins */

SELECT c.customer_id,c.name,o.order_id,o.total_amount
FROM Customers c
LEFT JOIN Orders o
ON c.customer_id = o.customer_id;

SELECT c.customer_id,c.name
FROM Customers c
LEFT JOIN Orders o
ON c.customer_id = o.customer_id
WHERE o.order_id IS NULL;

SELECT d.dep_name,e.emp_name
FROM Department d
LEFT JOIN Employees e
ON d.dep_id = e.dep_id;

SELECT c.name,
       o.total_amount
FROM Customers c
LEFT JOIN Orders o
ON c.customer_id = o.customer_id
WHERE o.total_amount > 5000;

SELECT c.name,
       o.order_id
FROM Customers c
LEFT JOIN Orders o
ON c.customer_id = o.customer_id
WHERE c.name LIKE 'A%';

/* Right joins */

SELECT o.order_id,o.total_amount,c.name
FROM Customers c
RIGHT JOIN Orders o
ON c.customer_id = o.customer_id;


select c.customer_id,c.name,c.email from Orders as o 
right join Customers as c on 
o.customer_id = c.customer_id where o.order_id is null

SELECT d.dep_name,e.emp_name
FROM Employees e
RIGHT JOIN Department d
ON e.dep_id = d.dep_id;

SELECT o.order_id,o.total_amount,c.name
FROM Customers c
RIGHT JOIN Orders o
ON c.customer_id = o.customer_id
WHERE o.total_amount > 7000;

SELECT d.dep_name,COUNT(e.emp_id) AS employee_count
FROM Employees e
RIGHT JOIN Department d
ON e.dep_id = d.dep_id
GROUP BY d.dep_name;

/* FULL OUTER JOIN */

SELECT c.customer_id,c.name,o.order_id,o.total_amount
FROM Customers c
FULL OUTER JOIN Orders o
ON c.customer_id = o.customer_id;


SELECT c.name,o.order_id,
    CASE 
        WHEN c.customer_id IS NOT NULL AND o.order_id IS NOT NULL THEN 'Matched'
        WHEN c.customer_id IS NOT NULL THEN 'Customer Only'
        WHEN o.order_id IS NOT NULL THEN 'Order Only'
    END AS Status
FROM Customers c
FULL OUTER JOIN Orders o
ON c.customer_id = o.customer_id;


SELECT e.emp_name,d.dep_name
FROM Employees e
LEFT JOIN Department d
ON e.dep_id = d.dep_id
UNION
SELECT e.emp_name,d.dep_name
FROM Employees e
RIGHT JOIN Department d
ON e.dep_id = d.dep_id;


select * from Customers as c
left join Orders as o on 
c.customer_id = o.customer_id
union 
select * from Customers as c
right join Orders as o on 
c.customer_id = o.customer_id 









