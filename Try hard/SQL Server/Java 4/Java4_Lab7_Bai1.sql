use master
go
drop database java4_lab7_bai1
go
create database java4_lab7_bai1
go
use java4_lab7_bai1
go

create table khach_hang (
	ma_khach_hang int identity(1,1) not null primary key,
	ten nvarchar(50) not null,
	email varchar(50) not null unique,
	mat_khau varchar(50) not null,
	sdt varchar(10) not null unique
)
go

insert into khach_hang(ten, email, mat_khau, sdt) values
(N'Dương Hồng Thái', 'duonghongthai@gmail.com', '123', '0966250199'),
(N'Nguyễn Văn Nam', 'nguyenvannam@gmail.com', '123', '0966250198'),
(N'Nguyễn Thị Sen', 'nguyenthisen@gmail.com', '123', '0966250197'),
(N'Bùi Đức Chính', 'buiducchinh@gmail.com', '123', '0966250196'),
(N'Bùi Đức Chính', 'chinhbd@gmail.com', '123', '0966250111'),
(N'Dương Thu Trang', 'duongthutrang@gmail.com', '123', '0966250195')
go

drop table khach_hang

select * from khach_hang where ten = N'Bùi Đức Chính' or sdt = N'Bùi Đức Chính'
