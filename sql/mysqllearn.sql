## ��������
select vend_name, prod_name, prod_price
from vendors, products
where vendors.vend_id = products.vend_id
order by vend_name, prod_name;
## �ڲ�����
select vend_name, prod_name, prod_price
from vendors inner join products
on vendors.vend_id = products.vend_id
order by vend_name, prod_name;
## ��������
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
## ʹ������
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
## ������
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
## ��Ȼ����, Ӧ��������һ���г����ڲ�ֹһ������, ���ܻ�����ͬ���ж�γ���, ��Ȼ�����ų���γ���, ʹ��
## ÿ����ֻ����һ��
## ��ֻ��ѡ����ЩΨһ����. �Ա�ʹ��ͨ���(select *), �����������ʹ����ȷ���Ӿ�
select c.*, o.order_num, o.order_date, oi.prod_id, oi.quantity, oi.item_price
from customers c, orders o, orderitems oi
where c.cust_id = o.cust_id
and oi.order_num = o.order_num
and prod_id = 'FB';
## �ⲿ����: �����������Щ����ر���û�а�������
## ����һ���򵥵�����
select customers.cust_id, orders.order_num
from customers inner join orders
on customers.cust_id = orders.cust_id;
## ����, leftָ����outer join���Ϊ�����������еı�
select customers.cust_id, orders.order_num
from customers left outer join orders
on customers.cust_id = orders.cust_id;
select customers.cust_id, orders.order_num
from customers right outer join orders
on customers.cust_id = orders.cust_id;
## ʹ�ô��ۼ�����������
## ����
select customers.cust_name, customers.cust_id,
count(orders.order_num) num_ord
from customers inner join orders
on customers.cust_id = orders.cust_id
group by customers.cust_id;
## ����
select customers.cust_name, customers.cust_id,
count(orders.order_num) num_ord
from customers left outer join orders
on customers.cust_id = orders.cust_id
group by customers.cust_id;
## ʹ��union
## ��ʹ�õ������
select vend_id, prod_id, prod_price
from products
where prod_price <= 5;
select vend_id, prod_id, prod_price 
from products 
where vend_id in (1001, 1002);
## ��ʹ��union�������������Ĳ�ѯ
select vend_id, prod_id, prod_price
from products
where prod_price <= 5
union
select vend_id, prod_id, prod_price 
from products 
where vend_id in (1001, 1002);
## ��ʹ��union�ﵽ��ͬ��Ч��
select vend_id, prod_id, prod_price
from products
where prod_price <= 5 or vend_id in (1001, 1002);
## ȡ�����߰����ظ�����
select vend_id, prod_id, prod_price
from products
where prod_price <= 5
union all
select vend_id, prod_id, prod_price 
from products 
where vend_id in (1001, 1002);
## ����ϲ�ѯ�������
## ֻ�������һ��select�Ӿ�֮�����order by���
## ��������ѯ�������������
select vend_id, prod_id, prod_price
from products
where prod_price <= 5
union 
select vend_id, prod_id, prod_price 
from products 
where vend_id in (1001, 1002)
order by vend_id;
## 19�� ��������
## ��һ������
insert into customers
values (null, 'Pep E. Lapew', 
	'100 Main Street', 'Los Angeles', 
	'CA', '90046', 'USA', null, null);
## ��дinsert���ĸ���ȫ�ķ�������:
insert into customers (cust_name, cust_address,
cust_city, cust_state, cust_zip, cust_country, cust_contact, cust_email)
values ('Pep E. Lapew', '100 Main Street', 'Los Angeles', 
	'CA', '90046', 'USA', null, null);
## ��������
INSERT INTO customers (cust_name, cust_address,
cust_city, cust_state, cust_zip, cust_country)
VALUES('Pep E. LaPew', '100 Main Street',
'Los Angeles', 'CA', '90046', 'USA');
INSERT INTO customers (cust_name, cust_address,
cust_city, cust_state, cust_zip, cust_country)
VALUES('M. Martian', '42 Galaxy Way', 'New York',
'NY', '11213', 'USA');
## ����ֻҪÿ��insert����е�����(�ʹ���)��ͬ, ����������ϸ������
INSERT INTO customers (cust_name, cust_address,
cust_city, cust_state, cust_zip, cust_country)
VALUES('Pep E. LaPew', '100 Main Street',
'Los Angeles', 'CA', '90046', 'USA'),
('M. Martian', '42 Galaxy Way', 'New York',
'NY', '11213', 'USA');
## ����˼�������������ݿ⴦�������, 
## ��ΪMySQL�õ���INSERT��䴦���������ʹ�ö���INSERT���졣
