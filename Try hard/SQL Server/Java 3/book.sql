create database book
go
use book
go

create table book (
id nvarchar(50) not null primary key,
title nvarchar(50) not null,
price money not null
)
go

insert into book values
('1', N'Lập trình java 3', 10000),
('2', N'Harry Potter', 500000),
('3', N'Ngồi khóc trên cây', 150000),
('4', N'Ngày xưa có một truyện tình', 400000),
('5', N'Góc sân và khoảng trời', 20000),
('6', N'Biên niên sử Narnia', 50000),
('7', N'Ngữ văn 10', 2000)

select * from book