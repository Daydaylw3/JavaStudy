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
## ���������������
insert into customers(cust_id, cust_contact, cust_email, cust_name,
cust_address, cust_city, cust_state, cust_zip, cust_country)
select cust_id, cust_contact, cust_email, cust_name, cust_address, 
cust_city, cust_state, cust_zip, cust_country
from custnew;
## INSERT SELECT��SELECT���ɰ���WHERE�Ӿ��Թ��˲��������
## 20�� ���º�ɾ������
## ��Ҫʡ��WHERE�Ӿ� ��ʹ��UPDATEʱһ��Ҫע��ϸ�ġ���Ϊ �Բ�ע�⣬�ͻ���±���������
## ��һ���򵥵�����
update customers set cust_email = 'elmer@fudd.com'
where cust_id = 10005;
## ���¶����
update customers
set cust_name = 'The Fudds',
	cust_email = 'elmer@fudd.com'
where cust_id = 10005;
## Ϊ��ɾ��ĳ���е�ֵ, ��������Ϊnull(������п���Ϊnull)
update customers
set cust_email = null
where cust_id = 10005;
## ɾ������
delete from customers
where cust_id = 10006;
## delete����Ҫ��������ͨ���, ��ɾ�������������ݶ�������
## �����ӱ���ɾ��������, ��Ҫʹ��DELETE. ��ʹ��TRUNCATE TABLE���,
## �������ͬ�Ĺ���, ���ٶȸ���(TRUNCATEʵ����ɾ��ԭ���ı����´���һ��
## ��, ����������ɾ�����е�����
## 22�� ��ͼ
## ��ͼ������ı���������ݵı�һ������ͼֻ����ʹ��ʱ��̬ �������ݵĲ�ѯ
select cust_name, cust_contact
from customers, orders, orderitems
where customers.cust_id = orders.cust_id
and orderitems.order_num = orders.order_num
and prod_id = 'TNT2';
## ���ڣ�������԰�������ѯ��װ��һ����Ϊproductcustomers������������
## �������ɵؼ�������ͬ������
select cust_name, cust_contact
from productcustomers
where prod_id = 'TNT2';
## ��ͼ��CREATE VIEW���������
## ʹ��SHOW CREATE VIEW viewname;���鿴������ͼ�����
## ��DROPɾ����ͼ�����﷨ΪDROP VIEW viewname;
## ������ͼʱ����������DROP����CREATE��Ҳ����ֱ����CREATE OR REPLACE VIEW
## ��ͼ��õ�Ӧ��֮һ�������ظ��ӵ�SQL, ��ͨ�������漰����
create view productcustomers as
select cust_name, cust_contact, prod_id
from customers, orders, orderitems
where customers.cust_id = orders.cust_id
and orderitems.order_num = orders.order_num;
## ��Ҫ�����µ����ת��Ϊ��ͼ������
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
## ����ͼ���˲���Ҫ������
## ���Զ���customeremaillist��ͼ, ����û�е����ʼ���ַ�Ŀͻ�
create view customeremaillist as
select cust_id, cust_name, cust_email
from customers
where cust_email is not null;
## ʹ����ͼ������ֶ�
## ԭ
select order_num, prod_id, quantity, item_price, quantity*item_price as expanded_price
from orderitems
where order_num = 20005;
## 
create view orderitemsexpanded as
select order_num, prod_id, quantity, item_price, quantity*item_price as expanded_price
from orderitems;
## ����������ͼ���ǿɸ��µġ������Ͽ���˵, ���MySQL��
## ����ȷ��ȷ�������µĻ�����, ���������(���������ɾ��)
## ��ʵ������ζ��, �����ͼ�����������²���, ���ܽ�����ͼ�ĸ���:
## ����(ʹ��group by��having)
## ����
## �Ӳ�ѯ
## ��
## �ۼ�����
## distinct
## ����(����)��
## �����洢���� 
CREATE PROCEDURE productpricing()
BEGIN
	SELECT Avg(prod_price) AS priceaverage
	FROM products;
END;
## �����ʹ�õ���mysql������ʵ�ó���Ӧ����ϸ�Ķ���˵��
## Ĭ�ϵ�MySQL���ָ���Ϊ;
## mysql������ʵ�ó���Ҳʹ��;��Ϊ���ָ���
## ���������ʵ�ó���Ҫ���ʹ洢���������ڵ�;�ַ�����������
## �ղ����Ϊ�洢���̵ĳɷ֣����ʹ�洢�����е�SQL���־䷨
## ����. ����취����ʱ����������ʵ�ó�������ָ���:
DELIMITER //
CREATE PROCEDURE productpricing()
BEGIN
	SELECT Avg(prod_price) AS priceaverage
	FROM products;
END//
## ��\�����⣬�κ��ַ��������������ָ���
## ���ʹ�ô洢����?
CALL productpricing();
## �洢�����ڴ���֮�󣬱������ڷ��������Թ�ʹ�ã�ֱ����ɾ��
drop procedure productpricing;
## �����̴�����ɾ����ʱ(������̲�����Ҳ ����������)
drop procedure if exists productpricing;