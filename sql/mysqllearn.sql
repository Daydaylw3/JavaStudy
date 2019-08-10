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