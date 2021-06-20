create database java4_lab3_bai1
go
use java4_lab3_bai1
go

create table tai_khoan (
username nvarchar(50) not null primary key,
pass nvarchar(50) not null,
ten nvarchar(50) not null,
is_admin bit not null
)

insert into tai_khoan values
('A', '123', 'Lan', 0),
('B', '123', 'Khue', 0),
('C', '123', 'Hai', 1),
('D', '123', 'Cuong', 0),
('E', '123', 'Tung', 0),
('F', '123', 'Nam', 1),
('G', '123', 'Bich', 0)

select * from tai_khoan where ten = 'Lan'
