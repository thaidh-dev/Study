use master
go
drop database java4_lab8_bai1
go
create database java4_lab8_bai1
go
use java4_lab8_bai1
go

create table phong_ban (
	ma_phong int identity(1,1) not null primary key,
	ten_phong nvarchar(50),
)
go

insert into phong_ban(ten_phong) values
(N'Hành chính'),
(N'IT')
go

create table nhan_vien (
	ma_nhan_vien int identity(1,1) not null primary key,
	ten_nhan_vien nvarchar(50) not null,
	email varchar(50) not null unique,
	ma_phong int not null foreign key references phong_ban(ma_phong)
)
go

insert into nhan_vien(ten_nhan_vien, email, ma_phong) values
(N'Dương Hồng Thái', 'thaidh@gmail.com', 1),
(N'Nguyễn Văn Cường', 'cuongnv@gmail.com', 1),
(N'Hoàng Công Việt Anh', 'anhhtv@gmail.com', 2),
(N'Nguyễn Trí Đức', 'ducnt@gmail.com', 1), 
(N'Hoàng Công Nam', 'namhc@gmail.com', 2),
(N'Lê Thanh Huyền', 'huyenlt@gmail.com', 1),
(N'Lê Linh Chi', 'chill@gmail.com', 2)
go

    select * from nhan_vien where ma_phong = 2
    select * from phong_ban
