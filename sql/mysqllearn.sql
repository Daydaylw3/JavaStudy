## 创建联结
select vend_name, prod_name, prod_price
from vendors, products
where vendors.vend_id = products.vend_id
order by vend_name, prod_name;
## 内部联结
select vend_name, prod_name, prod_price
from vendors inner join products
on vendors.vend_id = products.vend_id
order by vend_name, prod_name;
## 联结多个表
select prod_name, vend_name, prod_price, quantity
from orderitems, products, vendors
where products.vend_id = vendors.vend_id
and orderitems.prod_id = products.prod_id
and order_num = 20005
order by vend_name, prod_name;
##
select cust_name, cust_contact
from customers
where cust_id in (
	select cust_id
	from orders
	where order_num in (
		select order_num
		from orderitems
		where prod_id = 'TNT2'
	)
);
## 使用联结
select cust_name, cust_contact
from customers, orders, orderitems
where orderitems.prod_id = 'TNT2'
and orderitems.order_num = orders.order_num
and orders.cust_id = customers.cust_id;

select cust_name, cust_contact
from customers inner join orders, 
orders inner join orderitems
on orderitems.order_num = orders.order_num
on orders.cust_id = customers.cust_id;
## 自联结
select prod_id, prod_name
from products
where vend_id = (
	select vend_id
	from products
	where prod_id='DTNTR'
);
select p1.prod_id, p1.prod_name
from products p1, products p2
where p1.vend_id = p2.vend_id
and p2.prod_id='DTNTR';
## 自然联结, 应该至少有一个列出现在不止一个表中, 可能会有相同的列多次出现, 自然联结排除多次出现, 使得
## 每个列只出现一次
## 你只能选择那些唯一的列. 对表使用通配符(select *), 对其他表的列使用明确的子句
select c.*, o.order_num, o.order_date, oi.prod_id, oi.quantity, oi.item_price
from customers c, orders o, orderitems oi
where c.cust_id = o.cust_id
and oi.order_num = o.order_num
and prod_id = 'FB';
## 外部联结: 联结包括了那些在相关表中没有包含的行
## 这是一个简单的内联
select customers.cust_id, orders.order_num
from customers inner join orders
on customers.cust_id = orders.cust_id;
## 外联, left指定了outer join左边为包括了所有行的表
select customers.cust_id, orders.order_num
from customers left outer join orders
on customers.cust_id = orders.cust_id;
select customers.cust_id, orders.order_num
from customers right outer join orders
on customers.cust_id = orders.cust_id;
## 使用带聚集函数的联结
## 内联
select customers.cust_name, customers.cust_id,
count(orders.order_num) num_ord
from customers inner join orders
on customers.cust_id = orders.cust_id
group by customers.cust_id;
## 外联
select customers.cust_name, customers.cust_id,
count(orders.order_num) num_ord
from customers left outer join orders
on customers.cust_id = orders.cust_id
group by customers.cust_id;
## 使用union
## 先使用单条语句
select vend_id, prod_id, prod_price
from products
where prod_price <= 5;
select vend_id, prod_id, prod_price 
from products 
where vend_id in (1001, 1002);
## 再使用union连接两条单独的查询
select vend_id, prod_id, prod_price
from products
where prod_price <= 5
union
select vend_id, prod_id, prod_price 
from products 
where vend_id in (1001, 1002);
## 不使用union达到相同的效果
select vend_id, prod_id, prod_price
from products
where prod_price <= 5 or vend_id in (1001, 1002);
## 取消或者包含重复的行
select vend_id, prod_id, prod_price
from products
where prod_price <= 5
union all
select vend_id, prod_id, prod_price 
from products 
where vend_id in (1001, 1002);
## 对组合查询结果排序
## 只能在最后一条select子句之后添加order by语句
## 对整个查询结果集进行排序
select vend_id, prod_id, prod_price
from products
where prod_price <= 5
union 
select vend_id, prod_id, prod_price 
from products 
where vend_id in (1001, 1002)
order by vend_id;
## 19章 插入数据
## 举一个例子
insert into customers
values (null, 'Pep E. Lapew', 
	'100 Main Street', 'Los Angeles', 
	'CA', '90046', 'USA', null, null);
## 编写insert语句的更安全的方法如下:
insert into customers (cust_name, cust_address,
cust_city, cust_state, cust_zip, cust_country, cust_contact, cust_email)
values ('Pep E. Lapew', '100 Main Street', 'Los Angeles', 
	'CA', '90046', 'USA', null, null);
## 插入多个行
INSERT INTO customers (cust_name, cust_address,
cust_city, cust_state, cust_zip, cust_country)
VALUES('Pep E. LaPew', '100 Main Street',
'Los Angeles', 'CA', '90046', 'USA');
INSERT INTO customers (cust_name, cust_address,
cust_city, cust_state, cust_zip, cust_country)
VALUES('M. Martian', '42 Galaxy Way', 'New York',
'NY', '11213', 'USA');
## 或者只要每条insert语句中的列名(和次序)相同, 可以如下组合各个语句
INSERT INTO customers (cust_name, cust_address,
cust_city, cust_state, cust_zip, cust_country)
VALUES('Pep E. LaPew', '100 Main Street',
'Los Angeles', 'CA', '90046', 'USA'),
('M. Martian', '42 Galaxy Way', 'New York',
'NY', '11213', 'USA');
## 上面此技术可以提高数据库处理的性能, 
## 因为MySQL用单条INSERT语句处理多个插入比使用多条INSERT语句快。
