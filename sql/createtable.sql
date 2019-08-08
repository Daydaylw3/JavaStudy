-- 创建数据库 --
create database demo2
default character set utf8;
-- 创建用户并赋予该数据库权限 --
create user 'demo2'@'localhost';
grant all privileges on demo2.* to 'demo2'@'localhost';

-- 用新建用户登录数据库 & 创建vendors表 --
create table vendors (
	vend_id      int         NOT NULL AUTO_INCREMENT COMMENT '唯一供应商id',
	vend_name    varchar(50) NOT NULL                COMMENT '供应商名',
	vend_address varchar(50) NULL                    COMMENT '供应商地址',
	vend_city    varchar(50) NULL                    COMMENT '供应商城市',
	vend_state   varchar(5)  NULL                    COMMENT '供应商州',
	vend_zip     varchar(10) NULL                    COMMENT '供应商邮政编码',
	vend_country varchar(50) NULL                    COMMENT '供应商国家',
	PRIMARY KEY (vend_id)
) COMMENT = '存储销售商品的供应商表';

-- 新建products表 --
create table products (
	prod_id    varchar(10)  NOT NULL COMMENT '唯一产品id',
	vend_id    int          NOT NULL COMMENT '产品供应商id',
	prod_name  varchar(255) NOT NULL COMMENT '产品名',
	prod_price decimal(8,2) NOT NULL COMMENT '产品价格',
	prod_desc  TEXT         NULL     COMMENT '产品描述',
	PRIMARY KEY (prod_id),
	CONSTRAINT fk_products_vendors FOREIGN KEY (vend_id) REFERENCES vendors (vend_id)
) COMMENT = '销售商品表';

-- 新建customers表 --
create table customers (
	cust_id      int          NOT NULL AUTO_INCREMENT COMMENT '唯一的顾客id',
	cust_name    varchar(50)  NOT NULL                COMMENT '顾客名',
	cust_address varchar(50)  NULL                    COMMENT '顾客地址',
	cust_city    varchar(50)  NULL                    COMMENT '顾客的城市',
	cust_state   varchar(5)   NULL                    COMMENT '顾客的',
	cust_zip     varchar(10)  NULL                    COMMENT '顾客的邮政编码',
	cust_country varchar(50)  NULL                    COMMENT '顾客的国家',
	cust_contact varchar(50)  NULL                    COMMENT '顾客的联系名',
	cust_email   varchar(255) NULL                    COMMENT '顾客的联系email地址',
	PRIMARY KEY (cust_id)
) COMMENT = '所有顾客信息';

-- 新建orders表 --
create table orders (
	order_num  int      NOT NULL AUTO_INCREMENT COMMENT '唯一的订单号',
	order_date datetime NOT NULL                COMMENT '订单日期',
	cust_id    int      NOT NULL                COMMENT '订单顾客id',
	PRIMARY KEY (order_num),
	CONSTRAINT fk_orders_customers FOREIGN KEY (cust_id) REFERENCES customers (cust_id)
) COMMENT = '顾客订单表';

-- 新建orderitems表 --
create table orderitems (
	order_num  int          NOT NULL COMMENT '订单号',
	order_item int          NOT NULL COMMENT '订单物品号（在某个订单中的顺序）',
	prod_id    varchar(10)  NOT NULL COMMENT '产品id',
	quantity   int          NOT NULL COMMENT '物品数量',
	item_price decimal(8,2) NOT NULL COMMENT '物品价格',
	PRIMARY KEY (order_num, order_item),
	CONSTRAINT fk_orderitems_orders FOREIGN KEY (order_num) REFERENCES orders (order_num),
	CONSTRAINT fk_orderitems_products FOREIGN KEY (prod_id) REFERENCES products (prod_id)
) COMMENT = '存储每个订单中的实际物品';

-- 新建productnotes表 --
create table productnotes (
	note_id   int         NOT NULL AUTO_INCREMENT COMMENT '唯一注释id',
	prod_id   varchar(10) NOT NULL                COMMENT '产品id',
	note_date datetime    NOT NULL                COMMENT '增加注释的日期',
	note_text text        NULL                    COMMENT '注释文本',
	PRIMARY KEY (note_id),
	CONSTRAINT fk_productnotes_products FOREIGN KEY (prod_id) REFERENCES products (prod_id)
) ENGINE=MyISAM COMMENT = '产品说明表';