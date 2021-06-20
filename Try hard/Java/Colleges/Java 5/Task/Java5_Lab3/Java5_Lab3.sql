use master
go
drop database java5_lab3
go
create database java5_lab3
go
use java5_lab3
go

create table nganh (
	id_nganh int identity(1,1) primary key,
	ten_nganh nvarchar(50) not null
)
go

create table sinh_vien (
	id_sinh_vien int identity(1,1) primary key,
	ho_ten nvarchar(50) not null,
	diem decimal(18, 1) not null,
	id_nganh int foreign key references nganh(id_nganh)
)
go

insert into nganh(ten_nganh) values 
(N'Công nghệ thông tin'),
(N'Đồ họa'),
(N'Thiết kế Web')
go

insert into sinh_vien(ho_ten, diem, id_nganh) values
('dendi', 7, 1),
('puppey', 8, 1),
('notail', 5, 2),
('topson', 3, 3),
('miracle', 8, 2),
('kuroky', 7, 3),
('ceb', 4, 1)
go

select * from sinh_vien where diem = (select max(diem) from sinh_vien)



