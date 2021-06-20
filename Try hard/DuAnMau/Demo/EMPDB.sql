use master
go
drop database EMPDB
go
create database EMPDB
go
use EMPDB
go

CREATE TABLE nhan_vien (
	tai_khoan VARCHAR(20) not null PRIMARY KEY,
	ten nvarchar(50) not null,
	mat_khau VARCHAR(20) not null
)
go

insert into nhan_vien values 
('thaidh', N'Dương Hồng Thái', '123'),
('hailt', N'Lý Trung Hải', '123'),
('cuongnv', N'Nguyễn Văn Cường', '123'),
('huyenlt', N'Lê Thanh Huyền', '123'),
('anhht', N'Hồ Văn Anh', '123'),
('thuydh', N'Dương Hồng Thụy', '123'),
('dungnt', N'Nguyễn Thị Dung', '123')
go

create proc sp_select_nhan_vien(@ten nvarchar(50))
as
	select * from nhan_vien where ten like '%'+@ten+'%'
go

exec sp_select_nhan_vien N'Văn'





create table ban (
	x int not null,
	y int not null,
	ma int identity(1,1)
)
go

insert into ban(x, y) values 
(0, 0),
(100, 100)
go


create table khu (
	ten_khu varchar(10) not null primary key,
	so_ban_max int not null default 15,
	so_ban int not null,
	check(so_ban > 0 and so_ban <= so_ban_max)
)
go

insert into khu values ('A', 15, 15)
go

create table ban_an (
	ma int identity(1,1) primary key,
	ten_khu varchar(10) not null foreign key references khu(ten_khu),
	x int not null,
	y int not null
)
go

select * from ban_an

