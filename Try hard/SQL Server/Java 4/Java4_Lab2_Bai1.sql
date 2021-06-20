use master
go
drop database java4_lab2_bai1
go

create database java4_lab2_bai1
go

use java4_lab2_bai1
go

create table danh_ba (
id int identity(1,1) not null primary key,
ten nvarchar(50) not null,
sdt nvarchar(50) not null unique,
dia_chi nvarchar(50) not null
)
go

insert into danh_ba values
('Duong Hong Thai', '0966250191', 'Ha Noi'),
('Duong Hong A', '0966250192', 'Cao Bang'),
('Duong Hong B', '0966250193', 'Lang Son'),
('Duong Hong C', '0966250194', 'Hai Phong'),
('Duong Hong D', '0966250195', 'Ha Noi'),
('Duong Hong E', '0966250196', 'Hai Phong'),
('Duong Hong F', '0966250197', 'Cao Bang'),
('Duong Hong g', '0966250198', 'Lang Son'),
('Duong Hong h', '0966250199', 'Ha Noi'),
('Duong Hong i', '0966250110', 'Lang Son'),
('Duong Hong k', '0966250111', 'Cao bang'),
('Duong Hong l', '0966250112', 'Hai Phong'),
('Duong Hong m', '0966250113', 'Cao bang'),
('Duong Hong Thai', '0976250191', 'Ha Noi'),
('Duong Hong A', '0966950192', 'Cao Bang'),
('Duong Hong B', '0966550193', 'Lang Son'),
('Duong Hong C', '0966350194', 'Hai Phong'),
('Duong Hong D', '0966293195', 'Ha Noi'),
('Duong Hong E', '0966750196', 'Hai Phong')
go

select * from danh_ba
select * from danh_ba where ten like '%thai' and sdt = '0966250191' and dia_chi like '%ha noi%'
select * from danh_ba where ten like '%thai' and sdt = '0966250191'
select * from danh_ba where ten like '%thai' and dia_chi like '%ha noi%'
select * from danh_ba where ten like '%thai'
select * from danh_ba where sdt = '0966250191' and dia_chi like '%ha noi%'
select * from danh_ba where sdt = '0966250191'
select * from danh_ba where dia_chi like '%ha noi%'


drop table danh_ba

