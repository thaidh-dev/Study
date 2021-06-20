DROP DATABASE IF EXISTS demo_unit_test;

CREATE DATABASE IF NOT EXISTS demo_unit_test;

use demo_unit_test;

create table item (
id int not null AUTO_INCREMENT,
name nvarchar(10) not null,
price int not null,
quantity int not null,
primary key(id)
);

insert into item(name, price, quantity) values
(N'Áo', 10000, 7),
(N'Quần', 90000, 8),
(N'Ví', 100000, 2),
(N'Đồng hồ', 50000, 3),
(N'Laptop', 30000, 8),
(N'Nhẫn', 80000, 6),
(N'Tất', 20000, 9);

select * from item;
