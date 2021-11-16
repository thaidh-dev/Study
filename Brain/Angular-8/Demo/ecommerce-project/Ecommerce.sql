use master
go

if db_id('ecommerce') is not null 
	drop database database_goc
go

if db_id('ecommerce') IS NULL
	create database ecommerce
go

use ecommerce
go

create table product_category (
	id int identity(1,1) not null primary key,
	category_name varchar(255) null default null
)
go

INSERT INTO PRODUCT_CATEGORY(CATEGORY_NAME) VALUES ('BOOKS');
go

create table product (
	id INT identity(1,1) NOT NULL primary key,
	sku VARCHAR(255) DEFAULT NULL,
	ten VARCHAR(255) DEFAULT NULL,
	mo_ta VARCHAR(255) DEFAULT NULL,
	unit_price DECIMAL(13,2) DEFAULT NULL,
	image_url VARCHAR(255) DEFAULT NULL,
	active BIT DEFAULT 1,
	units_in_stock INT DEFAULT NULL,
	date_created DATETIME DEFAULT NULL,
	last_updated DATETIME DEFAULT NULL,
	category_id INT NOT NULL foreign key references product_category(id)
)

INSERT INTO PRODUCT (SKU, ten, mo_ta, IMAGE_URL, ACTIVE, UNITS_IN_STOCK, UNIT_PRICE, CATEGORY_ID,DATE_CREATED) VALUES 
('BOOK-TECH-1000', 'JavaScript - The Fun Parts', 'Learn JavaScript', 
'assets/images/products/placeholder.png', 1, 100, 19.99, 1, getdate()),
('BOOK-TECH-1001', 'Spring Framework Tutorial', 'Learn Spring',
'assets/images/products/placeholder.png', 1, 100, 29.99, 1, getdate()),
('BOOK-TECH-1002', 'Kubernetes - Deploying Containers', 'Learn Kubernetes',
'assets/images/products/placeholder.png', 1, 100, 24.99, 1, getdate()),
('BOOK-TECH-1003', 'Internet of Things (IoT) - Getting Started', 'Learn IoT',
'assets/images/products/placeholder.png'
, 1, 100, 29.99, 1, getdate()),
('BOOK-TECH-1004', 'The Go Programming Language: A to Z', 'Learn Go',
'assets/images/products/placeholder.png'
, 1, 100, 24.99, 1, getdate())
go

/**
select * from product
select * from product_category
**/