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
## 插入检索出的数据
insert into customers(cust_id, cust_contact, cust_email, cust_name,
cust_address, cust_city, cust_state, cust_zip, cust_country)
select cust_id, cust_contact, cust_email, cust_name, cust_address, 
cust_city, cust_state, cust_zip, cust_country
from custnew;
## INSERT SELECT中SELECT语句可包含WHERE子句以过滤插入的数据
## 20章 更新和删除数据
## 不要省略WHERE子句 在使用UPDATE时一定要注意细心。因为 稍不注意，就会更新表中所有行
## 举一个简单的例子
update customers set cust_email = 'elmer@fudd.com'
where cust_id = 10005;
## 更新多个列
update customers
set cust_name = 'The Fudds',
	cust_email = 'elmer@fudd.com'
where cust_id = 10005;
## 为了删除某个列的值, 可设置它为null(假设该列可以为null)
update customers
set cust_email = null
where cust_id = 10005;
## 删除数据
delete from customers
where cust_id = 10006;
## delete不需要列名或者通配符, 它删除的是整行数据而不是列
## 如果想从表中删除所有行, 不要使用DELETE. 可使用TRUNCATE TABLE语句,
## 它完成相同的工作, 但速度更快(TRUNCATE实际是删除原来的表并重新创建一个
## 表, 而不是逐行删除表中的数据
## 22章 视图
## 视图是虚拟的表。与包含数据的表不一样，视图只包含使用时动态 检索数据的查询
select cust_name, cust_contact
from customers, orders, orderitems
where customers.cust_id = orders.cust_id
and orderitems.order_num = orders.order_num
and prod_id = 'TNT2';
## 现在，假如可以把整个查询包装成一个名为productcustomers的虚拟表，则可以
## 如下轻松地检索出相同的数据
select cust_name, cust_contact
from productcustomers
where prod_id = 'TNT2';
## 视图用CREATE VIEW语句来创建
## 使用SHOW CREATE VIEW viewname;来查看创建视图的语句
## 用DROP删除视图，其语法为DROP VIEW viewname;
## 更新视图时，可以先用DROP再用CREATE，也可以直接用CREATE OR REPLACE VIEW
## 视图最常用的应用之一就是隐藏复杂的SQL, 这通常都会涉及联结
create view productcustomers as
select cust_name, cust_contact, prod_id
from customers, orders, orderitems
where customers.cust_id = orders.cust_id
and orderitems.order_num = orders.order_num;
## 想要把以下的语句转换为视图来重用
select Concat(RTrim(vend_name), ' (', RTrim(vend_country), ')')
as vend_title
from vendors
order by vend_name;
## 
create view vendorlocations as
select Concat(RTrim(vend_name), ' (', RTrim(vend_country), ')')
as vend_title
from vendors
order by vend_name;
## 用视图过滤不想要的数据
## 可以定义customeremaillist视图, 过滤没有电子邮件地址的客户
create view customeremaillist as
select cust_id, cust_name, cust_email
from customers
where cust_email is not null;
## 使用视图与计算字段
## 原
select order_num, prod_id, quantity, item_price, quantity*item_price as expanded_price
from orderitems
where order_num = 20005;
## 
create view orderitemsexpanded as
select order_num, prod_id, quantity, item_price, quantity*item_price as expanded_price
from orderitems;
## 并非所有视图都是可更新的。基本上可以说, 如果MySQL不
## 能正确地确定被更新的基数据, 则不允许更新(包括插入和删除)
## 这实际上意味着, 如果视图定义中有以下操作, 则不能进行视图的更新:
## 分组(使用group by和having)
## 联结
## 子查询
## 并
## 聚集函数
## distinct
## 导出(计算)列
## 创建存储过程 
CREATE PROCEDURE productpricing()
BEGIN
	SELECT Avg(prod_price) AS priceaverage
	FROM products;
END;
## 如果你使用的是mysql命令行实用程序，应该仔细阅读此说明
## 默认的MySQL语句分隔符为;
## mysql命令行实用程序也使用;作为语句分隔符
## 如果命令行实用程序要解释存储过程自身内的;字符，则它们最
## 终不会成为存储过程的成分，这会使存储过程中的SQL出现句法
## 错误. 解决办法是临时更改命令行实用程序的语句分隔符:
DELIMITER //
CREATE PROCEDURE productpricing()
BEGIN
	SELECT Avg(prod_price) AS priceaverage
	FROM products;
END//
## 除\符号外，任何字符都可以用作语句分隔符
## 如何使用存储过程?
CALL productpricing();
## 存储过程在创建之后，被保存在服务器上以供使用，直至被删除
drop procedure productpricing;
## 当过程存在想删除它时(如果过程不存在也 不产生错误)
drop procedure if exists productpricing;