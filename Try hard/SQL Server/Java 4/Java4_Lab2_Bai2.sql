create database java4_lab2_bai2

use java4_lab2_bai2

create table product (
Code nvarchar(50) not null primary key,
Name nvarchar(50) not null,
Price float not null
)

insert into product values
('sp01', 'Nokia', 20000),
('sp02', 'Apple', 50000),
('sp03', 'BPhone', 80000),
('sp04', 'Yonex', 30000),
('sp05', 'IPhone', 90000),
('sp06', 'Oracle', 10000),
('sp07', 'Samsung', 20000)

select * from product

drop table product
