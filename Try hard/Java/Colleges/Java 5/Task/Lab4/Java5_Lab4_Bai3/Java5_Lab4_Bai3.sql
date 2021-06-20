use master
go
drop database java5_lab4_bai3
go
create database java5_lab4_bai3
go
use java5_lab4_bai3
go

create table san_pham (
	id int identity(1,1) primary key,
	ten_san_pham varchar(50) not null,
	gia_cu money not null,
	giam_gia int not null
	check (giam_gia >= 0 and giam_gia <= 100)
)
go

insert into san_pham(ten_san_pham, gia_cu, giam_gia) values
('ao', 1000, 10),
('quan', 3000, 5),
('cap sach', 5000, 20),
('may tinh ban', 2000, 0),
('laptop', 7000, 10),
('bon tam', 5000, 3),
('dien thoai di dong', 10000, 50)
go

select * from san_pham
