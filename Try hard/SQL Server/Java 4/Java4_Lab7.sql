use master
go
drop database java4_lab7
go
create database java4_lab7
go
use java4_lab7
go

create table customers (
	makhachhang varchar(50) not null primary key,
	matkhau varchar(50) null,
	hoten varchar(50) null,
	email varchar(50) null,
	dienthoai varchar(50) null
)
go

insert into customers values
('KH01', '123', 'Nguyen Nghiem', 'nghiemn@fe.edu.vn', '0966250199'),
('KH02', 'abc', 'Tran Duy Phong', 'phongtd@fe.edu.vn', '0966250199'),
('KH03', '123', 'Le Pham Tuan Kiet', 'kietlpt@fe.edu.vn', '0966250199'),
('KH04', 'abc', 'Le Van Phung', 'phunglv@fe.edu.vn', '0966250199'),
('KH05', 'bcd', 'Bui Minh Nhat', 'nhatbm@fe.edu.vn', '0966250199')
go

select * from customers

select * from thai
