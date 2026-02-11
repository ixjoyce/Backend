use testdb;
/* JOIN + HAVING */

select c.customer_id,COUNT(*) as order_count from Orders as o 
join Customers as c on 
o.customer_id = c.customer_id group by c.customer_id having count(*)>3

select d.dep_id,d.dep_name,count(*) as employees_count from Employees as e 
join Department as d 
on 
e.dep_id = d.dep_id group by d.dep_id,d.dep_name having count(*) >5

select p.product_id,p.product_name,count(*) as order_count from products as p
join order_items as ot on 
p.product_id = ot.product_id group by p.product_id,p.product_name having count(*)>1

select c.customer_id,c.name,sum(o.total_amount) from Customers as c
join Orders as o on 
c.customer_id = o.customer_id
group by c.customer_id,c.name having sum(o.total_amount) > 2500

/* JOIN + COUNT */

select c.customer_id,COUNT(*) as order_count from Orders as o 
join Customers as c on 
o.customer_id = c.customer_id group by c.customer_id order by c.customer_id 

select d.dep_id,Count(*) as count_per_dep from Department as d 
join Employees as e on 
d.dep_id = e.dep_id 
group by d.dep_id

select p.product_id, Count(*) as product_count from products as p
join order_items as ot 
on p.product_id = ot.product_id group by p.product_id

/* JOIN + WHERE */

select * from Customers as c 
join Orders as o on 
c.customer_id = o.customer_id where order_date > '2026-01-05'


select * from Employees as e 
join Department as d on 
e.dep_id  = d.dep_id where dep_id 

select * from Order_Items as ot 
join Products as p on 
ot.product_id = p.product_id where p.price >1000

/* JOIN + LIKE */

select * from Customers as c 
join Orders as o on 
c.customer_id = o.customer_id 
where c.name like 's%'

select * from Employees as e 
join Department as d on 
e.dep_id = d.dep_id where e.emp_name like 'A%' or d.dep_name like 'E%'

select * from Products as p 
join Order_Items as ot on 
p.product_id = ot.product_id where p.price>1000 order by p.product_id

/* JOIN + Constraints  */

insert into Orders values 
(11,121,26,'2026-01-22',900)

select * from Employees as e 
join Department as d on 
e.dep_id = d.dep_id where e.dep_id is not null

/*  JOIN + Subquery Tasks */

select * from Customers
where customer_id in (select customer_id from Orders)

select * from Customers
where customer_id not in (select customer_id from Orders)


select * from Products 
where product_id not in (select product_id from Order_Items)

select * from Employees
where dep_id not in (select dep_id from Department)

select * from Orders as o 
where total_amount > (select AVG(total_amount) from Orders)


select * from Customers as c
join Orders as o on 
c.customer_id=o.customer_id
where o.total_amount > (select AVG(total_amount) from Orders)


select * from Employees as e1
where e1.salary >
(select AVG(e2.salary) from Employees as e2 
where e2.dep_id=e1.dep_id)


select * from Department
where dep_id in (select dep_id from Department)


alter table orders add status varchar(50)

update orders 
set status ='completed' where order_id in (1,2,3,6,7)

update orders 
set status ='cancelled' where order_id in (4,5,8,9)

select * from Orders


select * from Customers 
where customer_id not in (select customer_id from Orders where status ='cancelled')


select * from Employees as e 
join Department as d on 
e.dep_id = d.dep_id and 
e.salary >(select AVG(e2.salary) from Employees as e2 where e2.dep_id=d.dep_id)


select c.customer_id,c.name,t.total_amount
from Customers c join
(select customer_id, SUM(total_amount) AS total_amount from Orders group by customer_id) as t on 
c.customer_id = t.customer_id


select * from Customers as c where exists (select * from Orders as o where o.customer_id=c.customer_id)


select * from Customers as c where not exists (select * from Orders as o where o.customer_id=c.customer_id)


select * from Employees as e
where salary >
(select AVG(salary) from Employees where dep_id = e.dep_id)


select * from Employees as e
join 
(select dep_id,count(*) as employees_total from Employees group by dep_id) as emp_count on
e.dep_id = emp_count.dep_id